angular.module('todo')
.component('todoList',{
	templateUrl: 'app/todo/todoList/todoList.component.html',
	controller: function (todoService, $filter, $scope){
		var vm = this;
		
		vm.todos = [];
		console.log(this);
		console.log($scope);
		vm.filterIncomplete = $filter('incomplete');
		
		vm.reload = function() {
			vm.todo = {
					priority: 1
			}
			todoService.index().then(function(response) {
				vm.todos = response.data;
			});			
		}
		
		vm.reload();
		
		vm.add = function(todo){
			todoService.create(angular.copy(todo)).
			then(function(response){
				vm.reload();
			});
			
		};
		
		vm.count = function(){
			return vm.todos.length - vm.filterIncomplete(vm.todos).length;
		};
		
		vm.warningLevel = function(){
			var countIncomplete = vm.filterIncomplete(vm.todos).length;
			if (countIncomplete > 10) {
				return 'red';
			} else if (countIncomplete >= 5) {
				return 'yellow';
			} else {
				return 'green';
			}
		}
		
		vm.completed = function(todo){
			if (todo.completed){
				return 'completed';
			} else {
				return 'incomplete';
			}
		}
		
		vm.orderBy = null;
		vm.reverse = false;
		
		vm.changeOrderBy = function(columnName){
			if (vm.orderBy === columnName) {
				if (!vm.reverse) {
					vm.reverse = true;
				} else {
					vm.reverse = false;
					vm.orderBy = null;
				}
			} else {
				vm.orderBy = columnName;
			}
		}			
				
		vm.selected= null;
		vm.showTable = true;
		vm.displayTodo = function(todo){
			vm.selected = todo;
			vm.showTable=false;
		};
		
		vm.displayTable = function(){
			vm.selected = null;
			vm.showTable= true;
			
		};
		
		vm.updateTodo = function(todo){
			return todoService.update(todo).then(function(response){
				vm.reload();			
				return response;
			});
		};
		
		vm.deleteTodo = function(id){
			todoService.destroy(id)
			.then(function(response){
				vm.reload();
				
			});
		};
		
		
		var logTodo = function(e, msg){
			console.log(msg.task);
		}
		
		$scope.$on('created', logTodo);

	},
	controllerAs: 'vm'
})