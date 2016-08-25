/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var moduloFestival = ng.module("festivalesModule",[ngMessages]);

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

