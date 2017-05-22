

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Accidents</title>
</head>
<body>
<s:if test='#session.role_id == 2'>
<s:url var="url" action="inputAccident" />
<a href="<s:property value="#url"/>" class="btn btn-primary" role="button">Add accident</a>
</s:if>




<div class="container">
    <h2>Accidents</h2>
    <p>Accidents info</p>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>Disaster</th>
                <th>City</th>
                <th>Level</th>
                <th>Date</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>


            <s:iterator value="accidents" var="item" status="status">


                <tr>
                    <td><s:property value="disaster.name"/></td>
                    <td><s:property value="city.name"/></td>
                    <td><s:property value="level"/></td>
                    <td><s:property value="date"/></td>
                    <s:if test='#session.role_id == 2'>
                    <s:url action="updateAccident" var="url">
                        <s:param name="accidentId" value="accidentId"/>
                    </s:url>
                    <td><a href="<s:property value="#url"/>" class="btn btn-success" role="button">Edit</a></td>

                    <s:url action="deleteAccident" var="url">
                        <s:param name="accident.accidentId" value="accidentId"/>
                    </s:url>
                    <td><a href="<s:property value="#url"/>" onclick="return confirm('Are you sure?')" class="btn btn-danger" role="button">Delete</a></td>
                    </s:if>
                    <s:url action="viewAccident" var="url">
                        <s:param name="accidentId" value="accidentId"/>
                    </s:url>
                    <td><a href="<s:property value="#url"/>" class="btn btn-default" role="button">More</a></td>
                </tr>


            </s:iterator>


            </tbody>
        </table>
    </div>
</div>






</body>
</html>
	