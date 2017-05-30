

<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Index</title>
    <link rel="stylesheet" href="css/materialize.min.css" />

    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
</head>
<body>
<%-- <s:form action="helloWorld">
    <s:textfield label="What is your name?" name="name" />
    <s:textfield label="What is the date?" name="dateNow" />
    <s:submit />
</s:form> --%>


<div class="container" ng-app="myApp" ng-controller="productsCtrl">
    <div class="row">
        <div class="col s12">
            <h4>News</h4>
            <div id="modal-product-form" class="modal">
                <div class="modal-content">
                    <h4 id="modal-product-title">Create New Product</h4>
                    <div class="row">


                        <s:fielderror />
                        <label for="saveNews_news_title"> Title:</label>
                        <s:textfield ng-model="title" class="form-control" theme="simple" name="news.title" value="%{news.title}" label="%{getText('label.title')}" size="40"/>
                        <label for="saveNews_news_brief"> Brief:</label>
                        <s:textfield ng-model="brief" class="form-control" theme="simple" name="news.brief" value="%{news.brief}" label="%{getText('label.brief')}" size="40"/>
                        <label for="saveNews_news_content"> Content:</label>
                        <s:textarea ng-model="content" class="form-control" theme="simple" name="news.content" value="%{news.content}" label="%{getText('label.content')}" size="20"/>
                        <label for="saveNews_news_date"> Date:</label>
                        <s:textfield ng-model="date" type="date" class="form-control" theme="simple" name="news.date" value="%{news.date.toString()}" label="%{getText('label.date')}" size="20" />


                        <div class="input-field col s12">
                            <a id="btn-create-product" class="waves-effect waves-light btn margin-bottom-1em" ng-click="createProduct()"><i class="material-icons left">add</i>Create</a>

                            <a id="btn-update-product" class="waves-effect waves-light btn margin-bottom-1em" ng-click="updateProduct()"><i class="material-icons left">edit</i>Save Changes</a>

                            <a class="modal-action modal-close waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">close</i>Close</a>
                        </div>
                    </div>
                </div>
            </div>

            <table class="hoverable bordered">

                <thead>
                <tr>
                    <th >ID</th>
                    <th >Title</th>
                    <th class="width-30-pct">Brief</th>
                    <th class="width-30-pct">Content</th>
                    <th class="width-30-pct">Date</th>
                </tr>
                </thead>

                <tbody ng-init="getAll()">
                <tr ng-repeat="d in names | filter:search">
                    <td class="width-30-pct">{{ d.newsId }}</td>
                    <td class="width-30-pct">{{ d.title }}</td>
                    <td class="width-30-pct">{{ d.brief }}</td>
                    <td class="width-30-pct">{{ d.content }}</td>
                    <td class="width-30-pct" >{{ d.date }}</td>
                    <td>
                        <a ng-click="readOne(d.newsId)" class="waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">edit</i>Edit</a>
                        <a ng-click="deleteProduct(d.newsId)" class="waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">delete</i>Delete</a>
                    </td>
                </tr>
                </tbody>
            </table>

            <div class="fixed-action-btn" style="bottom:45px; right:24px;">
                <a class="waves-effect waves-light btn modal-trigger btn-floating btn-large red" href="#modal-product-form" ng-click="showCreateForm()"><i class="large material-icons">add</i></a>
            </div>
        </div>
    </div>
</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.0/js/materialize.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.5.7/angular.min.js"></script>
<script>
    var app = angular.module('myApp', []);
    app.controller('productsCtrl', function($scope, $http) {
        $scope.showCreateForm = function(){
            // clear form
            $scope.clearForm();

            // change modal title
            $('#modal-product-title').text("Create New Product");

            // hide update product button
            $('#btn-update-product').hide();

            // show create product button
            $('#btn-create-product').show();

        }
        $scope.clearForm = function(){
            $scope.title = "";
            $scope.brief = "";
            $scope.content = "";
            $scope.date = "";
        }
        $scope.createProduct = function(){

            // fields in key-value pairs
            $http.post('addproduct.action', {
                    'title' : $scope.title,
                    'brief' : $scope.brief,
                    'content': $scope.content,
                    'date' : $scope.date
                }
            ).success(function (data, status, headers, config) {
                console.log(data);

                // close modal
                $('#modal-product-form').closeModal();

                // clear modal content
                $scope.clearForm();

                // refresh the list
                $scope.getAll();
            });
        }
        $scope.getAll = function(){
            $http.get("readproducts.action").success(function(response){


                $scope.names = response.records;
                $scope.names.forEach(function(item, i, arr) {
                    item["date"] = item["date"].split('T')[0];
                });

            });
        }
        $scope.readOne = function(newsId){

            $('#modal-product-title').text("Edit News");
            $('#btn-update-product').show();
            $('#btn-create-product').hide();

            $http.post("readoneproducts.action", {
                'newsId' : newsId
            })
                .success(function(data, status, headers, config){

                    $scope.newsId = data['newsData']["newsId"];
                    $scope.title = data['newsData']["title"];
                    $scope.brief = data['newsData']["brief"];
                    $scope.content = data['newsData']["content"];
                    $scope.date = new Date(data['newsData']["date"]);

                    $('#modal-product-form').openModal();
                })
                .error(function(data, status, headers, config){
                    Materialize.toast('Unable to retrieve record.', 4000);
                });
        }
        $scope.updateProduct = function(){
            $http.post('updateproducts.action', {
                'newsId' : $scope.newsId,
                'title' : $scope.title,
                'brief' : $scope.brief,
                'content' : $scope.content,
                'date' : $scope.date
            })
                .success(function (data, status, headers, config){
                    $('#modal-product-form').closeModal();
                    $scope.clearForm();
                    $scope.getAll();
                });
        }
        $scope.deleteProduct = function(newsId){

            // ask the user if he is sure to delete the record
            if(confirm("Are you sure?")){
                // post the id of product to be deleted
                $http.post("deleteproduct.action", {
                    'newsId' : newsId
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
