/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import util.DBUtil;
import java.sql.*;
import models.Book;

import models.Customer;

/**
 *
 * @author jin3lee
 */
public class BookDB {
    
//    public static int insert(Customer customer)
//    {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps = null;
//        
//        String query
//                = "INSERT INTO Customer(username, name, password, email)"+"VALUE(?,?,?,?)";
//        try {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, customer.getUserName());
//            ps.setString(2, customer.getName());
//            ps.setString(3, customer.getPassWord());
//            ps.setString(4, customer.getEmail());
//            
//            return ps.executeUpdate();
//        } catch (SQLException e){
//            System.out.println(e);
//            return 0;
//        } finally {
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//        
//    }
//    
//    public static int update(Customer customer){
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps= null;
//        
//        String query = "UPDATE customer SET" + "username = ?, "+"name = ? "+"password=? "+"email=?";
//        
//        try 
//        {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, customer.getUserName());
//            ps.setString(2, customer.getName());
//            ps.setString(3, customer.getPassWord());
//            ps.setString(4, customer.getEmail());
//            
//            return ps.executeUpdate();
//        }
//        catch(SQLException e)
//        {
//            System.out.println(e);
//            return 0;
//        }
//        finally
//        {
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }
//    
//    public static int delete(Customer customer)
//    {
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps= null;
//        
//        String query = "DELETE FROM Customer "
//                + "WHERE email = ?";
//        
//        try
//        {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, customer.getEmail());
//            
//            return ps.executeUpdate();
//        }
//        catch(SQLException e)
//        {
//            System.out.println(e);
//            return 0;
//        }
//        finally
//        {
//            DBUtil.closePreparedStatment(ps);
//            pool.freeConnection(connection);
//        }
//    }
    
    public static boolean bookExists(String bookName)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Books "
                + "WHERE bookName = ?";
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, bookName);
            rs = ps.executeQuery();
            return rs.next();
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return false;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static Book getBook(String bookName)
    {
        Book book;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Books "
                + "WHERE bookName = ?";
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, bookName);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                //(int idProduct, double price, String category, String author,
            //int publishedYear, int amountInventory, String bookName) 
                return new Book(
                        rs.getInt("idProduct"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("author"),
                        rs.getInt("publishedYear"),
                        rs.getInt("amountInventory"),
                        rs.getString("bookName")
                );
            }
                
            return new Book();
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return new Book();
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    public static Book getBook(int idProduct)
    {
        int book;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Books "
                + "WHERE idProduct = ?";
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, ""+idProduct);
            rs = ps.executeQuery();
            
            if(rs.next())
            {
                //(int idProduct, double price, String category, String author,
            //int publishedYear, int amountInventory, String bookName) 
                return new Book(
                        rs.getInt("idProduct"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("author"),
                        rs.getInt("publishedYear"),
                        rs.getInt("amountInventory"),
                        rs.getString("bookName")
                );
            }
                
            return new Book();
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return new Book();
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
