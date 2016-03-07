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
public class Rating implements Serializable 
{
    int idRating;
    Date date;
    int Rating;
    String Customer_username;
    int Books_idProduct;

    public Rating(int idRating, Date date, int Rating, String Customer_username, int Books_idProduct) {
        this.idRating = idRating;
        this.date = date;
        this.Rating = Rating;
        this.Customer_username = Customer_username;
        this.Books_idProduct = Books_idProduct;
    }
    
    public Rating(Date date, int Rating, String Customer_username, int Books_idProduct) {
        this.date = date;
        this.Rating = Rating;
        this.Customer_username = Customer_username;
        this.Books_idProduct = Books_idProduct;
    }

    public int getIdRating() {
        return idRating;
    }

    public void setIdRating(int idRating) {
        this.idRating = idRating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
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
