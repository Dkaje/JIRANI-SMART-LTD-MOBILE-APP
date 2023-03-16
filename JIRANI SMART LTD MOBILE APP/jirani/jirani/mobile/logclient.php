<?php
include("../includes/connect.php");
//entry,id_no,account,fname,lname,email,phone,country,location,password,status,comment,reg_date
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $email = $_POST['email'];
    $password = md5($_POST['password']);
    $sql = "SELECT * FROM client WHERE email='$email' AND password='$password'";
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
            $result = array(); //entry,id_no,account,fname,lname,email,phone,country,location,password,status,comment,reg_date
            $result['login'] = array();
            $index['entry'] = $row['entry'];
            $index['id_no'] = $row['id_no'];
            $index['account'] = $row['account'];
            $index['fname'] = $row['fname'];
            $index['lname'] = $row['lname'];
            $index['email'] = $row['email'];
            $index['phone'] = $row['phone'];
            $index['country'] = $row['country'];
            $index['agent_email'] = $row['agent_email'];
            $index['location'] = $row['location'];
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