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
import models.Cart;

/**
 *
 * @author jin3lee
 */
public class CartDB 
{
    public static int insert(Cart cart)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO Cart(quantity, date, Books_idProduct, Customer_username)"+"VALUE(?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, ""+cart.getQuantity());
            ps.setString(2, ""+cart.getDate());
            ps.setString(3, ""+cart.getBooks_idProduct());
            ps.setString(4, ""+cart.getCustomer_username());
            
            return ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
//    public static ArrayList<> getCart(String Customer_username)
//    {
//        ArrayList<Cart> cart = new ArrayList<>();
//        
//        ConnectionPool pool = ConnectionPool.getInstance();
//        Connection connection = pool.getConnection();
//        PreparedStatement ps= null;
//        ResultSet rs = null;
//        
//        String query = "SELECT * FROM Cart "
//                + "WHERE Customer_username = ?";
//        
//        try
//        {
//            ps = connection.prepareStatement(query);
//            ps.setString(1, Customer_username);
//            rs = ps.executeQuery();
//            
//            if(rs.next())
//            {
//                //(int idProduct, double price, String category, String author,
//            //int publishedYear, int amountInventory, String bookName) 
//                return new Book(
//                        rs.getInt("idProduct"),
//                        rs.getDouble("price"),
//                        rs.getString("category"),
//                        rs.getString("author"),
//                        rs.getInt("publishedYear"),
//                        rs.getInt("amountInventory"),
//                        rs.getString("bookName")
//                );
//            }
//                
//            return new Book();
//        } 
//        catch(SQLException e)
//        {
//            System.out.println(e);
//            return new Book();
//        }
//        finally
//        {
//            DBUtil.closeResultSet(rs);
//            DBUtil.closePreparedStatement(ps);
//            pool.freeConnection(connection);
//        }
//    }
    
}
