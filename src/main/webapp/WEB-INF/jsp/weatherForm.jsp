<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <s:if test="weather==null || weather.weatherId == null">
        <title>Add weather</title>
    </s:if>
    <s:else>
        <title>Update weather</title>
    </s:else>
</head>
<body>
<s:if test="weather==null || weather.weatherId == null">
    <h1>Add news</h1>
</s:if>
<s:else>
    <h1>Update news</h1>
</s:else>
<s:actionerror />
<s:actionmessage />
<s:form  theme="simple" action="saveWeather" method="post">

    <div class="form-group">
        <s:fielderror />


        <s:select theme="simple" list="types" listKey="typeId" listValue="name" name="weather.typeId" value="%{weather.typeId}"/>
        <s:textfield type="number" class="form-control" theme="simple" name="weather.temp" value="%{weather.temp}" label="%{getText('label.temp')}" size="40"/>
        <s:textfield type="number" class="form-control" theme="simple" name="weather.wind" value="%{weather.wind}" label="%{getText('label.wind')}" size="40"/>
        <s:textfield type="number" class="form-control" theme="simple" name="weather.pressure" value="%{weather.pressure}" label="%{getText('label.pressure')}" size="40"/>
        <s:textfield type="date" class="form-control" theme="simple" name="weather.date" value="%{weather.date.toString()}" label="%{getText('label.date')}" size="20" />
        <s:hidden class="form-control" theme="simple" name="weather.weatherId" value="%{weather.weatherId}"/>
        <s:hidden class="form-control" theme="simple" name="weather.cityId" value="%{weather.cityId}"/>
        <s:hidden name="cityId" value="%{weather.cityId}"/>
    </div >

    <s:submit theme="simple" class="btn btn-primary" value="Save"/>

    <s:submit theme="simple" class="btn btn-default" value="Cancel" action="viewCity"/>


</s:form>

</body>
</html>