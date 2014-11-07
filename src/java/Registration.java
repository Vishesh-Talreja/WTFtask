/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.io.FileReader;
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
import java.util.logging.Logger;
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
    //private static final Logger logger = Logger.getLogger(Registration.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public boolean JUNIT(boolean flag)
    {
        String Fname="TEST";
        Fname = Fname.toLowerCase();
        String Lname="TEST";
        Lname = Lname.toLowerCase();
        String Email="test@test.com";
        Email = Email.toLowerCase();
        String user="Test";
        user = user.toLowerCase();
        String pass="test";
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        try {
            Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            Statement stmt=conn.createStatement();
            String query2 = "INSERT INTO IS2560.WTFuser (LASTNAME,FIRSTNAME,USERNAME,EMAIL,PASSWORD) VALUES ('"+Lname+"','"+Fname+"','"+user+"','"+Email+"','"+pass+"')";
            stmt.executeUpdate(query2);
            String query1 = "SELECT * FROM WTFuser where username = '"+user+"'";
            ResultSet rs = stmt.executeQuery(query1);
            boolean flag1 = rs.next();
            if (flag1 == true)
                stmt.executeUpdate("DELETE FROM IS2560.WTFuser WHERE username='test'");
            stmt.close();
            rs.close();
            conn.close();  
            if(flag1==true)
            {
                return true;
            }
            else
            {
                return false;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }
    /*This functions takes inputs from the user registration form and then inputs it into the databse
    after which the user is redirected to the login page, and is asked to login*/
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
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
        Connection conn=null;
        Statement stmt=null;
        String connection,username,password;
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\vinay\\Documents\\NetBeansProjects\\WTFtask\\WTFtask\\config.txt"));
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
            username = arg[0];
            password = arg[1];
            
        } finally {
            br.close();
        }
        try (PrintWriter out = response.getWriter()) {
            
        try{
            conn = DriverManager.getConnection(connection,username,password);
            stmt=conn.createStatement();
            String query2 = "INSERT INTO IS2560.WTFuser (LASTNAME,FIRSTNAME,USERNAME,EMAIL,PASSWORD) VALUES ('"+Lname+"','"+Fname+"','"+user+"','"+Email+"','"+pass+"')";
            stmt.executeUpdate(query2);
            //logger.debug("Regitration Database Connected"); 
            request.setAttribute("registration","complete");
                    //logger.debug("Failed Login");
            RequestDispatcher rm=request.getRequestDispatcher("task_login.jsp");
            rm.forward(request, response);
        }
        catch(SQLException ex)
        {
            out.print("Connection Failed!");
        }
        finally
        {
            stmt.close();
            conn.close();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
        }
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
