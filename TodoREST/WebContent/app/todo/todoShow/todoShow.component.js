angular.module('todo')
.component('todoShow', {
	templateUrl: 'app/todo/todoShow/todoShow.component.html',
	controller: function(todoService, $routeParams, $location){
		var vm = this;
		
		vm.editMode = false;
		vm.editTodo = null;
		vm.setEditTodo = function(){
			vm.editTodo = angular.copy(vm.todo);
		}
		
		vm.save = function() {
			if(parseInt($routeParams.id)){
				todoService.update(vm.editTodo)
				.then(function(response){
					
					vm.editMode = false;
					vm.editTodo = null;
					todoService.show($routeParams.id)
					.then(function(response){
						vm.todo = response.data;
					})
				});
			} else {
				
				vm.onUpdate({todo : vm.editTodo})
				.then(function(response){
					
					vm.editMode = false;
					vm.editTodo = null;
					todoService.show(vm.todo.id)
					.then(function(response){
						vm.todo = response.data;
						
					})
				});
			}
		}

		if (!vm.todo && parseInt($routeParams.id)) {
			todoService.show($routeParams.id)
			.then(function(response){
				vm.todo = response.data;
			})
			.catch(function(error){
			    $location.path('/notfound');
			})
		}
		
		vm.backButton = function(){
			if(parseInt($routeParams.id)){
				$location.path('/todo');
			} else {
				
				vm.goBack();
			}
		}
	},
	controllerAs: 'vm',
	bindings: {
		todo: '<',
		goBack: '&',
		onUpdate: '&'
	}
})