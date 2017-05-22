
	
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Index</title>
</head>
<body>
	<%-- <s:form action="helloWorld">
		<s:textfield label="What is your name?" name="name" />
		<s:textfield label="What is the date?" name="dateNow" />
		<s:submit />
	</s:form> --%>
<s:if test='#session.role_id == 2'>
	<s:url var="url" action="inputNews" />
	<a href="<s:property value="#url"/>" class="btn btn-primary" role="button">Add news</a>
</s:if>




		<s:iterator value="news" var="item" status="status">
			<h2><s:property value="date"/>&nbsp;<s:property value="title"/>

				<s:if test='#session.role_id == 2'>
					<s:url action="updateNews" var="url">
						<s:param name="newsId" value="newsId"/>
					</s:url>
					<a href="<s:property value="#url"/>" class="btn btn-success" role="button">Edit</a>
					<s:url action="deleteNews" var="url">
						<s:param name="news.newsId" value="newsId"/>
					</s:url>
					<a href="<s:property value="#url"/>" onclick="return confirm('Are you sure?')" class="btn btn-danger" role="button">Delete</a>
				</s:if>


			</h2>
			<p><s:property value="brief"/></p>
			<s:url action="viewNews" var="url">
				<s:param name="newsId" value="newsId"/>
			</s:url>
			<a href="<s:property value="#url"/>" class="btn btn-default" role="button">More</a>
			<hr />



		</s:iterator>

</body>
</html>
	