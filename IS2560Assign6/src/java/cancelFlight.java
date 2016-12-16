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
@WebServlet(urlPatterns = {"/cancelFlight"})
public class cancelFlight extends HttpServlet {
    private Connection conn;
    private Statement st;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        int bookingNo = Integer.parseInt(request.getParameter("bookingNo"));
        try (PrintWriter out = response.getWriter()) {
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String connectionURL = "jdbc:derby://localhost:1527/RachelChang";
                conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
                st = conn.createStatement();
                //delete record from FLIGHTBOOKING table
                String q1 = new String("DELETE FROM FLIGHTBOOKING WHERE BOOKINGNO="+bookingNo);
                st.executeUpdate(q1);
            }        
            catch (SQLException se)
            {
                se.printStackTrace();  
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(cancelFlight.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Cancel flight</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Flight with booking number " + bookingNo + " cancelled.</h1>");
            out.println("<p>Thank you for using Something Airlines, we look forward to your next flight!</p>");
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
