<?php
include("../includes/connect.php");
//entry,fname,lname,email,phone,role,password,status,comment,reg_date
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $fname = $_POST["fname"];
    $lname = $_POST['lname'];
    $phone = $_POST['phone'];
    $email = $_POST['email'];
    $password = md5($_POST['password']);
    $role = $_POST['role'];
    $year = date("Y");
    $mon = date("M");
    $select = "SELECT email FROM staff WHERE email='$email'";
    $query = mysqli_query($db, $select);
    if (mysqli_num_rows($query) > 0) {
        $result["success"] = 0;
        $result["message"] = "Email not accepted";
        echo json_encode($result);
        mysqli_close($db);
    } else {
        $select = "SELECT phone FROM staff WHERE phone='$phone'";
        $query = mysqli_query($db, $select);
        if (mysqli_num_rows($query) > 0) {
            $response["success"] = 0;
            $response["message"] = "Phone number not accepted";
            echo json_encode($response);
            mysqli_close($db);
        } else {
            $count_my_page = ("../includes/jirani.txt");
            $hits = file($count_my_page);
            $hits[0]++;
            $fp = fopen($count_my_page, "w");
            fputs($fp, "$hits[0]");
            fclose($fp);
            $values = $hits[0];
            $entry = $values . '~' . $year;
            $sql = "INSERT INTO staff(entry,fname,lname,email,phone,role,password)
    VALUES('$entry','$fname','$lname','$email','$phone','$role','$password')";
            if (mysqli_query($db, $sql)) {
                $response["success"] = 1;
                $response["message"] = "Account created. Kindly wait for Approval";
                echo json_encode($response);
                mysqli_close($db);
            } else {
                $response["success"] = 0;
                $response["message"] = " Failed to create account";
                echo json_encode($response);
                mysqli_close($db);
            }
        }
    }
}