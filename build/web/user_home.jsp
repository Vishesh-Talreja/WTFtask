<%-- 
    Document   : faulty_login
    Created on : Sep 29, 2014, 8:55:34 PM
    Author     : aashish kanade, vinay rajagopalan, vishesh talreja
--%>
   


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.io.*" %>

<%@ page import="java.sql.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.text.*" %>
<%@ page import="javax.servlet.http.Cookie;" %>
<%@ page import="java.util.Calendar.*" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="org.joda.time.format.*" %>
<%@ page import="org.joda.time.LocalDate" %>


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
    <script type="text/javascript" src="js/canvasjs.min.js"></script>
    
	<style type="text/css">
		
		.datepicker{z-index:1151 !important;}
		
		.btn-primary  {
			background-color:#336699;
			border-color:#336699;
		}
		
                	
		body {
		  background: -webkit-gradient(#2ECCFA,white); /* Chrome,Safari4+ */
		  background: -webkit-linear-gradient(#2ECCFA,white); /* Chrome10+,Safari5.1+ */
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
      
      
      <%
        /*String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) userName = cookie.getValue();
        }
        }
        if(userName == null) response.sendRedirect("task_login.jsp");
        return;*/
      %>
      
      <%
         Calendar cal = Calendar.getInstance(); 
         int year = cal.get(Calendar.YEAR);
         int month = cal.get(Calendar.MONTH)+1;
         int day = cal.get(Calendar.DAY_OF_MONTH);
         String currdate = Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
         DateTimeFormatter formatter = DateTimeFormat.forPattern( "yyyy-MM-dd" );
         LocalDate curr_date = formatter.parseLocalDate( currdate );
    
      %>
      
      <%
          String connectionURL="jdbc:derby://localhost:1527/WTFtask";
          Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
          boolean flag = false;
         try {
            
            Statement getTask = conn.createStatement();
            Statement updateTask = conn.createStatement();
            String getTasksQuery = "SELECT * FROM WTFtasks";
            ResultSet getTaskSet = getTask.executeQuery(getTasksQuery);
            ResultSet updateTaskSet;
            while(getTaskSet.next()) {
                
                
                if(getTaskSet.getString("RECUR").equals("weekly") ||getTaskSet.getString("RECUR").equals("monthly") ) {
                    
                    LocalDate task_date = formatter.parseLocalDate( getTaskSet.getString("DUEDATE"));
                    if (curr_date.isAfter(task_date)) {
                        flag = true;
                        String curr_task_date = task_date.toString();
                        String new_task_date = "";
                        if (getTaskSet.getString("RECUR").equals("weekly")) {
                            System.out.println("GOING TO ADD BY 7 "+getTaskSet.getString("TASKNAME"));
                            task_date = task_date.plusDays(7);
                            new_task_date = task_date.toString(); 
                            System.out.println("ADDED 7 days to "+getTaskSet.getString("TASKNAME")+ "OLD TASK DATE WAS "+ curr_task_date +" NEW DUE DATE IS " + new_task_date); 
                        }
                        else if (getTaskSet.getString("RECUR").equals("monthly")) {
                            System.out.println("GOING TO ADD BY 30 "+getTaskSet.getString("TASKNAME"));
                            task_date = task_date.plusDays(30);
                            new_task_date = task_date.toString();
                            System.out.println("ADDED 30 days to "+getTaskSet.getString("TASKNAME")+ "OLD TASK DATE WAS "+ curr_task_date +" NEW DUE DATE IS " + new_task_date); 
                        }
                        String updateTaskDate = "UPDATE IS2560.WTFtasks SET DUEDATE='"+ new_task_date +"' WHERE DUEDATE= '"+ curr_task_date +"'";
                        updateTask.executeUpdate(updateTaskDate);
                    }
                    else
                        flag = false;
                }
                
            }
            if (flag == false)
                System.out.println("No tasks to update");
            System.out.println("END WHILE");
            getTaskSet.close();
            getTask.close();
         }
          catch (SQLException e) {
              e.printStackTrace();
          }
          catch (Exception e) {
              e.printStackTrace();
          }
         
      %>
      
  <div class="col-md-2"></div>
  <div class="col-md-8 col-xs-12" style='padding: 0'>
    <!-- main container for page content-->
    <div style ="height:100vh;background-color: white;padding: 2em">
        <!-- top navbar-->
        <nav class="navbar navbar-default" role="navigation" style="border:hidden ;background-color:#2E9AFE;width: 100%;margin: -1">
          <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar" style="background-color:white;"></span>
                <span class="icon-bar" style="background-color:white;"></span>
                <span class="icon-bar" style="background-color:white;"></span>
              </button>

            <div class="navbar-brand" style="padding-top:0px;"><img  src="img/logo_nav.gif" style="height:140%;width50%;"/></div>

            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right" >
                    <li id="chart"> <a id="chartdisplaymodal" href="#chartdisplay" class="btn-group-sm" data-toggle="modal"  style="color:white" onclick="chartdisplay()">Chart</a></li>
                    <li id="group"><a id="showdisplayfriendmodal" href="#displayfriendmodal" class="btn-group-sm" data-toggle="modal"  style="color:white">Friends</a></li>
                    <li id="group"><a id="showaddtaskmodal" href="#addtaskmodal" class="btn-group-sm" data-toggle="modal"  style="color:white">Add a Task</a></li>
                    <li id="friend"><a id="showaddfriendmodal" href="#addfriendmodal" class="btn-group-sm" data-toggle="modal" style="color:white">Add a Friend</a></li>


                    <li ><a href="task_login.jsp" onclick="logout()" class="btn-group-sm" style="color:white">Log Out</a></li>

                </ul> 
            </div><!-- /.navbar-collapse -->
          </div><!-- /.container-fluid -->
        </nav><!-- end navbar-->
        
        <!-- Welcome message-->
        <h1 style="font-face:papyrus">Welcome <%=request.getAttribute("Name")%> </h1><br>

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
        <div class="row">
        <div class="col-md-8">
            <div class="col-md-4 col-md-offset-4 text-center"><h4><a  href="#myCarousel" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>&nbsp;Your Tasks&nbsp;<a  href="#myCarousel" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a></h4></div>
            <div class="col-md-12 col-xs-12">
                <div class="carousel slide" id="myCarousel">
                    <div class="carousel-inner">
                        <%
                        /*This block of java code displays the tasks the user has to complete, here it 
                          first connects to the database and then displays them in the form of thumbnails*/
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = new Date();
                        String user = (String)request.getAttribute("username");
                        String Name = (String)request.getAttribute("Name");
                        String sql,sql3;
                        
                        sql3 ="SELECT TASKID,STATUS FROM WTFtaskallocation where USERNAME = '"+user+"'";
                        int task_year,task_month,task_day;
                        try {
                            
                            Statement s = conn.createStatement();
                            Statement s1 = conn.createStatement();
                            Statement s2 = conn.createStatement();
                            ResultSet rs2 = s2.executeQuery(sql3);
                            int count = 0;
                            
                            while(rs2.next()){
                                sql = "SELECT * FROM WTFtasks where TASKID ="+rs2.getInt("TASKID");
                                ResultSet rs = s.executeQuery(sql);
                                while (rs.next()) {

                                      //String date[] = rs.getString("DUEDATE").split("-");
                                      //task_year = Integer.parseInt(date[0]);
                                      //task_month = Integer.parseInt(date[1]);
                                      //task_day = Integer.parseInt(date[2]);
                                      //if (year == task_year && month == task_month && day == task_day) {
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
                                      String duedate=rs.getString("DUEDATE");
                                      Date date1 = dateFormat.parse(duedate);
                                      out.println("<div class='col-lg-2 col-xs-12' >");
                                      if(rs2.getString("STATUS").equalsIgnoreCase("Complete"))
                                      {
                                          out.println("<div class='thumbnail' style = 'background-color:#A9F5A9;color:white;' align='center'>");
                                      }
                                      else
                                      {    
                                      if(date1.before(date)){
                                            out.println("<div class='thumbnail' style = 'background-color:#F5A9A9;color:white;' align='center'>");
                                      }
                                      else
                                      {    
                                      out.println("<div class='thumbnail' style = 'background-color:#E6E6E6;color:white;' align='center'>");
                                      }
                                      }
                                      out.println("<div class='caption'>");
                                      out.println("<h3>"+rs.getString("TASKNAME")+"</h3>");
                                      out.println("<p>POINTS: "+rs.getString("TASKPOINTS")+"<br>OWNER: "+rs1.getString("FIRSTNAME")+" "+rs1.getString("LASTNAME")+"<br>DUE-DATE: "+rs.getString("DUEDATE")+"</p>");
                                      //String Tpoints=rs.getString("TASKPOINTS");
                                      if(rs2.getString("STATUS").equalsIgnoreCase("Complete"))
                                      {
                                          out.println("<p><form method = 'get' action = 'Complete_Task'><button type ='submit' disabled='disabled' id='login' href='#' class='btn btn-primary' align='center'>Wrap Up</button></form></p>");
                                      }
                                      else
                                      {
                                          out.println("<p><form method = 'get' action = 'Complete_Task'><input type='hidden' name='Tname' value = '"+rs.getString("TASKNAME")+"'/><input type='hidden' name='Tpoints' value = '"+rs.getString("TASKPOINTS")+"'/><input type='hidden' name='Name' value = '"+Name+"'/><input type='hidden' name='user' value = '"+user+"'/><button type ='submit' id='login' href='#' class='btn btn-primary' align='center'>Wrap Up</button></form></p>");
                                      }
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
           <div class="col-md-4">
               <canvas id="myCanvas"></canvas>
                <script>
                  var canvas = document.getElementById('myCanvas');
                  var context = canvas.getContext('2d');

                  context.beginPath();
                  context.rect(0, 40, 50, 25);
                  context.fillStyle = '#A9F5A9';
                  context.fill();
                  context.fillStyle = 'black';
                  context.font = '16pt Calibri';
                  context.fillText('Completed Tasks', 60, 60);
                  
                  context.beginPath();
                  context.rect(0, 80, 50, 25);
                  context.fillStyle = '#F5A9A9';
                  context.fill();
                  context.fillStyle = 'black';
                  context.font = '16pt Calibri';
                  context.fillText('Past Due Date', 60, 100);
                </script>
               
           </div>
        </div>
        <!-- End View tasks carousel-->
        
        <!-- View owned tasks carousel-->
        <div class="row">
        <div class="col-md-8">
            <div class="col-md-6 col-md-offset-3 text-center"><h4><a  href="#myCarousel1" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>&nbsp;Tasks You Own&nbsp;<a  href="#myCarousel1" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a></h4></div>
            <div class="col-md-12 col-xs-12">
                <div class="carousel slide" id="myCarousel1">
                    <div class="carousel-inner">
                        <%  
                        /*This block of java code displays the tasks the user owns, here it 
                          first connects to the database and then displays them in the form of thumbnails*/

                        //String user2 = (String)request.getAttribute("username");
                        String sql7,sql10;
                        String connectionURL2="jdbc:derby://localhost:1527/WTFtask";
                        
                        sql7 ="SELECT * FROM WTFtasks WHERE OWNER  = '"+user+"'";
                        try {
                            Connection conn2 = DriverManager.getConnection(connectionURL2, "IS2560","IS2560");
                            Statement s6 = conn2.createStatement();
                            ResultSet rs8 = s6.executeQuery(sql7);
                            
                            
                            int count2 = 0;

                            while(rs8.next()){
                                      Statement s15 = conn2.createStatement();
                                      sql10= "SELECT STATUS FROM WTFtaskallocation WHERE USERNAME = '"+user+"' and TASKID = "+rs8.getString("TASKID")+"";
                                      ResultSet rs10 = s15.executeQuery(sql10);
                                      while(rs10.next())
                                      {
                                      if(count2==0)
                                      {    
                                      out.println("<div class='item active'>");
                                      }
                                      else
                                      {
                                         out.println("<div class='item'>"); 
                                      }
                                      String duedate1=rs8.getString("DUEDATE");
                                      Date date2 = dateFormat.parse(duedate1);
                                      out.println("<div class='col-lg-2 col-xs-12' >");
                                      if(rs10.getString("STATUS").equalsIgnoreCase("Complete"))
                                      {
                                          out.println("<div class='thumbnail' style = 'background-color:#A9F5A9;color:white;' align='center'>");
                                      }
                                      else
                                      {
                                      if(date2.before(date)){
                                          out.println("<div class='thumbnail' style = 'background-color:#F5A9A9;color:white;' align='center'>");
                                      }
                                      else
                                      { 
                                          out.println("<div class='thumbnail' style = 'background-color:#E6E6E6;color:white;' align='center'>");
                                      }
                                      }
                                      out.println("<div class='caption'>");
                                      out.println("<h3>"+rs8.getString("TASKNAME")+"</h3>");
                                      out.println("<p>POINTS: "+rs8.getString("TASKPOINTS")+"<br>DUE-DATE: "+rs8.getString("DUEDATE")+"</p>");
                                      if(rs10.getString("STATUS").equalsIgnoreCase("Complete"))
                                      {
                                          out.println("<p><form method = 'get' action = 'Complete_Task'><button type ='submit' disabled='disabled' id='login' href='#' class='btn btn-primary' align='center'>Remind</button></form></p>");
                                      }
                                      else
                                      {
                                          out.println("<p><form method = 'get' action = 'Complete_Task'><button type ='submit' id='login' href='#' class='btn btn-primary' align='center'>Remind</button></form></p>");
                                      }
                                      out.println("</div></div></div></div>");
                                      count2++;
                                      }
                                      rs10.close();
                                      s15.close();

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
                    <div class="col-md-4" style="padding-top:40px">
                        Your progress:
                        <div class="progress">
                        <div class="progress-bar progress-bar-striped active"  role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width: 60%">
                         60%<span class="sr-only">45% Complete</span>
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

                            //String user1 = (String)request.getAttribute("username");
                            String sql4,sql5,sql6;
                            String connectionURL1="jdbc:derby://localhost:1527/WTFtask";
                            sql4 ="SELECT * FROM WTFFriends where MAINUSERNAME = '"+user+"'";
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
    <div id="chartdisplay" class="modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="border-radius:20px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><br><br>
                    <form>
                    <input type="hidden" class="form-control input-md" name = "mainuser" id="mainuser" value="<%=request.getAttribute("username")%>">
                     </form>
                    <div id="chartContainer" style="height: 500px; width: 100%;">  </div> 
                 </div>
            </div>
        </div>
    </div>
    <!-- modal for adding new tasks-->
    <div id="addtaskmodal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <br><br>
                    <h3 class="modal-title" align="center">Add a task</h3>
                    <br>
                    <form id="addtaskForm" class="form-inline" align="center" method="get" action="Add_Task">
                        <div class="form-group">
                            <input type="text" class="form-control" name="taskname" Placeholder="Task name" /> 
                            <br>
                        </div>
                        <br>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Points" name="taskpoints"/>
                        </div>
                        <br><br>
                        <div class="form-group">
                            <input type="hidden" class="form-control"  name="user" value = "<%=request.getAttribute("username")%>"/>
                        </div>
                        <br>
                        <div class="form-group">
                            <input type="hidden" class="form-control" id="Name" name="Name" value = "<%=request.getAttribute("Name")%>"/>
                        </div>
                        <br>
                        <div class="form-group">
                            <div class="col-lg-12 col-xs-12">
                                <div class="input-group date" id="duedate">
                                    <input type="text" class="form-control"   name="duedate" Placeholder="Due date" />
                                    <span class="input-group-addon">
                                        <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
                                </div>  
                            </div>
                        </div>
                        <br><br>
                        <div class="form-group">
                            <div class="radio">
                                <b>Recurring :</b>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="recur" id="weekly" value="weekly">
                                    Weekly
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="recur" id="monthly" value="monthly">
                                    Monthly
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="recur" id="none" value="none">
                                    None
                                </label>
                            </div>
                        </div>
                        <br><br>
                        <div class="form-group">
                            <div class="col-md-9 col-xs-9">
                                <input type="text" class="form-control" Placeholder="Add friends..." id="addedfriend" name="addedfriend">
                            </div>
                            <div class="col-md-3 col-xs-3">
                                <button id="add" type="button" class="btn btn-success" onclick="showFriend()"> Add</button>	
                            </div>
                        </div>
                        <br><br>
                        <div id="somediv"  style="color:red;"></div>
                        <div id="content"></div>
                        <br>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary">Add task</button><br><br>
                        </div>
                        <br>
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
	var date = new Date();
        date.setDate(date.getDate());
	 $(function () {
                $("#duedate").datepicker({startDate: date});
                
                $('#duedate').on('changeDate', function(ev){
                    $(this).datepicker('hide');
                });
            });
          function chartdisplay(){
             var mainuser = $("#mainuser").val();
             console.log(mainuser);
             var list =[];
             var list1=[];
             var list2=[];
             var i=0;
             $.get('DisplayChart',"&mainuser="+mainuser,function(ResponseText){
               $.each(ResponseText, function(index, item)
               {
                   
                 $.each(item,function(index,data){
                    if(i===1)
                    {
                    list.push(data);
                    }
                    else if(i===2)
                    {
                        list1.push(data);
               
                    }
                    else
                    {
                        list2.push(data);
                    }
                    
                })
                i++;
               })
               console.log(list);
               console.log(list1);
               console.log(list2);
               var datapointsgraph=[[]];
               var datapointsgraph2=[[]];
               var data=[[]];
               var j=0;
               for (i=0;i<list.length;i++)
               {
                   datapointsgraph[j]={
                       
                       label:list1[i],y:Number(list2[i])
                   }
                   //console.log(datapoints[j]);
                   j++;
               }
               var k=0;
                for (i=0;i<list.length;i++)
               {
                   //console.log(list[i]-list2[i]);
                   datapointsgraph2[k]={
                       
                       label:list1[i],y:Number(list[i]-list2[i])
                       
                   }
                   //console.log(datapoints[j]);
                   k++;
               }
               //var z=datapointsgraph[0].y;
               //var q=datapointsgraph[1].y;
              var chart = new CanvasJS.Chart("chartContainer", {
                  
                                title:{
                                text:"Graphical Representaion"   
                                },
                                axisY:{
                                  title:"Points"   
                                },
                                data: [
                                {        
                                  type: "stackedColumn",
                                  toolTipContent: "{label}<br/><span style='\"'color: {color};'\"'><strong>{name}</strong></span>: {y}",
                                  name: "Points Completed",
                                  showInLegend: "true",
                                  color:"#A9F5A9",
                                  dataPoints: datapointsgraph
                                },  {        
                                  type: "stackedColumn",
                                  toolTipContent: "{label}<br/><span style='\"'color: {color};'\"'><strong>{name}</strong></span>: {y}",
                                  name: "Points Remaining",
                                  showInLegend: "true",
                                  color:"#F5A9A9",
                                  dataPoints: datapointsgraph2
                                }            
                                ]
                                
                                /**legend:{
                                  cursor:"pointer",
                                  itemclick: function(e) {
                                    if (typeof (e.dataSeries.visible) ===  "undefined" || e.dataSeries.visible) {
                                            e.dataSeries.visible = false;
                                    }
                                    else
                                    {
                                      e.dataSeries.visible = true;
                                    }
                                    chart.render();
                                  }
                                }**/
                              })

     chart.render();
              
    })
  }; 
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
              else if(response=="false1")
              {
                   $("#searchUpdate").text("You can't add yourself");
                   $("#searched_username").attr('value',username);
              }
              else
              {
                  $("#searchUpdate").text("User not Found");
              $("#searched_username").attr('value',username);
          }
           });
        });
	
        function logout() {
            $.get('Logout');
        }
        
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
		  interval: 4000,
                  wrap: false
		})
        $('#myCarousel1').carousel({
		  interval: 4000,
                  wrap: false
		})
                
		$('.carousel .item').each(function(){
			  var next = $(this).next();
			  if (!next.length) {
				next = $(this).siblings(':first');
			  }
			  next.children(':first-child').clone().appendTo($(this));
			  
			});
        
        
        $(document).ready(function() {
            $('.carousel').carousel('pause');
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




