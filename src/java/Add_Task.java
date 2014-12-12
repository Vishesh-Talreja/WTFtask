/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import org.apache.log4j.Logger;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public boolean JUNIT(boolean flag) throws SQLException
    {
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
        Statement stmt=conn.createStatement();
        ResultSet rs = null;
        try {
            
            
            String query2 = "INSERT INTO IS2560.WTFtasks (TASKNAME,TASKPOINTS,DUEDATE,CREATEDDATE,ALLOTEDTASKPOINTS,OWNER,RECUR) VALUES('test','0','2014-11-05','2014-11-05','0','none','test')";
            stmt.executeUpdate(query2);
            String query1 = "SELECT * FROM WTFtasks where taskname = 'test'";
            rs= stmt.executeQuery(query1);
            boolean result = rs.next();
            if (result)
                stmt.executeUpdate("DELETE FROM IS2560.WTFtasks WHERE taskname='test'");
            
            return result;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        finally {
            stmt.close();
            rs.close();
            conn.close();  
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
        String CreateDate=request.getParameter("currdate").replaceAll(" ","");
        String recur = request.getParameter("recur").replace(" "," ");
        //This piece of code extracts the database paqrameters from the file config.txt
        String connection=null,username=null,password=null,change_date = null, number_of_days= null;
        InputStream in = Login.class.getResourceAsStream("/config.txt");
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        try {
            
            String line=null;
                while((line=reader.readLine())!=null){
                    String[] arg = line.split(" ");
                    username = arg[0];
                    String user_arg[] = username.split("=");
                    username = user_arg[1];
                    password = arg[1];
                    String pass_arg[] = password.split("=");
                    password = pass_arg[1];
                    connection = arg[2];
                    String conn_arg[] = connection.split("=");
                    connection = conn_arg[1];
                    change_date = arg[3];
                    String cd_arg[] = change_date.split("=");
                    change_date = cd_arg[1];
                    number_of_days = arg[4];
                    String nbd_arg[] = number_of_days.split("=");
                    number_of_days = nbd_arg[1];
                }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        Connection conn = null;
        Statement stmt = null;
        try {
            conn = DriverManager.getConnection(connection, username,password);
            stmt = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Add_Task.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try (PrintWriter out = response.getWriter()) {
        try{
           
            String query3 = "INSERT INTO IS2560.WTFtasks (TASKNAME,TASKPOINTS,DUEDATE,CREATEDDATE,ALLOTEDTASKPOINTS,OWNER,RECUR) VALUES ('"+Tname+"','"+Tpoints+"','"+Tduedate+"','"+CreateDate+"','0' ,'"+user+"','"+recur+"')";
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
            
            //Return to homepage
            response.getWriter().write("true");
            
            
        }
        catch(SQLException ex)
        {
            out.print(ex+"Connection Failed!");
        }
        finally {
            try {
                stmt.close();
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Add_Task.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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
