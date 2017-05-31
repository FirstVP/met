

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Accidents</title>
    <link rel="stylesheet" href="css/materialize.min.css" />

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
</head>
<body>
<%-- <s:form action="helloWorld">
    <s:textfield label="What is your name?" name="name" />
    <s:textfield label="What is the date?" name="dateNow" />
    <s:submit />
</s:form> --%>


<div class="container" ng-app="myAppAccident" ng-controller="accidentsCtrl">
    <div class="row">
        <div class="col s12">
            <h4>Accidents</h4>
            <div id="modal-accident-form" class="modal">
                <div class="modal-content">
                    <h4 id="modal-accident-title">Create New Accident</h4>
                    <div class="row">


                        <s:fielderror />
                        <label  for="saveAccident_accident_disasterId"> Disaster:</label>

                        <s:select ng-model="disasterId" class="form-control" theme="simple" list="disasters" listKey="disasterId" listValue="name" name="accident.disasterId" value="%{accident.disasterId}"/>
                        <label for="saveAccident_accident_cityId"> City:</label>
                        <s:select ng-model="cityId" class="form-control" theme="simple" list="cities" listKey="cityId" listValue="name" name="accident.cityId" value="%{accident.cityId}"/>
                        <label for="saveAccident_accident_level"> Level:</label>
                        <s:select ng-model="level" class="form-control"
                                  list="#{'1':'1', '2':'2', '3':'3'}"
                                  name="accident.level"
                                  value="accident.level" />
                        <label for="saveAccident_accident_content"> Description:</label>
                        <s:textarea ng-model="content"  class="form-control" theme="simple" name="accident.content" value="%{accident.content}" label="%{getText('label.content')}" size="40"/>
                        <label for="saveAccident_accident_date"> Date:</label>
                        <s:textfield ng-model="date" type="date" class="form-control" theme="simple" name="accident.date" value="%{accident.date.toString()}" label="%{getText('label.date')}" size="20" />


                        <div class="input-field col s12">
                            <a id="btn-create-accident" class="waves-effect waves-light btn margin-bottom-1em" ng-click="createAccident()"><i class="material-icons left">add</i>Create</a>

                            <a id="btn-update-accident" class="waves-effect waves-light btn margin-bottom-1em" ng-click="updateAccident()"><i class="material-icons left">edit</i>Save Changes</a>

                            <a class="modal-action modal-close waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">close</i>Close</a>
                        </div>
                    </div>
                </div>
            </div>

            <table class="hoverable bordered">

                <thead>
                <tr>
                    <th >ID</th>
                    <th >Disaster</th>
                    <th class="width-30-pct">City</th>
                    <th class="width-30-pct">Level</th>
                    <th class="width-30-pct">Content</th>
                    <th class="width-30-pct">Date</th>
                </tr>
                </thead>

                <tbody ng-init="getAll()">
                <tr ng-repeat="d in names | filter:search">
                    <td class="width-30-pct">{{ d.accidentId }}</td>
                    <td class="width-30-pct">{{ d.disaster.name }}</td>
                    <td class="width-30-pct">{{ d.city.name }}</td>
                    <td class="width-30-pct">{{ d.level }}</td>
                    <td class="width-30-pct">{{ d.content }}</td>
                    <td class="width-30-pct" >{{ d.date }}</td>
                    <td>
                        <a ng-click="readOne(d.accidentId)" class="waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">edit</i>Edit</a>
                        <a ng-click="deleteAccident(d.accidentId)" class="waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">delete</i>Delete</a>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="fixed-action-btn" style="bottom:45px; right:24px;">
                <a class="waves-effect waves-light btn modal-trigger btn-floating btn-large red" href="#modal-accident-form" ng-click="showCreateForm()"><i class="large material-icons">add</i></a>
            </div>
        </div>
    </div>
</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.0/js/materialize.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.min.js"></script>
<script>
    var app = angular.module('myAppAccident', []);

    app.controller('accidentsCtrl', function($scope, $http) {
        $scope.showCreateForm = function(){
            // clear form
            $scope.clearForm();

            // change modal title
            $('#modal-accident-title').text("Create New Accident");

            // hide update accident button
            $('#btn-update-accident').hide();

            // show create accident button
            $('#btn-create-accident').show();

        }
        $scope.clearForm = function(){
            $scope.disasterId = "";
            $scope.cityId = "";
            $scope.level = "";
            $scope.content = "";
            $scope.date = "";
        }
        $scope.createAccident = function(){

            // fields in key-value pairs
            $http.post('addaccident.action', {
                    'disasterId' : $scope.disasterId,
                    'cityId' : $scope.cityId,
                    'level' : $scope.level,
                    'content': $scope.content,
                    'date' : $scope.date
                }
            ).success(function (data, status, headers, config) {
                console.log(data);

                // close modal
                $('#modal-accident-form').closeModal();

                // clear modal content
                $scope.clearForm();

                // refresh the list
                $scope.getAll();
            });
        }
        $scope.getAll = function(){
            $http.get("allaccident.action").success(function(response){


                $scope.names = response.records;
                $scope.names.forEach(function(item, i, arr) {
                    item["date"] = item["date"].split('T')[0];
                });

            });
        }
        $scope.readOne = function(accidentId){

            $('#modal-accident-title').text("Edit Accident");
            $('#btn-update-accident').show();
            $('#btn-create-accident').hide();

            $http.post("readaccident.action", {
                'accidentId' : accidentId
            })
                .success(function(data, status, headers, config){

                    $scope.accidentId = data['data']["accidentId"];
                    $scope.disasterId = data['data']["disasterId"];
                    $scope.cityId = data['data']["cityId"];
                    $scope.level = data['data']["level"];
                    $scope.content = data['data']["content"];
                    $scope.date = new Date(data['data']["date"]);

                    $('#modal-accident-form').openModal();
                })
                .error(function(data, status, headers, config){
                    Materialize.toast('Unable to retrieve record.', 4000);
                });
        }
        $scope.updateAccident = function(){

            $http.post('updateaccident.action', {
                'accidentId' : $scope.accidentId,
                'disasterId' : $scope.disasterId,
                'cityId' : $scope.cityId,
                'level' : $scope.level,
                'content': $scope.content,
                'date' : $scope.date
            })
                .success(function (data, status, headers, config){
                    $('#modal-accident-form').closeModal();
                    $scope.clearForm();
                    $scope.getAll();
                });
        }
        $scope.deleteAccident = function(accidentId){

            // ask the user if he is sure to delete the record
            if(confirm("Are you sure?")){
                // post the id of accident to be deleted
                $http.post("deleteaccident.action", {
                    'accidentId' : accidentId
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
