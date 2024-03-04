<%-- 
    Document   : edit-course
    Created on : Apr 21, 2023, 2:11:20 AM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Course" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Course</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    </head>
    <%
        Course course = (Course) request.getAttribute("course");
    %>
    <body>
        <div class="container">
            <h1>Edit Course</h1>
            <c:set var="course" value="${requestScope.course}"/>
            <form method="post" action="edit-course">
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" id="name" name="name" value="${course.name}">
                </div>
                <div class="form-group">
                    <label for="lecture">Lecture:</label>
                    <textarea class="form-control" id="lecture" name="lecture">${course.lecture}</textarea>
                </div>
                <div class="form-group">
                    <label for="year">Year:</label>
                    <input type="text" class="form-control" id="year" name="year" value="${course.year}">
                </div>            
                <div class="form-group">
                    <label for="notes">Notes:</label>
                    <textarea class="form-control" id="notes" name="notes">${course.notes}</textarea>
                </div>
                <input type="hidden" name="id" value="${course.id}">
                <button type="submit" class="btn btn-primary">Update</button>
                <a href="/student-management/courses" class="btn btn-default">Cancel</a>
            </form>
        </div>
    </body>
</html>
