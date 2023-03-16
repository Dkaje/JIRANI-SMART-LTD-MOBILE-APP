<?php
include("../includes/connect.php");
    $query = "SELECT * FROM loan order by reg_date asc";
    $response = mysqli_query($db, $query);
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['id'] = $row['id'];
            $index['loan_type'] = $row['loan_type'];
            $index['r_period'] = $row['r_period'];
            $index['rate'] = $row['rate'];
            $index['amount'] = $row['amount'];
            $index['reg_date'] = $row['reg_date'];
            array_push($results['victory'], $index);
        }//id,loan_type,r_period,rate,amount,reg_date
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Loan Record";
    }
    echo json_encode($results);