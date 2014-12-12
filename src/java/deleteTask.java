/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Savepoint;
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
@WebServlet(urlPatterns = {"/deleteTask"})
public class deleteTask extends HttpServlet {

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
    public boolean JUNIT(int id) throws SQLException
    {
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
        Statement st = conn.createStatement();
        conn.setAutoCommit(false);
        Savepoint spt1 = conn.setSavepoint("svpt1");
        try {
            String query = "DELETE FROM WTFtaskallocation WHERE TASKID= "+id;
            String query1 = "DELETE FROM WTFtasks WHERE TASKID= "+id;
            int allocation_rows = st.executeUpdate(query);
            if (allocation_rows == 1) {
                int task_rows = st.executeUpdate(query1);
                if( task_rows == 1)
                {
                    conn.rollback(spt1);
                    conn.commit();
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
                return false;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        finally {
            try{
                      st.close();
                      conn.close(); 
                  }
                  catch (SQLException ex) {
                       Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                  }     
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
        String taskPoints = request.getParameter("taskPoints");
        String taskDueDate = request.getParameter("taskDueDate");
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
                Connection conn = DriverManager.getConnection(connection,username,password);
                String getTaskId = "SELECT * FROM WTFtasks where TASKNAME = '"+taskName+"' AND TASKPOINTS='"+taskPoints+"' AND DUEDATE='"+taskDueDate+"'";
                String deleteTaskQuery = "DELETE FROM WTFtasks where TASKNAME = '"+taskName+"' AND TASKPOINTS='"+taskPoints+"'";
                Statement st = conn.createStatement();
                ResultSet taskSet = st.executeQuery(getTaskId);
                boolean flag = taskSet.next();
                if (flag ==true) {
                    int id = taskSet.getInt("TaskID");
                    String deleteTaskAllocationQuery = "DELETE FROM WTFtaskallocation where TASKID="+id;
                    st.executeUpdate(deleteTaskAllocationQuery);
                    int rows = st.executeUpdate(deleteTaskQuery);
                    if (rows != 0)
                        response.getWriter().write("true");
                    else
                        response.getWriter().write("false");
                }
                else {
                    response.getWriter().write("task not found");
                }
                taskSet.close();
                st.close();
                conn.close();

            }
            catch(SQLException ex) {
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
