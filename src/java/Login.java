/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import org.apache.log4j.Logger;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
//import java.util.logging.Logger;
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
    //private static final Logger logger = Logger.getLogger(Login.class);

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
    public boolean JUNIT(boolean flag) throws SQLException
    {
        String user="vtalreja";
        user = user.toLowerCase();
        String pass="firewaterthunder";
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        Connection conn;
        conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
        Statement st = conn.createStatement();
        ResultSet rs = null;
        try {
            
            String query1 = "SELECT * FROM WTFuser where username = '"+user+"'";
            rs = st.executeQuery(query1);
            boolean result = rs.next();
            return result;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
        finally {
            rs.close();
            st.close();
            conn.close();
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
        
        String user=request.getParameter("lusername").replaceAll(" ","");
        user = user.toLowerCase();
        String pass=request.getParameter("lpassword").replaceAll(" ","");
        Connection conn=null;
        Statement st =null;
        ResultSet rs =null;
        response.setContentType("text/html;charset=UTF-8");
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
        
        try{

            conn = DriverManager.getConnection(connection,username,password);
            String query1 = "SELECT * FROM WTFuser where username = '"+user+"'";
            st = conn.createStatement();
            rs = st.executeQuery(query1);

            boolean flag = rs.next();
            //logger.debug("Login Database Connected");
            /*Checks whether the query has returned some results, if not it will redirect to the login page
            with an error message, else it will redirect the user to the his home page*/
            if(flag==false)
            {
                    request.setAttribute("logon","fail");
                    //logger.debug("Failed Login");
                    RequestDispatcher rm=request.getRequestDispatcher("task_login.jsp");
                    rm.forward(request, response);
                    
            }
            else
            {
                if(rs.getString("password").equals(pass))
                {
                    
                    //Cookie usernameCookie = new Cookie("userName",userName);
                    //response.addCookie(usernameCookie);
                    //Cookie firstNameCookie = new Cookie("firstName",rs.getString("FIRSTNAME"));
                    //response.addCookie(firstNameCookie);
                    //Cookie lastNameCookie = new Cookie("lastName",rs.getString("LastNAME"));
                    //response.addCookie(lastNameCookie);
                    //out.println("Welcome "+rs.getString("FirstName"));
                    request.setAttribute("Name",rs.getString("FirstName"));
                    request.setAttribute("username",rs.getString("username"));
                    request.setAttribute("change_date",change_date);
                    request.setAttribute("number_of_days",number_of_days);
                    //logger.debug("Login Successful");
                    RequestDispatcher rd=request.getRequestDispatcher("user_home_new.jsp");
                    rd.include(request, response);
                    //response.sendRedirect("user_home.jsp");
                }
                else
                {
                    request.setAttribute("logon","fail");
                   // logger.debug("Login Failed due to invalid password");
                    RequestDispatcher rm=request.getRequestDispatcher("task_login.jsp");
                    rm.forward(request, response);
                }
            }
            
            
        }
        catch(SQLException ex)
        {
            out.print("Connection Failed!");
        }
        finally
        {
            try {
                st.close();
                rs.close();
                conn.close();
            } catch (SQLException ex) {
               // logger.debug("Login Successful");
                //Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
