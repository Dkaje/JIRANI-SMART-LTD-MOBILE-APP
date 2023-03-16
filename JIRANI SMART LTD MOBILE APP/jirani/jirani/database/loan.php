<?php
include("connector.php");

$sql = "CREATE TABLE loan(
  id varchar(20) PRIMARY KEY,
  loan_type varchar(100),
  r_period float,
  rate double,
  amount float,
  reg_date varchar(50)
)";
//id,loan_type,r_period,rate,amount,reg_date//loan
$result = mysqli_query($db, $sql);
if (!$result) {
  die("Connection failed: " . $db->connect_error);
} else {
  echo "table created successfully";
}
mysqli_close($db);