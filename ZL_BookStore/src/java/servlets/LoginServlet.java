/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.CustomerDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Customer;

/**
 *
 * @author jin3lee
 */
public class LoginServlet extends HttpServlet {

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
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        
        String username = "0";
        String password = "0";
        
        username = request.getParameter("username");
        password = request.getParameter("password");
        
        
        try
        {
            Class.forName("org.gjt.mm.mysql.Driver");
            String dbUrl = "jdbc:mysql://localhost:3306/zl";
            String dbUsername = "root";
            String dbPassword = "sesame";
            
            Connection conn =  DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        }
        catch(SQLException e)
        {
            for(Throwable t:e)
            {
                t.printStackTrace();
            }
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // initialize values
        String isCustomerExist = "Can't login??? wtf";
        boolean isValidated = false;
        
        ///
        if(CustomerDB.validateLogin(username, password))
        {
            isCustomerExist = "You exist and have logged in!!!! SUCCESS!";
            isValidated = true;
            
            // create cookie and store username
            Cookie c = new Cookie("currentUserLoggedIn", username);
            c.setMaxAge(60*30);    // removes the username when user closes the browser
            c.setPath("/");
            response.addCookie(c);
        }
        else
        {
            isCustomerExist = "FAILED TO LOGIN!";
        }
        
        
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            if(isValidated)
            {
                out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://www.localhost:8080/ZL_BookStore/MyAccount\" />");
                out.println("<h1>SUCCESSFULL Login! :)</h1>");
            }
            else
            {
               out.println("<meta http-equiv=\"refresh\" content=\"0; url=http://localhost:8080/ZL_BookStore/htmls/login.html\" />");
               out.println("<h1>FAILED Login! >:/</h1>");                 
            }
            
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
