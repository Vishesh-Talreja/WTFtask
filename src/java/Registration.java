/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import org.apache.log4j.Logger;
import java.io.BufferedReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
//import java.util.logging.Logger;
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
            //logger.debug("Login Successful");
            //Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
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
            //logger.debug("Login Successful");
            //Logger.getLogger(Registration.class.getName()).log(Level.SEVERE, null, ex);
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
