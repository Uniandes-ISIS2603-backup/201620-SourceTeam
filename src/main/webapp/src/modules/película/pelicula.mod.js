/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var mod = angular.module('peliculaModule', ['ui.router']);
mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/pelicula/';
         $urlRouterProvider.otherwise("/pelicula");
        $stateProvider.state('peliculaList', {
                url: '/pelicula',
                views: {
                    'mainView': {
                        controller: 'peliculaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pelicula.list.html'
                    }
                }
            }).state('peliculaCreate', {
                url: '/pelicula/create',
                views: {
                    'mainView': {
                        controller: 'peliculaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pelicula.create.html'
                    }
                }

            }).state('peliculaEdit', {
                url: '/pelicula/:nombrePelicula',
                param: {
                    nombrePelicula: null
                },
                views: {
                    'mainView': {
                        controller: 'peliculaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'pelicula.create.html'
                    }
                }
            });
       
}]);

