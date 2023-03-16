<?php
include("../includes/connect.php");
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id_no=$_POST["id_no"];
    $sum=mysqli_fetch_assoc(mysqli_query($db,"SELECT sum(total) as sta from loaner where id_no='$id_no' and status!='Rejected'"));
     $query = "SELECT * FROM loaner where id_no='$id_no' order by reg_date asc";
    $response = mysqli_query($db, $query);
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        //reg_id,borrow_id,mpesa,id_no,name,phone,amount,current_period,total,status,remarks,reg_date
        while ($row = mysqli_fetch_array($response)) {
            $index['reg_id'] = $row['reg_id'];
            $index['borrow_id'] = $row['borrow_id'];
            $index['mpesa'] = $row['mpesa'];
            $index['id_no'] = $row['id_no'];
            $index['name'] = $row['name'];
            $index['phone'] = $row['phone'];
            $index['interest'] = $row['interest'];
            $index['period'] = $row['period'];
            $index['expected'] = $row['expected'];
            $index['amount'] = $row['amount'];
            $index['current_period'] = $row['current_period'];
            $index['total'] = $row['total'];
            $index['summed'] = $sum['sta'];
            $index['status'] = $row['status'];
            $index['remarks'] = $row['remarks'];
            $index['reg_date'] = $row['reg_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Loan Record";
    }
    echo json_encode($results);
}