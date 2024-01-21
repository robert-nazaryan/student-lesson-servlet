<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
    <link rel="stylesheet" href="/css/style.css">
</head>
<body>
<% if (session.getAttribute("msg") != null) {%>
<span style="color: red"><%=session.getAttribute("msg")%></span>
<% session.removeAttribute("msg");%>
<%}%>

<form action="/login" method="post">
    <input type="email" name="email" placeholder="Email"> <br>
    <input type="password" name="password" placeholder="Password"> <br>
    <input type="submit" value="login">
</form><br><br><br><br>

<form action="/register" method="post">
    <input type="text" name="name" placeholder="Name"> <br>
    <input type="text" name="surname" placeholder="Surname"> <br>
    <input type="email" name="email" placeholder="Email"> <br>
    <input type="password" name="password" placeholder="Password"> <br>
    <input type="submit" value="register">
</form>

</body>
</html>
