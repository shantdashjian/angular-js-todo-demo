angular.module('todo')
.factory('todoService'
		, function($http, $filter, $location, authService, $rootScope) {
	var service = {};

	var BASE_URL = 'api/user';
//	var BASE_URL = 'http://shaundashjian.com:8080/TodoREST/api/user';

	// private
	var todos = [];
	var checkLogin = function(){
		if(!authService.getToken().id){
			$location.path('/login');
		}
	}

	// public
	service.index = function() {
		checkLogin();
		return $http({
			method : 'GET',
			url : BASE_URL + '/' + authService.getToken().id + '/todo'
		})
	};

	service.show = function(tid) {
		checkLogin();
		return $http({
			method : 'GET',
			url : BASE_URL + '/' + authService.getToken().id + '/todo/' + tid
		})
	};

	service.create = function(todo) {
		checkLogin();		
		todo.completed = false;
		todo.description = '';
		return $http({
			method : 'POST',
			url : BASE_URL + '/' + authService.getToken().id + '/todo',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : todo
		})
		.then(function(response){
			$rootScope.$broadcast('created', {
				task : todo.task
          });
			return response;
		});
	};

	service.update = function(todo) {
		checkLogin();
		if(todo.completed){
			todo.completedDate = $filter('date')(Date.now(), 'MM/dd/yyyy'); // 8/24/1999
		} else {
			todo.completedDate = "";
		}
		return $http({
			method : 'PUT',
			url : BASE_URL + '/' + authService.getToken().id + '/todo/' + todo.id,
			headers : {
				'Content-Type' : 'application/json'
			},
			data : todo
		})
	};

	service.destroy = function(tid) {
		checkLogin();
		return $http({
			method : 'DELETE',
			url : BASE_URL + '/' + authService.getToken().id + '/todo/' + tid,
		})
	};

	return service;
})
