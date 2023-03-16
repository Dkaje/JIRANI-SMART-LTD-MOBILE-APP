<?php
include("../includes/connect.php");
    $one = mysqli_fetch_assoc(mysqli_query($db, "SELECT SUM(amount) as meja from banker"));
    $two = mysqli_fetch_assoc(mysqli_query($db, "SELECT SUM(disbursed) as meja from banker"));
    $three = mysqli_fetch_assoc(mysqli_query($db, "SELECT SUM(balance) as meja from banker"));
    $query = "SELECT * FROM banker";
    $response = mysqli_query($db, $query);
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            if($one['meja']==0){
                $index['amount'] = 0;
            }else{
                $index['amount'] = $one['meja'];
            }
            if($two['meja']==0){
                $index['disbursed'] = 0;
            }else{
                $index['disbursed'] = $two['meja']; 
            }
            if($three['meja']==0){
                $index['balance'] = 0;
            }else{
                $index['balance'] = $three['meja'];
            }
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No record";
    }
    echo json_encode($results);