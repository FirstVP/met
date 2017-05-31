

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Disasters</title>
    <link rel="stylesheet" href="css/m.css" />

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
</head>
<body>
<%-- <s:form action="helloWorld">
    <s:textfield label="What is your name?" name="name" />
    <s:textfield label="What is the date?" name="dateNow" />
    <s:submit />
</s:form> --%>


<div class="container" ng-app="myAppDisaster" ng-controller="disastersCtrl">



    <div class="row">
        <div class="col s12">
            <h4>Disasters</h4>
            <div id="modal-disaster-form" class="modal">
                <div class="modal-content">
                    <h4 id="modal-disaster-title">Create New Disaster</h4>
                    <div class="row">


                        <s:fielderror />
                        <label for="disaster_name"> Name:</label>
                        <s:textfield ng-model="name" class="form-control" theme="simple" name="disaster.name" value="%{disaster.name}" label="%{getText('label.name')}" size="40"/>
                        <label for="disaster_global"> Is global:</label>
                        <s:checkbox ng-model="global" class="form-control" theme="simple" name="disaster.global" value="%{disaster.global}" label="%{getText('label.global')}" size="40"/>

                        <div class="input-field col s12">
                            <a id="btn-create-disaster" class="waves-effect waves-light btn margin-bottom-1em" ng-click="createDisaster()"><i class="material-icons left">add</i>Create</a>

                            <a id="btn-update-disaster" class="waves-effect waves-light btn margin-bottom-1em" ng-click="updateDisaster()"><i class="material-icons left">edit</i>Save Changes</a>

                            <a class="modal-action modal-close waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">close</i>Close</a>
                        </div>
                    </div>
                </div>
            </div>



            <table class="hoverable bordered">

                <thead>
                <tr>
                    <th >ID</th>
                    <th >Name</th>
                    <th class="width-30-pct">Is global</th>
                </tr>
                </thead>

                <tbody ng-init="getAll()">
                <tr ng-repeat="d in names | filter:search">
                    <td class="width-30-pct">{{ d.disasterId }}</td>
                    <td class="width-30-pct">{{ d.name }}</td>
                    <td class="width-30-pct" >{{ d.global }}</td>
                    <td>
                        <a ng-click="readOne(d.disasterId)" class="waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">edit</i>Edit</a>
                        <a ng-click="deleteDisaster(d.disasterId)" class="waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">delete</i>Delete</a>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="fixed-action-btn" style="bottom:45px; right:24px;">
                <a class="waves-effect waves-light btn modal-trigger btn-floating btn-large red" href="#modal-disaster-form" ng-click="showCreateForm()"><i class="large material-icons">add</i></a>
            </div>
        </div>
    </div>
</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.0/js/materialize.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.min.js"></script>
<script>
    var app = angular.module('myAppDisaster', []);

    app.controller('disastersCtrl', function($scope, $http) {
        $scope.showCreateForm = function(){
            // clear form
            $scope.clearForm();

            // change modal title
            $('#modal-disaster-title').text("Create New Disaster");

            // hide update disaster button
            $('#btn-update-disaster').hide();

            // show create disaster button
            $('#btn-create-disaster').show();

        }
        $scope.clearForm = function(){
            $scope.name = "";
            $scope.global = 0;
        }
        $scope.createDisaster = function(){

            // fields in key-value pairs
            $http.post('adddisaster.action', {
                    'name' : $scope.name,
                    'global' : $scope.global,
                }
            ).success(function (data, status, headers, config) {
                console.log(data);

                // close modal
                $('#modal-disaster-form').closeModal();

                // clear modal content
                $scope.clearForm();

                // refresh the list
                $scope.getAll();
            });
        }
        $scope.getAll = function(){
            $http.get("alldisaster.action").success(function(response){
                $scope.names = response.records;
            });
        }
        $scope.readOne = function(disasterId){

            $('#modal-disaster-title').text("Edit Disaster");
            $('#btn-update-disaster').show();
            $('#btn-create-disaster').hide();

            $http.post("readdisaster.action", {
                'disasterId' : disasterId
            })
                .success(function(data, status, headers, config){

                    $scope.disasterId = data['data']["disasterId"];
                    $scope.name = data['data']["name"];
                    $scope.global = data['data']["global"];

                    $('#modal-disaster-form').openModal();
                })
                .error(function(data, status, headers, config){
                    Materialize.toast('Unable to retrieve record.', 4000);
                });
        }
        $scope.updateDisaster = function(){

            $http.post('updatedisaster.action', {
                'disasterId' : $scope.disasterId,
                'name' : $scope.name,
                'global': $scope.global,
            })
                .success(function (data, status, headers, config){
                    $('#modal-disaster-form').closeModal();
                    $scope.clearForm();
                    $scope.getAll();
                });
        }
        $scope.deleteDisaster = function(disasterId){

            // ask the user if he is sure to delete the record
            if(confirm("Are you sure?")){
                // post the id of disaster to be deleted
                $http.post("deletedisaster.action", {
                    'disasterId' : disasterId
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
