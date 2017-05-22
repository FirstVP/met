<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <s:if test="disaster==null || disaster.disasterId == null">
        <title>Add disaster</title>
    </s:if>
    <s:else>
        <title>Update disaster</title>
    </s:else>
</head>
<body>
<s:if test="disaster==null || disaster.disasterId == null">
    <h1>Add disaster</h1>
</s:if>
<s:else>
    <h1>Update disaster</h1>
</s:else>
<s:actionerror />
<s:actionmessage />
<s:form  theme="simple" action="saveDisaster" method="post">

    <div class="form-group">
        <s:fielderror />
        <s:textfield class="form-control" theme="simple" name="disaster.name" value="%{disaster.name}" label="%{getText('label.name')}" size="40"/>
        <s:checkbox class="form-control" theme="simple" name="disaster.global" value="%{disaster.global}" label="%{getText('label.global')}" size="40"/>
        <s:hidden class="form-control" theme="simple" name="disaster.disasterId" value="%{disaster.disasterId}"/>
    </div >

    <s:submit theme="simple" class="btn btn-primary" value="Save"/>
    <s:submit theme="simple" class="btn btn-default" value="Cancel" action="disastersIndex"/>


</s:form>

</body>
</html>