

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Cities</title>
    <link rel="stylesheet" href="css/materialize.min.css" />

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
</head>
<body>
<%-- <s:form action="helloWorld">
    <s:textfield label="What is your name?" name="name" />
    <s:textfield label="What is the date?" name="dateNow" />
    <s:submit />
</s:form> --%>


<div class="container" ng-app="myAppCity" ng-controller="citysCtrl">
    <div class="row">
        <div class="col s12">
            <h4>City</h4>
            <div id="modal-city-form" class="modal">
                <div class="modal-content">
                    <h4 id="modal-city-title">Create New City</h4>
                    <div class="row">


                        <s:fielderror />
                        <label for="saveCity_city_code"> City code:</label>
                        <s:textfield ng-model="code" type="number" class="form-control" theme="simple" name="city.code" value="%{city.code}" label="%{getText('label.code')}" size="40"/>
                        <label for="saveCity_city_name"> Name:</label>
                        <s:textfield ng-model="name" class="form-control" theme="simple" name="city.name" value="%{city.name}" label="%{getText('label.name')}" size="40"/>
                        <label for="saveCity_city_rise"> Sea level rise:</label>
                        <s:textfield ng-model="rise" type="number" class="form-control" theme="simple" name="city.rise" value="%{city.rise}" label="%{getText('label.rise')}" size="40"/>
                        <label for="saveCity_city_square"> City square:</label>
                        <s:textfield ng-model="square" type="number" class="form-control" theme="simple" name="city.square" value="%{city.square}" label="%{getText('label.square')}" size="40"/>
                        <label for="saveCity_city_population"> City population:</label>
                        <s:textfield ng-model="population" type="number" class="form-control" theme="simple" name="city.population" value="%{city.population}" label="%{getText('label.population')}" size="40"/>


                        <div class="input-field col s12">
                            <a id="btn-create-city" class="waves-effect waves-light btn margin-bottom-1em" ng-click="createCity()"><i class="material-icons left">add</i>Create</a>

                            <a id="btn-update-city" class="waves-effect waves-light btn margin-bottom-1em" ng-click="updateCity()"><i class="material-icons left">edit</i>Save Changes</a>

                            <a class="modal-action modal-close waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">close</i>Close</a>
                        </div>
                    </div>
                </div>
            </div>

            <table class="hoverable bordered">

                <thead>
                <tr>
                    <th >ID</th>
                    <th >Code</th>
                    <th class="width-30-pct">Name</th>
                    <th class="width-30-pct">Rise</th>
                    <th class="width-30-pct">Square</th>
                    <th class="width-30-pct">Population</th>
                </tr>
                </thead>

                <tbody ng-init="getAll()">
                <tr ng-repeat="d in names | filter:search">
                    <td class="width-30-pct">{{ d.cityId }}</td>
                    <td class="width-30-pct">{{ d.code }}</td>
                    <td class="width-30-pct" >{{ d.name }}</td>
                    <td class="width-30-pct">{{ d.rise }}</td>
                    <td class="width-30-pct">{{ d.square }}</td>
                    <td class="width-30-pct" >{{ d.population }}</td>
                    <td>
                        <a ng-click="readOne(d.cityId)" class="waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">edit</i>Edit</a>
                        <a ng-click="deleteCity(d.cityId)" class="waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">delete</i>Delete</a>
                        <a href="viewCity.action?cityId={{ d.cityId }}" class="waves-effect waves-light btn margin-bottom-1em"><i class="material-icons note">more</i>More</a>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="fixed-action-btn" style="bottom:45px; right:24px;">
                <a class="waves-effect waves-light btn modal-trigger btn-floating btn-large red" href="#modal-city-form" ng-click="showCreateForm()"><i class="large material-icons">add</i></a>
            </div>
        </div>
    </div>
</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.0/js/materialize.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.min.js"></script>
<script>
    var app = angular.module('myAppCity', []);

    app.controller('citysCtrl', function($scope, $http) {
        $scope.showCreateForm = function(){
            // clear form
            $scope.clearForm();

            // change modal title
            $('#modal-city-title').text("Create New City");

            // hide update city button
            $('#btn-update-city').hide();

            // show create city button
            $('#btn-create-city').show();

        }
        $scope.clearForm = function(){
            $scope.code = "";
            $scope.name = "";
            $scope.rise = "";
            $scope.square = "";
            $scope.population = "";
        }
        $scope.createCity = function(){

            // fields in key-value pairs
            $http.post('addcity.action', {
                    'code' : $scope.code,
                    'name' : $scope.name,
                    'rise': $scope.rise,
                    'square' : $scope.square,
                    'population' : $scope.population
                }
            ).success(function (data, status, headers, config) {
                console.log(data);

                // close modal
                $('#modal-city-form').closeModal();

                // clear modal content
                $scope.clearForm();

                // refresh the list
                $scope.getAll();
            });
        }
        $scope.getAll = function(){
            $http.get("allcity.action").success(function(response){
                $scope.names = response.records;
            });
        }
        $scope.readOne = function(cityId){

            $('#modal-city-title').text("Edit City");
            $('#btn-update-city').show();
            $('#btn-create-city').hide();

            $http.post("readcity.action", {
                'cityId' : cityId
            })
                .success(function(data, status, headers, config){

                    $scope.cityId = data['data']["cityId"];
                    $scope.code = data['data']["code"];
                    $scope.name = data['data']["name"];
                    $scope.rise = data['data']["rise"];
                    $scope.square = data['data']["square"];
                    $scope.population = data['data']["population"];

                    $('#modal-city-form').openModal();
                })
                .error(function(data, status, headers, config){
                    Materialize.toast('Unable to retrieve record.', 4000);
                });
        }
        $scope.updateCity = function(){

            $http.post('updatecity.action', {
                'cityId' : $scope.cityId,
                'code' : $scope.code,
                'name' : $scope.name,
                'rise': $scope.rise,
                'square' : $scope.square,
                'population' : $scope.population
            })
                .success(function (data, status, headers, config){
                    $('#modal-city-form').closeModal();
                    $scope.clearForm();
                    $scope.getAll();
                });
        }
        $scope.deleteCity = function(cityId){

            // ask the user if he is sure to delete the record
            if(confirm("Are you sure?")){
                // post the id of city to be deleted
                $http.post("deletecity.action", {
                    'cityId' : cityId
                }).success(function (data, status, headers, config){
                    $scope.getAll();
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
