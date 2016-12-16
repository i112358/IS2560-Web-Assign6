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
@WebServlet(urlPatterns = {"/bookFlight"})
public class bookFlight extends HttpServlet {

    private Connection conn;
    private Statement st, st2;
    private ResultSet rs=null;
   /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int passId = Integer.parseInt(request.getParameter("passengerId"));
        int flightNo = Integer.parseInt(request.getParameter("flightNo"));
        String date = request.getParameter("flightDate");
        try (PrintWriter out = response.getWriter()) {
            /* Insert new record and get the details of that flight*/
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String connectionURL = "jdbc:derby://localhost:1527/RachelChang";
                conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
                st = conn.createStatement();
                String q1 = new String("INSERT INTO FLIGHTBOOKING (PASSENGERID, FLIGHTNO, DATE)"+
                " VALUES ("+passId+", "+flightNo+", '"+date+"')"
                );
                st2 = conn.createStatement();
                String q2 = new String("SELECT FLIGHTNO, DESTINATION, DEPARTURE, DEPARTTIME, DURATION FROM FLIGHT WHERE FLIGHTNO="+flightNo);
                st.executeUpdate(q1);
                rs = st2.executeQuery(q2);
            }        
            catch (SQLException se)
            {
                se.printStackTrace();  
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(bookFlight.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Book a Flight</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Booking for flight number " + request.getParameter("flightNo")+ " successful.</h1>");
            out.println("<h3>Your itinerary:</h3>");
            out.println("<ul style=\"list-style-type:none\">");
            out.println("<li>Flight Date: "+date+"</li>");
            try{
                while(rs.next())
                {
                    out.println(
                    "<li>Departing Time: "+rs.getString("DEPARTTIME")+"</li>"+
                    "<li>Departing from: "+rs.getString("DEPARTURE")+"</li>"+
                    "<li>Heading for: "+rs.getString("DESTINATION")+"</li>"+
                    "<li>Total time of flight: "+rs.getString("DURATION")+" hrs</li>"
                    );
                }
            }
            catch(SQLException sqle){
                sqle.printStackTrace();  
            }
            out.println("</ul>");
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
