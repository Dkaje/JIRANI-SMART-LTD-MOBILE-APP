<?php
include("connector.php");
$sql="CREATE TABLE loan_borrow(
  borrow_id int AUTO_INCREMENT PRIMARY KEY,
  loan_id varchar(100),
  loan_type varchar(100),
  rate VARCHAR(20),
  id_no int,
  name varchar(100),
  phone varchar(50),
  maxloan float,
  loan float,
  interest float,
  loan_period float,
  period float default 0,
  total float,
  expected_monthly float,
  balance float default 0,
  status varchar(20) default 'Pending',
  reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  pay_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  disburse varchar(20) default 'Pending',
  payment_status varchar(50) default 'Pending',
  fina_status varchar(50) default 'Pending'
)";//borrow_id,loan_id,loan_type,rate,id_no,name,phone,maxloan,loan,interest,loan_period,period,total,expected_monthly,
//balance,reg_date,pay_date,payment_status,fina_status
$result=mysqli_query($db,$sql);
if(!$result){
  die("Connection failed: " . $db->connect_error);
}
else {
  echo "table created successfully";
}
mysqli_close($db);
 ?>