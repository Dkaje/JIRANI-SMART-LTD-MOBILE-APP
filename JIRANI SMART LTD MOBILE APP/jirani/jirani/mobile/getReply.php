<?php
include("../includes/connect.php");
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $sender = $_POST['sender'];
    $query = "SELECT * FROM feedback where sender='$sender'";

    $response = mysqli_query($db, $query);
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['id'] = $row['id'];
            $index['sender'] = $row['sender'];
            $index['phone'] = $row['phone'];
            $index['message'] = $row['message'];
            $index['reg_date'] = $row['send_date'];
            $index['reply'] = $row['reply'];
            $index['reply_date'] = $row['reply_date'];
            array_push($results['victory'], $index);
        }
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No Recond found";
        echo json_encode($results);
    }
    echo json_encode($results);
}