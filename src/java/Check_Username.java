/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//import org.apache.log4j.Logger;
import com.google.gson.Gson;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Aashish
 */
@WebServlet(urlPatterns = {"/Check_Username"})
public class Check_Username extends HttpServlet {
    //private static final Logger logger = Logger.getLogger(Check_Username.class);

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
        String user="test@indiana.edu";
        user = user.toLowerCase();
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
        Statement st = conn.createStatement();
        ResultSet rs = null;
        try {
            String query1 = "SELECT * FROM WTFuser where username = '"+user+"'";
            rs = st.executeQuery(query1);
            boolean result = rs.next();
            
            return (!result);
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
        //Recieve inputs from form
        String user=request.getParameter("rusername").replaceAll(" ","");
        user = user.toLowerCase();
        //Create JSON object to send back to validator
        Map<String, String> options = new LinkedHashMap<String, String>();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
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
            Connection conn = DriverManager.getConnection(connection, username,password);
            String query1 = "SELECT * FROM WTFuser where username = '"+user+"'";
            Statement st = conn.createStatement();  //Query the database for availability
            ResultSet rs = st.executeQuery(query1);     //Store the results in a ResultSet
            boolean is = rs.next();     //Check is ResultSet is empty
            //logger.debug("Ajax Call for username made");
            if(!is) {   //The username is available
                options.put("valid", "true");
                String json = new Gson().toJson(options);
                response.getWriter().write(json);
                //logger.debug("Check username found");
            }
            else if(is){    //The username is not available
                options.put("valid", "false");
                String json = new Gson().toJson(options);
                response.getWriter().write(json);
                //logger.debug("Check username not found");
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
