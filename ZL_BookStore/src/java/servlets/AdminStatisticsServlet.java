/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import data.CustomerDB;
import data.TransactionDB;
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
                
                out.println("<a href=\"http://www.localhost:8080/ZL_BookStore/AdminStatistics?format=month\">Monthly Sales, Profit, & Growth</a>");
                out.println("<br />");
                out.println("<a href=\"http://www.localhost:8080/ZL_BookStore/AdminStatistics?format=week\">Weekly Sales, Profit, Growth</a>");
                
                out.println("<br />");
                out.println("<br />");
                
                boolean doWeek = false;
                if(request.getParameter("format").equals("week"))
                {
                    doWeek = true;
                }
                
                if(doWeek)
                {
                    getWeeklyStatistics(out, request);
                }
                else
                {
                    getMonthlyStatistics(out, request);
                }
                
                
                out.println("</body>");
                out.println("</html>");
            }
        }
        
    }

    public void getMonthlyStatistics(PrintWriter out, HttpServletRequest request)
    {
        int year = 2016;
        
        if(request.getParameter("theYear") != null)
        {
            year = Integer.parseInt(request.getParameter("theYear"));
        }
        
         out.println("<form action=\"http://www.localhost:8080/ZL_BookStore/AdminStatistics\" method=\"post\">");
            out.println("<select name=\"theYear\">\n");     
                for(int j = TransactionDB.getRangeOfYear().get(0); j <= TransactionDB.getRangeOfYear().get(1); j++)
                {
                    out.println("<option value=\""+j+"\">"+j+"</option>");
                }
            out.println("</select>");
            out.println("<input type=\"hidden\" name=\"format\" value=\"month\"/>");
            out.println("<input type=\"hidden\" name=\"filter\" value=\"1\"/>"
            + "<input type=\"submit\" value=\"Search\"/>"
            + "</form>");
        
            out.println("<H1>Monthly Sales, Profit, & Growth during " + year + "</H1>");
        
        out.println("<br />");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<td>Month</td>");
        out.println("<td>Sales</td>");
        out.println("<td>Profit</td>");
        out.println("<td>Growth</td>");
        out.println("</tr>");

        ArrayList<Integer> salesList = AggregateDataUtil.getMonthlySales(year);
        ArrayList<Double> profitList = AggregateDataUtil.getMonthlyProfit(year);
        ArrayList<Double> differenceList = AggregateDataUtil.getMonthlyProfit(year);

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
    }
    
     public void getWeeklyStatistics(PrintWriter out, HttpServletRequest request)
    {
        int year = 2016;
        int month = 3;
        String monthName = "March";
        
        if(request.getParameter("theMonth") != null)
        {
            month = Integer.parseInt(request.getParameter("theMonth"));
        }
        
        switch(month)
        {
            case 1:
                monthName = "January";
                break;
            case 2:
                monthName = "Feburary";
                break;
            case 3:
                monthName = "March";
                break;
            case 4:
                monthName = "April";
                break;
            case 5:
                monthName = "May";
                break;
            case 6:
                monthName = "June";
                break;
            case 7:
                monthName = "July";
                break;
            case 8:
                monthName = "August";
                break;
            case 9:
                monthName = "September";
                break;
            case 10:
                monthName = "October";
                break;
            case 11:
                monthName = "November";
                break;
            case 12:
                monthName = "December";
                break;
        }
        
         out.println("<form action=\"http://www.localhost:8080/ZL_BookStore/AdminStatistics\" method=\"post\">");
            out.println("<select name=\"theMonth\">\n");     
                    out.println("<option value=\""+"1"+"\">Week "+"January"+"</option>");
                    out.println("<option value=\""+"2"+"\">Week "+"Feburary"+"</option>");
                    out.println("<option value=\""+"3"+"\">Week "+"March"+"</option>");
                    out.println("<option value=\""+"4"+"\">Week "+"April"+"</option>");
                    out.println("<option value=\""+"5"+"\">Week "+"May"+"</option>");
                    out.println("<option value=\""+"6"+"\">Week "+"June"+"</option>");
                    out.println("<option value=\""+"7"+"\">Week "+"July"+"</option>");
                    out.println("<option value=\""+"8"+"\">Week "+"August"+"</option>");
                    out.println("<option value=\""+"9"+"\">Week "+"September"+"</option>");
                    out.println("<option value=\""+"10"+"\">Week "+"October"+"</option>");
                    out.println("<option value=\""+"11"+"\">Week "+"November"+"</option>");
                    out.println("<option value=\""+"12"+"\">Week "+"December"+"</option>");
            out.println("</select>");
            out.println("<input type=\"hidden\" name=\"format\" value=\"week\"/>");
            out.println("<input type=\"hidden\" name=\"filter\" value=\"1\"/>"
            + "<input type=\"submit\" value=\"Search\"/>"
            + "</form>");
        
            out.println("<H1>Weekly Sales, Profit, & Growth during Week " + monthName + "</H1>");
        
        out.println("<br />");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<td>Week</td>");
        out.println("<td>Sales</td>");
        out.println("<td>Profit</td>");
        out.println("<td>Growth</td>");
        out.println("</tr>");

        ArrayList<Integer> salesList = AggregateDataUtil.getWeeklySales(month, year);
        ArrayList<Double> profitList = AggregateDataUtil.getWeeklyProfit(month, year);
        ArrayList<Double> differenceList = AggregateDataUtil.getWeeklyProfit(month, year);

        for(int i = 0; i <= salesList.size()+1; i++)
        {
            out.println("<tr>");
            int week = i+1;
            out.println("<td>"+week+"</td><td>" + salesList.get(i).toString() + "</td>");

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
