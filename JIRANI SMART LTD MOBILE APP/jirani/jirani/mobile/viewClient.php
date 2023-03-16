<?php
include("../includes/connect.php");
if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    $query = "SELECT * FROM client where status=1 and agent_email='Pending' order by entry asc";
    $response = mysqli_query($db, $query);
    if (mysqli_num_rows($response) > 0) {
        $results['trust'] = 1;
        $results['victory'] = array();
        while ($row = mysqli_fetch_array($response)) {
            $index['entry'] = $row['entry'];
            $index['id_no'] = $row['id_no'];
            $index['fname'] = $row['fname'];
            $index['lname'] = $row['lname'];
            $index['email'] = $row['email'];
            $index['phone'] = $row['phone'];
            $index['country'] = $row['country'];
            $index['agent_email'] = $row['agent_email'];
            $index['location'] = $row['location'];
            array_push($results['victory'], $index);
        }//entry, id_no, fname, lname, email, phone, country, agent_email, location
    } else {
        $results['trust'] = 0;
        $results['mine'] = "No new Clients for assessment";
        echo json_encode($results);
    }
    echo json_encode($results);
}