/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.google.gson.Gson;
import java.io.*;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.*;
import java.sql.DriverManager;
import java.util.*;
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
        System.out.println(main_username);
        ArrayList<String> pointsearnedlist=new ArrayList<String>();
        ArrayList<String> pointspossiblelist=new ArrayList<String>();
        ArrayList<String> list=new ArrayList<String>();
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           String connectionURL = "jdbc:derby://localhost:1527/WTFtask";//Connection to server
           try {
            Connection conn = DriverManager.getConnection(connectionURL, "IS2560","IS2560");
            Statement stmt=conn.createStatement(); 
            String query1="SELECT * FROM WTFFriends where MAINUSERNAME='"+main_username+"'";//query that obtains the set of searched user
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query1);
            // System.out.println(rs);
            while(rs.next())
            {
                //System.out.println("inside while");
                String b=rs.getString("friendname");
                //System.out.println(b);
                list.add(b);
            }
            for(String b:list)
            {
                System.out.println(b);
                String username=b;
                String query2="SELECT * FROM WTFuser where USERNAME='"+username+"'";//query that obtains the set of searched user
                Statement st1 = conn.createStatement();
                ResultSet rs1 = st1.executeQuery(query2);
                System.out.println(b);
                if(rs1.next())
                {
               String pointsearned=rs1.getString("pointearned");
                   //System.out.println(bc);
                pointsearnedlist.add(pointsearned);
                 String pointspossible=rs1.getString("pointpossible");
                 //System.out.println(pointspossible);
                pointspossiblelist.add(pointspossible);
                }
                st1.close();
                 
            }
            //System.out.println(pointsearnedlist);
            //System.out.println(pointspossiblelist);
           //request.setAttribute("username",list);
            HashMap<String,ArrayList<String>> mapset=new HashMap<String,ArrayList<String>>();
            mapset.put("username", list);
            mapset.put("pointsearned", pointsearnedlist);
            mapset.put("pointspossible", pointspossiblelist);
            String json=new Gson().toJson(mapset);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);
            
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
