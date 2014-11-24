/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import org.apache.log4j.Logger;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author Aashish
 */
@WebServlet(urlPatterns = {"/Add_Task"})
public class Add_Task extends HttpServlet {
   // private static final Logger logger = Logger.getLogger(Add_Task.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
    }
    
    //This is the JUnit code for this servlet.
    public boolean JUNIT(boolean flag)
    {
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        try {
            Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            Statement stmt=conn.createStatement();
            
            String query2 = "INSERT INTO IS2560.WTFtasks (TASKNAME,TASKPOINTS,DUEDATE,RECUR,OWNER) VALUES('test','0','2014-11-05','none','test')";
            stmt.executeUpdate(query2);
            String query1 = "SELECT * FROM WTFtasks where taskname = 'test'";
            ResultSet rs = stmt.executeQuery(query1);
            boolean flag1 = rs.next();
            if (flag1 == true)
                stmt.executeUpdate("DELETE FROM IS2560.WTFtasks WHERE taskname='test'");
            stmt.close();
            rs.close();
            conn.close();  
            if(flag1==true)
            {
                return true;
            }
            else
            {
                return false;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        //Recieve inputs from form
        String name=request.getParameter("Name").replaceAll(" ","");
        name = name.toLowerCase();
        String user=request.getParameter("user").replaceAll(" ","");
        user = user.toLowerCase();
        String Tname=request.getParameter("taskname");
        String Tpoints=request.getParameter("taskpoints").replaceAll(" ","");
        String Tduedate=request.getParameter("duedate").replaceAll(" ","");
        String recur = request.getParameter("recur").replace(" "," ");
        //System.out.println("The task is selected to recur "+recur+"!!!!!!!");
        String assignee;
        if (request.getParameter("list") == null) {
            assignee = null;
        }
        else {
            assignee = request.getParameter("list");
            assignee = assignee.toLowerCase();
        }
        
        //This piece of code extracts the database paqrameters from the file config.txt
        String connection,username,password;
        BufferedReader br = new BufferedReader(new FileReader("/Users/visheshtalreja/Desktop/WTFtask/src/java/config.txt"));
        //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\vinay\\Documents\\NetBeansProjects\\WTFtask1\\WTFtask\\config.txt"));
        try {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
                
            }
            String everything = sb.toString();
            String arg[] = everything.split(" ");
            connection = arg[2];
            username = arg[0];
            password = arg[1];
            
        } finally {
            br.close();
        }
        try (PrintWriter out = response.getWriter()) {
        try{
            Connection conn = DriverManager.getConnection(connection, username,password);
            Statement stmt=conn.createStatement();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String CreateDate;
            CreateDate = dateFormat.format(date);
            String query3 = "INSERT INTO IS2560.WTFtasks (TASKNAME,TASKPOINTS,DUEDATE,CREATEDDATE,OWNER,RECUR) VALUES ('"+Tname+"','"+Tpoints+"','"+CreateDate+"','"+Tduedate+"','"+user+"','"+recur+"')";
            stmt.executeUpdate(query3);                                             //Insert task details into database
            String query4 = "SELECT * FROM IS2560.WTFtasks WHERE TASKNAME='"+Tname+"'";
            ResultSet rs = stmt.executeQuery(query4);                               //Extract taskId
            rs.next();
            //logger.debug("Add Task Database Connected");
            int id = rs.getInt("TaskID");
            
            //Assign each member to this task with designated points            
            String query6;
            query6 = "INSERT INTO WTFTASKALLOCATION VALUES ("+id+",'"+null+"','Pending')";
            stmt.executeUpdate(query6);
            stmt.close();
            //Return required parameterts back to homepage
            request.setAttribute("Name", name);
            request.setAttribute("TName",Tname);
            request.setAttribute("username",user);
            request.setAttribute("added", "yes");
            conn.close();
            RequestDispatcher rd=request.getRequestDispatcher("user_home_new.jsp");
            rd.forward(request, response);
            //response.sendRedirect("user_home.jsp");
            
        }
        catch(SQLException ex)
        {
            out.print(ex+"Connection Failed!");
        }
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
