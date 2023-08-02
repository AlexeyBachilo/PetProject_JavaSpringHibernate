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
        <h1>=====User Menu=====</h1>
        <p>You have deleted a user with id ${userId} at <%= new java.util.Date() %></p>

        <c:url var="mainUrl" value="/petproject/main/menu"/>
        <p> Return to <a href="${mainUrl}">Main Menu</a></p>

        <c:url var="userMenuUrl" value="/petproject/main/users"/>
        <p> Return to <a href="${userMenuUrl}">User Menu</a></p>
    </body>
</html>