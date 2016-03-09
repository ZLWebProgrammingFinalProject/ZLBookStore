/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.BookDB;
import data.CartDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Cart;
import util.CookieUtil;

/**
 *
 * @author jin3lee
 */
public class MyCartServlet extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ZL BookStore - Search Results</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://localhost:8080/ZL_BookStore/styles/lzStyles.css\">");
            out.println("</head>");
    
            out.println("<div class=\"top\">");
            out.println("<p id=\"companyName\">ZL Book Store</p> ");
            out.println("<div id=\"header\">");
            out.println("<form action=\"http://www.localhost:8080/ZL_BookStore/Search\" method=\"post\" >");
            out.println("<input name=\"searchWords\" type=\"search\" placeholder=\"Book name\"/>");
            out.println("<select name=\"books\">");
            out.println("<option value=\"all\" selected=\"selected\">all</option>");
            out.println("<option value=\"Education\">Education</option>");
            out.println("<option value=\"SciFi\">SciFi</option>");
            out.println("<option value=\"Romance\">Romance</option>");
            out.println("<option value=\"Classic\">Classic</option>");
            out.println("<option value=\"Kids\">Kids</option>");
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"Search\"/>");
            out.println("</form>");
            out.println("<br />");
            out.println("</div>");

            out.println("<div id=\"user\">");
            out.println("<a href=\"http://www.localhost:8080/ZL_BookStore/htmls/CreateAccount.html\" id=\"createAccount\">Create new account</a> |");
            out.println("<a href=\"http://www.localhost:8080/ZL_BookStore/htmls/login.html\" id=\"login\" >Login</a><br/>");
            out.println("</div>");

            out.println("<div id=\"myAccount\">");
            out.println("<a href=\"http://www.localhost:8080/ZL_BookStore/redirect.jsp\" id=\"homePage\">Home page</a> |");
            out.println("<a href=\"http://www.localhost:8080/ZL_BookStore/MyAccount\" id=\"account\">My account</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"main\">");
            
            Cookie[] cookies = request.getCookies();
            String Customer_username = CookieUtil.getCookieValue(cookies, "currentUserLoggedIn");
            
            ArrayList<Cart> carts = CartDB.getCart(Customer_username);
            
            
            out.println("<table border=\"1\">");
            
            out.println("<tr>");
            out.println("<td>Book ID</td>");
            out.println("<td>Title</td>");
            out.println("<td>Date</td>");
            out.println("<td>Quantity</td>");
            out.println("</tr>");
            for(int i = 0; i < carts.size(); i++)
            {
                out.println("<tr>");
                out.println("<td>"+carts.get(i).getBooks_idProduct()+"</td>");
                out.println("<td>"+BookDB.getBook(carts.get(i).getBooks_idProduct()).getBookName()+"</td>");
                out.println("<td>"+carts.get(i).getDate()+"</td>");
                out.println("<td>"+carts.get(i).getQuantity()+"</td>");
                out.println("</tr>");   
            }
            out.println("</table>");
            
            out.println("<form action=\"ConfirmPurchase\">\n  <input type=\"submit\" value=\"Purchase\">\n</form> ");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"left\">");
            out.println("<a href=\"http://www.localhost:8080/ZL_BookStore/MyCart\" id=\\\"myCart\\\">myCart</a><hr/>"
                    + "<a href=\"http://www.localhost:8080/ZL_BookStore/MyOrder\" id=\\\"myCart\\\">myOrder</a><hr/>"
                    + "<a href=\"http://www.localhost:8080/ZL_BookStore/LogOut\" id=\\\"myCart\\\">logOut</a><hr/>");
            out.println("</div>");
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
