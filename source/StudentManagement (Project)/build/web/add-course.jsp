<%-- 
    Document   : add-course
    Created on : Apr 20, 2023, 5:30:01 PM
    Author     : asus
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Course</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <h1>Add New Course</h1>
            <form action="/student-management/add-course" method="get">
                <div class="form-group">
                    <label for="id">ID</label>
                    <input type="number" class="form-control" id="id" name="id" required>
                </div>
                <div class="form-group">
                    <label for="name">Name:</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="address">Lecture</label>
                    <input type="text" class="form-control" id="lecture" name="lecture" required>
                </div>
                <div class="form-group">
                    <label for="grade">Year</label>
                    <input type="number" class="form-control" id="year" name="year" min="1000" max="9999" required>
                </div>
                <div class="form-group">
                    <label for="notes">Notes:</label>
                    <textarea class="form-control" id="notes" name="notes"></textarea>
                </div>
                <button type="submit" class="btn btn-primary">Add Course</button>
                <a href="/student-management/courses" class="btn btn-default">Cancel</a>
            </form>
        </div>
    </body>
</html>
