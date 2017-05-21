<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 09.05.2017
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<s:form action="register" method="form">
    <s:textfield name="name" label="UserName"></s:textfield>
    <s:password name="password" label="Password"></s:password>
    <s:textfield name="email" label="Email"></s:textfield>

    <s:submit value="register"></s:submit>

</s:form>
</body>
</html>
