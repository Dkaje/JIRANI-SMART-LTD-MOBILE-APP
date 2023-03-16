<?php
include("../includes/connect.php");
$email = $_POST['email'];
$password = md5($_POST['password']);
$sql = "SELECT * FROM staff WHERE email='$email' and role='Auction'";
$response = mysqli_query($db, $sql);
if (mysqli_num_rows($response) === 1) {
    mysqli_query($db, "UPDATE staff set password='$password' where email='$email' and role='Auction'");
    $row = mysqli_fetch_assoc($response);
    $result['success'] = 1;
    $result['message'] =  "Password was reset";
    echo json_encode($result);
    mysqli_close($db);
} else {
    $result['success'] = 0;
    $result['message'] = "Account not found";
    echo json_encode($result);
    mysqli_close($db);
}