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
        <input type="submit" value="Search"/>
    </form>
    <br />
    </div>
    
    <div id="user">
        <a href="htmls/CreateAccount.html" id="createAccount">Create new account</a> |
        <a href="htmls/login.html" id="login" >Login</a><br/>    
    </div>
    
    <div id="myAccount">
        <a href="#" id="homePage">Home page</a> |
        <a href="http://www.localhost:8080/ZL_BookStore/MyAccount" id="account">My account</a>
    </div>
    
    </div>
    
    <div class="main">
        <h1>Welcome to our Book Store</h1>
        <p>Feel free to create an account, login, search/browse for books, and purchase them!</p>
    </div>
    
    
    <div class="left">
        <br />
        <a href="http://www.localhost:8080/ZL_BookStore/ViewAllBooks" style="text-decoration:none;" id='index_side'>View All Books</a><br /><br />
        <a href="http://www.localhost:8080/ZL_BookStore/TopTenBooks" style="text-decoration:none;" id='index_side'>View Top 10 Best Sellers</a><br /><br />
        <a href="http://www.localhost:8080/ZL_BookStore/ViewAllBooks" style="text-decoration:none;" id='index_side'>View Top 5 By Category</a><br /><br />
        
    </div>
    
<body>
</body>

</html>
