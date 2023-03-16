<?php
//reg_id,borrow_id,mpesa,id_no,name,phone,amount,current_period,total,status,remarks,reg_date

include("../includes/connect.php");
function test_input($data)
{
    $data = trim($data);
    $data = stripslashes($data);
    $data = htmlspecialchars($data);
    return $data;
}
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $phone = $_POST['phone'];
    $id_no = $_POST['id_no'];
    $name = $_POST['name'];
    $amount=$_POST["amount"];
    $interest=$_POST["interest"];
    $period=$_POST["period"];
    $expected=$_POST["expected"];
    $current_period=$_POST["current_period"];
    $total=$_POST["total"];
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
        if(mysqli_num_rows(mysqli_query($db,"SELECT * from loaner where id_no='$id_no' and status='Pending'"))>0){
            $response["status"] = 0;
            $response["message"] = "Oops! It seems You have a previous payment. Kindly wait for approval";
        }else{
            if(mysqli_num_rows(mysqli_query($db,"SELECT * from loaner where mpesa='$mpesa'"))>0){
                $response["status"] = 0;
                $response["message"] = "Expired MPESA code";
            }else{
                if(mysqli_query($db,"INSERT into loaner(borrow_id,mpesa,id_no,name,phone,amount,expected,current_period,total,interest,period)
                values('$borrow_id','$mpesa','$id_no','$name','$phone','$amount','$expected','$current_period','$total','$interest','$period')")){
                $response["status"] = 1;
                $response["message"] = "payment was successful";
            } else {
                $response["status"] = 0;
                $response["message"] = " Failed to disburse";
            }
        }
    }
}
    echo json_encode($response);
    mysqli_close($db);
    }