<?php
include("../includes/connect.php");
$sum=mysqli_fetch_assoc(mysqli_query($db,"SELECT sum(loan) as sta from application where status='Disbursed'"));
    $query = "SELECT * FROM application where status='Disbursed' order by reg_date asc";
    $response = mysqli_query($db, $query);
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['id'] = $row['id'];
            $index['borrow_id'] = $row['borrow_id'];
            $start=mysqli_fetch_assoc(mysqli_query($db,"SELECT status as sta from loan_borrow where borrow_id='$row[borrow_id]'"));
            $index['loan_id'] = $row['loan_id'];
            $index['id_no'] = $row['id_no'];
            $index['name'] = $row['name'];
            $index['phone'] = $row['phone'];
            $index['loan'] = $row['loan'];
            $index['status'] = $row['status'];
            $index['auction'] = $start['sta'];
            $index['total'] = $sum['sta'];
            $index['amount'] = $row['amount'];
            $bala=mysqli_fetch_assoc(mysqli_query($db,"SELECT balance as sta from loan_borrow where borrow_id='$row[borrow_id]'"));
            $index['balance'] = $bala['sta'];
            $index['mpesa'] = $row['mpesa'];
            $index['fina_status'] = $row['fina_status'];
            $index['reg_date'] = $row['reg_date'];
            array_push($results['victory'], $index);
        }
//id,borrow_id,loan_id,id_no,name,phone,loan,status,fina_status,reg_date/getApp
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Loan Record";
    }
    echo json_encode($results);