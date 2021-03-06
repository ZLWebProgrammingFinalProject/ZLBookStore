/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;

/**
 *
 * @author jin3lee
 */
public class Customer implements Serializable 
{
    private String userName;
    private String passWord;
    private String name;
    private String email;
    private int admin;
    
    public Customer() 
    {
        this.userName = "";
        this.passWord = "";
        this.name = "";
        this.email = "";
        this.admin = 0;
    }
    
    public Customer(String userName, String passWord, String name, String email) 
    {
        this.userName = userName;
        this.passWord = passWord;
        this.name = name;
        this.email = email;
        this.admin = 0;
    }
    
    public Customer(String userName, String passWord, String name, String email, int admin) 
    {
        this.userName = userName;
        this.passWord = passWord;
        this.name = name;
        this.email = email;
        this.admin = admin;
    }
    
    
    public int isAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }
    
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
