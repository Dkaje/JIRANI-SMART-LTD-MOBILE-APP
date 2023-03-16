<?php
include("../includes/connect.php");
function test_input($data)
{
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = $_POST['id'];
    $loan = $_POST['loan'];
    $amount=$_POST["amount"];
    $borrow_id=$_POST["borrow_id"];
    $mpesa = test_input($_POST["mpesa"]);
    $caps = "/^[A-Z]{10,}$/";
    $nums = "/^[0-9]{10,}$/";
    if (preg_match($caps, $mpesa)) {
        $response["status"] = 0;
        $response["message"] = "invalid MPESA";
    } elseif (preg_match($nums, $mpesa)) {
        $response["status"] = 0;
        $response["message"] = "invalid MPESA";
    }else {
            if(mysqli_num_rows(mysqli_query($db,"SELECT * from application where mpesa='$mpesa'"))>0){
                $response["status"] = 0;
                $response["message"] = "Expired MPESA code";
            }else{
        $cate = mysqli_query($db, "UPDATE application set mpesa='$mpesa',status='Disbursed' where id='$id'");
        if ($cate) {
            mysqli_query($db,"UPDATE banker set disbursed=(disbursed+$loan),balance=(balance-$loan)");
            mysqli_query($db,"UPDATE loan_borrow set balance=$amount,disburse='Disbursed' where borrow_id='$borrow_id'");
            $response["status"] = 1;
            $response["message"] = "Disbursement was successful";
        } else {
            $response["status"] = 0;
            $response["message"] = " Failed to disburse";
        }
    }
}
    echo json_encode($response);
    mysqli_close($db);
    }