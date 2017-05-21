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

</head>
<body>
<hr/>


<s:form action="loginprocess">
<div class="form-group">
    <s:textfield name="username" label="Name" class="form-control" theme="simple" ></s:textfield>
    <s:password name="userpass" label="Password" class="form-control" theme="simple"  ></s:password>
</div>
    <s:submit value="login" class="btn btn-default" theme="simple"></s:submit>
</s:form>



</body>
</html>
