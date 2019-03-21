angular.module('todo')
.filter('incomplete', function(){
	return function(todos, includeCompleted){
		if (includeCompleted) {
			return todos;
		} else {
			
			return todos.filter(function(element){
				return !element.completed;
			})
		}
	}
})