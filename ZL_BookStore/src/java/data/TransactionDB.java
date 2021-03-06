/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import models.Book;
import models.Cart;
import models.Transaction;
import util.DBUtil;

/**
 *
 * @author jin3lee
 */
public class TransactionDB 
{
    public static int insert(Transaction transaction)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO Transactions(dateOfTransaction, price, quantity, Customer_username, Books_idProduct, category)"
                +" VALUE(?,?,?,?,?,?)";
        try {
            
            String test = "";
            
            int idProduct = transaction.getBooks_idProduct();
            Book book = BookDB.getBook(idProduct);
            String category = ""+book.getCategory();
            
            ps = connection.prepareStatement(query);
            ps.setDate(1, transaction.getDate());
            ps.setDouble(2, transaction.getPrice());
            ps.setInt(3, transaction.getQuantity());
            ps.setString(4, transaction.getCustomer_username());
            ps.setInt(5, transaction.getBooks_idProduct());
            ps.setString(6, category);
            
            return ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static void insert(ArrayList<Cart> carts)
    {
        for(int i = 0; i < carts.size(); i++)
        {
            Cart cart = carts.get(i);
            //Date date, double price, int quantity, String Customer_username, int Books_idProduct
            TransactionDB.insert(
                    new Transaction(
                        cart.getDate(), 
                        BookDB.getBook(cart.getBooks_idProduct()).getPrice() * cart.getQuantity(), 
                        cart.getQuantity(), 
                        cart.getCustomer_username(), 
                        cart.getBooks_idProduct(),
                        BookDB.getBook(cart.getBooks_idProduct()).getCategory()
                ));
        }
    }
    
    public static void insert(Cart cart)
    {
        Cart aCart = cart;
        Date a = aCart.getDate();
        
        //Date date, double price, int quantity, String Customer_username, int Books_idProduct
        TransactionDB.insert(
                new Transaction(
                        cart.getDate(), 
                        BookDB.getBook(cart.getBooks_idProduct()).getPrice() * cart.getQuantity(), 
                        cart.getQuantity(), 
                        cart.getCustomer_username(), 
                        cart.getBooks_idProduct(),
                        BookDB.getBook(cart.getBooks_idProduct()).getCategory()
                ));

    }
    
    public static ArrayList<Transaction> getTransactions(String Customer_username)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Transactions "
                + "WHERE Customer_username = ?";
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, Customer_username);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                //(int idProduct, double price, String category, String author,
                //int publishedYear, int amountInventory, String bookName) 
                int idTransaction = rs.getInt("idTransactions");
                Date date = rs.getDate("dateOfTransaction");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String username = rs.getString("Customer_username");
                int Books_idProduct = rs.getInt("Books_idProduct");
                
                transactions.add(
                    new Transaction(
                        idTransaction,
                            date,
                            price,
                            quantity,
                            username,
                            Books_idProduct,
                            BookDB.getBook(Books_idProduct).getCategory()
                    )
                );
                
            }
                
            return transactions;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return transactions;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    
    public static int getMonthlySale(int year, int month)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        int sales = 0;
        
        String query = "SELECT *\n" +
                        "FROM Transactions\n" +
                        "WHERE dateOfTransaction >= '"+year+"/"+month+"/01'\n" +
                        "AND dateOfTransaction <= '"+year+"/"+month+"/31'";
        
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            
            while(rs.next())
            {
                //(int idProduct, double price, String category, String author,
                //int publishedYear, int amountInventory, String bookName) 
                sales += rs.getInt("quantity");
            }
                
            return sales;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return sales;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    
    public static int getWeeklySale(int year, int month, int week)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        int sales = 0;
        
        int dayStart = 0;
        int dayEnd = 0;
        switch(week)
        {
            case 1:
                dayStart = 1;
                dayEnd = 7;
                break;
            case 2:
                dayStart = 8;
                dayEnd = 15;
                break;
            case 3:
                dayStart = 16;
                dayEnd = 23;
                break;
            case 4:
                dayStart = 24;
                dayEnd = 31;
                break;
        }
        
        String query = "SELECT *\n" +
                        "FROM Transactions\n" +
                        "WHERE dateOfTransaction >= '"+year+"/"+month+"/"+dayStart+"'\n" +
                        "AND dateOfTransaction <= '"+year+"/"+month+"/"+dayEnd+"'";
        
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            
            while(rs.next())
            {
                //(int idProduct, double price, String category, String author,
                //int publishedYear, int amountInventory, String bookName) 
                sales += rs.getInt("quantity");
            }
                
            return sales;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return sales;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static double getWeeklyProfit(int year, int month, int week)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        Double profit = 0.0;
        
        int dayStart = 0;
        int dayEnd = 0;
        switch(week)
        {
            case 1:
                dayStart = 1;
                dayEnd = 7;
                break;
            case 2:
                dayStart = 8;
                dayEnd = 15;
                break;
            case 3:
                dayStart = 16;
                dayEnd = 23;
                break;
            case 4:
                dayStart = 24;
                dayEnd = 31;
                break;
        }
        
        String query = "SELECT *\n" +
                        "FROM Transactions\n" +
                        "WHERE dateOfTransaction >= '"+year+"/"+month+"/"+dayStart+"'\n" +
                        "AND dateOfTransaction <= '"+year+"/"+month+"/"+dayEnd+"'";
        
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            
            while(rs.next())
            {
                //(int idProduct, double price, String category, String author,
                //int publishedYear, int amountInventory, String bookName) 
                profit += rs.getDouble("price");
            }
                
            return profit;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return profit;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public static double getMonthlyProfit(int year, int month)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        double profit = 0.0;
        
        String query = "SELECT *\n" +
                        "FROM Transactions\n" +
                        "WHERE dateOfTransaction >= '"+year+"/"+month+"/01'\n" +
                        "AND dateOfTransaction <= '"+year+"/"+month+"/31'";
        
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            
            while(rs.next())
            {
                //(int idProduct, double price, String category, String author,
                //int publishedYear, int amountInventory, String bookName) 
                profit += rs.getDouble("price");
            }
                
            return profit;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return profit;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    
    public static ArrayList<Integer> getRangeOfYear()
    {
        ArrayList<Integer> years = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        double profit = 0.0;
        
        String query = "SELECT *\n" +
                        "FROM Transactions";
        
        int min = 9999;
        int max = 0;
        
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            
            while(rs.next())
            {
                //(int idProduct, double price, String category, String author,
                //int publishedYear, int amountInventory, String bookName) 
                Date dat = rs.getDate("dateOfTransaction");
                
                Calendar cal = Calendar.getInstance();
                cal.setTime(dat);
                int year = cal.get(Calendar.YEAR);
                
                if(year > max)
                {
                    max = year;
                }
                if(year < min)
                {
                    min = year;
                }
            }
            
            years.add(new Integer(min));
            years.add(new Integer(max));
                
            return years;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return years;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static ArrayList<Transaction> getTwoOrMoreUsers(String category)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * \n" +
                    "  FROM Transactions\n" +
                    "  WHERE quantity > '1'"
                + " AND category = '"+category+"'";
        
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            
            while(rs.next())
            {
                //(int idProduct, double price, String category, String author,
                //int publishedYear, int amountInventory, String bookName) 
                 //(int idProduct, double price, String category, String author,
                //int publishedYear, int amountInventory, String bookName) 
                int idTransaction = rs.getInt("idTransactions");
                Date date = rs.getDate("dateOfTransaction");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                String username = rs.getString("Customer_username");
                int Books_idProduct = rs.getInt("Books_idProduct");
                
                transactions.add(
                    new Transaction(
                        idTransaction,
                            date,
                            price,
                            quantity,
                            username,
                            Books_idProduct,
                            BookDB.getBook(Books_idProduct).getCategory()
                    )
                );
            }
            
            return transactions;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return transactions;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
