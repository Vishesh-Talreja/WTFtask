/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.Gson;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import java.util.*;
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
@WebServlet(urlPatterns = {"/DisplayChart"})
public class DisplayChart extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        }
    }
    public boolean JUNIT(String username,String password)
    {
        String user=username;
        user = user.toLowerCase();
        String pass=password;
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        Connection conn = null;
         Statement st = null;
         ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            String query1 = "SELECT * FROM WTFuser where username = '"+user+"'";
            st = conn.createStatement();
            rs = st.executeQuery(query1);
            boolean flag1 = rs.next();
            if(flag1==true)
            {
                String query2 = "SELECT * FROM WTFFriends where MAINUSERNAME='"+user+"'";
                st = conn.createStatement();
                rs = st.executeQuery(query2);
                if(rs.next())
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
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
        response.setCharacterEncoding("UTF-8");
          String main_username = request.getParameter("mainuser");
        main_username = main_username.toLowerCase();
        ArrayList<String> pointsearnedlist=new ArrayList<String>();//Arraylist that stores points earned for each user
        ArrayList<String> pointspossiblelist=new ArrayList<String>();//Arraylist that stores points possible for each user
        ArrayList<String> list=new ArrayList<String>();
        ArrayList<String> firstnamelist=new ArrayList<String>();//Arraylist  that stores friend tobe displayed
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
            String query1="SELECT * FROM WTFFriends where MAINUSERNAME='"+main_username+"'";//query that obtains the set of searched user
            st = conn.createStatement();
            rs = st.executeQuery(query1);
            String query4="SELECT * FROM WTFuser where USERNAME='"+main_username+"'";//query that obtains the set of searched user
            Statement st2 = conn.createStatement();
            ResultSet rs3 = st2.executeQuery(query4);
            if(rs3.next())
            {
                String l=rs3.getString("firstname");
                pointsearnedlist.add(rs3.getString("pointearned"));
                pointspossiblelist.add(rs3.getString("pointpossible"));
                firstnamelist.add(rs3.getString("firstname"));
            }
            while(rs.next())
            {
                String b=rs.getString("friendname");
                list.add(b);
            }
            for(String b:list)
            {
                String username=b;
                String query2="SELECT * FROM WTFuser where USERNAME='"+username+"'";//query that obtains the set of searched user
                Statement st1 = conn.createStatement();
                ResultSet rs1 = st1.executeQuery(query2);
                if(rs1.next())
                {
               String pointsearned=rs1.getString("pointearned");
                pointsearnedlist.add(pointsearned);
                 String pointspossible=rs1.getString("pointpossible");
                pointspossiblelist.add(pointspossible);
                String firstname=rs1.getString("firstname");
                firstnamelist.add(firstname);
                }
                st1.close();
                 
            }
            HashMap<String,ArrayList<String>> mapset=new HashMap<String,ArrayList<String>>();//Creates a HashMap
            mapset.put("username", firstnamelist);//Add usernamelist to HashMap
            mapset.put("pointsearned", pointsearnedlist);//Add Pointsearned lsit to HashMap
            mapset.put("pointspossible", pointspossiblelist);//Add POINTPOSSIBLE list to HashMap
            String json=new Gson().toJson(mapset);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);//Returns hashmap to the frontend
            
           }
           catch(SQLException ex)
        {
            out.print("Connection Failed!");
        }
            finally{
          try{
              st.close();//connections are closed
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
    }// </editor-fold>

}
