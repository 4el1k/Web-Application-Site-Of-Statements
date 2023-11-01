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
</head>
<body>
<dev>
    <form method="POST"  action="http://localhost:8081/Site_Of_Statements_war/posting" enctype="multipart/form-data">
        <label for="description">Description</label>
        <input id="description" name="description" placeholder="Enter description...">
        <br>
        <br>
        <input type="file" name="file">
        <input type="submit" value="File Upload">
        <input type="text" name="title" placeholder="input title, baby">
        <input type="number" name="price" placeholder="price">
    </form>
</dev>
</body>
</html>
