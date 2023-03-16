<?php
include("../includes/connect.php");
//reg_id,borrow_id,amount,status
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $reg_id = $_POST['reg_id'];
    $status = $_POST['status'];
    $borrow_id = $_POST['borrow_id'];
    $amount = $_POST['amount'];
    $interest = $_POST['interest'];
    if($status=='Rejected'){
        if (mysqli_query($db, "UPDATE loaner set status='$status' where reg_id='$reg_id'")) {
            $response["status"] = 1;
            $response["message"] = "Rejection was successful";
        } else {
            $response["status"] = 0;
            $response["message"] = " Failed to Reject";
        }
    }else{
        if (mysqli_query($db, "UPDATE loaner set status='$status' where reg_id='$reg_id'")) {
            mysqli_query($db, "UPDATE banker set interest=(interest+$interest),balance=(balance+$interest)");
            $exp=mysqli_fetch_assoc(mysqli_query($db,"SELECT expected_monthly as sta from loan_borrow where borrow_id='$borrow_id'"));
            $expected=$exp['sta'];
            if(mysqli_query($db,"UPDATE loan_borrow inner join loaner on 
            loan_borrow.borrow_id=loaner.borrow_id set loan_borrow.period=round((((loan_borrow.balance-($amount)))/$expected),0), loan_borrow.balance=round((loan_borrow.balance-$amount),0) where loan_borrow.borrow_id='$borrow_id'")){
           $three = mysqli_fetch_assoc(mysqli_query($db, "SELECT balance as meja from loan_borrow where borrow_id='$borrow_id'"));
           if($three['meja']==0){
            mysqli_query($db, "UPDATE loan_borrow set payment_status='Cleared',fina_status='Recommended' where borrow_id='$borrow_id'");
            mysqli_query($db, "UPDATE application set fina_status='Cleared' where borrow_id='$borrow_id'");
        }
        $response["status"] = 1;
        $response["message"] = "Approval was successful";
        }
        } else {
            $response["status"] = 0;
            $response["message"] = " Failed to Approve";
        }
    }
    echo json_encode($response);
    mysqli_close($db);
    }