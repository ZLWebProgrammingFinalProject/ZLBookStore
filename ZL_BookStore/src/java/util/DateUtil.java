/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author jin3lee
 */
public class DateUtil 
{
    public static java.sql.Date getDate()
    {
        return new java.sql.Date(new java.util.Date().getTime());
    }
}
