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
        <h1>=====Task Menu=====</h1>
        <p>You have added a new task at <%= new java.util.Date() %></p>

        <c:url var="mainUrl" value="/petproject/main/menu"/>
        <p> Return to <a href="${mainUrl}">Main Menu</a></p>

        <c:url var="taskMenuUrl" value="/petproject/main/tasks"/>
        <p> Return to <a href="${taskMenuUrl}">Task Menu</a></p>
    </body>
</html>