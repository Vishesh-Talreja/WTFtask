/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vinay
 */
@WebServlet(urlPatterns = {"/ReuseTask"})
public class ReuseTask extends HttpServlet {

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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //this is the Juint test it returns true if the the task could be reused or else it returns false
    public boolean JUNIT(int id, String duedate)
    {
       String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
       Connection conn = null ;
       Statement st2 = null;
      try {
            conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
             String query2 ="UPDATE WTFtaskallocation SET status = 'Pending',username='null' where TaskId="+id;
             st2=conn.createStatement();
             int allocation_rows = st2.executeUpdate(query2);
             int task_rows;
             if(allocation_rows == 1)
             {
                 task_rows = st2.executeUpdate("UPDATE WTFtasks SET duedate = '"+duedate+"' where TaskId="+id);
                 if(task_rows == 1)
                    return true;
                 else return false;
             }
             else 
             {
                 return false;
             }
             
      }
       catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
      finally{
          try{
              st2.close();
             conn.close(); 
          }
          catch (SQLException ex) {
               Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            } 
      }  
    }
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8");
        String main_username = request.getParameter("mainuser");//Give sthe main user fromn the ajax call in the front end to let the system know who is reusing the task
        String taskid = request.getParameter("taskid");//Gives the task id for task that is supposed to be reused
        String duedate = request.getParameter("duedate");//The new duedate that is to be assigned to the task
        String[] due= duedate.split("/");
        StringBuilder sb1=new StringBuilder();
        sb1.append(due[1]);
        sb1.append("/");
        sb1.append(due[0]);
        sb1.append("/");
        sb1.append(due[2]);
        String date=sb1.toString();
        main_username = main_username.toLowerCase();
        int id=Integer.parseInt(taskid);
        String connection=null,dusername=null,password=null,change_date = null, number_of_days= null;
        //helps you connect to the database by taking input from the config file.
        InputStream in = Login.class.getResourceAsStream("/config.txt");
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        try {
            
            String line=null;
                while((line=reader.readLine())!=null){
                    String[] arg = line.split(" ");
                    dusername = arg[0];
                    String user_arg[] = dusername.split("=");
                    dusername = user_arg[1];
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
        try (PrintWriter out = response.getWriter()) {
        /* TODO output your page here. You may use following sample code. */
            Connection conn = null;
            Statement st = null;
            Statement st2 = null;
            try {
                conn = DriverManager.getConnection(connection,dusername,password);
                String query1="UPDATE WTFtaskallocation SET status = 'Pending',username='null' where TaskId="+id;//first makes the task pending and makes the task unassigned
                st = conn.createStatement();
                st.executeUpdate(query1);
                String query2="UPDATE WTFtasks SET Duedate= '"+date+"'where TaskId="+id ;//reassigns the task to the present user with the new date
                st2=conn.createStatement();
                st2.executeUpdate(query2);
            }
            catch(SQLException ex) {
                out.print("Connection Failed!");
            }
            finally{
                try{
                    st.close();//connections are closed
                    conn.close(); 
                    response.getWriter().write("true");
                }
                catch (SQLException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
