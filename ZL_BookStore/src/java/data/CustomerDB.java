/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.sql.*;

import models.Customer;

/**
 *
 * @author jin3lee
 */
public class CustomerDB {
    
    public static int insert(Customer customer)
    {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        
        String query
                = "INSERT INTO Customer(username, name, password, email)"+"VALUE(?,?,?,?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, customer.getEmail());
            ps.setString(2, customer.getUserName());
            ps.setString(3, customer.getPassWord());
            ps.setString(4, customer.getName());
            return ps.executeUpdate();
        } catch (SQLException e){
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        
    }
    
    public static int update(Customer customer){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps= null;
        
        String query = "UPDATE customer SET" + "username = ?, "+"name = ? "+"password=? "+"email=?";
        
        try 
        {
            ps = connection.prepareStatement(query);
            ps.setString(1, customer.getUserName());
            ps.setString(2, customer.getName());
            ps.setString(3, customer.getPassWord());
            ps.setString(4, customer.getEmail());
            
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
    
}
