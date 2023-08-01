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
        <h1>=====Update Task=====</h1>
        <c:url var="saveTaskUrl" value="/petproject/main/tasks/update?id=${taskAttribute.userId}"/>
        <form:form modelAttribute="taskAttribute" method="POST" action="${saveTaskUrl}">
            <table>
                <tr>
                    <td><form:label path="taskId">Id:</form:label></td>
                    <td><form:input path="taskId" disabled="true"/></td>
                </tr>
                <tr>
                    <td><form:label path="taskName">Task Name:</form:label></td>
                    <td><form:input path="taskName"/></td>
                </tr>
                <tr>
                    <td><form:label path="taskDescription">Task Description:</form:label></td>
                    <td><form:input path="taskDescription"/></td>
                </tr>
                <tr>
                    <td><form:label path="deadline">Deadline:</form:label></td>
                    <td><form:input path="deadline"/></td>
                </tr>
                <tr>
                    <td><form:label path="taskPoints">Task Points:</form:label></td>
                    <td><form:input path="taskPoints"/></td>
                </tr>
            </table>

            <input type="submit" value="Save"/>
        </form:form>
    </body>
</html>