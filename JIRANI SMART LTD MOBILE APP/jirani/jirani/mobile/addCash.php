<?php
include("../includes/connect.php");
$amount=$_POST['amount'];
if(mysqli_num_rows(mysqli_query($db,"SELECT * from banker"))>0){
    if(mysqli_query($db,"UPDATE banker set amount=(amount+$amount),balance=(balance+$amount)")){
        $res["status"]=1;
        $res["message"]="Account updated successfully";
    }else{
        $res["status"]=0;
        $res["message"]="Failed to update";
    }
}else{
    if(mysqli_query($db,"INSERT into banker(amount, balance)values('$amount','$amount')")){
        $res["status"]=1;
        $res["message"]="Record inserted successfully";
    
    }else{
        $res["status"]=0;
        $res["message"]="Failed to insert";
    
    }
}
echo json_encode($res);
