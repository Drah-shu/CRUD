<%--
  Created by IntelliJ IDEA.
  User: kiril
  Date: 07.06.2020
  Time: 15:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<div>
    <div>
        <div>
            <h2>Edit User</h2>
        </div>
        <form action="/admin/edit" method="post">
            <p>ID</P>
            ID :<input type="text" name="id" value="<%= request.getParameter("id")%>" readonly>
            <p> Новое имя </p>
            Name: <input type="text" name="name" value="<%= request.getParameter("name")%>"  required><br />
            <p> Новый пароль </p>
            Password: <input type="text" name="password" value="<%= request.getParameter("password")%>" required><br />

            <input type="checkbox" name="user" value="user">USER<Br>
            <input type="checkbox" name="admin" value="admin">ADMIN<Br>

            <input type="submit" value="Submit" />
        </form>
    </div>
</div>
<div>
    <button onclick="location.href='http://localhost:8085/admin'">Back to main</button>
</div>
</body>
</html>