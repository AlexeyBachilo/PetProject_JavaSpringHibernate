<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<% page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transactional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PetProject</title>
    </head>
    <body>
        <h1>=====Update User=====</h1>
        <c:url var="saveUserUrl" value="/petproject/main/users/update?id=${userAttribute.userId}"/>
        <form:form modelAttribute="userAttribute" method="POST" action="${saveUserUrl}">
            <table>
                <tr>
                    <td><form:label path="userId">Id:</form:label></td>
                    <td><form:input path="userId" disabled="true"/></td>
                </tr>
                <tr>
                    <td><form:label path="login">Login:</form:label></td>
                    <td><form:input path="login"/></td>
                </tr>
                <tr>
                    <td><form:label path="firstName">First Name:</form:label></td>
                    <td><form:input path="firstName"/></td>
                </tr>
                <tr>
                    <td><form:label path="lastName">Last Name:</form:label></td>
                    <td><form:input path="lastName"/></td>
                </tr>
            </table>

            <input type="submit" value="Save"/>
        </form:form>
    </body>
</html>