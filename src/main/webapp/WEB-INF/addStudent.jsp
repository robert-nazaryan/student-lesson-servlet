<%@ page import="java.util.List" %>
<%@ page import="org.example.studentlessonservlet.model.Lesson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Student</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>

<form action="/addStudent" method="post" enctype="multipart/form-data">
    <input type="text" name="studentName" placeholder="Name"><br>
    <input type="text" name="studentSurname" placeholder="Surname"><br>
    <input type="email" name="studentEmail" placeholder="Email"><br>
    <input type="number" name="studentAge" placeholder="Age"><br>
    <select name="studentLessonId">
        <%for (Lesson lesson : lessons) {%>
        <option value="<%=lesson.getId()%>"><%=lesson.getName()%>
        </option>
        <%}%>
    </select><br>
    <input type="file" name="studentPhoto"><br>
    <input type="submit" value="add">
</form>
</body>
</html>
