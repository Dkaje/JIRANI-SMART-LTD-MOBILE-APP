<?php
include('sessionadm.php');
//session_start();
/*if(empty($_SESSION["user_id"]))
{
	header('location:login.php');
}
else
{*/

 
    if (isset($_POST['approveBtn'])) {
        $id = $_POST['identity'];

        $sql = mysqli_query($db, "update staff set status='1', comment='' where entry = '$id'");
        if (!$sql) {
            die("Connection failed: " . $db->connect_error);
        } else {
            //echo "<script>alert('Account Approved Successfully')</script>";
            echo "<script>location.href='newStaff.php' </script>";
        }
    }

    if (isset($_POST['rejectBtn'])) {
        $id = $_POST['identity'];
        $remarks = $_POST['remarks'];

        $sql = mysqli_query($db, "update staff set status='2',comment='$remarks' where entry = '$id'");
        if (!$sql) {
            die("Connection failed: " . $db->connect_error);
        } else {
           // echo "<script>alert('Account Rejected Successfully')</script>";
            echo "<script>location.href='newStaff.php' </script>";
        }
    }
?><!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Favicon icon -->
    <title>jirani Smart LTD</title>
    <!-- Bootstrap Core CSS -->
    <link href="css/lib/bootstrap/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/helper.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:** -->
    <!--[if lt IE 9]>
    <script src="https:**oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https:**oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>

<body class="fix-header fix-sidebar">
    <!-- Preloader - style you can find in spinners.css -->
    <div class="preloader">
        <svg class="circular" viewBox="25 25 50 50">
			<circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="2" stroke-miterlimit="10" /> </svg>
    </div>
    <!-- Main wrapper  -->
    <div id="main-wrapper"><div class="header">
            <nav class="navbar top-navbar navbar-expand-md navbar-light">
                <!-- Logo -->
                <div class="navbar-header">
                    <a class="navbar-brand" href="index.php">
                        <!-- Logo icon -->
                        <b>JIRANI</b></a>
                </div>
                <!-- End Logo -->
                <div class="navbar-collapse">
                    <!-- toggle and nav items -->
                    <ul class="navbar-nav mr-auto mt-md-0">
                        <!-- This is  -->
                        <li class="nav-item"> <a class="nav-link nav-toggler hidden-md-up text-muted  " href="javascript:void(0)"><i class="mdi mdi-menu"></i></a> </li>
                        <li class="nav-item m-l-10"> <a class="nav-link sidebartoggler hidden-sm-down text-muted  " href="javascript:void(0)"><i class="ti-menu"></i></a> </li>
                     
                       
                    </ul>
                   
                </div>
            </nav>
        </div>
        <!-- End header header -->
        <!-- Left Sidebar  -->
        <div class="left-sidebar">
            <!-- Sidebar scroll-->
            <div class="scroll-sidebar">
                <!-- Sidebar navigation-->
                <?php include 'database/sider.php'; ?>
                <!-- End Sidebar navigation -->
            </div>
            <!-- End Sidebar scroll-->
        </div>
        <!-- End Left Sidebar  -->
        <!-- Page wrapper  -->
        <div class="page-wrapper">
            <!-- Bread crumb -->
            <div class="row page-titles">
                <div class="col-md-5 align-self-center">
                    <h3 class="text-primary">Dashboard</h3> </div>
               
            </div>
            <!-- End Bread crumb -->
            <!-- Container fluid  -->
            <div class="container-fluid">
                <!-- Start Page Content -->
                <div class="row">
                    <div class="col-12">
                        
                       
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">New Staff</h4>
                             
                                <div class="table-responsive m-t-40">
                                    <table id="myTable" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                            <th>Action</th>
                                <th>Entry</th>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Phone</th>
                                <th>Role</th>
                                <th>Reg Date</th>
                                <th>Status</th>
												 
                                            </tr>
                                        </thead>
                                        <tbody>
                                           
											
											<?php
												$sql="SELECT * FROM staff where role!='Auction' and status=0";
												$query=mysqli_query($db,$sql);
												
													if(!mysqli_num_rows($query) > 0 )
														{
															echo '<td colspan="9"><center>No User-Data!</center></td>';
														}//ship_company
													else
														{				
																	while($rows=mysqli_fetch_array($query))//national_id,fname,lname,phone,email,username,county,reg_date
																		{
																					
																				
																				
																					echo ' <tr><td>
                                                                                    <div class="dropdown" style="float: right;">
                                               <button class="btn btn-success btn-xs" type="button"
                                                   data-toggle="dropdown"><span style="font-size:16px">Approve</span></button>
                                               <ul class="dropdown-menu alert panel-footer">
                                                   <li>
                                                       <form method="post">
                                                           <input type="hidden" name="identity"
                                                               value="'.$rows['entry'].'" /><br>
                                                           <input type="submit" name="approveBtn"
                                                               value="Approve"
                                                               class="btn btn-success btn-flat btn-addon btn-sm m-b-10 m-l-5" style="font-size:16px"/>
                                                       </form>
                                                   </li>
                                               </ul>
                                           </div>
                                           <div class="dropdown" style="float: right;">
                                               <button class="btn btn-danger btn-xs" type="button"
                                                   data-toggle="dropdown"><span style="font-size:16px">Reject</span></button>
                                               <ul class="dropdown-menu alert panel-footer">
                                                   <li>
                                                       <form method="post">
                                                           <input type="hidden" name="identity"
                                                               value="'.$rows['entry'].'" />
                                                           <textarea class="form-control" rows="20"
                                                               name="remarks"
                                                               placeholder="Type some comment"
                                                               required></textarea>
                                                           <input type="submit" name="rejectBtn"
                                                               value="Reject"
                                                               class="btn btn-danger btn-flat btn-addon btn-xs m-b-10"style="font-size:16px" />
                                                       </form>
                                                   </li>
                                               </ul>
                                           </div></td><td>'.$rows['entry'].'</td>
																								<td>'.$rows['fname'].' '.$rows['lname'].'</td>
																								<td>'.$rows['email'].'</td>
																								<td>'.$rows['phone'].'</td>
																								<td>'.$rows['role'].'</td>
																								<td>'.$rows['reg_date'].'</td>																						
																								<td>Pending</td>
																									 </tr>';
																					 
																						
																						
																		}	
														}
												
											
											?>
                                             
                                            
                                           
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
						 </div>
                      
                            </div>
                        </div>
                    </div>
                </div>
                <!-- End PAge Content -->
            </div>
            <!-- End Container fluid  -->
			
			
			
			
            <!-- footer -->
            <footer class="footer"> © 2022 All rights reserved <a href="index.php">@Veronica</a></footer>
            <!-- End footer -->
        </div>
        <!-- End Page wrapper  -->
    </div>
    <!-- End Wrapper -->
    <!-- All Jquery -->
    <script src="js/lib/jquery/jquery.min.js"></script>
    <!-- Bootstrap tether Core JavaScript -->
    <script src="js/lib/bootstrap/js/popper.min.js"></script>
    <script src="js/lib/bootstrap/js/bootstrap.min.js"></script>
    <!-- slimscrollbar scrollbar JavaScript -->
    <script src="js/jquery.slimscroll.js"></script>
    <!--Menu sidebar -->
    <script src="js/sidebarmenu.js"></script>
    <!--stickey kit -->
    <script src="js/lib/sticky-kit-master/dist/sticky-kit.min.js"></script>
    <!--Custom JavaScript -->
    <script src="js/custom.min.js"></script>


    <script src="js/lib/datatables/datatables.min.js"></script>
    <script src="js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/dataTables.buttons.min.js"></script>
    <script src="js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.flash.min.js"></script>
    <script src="js/lib/datatables/cdnjs.cloudflare.com/ajax/libs/jszip/2.5.0/jszip.min.js"></script>
    <script src="js/lib/datatables/cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/pdfmake.min.js"></script>
    <script src="js/lib/datatables/cdn.rawgit.com/bpampuch/pdfmake/0.1.18/build/vfs_fonts.js"></script>
    <script src="js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.html5.min.js"></script>
    <script src="js/lib/datatables/cdn.datatables.net/buttons/1.2.2/js/buttons.print.min.js"></script>
    <script src="js/lib/datatables/datatables-init.js"></script>
</body>

</html>
