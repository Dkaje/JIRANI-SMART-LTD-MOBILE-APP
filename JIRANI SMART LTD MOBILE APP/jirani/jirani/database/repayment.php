<?php
include("connector.php");
$sql="CREATE TABLE loaner(
  reg_id int AUTO_INCREMENT PRIMARY KEY,
  borrow_id int,
  mpesa varchar(50),
  id_no int,
  name varchar(100),
  phone varchar(50),
  interest float,
  period float,
  expected float,
  amount float,
  current_period int,
  total float,
  status varchar(50) default 'Pending',
  remarks text,
  reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)";//reg_id,borrow_id,mpesa,id_no,phone,amount,current_period,total,status,remarks,reg_date
$result=mysqli_query($db,$sql);
if(!$result){
  die("Connection failed: " . $db->connect_error);
}
else {
  echo "table created successfully";
}
mysqli_close($db);
 ?>