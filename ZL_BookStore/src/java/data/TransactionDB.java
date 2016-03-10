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
}
