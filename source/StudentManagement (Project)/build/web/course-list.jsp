<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="model.Course" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Course Management</title>
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
            <h1>List of Courses:</h1>
            <div class="btn-group">
                <a href="add-course.jsp" class="btn btn-primary">Add</a>
                <a href="sort-courses" class="btn btn-primary">Sort By Name</a>
                <div class="input-group">
                <form method="get" action="/student-management/courses">
                    <button type="submit" class="btn btn-primary">Find By Name</button>
                    <input type="text" class="form-control" placeholder="Enter name" name="name" style="display: inline-block; width: auto;">
                </form>
            </div>

            </div>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Lecture</th>
                        <th>Year</th>
                        <th>Notes</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <%
                    if (request.getAttribute("courseList")!=null){
                        List<Course> courseList = (List<Course>) request.getAttribute("courseList");
                %>
                <tbody>
                    <c:forEach var="course" items="${courseList}">
                        <tr>
                            <td>${course.getId()}</td>
                            <td>${course.getName()}</td>
                            <td>${course.getLecture()}</td>
                            <td>${course.getYear()}</td>
                            <td>${course.getNotes()}</td>
                            <td>
                                <div class="btn-group mr-1" role="group">
                                    <form method="get" action="edit-course?id=${course.getId()}" class="mr-1">
                                      <input type="hidden" name="id" value="${course.getId()}">
                                      <button type="submit" class="btn btn-primary">Edit</button>
                                    </form>
                                    <form method="get" action="/student-management/delete-course" class="mr-1">
                                      <input type="hidden" name="id" value="${course.getId()}">
                                      <button type="submit" class="btn btn-danger">Delete</button>
                                    </form>
                                    <form method="get" action="/student-management/course-participants" class="mr-1">
                                      <input type="hidden" name="course_id" value="${course.getId()}">
                                      <button type="submit" class="btn btn-success">Participants</button>
                                    </form>
                                  </div>
                                  <form method="get" action="/student-management/add-to-course" class="form-inline">
                                    <div class="form-group mr-1">
                                      <input type="hidden" name="id" value="${course.getId()}">
                                      <button type="submit" class="btn btn-primary">Add Student</button>
                                    </div>
                                    <div class="form-group">
                                      <label for="studentId" class="sr-only">Student ID</label>
                                      <input type="text" class="form-control" id="studentId" name="studentId" placeholder="Enter Student ID..." required>
                                    </div>
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
