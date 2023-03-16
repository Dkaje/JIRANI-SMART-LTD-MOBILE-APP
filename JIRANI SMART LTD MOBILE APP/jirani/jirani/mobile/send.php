<?php
include("../includes/connect.php");
//id,message,sender,name,phone,send_date,reply,reply_date//feedback
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $message = $_POST["message"];
    $sender = $_POST["sender"];
    $phone = $_POST["phone"];
    $name = $_POST["name"];
    $sql = mysqli_query($db,"INSERT into feedback(message,sender,name,phone)
        values('$message','$sender','$name','$phone')");
      if ($sql) { 
       $response["success"] = 1;
        $response["message"] = "Message sent";
        echo json_encode($response);
        mysqli_close($db);
    } else {
        $response["success"] = 0;
        $response["message"] = "Could not send message";
        echo json_encode($response);
        mysqli_close($db);
    }
}