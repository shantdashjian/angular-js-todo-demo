angular.module('todo')
.component('columnHeader', {
	templateUrl : 'app/todo/columnHeader/columnHeader.component.html',
	controller : function(){
		var vm = this;
		
		
	},
	controllerAs : 'vm',
	bindings: {
		field: '@',
		text: '@'
	}
})
