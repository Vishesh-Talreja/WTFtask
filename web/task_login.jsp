<%--
    Document   : task_login
    Created on : Sep 28, 2014, 10:04:11 PM
    Author     : firewaterthunder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome to crib!</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	  
    <![endif]-->
	
	<style type="text/css">
		
		@media (max-width: 480px) { 

			.win {
				padding:2em;
			}
			
			html {height:100%;}
			
			#break 
			{display:none;}
			
			background-repeat:no-repeat;
		}
		
		.win {
			background-color:white;
			text-align:center;
			border-radius:20px;
			border:2px solid;
		}
			
		body {
		  background: -webkit-gradient(#888888,white); /* Chrome,Safari4+ */
		  background: -webkit-linear-gradient(#888888,white); /* Chrome10+,Safari5.1+ */
		  background-repeat:no-repeat;
		}
		
		html {height:100%;padding-top:5%;padding-bottom:10%;}
		
		::-webkit-scrollbar { 
                    display: none; 
                }

	</style>
	
  </head>
  
  <body>
	
	
		<div class="col-md-4"></div>
		<div class="col-md-4">
		
		
					<div class="win" id="log">
						<img src="img/logo.jpg" style="height:20%;"/><br><br>
						<form role="form" class="form-inline">
						  <div class="form-group">
							<input type="email" name="username" class="form-control" id="exampleInputEmail1" placeholder="Username">
						  </div>
						  <div id="break">
							<br>
						  </div>
						  <div class="form-group">
							<input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
						  </div>
						  <div id="break">
							<br>
						  </div>
						  <button class="btn btn-primary">Login</button><br><br>
						 <a  class="formNotice" href="#" role="button">
							Need an account? Sign up!
						 </a>
						<div id="break">
							<br>
						  </div>
						</form>
					</div>
		
					<div class="win" id="reg">
						<img src="img/logo.jpg" style="height:20%;"/><br><br>
						<form role="form" class="form-inline">
						  <div class="form-group">
							<input type="email" name="fname" class="form-control" id="exampleInputEmail1" placeholder="First Name">
						  </div>
						  <div class="form-group">
							<input type="email" name="lname" class="form-control" id="exampleInputEmail1" placeholder="Last Name">
						  </div>
						  <div id="break">
							<br>
						  </div>
						  <div class="form-group">
							<input type="email" name="email" class="form-control" id="exampleInputEmail1" placeholder="Email address">
						  </div>
						  <div class="form-group">
							<input type="email" name="username" class="form-control" id="exampleInputEmail1" placeholder="Username">
						  </div>
						  <div id="break">
							<br>
						  </div>
						  <div class="form-group">
							<input type="password" name="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
						  </div>
						  <div class="form-group">
							<input type="password" name="cpassword" class="form-control" id="exampleInputPassword1" placeholder="Confirm Password">
						  </div>
						  <div id="break">
							<br>
						  </div>
						  <button class="btn btn-primary">Sign up</button><br><br>
						  <a  class="formNotice" href="#" role="button">
							Have an account? Login!
						  </a>
						  <div id="break">
							<br>
						  </div>
						</form>
					</div>
				  
				

			</div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
	
	<script>

				$(document).ready(function() {
					$('#reg').hide();
					$('.formNotice').click(function() {
						$("#log").toggle();
						$("#reg").toggle();
					});						
				});
	</script>

  </body>
</html>