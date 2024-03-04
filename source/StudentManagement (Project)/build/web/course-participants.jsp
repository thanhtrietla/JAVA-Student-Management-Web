<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="model.Student" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Participants</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            /* Add styles for the navbar */
            .navbar {
                background-color: #333;
                overflow: hidden;
            }

            /* Style the links inside the navbar */
            .navbar a {
                float: left;
                display: block;
                color: #f2f2f2;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
                font-size: 17px;
            }

            /* Change the color of the links on hover */
            .navbar a:hover {
                background-color: #ddd;
                color: black;
            }

            /* Add an active class to the current link (highlight it) */
            .active {
                background-color: #4CAF50;
                color: white;
            }
        </style>
    </head>
    <body>       
        <div class="navbar">
            <a class="active" href="/student-management">Home</a>
            <a href="/student-management/students">Students</a>
            <a href="/student-management/courses">Courses</a>
        </div>
        <div class="container">
            <h1>Course Participants:</h1>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Grade</th>
                        <th>Birthday</th>
                        <th>Address</th>
                        <th>Notes</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <%
                    int courseID = Integer.parseInt(request.getAttribute("course_id").toString());
                    if (request.getAttribute("studentList")!=null){
                        List<Student> studentList = (List<Student>) request.getAttribute("studentList");
                %>
                <tbody>
                    <c:forEach var="student" items="${studentList}">
                        <tr>
                            <td>${student.getId()}</td>
                            <td>${student.getName()}</td>
                            <td>${student.getGrade()}</td>
                            <td>${student.getBirthday()}</td>
                            <td>${student.getAddress()}</td>
                            <td>${student.getNotes()}</td>
                            <td align="center">
                                <form method="get" action="/student-management/remove-from-course">
                                    <input type="hidden" name="student_id" value="${student.getId()}">
                                    <input type="hidden" name="course_id" value="<%= courseID %>">
                                    <button type="submit" class="btn btn-danger">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <%
                    }
                %>
            </table>
        </div>
    </body>
</html>
