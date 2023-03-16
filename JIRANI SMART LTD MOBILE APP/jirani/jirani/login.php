<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>login</title>
  
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
ob_start();
//error_reporting(0);
session_start(); // temp sessions
if(isset($_POST['submit']))   // if button is submit
{
	$username = $_POST['username'];  //fetch records from login form
	$password = $_POST['password'];
	
	if(!empty($_POST["submit"]))   // if records were not empty
     {
	$loginquery ="SELECT * FROM admin WHERE username='$username' && password='".md5($password)."'"; //selecting matching records
	$result=mysqli_query($db, $loginquery); //executing
	$row=mysqli_fetch_array($result);
	
	                        if(is_array($row))  // if matching records in the array & if everything is right
								{
                                    	$_SESSION["user_id"] = $row['username']; // put user id into temp session
										echo "<script>location.href='index.php' </script>";
	                            } 
							else
							    {
									echo "<script>alert('Invalid Username or Password!')</script>";
                                }
	 }
}
ob_end_flush();
if(isset($_POST['regist']))   // if button is submit
{
	$username = $_POST['username'];  //fetch records from login form
	$password = md5($_POST['password']);
	$cpassword = md5($_POST['cpassword']);
	if ($cpassword != $password) {
		$message = "Passwords Dont Match!";
    } else {
        $pdoExec = mysqli_query($db, "INSERT into admin(username,password)values('$username','$password')");
        if ($pdoExec) {
            echo "<script>alert('Account Registered')</script>";
            echo "<script>location.href='login.php' </script>";
        } else {
			echo "<script>alert('ailed to create account')</script>";
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
    <h2>Insert your account Credentials</h2>
	  <!--<span style="color:red;"><php echo $message; ?></span> 
   <span style="color:green;"><php echo $success; ?></span>-->
    <form action="" method="post">
	<div class="pen-title">
  <h1>Login Form</h1>
</div>
		<?php if(mysqli_num_rows(mysqli_query($db,"SELECT * from  admin"))>0){
			echo '<input type="text" placeholder="Username"  name="username" required/>
			<input type="password" placeholder="Password" name="password" required/>
			<input type="submit" id="buttn" name="submit" value="login" />';
		}else{
			echo '<input type="text" placeholder="Username"  name="username" required/>
			<input type="password" placeholder="Password" name="password" required/>
			<input type="password" placeholder="repeat Password" name="cpassword" required/>
			<input type="submit" id="buttn" name="regist" value="Register" />';
		} ?>
      <div class="cta">Forgot Password?<a href="password.php" style="color:#f30;"> Click here to reset Password</a></div>
    </form>
  </div>
  
  
</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

  

   



</body>

</html>
