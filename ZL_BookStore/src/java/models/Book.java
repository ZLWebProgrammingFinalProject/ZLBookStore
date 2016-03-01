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
public class Book implements Serializable {

    private String bookName;
    private String author;
    private double price;
    private String category;
    private int publishedYear;
    
    
    public Book() {
        
    }
    
    public Book(String bookName, String author, String category, double price, int publishedYear) {
        this.bookName=bookName;
        this.author=author;
        this.category=category;
        this.price=price;
        this.publishedYear=publishedYear;
        
    }
    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }
    
}
