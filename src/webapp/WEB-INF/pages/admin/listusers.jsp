<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<div class="row">
    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">
            <form action="/admin/add" method="get">
                <button type="submit">New User</button>
            </form>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Password</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>
                        <c:out value="${user.id}" />
                    </td>
                    <td>
                        <c:out value="${user.name}" />
                    </td>
                    <td>
                        <c:out value="${user.password}" />
                    </td>
                    <td>
                        <form method="get" action="/admin/edit">
                            <div>
                                <button type="submit" >
                                    <input hidden="true" value=${user.id} name="id"> <!--Передаю параметры в editUserPage.jsp-->
                                    <input hidden="true" value=${user.name} name="name">
                                    <input hidden="true" value=${user.password} name="password">
                                    Edit
                                </button>
                            </div>
                        </form>
                    </td>
                    <td>
                        <form method="get" action="/admin/delete">
                            <div>
                                <button type="submit">
                                    Delete
                                    <input hidden="true" value=${user.id} name="id">
                                </button>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <jsp:include page="../logout.jsp" />
        Status: <%= session.getAttribute("status") %>
    </div>
</div>
</body>
</html>