<?php
include('sessionadm.php');
//session_start();
/*if(empty($_SESSION["user_id"]))
{
	header('location:login.php');
}
else
{*/

?>
<!DOCTYPE html>
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
                                <h4 class="card-title">Seized Collateral</h4>
                             
                                <div class="table-responsive m-t-40">
                                    <table id="myTable" class="table table-bordered table-striped">
                                        <thead>
                                            <tr>
                                            <th>ClientID</th>
                                <th>category_one</th>
                                <th>Status</th>
                                <th>type_one</th>
                                <th>existence_one</th>
                                <th>worth_one</th>
                                <th>image_one</th>
                                <th>category_two</th>
                                <th>Status</th>
                                <th>type_two</th>
                                <th>existence_two</th>
                                <th>worth_two</th>
                                <th>image_two</th>
                                <th>category_three</th>
                                <th>Status</th>
                                <th>type_three</th>
                                <th>existence_three</th>
                                <th>worth_three</th>
                                <th>image_three</th>
                                <th>status</th>
                                <th>Date</th>
												 
                                            </tr>
                                        </thead>
                                        <tbody>
											
											<?php
												$sql="SELECT * FROM collateral where one_sta='Seized' or two_sta='Seized' or three_sta='Seized'";
												$query=mysqli_query($db,$sql);
												
													if(!mysqli_num_rows($query) > 0 )
														{
															echo '<td colspan="9"><center>No Data!</center></td>';
														}//ship_company
													else
														{				
																	while($rows=mysqli_fetch_array($query))//national_id,fname,lname,phone,email,username,county,reg_date
																		{
																					
																				
																				
																					echo ' <tr>
																								<td>'.$rows['id_no'].'</td>
																								<td>'.$rows['category_one'].'</td>
																								<td>'.$rows['one_sta'].'</td>
																								<td>'.$rows['type_one'].' </td>
																								<td>'.$rows['existence_one'].' yrs</td>
																								<td>KES '.$rows['worth_one'].'</td>	
																								<td><img src="mobile/images/'.$rows['image_one'].'" height="80px" width="80px"/></td>	
																								<td>'.$rows['category_two'].'</td>
																								<td>'.$rows['two_sta'].'</td>
																								<td>'.$rows['type_two'].' </td>
																								<td>'.$rows['existence_two'].' yrs</td>
																								<td>KES '.$rows['worth_two'].'</td>	
																								<td><img src="mobile/images/'.$rows['image_two'].'" height="80px" width="80px"/></td>
																								<td>'.$rows['category_three'].'</td>
																								<td>'.$rows['three_sta'].'</td>
																								<td>'.$rows['type_three'].' </td>
																								<td>'.$rows['existence_three'].' yrs</td>
																								<td>KES '.$rows['worth_three'].'</td>	
																								<td><img src="mobile/images/'.$rows['image_three'].'" height="80px" width="80px"/></td>
																								<td>'.$rows['status_auc'].'</td>	
																								<td>'.$rows['reg_date'].'</td>
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
