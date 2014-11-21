/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
 
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Hours;
import org.joda.time.Minutes;
import org.joda.time.Seconds;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author visheshtalreja
 */
@WebServlet(urlPatterns = {"/Complete_Task"})
public class Complete_Task extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public boolean JUNIT(boolean flag)
    {
        String user="vtalreja";
        user = user.toLowerCase();
        String pass="firewaterthunder";
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        Connection conn;
        try {
            conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            String query1 = "SELECT * FROM WTFuser where username = '"+user+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            boolean flag1 = rs.next();
            st.close();
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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
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
    /*This function is used to change a status of a task from Pending to complete*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        String Tname=request.getParameter("Tname");
        String Tpoints=request.getParameter("Tpoints");
        String name=request.getParameter("Name").replaceAll(" ","");
        name = name.toLowerCase();
        String user=request.getParameter("user").replaceAll(" ","");
        user = user.toLowerCase();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs = null;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date task_date = new Date();
        String connection,username,password;
        //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\vinay\\Documents\\NetBeansProjects\\WTFtask\\WTFtask\\config.txt"));
        BufferedReader br = new BufferedReader(new FileReader("/Users/visheshtalreja/Desktop/WTFtask/src/java/config.txt"));
        //BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Aashish\\Documents\\NetBeansProjects\\WTFtask\\config.txt"));
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
            /* TODO output your page here. You may use following sample code. */
            
           try{
            conn = DriverManager.getConnection(connection, username,password);
            stmt=conn.createStatement();
            //Slecting data from the tasks table
            String query1 = "SELECT * FROM IS2560.WTFtasks WHERE TASKNAME='"+Tname+"'";
            rs = stmt.executeQuery(query1);                               //Extract taskId
            rs.next();
            int id = rs.getInt("TaskID");
            String recurValue =rs.getString("RECUR");
            String taskdate = rs.getString("DUEDATE");
            Statement stmt1=conn.createStatement();
            String query5="SELECT * FROM WTFTASKALLOCATION WHERE TASKID="+id;
            ResultSet rs1=stmt1.executeQuery(query5);
            rs1.next();
            //Slecting data from the user table
            String query2 = "SELECT * FROM IS2560.WTFuser WHERE USERNAME='"+user+"'";
            rs = stmt.executeQuery(query2);
            rs.next();
            float TpointsBefore=Float.parseFloat(rs.getString("POINTEARNED"));
            float TpointsNow = Float.parseFloat(Tpoints);
            TpointsNow =TpointsNow+TpointsBefore;
            String NewTpoints = Float.toString(TpointsNow);
            if("Pending".equals(rs1.getString("STATUS")))
            {    
                //Adding taskpoints to the user table 
                String query3 = "UPDATE IS2560.WTFuser SET POINTEARNED = '"+NewTpoints+"' WHERE USERNAME ='"+user+"'";
                stmt.executeUpdate(query3);
            }
            rs1.close();
            stmt1.close();
            String query = null;
            if(recurValue.equals("none")) {
                query = "UPDATE IS2560.WTFtaskallocation SET STATUS = 'Complete' WHERE USERNAME ='"+user+"' and TASKID="+id;
                stmt.executeUpdate(query);
            }
            else {
                task_date = dateFormat.parse(taskdate);
                Calendar c = Calendar.getInstance();
                c.setTime(task_date); 
                if(recurValue.equals("weekly")) {
                    //System.out.println("Task duedate postponed by 7 days");
                    c.add(Calendar.DATE, 7); // Adding 5 days
                    taskdate = dateFormat.format(c.getTime());
                }
                else if(recurValue.equals("monthly")) {
                    //System.out.println("Task duedate postponed by 30 days");
                    c.add(Calendar.DATE, 30); // Adding 5 days
                    taskdate = dateFormat.format(c.getTime());
                }
                
                String updateQuery = "UPDATE IS2560.WTFtasks SET DUEDATE='"+taskdate+"' WHERE TASKID="+id;
                stmt.executeUpdate(updateQuery);
            }
            request.setAttribute("Name", name);
            request.setAttribute("username",user);
            RequestDispatcher rd=request.getRequestDispatcher("user_home_new.jsp");
            rd.forward(request, response);
           }
           catch(SQLException ex)
           {
             out.print(ex+"Connection Failed!");
           } catch (ParseException ex) {
                Logger.getLogger(Complete_Task.class.getName()).log(Level.SEVERE, null, ex);
            }
           finally
           {
                try {
                    //Releasing all the resources
                    rs.close();
                    
                    stmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Complete_Task.class.getName()).log(Level.SEVERE, null, ex);
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
