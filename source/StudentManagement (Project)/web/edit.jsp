<%-- 
    Document   : edit
    Created on : Apr 21, 2023, 2:11:20 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Student" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Student</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <%
        Student student = (Student) request.getAttribute("student");
    %>
    <body>
        <div class="container">
            <h1>Edit Student</h1>
            <c:set var="student" value="${requestScope.student}"/>
            <form method="post" action="edit">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" id="name" name="name" value="${student.name}">
                </div>
                <div class="form-group">
                    <label for="grade">Grade:</label>
                    <input type="text" class="form-control" id="grade" name="grade" value="${student.grade}">
                </div>
                <div class="form-group">
                    <label for="birthday">Birthday:</label>
                    <input type="date" class="form-control" id="birthday" name="birthday" value="${student.birthday}">
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <textarea class="form-control" id="address" name="address">${student.address}</textarea>
                </div>
                <div class="form-group">
                    <label for="notes">Notes:</label>
                    <textarea class="form-control" id="notes" name="notes">${student.notes}</textarea>
                </div>
                <input type="hidden" name="id" value="${student.id}">
                <button type="submit" class="btn btn-primary">Update</button>
                <a href="/student-management/students" class="btn btn-default">Cancel</a>
            </form>
        </div>
    </body>
</html>
