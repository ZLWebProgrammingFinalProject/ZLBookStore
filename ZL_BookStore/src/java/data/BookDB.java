/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import util.DBUtil;
import java.sql.*;
import java.util.ArrayList;
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
    public static int updateInventoryCount(int idProduct, int amountInventory){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        
        String query = "UPDATE Books SET " 
                +"amountInventory=? "
                +"WHERE idProduct=?";
        
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setInt(1, amountInventory);
            ps.setInt(2, idProduct);
            
            return ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.out.println(e);
            return 0;
        }
        finally
        {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
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
    
    public static int getBookInventoryCount(int idProduct)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Books "
                + "WHERE idProduct = ?";
        
        int inventoryCount = 0;
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, ""+idProduct);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
               inventoryCount = rs.getInt("amountInventory");
            }
            return inventoryCount;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return inventoryCount;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static int getBookIdCount()
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT Count(idProduct)\n" +
                       "From Books";
        
        int inventoryCount = 0;
        
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
               inventoryCount = rs.getInt("Count(idProduct)");
            }
            return inventoryCount;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return inventoryCount;
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
    
    public static ArrayList<Book> getAllBooks()
    {
        ArrayList<Book> books = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Books";
        
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                //(int idProduct, double price, String category, String author,
            //int publishedYear, int amountInventory, String bookName) 
                books.add(
                    new Book
                    (
                        rs.getInt("idProduct"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("author"),
                        rs.getInt("publishedYear"),
                        rs.getInt("amountInventory"),
                        rs.getString("bookName")
                    )
                );
            }
                
            return books;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return books;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Book> getTopTenBestSellers()
    {
        ArrayList<Book> books = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT Count(idTransactions), Books_idProduct\n" +
                        "From Transactions\n" +
                        "GROUP BY Books_idProduct\n" +
                        "ORDER BY Count(idTransactions) DESC"
                + " LIMIT 10";
        
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                //(int idProduct, double price, String category, String author,
                //int publishedYear, int amountInventory, String bookName) 
                int Books_idProduct = rs.getInt("Books_idProduct");
                
                books.add(BookDB.getBook(Books_idProduct));
            }
                
            return books;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return books;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Book> getFiveByCategory(String category)
    {
        ArrayList<Book> books = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT Count(idTransactions), Books_idProduct\n" +
                        "From Transactions WHERE category='"+category+"'\n" +
                        "GROUP BY Books_idProduct\n" +
                        "ORDER BY Count(idTransactions) DESC"
                + " LIMIT 10";
        
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                //(int idProduct, double price, String category, String author,
                //int publishedYear, int amountInventory, String bookName) 
                int Books_idProduct = rs.getInt("Books_idProduct");
                
                books.add(BookDB.getBook(Books_idProduct));
            }
                
            return books;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return books;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static ArrayList<Book> getBooksAToZ()
    {
        ArrayList<Book> books = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * From Books\n" +
                        "ORDER BY bookName ASC";
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                books.add(new Book(
                        rs.getInt("idProduct"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("author"),
                        rs.getInt("publishedYear"),
                        rs.getInt("amountInventory"),
                        rs.getString("bookName")));
            }
                
            return books;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return books;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static ArrayList<Book> getBooksZToA()
    {
        ArrayList<Book> books = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * From Books\n" +
                        "ORDER BY bookName DESC";
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                books.add(new Book(
                        rs.getInt("idProduct"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("author"),
                        rs.getInt("publishedYear"),
                        rs.getInt("amountInventory"),
                        rs.getString("bookName")));
            }
                
            return books;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return books;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public static ArrayList<Book> getAuthorAToZ()
    {
        ArrayList<Book> books = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * From Books\n" +
                        "ORDER BY author ASC";
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                books.add(new Book(
                        rs.getInt("idProduct"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("author"),
                        rs.getInt("publishedYear"),
                        rs.getInt("amountInventory"),
                        rs.getString("bookName")));
            }
                
            return books;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return books;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static ArrayList<Book> getAuthorZToA()
    {
        ArrayList<Book> books = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * From Books\n" +
                        "ORDER BY author DESC";
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                books.add(new Book(
                        rs.getInt("idProduct"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("author"),
                        rs.getInt("publishedYear"),
                        rs.getInt("amountInventory"),
                        rs.getString("bookName")));
            }
                
            return books;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return books;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static ArrayList<Book> getBooksByCategory(String category)
    {
        ArrayList<Book> books = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * From Books\n" +
                        "WHERE category='"+category+"'";
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                books.add(new Book(
                        rs.getInt("idProduct"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("author"),
                        rs.getInt("publishedYear"),
                        rs.getInt("amountInventory"),
                        rs.getString("bookName")));
            }
                
            return books;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return books;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    
    public static ArrayList<Book> getBooksByRange(String category)
    {
        ArrayList<Book> books = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * From Books\n" +
                        "WHERE category='"+category+"'";
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                books.add(new Book(
                        rs.getInt("idProduct"),
                        rs.getDouble("price"),
                        rs.getString("category"),
                        rs.getString("author"),
                        rs.getInt("publishedYear"),
                        rs.getInt("amountInventory"),
                        rs.getString("bookName")));
            }
                
            return books;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return books;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
