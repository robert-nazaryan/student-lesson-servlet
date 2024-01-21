<%@ page import="org.example.studentlessonservlet.model.Lesson" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.studentlessonservlet.model.Student" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Student</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>
<%Student student = (Student) request.getAttribute("student");%>

<form action="/updateStudent" method="post" enctype="multipart/form-data">
    <input type="hidden" name="studentId" value="<%=student.getId()%>">
    <input type="text" name="studentName" placeholder="Name" value="<%=student.getName()%>"><br>
    <input type="text" name="studentSurname" placeholder="Surname" value="<%=student.getSurname()%>"><br>
    <input type="email" name="studentEmail" placeholder="Email" value="<%=student.getEmail()%>"><br>
    <input type="number" name="studentAge" placeholder="Age" value="<%=student.getAge()%>"><br>
    <select name="studentLessonId">
        <%for (Lesson lesson : lessons) {%>
        <%if (lesson.getId() == student.getLesson().getId()) {%>
        <option value="<%=lesson.getId()%>" selected><%=lesson.getName()%></option>
        <%
                continue;
            }
        %>
        <option value="<%=lesson.getId()%>"><%=lesson.getName()%></option>
        <%}%>
    </select><br>
    <input type="file" name="studentPhoto"><br>
    <input type="submit" value="save">
</form>
</body>
</html>
