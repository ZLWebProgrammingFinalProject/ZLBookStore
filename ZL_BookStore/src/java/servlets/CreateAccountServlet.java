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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Customer;

/**
 *
 * @author jin3lee
 */
public class CreateAccountServlet extends HttpServlet 
{

    /**
     *
     */
    @Override
    public void init()
    {
    
    }

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
        String name = "0";
        String email = "0";
        String password = "0";
        
        username = request.getParameter("username");
        password = request.getParameter("pwd1");
        name = request.getParameter("name");
        email = request.getParameter("email");
        
        String hasConnected = "false";
        
        try
        {
            Class.forName("org.gjt.mm.mysql.Driver");
            String dbUrl = "jdbc:mysql://localhost:3306/zl";
            String dbUsername = "root";
            String dbPassword = "sesame";
            
            Connection conn =  DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            hasConnected = "true";
        }
        catch(SQLException e)
        {
            for(Throwable t:e)
            {
                t.printStackTrace();
            }
            hasConnected = "failed -> SQLException";
        } catch (ClassNotFoundException ex) { 
            Logger.getLogger(CreateAccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        // String userName, String passWord, String name, String email
        Customer customer = new Customer(username, password, name, email);
        
        String isCustomerExist = "Don't Know if it exists..";
        ///
        if(CustomerDB.customerExists(customer.getUserName()))
        {
            isCustomerExist = "CUSTOMER DOES EXIST..";
        }
        else
        {
            isCustomerExist = "DOESN'T EXIST";
        }
        
        CustomerDB.insert(customer);
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateAccountServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> Connection "+ hasConnected +"</h1>");
            out.println("<h1>Thank you for registering with us " + name + "!</h1>");
            out.println("<h1>We will send an email to " + email + " to confirm "+ username +"'s registration.</h1>");
            out.println("<h2>"+ hasConnected +"</h2>");
            out.println("<h2> sup done " + isCustomerExist + "</h2>");
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
