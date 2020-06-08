<%--
  Created by IntelliJ IDEA.
  User: kiril
  Date: 07.06.2020
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<div>
    <div>
        <div>
            <h2>Add User</h2>
        </div>

        <form action="/admin/add" method="POST">
            <p> Имя </p>
            Name: <input type="text" name="name" ><br />
            <p> Пароль </p>
            Password: <input type="text" name="password"><br />

            <input type="checkbox" name="user" value="user">USER<Br>
            <input type="checkbox" name="admin" value="admin">ADMIN<Br>

            <input type="submit" value="Submit" />
        </form>
    </div>
    <button onclick="location.href='http://localhost:8085/admin'">Back to main</button>
</div>
</body>
</html>
