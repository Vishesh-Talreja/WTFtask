/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.apache.log4j.Logger;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.*;
import java.sql.DriverManager;
import java.util.*;
import java.util.logging.Level;
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
@WebServlet(urlPatterns = {"/Login"})
public class Login extends HttpServlet {
private static final Logger logger = Logger.getLogger(Login.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @SuppressWarnings("empty-statement")
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String user=request.getParameter("lusername").replaceAll(" ","");
        user = user.toLowerCase();
        String pass=request.getParameter("lpassword").replaceAll(" ","");

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()){
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        try{
            
            Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            String query1 = "SELECT * FROM WTFuser where username = '"+user+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            boolean flag = rs.next();
            logger.debug("Login Database Connected");
            /*Checks whether the query has returned some results, if not it will redirect to the login page
            with an error message, else it will redirect the user to the his home page*/
            if(flag==false)
            {
                    request.setAttribute("logon","fail");
                    logger.debug("Failed Login");
                    RequestDispatcher rm=request.getRequestDispatcher("task_login.jsp");
                    rm.forward(request, response);
                    
            }
            else
            {
                if(rs.getString("password").equals(pass))
                {
                    out.println("Welcome "+rs.getString("FirstName"));
                    request.setAttribute("Name",rs.getString("FirstName"));
                    request.setAttribute("username",rs.getString("username"));
                    logger.debug("Login Successful");
                    RequestDispatcher rd=request.getRequestDispatcher("user_home.jsp");
                    rd.forward(request, response);
                }
                else
                {
                    request.setAttribute("logon","fail");
                    logger.debug("Login Failed due to invalid password");
                    RequestDispatcher rm=request.getRequestDispatcher("task_login.jsp");
                    rm.forward(request, response);
                }
            }
            
            st.close();
            rs.close();
            conn.close();
            
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
