/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var mod = angular.module('peliculaModule', ['ui.router']);
mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/pelicula/';
         $urlRouterProvider.otherwise("/pelicula");
         $stateProvider
            .state('pelicula', {
                        url: "/pelicula",
                        templateUrl: basePath+"pelicula.html"
                    });
}]);

