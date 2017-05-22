

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Cities</title>
</head>
<body>

<s:url var="url" action="inputCity" />
<a href="<s:property value="#url"/>" class="btn btn-primary" role="button">Add city</a>


<s:iterator value="weathers" var="item" status="status">

        <p><s:property value="date"/></p>
    <p><s:property value="type.name"/></p>



</s:iterator>


<div class="container">
    <h2>Cities</h2>
    <p>Cities info</p>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>Code</th>
                <th>Name</th>
                <th>Rise</th>
                <th>Square</th>
                <th>Population</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>


            <s:iterator value="cities" var="item" status="status">


                <tr>
                    <td><s:property value="code"/></td>
                    <td><s:property value="name"/></td>
                    <td><s:property value="rise"/></td>
                    <td><s:property value="square"/></td>
                    <td><s:property value="population"/></td>

                    <s:url action="updateCity" var="url">
                        <s:param name="cityId" value="cityId"/>
                    </s:url>
                    <td><a href="<s:property value="#url"/>" class="btn btn-success" role="button">Edit</a></td>

                    <s:url action="deleteCity" var="url">
                        <s:param name="city.cityId" value="cityId"/>
                    </s:url>
                    <td><a href="<s:property value="#url"/>" onclick="return confirm('Are you sure?')" class="btn btn-danger" role="button">Delete</a></td>
                </tr>


            </s:iterator>


            </tbody>
        </table>
    </div>
</div>






</body>
</html>
	