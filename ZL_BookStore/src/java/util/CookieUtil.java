/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jin3lee
 */
public class CookieUtil 
{
    public static String getCookieValue(Cookie[] cookies, String cookieName)
    {
        String cookieValue = "";
        if(cookies != null)
        {
            for(Cookie cookie: cookies)
            {
                if(cookieName.equals(cookie.getName()))
                {
                    cookieValue = cookie.getValue();
                }
            }
        }
        return cookieValue;
    }
    
    public static void deleteCookie(
            HttpServletRequest req, 
            HttpServletResponse resp, 
            String cookieName) 
    {
        Cookie[] cookies = req.getCookies();
        if (cookies != null)
            for (int i = 0; i < cookies.length; i++) 
            {
                if(cookieName.equals(cookies[i].getName()))
                {
                    cookies[i].setValue("");
                    cookies[i].setPath("/");
                    cookies[i].setMaxAge(0);
                    resp.addCookie(cookies[i]);
                }
            }
        }
}
