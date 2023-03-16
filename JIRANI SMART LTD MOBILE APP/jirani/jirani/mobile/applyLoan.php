<?php
//borrow_id,loan_id,loan_type,rate,id_no,name,phone,maxloan,loan,interest,loan_period,period,total,expected_monthly,
//balance,status,reg_date,pay_date,payment_status,fina_status
include("../includes/connect.php");
    $loan_type = $_POST["loan_type"];
    $interest = $_POST["interest"];
    $loan_id = $_POST["loan_id"];
    $rate = $_POST["rate"];
    $id_no = $_POST["id_no"];
    $name = $_POST["name"];
    $phone = $_POST["phone"];
    $maxloan = $_POST["maxloan"];
    $loan = $_POST["loan"];
    $loan_period = $_POST["loan_period"];
    $total = $_POST["total"];
    $expected_monthly = $_POST["expected_monthly"]; 
    
    if(mysqli_num_rows(mysqli_query($db,"SELECT * from loan_borrow where id_no='$id_no' and status='Approved' and fina_status='Pending'"))>0){
    $res["status"]=0;
    $res["message"]='Failed!! It seems you an uncleared loan.';
    }elseif(mysqli_num_rows(mysqli_query($db,"SELECT * from loan_borrow where id_no='$id_no' and status='Approved' and fina_status='Defaulter'"))>0){
        $res["status"]=0;
        $res["message"]='Denied!! It seems you have defaulted payment before.';
        }elseif(mysqli_num_rows(mysqli_query($db,"SELECT * from loan_borrow where id_no='$id_no' and status='Pending'"))>0){
    $res["status"]=0;
    $res["message"]='Failed!! It seems you have another loan application with pending approval.';
    }
    elseif(mysqli_num_rows(mysqli_query($db,"SELECT * from loan_borrow where id_no='$id_no' and balance>0"))>0){
        $res["status"]=0;
        $res["message"]='Failed!! You have an uncleared loan.';
        }
        else{if(mysqli_query($db,"INSERT into loan_borrow(loan_id,loan_type,rate,id_no,name,phone,maxloan,loan,interest,loan_period,period,total,expected_monthly)
    values('$loan_id','$loan_type','$rate','$id_no','$name','$phone','$maxloan','$loan','$interest','$loan_period','$loan_period','$total','$expected_monthly')")){
        $res["status"]=1;
        $res["message"]="Loan Application was submitted successfully";
    }else{
        $res["status"]=0;
        $res["message"]="Failed to submit";
    }
}
        echo json_encode($res);
        mysqli_close($db);
