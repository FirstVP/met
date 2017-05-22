

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Disasters</title>
</head>
<body>
<s:if test='#session.role_id == 2'>
<s:url var="url" action="inputDisaster" />
<a href="<s:property value="#url"/>" class="btn btn-primary" role="button">Add disaster</a>
    </s:if>




<div class="container">
    <h2>Disasters</h2>
    <p>Disasters info</p>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Is global</th>
            </tr>
            </thead>
            <tbody>


            <s:iterator value="disasters" var="item" status="status">


                <tr>
                    <td><s:property value="name"/></td>
                    <td><s:property value="global"/></td>
                    <s:if test='#session.role_id == 2'>
                    <s:url action="updateDisaster" var="url">
                        <s:param name="disasterId" value="disasterId"/>
                    </s:url>
                    <td><a href="<s:property value="#url"/>" class="btn btn-success" role="button">Edit</a></td>

                    <s:url action="deleteDisaster" var="url">
                        <s:param name="disaster.disasterId" value="disasterId"/>
                    </s:url>
                    <td><a href="<s:property value="#url"/>" onclick="return confirm('Are you sure?')" class="btn btn-danger" role="button">Delete</a></td>
                    </s:if>
                    <!--<s:url action="viewDisaster" var="url">
                        <s:param name="disasterId" value="disasterId"/>
                    </s:url>
                    <td><a href="<s:property value="#url"/>" class="btn btn-default" role="button">More</a></td>-->
                </tr>


            </s:iterator>


            </tbody>
        </table>
    </div>
</div>






</body>
</html>
	