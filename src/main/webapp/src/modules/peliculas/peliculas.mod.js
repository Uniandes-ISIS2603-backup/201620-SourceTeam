/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var mod = angular.module('peliculasModule', ['ui.router']);
mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/pelicula/';
         $urlRouterProvider.otherwise("/peliculas");
        $stateProvider.state('peliculas', {
                url: '/pelicula',
                views: {
                    'mainView': {
                        controller: 'peliculasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'peliculas.list.html'
                    }
                }
            }).state('peliculasCreate', {
                url: '/peliculas/create',
                views: {
                    'mainView': {
                        controller: 'peliculasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'peliculas.create.html'
                    }
                }

            }).state('peliculasEdit', {
                url: '/peliculas/:nombrePelicula',
                param: {
                    nombrePelicula: null
                },
                views: {
                    'mainView': {
                        controller: 'peliculasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'peliculas.create.html'
                    }
                }
            });
       
}]);

