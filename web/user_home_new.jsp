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
         try {
            Statement getTask = conn.createStatement();
            Statement updateTask = conn.createStatement();
            Statement pointsInfo = conn.createStatement();
            String getTasksQuery = "SELECT * FROM WTFtasks";
            ResultSet getTaskSet = getTask.executeQuery(getTasksQuery);
            ResultSet updateTaskSet, pointsInfoSet;
            int taskid;
            String taskpoints;
            while(getTaskSet.next()) {
                taskid = getTaskSet.getInt("TASKID");
                taskpoints = getTaskSet.getString("ALLOTEDTASKPOINTS");
                if(getTaskSet.getString("RECUR").equals("weekly") ||getTaskSet.getString("RECUR").equals("monthly") ) {
                    LocalDate task_date = formatter.parseLocalDate( getTaskSet.getString("DUEDATE"));
                    if (curr_date.isAfter(task_date)) {
                        String curr_task_date = task_date.toString();
                        String new_task_date = "";
                        if (getTaskSet.getString("RECUR").equals("weekly")) {
                            task_date = task_date.plusDays(7);
                            new_task_date = task_date.toString(); 
                        }
                        else if (getTaskSet.getString("RECUR").equals("monthly")) {
                            task_date = task_date.plusDays(30);
                            new_task_date = task_date.toString();
                        }
                        String updateTaskDate = "UPDATE IS2560.WTFtasks SET DUEDATE='"+ new_task_date +"' WHERE DUEDATE= '"+ curr_task_date +"'";
                        updateTask.executeUpdate(updateTaskDate);
                        ResultSet taskStatusSet = updateTask.executeQuery("SELECT * FROM WTFTASKALLOCATION WHERE TASKID = "+taskid);
                        taskStatusSet.next();
                        if(taskStatusSet.getString("STATUS").equalsIgnoreCase("Pending")) {
                            String getPointsInfo = "SELECT * FROM WTFuser where USERNAME IN (SELECT USERNAME FROM WTFTASKALLOCATION WHERE TASKID="+taskid+")";
                            pointsInfoSet = pointsInfo.executeQuery(getPointsInfo);
                            pointsInfoSet.next();
                            float user_points_possible = Float.parseFloat(pointsInfoSet.getString("POINTPOSSIBLE"));
                            float task_points = Float.parseFloat(taskpoints);
                            user_points_possible = user_points_possible - task_points;
                            pointsInfo.executeUpdate("UPDATE IS2560.WTFuser SET POINTPOSSIBLE = '"+user_points_possible+"' WHERE USERNAME IN (SELECT USERNAME FROM WTFTASKALLOCATION WHERE TASKID = "+taskid+")");
                        }
                        String updateTaskStatus = "UPDATE IS2560.WTFtaskallocation SET STATUS='Pending',USERNAME='null' WHERE TASKID="+taskid;
                        updateTask.executeUpdate(updateTaskStatus);
                    }
                }     
            }
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
        

                        
                    
                    
        
                         
                         
        
        <!-- Top bar -->                 
        <div class="row" style="height:8rem;background-color:#7f7f7f">
            <img src="img/logo_nav.gif" align="center" style="height:100%;padding-left:1rem;"></img>
            <div style="float:right;padding-top:3rem;padding-right:3rem;color:white">Signed in as <b id="identity"><%=request.getAttribute("Name")%></b></div>
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
                                                          String duedate=rs.getString("DUEDATE");
                                                          Date date1 = dateFormat.parse(duedate);
                                                          if(rs2.getString("STATUS").equalsIgnoreCase("Pending"))
                                                          {    
                                                          out.println("<tr>");
                                                          out.println("<td>"+rs.getString("TASKNAME")+"</td>");
                                                          out.println("<td>"+rs.getString("ALLOTEDTASKPOINTS")+"</td>");
                                                          out.println("<td>"+rs.getString("DUEDATE")+"</td>");
                                                          out.println("<td><button type='button' onclick='completeTask(this)' class='btn btn-success'>Wrap up</button></td>");
                                                          out.println("</tr>");
                                                          }

                                                      }
                                                      rs.close();
                                                  }

                                                  s.close();
                                                  s1.close();
                                                  s2.close();
                                                  
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
                            <div class="panel panel-default">
                                        <div class="panel-heading" align="center"><b>Overdue Tasks</b></div>
                                        <div class="panel-body" style="height:20rem; overflow:auto">
                                    <table class="table table-hover">
                                        <tr>
                                            <th>Name</th>
                                            <th>Points</th>
                                            <th>Due date</th>
                                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                        </tr>
                                        <%
                                            /*This block of java code displays the tasks that are now overdue*/
                                            
                                            System.out.println(user+" "+Name);
                                            sql3 ="SELECT TASKID,STATUS FROM WTFtaskallocation where USERNAME = '"+user+"'";
                                            try {

                                                Statement s = conn.createStatement();
                                                Statement s1 = conn.createStatement();
                                                Statement s2 = conn.createStatement();
                                                ResultSet rs2 = s2.executeQuery(sql3);
                                                while(rs2.next()){
                                                    
                                                    sql = "SELECT * FROM WTFtasks where TASKID ="+rs2.getInt("TASKID");
                                                    ResultSet rs = s.executeQuery(sql);
                                                    while (rs.next()) {
                                                          String duedate=rs.getString("DUEDATE");
                                                          Date date1 = dateFormat.parse(duedate);
                                                          if(rs2.getString("STATUS").equalsIgnoreCase("Pending")&&date1.before(date))
                                                          {    
                                                          out.println("<tr>");
                                                          out.println("<td>"+rs.getString("TASKNAME")+"</td>");
                                                          out.println("<td>"+rs.getString("ALLOTEDTASKPOINTS")+"</td>");
                                                          out.println("<td>"+rs.getString("DUEDATE")+"</td>");
                                                          out.println("<td><button type='button' onclick='completeTask(this)' class='btn btn-success'>Wrap up</button></td>");
                                                          out.println("</tr>");
                                                          }

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
                    </div>

                    <!-- Spacing row-->
                    <div class="row" style="height:1rem"></div> <!-- End spacing row-->

                    <!-- 2nd row -->
                    <div class="row">
                        <!-- Friends panel-->
                        <div class="col-md-6">
                            <div class="panel panel-default" >
                                <div class="panel-heading" align="center"><b>Friend list</b></div>
                                <div class="panel-body" style="height:20rem; overflow:auto">
                                    <table class="table table-hover">
                                        <tr>
                                     <%
                                        //This piece of code is used to extract the current system date
                                        Calendar cal1 = Calendar.getInstance();
                                        int week=cal1.get(Calendar.DAY_OF_WEEK_IN_MONTH);
                                        int year1 = cal1.get(Calendar.YEAR);
                                        int month1 = cal1.get(Calendar.MONTH)+1;
                                        int day1 = cal1.get(Calendar.DAY_OF_MONTH);
                                        String currdate1 = Integer.toString(year1)+"-"+Integer.toString(month1)+"-"+Integer.toString(day1);
                                        DateTimeFormatter formatter1 = DateTimeFormat.forPattern( "yyyy-MM-dd" );
                                        LocalDate curr_date1 = formatter1.parseLocalDate( currdate1 );
                                        String connectionURL1="jdbc:derby://localhost:1527/WTFtask";
                                        Connection conn11 = DriverManager.getConnection(connectionURL1, "IS2560","IS2560");
                                        String sql5;
                                        String weekupdated=null;
                                        sql5 ="SELECT * FROM WTFuser where USERNAME = '"+user+"'";
                                          
               
                                            try{
                                                
                                                Statement s3 = conn11.createStatement();
                                                Statement s4 = conn11.createStatement();
                                                ResultSet rs3 = s3.executeQuery(sql5);
                                                 while(rs3.next()){
                                                    weekupdated=rs3.getString("WEEKUPDATED");
                                                 }
                                                 
                                                 int weekupdatedInt=Integer.parseInt(weekupdated);
                                                 System.out.println("Week updated ="+weekupdatedInt+" week "+week);
                                                 System.out.println("byad");
                                                 if ((week >= 1 && weekupdatedInt >= 4))
                                                 {
                                                    System.out.println("yes");
                                                    String s5="UPDATE WTFuser SET WEEKUPDATED = '0' where USERNAME = '"+user+"'"; 
                                                    s3.executeUpdate(s5);
                                                    weekupdatedInt=0;
                                                 }
                                                 
                                                 if(weekupdatedInt<week)
                                                 {
                                                   out.println("<div id='updatePoints' class='modal show' data-backdrop='static'>");
                                                   out.println("<div class='modal-dialog'>");
                                                   out.println("<div class='modal-content'>");
                                                   out.println("<div class='modal-body'>");
                                                   out.println("<br><br><h4 align='center' class='modal-title'>First things first, you need to update your weekly points!</h4><br>");                                 
                                                   out.println("<form class='form-inline' align='center'><div class='form-group'><input type='hidden' name='weekupdated' id='week' value = '"+week+"'/><input class='form-control' style='width:33%;margin-left:33%' name='weeklypoint' id='weeklypoints' placeholder='weekly points'/><br><button style='width:20%;margin-left:40%' type ='button' id='weeklyupdate' href='#' class='form-control btn btn-primary'onclick='UpdatePoints()' align='right'>Update</button></div></form>");
                                                   out.println("</div></div>");
                                                     
                                                 }
                                                
                                            }
                                            catch (SQLException e) {
                                                  e.printStackTrace();
                                              }
                                              catch (Exception e) {
                                                  e.printStackTrace();
                                              }
                                       
                                            /*This piece of java code connects to the database and then displays the friends of the
                                            user that is logged on on a separate modal*/

                                            out.println("<tr><th>Name</th><th>Points alloted</th><th>Points completed</th></tr>");
                                            String selectFriends,selectUser,sql6;
                                            String connectionURL10="jdbc:derby://localhost:1527/WTFtask";
                                            selectFriends ="SELECT * FROM WTFFriends where MAINUSERNAME = '"+user+"'";
                                            try {
                                                Connection conn1 = DriverManager.getConnection(connectionURL10, "IS2560","IS2560");
                                                Statement selectFriendStatement = conn1.createStatement();
                                                Statement selectUserStatement = conn1.createStatement();
                                                
                                                    selectUser="SELECT * from WTFuser";
                                                    ResultSet userSet = selectUserStatement.executeQuery(selectUser);
                                                    while(userSet.next()) {
                                                        out.println("<tr>");
                                                        out.println("<td>"+userSet.getString("FIRSTNAME")+"</td>");
                                                        out.println("<td>"+userSet.getString("WEEKLYPOINTS")+"</td>");
                                                        out.println("<td>"+userSet.getString("WEEKLYPOINTSDONE")+"</td>");
                                                        out.println("</tr>");
                                                    }
                                                    userSet.close();
                                                selectUserStatement.close();
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
                                            percentage = (int)((completedTasks*100)/totalTasks);
                                            
                                        }
                                        completeSet.close();
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
                                    <p>Tasks completed : <%= completedTasks %></p>
                                    <p>Tasks remaining : <%= (totalTasks - completedTasks) %></p>
                                </div>
                            </div>
                                
                        </div> <!-- End Extra Panel-->
                    </div> <!-- End 2nd row-->
                    
                    <!-- Spacing row-->
                    <div class="row" style="height:1rem"></div> <!-- End spacing row-->
                     
                </div> <!-- End Home page -->

                <!--Tasks page -->
                <div role="tabpanel" class="tab-pane" id="tasks">
                    <div class="row" style="height:3rem;"></div>
                    <div class="row">
                        <div class="col-md-12" >
                            <div class="panel panel-default"  >
                                <div class="panel-heading" align="center" ><b>Master task list</b></div>
                                <div class="panel-body" style="height:50rem; overflow:auto">
                                    <span class='glyphicon glyphicon-ok' style='color:green'></span> : Complete&nbsp;&nbsp;
                                    <span class='glyphicon glyphicon-exclamation-sign' style='color:red'></span> : Pending&nbsp;&nbsp;
                                    <span class='glyphicon glyphicon-star'></span> : Overdue
                                    <br><br>
                                    <table class="table table-hover table-responsive">
                                        <tr>
                                            <th>Name</th>
                                            <th>Points</th>
                                            <th>Due date</th>
                                            <th>Status</th>
                                            <th>Assignee</th>
                                            <th>Reuse</th>
                                            <th>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                        </tr>
                                        <%  
                                            /*This piece of code displays the master task list*/

                                            String selectTasks, getStatus;
                                            String connectionURL11="jdbc:derby://localhost:1527/WTFtask";
                                            selectTasks ="SELECT * FROM WTFtasks";
                                            int id = 0;
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
                                                    String duedate=taskSet.getString("DUEDATE");
                                                    Date date1 = dateFormat.parse(duedate);
                                                    out.println("<tr value='table row'>");
                                                    out.println("<td>"+taskSet.getString("TASKNAME")+"</td>");
                                                    out.println("<td>"+taskSet.getString("TASKPOINTS")+"</td>");
                                                    out.println("<td>"+taskSet.getString("DUEDATE")+"</td>");
                                                    if (statusSet.getString("STATUS").equalsIgnoreCase("Complete")) {
                                                        out.println("<td><span class='glyphicon glyphicon-ok' style='color:green'></span></td>"); 
                                                    }
                                                    else {
                                                        out.println("<td><span class='glyphicon glyphicon-exclamation-sign' style='color:red'></span></td>");  
                                                    }
                                                    if (statusSet.getString("username").equalsIgnoreCase("null")) {
                                                        out.println("<td><button onclick='assign(this)' class='btn btn-success'>Assign to me</button></td>");
                                                    }
                                                    else {
                                                        out.println("<td>"+statusSet.getString("username")+"</td>");
                                                    }
                                                    if (taskSet.getString("recur").equalsIgnoreCase("none")) {
                                                        if (statusSet.getString("STATUS").equalsIgnoreCase("Complete")){
                                                            out.println("<td><a data-toggle='modal' data-id='"+taskSet.getString("TASKID")+"' title='Add this item' class='open-AddBookDialog btn btn-success' href='#reusetask'>Reuse Task</a></td>");
                                                        }
                                                        else {
                                                            out.println("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
                                                        }
                                                    }
                                                    else {
                                                        out.println("<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>");
                                                    }
                                                    if(date1.before(date))
                                                    {
                                                        out.println("<td><button type='button' id='e"+id+"' onclick='editTask(this)' style='border:none;background-color:white;color:black'><span class='glyphicon glyphicon-edit'></span></button>&nbsp;&nbsp;&nbsp;&nbsp;<button type='button' id='d"+id+"' value='scamy' onclick='deleteTask(this)' style='border:none;background-color:white;color:black'><span class='glyphicon glyphicon-trash'></span></button>&nbsp;&nbsp;&nbsp;&nbsp;<span class='glyphicon glyphicon-star'></span></td>");
                                                    }
                                                    else
                                                    {
                                                        out.println("<td><button type='button' id='e"+id+"' onclick='editTask(this)' style='border:none;background-color:white;color:black'><span class='glyphicon glyphicon-edit'></span></button>&nbsp;&nbsp;&nbsp;&nbsp;<button type='button' id='d"+id+"' value='scamy' onclick='deleteTask(this)' style='border:none;background-color:white;color:black'><span class='glyphicon glyphicon-trash'></span></button></td>");
                                                    }
                                                    out.println("</tr>");
                                                    id++;
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
                        	
                    </div>
                </div> <!-- End Tasks page -->

          </div> <!-- End Tab content -->

        </div> <!-- End Toggle menu -->
                         
                         
                         
                         
                         
                         
                         
    <!-- main container-->
  </div>
                                
  <div class="col-md-2 col-xs-0"></div>
  
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
                            <input type="hidden" class="form-control input-md" name = "mainuser_firstname" id="mainuser_firstname" value="<%=request.getAttribute("Name")%>">
                            <input type="hidden" class="form-control input-md" name = "searched_username" id="searched_username" ><br>
                            <button class="btn btn-success"  type="disable" id="addfriend" disabled >Add</button>
                        </div><br>

                        <div id="searchUpdate" style="color:red;"></div><br>
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
    
    <!-- Change chart display modal-->
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
    </div><!-- End chart display modal -->
    
    <!-- Change due date modal -->               
    <div id="reusetask" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog" style="border-radius:20px;">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button><br><br>
                    <div class="modal-body">
                        <input type="hidden" name="bookId" id="bookId" value=""/>
                        <input type="hidden" class="form-control input-md" name = "mainuser" id="mainuser1" value="<%=request.getAttribute("username")%>">
                        <h3 align="center">Enter the new due date</h3><br>
                        <div class="hero-unit">
                            <div class="input-group date" id="example1" style="width:33%;margin-left:33%" >
                                <input  type="text" class="form-control"  placeholder="Due date"  id="example1"> <br>
                                <span class="input-group-addon">
                                    <span class="glyphicon glyphicon-calendar"></span>
                                </span>
                            </div> <br>
                        </div>
                        <div class="form-group">
                            <button type="submit" class="btn btn-primary" id="reuseupdated" style="width:20%;margin-left:40%" onclick="reusetask1()">Reuse</button>
                        </div>
                    </div>
                     
                 </div>
            </div>
        </div>
    </div><!-- End Change due date modal -->
                               
    <!-- modal for adding new tasks-->
    <div id="addtaskmodal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel1" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <br><br>
                    <h3 class="modal-title" align="center">Add a task</h3>
                    <br>
                    <form class="form-inline" align="center">
                        <div class="form-group">
                            <input type="text" class="form-control" id="tname" name="taskname" Placeholder="Task name" /> 
                            <br>
                        </div>
                        <br><br>
                        <div class="form-group">
                            <input type="text" class="form-control" id="tpoints" placeholder="Points" name="taskpoints"/>
                        </div>
                        <br><br>
                        <div class="form-group">
                            <div class="radio">
                                <label>
                                    <b>Due:</b>
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="due" id="date" onclick="switchIT(this)">
                                     Date
                                     <div class="input-group date" id="duedate" >
                                        <input type="text" class="form-control" id="due-date" name="duedate" Placeholder="Due date" />
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
                                    </div>  
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="due" id="day" onclick="switchIT(this)">
                                    Day
                                    <input type="number" class="form-control" id="duedays" name="duedays" min="0" Placeholder="Due days" />
                                </label>
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
                        <br>
                        <br>
                        <div class="form-group">
                            <div id="errorMessageContainer" style="color:red"></div><br>
                            <button type="button" class="btn btn-primary" onclick="addTask()">Add task</button><br><br>
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

        //This code toggles between the two tabs namely "home" and "tasks" on the homepage      
        $('#myTab a').click(function (e) {
              e.preventDefault();
              $(this).tab('show');
        })
        
        //Here we set the two duedate options as hidden initially
        $('#duedate').hide();
        $('#duedays').hide();
        
        // Here we add the created task to the database
        function addTask() {
            var flag = 0;
            var taskname = $("#tname").val();
            var taskpoints = $("#tpoints").val();
            var due_date = $("#due-date").val();
            var duedays = $("#duedays").val();
            var username = '<%=user%>';
            var name = '<%=Name%>';
            var recur;
            if(document.getElementById("weekly").checked) {
                recur = 'weekly';
            }
            else if(document.getElementById("monthly").checked) {
                recur = 'monthly';
            }
            else if(document.getElementById("none").checked) {
                recur = 'none';
            }
            else
                flag = 1;
            if(taskname === "" || taskpoints === "") {
                $("#errorMessageContainer").text("incomplete taskname or taskpoints");
            }
            else {
                if(due_date === "" && duedays === "") {
                    $("#errorMessageContainer").text("incomplete duedate and duedays");
                }
                else
                    if(flag == 0) {
                        $("#errorMessageContainer").text("");
                        if (due_date === "") {
                            var someDate = new Date();
                            var millisecondOffset = duedays * 24 * 60 * 60 * 1000;
                            someDate.setTime(someDate.getTime() + millisecondOffset); 
                            var dd = someDate.getDate();
                            var mm = someDate.getMonth() + 1;
                            var y = someDate.getFullYear();
                            due_date = y + '-'+ mm + '-'+ dd;
                            alert(due_date);
                        }
                        $.get('Add_Task',"&Name="+name+"&user="+username+"&taskname="+taskname+"&taskpoints="+taskpoints+"&duedate="+due_date+"&recur="+recur,function(ResponseText){ 
                            if(ResponseText==="true")
                                alert("Added task "+taskname+" "+taskpoints+" "+due_date+" "+recur+" "+username+" "+name); 
                        })
                        location.reload();
                    }
                    else
                        $("#errorMessageContainer").text("recur value missing");
            }   
        }
        
        
        function switchIT(selection){
            var scam = $(selection).attr('id');
            if (scam === 'date') {
                $('#duedate').show();
                $('#duedays').val("");
                $('#duedays').hide(); 
            }
            else if (scam === 'day') {
                $('#duedays').show();
                $('#duedate').children(":first").val("");
                $('#duedate').hide();
            }
        }
        
        //Here
        $(document).on("click", ".open-AddBookDialog", function () {
            var myBookId = $(this).data('id');
            $(".modal-body #bookId").val( myBookId );
            $('#example1').datepicker({
                    format: "dd/mm/yyyy",
                    startDate:'+0D'
                    
            });
            $('#example1').on('changeDate', function(ev){
                    $(this).datepicker('hide');
                });
            
        });
    
        //Here
        function UpdatePoints(){
            var points=$("#weeklypoints").val();
            var weekupdated=$("#week").val();
            var mainuser=$(".modal-body #mainuser1").val();
            $.get('WeeklyPointsUpdate',"&week="+weekupdated+"&mainuser="+mainuser+"&points="+points,function(ResponseText){ 
               
            })
            location.reload();
        }
        
        //Here
        function reusetask1(){
            var id=$(".modal-body #bookId").val();
            console.log(id);
            var mainuser=$(".modal-body #mainuser1").val();
            var duedate=$(".hero-unit #example1").children(":first").val();
            alert(duedate);
          
        };
        
        //Here the selected task is deleted from the database
        function deleteTask(task) {
            var id, name, points, duedate;
            id = $(task).attr('id');
            name = $(task).parent().parent().children(":eq(0)").text();
            points = $(task).parent().parent().children(":eq(1)").text();
            duedate = $(task).parent().parent().children(":eq(2)").text();
            $.get('deleteTask','&taskName='+name+"&taskPoints="+points+'&taskDueDate='+duedate,function(ResponseText) {
                if (ResponseText == "true") {
                    $(task).parent().parent().remove();
                }
            });
        }
        //This function serves as a jQuery call to the Complete_Task servlet
        function completeTask(task)
        {
            var username, name, points, duedate;
            name = $(task).parent().parent().children(":eq(0)").text();
            points = $(task).parent().parent().children(":eq(1)").text();
            duedate = $(task).parent().parent().children(":eq(2)").text();
            username = '<%=user%>';
            $.get('Complete_Task','&taskName='+name+"&taskPoints="+points+'&taskDueDate='+duedate+'&username='+username,function(ResponseText) {
                if (ResponseText == "true") {
                    location.reload();
                }
                else {
                    alert("diff");
                }
            });
        }    
        
        //Here the user is assigned to the selected task
        function assign(assignButton) { 
            var username, name, points, duedate;
            name = $(assignButton).parent().parent().children(":eq(0)").text();
            points = $(assignButton).parent().parent().children(":eq(1)").text();
            duedate = $(assignButton).parent().parent().children(":eq(2)").text();
            username = '<%=user%>';
            $.get('AssignTask','&taskName='+name+"&taskPoints="+points+'&taskDueDate='+duedate+'&username='+username,function(ResponseText) {
                if (ResponseText == "true") {
                    $(assignButton).parent().text(username);
                    location.reload();
                }
                else {
                    alert("diff");
                }
            });   
        }
        
        //Here the datepicker is initialized with past dates disabled and hide on date select
	 $(function () {
                $("#duedate").datepicker({startDate:'+0D'});
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
                   j++;
               }
               var k=0;
                for (i=0;i<list.length;i++)
               {
                   datapointsgraph2[k]={
                       
                       label:list1[i],y:Number(list[i]-list2[i])
                       
                   }
                   k++;
               }
              var chart = new CanvasJS.Chart("chartContainer", {
                                       
                                title:{
                                text:"The performance so far..."   
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
                                  color:"#04B404",
                                  dataPoints: datapointsgraph
                                },  {        
                                  type: "stackedColumn",
                                  toolTipContent: "{label}<br/><span style='\"'color: {color};'\"'><strong>{name}</strong></span>: {y}",
                                  name: "Points Remaining",
                                  showInLegend: "true",
                                  color:"#DF013A",
                                  dataPoints: datapointsgraph2
                                }            
                                ]
                                
                                
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
	
                
        //This code removes the added friend in the add task modal
	function removeFriend(item) {
		$(item).remove();
                document.getElementById("add").disabled = false;
	}	
        
        //Show/hide and forms and reset values on close.
	$("#inviteForm").hide();
        
        $("#showaddtaskmodal").click(function() {	
                document.forms["addtaskForm"].reset();
                $("#errorMessageContainer").text("");
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




