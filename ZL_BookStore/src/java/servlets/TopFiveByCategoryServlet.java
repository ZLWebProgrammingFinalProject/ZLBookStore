/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.BookDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Book;

/**
 *
 * @author jin3lee
 */
public class TopFiveByCategoryServlet extends HttpServlet {

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
            
        String category = "Romance";
        if(request.getParameter("category") == null)
        {
            category = "Romance";
        }
        else
        {
            category = request.getParameter("category");
        }
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ZL BookStore - Top 10 Best Sellers</title>");
            out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"http://localhost:8080/ZL_BookStore/styles/lzStyles.css\">");
            out.println("</head>");
    
            out.println("<div class=\"top\">");
            out.println("<p id=\"companyName\">ZL Book Store</p> ");
            out.println("<div id=\"header\">");
            out.println("<form action=\"http://www.localhost:8080/ZL_BookStore/Search\" method=\"post\" >");
            out.println("<input name=\"searchWords\" type=\"search\" placeholder=\"Book name\"/>");
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
            
            ArrayList<Book> books = BookDB.getFiveByCategory(category);
            
            
            out.println("<table border=\"1\">");
            
            out.println("<tr>");
            out.println("<td><a href=\"http://www.localhost:8080/ZL_BookStore/TopFiveByCategory?category=Romance\">Romance</a></td>");
            out.println("<td><a href=\"http://www.localhost:8080/ZL_BookStore/TopFiveByCategory?category=Classic\">Classic</a></td>");
            out.println("<td><a href=\"http://www.localhost:8080/ZL_BookStore/TopFiveByCategory?category=Scifi\">Scifi</a></td>");
            out.println("<td><a href=\"http://www.localhost:8080/ZL_BookStore/TopFiveByCategory?category=Kids\">Kids</a></td>");
            out.println("<td><a href=\"http://www.localhost:8080/ZL_BookStore/TopFiveByCategory?category=Education\">Education</a></td>");
            out.println("<td><a href=\"http://www.localhost:8080/ZL_BookStore/TopFiveByCategory?category=Music\">Music</a></td>");
            out.println("</tr>");
            out.println("</table>");
            
            
            out.println("<br />");
            out.println("<h1> Top 5 Best Sellers of "+category+"</h1>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<td>Rank</td>");
            out.println("<td>Title</td>");
            out.println("<td>Author</td>");
            out.println("<td>Category</td>");
            out.println("<td>Published Year</td>");
            out.println("<td>Price</td>");
            out.println("<td>Stock</td>");
            out.println("</tr>");
            for(int i = 0; i < books.size(); i++)
            {
                int rank = i+1;
                out.println("<tr>");
                out.println("<td>"+rank+"</td>");
                out.println("<td>"+books.get(i).getBookName()+"</td>");
                out.println("<td>"+books.get(i).getAuthor()+"</td>");
                out.println("<td>"+books.get(i).getCategory()+"</td>");
                out.println("<td>"+books.get(i).getPublishedYear()+"</td>");
                out.println("<td>"+books.get(i).getPrice()+"</td>");
                out.println("<td>"+books.get(i).getAmountInventory()+"</td>");
                out.println("<td><form action=\"http://www.localhost:8080/ZL_BookStore/AddToCart\" method=\"post\" >");
                    out.println("<input type=\"hidden\" name=\"Books_idProduct\" value=\""+books.get(i).getIdProduct()+"\"\">");
                    out.println("<input type=\"hidden\" name=\"count\" value=\"1\">");
                    out.println("<input type=\"submit\" value=\"Add to Cart\"/>");
                    out.println("</form></td>");
                out.println("</tr>");
            }
            out.println("</table>");
            
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"left\">");
            
            out.println("<br />"
                    + "<a style=\"text-decoration:none;\" href=\"http://www.localhost:8080/ZL_BookStore/ViewAllBooks\""
                    + " style=\"text-decoration:none;\" id=\'index_side\'>View All Books</a>"
                    + "<br />"
                    + "<br />"
                    + "<a style=\"text-decoration:none;\" href=\"http://www.localhost:8080/ZL_BookStore/TopTenBooks\""
                    + "style=\"text-decoration:none;\" id=\'index_side\'>View Top 10 Best Sellers</a>"
                    + "<br />"
                    + "<br />"
                    + "<a style=\"text-decoration:none;\" href=\"http://www.localhost:8080/ZL_BookStore/TopFiveByCategory\" "
                    + "style=\"text-decoration:none;\" id=\'index_side\'>View Top 5 By Category</a><br /><br />");
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
