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

<s:form theme="simple" action="register" method="form">
    <div class="form-group">
        <s:fielderror />
    <s:textfield class="form-control" theme="simple" name="name" label="UserName"></s:textfield>
    <s:password class="form-control" theme="simple" name="password" label="Password"></s:password>
    <s:textfield class="form-control" theme="simple" name="email" label="Email"></s:textfield>
    </div >

    <s:submit class="btn btn-primary" value="register"></s:submit>

</s:form>
</body>
</html>
