<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 21.05.2017
  Time: 6:59
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Full news info</title>
</head>
<body>
<h1 align="center"><s:property value="%{news.title}"/>

    <s:url action="updateNews" var="url">
        <s:param name="newsId" value="newsId"/>
    </s:url>
    <a href="<s:property value="#url"/>" class="btn btn-success" role="button">Edit</a>
    <s:url action="deleteNews" var="url">
        <s:param name="news.newsId" value="newsId"/>
    </s:url>
    <a href="<s:property value="#url"/>" onclick="return confirm('Are you sure?')" class="btn btn-danger" role="button">Delete</a>
</h1>
<h3 align="center"><s:property value="%{news.date}"/></h3>
<div class="container">
    <h2><s:property value="%{news.brief}"/></h2>
    <div class="panel panel-default">
        <div class="panel-body"><p><s:property value="%{news.content}"/></p></div>
    </div>
</div>



</body>
</html>
