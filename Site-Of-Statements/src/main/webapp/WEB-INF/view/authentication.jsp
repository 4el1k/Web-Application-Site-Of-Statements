<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <style><%@include file="/style/stylesAuthentication.css" %> </style>
    <title>Main page</title>
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
    <div class="sign-up-service-main">
        <div>
            <div class="sign-up-service-heading">
                <h1>Authentication</h1>
            </div>
            <div>
                <div>
                    <form method="POST" action="http://localhost:8081/Site_Of_Statements_war/authentication">
                        <input class="fill-field" type="email" name="mail" placeholder="enter mail"/>
                        <input class="fill-field" type="text" name="name" placeholder="enter username"/>
                        <input class="fill-field" type="number" name="phoneNumber" placeholder="enter number"/>
                        <input class="fill-field" type="password" name="password" placeholder="enter password"/>
                        <input class="fill-field" type="password" name="passwordReplay" placeholder="replay password"/>
                        <input class="button" type="submit" value="SignUp"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
