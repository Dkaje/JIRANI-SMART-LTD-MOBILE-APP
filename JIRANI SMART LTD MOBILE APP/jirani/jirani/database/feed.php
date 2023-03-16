<?php
include("connector.php");
$sql = "CREATE TABLE feedback(
    id int auto_increment PRIMARY KEY,
    message varchar(250),
    sender VARCHAR(20),
    name varchar(50),
    phone VARCHAR(20),
    send_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    reply varchar(250) default 'Pending',
    reply_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
    )";//id,message,sender,name,phone,send_date,reply,reply_date//feedback
$result = mysqli_query($db, $sql);
if (!$result) {
    die("Connection failed: " . $db->connect_error);
} else {
    echo "table created";
}
mysqli_close($db);