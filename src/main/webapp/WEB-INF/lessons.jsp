<%@ page import="java.util.List" %>
<%@ page import="org.example.studentlessonservlet.model.Lesson" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Lessons</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<% List<Lesson> lessons = (List<Lesson>) request.getAttribute("lessons");%>

<a href="/addLesson">Add Lesson</a><br><br>

<% if (!lessons.isEmpty()) {%>
<table>
    <tr>
        <th>ID</th>
        <th>NAME</th>
        <th>DURATION</th>
        <th>LECTURER NAME</th>
        <th>PRICE</th>
        <th>DELETE</th>
    </tr>

    <% for (Lesson lesson : lessons) {%>
    <tr>
        <td><%=lesson.getId()%>
        </td>
        <td><%=lesson.getName()%>
        </td>
        <td><%=lesson.getDuration()%> hours</td>
        <td><%=lesson.getLectureName()%>
        </td>
        <td><%=lesson.getPrice()%>
        </td>
        <td><a href="/deleteLesson?id=<%=lesson.getId()%>">delete</a></td>
    </tr>
    <%}%>
</table>
<%}%>
</body>
</html>
