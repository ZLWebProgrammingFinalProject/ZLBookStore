/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.beans.*;
import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author jin3lee
 */
public class Cart implements Serializable 
{
    
    int idCart;
    int quantity;
    Date date;
    int Books_idProduct;
    String Customer_username;
    
    public Cart(int idCart, int quantity, Date date, int Books_idProduct, String Customer_username) {
        this.idCart = idCart;
        this.quantity = quantity;
        this.date = date;
        this.Books_idProduct = Books_idProduct;
        this.Customer_username = Customer_username;
    }
    public Cart(int quantity, Date date, int Books_idProduct, String Customer_username) {
        
        this.quantity = quantity;
        this.date = date;
        this.Books_idProduct = Books_idProduct;
        this.Customer_username = Customer_username;
    }
    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getBooks_idProduct() {
        return Books_idProduct;
    }

    public void setBooks_idProduct(int Books_idProduct) {
        this.Books_idProduct = Books_idProduct;
    }

    public String getCustomer_username() {
        return Customer_username;
    }

    public void setCustomer_username(String Customer_username) {
        this.Customer_username = Customer_username;
    }
    
}
