<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 22.05.2017
  Time: 4:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>About city</title>
</head>
<body>


    <h2><s:property value="%{city.name}"/></h2>
    <div class="panel-group">
        <div class="panel panel-default">
            <div class="panel-heading">Information
                <s:if test='#session.role_id == 2'>
                <s:url action="updateCity" var="url">
                    <s:param name="cityId" value="cityId"/>
                </s:url>
                <a href="<s:property value="#url"/>" class="btn btn-success" role="button">Edit</a>

                <s:url action="deleteCity" var="url">
                    <s:param name="city.cityId" value="cityId"/>
                </s:url>
                <a href="<s:property value="#url"/>" onclick="return confirm('Are you sure?')" class="btn btn-danger" role="button">Delete</a>
                    </s:if>
            </div>
            <div class="panel-body">
                <p>Rise: <s:property value="%{city.rise}"/> m</p>
                <p>Square: <s:property value="%{city.square}"/> m^2</p>
                <p>Population: <s:property value="%{city.population}"/></p>
            </div>
        </div>

        <div class="panel panel-primary">
            <div class="panel-heading">Humidity</div>
            <div class="panel-body"> <img src="http://pogoda.by/mg/366/egrr_T<s:property value="%{city.code}"/>.gif" class="img-rounded img-responsive" border="1" ></div>
        </div>

        <div class="panel panel-info">
            <div class="panel-heading">Wind</div>
            <div class="panel-body"><img src="http://pogoda.by/mg/366/egrr_W<s:property value="%{city.code}"/>.gif" class="img-rounded img-responsive" border="1" ></div>
        </div>

    </div>

<div class="container">

    <h2>Weather
<s:if test='#session.role_id == 2'>
        <s:url action="inputWeather" var="url">
            <s:param name="weather.cityId" value="cityId"/>
        </s:url>
        <a href="<s:property value="#url"/>" class="btn btn-primary" role="button">Add weather</a></h2>
    </s:if>
    <p>Weather info</p>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Day</th>
                <th>Air temperature, °C</th>
                <th>Condition</th>
                <th>Wind, m/s</th>
                <th>Atmosphere pressure, kPa</th>
                <th></th>
                <th></th>
            </tr>
            </thead>
            <tbody>


            <s:iterator value="weathers" var="item" status="status">


                <tr>
                    <td><s:property value="date"/></td>
                    <td><s:property value="temp"/></td>
                    <td><s:property value="type.name"/>
                        <img src="<s:property value="type.image"/>" alt="<s:property value="type.name"/>">
                    </td>
                    <td><s:property value="wind"/></td>
                    <td><s:property value="pressure"/></td>
                    <s:if test='#session.role_id == 2'>
                    <s:url action="updateWeather" var="url">
                        <s:param name="weatherId" value="weatherId"/>
                    </s:url>
                    <td><a href="<s:property value="#url"/>" class="btn btn-success" role="button">Edit</a></td>

                    <s:url action="deleteWeather" var="url" escapeAmp="false">
                        <s:param name="weather.weatherId" value="weatherId"/>
                        <s:param name="weather.cityId" value="cityId"/>
                    </s:url>
                    <td><a href="<s:property value="#url"/>" onclick="return confirm('Are you sure?')" class="btn btn-danger" role="button">Delete</a></td>
                    </s:if>




                </tr>


            </s:iterator>


            </tbody>
        </table>
</div>



</body>
</html>
