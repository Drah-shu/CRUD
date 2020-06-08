<%--
  Created by IntelliJ IDEA.
  User: kiril
  Date: 08.06.2020
  Time: 20:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete</title>
</head>
<body>
<form action="/admin/delete" method="POST">
    Id :<input type="text" name="id" value="<%= request.getParameter("id")%>" required>
    <div class="w3-container">
        <p><button type="submit">Delete</button></p>
    </div>
</form>
<jsp:include page="../logout.jsp" />
</body>
</html>
