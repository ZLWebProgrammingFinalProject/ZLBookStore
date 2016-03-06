/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.beans.*;
import java.io.Serializable;

/**
 *
 * @author jin3lee
 */
public class Book implements Serializable 
{
    private int idProduct;
    private double price;
    private String category;
    private String author;
    private int publishedYear;
    private int amountInventory;
    private String bookName;
    
    
    public Book() 
    {
        this.idProduct = 0;
        this.price = 0.0;
        this.category = "none";
        this.author = "";
        this.publishedYear = 0;
        this.amountInventory = 0;
        this.bookName = "";
    }
    
    public Book(int idProduct, double price, String category, String author,
            int publishedYear, int amountInventory, String bookName) 
    {
        this.idProduct = idProduct;
        this.price = price;
        this.category = category;
        this.author = author;
        this.publishedYear = publishedYear;
        this.amountInventory = amountInventory;
        this.bookName = bookName;
    }
    
    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public int getAmountInventory() {
        return amountInventory;
    }

    public void setAmountInventory(int amountInventory) {
        this.amountInventory = amountInventory;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
    
}
