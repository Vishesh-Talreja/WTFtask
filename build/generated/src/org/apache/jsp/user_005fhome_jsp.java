package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;
import java.util.Calendar.*;
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.Cookie;
import org.joda.time.format.*;
import org.joda.time.LocalDate;

public final class user_005fhome_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("   \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Frameset//EN\" \"http://www.w3.org/TR/html4/frameset.dtd\">\n");
      out.write("<html lang=\"en\">\n");
      out.write("    \n");
      out.write("  <head>\n");
      out.write("    <meta charset=\"utf-8\">\n");
      out.write("    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("    <title>Crib</title>\n");
      out.write("    <!-- Bootstrap -->\n");
      out.write("    <link href=\"css/bootstrap.min.css\" rel=\"stylesheet\">\n");
      out.write("    <link rel=\"stylesheet\" href=\"http://bsdp-assets.blackcherry.us/1.3.0/datepicker.min.css\">\n");
      out.write("    <!-- BootstrapValidator CSS -->\n");
      out.write("    <link rel=\"stylesheet\" href=\"dist/css/bootstrapValidator.min.css\"/>\n");
      out.write("    <script type=\"text/javascript\" src=\"js/canvasjs.min.js\"></script>\n");
      out.write("    \n");
      out.write("\t<style type=\"text/css\">\n");
      out.write("\t\t\n");
      out.write("\t\t.datepicker{z-index:1151 !important;}\n");
      out.write("\t\t\n");
      out.write("\t\t.btn-primary  {\n");
      out.write("\t\t\tbackground-color:#336699;\n");
      out.write("\t\t\tborder-color:#336699;\n");
      out.write("\t\t}\n");
      out.write("\t\t\n");
      out.write("                \t\n");
      out.write("\t\tbody {\n");
      out.write("\t\t  background: -webkit-gradient(#888888,white); /* Chrome,Safari4+ */\n");
      out.write("\t\t  background: -webkit-linear-gradient(#888888,white); /* Chrome10+,Safari5.1+ */\n");
      out.write("\t\t background-repeat: no-repeat;\n");
      out.write("\t\t}\n");
      out.write("                \n");
      out.write("\t\t#myCarousel {height:30vh;}\n");
      out.write("                .item {height:25vh;}\n");
      out.write("                html {height:100%}\n");
      out.write("\n");
      out.write("                ::-webkit-scrollbar { \n");
      out.write("                            display: none; \n");
      out.write("                }\n");
      out.write("                \n");
      out.write("                .carousel-inner .active.left { left: -50%; }\n");
      out.write("\t\t.carousel-inner .next        { left:  50%; }\n");
      out.write("\t\t.carousel-inner .prev        { left:  -50%; }\n");
      out.write("\t\t.carousel-control.left,.carousel-control.right {background-image:none;}\n");
      out.write("\n");
      out.write("\t\t.col-lg-2 {width: 50%;}\n");
      out.write("                \n");
      out.write("                .event {\n");
      out.write("                  width: 300px;\n");
      out.write("                  height: 80px;\n");
      out.write("                  background: #fff;\n");
      out.write("                  border: 1px solid #CCC;\n");
      out.write("                  border-radius: 2px;\n");
      out.write("                  margin: 50px;\n");
      out.write("                }\n");
      out.write("                .event:before {\n");
      out.write("                  content: '';\n");
      out.write("                  display: block;\n");
      out.write("                  width: 295px;\n");
      out.write("                  height: 70px;\n");
      out.write("                  background: #fff;\n");
      out.write("                  border: 1px solid #CCC;\n");
      out.write("                  border-radius: 2px; \n");
      out.write("                  transform: rotate(2deg);\n");
      out.write("                  position: relative;\n");
      out.write("                  top: 12px;\n");
      out.write("                  left: 2px;\n");
      out.write("                  z-index: -1;\n");
      out.write("                }\n");
      out.write("                .event:after {\n");
      out.write("                  content: '';\n");
      out.write("                  display: block;\n");
      out.write("                  width: 295px;\n");
      out.write("                  height: 75px;\n");
      out.write("                  background: #fff;\n");
      out.write("                  border: 1px solid #CCC;\n");
      out.write("                  border-radius: 2px; \n");
      out.write("                  transform: rotate(-2deg);\n");
      out.write("                  position: relative;\n");
      out.write("                  top: -136px;\n");
      out.write("                  z-index: -2;  \n");
      out.write("                }\n");
      out.write("                .event > span {\n");
      out.write("                  display: block;\n");
      out.write("                  width: 30px;\n");
      out.write("                  background: #232323;  \n");
      out.write("                  position: relative;\n");
      out.write("                  top: -55px;\n");
      out.write("                  left: -15px;\n");
      out.write("\n");
      out.write("                  /* Text */\n");
      out.write("                  color: #fff;\n");
      out.write("                  font-size: 10px;\n");
      out.write("                  padding: 2px 7px;\n");
      out.write("                  text-align: right;\n");
      out.write("                }\n");
      out.write("                .event > .info {\n");
      out.write("                  display: inline-block;\n");
      out.write("                  position: relative;\n");
      out.write("                  top: -75px;\n");
      out.write("                  left: 40px;\n");
      out.write("\n");
      out.write("                  /* Text */\n");
      out.write("                  color: #232323;\n");
      out.write("                  font-weight: 600;\n");
      out.write("                  line-height: 25px;\n");
      out.write("                }\n");
      out.write("                .event > .info:first-line {\n");
      out.write("                  text-transform: uppercase;\n");
      out.write("                  font-size: 10px;\n");
      out.write("                  margin: 10px 0 0 0;\n");
      out.write("                  font-weight: 700;\n");
      out.write("                }\n");
      out.write("                .event > .price {\n");
      out.write("                  display: inline-block;\n");
      out.write("                  width: 60px;\n");
      out.write("                  position: relative;\n");
      out.write("                  top: -85px;\n");
      out.write("                  left: 115px; \n");
      out.write("\n");
      out.write("                  /* Text */\n");
      out.write("                  color: #E35354;\n");
      out.write("                  text-align: center;\n");
      out.write("                  font-weight: 700;\n");
      out.write("                }\n");
      out.write("\n");
      out.write("\t</style>\t\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("      \n");
      out.write("      \n");
      out.write("      ");

        String userName = null;
        Cookie[] cookies = request.getCookies();
        if(cookies !=null){
        for(Cookie cookie : cookies){
            if(cookie.getName().equals("user")) userName = cookie.getValue();
        }
        }
        if(userName == null) response.sendRedirect("task_login.jsp");
      
      out.write("\n");
      out.write("      \n");
      out.write("      ");

         Calendar cal = Calendar.getInstance(); 
         int year = cal.get(Calendar.YEAR);
         int month = cal.get(Calendar.MONTH)+1;
         int day = cal.get(Calendar.DAY_OF_MONTH);
         String currdate = Integer.toString(year)+"-"+Integer.toString(month)+"-"+Integer.toString(day);
         DateTimeFormatter formatter = DateTimeFormat.forPattern( "yyyy-MM-dd" );
         LocalDate curr_date = formatter.parseLocalDate( currdate );
    
      
      out.write("\n");
      out.write("      \n");
      out.write("      ");

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
         
      
      out.write("\n");
      out.write("      \n");
      out.write("  <div class=\"col-md-2\"></div>\n");
      out.write("  <div class=\"col-md-8 col-xs-12\" style='padding: 0'>\n");
      out.write("    <!-- main container for page content-->\n");
      out.write("    <div style =\"height:100vh;background-color: white;padding: 2em\">\n");
      out.write("        <!-- top navbar-->\n");
      out.write("        <nav class=\"navbar navbar-default\" role=\"navigation\" style=\"border:hidden ;background-color:#7F7F7F;width: 100%;margin: -1\">\n");
      out.write("          <div class=\"container-fluid\">\n");
      out.write("            <!-- Brand and toggle get grouped for better mobile display -->\n");
      out.write("            <div class=\"navbar-header\">\n");
      out.write("              <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">\n");
      out.write("                <span class=\"sr-only\">Toggle navigation</span>\n");
      out.write("                <span class=\"icon-bar\" style=\"background-color:white;\"></span>\n");
      out.write("                <span class=\"icon-bar\" style=\"background-color:white;\"></span>\n");
      out.write("                <span class=\"icon-bar\" style=\"background-color:white;\"></span>\n");
      out.write("              </button>\n");
      out.write("\n");
      out.write("            <div class=\"navbar-brand\" style=\"padding-top:0px;\"><img  src=\"img/logo_nav.jpg\" style=\"height:140%;width50%;\"/></div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("            <!-- Collect the nav links, forms, and other content for toggling -->\n");
      out.write("            <div class=\"collapse navbar-collapse\" id=\"bs-example-navbar-collapse-1\">\n");
      out.write("                <ul class=\"nav navbar-nav navbar-right\" >\n");
      out.write("                    <li id=\"chart\"> <a id=\"chartdisplaymodal\" href=\"#chartdisplay\" class=\"btn-group-sm\" data-toggle=\"modal\"  style=\"color:white\" onclick=\"chartdisplay()\">Chart</a></li>\n");
      out.write("                    <li id=\"group\"><a id=\"showdisplayfriendmodal\" href=\"#displayfriendmodal\" class=\"btn-group-sm\" data-toggle=\"modal\"  style=\"color:white\">Friends</a></li>\n");
      out.write("                    <li id=\"group\"><a id=\"showaddtaskmodal\" href=\"#addtaskmodal\" class=\"btn-group-sm\" data-toggle=\"modal\"  style=\"color:white\">Add a Task</a></li>\n");
      out.write("                    <li id=\"friend\"><a id=\"showaddfriendmodal\" href=\"#addfriendmodal\" class=\"btn-group-sm\" data-toggle=\"modal\" style=\"color:white\">Add a Friend</a></li>\n");
      out.write("                    <li ><a href=\"task_login.jsp\" onclick=\"logout()\" class=\"btn-group-sm\" style=\"color:white;border:none;background-color:#7F7F7F\">Log Out</a></li>\n");
      out.write("                </ul> \n");
      out.write("            </div><!-- /.navbar-collapse -->\n");
      out.write("          </div><!-- /.container-fluid -->\n");
      out.write("        </nav><!-- end navbar-->\n");
      out.write("        \n");
      out.write("        <!-- Welcome message-->\n");
      out.write("        <h1 style=\"font-face:papyrus\">Welcome ");
      out.print(request.getAttribute("Name"));
      out.write(" </h1><br>\n");
      out.write("\n");
      out.write("        ");

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
        
      out.write("\n");
      out.write("        \n");
      out.write("        <!-- View Tasks carousel-->\n");
      out.write("        <div class=\"row\">\n");
      out.write("        <div class=\"col-md-8\">\n");
      out.write("            <div class=\"col-md-4 col-md-offset-4 text-center\"><h4><a  href=\"#myCarousel\" data-slide=\"prev\"><i class=\"glyphicon glyphicon-chevron-left\"></i></a>&nbsp;Your Tasks&nbsp;<a  href=\"#myCarousel\" data-slide=\"next\"><i class=\"glyphicon glyphicon-chevron-right\"></i></a></h4></div>\n");
      out.write("            <div class=\"col-md-12 col-xs-12\">\n");
      out.write("                <div class=\"carousel slide\" id=\"myCarousel\">\n");
      out.write("                    <div class=\"carousel-inner\">\n");
      out.write("                        ");

                        /*This block of java code displays the tasks the user has to complete, here it 
                          first connects to the database and then displays them in the form of thumbnails*/
                        String user = (String)request.getAttribute("username");
                        String sql,sql3;
                        
                        sql3 ="SELECT TASKID FROM WTFtaskallocation where USERNAME = '"+user+"'";
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
                                      String date[] = rs.getString("DUEDATE").split("-");
                                      task_year = Integer.parseInt(date[0]);
                                      task_month = Integer.parseInt(date[1]);
                                      task_day = Integer.parseInt(date[2]);
                                      if (year == task_year && month == task_month && day == task_day) {
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
                                        //#F5A9A9//#A9F5A9
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
                          
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>  \n");
      out.write("        </div>\n");
      out.write("           <div class=\"col-md-4\">\n");
      out.write("               <canvas id=\"myCanvas\"></canvas>\n");
      out.write("                <script>\n");
      out.write("                  var canvas = document.getElementById('myCanvas');\n");
      out.write("                  var context = canvas.getContext('2d');\n");
      out.write("\n");
      out.write("                  context.beginPath();\n");
      out.write("                  context.rect(0, 40, 50, 25);\n");
      out.write("                  context.fillStyle = '#A9F5A9';\n");
      out.write("                  context.fill();\n");
      out.write("                  context.fillStyle = 'black';\n");
      out.write("                  context.font = '16pt Calibri';\n");
      out.write("                  context.fillText('Completed Tasks', 60, 60);\n");
      out.write("                  \n");
      out.write("                  context.beginPath();\n");
      out.write("                  context.rect(0, 80, 50, 25);\n");
      out.write("                  context.fillStyle = '#F5A9A9';\n");
      out.write("                  context.fill();\n");
      out.write("                  context.fillStyle = 'black';\n");
      out.write("                  context.font = '16pt Calibri';\n");
      out.write("                  context.fillText('Past Due Date', 60, 100);\n");
      out.write("                </script>\n");
      out.write("               \n");
      out.write("           </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- End View tasks carousel-->\n");
      out.write("        \n");
      out.write("        <!-- View owned tasks carousel-->\n");
      out.write("        <div class=\"row\">\n");
      out.write("        <div class=\"col-md-8\">\n");
      out.write("            <div class=\"col-md-6 col-md-offset-3 text-center\"><h4><a  href=\"#myCarousel1\" data-slide=\"prev\"><i class=\"glyphicon glyphicon-chevron-left\"></i></a>&nbsp;Tasks You Own&nbsp;<a  href=\"#myCarousel1\" data-slide=\"next\"><i class=\"glyphicon glyphicon-chevron-right\"></i></a></h4></div>\n");
      out.write("            <div class=\"col-md-12 col-xs-12\">\n");
      out.write("                <div class=\"carousel slide\" id=\"myCarousel1\">\n");
      out.write("                    <div class=\"carousel-inner\">\n");
      out.write("                        ");
  
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

                          
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("            </div>  \n");
      out.write("        </div>\n");
      out.write("                    <div class=\"col-md-4\" style=\"padding-top:40px\">\n");
      out.write("                        Your progress:\n");
      out.write("                        <div class=\"progress\">\n");
      out.write("                        <div class=\"progress-bar progress-bar-striped active\"  role=\"progressbar\" aria-valuenow=\"60\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 60%\">\n");
      out.write("                         60%<span class=\"sr-only\">45% Complete</span>\n");
      out.write("                        </div>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- End owned tasks carousel-->\n");
      out.write("    </div>\n");
      out.write("    <!-- main container-->\n");
      out.write("  </div>\n");
      out.write("  <div class=\"col-md-2\"></div>\n");
      out.write("    \n");
      out.write("    <!-- Modal for showing friends-->\n");
      out.write("    <div id=\"displayfriendmodal\" class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n");
      out.write("        <div class=\"modal-dialog modal-lg\" style=\"border-radius:20px;\">\n");
      out.write("            <div class=\"modal-content\">\n");
      out.write("                <div class=\"modal-header\">\n");
      out.write("                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button><br><br>\n");
      out.write("                        <h3 class=\"modal-title\" align=\"center\">Your Friends</h3></br>\n");
      out.write("                        ");
  
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



                        
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <!-- End show friends modal-->\n");
      out.write("\n");
      out.write("    <!-- Modal for adding and inviting new friends-->\n");
      out.write("    <div id=\"addfriendmodal\" class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n");
      out.write("        <div class=\"modal-dialog\" style=\"border-radius:20px;\">\n");
      out.write("            <div class=\"modal-content\">\n");
      out.write("                <div class=\"modal-header\">\n");
      out.write("                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button><br><br>\n");
      out.write("                    <h3 class=\"modal-title\" align=\"center\">Add a Friend</h3></br>\n");
      out.write("                    <form id =\"searchForm\" class=\"form-inline\" align=\"center\" method=\"get\" action=\"New_friend\">\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <div class=\"col-md-10 col-xs-10\">\n");
      out.write("                                <input type=\"text\" name=\"searchname\" id=\"searchname\" class=\"form-control input-md\" Placeholder=\"Search...\" required>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"col-md-1 col-xs-1\" style=\"padding-top:2px;\">\n");
      out.write("                                <button id=\"SearchButton\" type=\"button\" onclick=\"Search()\"><span class=\"glyphicon glyphicon-search\"></span> </button> \n");
      out.write("                            </div></br></br>\n");
      out.write("                            <input type=\"hidden\" class=\"form-control input-md\" name = \"mainuser\" id=\"mainuser\" value=\"");
      out.print(request.getAttribute("username"));
      out.write("\">\n");
      out.write("                            <input type=\"hidden\" class=\"form-control input-md\" name = \"mainuser_firstname\" id=\"mainuser\" value=\"");
      out.print(request.getAttribute("Name"));
      out.write("\">\n");
      out.write("                            <input type=\"hidden\" class=\"form-control input-md\" name = \"searched_username\" id=\"searched_username\" >\n");
      out.write("                            <button class=\"btn btn-success\"  type=\"disable\" id=\"addfriend\" disabled >Add</button>\n");
      out.write("                        </div><br>\n");
      out.write("\n");
      out.write("                        <div id=\"searchUpdate\" style=\"color:red;\"></div>\n");
      out.write("                        <a align=\"center\" id=\"Invite\" href=\"#\" >Can't find your friend? Invite them!</a>  \n");
      out.write("                    </form>\n");
      out.write("                    <form id=\"inviteForm\" class=\"form-inline\" align=\"center\" method=\"get\" action = \"Add_Friend\" >\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <input type=\"text\" class=\"form-control\" name=\"firstname\" Placeholder=\"First name\" />\n");
      out.write("                        </div>\n");
      out.write("                        <div id=\"break\">\n");
      out.write("                            <br>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <input type=\"text\" class=\"form-control\" name=\"lastname\" Placeholder=\"Last name\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div id=\"break\">\n");
      out.write("                            <br>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <input type=\"text\" class=\"form-control\" name=\"email\" Placeholder=\"Email address\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <input type=\"hidden\" class=\"form-control input-md\" name = \"user\" value=\"");
      out.print(request.getAttribute("Name"));
      out.write("\">\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <input type=\"hidden\" class=\"form-control input-md\" name = \"username\" value=\"");
      out.print(request.getAttribute("username"));
      out.write("\">\n");
      out.write("                        </div>\n");
      out.write("                        <div id=\"break\">\n");
      out.write("                            <br>\n");
      out.write("                        </div>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <button type =\"submit\" id=\"login\" href=\"#\" class=\"btn btn-primary\" align=\"center\">Invite</button>\n");
      out.write("                        </div><br>\n");
      out.write("                    </form>\t\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <!-- End add friend modal-->                    \n");
      out.write("    <div id=\"chartdisplay\" class=\"modal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel\" aria-hidden=\"true\">\n");
      out.write("        <div class=\"modal-dialog\" style=\"border-radius:20px;\">\n");
      out.write("            <div class=\"modal-content\">\n");
      out.write("                <div class=\"modal-header\">\n");
      out.write("                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button><br><br>\n");
      out.write("                    <form>\n");
      out.write("                    <input type=\"hidden\" class=\"form-control input-md\" name = \"mainuser\" id=\"mainuser\" value=\"");
      out.print(request.getAttribute("username"));
      out.write("\">\n");
      out.write("                     </form>\n");
      out.write("                    <div id=\"chartContainer\" style=\"height: 500px; width: 100%;\">  </div> \n");
      out.write("                 </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <!-- modal for adding new tasks-->\n");
      out.write("    <div id=\"addtaskmodal\" class=\"modal fade\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"myModalLabel1\" aria-hidden=\"true\">\n");
      out.write("        <div class=\"modal-dialog\">\n");
      out.write("            <div class=\"modal-content\">\n");
      out.write("                <div class=\"modal-header\">\n");
      out.write("                    <button type=\"button\" class=\"close\" data-dismiss=\"modal\"><span aria-hidden=\"true\">&times;</span><span class=\"sr-only\">Close</span></button>\n");
      out.write("                    <br><br>\n");
      out.write("                    <h3 class=\"modal-title\" align=\"center\">Add a task</h3>\n");
      out.write("                    <br>\n");
      out.write("                    <form id=\"addtaskForm\" class=\"form-inline\" align=\"center\" method=\"get\" action=\"Add_Task\">\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <input type=\"text\" class=\"form-control\" name=\"taskname\" Placeholder=\"Task name\" /> \n");
      out.write("                            <br>\n");
      out.write("                        </div>\n");
      out.write("                        <br>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <input type=\"text\" class=\"form-control\" placeholder=\"Points\" name=\"taskpoints\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <br><br>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <input type=\"hidden\" class=\"form-control\"  name=\"user\" value = \"");
      out.print(request.getAttribute("username"));
      out.write("\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <br>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <input type=\"hidden\" class=\"form-control\" id=\"Name\" name=\"Name\" value = \"");
      out.print(request.getAttribute("Name"));
      out.write("\"/>\n");
      out.write("                        </div>\n");
      out.write("                        <br>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <div class=\"col-lg-12 col-xs-12\">\n");
      out.write("                                <div class=\"input-group date\" >\n");
      out.write("                                    <input type=\"text\" class=\"form-control\"  id=\"duedate\" name=\"duedate\" Placeholder=\"Due date\" />\n");
      out.write("                                    <span class=\"input-group-addon\">\n");
      out.write("                                        <span class=\"glyphicon glyphicon-calendar\"></span>\n");
      out.write("                                    </span>\n");
      out.write("                                </div>  \n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <br><br>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <div class=\"radio\">\n");
      out.write("                                <b>Recurring :</b>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"radio\">\n");
      out.write("                                <label>\n");
      out.write("                                    <input type=\"radio\" name=\"recur\" id=\"weekly\" value=\"weekly\">\n");
      out.write("                                    Weekly\n");
      out.write("                                </label>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"radio\">\n");
      out.write("                                <label>\n");
      out.write("                                    <input type=\"radio\" name=\"recur\" id=\"monthly\" value=\"monthly\">\n");
      out.write("                                    Monthly\n");
      out.write("                                </label>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"radio\">\n");
      out.write("                                <label>\n");
      out.write("                                    <input type=\"radio\" name=\"\" id=\"none\" value=\"none\" checked>\n");
      out.write("                                    None\n");
      out.write("                                </label>\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <br><br>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <div class=\"col-md-9 col-xs-9\">\n");
      out.write("                                <input type=\"text\" class=\"form-control\" Placeholder=\"Add friends...\" id=\"addedfriend\" name=\"addedfriend\">\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"col-md-3 col-xs-3\">\n");
      out.write("                                <button id=\"add\" type=\"button\" class=\"btn btn-success\" onclick=\"showFriend()\"> Add</button>\t\n");
      out.write("                            </div>\n");
      out.write("                        </div>\n");
      out.write("                        <br><br>\n");
      out.write("                        <div id=\"somediv\"  style=\"color:red;\"></div>\n");
      out.write("                        <div id=\"content\"></div>\n");
      out.write("                        <br>\n");
      out.write("                        <div class=\"form-group\">\n");
      out.write("                            <button type=\"submit\" class=\"btn btn-primary\">Add task</button><br><br>\n");
      out.write("                        </div>\n");
      out.write("                        <br>\n");
      out.write("                    </form>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    <!-- end add task modal-->                                                         \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("    \n");
      out.write("\n");
      out.write("    <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js\"></script>\n");
      out.write("    <!-- Include all compiled plugins (below), or include individual files as needed -->\n");
      out.write("    <script src=\"js/bootstrap.min.js\"></script>\n");
      out.write("    <script src=\"http://bsdp-assets.blackcherry.us/1.3.0/bootstrap-datepicker.min.js\"></script>\n");
      out.write("    <!-- BootstrapValidator JS -->\n");
      out.write("    <script type=\"text/javascript\" src=\"dist/js/bootstrapValidator.min.js\"></script>\n");
      out.write("\n");
      out.write("    <script>\n");
      out.write("\n");
      out.write("\ti = 0;\n");
      out.write("\t\n");
      out.write("\t $(function () {\n");
      out.write("                $(\"#duedate\").datepicker();\n");
      out.write("            });\n");
      out.write("          function chartdisplay(){\n");
      out.write("             var mainuser = $(\"#mainuser\").val();\n");
      out.write("             console.log(mainuser);\n");
      out.write("             var list =[];\n");
      out.write("             var list1=[];\n");
      out.write("             var list2=[];\n");
      out.write("             var i=0;\n");
      out.write("             $.get('DisplayChart',\"&mainuser=\"+mainuser,function(ResponseText){\n");
      out.write("               $.each(ResponseText, function(index, item)\n");
      out.write("               {\n");
      out.write("                   \n");
      out.write("                 $.each(item,function(index,data){\n");
      out.write("                    if(i===1)\n");
      out.write("                    {\n");
      out.write("                    list.push(data);\n");
      out.write("                    }\n");
      out.write("                    else if(i===2)\n");
      out.write("                    {\n");
      out.write("                        list1.push(data);\n");
      out.write("               \n");
      out.write("                    }\n");
      out.write("                    else\n");
      out.write("                    {\n");
      out.write("                        list2.push(data);\n");
      out.write("                    }\n");
      out.write("                    \n");
      out.write("                })\n");
      out.write("                i++;\n");
      out.write("               })\n");
      out.write("               console.log(list);\n");
      out.write("               console.log(list1);\n");
      out.write("               console.log(list2);\n");
      out.write("               var datapointsgraph=[[]];\n");
      out.write("               var datapointsgraph2=[[]];\n");
      out.write("               var data=[[]];\n");
      out.write("               var j=0;\n");
      out.write("               for (i=0;i<list.length;i++)\n");
      out.write("               {\n");
      out.write("                   datapointsgraph[j]={\n");
      out.write("                       \n");
      out.write("                       label:list1[i],y:Number(list2[i])\n");
      out.write("                   }\n");
      out.write("                   //console.log(datapoints[j]);\n");
      out.write("                   j++;\n");
      out.write("               }\n");
      out.write("               var k=0;\n");
      out.write("                for (i=0;i<list.length;i++)\n");
      out.write("               {\n");
      out.write("                   datapointsgraph2[k]={\n");
      out.write("                       \n");
      out.write("                       label:list1[i],y:Number(list[i]-list2[i])\n");
      out.write("                   }\n");
      out.write("                   //console.log(datapoints[j]);\n");
      out.write("                   k++;\n");
      out.write("               }\n");
      out.write("               //var z=datapointsgraph[0].y;\n");
      out.write("               //var q=datapointsgraph[1].y;\n");
      out.write("              var chart = new CanvasJS.Chart(\"chartContainer\", {\n");
      out.write("                  \n");
      out.write("                                title:{\n");
      out.write("                                text:\"Graphical Representaion\"   \n");
      out.write("                                },\n");
      out.write("                                axisY:{\n");
      out.write("                                  title:\"Points\"   \n");
      out.write("                                },\n");
      out.write("                                data: [\n");
      out.write("                                {        \n");
      out.write("                                  type: \"stackedColumn\",\n");
      out.write("                                  toolTipContent: \"{label}<br/><span style='\\\"'color: {color};'\\\"'><strong>{name}</strong></span>: {y}\",\n");
      out.write("                                  name: \"Points Completed\",\n");
      out.write("                                  showInLegend: \"true\",\n");
      out.write("                                  dataPoints: datapointsgraph\n");
      out.write("                                },  {        \n");
      out.write("                                  type: \"stackedColumn\",\n");
      out.write("                                  toolTipContent: \"{label}<br/><span style='\\\"'color: {color};'\\\"'><strong>{name}</strong></span>: {y}\",\n");
      out.write("                                  name: \"Points Remaining\",\n");
      out.write("                                  showInLegend: \"true\",\n");
      out.write("                                  dataPoints: datapointsgraph2\n");
      out.write("                                }            \n");
      out.write("                                ]\n");
      out.write("                                \n");
      out.write("                                /**legend:{\n");
      out.write("                                  cursor:\"pointer\",\n");
      out.write("                                  itemclick: function(e) {\n");
      out.write("                                    if (typeof (e.dataSeries.visible) ===  \"undefined\" || e.dataSeries.visible) {\n");
      out.write("                                            e.dataSeries.visible = false;\n");
      out.write("                                    }\n");
      out.write("                                    else\n");
      out.write("                                    {\n");
      out.write("                                      e.dataSeries.visible = true;\n");
      out.write("                                    }\n");
      out.write("                                    chart.render();\n");
      out.write("                                  }\n");
      out.write("                                }**/\n");
      out.write("                              })\n");
      out.write("\n");
      out.write("     chart.render();\n");
      out.write("              \n");
      out.write("    })\n");
      out.write("  }; \n");
      out.write("        //Here the entered name is validated from the database via an ajax call to determine if the said person exists.\n");
      out.write("\t$(\"#SearchButton\").click(function(){\n");
      out.write("           var searchname = $(\"#searchname\").val();\n");
      out.write("           var mainuser = $(\"#mainuser\").val();\n");
      out.write("            $.get('Search',\"&searchname=\"+searchname+\"&mainuser=\"+mainuser,function(ResponseText){\n");
      out.write("              var parts = ResponseText.split(\"&\");\n");
      out.write("              var response = parts[0];\n");
      out.write("              var username = parts[1];\n");
      out.write("              if(response==\"true\") {\n");
      out.write("                    $(\"#searchUpdate\").text(\"User Found\");\n");
      out.write("                    $(\"#addfriend\").removeAttr(\"disabled\");     \n");
      out.write("              }\n");
      out.write("              else\n");
      out.write("                  $(\"#searchUpdate\").text(\"User not Found\");\n");
      out.write("              $(\"#searched_username\").attr('value',username);\n");
      out.write("           });\n");
      out.write("        });\n");
      out.write("\t\n");
      out.write("        function logout() {\n");
      out.write("            $.get('Logout');\n");
      out.write("        }\n");
      out.write("        \n");
      out.write("        //Here the entered name is validated from the database via an ajax call to check whether the said person is a friend or not.\n");
      out.write("        function showFriend() {\n");
      out.write("                 var addedfriend = $(\"#addedfriend\").val();\n");
      out.write("                 var curr_user = $(\"#Name\").val();\n");
      out.write("                 if (addedfriend != curr_user) {\n");
      out.write("                    $.get('Validate_Assignee','&curr_user='+curr_user+'&addedfriend='+addedfriend, function(responseText) { // Execute Ajax GET request on URL of \"someservlet\" and execute the following function with Ajax response text...\n");
      out.write("                          if (responseText === \"true\") {\n");
      out.write("                              $(\"#somediv\").text(\"\");\n");
      out.write("                              i = i + 1;\n");
      out.write("                              var name = document.getElementById(\"addedfriend\").value;\n");
      out.write("                              string = \"<div id='here\"+i+\"' onClick='removeFriend(this)' ><input type='text' style='border:none' name='list'  value='\"+name+\"' />\"+\"&nbsp;<span class='glyphicon glyphicon-remove' style='color:#7F7F7F;'></span><br></div>\";\n");
      out.write("                              $(\"#content\").append(string);\n");
      out.write("                              $(\"#addedfriend\").val('');\n");
      out.write("                           }\n");
      out.write("                           else if (responseText == \"false\"){\n");
      out.write("                              $(\"#somediv\").text(\"Not your friend! Add him first!\");\n");
      out.write("                              $(\"#addedfriend\").val('');\n");
      out.write("                           }\n");
      out.write("                           else {\n");
      out.write("                               $(\"#somediv\").text(responseText);\n");
      out.write("                               $(\"#addedfriend\").val('');  \n");
      out.write("                           }\n");
      out.write("                       });\n");
      out.write("                   }\n");
      out.write("                   else {\n");
      out.write("                        $(\"#somediv\").text(\"\");\n");
      out.write("                        i = i + 1;\n");
      out.write("                        var name = document.getElementById(\"addedfriend\").value;\n");
      out.write("                        string = \"<div id='here\"+i+\"' onClick='removeFriend(this)' ><input type='text' style='border:none' name='list'  value='\"+name+\"' />\"+\"&nbsp;<span class='glyphicon glyphicon-remove' style='color:#7F7F7F;'></span><br></div>\";\n");
      out.write("                        $(\"#content\").append(string);\n");
      out.write("                        $(\"#addedfriend\").val('');\n");
      out.write("                   }\n");
      out.write("\t}\n");
      out.write("        \n");
      out.write("        //This code removes the added friend in the add task modal\n");
      out.write("\tfunction removeFriend(item) {\n");
      out.write("\t\t$(item).remove();\n");
      out.write("\t}\t\n");
      out.write("        \n");
      out.write("        //Show/hide and forms and reset values on close.\n");
      out.write("\t$(\"#inviteForm\").hide();\n");
      out.write("        \n");
      out.write("        $(\"#showaddtaskmodal\").click(function() {\t\n");
      out.write("                document.forms[\"addtaskForm\"].reset();\n");
      out.write("                $(\"#somediv\").text(\"\");\n");
      out.write("                $(\"#content\").text(\"\");\n");
      out.write("\t});\n");
      out.write("        \n");
      out.write("        $(\"#showaddfriendmodal\").click(function(){\n");
      out.write("\n");
      out.write("\t\t$(\"#searchForm\").show();\n");
      out.write("\t\t$(\"#inviteForm\").hide();\n");
      out.write("                document.forms[\"searchForm\"].reset();\n");
      out.write("                document.forms[\"inviteForm\"].reset();\n");
      out.write("\t});\n");
      out.write("\t\n");
      out.write("        $(\"#Invite\").click(function(){\n");
      out.write("\t\t$(\"#searchForm\").hide();\n");
      out.write("\t\t$(\"#inviteForm\").show();\n");
      out.write("                document.forms[\"searchForm\"].reset();\n");
      out.write("                document.forms[\"inviteForm\"].reset();\n");
      out.write("\t});\n");
      out.write("        \n");
      out.write("        //Carousel slide code to display 2 tasks on one slide and move ahead by 1 task.\n");
      out.write("        $('#myCarousel').carousel({\n");
      out.write("\t\t  interval: 4000,\n");
      out.write("                  wrap: false\n");
      out.write("\t\t})\n");
      out.write("        $('#myCarousel1').carousel({\n");
      out.write("\t\t  interval: 4000,\n");
      out.write("                  wrap: false\n");
      out.write("\t\t})\n");
      out.write("                \n");
      out.write("\t\t$('.carousel .item').each(function(){\n");
      out.write("\t\t\t  var next = $(this).next();\n");
      out.write("\t\t\t  if (!next.length) {\n");
      out.write("\t\t\t\tnext = $(this).siblings(':first');\n");
      out.write("\t\t\t  }\n");
      out.write("\t\t\t  next.children(':first-child').clone().appendTo($(this));\n");
      out.write("\t\t\t  \n");
      out.write("\t\t\t});\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        $(document).ready(function() {\n");
      out.write("            $('.carousel').carousel('pause');\n");
      out.write("            // Bootstrap validator code for add task form.\n");
      out.write("            $('#addtaskForm').bootstrapValidator({\n");
      out.write("                    container:'tooltip',\n");
      out.write("                    feedbackIcons: {\t\t\n");
      out.write("                    valid: 'glyphicon glyphicon-ok',\n");
      out.write("                    invalid: 'glyphicon glyphicon-remove',\n");
      out.write("                    validating: 'glyphicon glyphicon-refresh'\n");
      out.write("                    },\n");
      out.write("                    fields: {\n");
      out.write("\t\t\ttaskname: {\n");
      out.write("                        \tvalidators: {\n");
      out.write("\t\t\t\tnotEmpty: {\n");
      out.write("                                    message: 'Task name is required'\n");
      out.write("                                },\n");
      out.write("                                regexp: {\n");
      out.write("                                    regexp: /^[a-zA-Z ]+$/,\n");
      out.write("                                    message: 'Alphabets only'\n");
      out.write("                                },\n");
      out.write("                                }\n");
      out.write("                        },\n");
      out.write("                        taskpoints: {\n");
      out.write("                            validators: {\n");
      out.write("\t\t\t\tnotEmpty: {\n");
      out.write("                                    message: 'Task points are required'\n");
      out.write("\t\t\t\t},\n");
      out.write("\t\t\t\tregexp: {\n");
      out.write("                                    regexp: /^[0-9]+$/,\n");
      out.write("                                    message: 'Numbers only'\n");
      out.write("\t\t\t\t},\n");
      out.write("                            }\n");
      out.write("                        },\n");
      out.write("                    }\n");
      out.write("            });\n");
      out.write("                \n");
      out.write("            //Bootstrap validator code for add friend form.\n");
      out.write("            $('#inviteForm').bootstrapValidator({\n");
      out.write("                container:'tooltip',\n");
      out.write("\t\tfeedbackIcons: {\n");
      out.write("\t\t\tvalid: 'glyphicon glyphicon-ok',\n");
      out.write("\t\t\tinvalid: 'glyphicon glyphicon-remove',\n");
      out.write("                    \tvalidating: 'glyphicon glyphicon-refresh'\n");
      out.write("\t\t},\n");
      out.write("\t\tfields: {\n");
      out.write("\t\t\tfirstname: {\n");
      out.write("\t\t\t\tvalidators: {\n");
      out.write("                            \t\tnotEmpty: {\n");
      out.write("                                            message: 'First name is required'\n");
      out.write("                                        },\n");
      out.write("                                        regexp: {\n");
      out.write("                                            regexp: /^[a-zA-Z]+$/,\n");
      out.write("                                            message: 'Alphabets only'\n");
      out.write("                                        },\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t},\n");
      out.write("\t\t\tlastname: {\n");
      out.write("\t\t\t\tvalidators: {\n");
      out.write("\t\t\t\t\tnotEmpty: {\n");
      out.write("\t\t\t\t\t\tmessage: 'Last name is required'\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\tregexp: {\n");
      out.write("\t\t\t\t\t\tregexp: /^[a-zA-Z]+$/,\n");
      out.write("\t\t\t\t\t\tmessage: 'Alphabets only'\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t},\n");
      out.write("                        email: {\n");
      out.write("\t\t\t\tvalidators: {\n");
      out.write("                            \t\tnotEmpty: {\n");
      out.write("                                    \t\tmessage: 'Email is required'\n");
      out.write("\t\t\t\t\t},\n");
      out.write("\t\t\t\t\tregexp: {\n");
      out.write("\t\t\t\t\t\tregexp: /^(([^<>()[\\]\\\\.,;:\\s@\\\"]+(\\.[^<>()[\\]\\\\.,;:\\s@\\\"]+)*)|(\\\".+\\\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/,\n");
      out.write("                                                message: 'Email is invalid'\n");
      out.write("                                        },\n");
      out.write("                                }\n");
      out.write("                        },\n");
      out.write("\t\t}\n");
      out.write("            });\n");
      out.write("        });\n");
      out.write("\t\n");
      out.write("    </script>\n");
      out.write("  </body>\n");
      out.write("</html>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
