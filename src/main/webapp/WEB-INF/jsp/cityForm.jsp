<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <s:if test="city==null || city.cityId == null">
        <title>Add city</title>
    </s:if>
    <s:else>
        <title>Update city</title>
    </s:else>
</head>
<body>
<s:if test="city==null || city.cityId == null">
    <h1>Add city</h1>
</s:if>
<s:else>
    <h1>Update city</h1>
</s:else>
<s:actionerror />
<s:actionmessage />
<s:form  theme="simple" action="saveCity" method="post">

    <div class="form-group">
        <s:fielderror />
        <s:textfield type="number" class="form-control" theme="simple" name="city.code" value="%{city.code}" label="%{getText('label.code')}" size="40"/>
        <s:textfield class="form-control" theme="simple" name="city.name" value="%{city.name}" label="%{getText('label.name')}" size="40"/>
        <s:textfield type="number" class="form-control" theme="simple" name="city.rise" value="%{city.rise}" label="%{getText('label.rise')}" size="40"/>
        <s:textfield type="number" class="form-control" theme="simple" name="city.square" value="%{city.square}" label="%{getText('label.square')}" size="40"/>
        <s:textfield type="number" class="form-control" theme="simple" name="city.population" value="%{city.population}" label="%{getText('label.population')}" size="40"/>
        <s:hidden class="form-control" theme="simple" name="city.cityId" value="%{city.cityId}"/>
    </div >

    <s:submit theme="simple" class="btn btn-primary" value="Save"/>
    <s:submit theme="simple" class="btn btn-default" value="Cancel" action="citiesIndex"/>


</s:form>

</body>
</html>