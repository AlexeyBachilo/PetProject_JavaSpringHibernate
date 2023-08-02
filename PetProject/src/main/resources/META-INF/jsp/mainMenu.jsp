<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType"text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=URF-8">
        <title>PetProject</title>
    </head>
    <body>
        <h1>=====Main Menu=====</h1>

        <c:url var="userMenuUrl" value="/petproject/main/users"/>
        <p>1. <a href="${userMenuUrl}">User Menu</a></p>

        <c:url var="taskMenuUrl" value="/petproject/main/tasks"/>
        <p>2. <a href="${taskMenuUrl}">Task Menu</a></p>
    </body>
</html>