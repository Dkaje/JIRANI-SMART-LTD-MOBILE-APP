<?php
//reg_id,one_sta,two_sta,three_sta
include("../includes/connect.php");
    $reg_id = $_POST["reg_id"];
    $one_sta = $_POST["one_sta"];
    $two_sta = $_POST["two_sta"];
    $three_sta = $_POST["three_sta"];
    $borrow_id = $_POST["borrow_id"];
    if(mysqli_query($db,"UPDATE collateral set one_sta='$one_sta',two_sta='$two_sta',three_sta='$three_sta' where reg_id='$reg_id'")){
        mysqli_query($db,"UPDATE loan_borrow set payment_status='Adjusted' where borrow_id='$borrow_id'");
        $res["status"]=1;
        $res["message"]="collateral Update was successful";
    }else{
        $res["status"]=0;
        $res["message"]="Failed to update";
    }
        echo json_encode($res);
        mysqli_close($db);
