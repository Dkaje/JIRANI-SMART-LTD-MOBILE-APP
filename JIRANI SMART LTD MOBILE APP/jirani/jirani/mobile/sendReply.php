<?php
include("../includes/connect.php");
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id = $_POST['id'];
    $reply = $_POST['reply'];
    $sql = "UPDATE feedback set reply='$reply' where id='$id'";
    if (mysqli_query($db, $sql)) {
        $response["success"] = 1;
        $response["message"] = "reply sent";

        echo json_encode($response);
        mysqli_close($db);
    } else {
        $response["success"] = 0;
        $response["message"] = "Failed";

        echo json_encode($response);
        mysqli_close($db);
    }
}