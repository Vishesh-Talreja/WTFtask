/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
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
@WebServlet(urlPatterns = {"/WeeklyPointsUpdate"})
public class WeeklyPointsUpdate extends HttpServlet {

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
    public boolean JUNIT(String week,String weeklyPoints,String main_username) throws SQLException
    {
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
        Statement st2 = null;
        conn.setAutoCommit(false);
        Savepoint spt1 = conn.setSavepoint("svpt1");
        try {
           String query2 ="UPDATE WTFuser SET WEEKUPDATED='"+week+"',WEEKLYPOINTS = '"+weeklyPoints+"',WEEKLYPOINTSDONE='0' where USERNAME='"+main_username+"'";
           st2=conn.createStatement();
           int rows=st2.executeUpdate(query2);
           if(rows == 1)
           {
               conn.rollback(spt1);
               conn.commit();
               return true;
           }
           else 
           {
               return false;
           }     
        }
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
          finally{
                  try{
                      st2.close();
                      conn.close(); 
                  }
                  catch (SQLException ex) {
                       Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                  }     
          }
        
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8");
        String main_username = request.getParameter("mainuser");
        String week = request.getParameter("week");
        String weeklyPoints = request.getParameter("points");
        main_username = main_username.toLowerCase();
        String connection=null,dusername=null,dpassword=null,change_date = null, number_of_days= null;
        InputStream in = Login.class.getResourceAsStream("/config.txt");
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        try {
            String line=null;
                while((line=reader.readLine())!=null){
                    String[] arg = line.split(" ");
                    dusername = arg[0];
                    String user_arg[] = dusername.split("=");
                    dusername = user_arg[1];
                    dpassword = arg[1];
                    String pass_arg[] = dpassword.split("=");
                    dpassword = pass_arg[1];
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
            Statement st2 = null;
            try {
                conn = DriverManager.getConnection(connection,dusername,dpassword);
                String query ="Select * from WTFuser where USERNAME='"+main_username+"'";
                st = conn.createStatement();
                ResultSet newPoints = st.executeQuery(query);
                newPoints.next();
                float inputPoints = Float.parseFloat(weeklyPoints);
                float pointsDone = Float.parseFloat(newPoints.getString("WEEKLYPOINTSDONE"));
                float points = Float.parseFloat(newPoints.getString("WEEKLYPOINTS"));
                if (pointsDone>points) {
                    inputPoints = inputPoints - (pointsDone-points);
                }
                else {
                    inputPoints = inputPoints + (points -pointsDone);
                }
                weeklyPoints = Float.toString(inputPoints);
                String query1="UPDATE WTFuser SET WEEKUPDATED='"+week+"',WEEKLYPOINTS = '"+weeklyPoints+"',WEEKLYPOINTSDONE='0' where USERNAME='"+main_username+"'";
                int rows = st.executeUpdate(query1);
            }
            catch(SQLException ex) {
                out.print("Connection Failed!");
            }
            finally{
                try{
                    st.close();//connections are closed
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
