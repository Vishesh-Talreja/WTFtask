<%-- 
    Document   : user_home_new
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
    <link href="css/metro-bootstrap.min.css" rel="stylesheet">
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
                
                html { height:100%;width:100%}
		
                body { height:100%;width:100%}
		::-webkit-scrollbar { 
                    display: none; 
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
        */
      %>
      
      
      
      <%
         //This piece of code is used to extract the current system date
         Calendar cal = Calendar.getInstance(); 
         int year = cal.get(Calendar.YEAR);
         int month = cal.get(Calendar.MONTH)+1;
         int day = cal.get(Calendar.DAY_OF_MONTH);
         String currdate = Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
         DateTimeFormatter formatter = DateTimeFormat.forPattern( "yyyy-MM-dd" );
         LocalDate curr_date = formatter.parseLocalDate( currdate );
    
      %>
      
      <%
         
         //This piece of code is used to reset the due date for a recurring task whose due date has already passed. THis code extracts 
         //the due dates of each task in the database one by one, compares it to the system date and if it is past the system date, adds the designated amount of days to the due date.
         String connectionURL="jdbc:derby://localhost:1527/WTFtask";
         Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
         boolean flag = false;
         try {
            
            Statement getTask = conn.createStatement();
            Statement updateTask = conn.createStatement();
            String getTasksQuery = "SELECT * FROM WTFtasks";
            ResultSet getTaskSet = getTask.executeQuery(getTasksQuery);
            ResultSet updateTaskSet;
            int taskid;
            while(getTaskSet.next()) {
                taskid = getTaskSet.getInt("TASKID");
                //System.out.println("THE ID IS "+taskid);
                if(getTaskSet.getString("RECUR").equals("weekly") ||getTaskSet.getString("RECUR").equals("monthly") ) {
                    
                    LocalDate task_date = formatter.parseLocalDate( getTaskSet.getString("DUEDATE"));
                    if (curr_date.isAfter(task_date)) {
                        flag = true;
                        String curr_task_date = task_date.toString();
                        String new_task_date = "";
                        if (getTaskSet.getString("RECUR").equals("weekly")) {
                            //System.out.println("GOING TO ADD BY 7 "+getTaskSet.getString("TASKNAME"));
                            task_date = task_date.plusDays(7);
                            new_task_date = task_date.toString(); 
                            //System.out.println("ADDED 7 days to "+getTaskSet.getString("TASKNAME")+ "OLD TASK DATE WAS "+ curr_task_date +" NEW DUE DATE IS " + new_task_date); 
                        }
                        else if (getTaskSet.getString("RECUR").equals("monthly")) {
                            //System.out.println("GOING TO ADD BY 30 "+getTaskSet.getString("TASKNAME"));
                            task_date = task_date.plusDays(30);
                            new_task_date = task_date.toString();
                            //System.out.println("ADDED 30 days to "+getTaskSet.getString("TASKNAME")+ "OLD TASK DATE WAS "+ curr_task_date +" NEW DUE DATE IS " + new_task_date); 
                        }
                        System.out.println("THE ID IS "+taskid);
                        String updateTaskDate = "UPDATE IS2560.WTFtasks SET DUEDATE='"+ new_task_date +"' WHERE DUEDATE= '"+ curr_task_date +"'";
                        updateTask.executeUpdate(updateTaskDate);
                        String updateTaskStatus = "UPDATE IS2560.WTFtaskallocation SET STATUS='Pending' WHERE TASKID="+taskid;
                        updateTask.executeQuery(updateTaskStatus);
                    }
                    else
                        flag = false;
                }
                
            }
            if (flag == false)
                //System.out.println("No tasks to update");
            //System.out.println("END WHILE");
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
      
  <!-- Main content-->
    <div class="col-md-2 col-xs-0">
			
    </div>
    <div class="col-md-8 col-xs-12">

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
        

                        
                    
                    
        <!-- View owned tasks carousel
        <div class="row">
        <div class="col-md-8">
            <div class="col-md-6 col-md-offset-3 text-center"><h4><a  href="#myCarousel1" data-slide="prev"><i class="glyphicon glyphicon-chevron-left"></i></a>&nbsp;Tasks You Own&nbsp;<a  href="#myCarousel1" data-slide="next"><i class="glyphicon glyphicon-chevron-right"></i></a></h4></div>
            <div class="col-md-12 col-xs-12">
                <div class="carousel slide" id="myCarousel1">
                    <div class="carousel-inner">
                        <%  
                        /*This block of java code displays the tasks the user owns, here it 
                          first connects to the database and then displays them in the form of thumbnails

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
                                      sql10= "SELECT STATUS FROM WTFtaskallocation WHERE TASKID = "+rs8.getString("TASKID")+"";
                                      ResultSet rs10 = s15.executeQuery(sql10);
                                      if(rs10.next())
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
                        */
                          %>
                    </div>
                </div>
            </div>  
        </div>
            
                    
                    
        </div>-->

                         
                         
        
        <!-- Top bar -->                 
        <div class="row" style="height:8rem;background-color:#7f7f7f">
            <img src="img/logo_nav.gif" align="center" style="height:100%;padding-left:1rem;"></img>
            <div style="float:right;padding-top:3rem;padding-right:3rem;color:white">Signed in as <b><%=request.getAttribute("Name")%></b></div>
        </div>
	
        <!-- Spacing row -->
        <div class="row" style="height:1rem;"></div>
		
        <!-- Toggle menu -->
        <div role="tabpanel">

          <!-- Nav tabs -->
          <ul id="myTab" class="nav nav-tabs nav-justified" role="tablist">
                <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">Home</a></li>
                <li role="presentation"><a href="#tasks" aria-controls="profile" role="tab" data-toggle="tab">Tasks</a></li>
          </ul>

          <!-- Tab panes -->
          <div class="tab-content">

                <!-- Home page -->
                <div role="tabpanel" class="tab-pane active" id="home">
                    <div class="row" style="height:3rem;"></div>
                    <div class="row" style="height:1%;overflow:hidden;">
                        <div class="col-md-3 col-xs-6">
                            <div class="thumbnail tile" style="width:100%;padding:2rem;background-color:white;border:none">
                                <a id="showaddtaskmodal" href="#addtaskmodal" data-toggle="modal">
                                    <h4 align="center" style="color:black;"> Add a task </h4>
                                    <img src="img/add_task.png" />
                                </a>
                            </div>

                        </div>
                        <div class="col-md-3 col-xs-6">
                            <div class="thumbnail tile" style="width:100%;padding:2rem;background-color:white;border:none">
                                <a id="showaddfriendmodal" href="#addfriendmodal" data-toggle="modal">
                                    <h4 align="center" style="color:black;"> Add a friend </h4>
                                    <img src="img/add_friend.png" />
                                </a>
                            </div>
                        </div>
                        <div class="col-md-3 col-xs-6">
                            <div class="thumbnail tile" style="width:100%;padding:2rem;background-color:white;border:none">
                                <a id="chartdisplaymodal" href="#chartdisplay" data-toggle="modal" onclick="chartdisplay()">
                                    <h4 align="center" style="color:black;"> View charts </h4>
                                    <img src="img/view_chart.png" />
                                </a>
                            </div>
                        </div>
                        <div class="col-md-3 col-xs-6">
                            <div class="thumbnail tile" style="width:100%;padding:2rem;background-color:white;border:none">
                                <a href="task_login.jsp">
                                    <h4 align="center" style="color:black;"> Logout </h4>
                                    <img src="img/logout.png" /> 
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-6" >
                            <div class="panel panel-default"  >
                                <div class="panel-heading" align="center" ><b>Task to do</b></div>
                                <div class="panel-body" style="height:20rem; overflow:auto">
                                    <table class="table table-hover">
                                        <tr>
                                            <th>Name</th>
                                            <th>Points</th>
                                            <th>Due date</th>
                                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                        </tr>
                                        <%
                                            /*This block of java code displays the tasks the user has to complete, here it 
                                              first connects to the database and then displays them in the form of thumbnails*/
                                            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                            Date date = new Date();
                                            String user = (String)request.getAttribute("username");
                                            String Name = (String)request.getAttribute("Name");
                                            System.out.println(user+" "+Name);
                                            String sql,sql3;

                                            sql3 ="SELECT TASKID,STATUS FROM WTFtaskallocation where USERNAME = '"+user+"'";
                                            int task_year,task_month,task_day;
                                            try {

                                                Statement s = conn.createStatement();
                                                Statement s1 = conn.createStatement();
                                                Statement s2 = conn.createStatement();
                                                ResultSet rs2 = s2.executeQuery(sql3);
                                                while(rs2.next()){
                                                    
                                                    sql = "SELECT * FROM WTFtasks where TASKID ="+rs2.getInt("TASKID");
                                                    ResultSet rs = s.executeQuery(sql);
                                                    while (rs.next()) {
                                                          //String date[] = rs.getString("DUEDATE").split("-");
                                                          //task_year = Integer.parseInt(date[0]);
                                                          //task_month = Integer.parseInt(date[1]);
                                                          //task_day = Integer.parseInt(date[2]);
                                                          //if (year == task_year && month == task_month && day == task_day) {
                                                          
                                                          String duedate=rs.getString("DUEDATE");
                                                          Date date1 = dateFormat.parse(duedate);
                                                          out.println("<tr>");
                                                          out.println("<td>"+rs.getString("TASKNAME")+"</td>");
                                                          out.println("<td>"+rs.getString("TASKPOINTS")+"</td>");
                                                          out.println("<td>"+rs.getString("DUEDATE")+"</td>");
                                                           if(rs2.getString("STATUS").equalsIgnoreCase("Complete"))
                                                            {
                                                                out.println("<td><form method = 'get' action = 'Complete_Task'><button type ='submit' disabled='disabled' id='login' href='#' class='btn btn-primary' align='center'>Wrap Up</button></form></td>");
                                                            }
                                                            else
                                                            {
                                                                out.println("<td><form method = 'get' action = 'Complete_Task'><button type ='submit' id='login' href='#' class='btn btn-primary' align='center'>Wrap Up</button></form></td>");
                                                            }
                                                          //out.println("<td><a href='#'><span class='glyphicon glyphicon-edit'></span></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href='#'><span class='glyphicon glyphicon-trash'></span></a></td>");
                                                          out.println("</tr>");

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
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <%
                                //This piece of code is used to update the individual progress bar of the logged in user. I); It wll extract the task completion statistics of the user such as
                                //total tasks assigned and tasks completed, calcuate the percentage of tasks completed and accordingly update the progress bar.
                                Connection conn4 = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
                                int totalTasks = 0;
                                int completedTasks = 0;
                                int percentage = 0;
                                try {
                                    Statement getProgress = conn4.createStatement();
                                    String getProgressQuery = "SELECT COUNT(*) FROM WTFTASKALLOCATION WHERE USERNAME = '"+user+"'";
                                    ResultSet totalSet = getProgress.executeQuery(getProgressQuery);
                                    boolean assigned = totalSet.next();
                                    if (assigned == true) {
                                        totalTasks = Integer.parseInt(totalSet.getString(1));

                                        getProgressQuery = "SELECT COUNT(*) FROM WTFTASKALLOCATION WHERE USERNAME = '"+user+"' AND STATUS = 'Complete'";
                                        ResultSet completeSet = getProgress.executeQuery(getProgressQuery);
                                        boolean completed = completeSet.next();
                                        if (completed == true) {
                                            completedTasks = Integer.parseInt(completeSet.getString(1));
                                            //System.out.println(completedTasks);
                                            //System.out.println(totalTasks);
                                            percentage = (int)((completedTasks*100)/totalTasks);
                                            //System.out.println(percentage);

                                        }
                                        completeSet.close();
                                    }
                                    else { 
                                        //System.out.println("no tasks assigned");
                                    }
                                    totalSet.close();
                                    getProgress.close();
                                    conn4.close();
                                }
                                catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                catch (Exception e) {
                                    e.printStackTrace();
                                }
                            %>
                            <div class="panel panel-default">
                                <div class="panel-heading" align="center"><b>Monthly progress</b></div>
                                <div class="panel-body" style="height:20rem; overflow:auto;padding:5rem;">
                                    <div class="col-md-3"><b>Your progress</b></div>
                                    <div class="col-md-9">
                                        <div class="progress">
                                            <div class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="45" aria-valuemin="0" aria-valuemax="100" style="width:<%= percentage %>%;color:black;">
                                                <span class="sr-only">45% Complete</span><%= percentage %>%
                                            </div>
                                        </div>
                                    </div>
                                    <p>Tasks completed : 2</p>
                                    <p>Tasks remaining : 2</p>
                                </div>
                            </div>	
                        </div>	
                    </div>

                    <!-- Spacing row-->
                    <div class="row" style="height:1rem"></div> <!-- End spacing row-->

                    <!-- 2nd row -->
                    <div class="row">
                        <!-- Friends panel-->
                        <div class="col-md-6">
                            <div class="panel panel-default" >
                                <div class="panel-heading" align="center"><b>Your Friends</b></div>
                                <div class="panel-body" style="height:20rem; overflow:auto">
                                    <table class="table table-hover">
                                        <tr>
                                            <th>Name</th>
                                            <th>Points earned</th>
                                            <th>Points remaining</th>
                                        </tr>
                                        <%  
                                            /*This piece of java code connects to the database and then displays the friends of the
                                            user that is logged on on a separate modal*/

                                            //String user1 = (String)request.getAttribute("username");
                                            String selectFriends,selectUser,sql6;
                                            String connectionURL10="jdbc:derby://localhost:1527/WTFtask";
                                            selectFriends ="SELECT * FROM WTFFriends where MAINUSERNAME = '"+user+"'";
                                            try {
                                                Connection conn1 = DriverManager.getConnection(connectionURL10, "IS2560","IS2560");
                                                Statement selectFriendStatement = conn1.createStatement();
                                                Statement selectUserStatement = conn1.createStatement();
                                                ResultSet friendSet = selectFriendStatement.executeQuery(selectFriends);
                                                
                                                while(friendSet.next())
                                                {
                                                    selectUser="SELECT * from WTFuser where USERNAME='"+friendSet.getString("FRIENDNAME")+"'";
                                                    ResultSet userSet = selectUserStatement.executeQuery(selectUser);
                                                    while(userSet.next()) {
                                                        out.println("<tr>");
                                                        out.println("<td>"+userSet.getString("FIRSTNAME")+"</td>");
                                                        out.println("<td>"+userSet.getString("POINTEARNED")+"</td>");
                                                        out.println("<td>"+(Integer.parseInt(userSet.getString("POINTPOSSIBLE"))- Integer.parseInt(userSet.getString("POINTEARNED")))+"</td>");
                                                        out.println("</tr>");
                                                    }
                                                    userSet.close();

                                                }
                                                selectUserStatement.close();
                                                friendSet.close();
                                                selectFriendStatement.close();
                                                conn1.close();

                                            }
                                            catch(SQLException e)
                                            {
                                                e.printStackTrace();
                                            }
                                        %>
                                        
                                    </table>
                                </div>
                            </div>
                        </div> <!-- End Friends panel-->

                        <!-- Extra panel-->
                        <div class="col-md-6" style="height:20rem;">
                                <div class="panel panel-default">
                                        <div class="panel-heading" align="center"><b>Extra</b></div>
                                        <div class="panel-body" style="height:20rem; overflow:auto; padding:5rem;">	
                                                <!-- Content -->
                                                Content to be added
                                        </div>
                                </div>
                        </div> <!-- End Extra Panel-->
                    </div> <!-- End 2nd row-->
                </div> <!-- End Home page -->

                <!--Tasks page -->
                <div role="tabpanel" class="tab-pane" id="tasks">
                    <div class="row" style="height:3rem;"></div>
                    <div class="row">
                        <div class="col-md-6" >
                            <div class="panel panel-default"  >
                                <div class="panel-heading" align="center" ><b>Master task list</b></div>
                                <div class="panel-body" style="height:60rem; overflow:auto">
                                    <span class='glyphicon glyphicon-ok' style='color:green'></span> : Complete&nbsp;&nbsp;
                                    <span class='glyphicon glyphicon-exclamation-sign' style='color:red'></span> : Pending
                                    <br><br>
                                    <table class="table table-hover table-responsive">
                                        <tr>
                                            <th>Name</th>
                                            <th>Points</th>
                                            <th>Due date</th>
                                            <th>Status</th>
                                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                        </tr>
                                        <%  
                                            /*This piece of java code connects to the database and then displays the friends of the
                                            user that is logged on on a separate modal*/

                                            //String user1 = (String)request.getAttribute("username");
                                            String selectTasks, getStatus;
                                            String connectionURL11="jdbc:derby://localhost:1527/WTFtask";
                                            selectTasks ="SELECT * FROM WTFtasks";
                                            try {
                                                Connection conn10 = DriverManager.getConnection(connectionURL11, "IS2560","IS2560");
                                                Statement selectTasksStatement = conn10.createStatement();
                                                Statement getStatusStatement = conn10.createStatement();
                                                ResultSet taskSet = selectTasksStatement.executeQuery(selectTasks);
                                                while(taskSet.next())
                                                {
                                                    getStatus = "SELECT * FROM WTFTASKALLOCATION WHERE TASKID="+taskSet.getInt("TASKID");
                                                    ResultSet statusSet = getStatusStatement.executeQuery(getStatus);
                                                    statusSet.next();
                                                    out.println("<tr>");
                                                    out.println("<td>"+taskSet.getString("TASKNAME")+"</td>");
                                                    out.println("<td>"+taskSet.getString("TASKPOINTS")+"</td>");
                                                    out.println("<td>"+taskSet.getString("DUEDATE")+"</td>");
                                                    if (statusSet.getString("STATUS").equalsIgnoreCase("Complete")) {
                                                        out.println("<td><span class='glyphicon glyphicon-ok' style='color:green'></span></td>");
                                                    }
                                                    else {
                                                        out.println("<td><span class='glyphicon glyphicon-exclamation-sign' style='color:red'></span></td>");
                                                    }
                                                    out.println("<td><button type='button' onclick='editTask(this)' style='border:none;background-color:white;color:black'><span class='glyphicon glyphicon-edit'></span></button>&nbsp;&nbsp;&nbsp;&nbsp;<button type='button' onclick='deleteTask(this)' style='border:none;background-color:white;color:black'><span class='glyphicon glyphicon-trash'></span></button></td>");
                                                    out.println("</tr>");
                                                    statusSet.close();
                                                } 
                                                taskSet.close();
                                                getStatusStatement.close();
                                                selectTasksStatement.close();
                                                conn10.close();
                                            }
                                            catch(SQLException e)
                                            {
                                                e.printStackTrace();
                                            }
                                        %>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="row" style="margin:1rem;">
                                <div class="panel panel-default">
                                    <div class="panel-heading" align="center"><b>Extra</b></div>
                                    <div class="panel-body" style="height:20rem; overflow:auto;padding:5rem;">

                                    </div>
                                </div>
                            </div>
                            <div class="row" style="margin:1rem;">
                                <div class="panel panel-default">
                                    <div class="panel-heading" align="center"><b>Extra</b></div>
                                    <div class="panel-body" style="height:20rem; overflow:auto;padding:5rem;">

                                    </div>
                                </div>
                            </div>
                        </div>	
                    </div>
                </div> <!-- End Tasks page -->

          </div> <!-- End Tab content -->

        </div> <!-- End Toggle menu -->
                         
                         
                         
                         
                         
                         
                         
    <!-- main container-->
  </div>
                                
  <div class="col-md-2 col-xs-0"></div>
  
  
  
  
  
    
    <!-- Modal for showing friends
    <div id="displayfriendmodal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg" style="border-radius:20px;">
            <div class="modal-content">
                <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><br><br>
                        <h3 class="modal-title" align="center">Your Friends</h3></br>
                        <%  
                            /*This piece of java code connects to the database and then displays the friends of the
                            user that is logged on on a separate modal

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
                            }*/
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
    
    
    <div id="chartdisplay" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="border-radius:20px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><br><br>
                    <form>
                    <input type="hidden" class="form-control input-md" name = "mainuser" id="mainuser" value="<%=request.getAttribute("username")%>">
                     </form>
                    <div id="chartContainer" style="height: 500px; width: 100%;"> </div> 
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
                        <br><br>
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="Points" name="taskpoints"/>
                        </div>
                        <br><br>
                        <div class="form-group">
                            <input type="hidden" class="form-control"  name="user" value = "<%=request.getAttribute("username")%>"/>
                        </div>
                        
                        <div class="form-group">
                            <input type="hidden" class="form-control" id="Name" name="Name" value = "<%=request.getAttribute("Name")%>"/>
                        </div>
                        
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

        $('#myTab a').click(function (e) {
		  e.preventDefault()
		  $(this).tab('show')
		})
        
	i = 0;
        //Here we simply obtain the current system date
	var date = new Date();
        date.setDate(date.getDate());
        
        function editTask(task) {
            alert("done");
        }
        
        function deleteTask(task) {
            
            alert("delete here");
        }
        
        //Here the datepicker is initialized with past dates disabled and hide on date select
	 $(function () {
                $("#duedate").datepicker({startDate: date});
                
                $('#duedate').on('changeDate', function(ev){
                    $(this).datepicker('hide');
                });
            });
            
          //This function displays a chart showing the progress of all your friends in terms of points.  
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
                              document.getElementById("add").disabled = true;
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
                        document.getElementById("add").disabled = true;
                   }
	}
        
        //This code removes the added friend in the add task modal
	function removeFriend(item) {
		$(item).remove();
                document.getElementById("add").disabled = false;
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
        
        $(document).ready(function() {
            $('.carousel').carousel('pause');
            // Bootstrap validator code for add task form.
            $('#addtaskForm').bootstrapValidator({
                    
                    
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
                        recur: {
                            validators: {
                                notEmpty: {
                                    message: 'Required'
                                },
                            }
                        },
                        duedate : {
                            validators: {
                                notEmpty: {
                                    message: 'Dats is required'
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




