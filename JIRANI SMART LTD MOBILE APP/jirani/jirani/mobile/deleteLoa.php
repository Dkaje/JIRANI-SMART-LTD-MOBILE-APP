<?php
//loan_type,interest,repayment,amount
//id,loan_type,r_period,rate,amount,reg_date//loan
include("../includes/connect.php");
    $id = $_POST["id"];
    if(mysqli_query($db,"DELETE from loan where id='$id'")){
       $res["status"]=1;
        $res["message"]="Loan Deleted successfully";
    }else{
        $res["status"]=0;
        $res["message"]="Failed to delete loan";
    }
        echo json_encode($res);
        mysqli_close($db);