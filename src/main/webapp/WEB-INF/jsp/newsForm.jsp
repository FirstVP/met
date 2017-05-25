<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html>
<head>
    <s:if test="news==null || news.newsId == null">
        <title>Add news</title>
    </s:if>
    <s:else>
        <title>Update news</title>
    </s:else>
</head>
<body>
<s:if test="news==null || news.newsId == null">
    <h1>Add news</h1>
</s:if>
<s:else>
    <h1>Update news</h1>
</s:else>
<s:actionerror />
<s:actionmessage />
<s:form  theme="simple" action="saveNews" method="post">

<div class="form-group">
    <s:fielderror />
    <label for="saveNews_news_title"> Title:</label>
    <s:textfield class="form-control" theme="simple" name="news.title" value="%{news.title}" label="%{getText('label.title')}" size="40"/>
    <label for="saveNews_news_brief"> Brief:</label>
    <s:textfield class="form-control" theme="simple" name="news.brief" value="%{news.brief}" label="%{getText('label.brief')}" size="40"/>
    <label for="saveNews_news_content"> Content:</label>
    <s:textarea class="form-control" theme="simple" name="news.content" value="%{news.content}" label="%{getText('label.content')}" size="20"/>
    <label for="saveNews_news_date"> Date:</label>
    <s:textfield type="date" class="form-control" theme="simple" name="news.date" value="%{news.date.toString()}" label="%{getText('label.date')}" size="20" />
    <s:hidden class="form-control" theme="simple" name="news.newsId" value="%{news.newsId}"/>
    </div >

    <s:submit theme="simple" class="btn btn-primary" value="Save"/>
    <s:submit theme="simple" class="btn btn-default" value="Cancel" action="index"/>


</s:form>

</body>
</html>