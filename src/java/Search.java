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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           String connectionURL = "jdbc:derby://localhost:1527/WTFtask";//Connection to server
           try {
               Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
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
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query1);
                boolean flag = rs.next();
                if (flag == true){
                    username=rs.getString("username");
                    response.getWriter().write("true&"+username);
                    System.out.println(username);
                }
                
                rs.close();
            }
            else {
                System.out.println("Either first or last");
                String query1="SELECT * FROM WTFuser where FIRSTNAME = '"+searched_user+"'";//query to get searched user list by firstname
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(query1);
                boolean flag1 = rs.next();
                if(flag1 == true) { 
                    
                    username=rs.getString("username");
                    
                    response.getWriter().write("true&"+username);
                    rs.close();
                }
                else {
                    String query2="SELECT * FROM WTFuser where LASTNAME = '"+searched_user+"'";//query to get searched user list by Lastname
                    Statement st1 = conn.createStatement();
                    ResultSet rs1 = st.executeQuery(query2);
                    boolean flag2 = rs1.next();
                    if (flag2 == true) {
                        
                        
                        username=rs1.getString("username");
                       
                        response.getWriter().write("true&"+username);
                        rs1.close();
                    }
                   
                    st1.close();//connection close
                }
                
            }
            
           
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
    }
}// </editor-fold>


