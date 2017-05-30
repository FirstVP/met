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

    <link rel="stylesheet" href="css/materialize.min.css" />

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
</head>
<body>


<h2><s:property value="%{city.name}"/></h2>
<div class="panel-group">
    <div class="panel panel-default">
        <div class="panel-heading">Information
            <s:if test='#session.role_id == 5'>
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
    <s:if test='#session.role_id != null'>
        <s:url var="url" action="viewPDF" escapeAmp="false">
            <s:param name="type">CityStats <s:property value="%{city.cityId}"/></s:param>
            <s:param name="hasProtection">false</s:param>
        </s:url>
        <li><a href="<s:property value="#url"/>">Get weather statistics of the city (PDF)</a></li>

        <s:url var="url" action="viewPDF" escapeAmp="false">
            <s:param name="type">CityStats <s:property value="%{city.cityId}"/></s:param>
            <s:param name="hasProtection">true</s:param>
        </s:url>
        <li><a href="<s:property value="#url"/>">Get weather statistics of the city (Protected PDF)</a></li>

        <s:url var="url" action="viewXLS" escapeAmp="false">
            <s:param name="type">CityStats <s:property value="%{city.cityId}"/></s:param>
        </s:url>
        <li><a href="<s:property value="#url"/>">Get weather statistics of the city (XLS)</a></li>

        <s:url var="url" action="viewCSV" escapeAmp="false">
            <s:param name="type">CityStats <s:property value="%{city.cityId}"/></s:param>
        </s:url>
        <li><a href="<s:property value="#url"/>">Get weather statistics of the city (CSV)</a></li>
    </s:if >
    <div class="panel panel-primary">
        <div class="panel-heading">Humidity</div>
        <div class="panel-body"> <img src="http://pogoda.by/mg/366/egrr_T<s:property value="%{city.code}"/>.gif" class="img-rounded img-responsive" border="1" ></div>
    </div>

    <div class="panel panel-info">
        <div class="panel-heading">Wind</div>
        <div class="panel-body"><img src="http://pogoda.by/mg/366/egrr_W<s:property value="%{city.code}"/>.gif" class="img-rounded img-responsive" border="1" ></div>
    </div>

</div>

<div class="container" ng-app="myAppWeather" ng-controller="weathersCtrl">
    <div class="row">
        <div class="col s12">
            <h4>Weather</h4>
            <div id="modal-weather-form" class="modal">
                <div class="modal-content">
                    <h4 id="modal-weather-title">Create New Weather</h4>
                    <div class="row">


                        <s:fielderror />

                        <label for="saveWeather_weather_typeId"> Condition:</label>
                        <s:select ng-model="typeId" class="form-control" theme="simple" list="types" listKey="typeId" listValue="name" name="weather.typeId" value="%{weather.typeId}"/>
                        <label for="saveWeather_weather_temp"> Air temperature:</label>
                        <s:textfield ng-model="temp" type="number" class="form-control" theme="simple" name="weather.temp" value="%{weather.temp}" label="%{getText('label.temp')}" size="40"/>
                        <label for="saveWeather_weather_wind"> Wind speed:</label>
                        <s:textfield ng-model="wind" type="number" class="form-control" theme="simple" name="weather.wind" value="%{weather.wind}" label="%{getText('label.wind')}" size="40"/>
                        <label for="saveWeather_weather_pressure"> Atmospheric pressure:</label>
                        <s:textfield ng-model="pressure" type="number" class="form-control" theme="simple" name="weather.pressure" value="%{weather.pressure}" label="%{getText('label.pressure')}" size="40"/>
                        <label for="saveWeather_weather_date"> Date:</label>
                        <s:textfield ng-model="date" type="date" class="form-control" theme="simple" name="weather.date" value="%{weather.date.toString()}" label="%{getText('label.date')}" size="20" />
                        <s:hidden ng-model="cityId" class="form-control" theme="simple" name="weather.cityId" value="%{weather.cityId}"/>


                        <div class="input-field col s12">
                            <a id="btn-create-weather" class="waves-effect waves-light btn margin-bottom-1em" ng-click="createWeather()"><i class="material-icons left">add</i>Create</a>

                            <a id="btn-update-weather" class="waves-effect waves-light btn margin-bottom-1em" ng-click="updateWeather()"><i class="material-icons left">edit</i>Save Changes</a>

                            <a class="modal-action modal-close waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">close</i>Close</a>
                        </div>
                    </div>
                </div>
            </div>

            <table class="hoverable bordered">

                <thead>
                <tr>
                    <th >ID</th>
                    <th >Day</th>
                    <th >Temperature</th>
                    <th class="width-30-pct">Condition</th>
                    <th class="width-30-pct">Wind</th>
                    <th class="width-30-pct">Pressure</th>
                </tr>
                </thead>

                <tbody ng-init="getAll(<s:property value="cityId"/>)">
                <tr ng-repeat="d in names | filter:search">
                    <td class="width-30-pct">{{ d.weatherId }}</td>
                    <td class="width-30-pct">{{ d.date }}</td>
                    <td class="width-30-pct" >{{ d.temp }}</td>
                    <td class="width-30-pct" >{{ d.type.name }}
                        <img src="{{ d.type.image }}" alt="{{ d.type.image }}">
                    </td>
                    <td class="width-30-pct">{{ d.wind }}</td>
                    <td class="width-30-pct">{{ d.pressure }}</td>
                    <td>
                        <a ng-click="readOne(d.weatherId)" class="waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">edit</i>Edit</a>
                        <a ng-click="deleteWeather(d.weatherId)" class="waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">delete</i>Delete</a>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="fixed-action-btn" style="bottom:45px; right:24px;">
                <a class="waves-effect waves-light btn modal-trigger btn-floating btn-large red" href="#modal-weather-form" ng-click="showCreateForm()"><i class="large material-icons">add</i></a>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.0/js/materialize.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.min.js"></script>
<script>
    var app = angular.module('myAppWeather', []);

    app.controller('weathersCtrl', function($scope, $http) {
        $scope.showCreateForm = function(){
            // clear form
            $scope.clearForm();

            // change modal title
            $('#modal-weather-title').text("Create New Weather");

            // hide update weather button
            $('#btn-update-weather').hide();

            // show create weather button
            $('#btn-create-weather').show();

        }
        $scope.clearForm = function(){
            $scope.typeId = "";
            $scope.cityId = "<s:property value="cityId"/>";
            $scope.temp = "";
            $scope.wind = "";
            $scope.pressure = "";
            $scope.date = "";
        }
        $scope.createWeather = function(){

            // fields in key-value pairs
            $http.post('addweather.action', {
                    'typeId' : $scope.typeId,
                    'cityId' : $scope.cityId,
                    'temp': $scope.temp,
                    'wind' : $scope.wind,
                    'pressure' : $scope.pressure,
                    'date' : $scope.date
                }
            ).success(function (data, status, headers, config) {
                console.log(data);

                // close modal
                $('#modal-weather-form').closeModal();

                // clear modal content
                $scope.clearForm();

                // refresh the list
                $scope.getAll(<s:property value="cityId"/>);
            });
        }
        $scope.getAll = function(cityId){
            $http.post("allweather.action", {
                'cityId' : cityId
            }).success(function(response){
                $scope.names = response.records;
                $scope.names.forEach(function(item, i, arr) {
                    item["date"] = item["date"].split('T')[0];
                });
            });
        }
        $scope.readOne = function(weatherId){

            $('#modal-weather-title').text("Edit Weather");
            $('#btn-update-weather').show();
            $('#btn-create-weather').hide();

            $http.post("readweather.action", {
                'weatherId' : weatherId
            })
                .success(function(data, status, headers, config){

                    $scope.weatherId = data['data']["weatherId"];
                    $scope.typeId = data['data']["typeId"];
                    $scope.cityId = data['data']["cityId"];
                    $scope.temp = data['data']["temp"];
                    $scope.wind = data['data']["wind"];
                    $scope.pressure = data['data']["pressure"];
                    $scope.date = new Date(data['data']["date"]);

                    $('#modal-weather-form').openModal();
                })
                .error(function(data, status, headers, config){
                    Materialize.toast('Unable to retrieve record.', 4000);
                });
        }
        $scope.updateWeather = function(){

            $http.post('updateweather.action', {
                'weatherId' : $scope.weatherId,
                'typeId' : $scope.typeId,
                'cityId' : $scope.cityId,
                'temp': $scope.temp,
                'wind' : $scope.wind,
                'pressure' : $scope.pressure,
                'date' : $scope.date
            })
                .success(function (data, status, headers, config){
                    $('#modal-weather-form').closeModal();
                    $scope.clearForm();
                    $scope.getAll(<s:property value="cityId"/>);
                });
        }
        $scope.deleteWeather = function(weatherId){

            // ask the user if he is sure to delete the record
            if(confirm("Are you sure?")){
                // post the id of weather to be deleted
                $http.post("deleteweather.action", {
                    'weatherId' : weatherId
                }).success(function (data, status, headers, config){
                    $scope.getAll(<s:property value="cityId"/>);
                });
            }
        }
    });

    $(document).ready(function(){
        // initialize modal
        $('.modal-trigger').leanModal();
    });
    // jquery codes will be here

</script>
</body>
</html>
