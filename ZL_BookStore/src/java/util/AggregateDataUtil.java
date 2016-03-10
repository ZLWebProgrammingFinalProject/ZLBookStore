/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import data.BookDB;
import data.TransactionDB;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import models.Transaction;

/**
 *
 * @author jin3lee
 */
public class AggregateDataUtil 
{
    
    public static ArrayList<Integer> getMonthlySales(int year)
    {
        // get this year
        ArrayList<Integer> retVal = new ArrayList<>();
        for(int i = 1; i <= 12; i++)
        {
            retVal.add(new Integer(TransactionDB.getMonthlySale(year, i)));
        }
        return retVal;
    }
    
    public static ArrayList<Integer> getWeeklySales(int month, int year)
    {
//        
        ArrayList<Integer> retVal = new ArrayList<>();
        for(int i = 1; i <= 4; i++)
        {
            retVal.add(new Integer(TransactionDB.getWeeklySale(year, month, i)));
        }
        return retVal;
    }
    
    public static ArrayList<Double> getMonthlyProfit(int year)
    {
        ArrayList<Double> retVal = new ArrayList<>();
        for(int i = 1; i <= 12; i++)
        {
            retVal.add(new Double(TransactionDB.getMonthlyProfit(year, i)));
        }
        return retVal;
    }
    
    public static ArrayList<Double> getWeeklyProfit(int month, int year)
    {
        ArrayList<Double> retVal = new ArrayList<>();
        for(int i = 1; i <= 4; i++)
        {
            retVal.add(new Double(TransactionDB.getWeeklyProfit(year, month, i)));
        }
        return retVal;
    }
    
    public static ArrayList<String> getTwoOrMorePurchaseUsers(String category)
    {
        ArrayList<String> row = new ArrayList<>();
        
        ArrayList<Transaction> transactions = TransactionDB.getTwoOrMoreUsers(category);
        
        Calendar cal = Calendar.getInstance();
        
        for(int i = 0; i <transactions.size(); i++)
        {
            cal.setTime(transactions.get(i).getDate());
            row.add(new String("<tr><td>"+transactions.get(i).getCustomer_username()+"</td>"
                    + "<td>"+BookDB.getBook(transactions.get(i).getBooks_idProduct()).getCategory()+"</td>"
                    + "<td>"+getMonthName(cal.get(Calendar.MONTH))+"</td><tr>"));
        }
        
        
        return row;
    }
    
    public static String getMonthName(int month)
    {
        String monthName = "";
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
        
        return monthName;
    }
}
