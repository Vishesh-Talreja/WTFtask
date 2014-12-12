/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
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
@WebServlet(urlPatterns = {"/Search"})
public class Search extends HttpServlet {

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
  
 /* JUNIT Testing*/
    public boolean JUNIT(String searched_user)
    {
      String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
      Connection conn = null ;
      Statement st1=null;
      ResultSet rs1=null;
      try {
            conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            Statement stmt=conn.createStatement(); 
            char searcheduser[] = searched_user.toCharArray();
            boolean isWhiteSpace = false;
            for (int i=0;i<searcheduser.length;i++) {
                if(searcheduser[i]==' '){
                   isWhiteSpace = true;
                }
            }
            if(isWhiteSpace) {
                String[] parts = searched_user.split(" ");
                String first_name = parts[0];
                String last_name = parts[1];
                String query1="SELECT * FROM WTFuser where FIRSTNAME = '"+first_name+"' AND LASTNAME = '"+last_name+"'";//query that obtains the set of searched user
                st1 = conn.createStatement();
                rs1 = st1.executeQuery(query1);
                boolean result = rs1.next();
                return result;
            }
            else {
                String query1="SELECT * FROM WTFuser where FIRSTNAME = '"+searched_user+"'";//query to get searched user list by firstname
                 st1 = conn.createStatement();
                 rs1 = st1.executeQuery(query1);
                boolean firstname_result = rs1.next();
                if(firstname_result) { 
                    return firstname_result;
                }
                else {
                    String query2="SELECT * FROM WTFuser where LASTNAME = '"+searched_user+"'";//query to get searched user list by Lastname
                    st1 = conn.createStatement();
                    rs1 = st1.executeQuery(query2);
                    boolean lastname_result = rs1.next();
                    return lastname_result;
                   
                  // st1.close();//connection close
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
              rs1.close();
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
    /** This servlet is responsible for searching the friends in the search field of add a friend modal*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //response.setContentType("text/html;charset=UTF-8");
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        String searched_user = request.getParameter("searchname");//Is used to get the name of the user being searched from search modal
        searched_user = searched_user.toLowerCase();//makes it lower case
        String main_username = request.getParameter("mainuser");
        main_username = main_username.toLowerCase();        
        String username = "";
        String connection=null,dusername=null,password=null,change_date = null, number_of_days= null;
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
            ResultSet rs = null;
            try {
            conn = DriverManager.getConnection(connection,dusername,password);
            Statement stmt=conn.createStatement(); 
            char searcheduser[] = searched_user.toCharArray();
            boolean isWhiteSpace = false;
            for (int i=0;i<searcheduser.length;i++) {
                if(searcheduser[i]==' '){
                   isWhiteSpace = true;
                }
            }
            if(isWhiteSpace) {
                String[] parts = searched_user.split(" ");
                String first_name = parts[0];
                String last_name = parts[1];
                String query1="SELECT * FROM WTFuser where FIRSTNAME = '"+first_name+"' AND LASTNAME = '"+last_name+"'";//query that obtains the set of searched user
                st = conn.createStatement();
                rs = st.executeQuery(query1);
                boolean flag = rs.next();
                if (flag == true){
                    username=rs.getString("username");
                    if (username.equals(main_username))
                    {
                        response.getWriter().write("false1&"+username);
                    }
                    else
                    {
                    response.getWriter().write("true&"+username);
                    }
                }
                
            }
            else {
                String query1="SELECT * FROM WTFuser where FIRSTNAME = '"+searched_user+"'";//query to get searched user list by firstname
                st = conn.createStatement();
                rs = st.executeQuery(query1);
                boolean flag1 = rs.next();
                if(flag1 == true) { 
                    
                    username=rs.getString("username");
                    if (username.equals(main_username))
                    {
                        response.getWriter().write("false1&"+username);
                    }
                    else
                    {
                    response.getWriter().write("true&"+username);
                    }
                }
                else {
                    String query2="SELECT * FROM WTFuser where LASTNAME = '"+searched_user+"'";//query to get searched user list by Lastname
                   st = conn.createStatement();
                     rs = st.executeQuery(query2);
                    boolean flag2 = rs.next();
                    if (flag2 == true) {
                        
                        
                        username=rs.getString("username");
                        if (username.equals(main_username))
                        {
                        response.getWriter().write("false1&"+username);
                        }
                       else
                        {
                        response.getWriter().write("true&"+username);
                        }
                    }
                }
                
            }
            
           
        }
        catch(SQLException ex)
        {
            out.print("Connection Failed!");
        }
            finally{
          try{
              st.close();
              rs.close();
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
    }
}// </editor-fold>


