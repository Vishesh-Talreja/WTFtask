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
@WebServlet(urlPatterns = {"/New_friend"})
public class New_friend extends HttpServlet {

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
    /** This servlet is responsible for adding a searched user in add a friend modal*/
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");

        String searched_username = request.getParameter("searched_username");//Obtains searched username of the user
        String main_username = request.getParameter("mainuser");//obtains the username who is logged in
        String main_user_firstname = request.getParameter("mainuser_firstname");

       //System.out.println(user);
       try (PrintWriter out = response.getWriter()){

          
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        try{
            
            Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            String query="INSERT INTO IS2560.WTFFriends (mainusername,friendname) VALUES ( '"+main_username+"' , '"+searched_username+"' )";//Inserts into WTFFriends database
            Statement st = conn.createStatement();
            st.executeUpdate(query);

            Statement st1 = conn.createStatement();
            String query2 = "SELECT * FROM IS2560.WTFuser WHERE username='"+searched_username+"'";
            ResultSet rs = st1.executeQuery(query2);
            boolean is = rs.next();
            String searched_user_firstname = rs.getString("Firstname");
            System.out.println(searched_user_firstname);
            request.setAttribute("FName",searched_user_firstname);//set the attribute feild fname to searched_user_firstname to be accessed from jsp
            request.setAttribute("Name",main_user_firstname);//set the attribute feild Name to main_user_firstname to be accessed from jsp
            request.setAttribute("username",main_username);//set the attribute feild username to main_username to be accessed from jsp

            request.setAttribute("added_friend","true");

            RequestDispatcher rd=request.getRequestDispatcher("user_home.jsp");//Loads the main page after friend has been added 

            rd.forward(request, response);
            rs.close();
            st.close();
            conn.close();//connection closed
            
        }
        catch(SQLException ex)
        {
            response.getWriter().write("false");
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
