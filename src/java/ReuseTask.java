/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public boolean JUNIT(int id)
    {
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
      Connection conn = null ;
      Statement st2 = null;
      try {
            conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
             String query2 ="UPDATE WTFtaskallocation SET status = 'Pending',username='null' where TaskId="+id;;
             st2=conn.createStatement();
             int r=st2.executeUpdate(query2);
             if(r==1)
             {
                 System.out.println(r);
                 return true;
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
          String main_username = request.getParameter("mainuser");
           String taskid = request.getParameter("taskid");
           String duedate = request.getParameter("duedate");
           //System.out.println(duedate);
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
           
           System.out.println("xyz");
           System.out.println(id);
            System.out.println(date);
           String connection=null,dusername=null,password=null;
        InputStream in = Login.class.getResourceAsStream("/config.txt");
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        try {
            
            String line=null;
            System.out.println("sam");
                while((line=reader.readLine())!=null){
                    String[] arg = line.split(" ");
                    dusername = arg[0];
                    password = arg[1];
                    connection = arg[2];
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
           String query1="UPDATE WTFtaskallocation SET status = 'Pending',username='null' where TaskId="+id;
           st = conn.createStatement();
           st.executeUpdate(query1);
           String query2="UPDATE WTFtasks SET Duedate= '"+date+"'where TaskId="+id ;
           st2=conn.createStatement();
           st2.executeUpdate(query2);
            
           }
             catch(SQLException ex)
        {
            out.print("Connection Failed!");
        }
            finally{
          try{
              st.close();//connections are closed
              //rs.close();
             conn.close(); 
             RequestDispatcher rm=request.getRequestDispatcher("user_home_new.jsp");
             rm.forward(request, response);
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
