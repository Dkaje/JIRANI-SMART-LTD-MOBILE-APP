<?php
include("../includes/connect.php");
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $borrow_id = $_POST['borrow_id'];
    $id=$_POST["id"];
        $cate = mysqli_query($db, "UPDATE loan_borrow set fina_status='Defaulter' where borrow_id='$borrow_id'");
        if ($cate) {
            mysqli_query($db, "UPDATE application set fina_status='Defaulter' where id='$id'");
            $response["success"] = 1;
            $response["message"] = "Confirmation was sucessful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to confirm";
        }
        echo json_encode($response);
        mysqli_close($db);
    }