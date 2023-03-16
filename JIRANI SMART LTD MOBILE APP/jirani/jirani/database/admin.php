<?php
include("connector.php");

$sql = "CREATE TABLE admin(
  id varchar(50) PRIMARY KEY,
  username varchar(50) NOT NULL,
  password VARCHAR(250),
  reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)";

$result = mysqli_query($db, $sql);
if (!$result) {
  die("Connection failed: " . $db->connect_error);
} else {
  echo "table created successfully";
}
mysqli_close($db);
