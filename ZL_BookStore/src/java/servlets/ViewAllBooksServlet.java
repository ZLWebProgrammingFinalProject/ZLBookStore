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
import models.Book;
import util.CookieUtil;

/**
 *
 * @author jin3lee
 */
public class ViewAllBooksServlet extends HttpServlet {

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
            out.println("<title>ZL BookStore - All Books</title>");
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
            

            out.println("<table border=\"1\">");
            
            out.println("<tr>");
            
            SearchByBookID(out);
            SearchByTitle(out);
            out.println("<td>Title</td>");
            out.println("<td>Author</td>");
            out.println("<td>Category</td>");
            out.println("<td>Published Year</td>");
            out.println("<td>Price</td>");
            out.println("<td>Stock</td>");
            
            out.println("</tr>");
            
            if(request.getParameter("filter") == null)
            {
                
                    filterDefault(out);
            }
            else
            {
                switch(Integer.parseInt(request.getParameter("filter")))
            {
                // displays all books
                case 1:
                    String retVal = request.getParameter("idProduct");
                    int idProduct = Integer.parseInt(retVal);
                    filter1(out, idProduct);
                    break;
                // displays a to z
                case 2:
                    filterDefault(out);
                    break;
                // displays z to a
                case 3:
                    filterDefault(out);
                    break;
                default:
                    filterDefault(out);
                    break;
            }
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
                    + "<a style=\"text-decoration:none;\" href=\"http://www.localhost:8080/ZL_BookStore/TopFiveByCategory\""
                    + "style=\"text-decoration:none;\" id=\'index_side\'>View Top 5 By Category</a><br /><br />");
            out.println("</div>");
            out.println("</html>");
        }
    }
    
    private void SearchByTitle(PrintWriter out)
    {
        out.println("<td>Title");
        out.println("<br />");
        out.println("<form action=\"http://www.localhost:8080/ZL_BookStore/ViewAllBooks\" method=\"post\">");
        out.println("<input type=\"hidden\" name=\"filter\" value=\"2\"/>");
        out.println("<input type=\"submit\" value=\"A->Z\">");
        out.println("</form>");
        out.println("<form action=\"http://www.localhost:8080/ZL_BookStore/ViewAllBooks\" method=\"post\">");
        out.println("<input type=\"hidden\" name=\"filter\" value=\"3\"/>");
        out.println("<input type=\"submit\" value=\"Z->A\">");
        out.println("</form>");
        
        out.println("</td>");
                
    }
    
    private void SearchByBookID(PrintWriter out)
    {
            out.println("<td>Book ID");
            out.println("<form action=\"http://www.localhost:8080/ZL_BookStore/ViewAllBooks\" method=\"post\">");
            out.println("<select name=\"idProduct\">\n");     
                for(int j = 1; j <= BookDB.getBookIdCount(); j++)
                {
                    out.println("<option value=\""+j+"\">"+j+"</option>");
                }
            out.println("</select>"
                    + "<input type=\"hidden\" name=\"filter\" value=\"1\"/>"
                    + "<input type=\"submit\" value=\"Search\"/>"
                    + "<form>");
            out.println("</td>");
    }
    
    // displays all books
    private void filterDefault(PrintWriter out)
    {
        ArrayList<Book> books = BookDB.getAllBooks();
        
        for(int i = 0; i < books.size(); i++)
            {
                out.println("<tr>");
                out.println("<td>"+books.get(i).getIdProduct()+"</td>");
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
    }
    
    // displays all books
    private void filter1(PrintWriter out, int idProduct)
    {
        Book books = BookDB.getBook(idProduct);
        
        out.println("<tr>");
        out.println("<td>"+books.getIdProduct()+"</td>");
        out.println("<td>"+books.getBookName()+"</td>");
        out.println("<td>"+books.getAuthor()+"</td>");
        out.println("<td>"+books.getCategory()+"</td>");
        out.println("<td>"+books.getPublishedYear()+"</td>");
        out.println("<td>"+books.getPrice()+"</td>");
        out.println("<td>"+books.getAmountInventory()+"</td>");
        out.println("<td><form action=\"http://www.localhost:8080/ZL_BookStore/AddToCart\" method=\"post\" >");
            out.println("<input type=\"hidden\" name=\"Books_idProduct\" value=\""+books.getIdProduct()+"\"\">");
            out.println("<input type=\"hidden\" name=\"count\" value=\"1\">");
            out.println("<input type=\"submit\" value=\"Add to Cart\"/>");
            out.println("</form></td>");
        out.println("</tr>");
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
