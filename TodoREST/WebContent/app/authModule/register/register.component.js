angular.module('authModule')
.component('register', {
	templateUrl: 'app/authModule/register/register.component.html',
	controller: function(authService, $location){
		var vm = this;
		vm.register = function(user){
			authService.register(user)
			.then(function(response){
				$location.path("/todo");
			})
		}
	},
	controllerAs: 'vm'
})