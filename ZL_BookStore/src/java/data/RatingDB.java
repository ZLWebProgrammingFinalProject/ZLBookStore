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
import models.Rating;
import util.DBUtil;

/**
 *
 * @author jin3lee
 */
public class RatingDB 
{
    public static boolean hasRated
        (String Customer_username,
         int Books_idProduct)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Rating "
                + "WHERE Customer_username = ?"
                + "AND Books_idProduct = ?";
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, Customer_username);
            ps.setString(2, ""+Books_idProduct);
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
        
    public static int getRating
        (String Customer_username,
         int Books_idProduct)
    {
        int retRating = -1;
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM Rating "
                + "WHERE Customer_username = ?"
                + "AND Books_idProduct = ?";
        
        try
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, Customer_username);
            ps.setString(2, ""+Books_idProduct);
            rs = ps.executeQuery();
            
            while(rs.next())
            {
                retRating = rs.getInt("Rating");
                break;
            }
            return retRating;
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return retRating;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    } 
        
    public static int getAverageRatingValue
        (int Books_idProduct)
    {
        int retRating = -1;
        ArrayList<Integer> ratingList = new ArrayList<>();
        
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        ResultSet rs = null;
        
        String query = "Select * From Rating\n" +
                        "WHERE Books_idProduct='"+Books_idProduct+"'";
        
        try
        {
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            
            int total = 0;
            while(rs.next())
            {
                retRating = rs.getInt("Rating");
                ratingList.add(new Integer(retRating));
                total+=retRating;
            }
            if(ratingList.size() == 0)
            {
                return 0;
            }
            return total/ratingList.size();
        } 
        catch(SQLException e)
        {
            System.out.println(e);
            return retRating;
        }
        finally
        {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    } 
        
    public static int insert(Rating rating)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO Rating(date, Rating, Customer_username, Books_idProduct)"+"VALUE(?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, ""+rating.getDate());
            ps.setString(2, ""+rating.getRating());
            ps.setString(3, ""+rating.getCustomer_username());
            ps.setString(4, ""+rating.getBooks_idProduct());
            
            return ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
