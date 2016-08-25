/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var mod = angular.module('salaModule', ['ui.router']);
mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/sala/';
         $urlRouterProvider.otherwise("/sala");
         $stateProvider
            .state('sala', {
                        url: "/sala",
                        templateUrl: basePath+"sala.html"
                    });
}]);


