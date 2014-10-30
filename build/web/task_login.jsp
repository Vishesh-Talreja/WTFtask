<%--
    Document   : task_login
    Created on : Sep 28, 2014, 10:04:11 PM
    Author     : firewaterthunder
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.Cookie;" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Welcome to crib!</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="dist/css/bootstrapValidator.min.css"/>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
	  
    <![endif]-->
	
	<style type="text/css">
                .win {
			background-color:white;
			text-align:center;
			border-radius:20px;
			border:2px solid;
                        padding:0.5em;
		}
			
		body {
                        background: -webkit-gradient(#2ECCFA,white); /* Chrome,Safari4+ */
                        background: -webkit-linear-gradient(#2ECCFA,white); /* Chrome10+,Safari5.1+ */
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
        
        
        <!-- Alert message for incorrect login details-->
        <% if ((String)request.getAttribute("logon")=="fail")
        {
            out.println("<div class='alert alert-danger alert-dismissible' role='alert'>");
            out.println("<button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button>");
            out.println("<strong>The entered username or password is incorrect</strong>");
            out.println("</div>");
        }%>

        <!--Login from starts here -->
        <div class="win" id="log">
            <img src="img/logo.jpg" style="height:20%;"/><br><br>
            <form role="form" id="login_form" class="form-inline" method="get" action="Login">
                <div class="form-group">
                    <input type="text" name="lusername" class="form-control" id="exampleInputEmail1" placeholder="Username">
                </div>
                <div id="break">
                    <br>
                </div>
                <div class="form-group">
                    <input type="password" name="lpassword" class="form-control" id="exampleInputPassword1" placeholder="Password">
                </div>
                <div id="break">
                    <br>
                </div>
                <div id="message"></div>
                <br>
                <button type="submit" class="btn btn-primary">Login</button><br><br>
                <a class="formNotice" href="#" role="button">Need an account? Sign up!</a>
                <div id="break">
                    <br>
                </div>
            </form>
        </div>
        <!-- End Login form-->

        <!--Registration from starts here -->
        <div class="win" id="reg">
            <img src="img/logo.jpg" style="height:20%;"/><br><br>
            <form role="form" class="form-inline" method="get" action="Registration" id="reg_form">
                <div class="form-group">
                    <input type="text" name="fname" class="form-control" id="exampleInputEmail1" placeholder="First Name">
                </div>
                <div class="form-group">
                    <input type="text" name="lname" class="form-control" id="exampleInputEmail1" placeholder="Last Name">
                </div>
                <div id="break">
                    <br>
                </div>
                <div class="form-group">
                    <input type="email" name="remail" class="form-control" id="exampleInputEmail1" placeholder="Email address">
                </div>
                <div class="form-group">
                    <input type="username" name="rusername" class="form-control" id="exampleInputEmail2" placeholder="Username" novalidate>
                </div>
                <div id="break">
                    <br>
                </div>
                <div class="form-group">
                    <input type="password" name="rpassword" class="form-control" id="exampleInputPassword2" placeholder="Password">
                </div>
                <div class="form-group">
                    <input type="password" name="cpassword" class="form-control" id="exampleInputPassword1" placeholder="Confirm Password">
                </div>
                <div id="break">
                    <br>
                </div>
                <div id="message1"></div>
                <br>
                <button type="submit" class="btn btn-primary">Sign up</button><br><br>
                <a  class="formNotice" href="#" role="button">Have an account? Login!</a>
                <div id="break">
                    <br>
                </div>
            </form>
        </div>
        <!-- End registration form-->
    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Bootstrap Validator JS-->
    <script type="text/javascript" src="dist/js/bootstrapValidator.min.js"></script>
	
    <script>

        $(document).ready(function() {
            //Forms hide/show toggle code
            $('#reg').hide();
            $('.formNotice').click(function() {
                    $("#log").toggle();
                    $("#reg").toggle();
            });
            
            //Bootstrap validator code for login form
            $('#login_form').bootstrapValidator({
                container:'#message',
                feedbackIcons: {		
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    lusername: {
                            validators: {
                            notEmpty: {
                                message: 'Username is required'
                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9]+$/,
                                message: 'Alphabets only'
                            },
                            }
                    },
                    lpassword: {
                            validators: {
                                notEmpty: {
                                    message: 'Password is required'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z0-9]+$/,
                                    message: 'Alphanumeric only'
                                },
                            }
                    },
                }
            });
            
            //Bootstrap validator code for registration form
            $('#reg_form').bootstrapValidator({
                container:'#message1',
                feedbackIcons: {		
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    fname: {
                            validators: {
                                notEmpty: {
                                    message: 'First name is required'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z]+$/,
                                    message: 'Alphabets only'
                                },
                            }
                    },
                    lname: {
                            validators: {
                                notEmpty: {
                                    message: 'Last name is required'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z]+$/,
                                    message: 'Alphabets only'
                                },
                            }
                    },
                    rusername: {
                            validators: {
                                notEmpty: {
                                    message: 'Username is required'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z0-9]+$/,
                                    message: 'Alphabets only'
                                },
                                remote: {

                                    message: 'The username is not available',
                                    url: 'Check_Username',
                                    data: {
                                        type: 'username'
                                    }
                                }
                            }
                    },
                    remail: {
                            validators: {

                                    notEmpty: {
                                            message: 'Email is required'
                                    },
                                    regexp: {
                                            regexp: /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                                            message: 'Email is invalid'
                                    },
                                    remote: {
                                    message: 'The email is already being used',
                                    url: 'Check_Email',
                                    data: {
                                        type: 'email'
                                    }
                            }
                            }
                    },
                    rpassword: {
                            validators: {
                                notEmpty: {
                                    message: 'Password is required'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z0-9]+$/,
                                    message: 'Alphanumeric only'
                                },
                            }
                    },
                    cpassword: {
                            validators: {
                                notEmpty: {
                                    message: 'Retype Password for confirmation'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z0-9]+$/,
                                    message: 'Alphanumeric only'
                                },
                                identical: {
                                    field: 'rpassword',
                                    message: 'Retype password for confirmation'
                                },
                            }
                    },
                }
            });

        });
    </script>
  </body>
</html>