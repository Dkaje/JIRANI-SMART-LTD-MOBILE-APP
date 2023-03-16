<?php
include("../includes/connect.php");
//entry,fname,lname,email,phone,role,password,status,comment,reg_date
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $email = $_POST['email'];
    $password = md5($_POST['password']);
    $sql = "SELECT * FROM staff WHERE email='$email' AND password='$password' and role='Inventory'";
    $response = mysqli_query($db, $sql);
    if (mysqli_num_rows($response) === 1) {
        $row = mysqli_fetch_array($response);
        if ($row['status'] == 0) {
            $result['success'] = "0";
            $result['message'] = "Access to your Account is pending";
            echo json_encode($result);
        } elseif ($row['status'] == 2) {
            $result = array();
            $result['login'] = array();
            $index['remarks'] = $row['comment'];
            $result['success'] = "2";
            $result['message'] = "Access to your account is blocked";
            array_push($result['login'], $index);
            echo json_encode($result);
        } else {
            $result = array();
            $result['login'] = array();
            $index['entry'] = $row['entry'];
            $index['fname'] = $row['fname'];
            $index['lname'] = $row['lname'];
            $index['email'] = $row['email'];
            $index['phone'] = $row['phone'];
            $index['role'] = $row['role'];
            $index['reg_date'] = $row['reg_date'];
            array_push($result['login'], $index);
            $result['success'] = "1";
            $result['message'] = "Login was successful";
            echo json_encode($result);
            mysqli_close($db);
        }
    } else {
        $result['success'] = "0";
        $result['message'] = "Account not found";
        echo json_encode($result);
        mysqli_close($db);
    }
}