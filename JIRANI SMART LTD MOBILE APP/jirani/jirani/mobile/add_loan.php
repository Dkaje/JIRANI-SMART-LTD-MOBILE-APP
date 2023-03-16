<?php
//loan_type,interest,repayment,amount
//id,loan_type,r_period,rate,amount,reg_date//loan
include("../includes/connect.php");
    $loan_type = $_POST["loan_type"];
    $interest = $_POST["interest"];
    $repayment = $_POST["repayment"];
    $balance = $_POST["balance"];
    $amount = $_POST["amount"];
    $one = mysqli_fetch_assoc(mysqli_query($db, "SELECT SUM(amount) as meja from loan"));
    $total=$one['meja'];
    $liquid=$total+$amount;
    if($liquid>$balance){
        $res["status"]=8;
        $res["message"]="Failed!! Older Posted Loans=$total. Current System Account= $balance. If the Above Loan was Posted, the Total Amount available for loan would be $liquid. Please reduce this $amount Loan.";
    }else{
                    $hits = file("../includes/loaner.txt");
                    $hits[0]++;
                    $fp = fopen("../includes/loaner.txt", "w");
                    fputs($fp, "$hits[0]");
                    fclose($fp);
                    $values = $hits[0];
                    $currentDateTime = date('Y-m-d H:i:s');
    if(mysqli_num_rows(mysqli_query($db,"SELECT * from loan where loan_type='$loan_type'"))>0){
        $res["status"]=0;
        $res["message"]="You cannot overwrite an existing loan type";
    }elseif(mysqli_query($db,"INSERT into loan(id,loan_type,r_period,rate,amount,reg_date)
    values('$values','$loan_type','$repayment','$interest','$amount','$currentDateTime')")){
        $res["status"]=1;
        $res["message"]="Loan uploaded successfully";
    }else{
        $res["status"]=0;
        $res["message"]="Failed to upload loan";
    }
}
        echo json_encode($res);
        mysqli_close($db);
