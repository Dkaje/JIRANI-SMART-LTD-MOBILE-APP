<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>Password Reset</title>
  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">

  <link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
<link rel='stylesheet prefetch' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

      <link rel="stylesheet" href="css/login.css">

	  <style type="text/css">
	  #buttn{
		  color:#fff;
		  background-color: #ff3300;
	  }
	  </style>
  
</head>

<body>
<?php
include("database/connector.php"); //INCLUDE CONNECTION
error_reporting(0); // hide undefine index errors
session_start(); // temp sessions

if(isset($_POST['regist']))   // if button is submit
{
	$username = $_POST['username'];  //fetch records from login form
	$password = md5($_POST['password']);
	$cpassword = md5($_POST['cpassword']);
	if ($cpassword != $password) {
		$message = "Passwords Dont Match!";
    } else {
        if(mysqli_num_rows(mysqli_query($db,"SELECT * from  admin where username='$username'"))>0){
            $pdoExec = mysqli_query($db, "UPDATE admin set password='$password' where username='$username'");
            if ($pdoExec) {
                echo "<script>alert('Password reset')</script>";
                echo "<script>location.href='login.php' </script>";
            } else {
              echo "<script>alert('Failed to reset password')</script>";
            }
        }else {
          echo "<script>alert('Account not found')</script>";
        }
        
    }	
}
?>
  
<!-- Form Mixin-->
<!-- Input Mixin-->
<!-- Button Mixin-->
<!-- Pen Title-->

<!-- Form Module-->
<div class="module form-module">
  <div class="toggle">
   
  </div>
  <div class="form">
    <h2>Reset Your Password</h2>
    <form action="" method="post"><div class="pen-title">
  <h1>Password Reset Form</h1>
</div>
      <input type="text" placeholder="Username"  name="username" required/>
      <input type="password" placeholder="Password" name="password" required/>
      <input type="password" placeholder="repeat Password" name="cpassword" required/>
      <input type="submit" id="buttn" name="regist" value="Reset" />  
  <div class="cta">Login?<a href="login.php" style="color:#f30;"> Click here to Login</a></div>
    </form>
  </div>

</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

  

   



</body>

</html>
