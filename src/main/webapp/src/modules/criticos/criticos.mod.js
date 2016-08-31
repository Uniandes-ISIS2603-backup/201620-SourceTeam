/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
var moduloCritico = ng.module("criticosModule",['ui.router']);

moduloCritico.config(['$stateProvider','$urlRouterProvider', function($stateProvider,$urlRouterProvider){
        var basePath = 'src/modules/criticos/';
        $urlRouterProvider.otherwise("/criticos");
        $stateProvider
                .state('criticos',{
                    url: "/criticos",
                    templateUrl: basePath + "criticos.html",
                    controller: function($scope){
                        $scope.festivales = [];
                    }
                })
}]);
})(window.angular)