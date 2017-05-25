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
<h2>Sign up</h2>
<s:form theme="simple" action="register" method="form">
    <div class="form-group">
        <s:fielderror />
        <label for="regirster_name"> Name:</label>
    <s:textfield class="form-control" theme="simple" name="name" label="UserName"></s:textfield>
        <label for="regirster_password"> Password:</label>
    <s:password class="form-control" theme="simple" name="password" label="Password"></s:password>
        <label for="regirster_email"> Email:</label>
    <s:textfield class="form-control" theme="simple" name="email" label="Email"></s:textfield>
    </div >

    <s:submit class="btn btn-primary" value="Sign up"></s:submit>

</s:form>
</body>
</html>
