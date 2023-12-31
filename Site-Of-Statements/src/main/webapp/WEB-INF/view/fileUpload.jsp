<%--
  Created by IntelliJ IDEA.
  User: bebra
  Date: 30.10.2023
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Files Upload</title>
    <style><%@include file="/style/stylesPosting.css" %> </style>

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
    <div class="main">
        <div class="form">
            <form method="POST"  action="http://localhost:8081/Site_Of_Statements_war/posting" enctype="multipart/form-data">
                <h1>Create post</h1>
                <input class="fill-field" id="description" name="description" placeholder="Enter description...">
                <input class="fill-field" type="text" name="title" placeholder="input title, baby">
                <input class="fill-field" type="number" name="price" placeholder="price">
                <input class="button" type="file" name="file" placeholder="gooo">
                <input class="button" type="submit" value="Create">
            </form>
        </div>
    </div>
</div>
</body>
</html>
