/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
var moduloFestival = ng.module("festivalesModule",['ui.router']);

moduloFestival.config(['$stateProvider','$urlRouterProvider', function($stateProvider,$urlRouterProvider){
        var basePath = 'src/modules/festivales/';
        $urlRouterProvider.otherwise("/festivales");
        $stateProvider
                .state('festivales',{
                    url: "/festivales",
                    templateUrl: basePath + "festivales.html",
                    controller: function($scope){
                        $scope.festivales = [];
                    }
                })
}]);
})(window.angular);


