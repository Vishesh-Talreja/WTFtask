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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author visheshtalreja
 */
@WebServlet(urlPatterns = {"/Complete_Task"})
public class Complete_Task extends HttpServlet {

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
        String user="vtalreja";
        user = user.toLowerCase();
        String pass="firewaterthunder";
        String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
        Connection conn;
        try {
            conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            String query1 = "SELECT * FROM WTFuser where username = '"+user+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            boolean flag1 = rs.next();
            st.close();
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
        processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        String Tname=request.getParameter("Tname");
        String Tpoints=request.getParameter("Tpoints");
        String name=request.getParameter("Name").replaceAll(" ","");
        name = name.toLowerCase();
        String user=request.getParameter("user").replaceAll(" ","");
        user = user.toLowerCase();
        Connection conn=null;
        Statement stmt=null;
        ResultSet rs = null;
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String connectionURL = "jdbc:derby://localhost:1527/WTFtask";
           try{
            conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            stmt=conn.createStatement();
            String query1 = "SELECT * FROM IS2560.WTFtasks WHERE TASKNAME='"+Tname+"'";
            rs = stmt.executeQuery(query1);                               //Extract taskId
            rs.next();
            int id = rs.getInt("TaskID");
            String query2 = "SELECT * FROM IS2560.WTFuser WHERE USERNAME='"+user+"'";
            rs = stmt.executeQuery(query2);
            rs.next();
            int TpointsBefore=Integer.parseInt(rs.getString("POINTEARNED"));
            int TpointsNow = Integer.parseInt(Tpoints);
            TpointsNow =TpointsNow+TpointsBefore;
            String NewTpoints = Integer.toString(TpointsNow);
            String query3 = "UPDATE IS2560.WTFuser SET POINTEARNED = '"+NewTpoints+"' WHERE USERNAME ='"+user+"'";
            stmt.executeUpdate(query3);
            String query = "UPDATE IS2560.WTFtaskallocation SET STATUS = 'Complete' WHERE USERNAME ='"+user+"' and TASKID="+id;
            stmt.executeUpdate(query);
            stmt.close();
            conn.close();
            request.setAttribute("Name", name);
            request.setAttribute("username",user);
            RequestDispatcher rd=request.getRequestDispatcher("user_home.jsp");
            rd.forward(request, response);
           }
           catch(SQLException ex)
           {
             out.print(ex+"Connection Failed!");
           }
           finally
           {
                try {
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Complete_Task.class.getName()).log(Level.SEVERE, null, ex);
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
