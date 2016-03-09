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
public class Transaction implements Serializable 
{
    int idTransactions;
    Date date;
    double price;
    int quantity;
    String Customer_username;
    int Books_idProduct;
    String category;
    
    public Transaction(int idTransactions, Date date, double price, int quantity, String Customer_username, int Books_idProduct, String category)
    {
        this.idTransactions = idTransactions;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        this.Customer_username = Customer_username;
        this.Books_idProduct = Books_idProduct;
    }
    
    public Transaction(Date date, double price, int quantity, String Customer_username, int Books_idProduct, String category)
    {
        this.idTransactions = idTransactions;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        this.Customer_username = Customer_username;
        this.Books_idProduct = Books_idProduct;
    }

    public int getIdTransactions() {
        return idTransactions;
    }

    public void setIdTransactions(int idTransactions) {
        this.idTransactions = idTransactions;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomer_username() {
        return Customer_username;
    }

    public void setCustomer_username(String Customer_username) {
        this.Customer_username = Customer_username;
    }

    public int getBooks_idProduct() {
        return Books_idProduct;
    }

    public void setBooks_idProduct(int Books_idProduct) {
        this.Books_idProduct = Books_idProduct;
    }
}
