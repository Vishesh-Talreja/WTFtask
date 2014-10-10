<%-- 
    Document   : faulty_login
    Created on : Sep 29, 2014, 8:55:34 PM
    Author     : aashish kanade, vinay rajagopalan, vishesh talreja
--%>
   

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.*" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Frameset//EN" "http://www.w3.org/TR/html4/frameset.dtd">
<html lang="en">
    
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Crib</title>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="http://bsdp-assets.blackcherry.us/1.3.0/datepicker.min.css">
    <!-- BootstrapValidator CSS -->
    <link rel="stylesheet" href="dist/css/bootstrapValidator.min.css"/>
    
	<style type="text/css">
		
		.datepicker{z-index:1151 !important;}
		
		.btn-primary  {
			background-color:#336699;
			border-color:#336699;
		}
		
                	
		body {
		  background: -webkit-gradient(#888888,white); /* Chrome,Safari4+ */
		  background: -webkit-linear-gradient(#888888,white); /* Chrome10+,Safari5.1+ */
		 background-repeat: no-repeat;
		}
                
		#myCarousel {height:30vh;}
                .item {height:25vh;}
                html {height:100%}

                ::-webkit-scrollbar { 
                            display: none; 
                }
                
                .carousel-inner .active.left { left: -50%; }
		.carousel-inner .next        { left:  50%; }
		.carousel-inner .prev        { left:  -50%; }
		.carousel-control.left,.carousel-control.right {background-image:none;}

		.col-lg-2 {width: 50%;}
                
                .event {
                  width: 300px;
                  height: 80px;
                  background: #fff;
                  border: 1px solid #CCC;
                  border-radius: 2px;
                  margin: 50px;
                }
                .event:before {
                  content: '';
                  display: block;
                  width: 295px;
                  height: 70px;
                  background: #fff;
                  border: 1px solid #CCC;
                  border-radius: 2px; 
                  transform: rotate(2deg);
                  position: relative;
                  top: 12px;
                  left: 2px;
                  z-index: -1;
                }
                .event:after {
                  content: '';
                  display: block;
                  width: 295px;
                  height: 75px;
                  background: #fff;
                  border: 1px solid #CCC;
                  border-radius: 2px; 
                  transform: rotate(-2deg);
                  position: relative;
                  top: -136px;
                  z-index: -2;  
                }
                .event > span {
                  display: block;
                  width: 30px;
                  background: #232323;  
                  position: relative;
                  top: -55px;
                  left: -15px;

                  /* Text */
                  color: #fff;
                  font-size: 10px;
                  padding: 2px 7px;
                  text-align: right;
                }
                .event > .info {
                  display: inline-block;
                  position: relative;
                  top: -75px;
                  left: 40px;

                  /* Text */
                  color: #232323;
                  font-weight: 600;
                  line-height: 25px;
                }
                .event > .info:first-line {
                  text-transform: uppercase;
                  font-size: 10px;
                  margin: 10px 0 0 0;
                  font-weight: 700;
                }
                .event > .price {
                  display: inline-block;
                  width: 60px;
                  position: relative;
                  top: -85px;
                  left: 115px; 

                  /* Text */
                  color: #E35354;
                  text-align: center;
                  font-weight: 700;
                }

	</style>	
  </head>
  <body>
  <div class="col-md-2"></div>
  <div class="col-md-8 col-xs-12" style='padding: 0'>
    <!-- main container for page content-->
    <div style ="height:100vh;background-color: white;padding: 2em">
        <!-- top navbar-->
        <nav class="navbar navbar-default" role="navigation" style="border:hidden ;background-color:#7F7F7F;width: 100%;margin: -1">
          <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar" style="background-color:white;"></span>
                <span class="icon-bar" style="background-color:white;"></span>
                <span class="icon-bar" style="background-color:white;"></span>
              </button>

            <div class="navbar-brand" style="padding-top:0px;"><img  src="img/logo_nav.jpg" style="height:140%;width50%;"/></div>

            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right" >
                    <li id="group"><a id="showdisplayfriendmodal" href="#displayfriendmodal" class="btn-group-sm" data-toggle="modal"  style="color:white">Friends</a></li>
                    <li id="group"><a id="showaddtaskmodal" href="#addtaskmodal" class="btn-group-sm" data-toggle="modal"  style="color:white">Add a Task</a></li>
                    <li id="friend"><a id="showaddfriendmodal" href="#addfriendmodal" class="btn-group-sm" data-toggle="modal" style="color:white">Add a Friend</a></li>
                    <li ><a href="task_login.jsp" class="btn-group-sm" style="color:white">Log Out</a></li>
                </ul> 
            </div><!-- /.navbar-collapse -->
          </div><!-- /.container-fluid -->
        </nav><!-- end navbar-->
        
        <!-- Welcome message-->
        <h1 style="font-face:papyrus">Welcome <%=request.getAttribute("Name")%></h1><br>

        <%
             /*This block of java code would check the values returned from various servlets 
            and based on that will display a particular alert message*/
             if ((String)request.getAttribute("send")=="yes")
                {
                    out.println("<div class='alert alert-success alert-dismissible' role='alert'>");
                    out.println("<button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button>");
                    out.println("<strong>Success!&nbsp;</strong>&nbsp;Your friend <strong>"+request.getAttribute("rname")+"</strong>&nbsp;has been invited.");
                    out.println("</div>");
                }
             if ((String)request.getAttribute("added_friend")=="true")
             {
                 out.println("<div class='alert alert-success alert-dismissible' role='alert'>");
                 out.println("<button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button>");
                 out.println("<strong>Success!&nbsp;</strong>&nbsp;Your friend <strong>"+request.getAttribute("FName")+"</strong>&nbsp;has been added.");
                 out.println("</div>");
             }
             if ((String)request.getAttribute("added")=="yes")
                {
                    out.println("<div class='alert alert-success alert-dismissible' role='alert'>");
                    out.println("<button type='button' class='close' data-dismiss='alert'><span aria-hidden='true'>&times;</span><span class='sr-only'>Close</span></button>");
                    out.println("<strong>&nbsp;Success! "+request.getAttribute("TName")+"</strong>&nbsp;has been added.");
                    out.println("</div>");
                }
        %>
        
        <!-- View Tasks carousel-->
        <div class="col-md-12">
            <div class="col-md-4 col-md-offset-4 text-center"><h4><a  href="#myCarousel" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>&nbsp;Task viewer&nbsp;<a  href="#myCarousel" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a></h4></div>
            <div class="col-md-12 col-xs-12">
                <div class="carousel slide" id="myCarousel">
                    <div class="carousel-inner">
                        <%
                        /*This block of java code displays the tasks the user has to complete, here it 
                          first connects to the database and then displays them in the form of thumbnails*/
                        String user = (String)request.getAttribute("username");
                        String sql,sql3;
                        String connectionURL="jdbc:derby://localhost:1527/WTFtask";
                        sql3 ="SELECT TASKID FROM WTFtaskallocation where USERNAME = '"+user+"'";


                        try {
                            Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
                            Statement s = conn.createStatement();
                            Statement s1 = conn.createStatement();
                            Statement s2 = conn.createStatement();
                            ResultSet rs2 = s2.executeQuery(sql3);
                            int count = 0;

                            while(rs2.next()){
                                sql = "SELECT * FROM WTFtasks where TASKID ="+rs2.getInt("TASKID");
                                ResultSet rs = s.executeQuery(sql);
                                while (rs.next()) {
                                      String sql2 ="SELECT FIRSTNAME,LASTNAME FROM WTFuser WHERE USERNAME='"+rs.getString("OWNER")+"'";                       
                                      ResultSet rs1 = s1.executeQuery(sql2);
                                      rs1.next();
                                      if(count==0)
                                      {    
                                      out.println("<div class='item active'>");
                                      }
                                      else
                                      {
                                         out.println("<div class='item'>"); 
                                      }
                                      out.println("<div class='col-lg-2 col-xs-12' >");
                                      out.println("<div class='thumbnail' style = 'background-color:#E6E6E6;color:white;' align='center'>");
                                      out.println("<div class='caption'>");
                                      out.println("<h3>"+rs.getString("TASKNAME")+"</h3>");
                                      out.println("<p>POINTS: "+rs.getString("TASKPOINTS")+"<br>OWNER: "+rs1.getString("FIRSTNAME")+" "+rs1.getString("LASTNAME")+"<br>DUE-DATE: "+rs.getString("DUEDATE")+"</p>");
                                      out.println("<p><a href='#' class='btn btn-primary' role='button'>Wrap Up</a></p>");
                                      out.println("</div></div></div></div>");
                                      count++;
                                      rs1.close();
                                  }
                                  rs.close();
                              }

                              s.close();
                              s1.close();
                              s2.close();
                              conn.close();
                          }
                          catch (SQLException e) {
                              e.printStackTrace();
                          }
                          catch (Exception e) {
                              e.printStackTrace();
                          }
                          %>
                    </div>
                </div>
            </div>  
        </div>
        <!-- End View tasks carousel-->
        
        <!-- View owned tasks carousel-->
        <div class="col-md-12">
            <div class="col-md-4 col-md-offset-4 text-center"><h4><a  href="#myCarousel1" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>&nbsp;Tasks you own&nbsp;<a  href="#myCarousel1" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a></h4></div>
            <div class="col-md-12 col-xs-12">
                <div class="carousel slide" id="myCarousel1">
                    <div class="carousel-inner">
                        <%  
                        /*This block of java code displays the tasks the user owns, here it 
                          first connects to the database and then displays them in the form of thumbnails*/

                        String user2 = (String)request.getAttribute("username");
                        String sql7;
                        String connectionURL2="jdbc:derby://localhost:1527/WTFtask";
                        sql7 ="SELECT * FROM WTFtasks WHERE OWNER  = '"+user2+"'";
                        try {
                            Connection conn2 = DriverManager.getConnection(connectionURL2, "IS2560","IS2560");
                            Statement s6 = conn2.createStatement();
                            ResultSet rs8 = s6.executeQuery(sql7);
                            int count2 = 0;

                            while(rs8.next()){

                                      if(count2==0)
                                      {    
                                      out.println("<div class='item active'>");
                                      }
                                      else
                                      {
                                         out.println("<div class='item'>"); 
                                      }
                                      out.println("<div class='col-lg-2 col-xs-12' >");
                                      out.println("<div class='thumbnail' style = 'background-color:#E6E6E6;color:white;' align='center'>");
                                      out.println("<div class='caption'>");
                                      out.println("<h3>"+rs8.getString("TASKNAME")+"</h3>");
                                      out.println("<p>POINTS: "+rs8.getString("TASKPOINTS")+"<br>DUE-DATE: "+rs8.getString("DUEDATE")+"</p>");
                                      out.println("<p><a href='#' class='btn btn-primary' role='button'>Wrap Up</a></p>");
                                      out.println("</div></div></div></div>");
                                      count2++;

                              }
                              rs8.close();
                              s6.close();
                              conn2.close();
                          }
                          catch (SQLException e) {
                              e.printStackTrace();
                          }

                          %>
                    </div>
                </div>
            </div>  
        </div>
        <!-- End owned tasks carousel-->
    </div>
    <!-- main container-->
  </div>
  <div class="col-md-2"></div>
    
    <!-- Modal for showing friends-->
    <div id="displayfriendmodal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" style="border-radius:20px;">
            <div class="modal-content">
                <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><br><br>
                        <h3 class="modal-title" align="center">Your Friends</h3></br>
                        <%  
                            /*This piece of java code connects to the database and then displays the friends of the
                            user that is logged on on a separate modal*/

                            String user1 = (String)request.getAttribute("username");
                            String sql4,sql5,sql6;
                            String connectionURL1="jdbc:derby://localhost:1527/WTFtask";
                            sql4 ="SELECT * FROM WTFFriends where MAINUSERNAME = '"+user1+"'";
                            try {
                                Connection conn1 = DriverManager.getConnection(connectionURL1, "IS2560","IS2560");
                                Statement s4 = conn1.createStatement();
                                Statement s5 = conn1.createStatement();
                                ResultSet rs3 = s4.executeQuery(sql4);
                                int count1=0;
                                while(rs3.next())
                                {
                                    sql5="SELECT * from WTFuser where USERNAME='"+rs3.getString("FRIENDNAME")+"'";
                                    ResultSet rs4 = s5.executeQuery(sql5);
                                    rs4.next();
                                    count1++;
                                    out.println("<div class='row'>");
                                    out.println("<div class='col-md-6' align='center'>");
                                    out.println("<div class='event' align='left'>");
                                    out.println("<span>#00"+count1+"</span>");
                                    out.println("<div class='info'>");
                                    out.println("<br />"+rs4.getString("FIRSTNAME")+" "+rs4.getString("LASTNAME")+"</div></div></div>");
                                    boolean flag1=rs3.next();
                                    if(flag1==true)
                                    {    
                                        sql6="SELECT * from WTFuser where USERNAME='"+rs3.getString("FRIENDNAME")+"'";
                                        ResultSet rs5 = s5.executeQuery(sql6);
                                        rs5.next();
                                        count1++;
                                        out.println("<div class='col-md-6' align='center'>");
                                        out.println("<div class='event' align='left'>");
                                        out.println("<span>#00"+count1+"</span>");
                                        out.println("<div class='info'>");
                                        out.println("<br />"+rs5.getString("FIRSTNAME")+" "+rs5.getString("LASTNAME")+"</div></div></div></div>");
                                        rs5.close();
                                    }
                                    else
                                    {
                                        out.println("</div>");
                                    }
                                    rs4.close();

                                }
                                s5.close();
                                rs3.close();
                                s4.close();
                                conn1.close();

                            }
                            catch(SQLException e)
                            {
                                e.printStackTrace();
                            }



                        %>
                </div>
            </div>
        </div>
    </div>
    <!-- End show friends modal-->

    <!-- Modal for adding and inviting new friends-->
    <div id="addfriendmodal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="border-radius:20px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><br><br>
                    <h3 class="modal-title" align="center">Add a Friend</h3></br>
                    <form id ="searchForm" class="form-inline" align="center" method="get" action="New_friend">
                        <div class="form-group">
                            <div class="col-md-10 col-xs-10">
                                <input type="text" name="searchname" id="searchname" class="form-control input-md" Placeholder="Search..." required>
                            </div>
                            <div class="col-md-1 col-xs-1" style="padding-top:2px;">
                                <button id="SearchButton" type="button" onclick="Search()"><span class="glyphicon glyphicon-search"></span> </button> 
                            </div></br></br>
                            <input type="hidden" class="form-control input-md" name = "mainuser" id="mainuser" value="<%=request.getAttribute("username")%>">
                            <input type="hidden" class="form-control input-md" name = "mainuser_firstname" id="mainuser" value="<%=request.getAttribute("Name")%>">
                            <input type="hidden" class="form-control input-md" name = "searched_username" id="searched_username" >
                            <button class="btn btn-success"  type="disable" id="addfriend" disabled >Add</button>
                        </div><br>

                        <div id="searchUpdate" style="color:red;"></div>
                        <a align="center" id="Invite" href="#" >Can't find your friend? Invite them!</a>  
                    </form>
                    <form id="inviteForm" class="form-inline" align="center" method="get" action = "Add_Friend" >
                        <div class="form-group">
                            <input type="text" class="form-control" name="firstname" Placeholder="First name" />
                        </div>
                        <div id="break">
                            <br>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="lastname" Placeholder="Last name"/>
                        </div>
                        <div id="break">
                            <br>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-control" name="email" Placeholder="Email address"/>
                        </div>
                        <div class="form-group">
                            <input type="hidden" class="form-control input-md" name = "user" value="<%=request.getAttribute("Name")%>">
                        </div>
                        <div class="form-group">
                            <input type="hidden" class="form-control input-md" name = "username" value="<%=request.getAttribute("username")%>">
                        </div>
                        <div id="break">
                            <br>
                        </div>
                        <div class="form-group">
                            <button type ="submit" id="login" href="#" class="btn btn-primary" align="center">Invite</button>
                        </div><br>
                    </form>	
                </div>
            </div>
        </div>
    </div>
    <!-- End add friend modal-->                    
    
    <!-- modal for adding new tasks-->
    <div id="addtaskmodal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><br><br>
                    <h3 class="modal-title" align="center">Add a task</h3></br>
                    <form id="addtaskForm" class="form-inline" align="center" method="get" action="Add_Task">
                        <div class="form-group">
                            <input type="text" class="form-control" name="taskname" Placeholder="Task name" />
                            <div id="break">
                                <br>
                            </div>
                        </div><br>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Points" name="taskpoints"/>
                            <div id="break">
                                <br>
                            </div>
                        </div><br>
                        <div class="form-group">
                            <input type="hidden" class="form-control"  name="user" value = "<%=request.getAttribute("username")%>"/>
                            <div id="break">
                                <br>
                            </div>
                        </div><br>
                        <div class="form-group">
                            <input type="hidden" class="form-control" id="Name" name="Name" value = "<%=request.getAttribute("Name")%>"/>
                            <div id="break">
                                <br>
                            </div>
                        </div><br>
                        <div class="form-group">
                            <div class="col-lg-12 col-xs-12">
                                <div class="input-group date" >
                                    <input type="text" class="form-control"  id="duedate" name="duedate" Placeholder="Due date" />
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>  
                            </div>
                        </div>
                        <br>
                        <div id="break">
                            <br>
                        </div>
                        <div class="form-group">
                            <div class="col-md-9 col-xs-9">
                                <input type="text" class="form-control" Placeholder="Add friends..." id="addedfriend" name="addedfriend">
                            </div>
                            <div class="col-md-3 col-xs-3">
                               <button id="add" type="button" class="btn btn-success" onclick="showFriend()"> Add</button>	
                            </div>
                        </div><br><br>
                        <div id="somediv"  style="color:red;"></div>
                        <div id="break-inverse">
                            <br><br>
                        </div>
                        <div id="content"></div><br>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Add task</button><br><br>
                        </div><br>
                    </form>
                </div>
            </div>
        </div>
    </div>
     <!-- end add task modal-->                                                         
     
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
    <script src="http://bsdp-assets.blackcherry.us/1.3.0/bootstrap-datepicker.min.js"></script>
    <!-- BootstrapValidator JS -->
    <script type="text/javascript" src="dist/js/bootstrapValidator.min.js"></script>

    <script>

	i = 0;
	
	 $(function () {
                $("#duedate").datepicker();
            });
          
        //Here the entered name is validated from the database via an ajax call to determine if the said person exists.
	$("#SearchButton").click(function(){
           var searchname = $("#searchname").val();
           var mainuser = $("#mainuser").val();
            $.get('Search',"&searchname="+searchname+"&mainuser="+mainuser,function(ResponseText){
              var parts = ResponseText.split("&");
              var response = parts[0];
              var username = parts[1];
              if(response=="true") {
                    $("#searchUpdate").text("User Found");
                    $("#addfriend").removeAttr("disabled");     
              }
              else
                  $("#searchUpdate").text("User not Found");
              $("#searched_username").attr('value',username);
           });
        });
	
        //Here the entered name is validated from the database via an ajax call to check whether the said person is a friend or not.
        function showFriend() {
                 var addedfriend = $("#addedfriend").val();
                 var curr_user = $("#Name").val();
                 if (addedfriend != curr_user) {
                    $.get('Validate_Assignee','&curr_user='+curr_user+'&addedfriend='+addedfriend, function(responseText) { // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
                          if (responseText === "true") {
                              $("#somediv").text("");
                              i = i + 1;
                              var name = document.getElementById("addedfriend").value;
                              string = "<div id='here"+i+"' onClick='removeFriend(this)' ><input type='text' style='border:none' name='list'  value='"+name+"' />"+"&nbsp;<span class='glyphicon glyphicon-remove' style='color:#7F7F7F;'></span><br></div>";
                              $("#content").append(string);
                              $("#addedfriend").val('');
                           }
                           else if (responseText == "false"){
                              $("#somediv").text("Not your friend! Add him first!");
                              $("#addedfriend").val('');
                           }
                           else {
                               $("#somediv").text(responseText);
                               $("#addedfriend").val('');  
                           }
                       });
                   }
                   else {
                        $("#somediv").text("");
                        i = i + 1;
                        var name = document.getElementById("addedfriend").value;
                        string = "<div id='here"+i+"' onClick='removeFriend(this)' ><input type='text' style='border:none' name='list'  value='"+name+"' />"+"&nbsp;<span class='glyphicon glyphicon-remove' style='color:#7F7F7F;'></span><br></div>";
                        $("#content").append(string);
                        $("#addedfriend").val('');
                   }
	}
        
        //This code removes the added friend in the add task modal
	function removeFriend(item) {
		$(item).remove();
	}	
        
        //Show/hide and forms and reset values on close.
	$("#inviteForm").hide();
        
        $("#showaddtaskmodal").click(function() {	
                document.forms["addtaskForm"].reset();
                $("#somediv").text("");
                $("#content").text("");
	});
        
        $("#showaddfriendmodal").click(function(){

		$("#searchForm").show();
		$("#inviteForm").hide();
                document.forms["searchForm"].reset();
                document.forms["inviteForm"].reset();
	});
	
        $("#Invite").click(function(){
		$("#searchForm").hide();
		$("#inviteForm").show();
                document.forms["searchForm"].reset();
                document.forms["inviteForm"].reset();
	});
        
        //Carousel slide code to display 2 tasks on one slide and move ahead by 1 task.
        $('#myCarousel').carousel({
		  interval: 4000
		})

		$('.carousel .item').each(function(){
			  var next = $(this).next();
			  if (!next.length) {
				next = $(this).siblings(':first');
			  }
			  next.children(':first-child').clone().appendTo($(this));
			  
			});
        
        
        $(document).ready(function() {
            // Bootstrap validator code for add task form.
            $('#addtaskForm').bootstrapValidator({
                    container:'tooltip',
                    feedbackIcons: {		
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
			taskname: {
                        	validators: {
				notEmpty: {
                                    message: 'Task name is required'
                                },
                                regexp: {
                                    regexp: /^[a-zA-Z ]+$/,
                                    message: 'Alphabets only'
                                },
                                }
                        },
                        taskpoints: {
                            validators: {
				notEmpty: {
                                    message: 'Task points are required'
				},
				regexp: {
                                    regexp: /^[0-9]+$/,
                                    message: 'Numbers only'
				},
                            }
                        },
                    }
            });
                
            //Bootstrap validator code for add friend form.
            $('#inviteForm').bootstrapValidator({
                container:'tooltip',
		feedbackIcons: {
			valid: 'glyphicon glyphicon-ok',
			invalid: 'glyphicon glyphicon-remove',
                    	validating: 'glyphicon glyphicon-refresh'
		},
		fields: {
			firstname: {
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
			lastname: {
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
                        email: {
				validators: {
                            		notEmpty: {
                                    		message: 'Email is required'
					},
					regexp: {
						regexp: /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/,
                                                message: 'Email is invalid'
                                        },
                                }
                        },
		}
            });
        });
	
    </script>
  </body>
</html>




