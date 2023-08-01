<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType"text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=URF-8">
        <title>PetProject</title>
    </head>
    <body>
        <h1>=====Task Menu=====</h1>

        <c:url var="addTaskUrl" value="/petproject/main/tasks" />
        <table style="border: 1px solid; width: 500px; text-align:center">
            <thead style="background:#fcf">
                <tr>
                    <th>Task Name</th>
                    <th>Task Description</th>
                    <th>Deadline</th>
                    <th>Completed</th>
                    <th>Task Points</th>
                    <th>Assigned User</th>
                    <th colspan="6"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${tasks}" var="task">
                    <c:url var="editTaskUrl" value="/petproject/main/tasks/edit?id=${task.taskId}"/>
                    <c:url var="deleteTaskUrl" value="/petproject/main/tasks/delete?id=${task.taskId}"/>
                   <tr>
                    <td><c:out value="${task.taskName}"/></td>
                    <td><c:out value="${task.taskDescription}"/></td>
                    <td><c:out value="${task.deadline}"/></td>
                    <td><c:out value="${task.isCompleted}"/></td>
                    <td><c:out value="${task.user}"/></td>
                    <td><a href="${editTaskUrl}">Edit</a></td>
                    <td><a href="${deleteTaskUrl}">Delete</a></td>
                    <td><a href="${addTaskUrl}">Add</a></td>
                   </tr>
                </c:forEach>
            </tbody>
        </table>

        <c:if test="${empty tasks}">
         There are currently no tasks in the list. <a href="${addTaskUrl}">Add</a> new task.
        </c:if>
    </body>
</html>