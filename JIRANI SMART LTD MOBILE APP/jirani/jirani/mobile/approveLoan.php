<?php
//borrow_id,status,loan_id,id_no,name,phone,loan
include("../includes/connect.php");
    $borrow_id = $_POST["borrow_id"];
    $status = $_POST["status"];
    $loan_id = $_POST["loan_id"];
    $id_no = $_POST["id_no"];
    $name = $_POST["name"];
    $phone = $_POST["phone"];
    $loan = $_POST["loan"];
    $amount = $_POST["amount"];
    if (mysqli_query($db, "UPDATE loan_borrow set status='$status' where borrow_id='$borrow_id'")) {
        mysqli_query($db,"INSERT into application(borrow_id,loan_id,id_no,name,phone,loan,amount)
        values('$borrow_id','$loan_id','$id_no','$name','$phone','$loan','$amount')");
     $res["status"]=1;
     $res["message"]="Loan Application was Approved successfully";
 }else{
     $res["status"]=0;
     $res["message"]="Failed to approve loan";
 }
        echo json_encode($res);
        mysqli_close($db);
