<?php
//loan_type,interest,repayment,amount
//id,loan_type,r_period,rate,amount,reg_date//loan
include("../includes/connect.php");
    $id = $_POST["id"];
    $interest = $_POST["interest"];
    $repayment = $_POST["repayment"];
    $amount = $_POST["amount"];
    if(mysqli_query($db,"UPDATE loan set r_period='$repayment',rate='$interest',amount='$amount' where id='$id'")){
       $res["status"]=1;
        $res["message"]="Loan updated successfully";
    }else{
        $res["status"]=0;
        $res["message"]="Failed to update loan";
    }
        echo json_encode($res);
        mysqli_close($db);
