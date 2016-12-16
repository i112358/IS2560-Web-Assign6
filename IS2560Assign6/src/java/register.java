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
@WebServlet(urlPatterns = {"/register"})
public class register extends HttpServlet {

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
        String firstName=request.getParameter("firstName");
        String lastName=request.getParameter("lastName");
        String birthDate=request.getParameter("birthDate");
        String gender= request.getParameter("gender");
        String address=request.getParameter("address");
        String q1;
        int autoIncKeyFromApi = -1;
        try (PrintWriter out = response.getWriter()) {
            try{
                Class.forName("org.apache.derby.jdbc.ClientDriver");
                String connectionURL = "jdbc:derby://localhost:1527/RachelChang";
                conn = DriverManager.getConnection(connectionURL, "IS2560", "IS2560");
                st = conn.createStatement();
                if(address!=null)
                {
                    q1 = new String("INSERT INTO PASSENGER (FIRSTNAME, LASTNAME, BIRTHDATE, GENDER, ADDRESS)"+
                        " VALUES ('"+firstName+"', '"+lastName+"', '"+birthDate+"', '"+gender+"', '"+address+"')"
                    );
                }
                else{
                    q1 = new String("INSERT INTO PASSENGER (FIRSTNAME, LASTNAME, BIRTHDATE, GENDER)"+
                        " VALUES ('"+firstName+"', '"+lastName+"', '"+birthDate+"', '"+gender+"')"
                    );
                }                
                st.executeUpdate(q1,Statement.RETURN_GENERATED_KEYS);
                
                rs = st.getGeneratedKeys();
                if (rs.next()) {
                    autoIncKeyFromApi = rs.getInt(1);
                }
            }        
            catch (SQLException se)
            {
                se.printStackTrace();  
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(bookFlight.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Successful register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Hi " +firstName+ ", welcome to Something Airlines.</h1>");
            out.println("<p>Your Passenger ID is: \"" +autoIncKeyFromApi+ "\" <br />This ID number is VERY IMPORTANT, please remember it. You will book flights or edit personal information with this number.</p>");
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
