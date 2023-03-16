<?php
include("../includes/connect.php");
//$driver=$_POST["driver"];
$sql = "SELECT * from agent where status=1";
if (!$db->query($sql)) {
    echo "Faied to create connection";
} else {
    $result = $db->query($sql); 
    if ($result->num_rows > 0) {
        $return_arr['driver'] = array();
        while ($row = $result->fetch_array()) {
            array_push($return_arr['driver'], array(
                'driver_id' => $row['email']
            ));
        }
        echo json_encode($return_arr);
    }
}