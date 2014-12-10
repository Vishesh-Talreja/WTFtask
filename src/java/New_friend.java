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
@WebServlet(urlPatterns = {"/New_friend"})
public class New_friend extends HttpServlet {

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
    
    public boolean JUNIT(String mainusername,String searchedusername)
    {
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
      Connection conn = null ;
      Statement st1 = null;
       ResultSet rs2 = null;
      try {
            conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
             Statement st=conn.createStatement(); 
             String query2 = "SELECT FRIENDNAME FROM WTFFriends where MAINUSERNAME='"+mainusername+"'";
             st1 = conn.createStatement();
            rs2 = st.executeQuery(query2);
             while(rs2.next())
             {
                 String friendname=rs2.getString("FRIENDNAME");
                 if(friendname.equals(searchedusername))
                 {
                     return false;
                 }
             }
             
      }
       catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
      finally{
          try{
              st1.close();
              rs2.close();
             conn.close(); 
          }
          catch (SQLException ex) {
               Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
          
      }
        return true;
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
    /** This servlet is responsible for adding a searched user in add a friend modal*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");

        String searched_username = request.getParameter("searched_username");//Obtains searched username of the user
        String main_username = request.getParameter("mainuser");//obtains the username who is logged in
        String main_user_firstname = request.getParameter("mainuser_firstname");
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
            Connection conn=null;
            Statement st1 = null;
            ResultSet rs = null;
        try{
            
            conn = DriverManager.getConnection(connection,username,password);
            String query="INSERT INTO IS2560.WTFFriends (mainusername,friendname) VALUES ( '"+main_username+"' , '"+searched_username+"' )";//Inserts into WTFFriends database
            Statement st = conn.createStatement();
            st.executeUpdate(query);

            st1 = conn.createStatement();
            String query2 = "SELECT * FROM IS2560.WTFuser WHERE username='"+searched_username+"'";
            rs = st1.executeQuery(query2);
            boolean is = rs.next();
            String searched_user_firstname = rs.getString("Firstname");
            System.out.println(searched_user_firstname);
            request.setAttribute("FName",searched_user_firstname);//set the attribute feild fname to searched_user_firstname to be accessed from jsp
            request.setAttribute("Name",main_user_firstname);//set the attribute feild Name to main_user_firstname to be accessed from jsp
            request.setAttribute("username",main_username);//set the attribute feild username to main_username to be accessed from jsp

            request.setAttribute("added_friend","true");

            RequestDispatcher rd=request.getRequestDispatcher("user_home.jsp");//Loads the main page after friend has been added 

            rd.forward(request, response);
           //connection closed
            
        }
        catch(SQLException ex)
        {
            response.getWriter().write("false");
            out.print("Connection Failed!"); 
        }
        finally{
          try{
            rs.close();
            st1.close();
            conn.close();
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



