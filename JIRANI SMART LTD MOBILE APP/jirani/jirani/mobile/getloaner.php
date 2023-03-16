<?php
include("../includes/connect.php");
    $query = "SELECT * FROM loan_borrow order by reg_date asc";
    $response = mysqli_query($db, $query);
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['borrow_id'] = $row['borrow_id'];
            $index['loan_id'] = $row['loan_id'];
            $index['loan_type'] = $row['loan_type'];
            $index['rate'] = $row['rate'];
            $index['id_no'] = $row['id_no'];
            $index['name'] = $row['name'];
            $index['phone'] = $row['phone'];
            $index['maxloan'] = $row['maxloan'];
            $index['loan'] = $row['loan'];
            $index['interest'] = $row['interest'];
            $index['loan_period'] = $row['loan_period'];
            $index['period'] = $row['period'];
            $index['total'] = $row['total'];
            $index['expected_monthly'] = $row['expected_monthly'];
            $index['balance'] = $row['balance'];
            $index['status'] = $row['status'];
            $index['reg_date'] = $row['reg_date'];
            $index['pay_date'] = $row['pay_date'];
            $index['payment_status'] = $row['payment_status'];
            $index['fina_status'] = $row['fina_status'];
            array_push($results['victory'], $index);
        }
        
        //borrow_id,loan_id,loan_type,rate,id_no,name,phone,maxloan,loan,interest,loan_period,period,total,expected_monthly,
//balance,status,reg_date,pay_date,payment_status,fina_status
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Loan Record";
    }
    echo json_encode($results);