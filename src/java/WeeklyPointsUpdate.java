/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8");
           String main_username = request.getParameter("mainuser");
           String week = request.getParameter("week");
           String isNewWeek = request.getParameter("isNewWeek");
           String weeklyPoints = request.getParameter("points");
           //System.out.println(duedate);
           main_username = main_username.toLowerCase();
           String connection=null,dusername=null,password=null;
        InputStream in = Login.class.getResourceAsStream("/config.txt");
        BufferedReader reader=new BufferedReader(new InputStreamReader(in));
        try {
            
            String line=null;
            System.out.println("sam");
                while((line=reader.readLine())!=null){
                    String[] arg = line.split(" ");
                    dusername = arg[0];
                    password = arg[1];
                    connection = arg[2];
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
           conn = DriverManager.getConnection(connection,dusername,password);
           String query ="Select * from WTFuser where USERNAME='"+main_username+"'";
           st = conn.createStatement();
           ResultSet newPoints = st.executeQuery(query);
           newPoints.next();
           float inputPoints = Float.parseFloat(weeklyPoints);
           float pointsDone = Float.parseFloat(newPoints.getString("WEEKLYPOINTSDONE"));
           float points = Float.parseFloat(newPoints.getString("WEEKLYPOINTS"));
           if (pointsDone>points)
           {
               inputPoints = inputPoints - (pointsDone-points);
           }
           else
           {
               inputPoints = inputPoints + (points -pointsDone);
           }
           weeklyPoints = Float.toString(inputPoints);
           String query1="UPDATE WTFuser SET WEEKLYPOINTS = '"+weeklyPoints+"',WEEKUPDATED='"+week+"',WEEKLYPOINTSDONE='0' where USERNAME='"+main_username+"'";
           st.executeUpdate(query1);

           }
             catch(SQLException ex)
        {
            out.print("Connection Failed!");
        }
            finally{
          try{
              st.close();//connections are closed
              //rs.close();
             conn.close(); 
            // RequestDispatcher rm=request.getRequestDispatcher("user_home_new.jsp");
            // rm.forward(request, response);
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
