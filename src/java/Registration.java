/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import org.apache.log4j.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 *
 * @author visheshtalreja
 */
@WebServlet(urlPatterns = {"/Registration"})
public class Registration extends HttpServlet {
    private static final Logger logger = Logger.getLogger(Registration.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    /*This functions takes inputs from the user registration form and then inputs it into the databse
    after which the user is redirected to the login page, and is asked to login*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String Fname=request.getParameter("fname").replaceAll(" ","");
        Fname = Fname.toLowerCase();
        String Lname=request.getParameter("lname").replaceAll(" ","");
        Lname = Lname.toLowerCase();
        String Email=request.getParameter("remail").replaceAll(" ","");
        Email = Email.toLowerCase();
        String user=request.getParameter("rusername").replaceAll(" ","");
        user = user.toLowerCase();
        String pass=request.getParameter("rpassword").replaceAll(" ","");
        
        try (PrintWriter out = response.getWriter()) {
            String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        try{
            Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            Statement stmt=conn.createStatement();
            String query2 = "INSERT INTO IS2560.WTFuser (LASTNAME,FIRSTNAME,USERNAME,EMAIL,PASSWORD) VALUES ('"+Lname+"','"+Fname+"','"+user+"','"+Email+"','"+pass+"')";
            stmt.executeUpdate(query2);
            logger.debug("Regitration Database Connected");
            RequestDispatcher rd=request.getRequestDispatcher("task_login.jsp");
            rd.forward(request, response);
            conn.close();
        }
        catch(SQLException ex)
        {
            out.print("Connection Failed!");
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
        processRequest(request, response);
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
