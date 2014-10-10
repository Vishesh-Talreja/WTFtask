/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aashish
 */
@WebServlet(urlPatterns = {"/Validate_Assignee"})
public class Validate_Assignee extends HttpServlet {

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
        String adder = request.getParameter("curr_user").replaceAll(" ","");
        adder = adder.toLowerCase();
        String addedfriend = request.getParameter("addedfriend").replaceAll(" ","");
        addedfriend = addedfriend.toLowerCase();
        try (PrintWriter out = response.getWriter()){
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        try{

            Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            String query1 = "SELECT * FROM WTFuser where Firstname = '"+adder+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            boolean dummy = rs.next();
            String adder_username = rs.getString("username");
            String query2 = "SELECT * FROM WTFFriends where mainusername = '"+adder_username+"'";
            ResultSet rs1 = st.executeQuery(query2);
            boolean is = rs1.next();
            if (!is) {      //The user does not have any friends in his list.
                response.getWriter().write("Add friends first!");
            }
            else { 
                String query3 = "SELECT * FROM WTFFriends where mainusername = '"+adder_username+"' AND friendname IN (Select username FROM WTFuser WHERE Firstname = '"+addedfriend+"')";
                ResultSet rs2 = st.executeQuery(query3);
                boolean is1 = rs2.next();
                System.out.println(is1);
                if (is1)
                    response.getWriter().write("true");     //The friend is authenticated.
                else
                    response.getWriter().write("false");    //The entered name is not his friend.
                rs2.close();
            }
            
            
            st.close();
            rs.close();
            rs1.close();
            
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
