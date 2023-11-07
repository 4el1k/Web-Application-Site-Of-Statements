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
</body>
</html>
