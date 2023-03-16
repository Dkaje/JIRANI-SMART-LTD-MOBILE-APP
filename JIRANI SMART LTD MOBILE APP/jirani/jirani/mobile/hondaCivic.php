<?php

include("../includes/connect.php");

$sql = "SELECT * from collateral where status_auc='Pending' and status!='Pending'";

$response = mysqli_query($db, $sql);

if (mysqli_num_rows($response) > 0) {
    $results['trust'] = 1;
    $results['victory'] = array();
    while ($row = mysqli_fetch_array($response)) {
        $index['reg_id'] = $row['reg_id'];
            $index['id_no'] = $row['id_no'];
            $index['name'] = $row['name'];
            $index['phone'] = $row['phone'];
            $index['email'] = $row['email'];
            $index['county'] = $row['county'];
            $index['location'] = $row['location'];
            $index['agent_email'] = $row['agent_email'];
            $index['category_one'] = $row['category_one'];
            $index['type_one'] = $row['type_one'];
            $index['existence_one'] = $row['existence_one'];
            $index['worth_one'] = $row['worth_one'];
            $index['image_one'] = $row['image_one'];
            $index['one_sta'] = $row['one_sta'];
            $index['category_two'] = $row['category_two'];
            $index['type_two'] = $row['type_two'];
            $index['existence_two'] = $row['existence_two'];
            $index['worth_two'] = $row['worth_two'];
            $index['image_two'] = $row['image_two'];
            $index['two_sta'] = $row['two_sta'];
            $index['category_three'] = $row['category_three'];
            $index['type_three'] = $row['type_three'];
            $index['existence_three'] = $row['existence_three'];
            $index['worth_three'] = $row['worth_three'];
            $index['image_three'] = $row['image_three'];
            $index['three_sta'] = $row['three_sta'];
            $index['status'] = $row['status'];
            $index['status_auc'] = $row['status_auc'];
            $index['reg_date'] = $row['reg_date'];
            array_push($results['victory'], $index);
    }
} else {

    $results['trust'] = 0;
    $results['mine'] = "No Record";
}
echo json_encode($results);