angular.module('appModule',['todo', 'static','ngRoute', 'nav', 'authModule'])
.config(function($routeProvider){
	  $routeProvider
	    .when('/', {
	      template : '<home>Loading home</home>'
	    })
	    .when('/register', {
	      template : '<register>Loading register</register>'
	    })
	    .when('/login', {
	      template : '<login>Loading login</login>'
	    })
	    .when('/contact', {
	      template : '<contact>Loading contact</contact>'
	    })
	    .when('/about', {
	      template : '<about>Loading about</about>'
	    })
	    .when('/todo', {
	      template : '<todo-list>Loading todo list</todo-list>'

	    })
	    .when('/todo/:id', {
	      template : '<todo-show>Loading todo show</todo-show>'

	    })
	    .otherwise({
	      template : '<not-found>Loading not found</not-found>'
	    })
	});