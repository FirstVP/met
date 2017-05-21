
<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 19.02.2017
  Time: 3:27
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table for Lab2</title>
</head>
<body>
<table border="1">
    <caption>Таблица новостей</caption>
        <tr>
            <th>ИД</th>
            <th>Заголовок</th>
            <th>Краткий текст</th>
            <th>Полный текст</th>
        </tr>

    <c:forEach items="${news}" var="item">
        <tr>
            <td>${item.newsId}</td>
            <td>${item.title}</td>
            <td>${item.brief}</td>
            <td>${item.content}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
