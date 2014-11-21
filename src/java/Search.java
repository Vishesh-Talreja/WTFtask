/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.*;
import java.sql.DriverManager;
import java.util.*;
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
                boolean flag = rs1.next();
                if (flag == true){
                     System.out.println("inside waste");
                    return true;
                }
            }
            else {
                System.out.println("Either first or last");
                String query1="SELECT * FROM WTFuser where FIRSTNAME = '"+searched_user+"'";//query to get searched user list by firstname
                 st1 = conn.createStatement();
                 rs1 = st1.executeQuery(query1);
                boolean flag1 = rs1.next();
                if(flag1 == true) { 
                   
                    return true;
                }
                else {
                    String query2="SELECT * FROM WTFuser where LASTNAME = '"+searched_user+"'";//query to get searched user list by Lastname
                    st1 = conn.createStatement();
                    rs1 = st1.executeQuery(query2);
                    boolean flag2 = rs1.next();
                    if (flag2 == true) { 
                        return true;
                    }
                   
                  // st1.close();//connection close
                }
            
           }
            return false;
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
        String connection,dusername,password;
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
            dusername = arg[0];
            password = arg[1];
            
        } finally {
            br.close();
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
               // System.out.println("WHITE SPACE DETECTED");
               // System.out.println("Full name");
                String[] parts = searched_user.split(" ");
                String first_name = parts[0];
                String last_name = parts[1];
               // System.out.println(first_name);
                //System.out.println(last_name);
                String query1="SELECT * FROM WTFuser where FIRSTNAME = '"+first_name+"' AND LASTNAME = '"+last_name+"'";//query that obtains the set of searched user
                st = conn.createStatement();
                rs = st.executeQuery(query1);
                boolean flag = rs.next();
                if (flag == true){
                    username=rs.getString("username");
                    if (username.equals(main_username))
                    {
                        System.out.println("Inside if 9");
                        response.getWriter().write("false1&"+username);
                    }
                    else
                    {
                    response.getWriter().write("true&"+username);
                    System.out.println(username);
                    }
                }
                
            }
            else {
                System.out.println("Either first or last");
                String query1="SELECT * FROM WTFuser where FIRSTNAME = '"+searched_user+"'";//query to get searched user list by firstname
                st = conn.createStatement();
                rs = st.executeQuery(query1);
                boolean flag1 = rs.next();
                if(flag1 == true) { 
                    
                    username=rs.getString("username");
                    System.out.println(username);
                     System.out.println(main_username);
                    if (username.equals(main_username))
                    {
                        System.out.println("Inside if1");
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
                         System.out.println("Inside if");
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


