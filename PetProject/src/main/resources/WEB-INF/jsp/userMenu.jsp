<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType"text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=URF-8">
        <title>PetProject</title>
    </head>
    <body>
        <h1>=====User Menu=====</h1>

        <c:url var="addUserUrl" value="/petproject/main/users" />
        <table style="border: 1px solid; width: 500px; text-align:center">
            <thead style="background:#fcf">
                <tr>
                    <th>Login</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Points</th>
                    <th>Tasks</th>
                    <th colspan="5"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <c:url var="editUserUrl" value="/petproject/main/users/edit?id=${user.userId}"/>
                    <c:url var="deleteUserUrl" value="/petproject/main/users/delete?id=${user.userId}"/>
                   <tr>
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.firstName}"/></td>
                    <td><c:out value="${user.lastName}"/></td>
                    <td><c:out value="${user.userPoints}"/></td>
                    <td><c:out value="${user.tasks}"/></td>
                    <td><a href="${editUserUrl}">Edit</a></td>
                    <td><a href="${deleteUserUrl}">Delete</a></td>
                    <td><a href="${addUserUrl}">Add</a></td>
                   </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${empty users}">
         There are currently no users in the list. <a href="${addUserUrl}">Add</a> new user.
        </c:if>
    </body>
</html>