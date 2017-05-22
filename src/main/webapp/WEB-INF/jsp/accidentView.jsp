<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.05.2017
  Time: 11:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>Accident information</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <h3 class="panel-title">Information

                        <s:url action="updateAccident" var="url">
                            <s:param name="accidentId" value="accidentId"/>
                        </s:url>
                        <a href="<s:property value="#url"/>" class="btn btn-success" role="button">Edit</a>

                        <s:url action="deleteAccident" var="url">
                            <s:param name="accident.accidentId" value="accidentId"/>
                        </s:url>
                        <a href="<s:property value="#url"/>" onclick="return confirm('Are you sure?')" class="btn btn-danger" role="button">Delete</a>
                    </h3>
                    <span class="pull-right">
                        <ul class="nav panel-tabs">
                            <li class="active"><a href="#tab1" data-toggle="tab">Disaster: short info</a></li>
                            <li><a href="#tab2" data-toggle="tab">Level</a></li>
                            <li><a href="#tab3" data-toggle="tab">Content</a></li>
                        </ul>
                    </span>
                </div>
                <div class="panel-body">
                    <div class="tab-content">
                        <div class="tab-pane active" id="tab1"><s:property value="%{accident.disaster.name}"/>, <s:property value="%{accident.city.name}"/>, <s:property value="%{accident.date}"/></div>
                        <div class="tab-pane" id="tab2"><s:property value="%{accident.level}"/></div>
                        <div class="tab-pane" id="tab3"><s:property value="%{accident.content}"/></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
