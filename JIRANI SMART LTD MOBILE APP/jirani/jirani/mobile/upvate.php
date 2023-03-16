<?php
include("../includes/connect.php");
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $reg_id = $_POST['reg_id'];
    $status = $_POST['status'];
        $cate = mysqli_query($db, "UPDATE collateral set status_auc='$status' where reg_id='$reg_id'");
        if ($cate) {
            $response["success"] = 1;
            $response["message"] = "Update was sucessful";
            echo json_encode($response);
            mysqli_close($db);
        } else {
            $response["success"] = 0;
            $response["message"] = " Failed to update";
            echo json_encode($response);
            mysqli_close($db);
        }
    }