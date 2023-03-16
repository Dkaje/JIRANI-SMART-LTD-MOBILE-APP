<?php
include("../includes/connect.php");
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $borrow_id = $_POST['borrow_id'];
    $status = $_POST['status'];
        $cate = mysqli_query($db, "UPDATE loan_borrow set status='$status' where borrow_id='$borrow_id'");
        if ($cate) {
            $response["success"] = 1;
            $response["message"] = "Rejection was sucessful";
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to update";
        }
        echo json_encode($response);
        mysqli_close($db);
    }