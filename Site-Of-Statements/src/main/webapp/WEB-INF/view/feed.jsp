<%@ page import="ru.itis.models.Post" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: bebra
  Date: 02.11.2023
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">

    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Permanent+Marker&display=swap" rel="stylesheet">
    <style><%@include file="/style/stylesFeed.css" %> </style>
</head>
<body>
<% List<Post> posts = (List<Post>) request.getAttribute("posts"); %>
<div>
    <div class = "menu">
        <form action="http://localhost:8081/Site_Of_Statements_war/profile">
            <input class="menu-button" type="submit" value="profile">
        </form>
        <form action="http://localhost:8081/Site_Of_Statements_war/userposts">
            <input class="menu-button" type="submit" value="My Post">
        </form>
        <form action="http://localhost:8081/Site_Of_Statements_war/favoriteposts">
            <input class="menu-button" type="submit" value="favorite posts">
        </form>
        <form action="http://localhost:8081/Site_Of_Statements_war/authentication">
            <input class="menu-button" type="submit" value="SignUp">
        </form>
        <form action="http://localhost:8081/Site_Of_Statements_war/authorisation">
            <input class="menu-button" type="submit" value="SignIn">
        </form>
    </div>
    <div class="scroll">

        <div class = "feed">
            <% for (Post post : posts) { %>
                <div class="frame">
                    <div>
                        <p class="title">Title: <%=post.getTitle()%></p>
                        <p class="price">Price: <%=post.getPrice()%> Rubles</p>
                        <p class="description">Description: <%=post.getDescription()%></p>
                        <form method="post" action="http://localhost:8081/Site_Of_Statements_war/feed">
                            <input class="feed-button" type="submit" value="More info">
                            <input name="postId" value="<%=post.getId()%>" type="hidden">
                        </form>
                    </div>
                    <div>
                        <%String string = "photos/" + post.getPathsOfPhotos().get(0);%>
                        <img class="frame-image" src="<%=string%>"/>
                    </div>
                </div>
                <%}%>
        </div>
    </div>
</div>
</body>
</html>
