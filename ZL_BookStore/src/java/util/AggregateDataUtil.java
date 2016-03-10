/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import data.TransactionDB;
import java.sql.Date;
import java.util.ArrayList;

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
//        for(int i = 1; i <= 12; i++)
//        {
//            retVal.add(new Integer(TransactionDB.getMonthlySale(year, i)));
//        }
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
    
    public static ArrayList<Integer> getWeeklyProfit(int month, int year)
    {
        ArrayList<Integer> retVal = new ArrayList<>();
        
        return retVal;
    }
    
    
    public static ArrayList<Integer> getMonthlyDifference(int year)
    {
        ArrayList<Integer> retVal = new ArrayList<>();
        
        return retVal;
    }
    
    public static ArrayList<Integer> getWeeklyDifference(int month, int year)
    {
        ArrayList<Integer> retVal = new ArrayList<>();
        
        return retVal;
    }
    
}
