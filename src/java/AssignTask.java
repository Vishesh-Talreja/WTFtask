/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
/**
 *
 * @author Aashish
 */
@WebServlet(urlPatterns = {"/AssignTask"})
public class AssignTask extends HttpServlet {

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
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
        }
    }
    public boolean JUNIT(boolean flag)
    {
        String user="vtalreja";
        user = user.toLowerCase();
        String friend = "akanade";
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        Connection conn;
        try {
            conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            String query1 = "SELECT * FROM WTFFriends where mainusername = '"+user+"' and friendname = '"+friend+"'";
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
        
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        //Recieve input from form
        String taskName = request.getParameter("taskName");
        String taskPoints = request.getParameter("taskPoints").replaceAll(" ","");
        String taskDueDate = request.getParameter("taskDueDate").replaceAll(" ","");
        String currDate = request.getParameter("currDate").replaceAll(" ","");
        String userName = request.getParameter("username").replaceAll(" ","");
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
        try (PrintWriter out = response.getWriter()){
        
        try{
            boolean isTaskDoneEarly=false;
            Connection conn = DriverManager.getConnection(connection,username,password);
            String getTaskId = "SELECT * FROM WTFtasks where TASKNAME = '"+taskName+"' AND TASKPOINTS='"+taskPoints+"' AND DUEDATE='"+taskDueDate+"'";
            Statement st = conn.createStatement();
            ResultSet taskSet = st.executeQuery(getTaskId);
            taskSet.next();
            int id = taskSet.getInt("TaskID");
            String CreateDate = taskSet.getString("CREATEDDATE");
            String DueDate = taskSet.getString("DUEDATE");
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date d1 = dateFormat.parse(CreateDate);
                Date d2 = dateFormat.parse(DueDate);
                long diff1 = d2.getTime() - d1.getTime();
                Date d3 = new Date();
                d3 = dateFormat.parse(currDate);
                long diff2 = d3.getTime() - d2.getTime();
                if((diff2>=diff1/2))
                {
                    isTaskDoneEarly=true;
                }
 
            } catch (ParseException ex) {
                Logger.getLogger(AssignTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            String assignTask = "UPDATE IS2560.WTFTASKALLOCATION SET USERNAME='"+userName+"' WHERE TASKID="+id;
            st.executeUpdate(assignTask);
            String getPointsPossible = "SELECT * FROM WTFuser where username ='"+userName+"'";
            ResultSet pointsSet = st.executeQuery(getPointsPossible);
            pointsSet.next();
            float points = Float.parseFloat(pointsSet.getString("POINTPOSSIBLE"));
            points = points + Float.parseFloat(taskPoints);
            String newtaskPoints = Float.toString(points);
            String updatePointsPossible = "UPDATE IS2560.WTFuser SET POINTPOSSIBLE ='"+newtaskPoints+"' WHERE USERNAME='"+userName+"'";
            int rows = st.executeUpdate(updatePointsPossible);
            String updateAllotedPoints ="UPDATE WTFtasks SET ALLOTEDTASKPOINTS ='"+taskPoints+"' WHERE TASKID="+id;
            st.executeUpdate(updateAllotedPoints);
            float totalPoints = 0;
            float reducedPoints = (20*Float.parseFloat(taskPoints))/100;
            float decreasePoints = Float.parseFloat(taskPoints)-reducedPoints;
            if(isTaskDoneEarly==true)
            {    
                String newtaskPoint = "UPDATE WTFtasks SET TASKPOINTS='"+decreasePoints+"' WHERE TASKID="+id;
                st.executeUpdate(newtaskPoint);
            }
            String addTpoints = "SELECT * FROM WTFtasks where TASKID <>"+id;
            ResultSet rs = st.executeQuery(addTpoints);
            while(rs.next())
            {
                int Tid = rs.getInt("TASKID");
                Statement st2 = conn.createStatement();
                ResultSet rs1 = st2.executeQuery("SELECT STATUS,USERNAME FROM WTFTASKALLOCATION WHERE TASKID ="+Tid);
                rs1.next();
                if("Pending".equals(rs1.getString("STATUS"))&&"null".equals(rs1.getString("USERNAME")))
                {    
                    totalPoints = totalPoints+Float.parseFloat(rs.getString("TASKPOINTS"));
                }
                rs1.close();
                st2.close();
            }
            String alterTpoints="SELECT * FROM WTFtasks where TASKID <>"+id;
            rs = st.executeQuery(alterTpoints);
            while(rs.next())
            {
                int Tid = rs.getInt("TASKID");
                float Tpoints = Float.parseFloat(rs.getString("TASKPOINTS"));
                float increasedPoints = reducedPoints * (Tpoints/totalPoints);
                Tpoints = Tpoints+increasedPoints;
                String newTpoints = Float.toString(Tpoints);
                Statement st2 = conn.createStatement();
                ResultSet rs1 = st2.executeQuery("SELECT STATUS,USERNAME FROM WTFTASKALLOCATION WHERE TASKID ="+Tid);
                rs1.next();
                if("Pending".equals(rs1.getString("STATUS"))&&"null".equals(rs1.getString("USERNAME"))&&isTaskDoneEarly==true)
                { 
                    String newPoints = "UPDATE WTFtasks SET TASKPOINTS='"+newTpoints+"' WHERE TASKID="+Tid;
                    Statement st1 = conn.createStatement();
                    st1.executeUpdate(newPoints);
                    st1.close();
                }
                rs1.close();
                st2.close();
            }
            
            st.close();
            taskSet.close();
            conn.close();
            if (rows == 1)
                response.getWriter().write("true");     //The task is assigned.
            else
                response.getWriter().write("false");    //The task is not assigned.
            
            
            
        }
        catch(SQLException ex)
        {
            out.print("Connection Failed!");
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
