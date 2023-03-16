<?php
include("connector.php");
$sql="CREATE TABLE application(
  id int AUTO_INCREMENT PRIMARY KEY,
  borrow_id int,
  loan_id varchar(100),
  id_no int,
  name varchar(100),
  phone varchar(50),
  loan float,
  amount float,
  status varchar(20) default 'Pending',
  mpesa varchar(20),
  fina_status varchar(50) default 'Pending',
  reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)";//id,borrow_id,loan_id,id_no,name,phone,loan,status,fina_status,reg_date
$result=mysqli_query($db,$sql);
if(!$result){
  die("Connection failed: " . $db->connect_error);
}
else {
  echo "table created successfully";
}
mysqli_close($db);
 ?>