
	
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
<s:if test='#session.role_id == 2'>
	<s:url var="url" action="inputNews" />
	<a href="<s:property value="#url"/>" class="btn btn-primary" role="button">Add news</a>
</s:if>


	<div class="container" ng-app="myApp" ng-controller="productsCtrl">
		<div class="row">

				<h4>News</h4>
			<div id="modal-product-form" class="modal">
				<div class="modal-content">
					<h4 id="modal-product-title">Create New Product</h4>
					<div class="row">
						<div class="input-field col s12">
							<input ng-model="title" type="text" class="validate" id="form-name" placeholder="Type name here..." />
							<label for="title">Name</label>
						</div>

						<div class="input-field col s12">
							<textarea ng-model="brief" type="text" class="validate materialize-textarea" placeholder="Type description here..."></textarea>
							<label for="brief">Description</label>
						</div>


						<div class="input-field col s12">
							<input ng-model="content" type="text" class="validate" id="form-price" placeholder="Type price here..." />
							<label for="content">Price</label>
						</div>

						<div class="input-field col s12">
							<input ng-model="date" type="text" class="validate" id="form-price" placeholder="Type price here..." />
							<label for="date">Price</label>
						</div>


						<div class="input-field col s12">
							<a id="btn-create-product" class="waves-effect waves-light btn margin-bottom-1em" ng-click="createProduct()"><i class="material-icons left">add</i>Create</a>

							<a id="btn-update-product" class="waves-effect waves-light btn margin-bottom-1em" ng-click="updateProduct()"><i class="material-icons left">edit</i>Save Changes</a>

							<a class="modal-action modal-close waves-effect waves-light btn margin-bottom-1em"><i class="material-icons left">close</i>Close</a>
						</div>
					</div>
				</div>
			</div>

			<div class="fixed-action-btn" style="bottom:45px; right:24px;">
				<a class="waves-effect waves-light btn modal-trigger btn-floating btn-large red" href="#modal-product-form" ng-click="showCreateForm()"><i class="large material-icons">add</i></a>
			</div>

		</div>
	</div>



		<!--<s:iterator value="news" var="item" status="status">
			<h2><s:property value="date"/>&nbsp;<s:property value="title"/>

				<s:if test='#session.role_id == 2'>
					<s:url action="updateNews" var="url">
						<s:param name="newsId" value="newsId"/>
					</s:url>
					<a href="<s:property value="#url"/>" class="btn btn-success" role="button">Edit</a>
					<s:url action="deleteNews" var="url">
						<s:param name="news.newsId" value="newsId"/>
					</s:url>
					<a href="<s:property value="#url"/>" onclick="return confirm('Are you sure?')" class="btn btn-danger" role="button">Delete</a>
				</s:if>


			</h2>
			<p><s:property value="brief"/></p>
			<s:url action="viewNews" var="url">
				<s:param name="newsId" value="newsId"/>
			</s:url>
			<a href="<s:property value="#url"/>" class="btn btn-default" role="button">More</a>
			<hr />



		</s:iterator>-->

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<script src="js/materialize.min.js"></script>
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
                $http.post('create_product.php', {
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
        });

        $(document).ready(function(){
            // initialize modal
            $('.modal-trigger').leanModal();
        });
        // jquery codes will be here

	</script>
</body>
</html>
	