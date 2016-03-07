/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.CartDB;
import data.CustomerDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Cart;
import models.Customer;
import org.joda.time.LocalDateTime;
import util.CookieUtil;

/**
 *
 * @author jin3lee
 */
public class AddToCartServlet extends HttpServlet {

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
                 
        int quantity = 0;
        Date date= null;
        int Books_idProduct = 0;
        String Customer_username= "0";
        LocalDateTime dateTime = LocalDateTime.now();
        
        Cookie[] cookies = request.getCookies();
        
        String count = request.getParameter("count");
        quantity = Integer.parseInt(count);
        date = new Date(dateTime.getYear(), dateTime.getMonthOfYear(), dateTime.getDayOfMonth());
        Books_idProduct = Integer.parseInt(CookieUtil.getCookieValue(cookies, "Books_idProduct"));
        Customer_username = CookieUtil.getCookieValue(cookies, "currentUserLoggedIn");
        
        CookieUtil.deleteCookie(request, response, "Books_idProduct");
        
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
        Cart cart = new Cart(quantity, date, Books_idProduct, Customer_username);
  
        CartDB.insert(cart);
        
        response.sendRedirect("http://localhost:8080/ZL_BookStore/htmls/login.html");
        
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
