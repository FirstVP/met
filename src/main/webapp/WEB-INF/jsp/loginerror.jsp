<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 09.05.2017
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login: error</title>
</head>
<body>
    Wrong username or password!
    <% response.sendRedirect("login.action"); %>
</body>
</html>
