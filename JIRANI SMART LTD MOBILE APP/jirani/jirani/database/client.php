<?php
include("connector.php");

$sql = "CREATE TABLE client(
  entry varchar(50) PRIMARY KEY,
  id_no int,
  account bigint,
  fname VARCHAR(50),
  lname VARCHAR(50),
  email VARCHAR(50),
  phone VARCHAR(50),
  country VARCHAR(50),
  agent_email varchar(50) default 'Pending',
  location VARCHAR(50),
  password VARCHAR(250),
  status int default 0,
  comment VARCHAR(250),
  reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)";
//entry,id_no,account,fname,lname,email,phone,country,location,password,status,comment,reg_date
$result = mysqli_query($db, $sql);
if (!$result) {
  die("Connection failed: " . $db->connect_error);
} else {
  echo "table created successfully";
}
mysqli_close($db);