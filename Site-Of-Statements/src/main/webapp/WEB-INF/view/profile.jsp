<%@ page import="ru.itis.models.Account" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <style><%@include file="/style/stylesProfile.css" %> </style>
    <title>My account</title>
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

    <div>
        <form action="http://localhost:8081/Site_Of_Statements_war/posting">
            <input class="button" type="submit" value="Make post"/>
        </form>
    </div>

</div>
</body>
</html>
