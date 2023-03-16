<?php
include("connector.php");
//id_no,category_one,type_one,existence_one,worth_one,image_one
//category_two,type_two,existence_two,worth_two,image_two
//category_three,type_three,existence_three,worth_three,image_three
$result=mysqli_query($db,"CREATE TABLE collateral(
  reg_id int AUTO_INCREMENT PRIMARY KEY,
  id_no bigint,
  name varchar(100),
  phone varchar(50),
  email varchar(50),
  county varchar(50),
  location varchar(250),
  agent_email varchar(50),
  category_one varchar(250),type_one varchar(250),existence_one varchar(250),
  worth_one float,image_one varchar(250),one_sta varchar(20) default 'Pending',
  category_two varchar(250),type_two varchar(250),existence_two varchar(250),
  worth_two float,image_two varchar(250),two_sta varchar(20) default 'Pending',
  category_three varchar(250),type_three varchar(250),existence_three varchar(250),
  worth_three float,image_three varchar(250),three_sta varchar(20) default 'Pending',
  status varchar(50) default 'Pending',
  status_auc varchar(50) default 'Pending',
  reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)");
if(!$result){
  die("Connection failed: " . $db->connect_error);
}
else {
  echo "table created successfully";
}
mysqli_close($db);
 ?>