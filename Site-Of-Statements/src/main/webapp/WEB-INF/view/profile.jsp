<%@ page import="ru.itis.models.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <style><%@include file="/style/stylesProfile.css" %> </style>
    <title>My account</title>

    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap" rel="stylesheet">
</head>
<body>
<div>
    <div class = "menu">
        <form action="http://localhost:8081/Site_Of_Statements_war/profile">
            <input class="menu-button" type="submit" value="Profile">
        </form>
        <form action="http://localhost:8081/Site_Of_Statements_war/userposts">
            <input class="menu-button" type="submit" value="My posts">
        </form>
        <form action="http://localhost:8081/Site_Of_Statements_war/favoriteposts">
            <input class="menu-button" type="submit" value="Favorite posts">
        </form>
        <form action="http://localhost:8081/Site_Of_Statements_war/authentication">
            <input class="menu-button" type="submit" value="SignUp">
        </form>
        <form action="http://localhost:8081/Site_Of_Statements_war/authorisation">
            <input class="menu-button" type="submit" value="SignIn">
        </form>
    </div>
    <div class="main-div">
        <div class="userImage">
            <img src="https://twitchgid.ru/wp-content/cache/thumb/0f/e50a5ad1c9dca0f_730x400.jpg"/>
        </div>

        <div class="fields">
            <%Account account = (Account) request.getAttribute("account");%>
            <h1>mail: <%=account.getMail()%></h1>
            <h1>name: <%=account.getName()%></h1>
        </div>
        <form action="http://localhost:8081/Site_Of_Statements_war/posting">
            <div class="wrap">
                <button class="button">
                    create post
                </button>
            </div>
        </form>
    </div>
</div>
</body>
</html>
