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
<p><s:property value="%{city.code}"/></p>
<p><s:property value="%{city.name}"/></p>
<p><s:property value="%{city.rise}"/></p>
<p><s:property value="%{city.square}"/></p>
<p><s:property value="%{city.population}"/></p>
<s:url action="updateCity" var="url">
    <s:param name="cityId" value="cityId"/>
</s:url>
<a href="<s:property value="#url"/>" class="btn btn-success" role="button">Edit</a>

<s:url action="deleteCity" var="url">
    <s:param name="city.cityId" value="cityId"/>
</s:url>
<a href="<s:property value="#url"/>" onclick="return confirm('Are you sure?')" class="btn btn-danger" role="button">Delete</a>

<div class="container">
    <h2>Weather
        <s:url action="inputWeather" var="url">
            <s:param name="weather.cityId" value="cityId"/>
        </s:url>
        <a href="<s:property value="#url"/>" class="btn btn-primary" role="button">Add weather</a></h2>
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

                    <s:url action="updateWeather" var="url">
                        <s:param name="weatherId" value="weatherId"/>
                    </s:url>
                    <td><a href="<s:property value="#url"/>" class="btn btn-success" role="button">Edit</a></td>

                    <s:url action="deleteWeather" var="url" escapeAmp="false">
                        <s:param name="weather.weatherId" value="weatherId"/>
                        <s:param name="weather.cityId" value="cityId"/>
                    </s:url>
                    <td><a href="<s:property value="#url"/>" onclick="return confirm('Are you sure?')" class="btn btn-danger" role="button">Delete</a></td>





                </tr>


            </s:iterator>


            </tbody>
        </table>
</div>



</body>
</html>
