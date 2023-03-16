<?php
include("../includes/connect.php");
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    //agent_email,entry,id_no,county,location,name,phone,email
    //agent_email,entry,id_no,county,location,name,phone,email
    $agent_email = $_POST["agent_email"];
    $entry = $_POST["entry"];
    $id_no = $_POST["id_no"];
    $county = $_POST["county"];
    $location = $_POST["location"];
    $name = $_POST["name"];
    $phone = $_POST["phone"];
    $email = $_POST["email"];
    $query = mysqli_query($db, "UPDATE client set agent_email='$agent_email' where entry='$entry'");
    if ($query) {
        mysqli_query($db,"INSERT into collateral(agent_email,id_no,county,location,name,phone,email)
        values('$agent_email','$id_no','$county','$location','$name','$phone','$email')");
        $response["success"] = 1;
        $response["message"] = "Attachment was successfully";
        echo json_encode($response);
        mysqli_close($db);
    } else {
        $response["success"] = 0;
        $response["message"] = "Failed to attach Agent";
        echo json_encode($response);
        mysqli_close($db);
    }
}