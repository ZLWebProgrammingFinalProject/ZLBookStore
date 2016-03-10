/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.CustomerDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.AggregateDataUtil;
import util.CookieUtil;

/**
 *
 * @author jin3lee
 */
public class AdminStatisticsServlet extends HttpServlet {

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
        
//        Cookie[] cookies = request.getCookies();
//        String Customer_username = CookieUtil.getCookieValue(cookies, "currentUserLoggedIn");
        
        //if(!CustomerDB.isAdmin(Customer_username))
        if(false)
        {
            response.sendRedirect("http://localhost:8080/ZL_BookStore/htmls/adminLogin.html");
        }
        else
        {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>ZL ADMIN</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet AdminStatisticsServlet</h1>");
                
                out.println("<a href=\"#\">Monthly Sales, Profit, & Growth</a>");
                out.println("<br />");
                out.println("<a href=\"#\">Weekly Sales, Profit, Growth</a>");
                
                out.println("<br />");
                out.println("<br />");
                
                out.println("<H1>Monthly Sales, Profit, & Growth during " + 2016 + "</H1>");
                out.println("<br />");
                
                
                
            out.println("<table border=\"1\">");
            
            out.println("<tr>");
            out.println("<td>Month</td>");
            out.println("<td>Sales</td>");
            out.println("<td>Profit</td>");
            out.println("<td>Growth</td>");
            out.println("</tr>");
            
                ArrayList<Integer> salesList = AggregateDataUtil.getMonthlySales(2016);
                ArrayList<Double> profitList = AggregateDataUtil.getMonthlyProfit(2016);
                ArrayList<Double> differenceList = AggregateDataUtil.getMonthlyProfit(2016);
                
                for(int i = 0; i <= salesList.size()+1; i++)
                {
                    out.println("<tr>");
                    int month = i+1;
                    out.println("<td>"+month+"</td><td>" + salesList.get(i).toString() + "</td>");
                    
                    DecimalFormat formatter;
                    formatter = new DecimalFormat("##.##");
                    
                    out.println("<td>" + formatter.format(profitList.get(i)) + "</td>");
                    double difference;
                    if(i != 0)
                    {
                        difference = differenceList.get(i) - differenceList.get(i-1);
                    }
                    else
                    {
                        difference = 0;
                    }
                    out.println("<td>" + formatter.format(difference) + "</td>");
                    out.println("</tr>");
                }
                
                out.println("</body>");
                out.println("</html>");
            }
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