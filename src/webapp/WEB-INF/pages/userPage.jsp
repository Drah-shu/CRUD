<%--
  Created by IntelliJ IDEA.
  User: kiril
  Date: 07.06.2020
  Time: 18:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



    Привет юзверь



    <div>
        <jsp:include page="logout.jsp" />
        Status: <%= session.getAttribute("status") %>
    </div>
</body>
</html>
