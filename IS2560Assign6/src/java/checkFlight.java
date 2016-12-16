/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Rachel
 */
@WebServlet(urlPatterns = {"/checkFlight"})
public class checkFlight extends HttpServlet {
    private Connection conn;
    private Statement st;
    private ResultSet rs=null;
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
        //int passId = Integer.parseInt(request.getParameter("passId"));
        String passId=request.getParameter("passId");
        try (PrintWriter out = response.getWriter()) {
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String connectionURL = "jdbc:derby://localhost:1527/RachelChang";
                conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
                st = conn.createStatement();
                //get all flights belonging to particular passenger
                String q1 = new String("SELECT FB.BOOKINGNO AS BOOKING_NO, FB.FLIGHTNO AS FLIGHT_NO, FB.DATE AS FLIGHT_DATE"
                        +", F.DESTINATION AS FLIGHT_DEST, F.DEPARTURE AS FLIGHT_DEPART, F.DEPARTTIME AS DEPART_TIME"+
                        " FROM FLIGHTBOOKING FB"+", FLIGHT F"+
                        " WHERE FB.FLIGHTNO=F.FLIGHTNO AND FB.PASSENGERID="+passId
                );     
                rs =  st.executeQuery(q1);
            }        
            catch (SQLException se)
            {
                se.printStackTrace();  
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(checkFlight.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /* output table containing flights of a passenger*/
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet checkFlight</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Following flights could be found:</h1>");
            out.println("<table><tr><th>Booking No.</th><th>Flight Date</th><th>Flight No.</th><th>Departing from</th><th>Destination</th><th>Departing time</th><th></th></tr>");            
            try{
                while(rs.next())
                {
                    out.println("<tr><td>"
                    +rs.getString("BOOKING_NO")+"</td><td>"
                    +rs.getString("FLIGHT_DATE")+"</td><td>"       
                    +rs.getString("FLIGHT_NO")+"</td><td>"
                    +rs.getString("FLIGHT_DEPART")+"</td><td>"
                    +rs.getString("FLIGHT_DEST")+"</td><td>"
                    +rs.getString("DEPART_TIME")+"</td>"
                    +"<td><a href=\"cancelFlight?bookingNo="+rs.getString("BOOKING_NO")+"\">Cancel this flight!</a></td>");
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
