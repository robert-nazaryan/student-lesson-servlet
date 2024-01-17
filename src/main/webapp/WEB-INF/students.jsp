<%@ page import="org.example.studentlessonservlet.model.Student" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<%List<Student> students = (List<Student>) request.getAttribute("students");%>

<a href="/addStudent">Add Student</a><br><br>

<% if (!students.isEmpty()) {%>
<table>
    <tr>
        <th>PHOTO</th>
        <th>ID</th>
        <th>NAME</th>
        <th>SURNAME</th>
        <th>EMAIL</th>
        <th>AGE</th>
        <th>LESSON NAME</th>
        <th>UPDATE</th>
        <th>DELETE</th>
    </tr>

    <%for (Student student : students) {%>
    <tr>
        <td>
            <%if (student.getPhotoName() != null) {%>
            <img src="/downloadImage?imageName=<%=student.getPhotoName()%>">
            <%} else {%>
            <img src="/img/defaultProfilePhoto.png">
            <%}%>
        </td>
        <td><%=student.getId()%>
        </td>
        <td><%=student.getName()%>
        </td>
        <td><%=student.getSurname()%>
        </td>
        <td><%=student.getEmail()%>
        </td>
        <td><%=student.getAge()%>
        </td>
        <td><%=student.getLesson().getName()%>
        </td>
        <td><a href="/updateStudent?id=<%=student.getId()%>">update</a></td>
        <td><a href="/deleteStudent?id=<%=student.getId()%>">delete</a></td>
    </tr>
    <%}%>
</table>
<%}%>
</body>
</html>
