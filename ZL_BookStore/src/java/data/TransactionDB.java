/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                = "INSERT INTO Transactions(date, price, quantity, Customer_username, Books_idProduct)"+"VALUE(?,?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, ""+transaction.getDate());
            ps.setString(2, ""+transaction.getPrice());
            ps.setString(3, ""+transaction.getQuantity());
            ps.setString(4, ""+transaction.getCustomer_username());
            ps.setString(5, ""+transaction.getBooks_idProduct());
            
            return ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static ArrayList<Transaction> getTransactions(String Customer_username)
    {
        ArrayList<Transaction> transactions = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Transaction "
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
                transactions.add(
                    new Transaction(
                        rs.getInt("idTransaction"),  
                        rs.getDate("date"),  
                        rs.getDouble("price"),  
                        rs.getInt("quantity"), 
                        rs.getString("Customer_username"), 
                        rs.getInt("Books_idProduct")
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
