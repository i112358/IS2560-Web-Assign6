/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rachel
 */
public class searchFlight extends HttpServlet {
     private Connection conn;
     private Statement st;
     private ResultSet rs=null;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String connectionURL = "jdbc:derby://localhost:1527/RachelChang";
                conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
                st = conn.createStatement();
                //search for all available flights
                String q1 = new String("SELECT * FROM FLIGHT");
                rs =  st.executeQuery(q1);
            }        
            catch (SQLException se)
            {
                se.printStackTrace();  
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(searchFlight.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Search available flights</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Following flights could be found:</h1>");
            out.println("<table><tr><th>Flight No.</th><th>Departing from</th><th>Destination</th><th>Departing time</th><th>Flight duration</th><th></th></tr>");
            try{
                while(rs.next())
                {
                    out.println("<tr><td>"+
                    rs.getString("FLIGHTNO")+"</td><td>"
                    +rs.getString("DEPARTURE")+"</td><td>"
                    +rs.getString("DESTINATION")+"</td><td>"
                    +rs.getString("DEPARTTIME")+"</td><td>"
                    +rs.getString("DURATION")+" Hrs</td>"
                    +"<td><a href=\"bookingForm.jsp?flightNo="+rs.getString("FLIGHTNO")+"\">Book this flight!</a></td>");
                    out.println("</tr>");
                }
            }
            catch(SQLException sqle){
                sqle.printStackTrace();  
            }
            out.println("</table>");
            out.println("<a href=\"index.jsp\">Return to top page</a>");
            out.println("</body>");
            out.println("</html>");
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
