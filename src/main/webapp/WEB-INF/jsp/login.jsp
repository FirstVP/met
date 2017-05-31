<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 09.05.2017
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
    <title>Login</title>

    <link rel="stylesheet" href="css/materialize.min.css" />

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />

</head>
<body>
<hr/>
<h2>Sign in</h2>

<s:form action="loginprocess">
<div class="form-group">
    <s:fielderror />
    <label for="loginprocess_username"> Name:</label>
    <s:textfield name="username" label="Name" class="form-control" theme="simple" ></s:textfield>
    <label for="loginprocess_password"> Password:</label>
    <s:password name="userpass" label="Password" class="form-control" theme="simple"  ></s:password>
</div>
    <s:submit value="Sign in" class="btn btn-default" theme="simple"></s:submit>
</s:form>



</body>
</html>
