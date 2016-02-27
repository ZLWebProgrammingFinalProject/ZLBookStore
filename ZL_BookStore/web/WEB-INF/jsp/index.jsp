<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>ZL BookStore</title>
        <link rel="stylesheet" type="text/css" href="styles/lzStyles.css">
    </head>
    
    <div class="top">
         <p id="companyName">ZL Book Store</p> 
    <div id="header">
    <form action="http://www.localhost:8080/ZL_BookStore/Search" method="post" >
        <input name="searchWords" type="search" placeholder="Book name"/>
        <select name="books">
            <option value="all" selected="selected">all</option>  
            <option value="Education">Education</option>
            <option value="SciFi">SciFi</option>
            <option value="Romance">Romance</option>
            <option value="Classic">Classic</option>
            <option value="Kids">Kids</option>
        </select>
        <input type="submit" value="Search"/>
    </form>
    <br />
    </div>
    
    <div id="user">
        <a href="htmls/CreateAccount.html" id="createAccount">Create new account</a> |
        <a href="htmls/login.html" id="login" >Login</a><br/>    
    </div>
    
    <div id="myAccount">
        <a href="htmls/myAccount.html" id="account">My account</a>
    </div>
    
    </div>
    
    <div class="main">
    </div>
    
    
    <div class="left">
    </div>
    
<body>
</body>

</html>
