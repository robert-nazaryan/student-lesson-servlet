<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Lesson</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<form action="/addLesson" method="post">
    <input type="text" name="lessonName" placeholder="Name"><br>
    <input type="number" step="any" name="lessonDuration" placeholder="Duration"><br>
    <input type="text" name="lessonLecturerName" placeholder="LecturerName"><br>
    <input type="number" step="any" name="lessonPrice" placeholder="Price"><br>
    <input type="submit" name="add">
</form>
</body>
</html>
