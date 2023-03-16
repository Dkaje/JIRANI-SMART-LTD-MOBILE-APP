<?php
include("../includes/connect.php");
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $id_no=$_POST["id_no"];
    $one = mysqli_fetch_assoc(mysqli_query($db, "SELECT SUM(worth_one) as papa from collateral where id_no='$id_no' and status_auc='Approved'"));
    $two = mysqli_fetch_assoc(mysqli_query($db, "SELECT SUM(worth_two) as papa from collateral where id_no='$id_no' and status_auc='Approved'"));
    $three = mysqli_fetch_assoc(mysqli_query($db, "SELECT SUM(worth_three) as papa from collateral where id_no='$id_no' and status_auc='Approved'"));
    $query = "SELECT * FROM collateral where id_no='$id_no' and status_auc='Approved'";
    $response = mysqli_query($db, $query);
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['one'] = $one['papa'];
            $index['two'] = $two['papa'];
            $index['three'] = $three['papa'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No record";
    }
    echo json_encode($results);
}