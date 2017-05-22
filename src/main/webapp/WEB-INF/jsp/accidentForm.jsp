<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <s:if test="accident==null || accident.accidentId == null">
        <title>Add accident</title>
    </s:if>
    <s:else>
        <title>Update accident</title>
    </s:else>
</head>
<body>
<s:if test="accident==null || accident.accidentId == null">
    <h1>Add accident</h1>
</s:if>
<s:else>
    <h1>Update accident</h1>
</s:else>
<s:actionerror />
<s:actionmessage />
<s:form  theme="simple" action="saveAccident" method="post">

    <div class="form-group">
        <s:fielderror />


        <s:select theme="simple" list="disasters" listKey="disasterId" listValue="name" name="accident.disasterId" value="%{accident.disasterId}"/>
        <s:select theme="simple" list="cities" listKey="cityId" listValue="name" name="accident.cityId" value="%{accident.cityId}"/>

        <s:select
                  list="#{'1':'1', '2':'2', '3':'3'}"
                  name="accident.level"
                  value="accident.level" />
        <s:textarea  class="form-control" theme="simple" name="accident.content" value="%{accident.content}" label="%{getText('label.content')}" size="40"/>
        <s:textfield type="date" class="form-control" theme="simple" name="accident.date" value="%{accident.date.toString()}" label="%{getText('label.date')}" size="20" />
        <s:hidden class="form-control" theme="simple" name="accident.accidentId" value="%{accident.accidentId}"/>
    </div >

    <s:submit theme="simple" class="btn btn-primary" value="Save"/>

    <s:submit theme="simple" class="btn btn-default" value="Cancel" action="accidentsIndex"/>


</s:form>

</body>
</html>