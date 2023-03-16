<?php
include("connector.php");
$sql="CREATE TABLE banker(
  id int AUTO_INCREMENT PRIMARY KEY,
  amount float,
  disbursed float default 0,
  interest float default 0,
  balance float,
  reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)";
$result=mysqli_query($db,$sql);
if(!$result){
  die("Connection failed: " . $db->connect_error);
}
else {
  echo "table created successfully";
}
mysqli_close($db);
 ?>