<?php
include("../includes/connect.php");
//entry,id_no,account,fname,lname,email,phone,country,location,password,status,comment,reg_date
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $fname = $_POST["fname"];
    $lname = $_POST['lname'];
    $phone = $_POST['phone'];
    $email = $_POST['email'];
    $password = md5($_POST['password']);
    $account = $_POST['account'];
    $id_no = $_POST['id_no'];
    $country = $_POST['county'];
    $location=$_POST["location"];
    $year = date("Y");
    $mon = date("M");
    $select = "SELECT email FROM client WHERE email='$email'";
    $query = mysqli_query($db, $select);
    if (mysqli_num_rows($query) > 0) {
        $result["success"] = 0;
        $result["message"] = "Email not accepted";
        echo json_encode($result);
        mysqli_close($db);
    } else {
        $select = "SELECT id_no FROM client WHERE id_no='$id_no'";
        $query = mysqli_query($db, $select);
        if (mysqli_num_rows($query) > 0) {
            $result["success"] = 0;
            $result["message"] = "ID NO not Accepted";
            echo json_encode($result);
            mysqli_close($db);
        } else {
            $select = "SELECT account FROM client WHERE account='$account'";
            $query = mysqli_query($db, $select);
            if (mysqli_num_rows($query) > 0) {
                $result["success"] = 0;
                $result["message"] = "Account number not accepted";
                echo json_encode($result);
                mysqli_close($db);
            } else {
                $select = "SELECT phone FROM client WHERE phone='$phone'";
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
                    $sql = "INSERT INTO client(entry,id_no,account,fname,lname,email,phone,country,location,password)
    VALUES('$entry','$id_no','$account','$fname','$lname','$email','$phone','$country','$location','$password')";
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
    }
}