<%@ page import="org.example.studentlessonservlet.model.Lesson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Lesson</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%Lesson lesson = (Lesson) request.getAttribute("lesson");%>

<form action="/updateLesson" method="post">
    <input type="hidden" name="lessonId" value="<%=lesson.getId()%>">
    <input type="text" name="lessonName" placeholder="Name" value="<%=lesson.getName()%>"><br>
    <input type="number" step="any" name="lessonDuration" placeholder="Duration" value="<%=lesson.getDuration()%>"><br>
    <input type="text" name="lessonLecturerName" placeholder="LecturerName" value="<%=lesson.getLectureName()%>"><br>
    <input type="number" step="any" name="lessonPrice" placeholder="Price" value="<%=lesson.getPrice()%>"><br>
    <input type="submit" name="save">
</form>
</body>
</html>
