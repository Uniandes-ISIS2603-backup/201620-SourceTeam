/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var mod = angular.module('peliculasModule', ["ngMessages"]);
 mod.constant("peliculasContext", "api/peliculas");
mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/peliculas/';
         $urlRouterProvider.otherwise("/peliculas");
        $stateProvider.state('peliculasList', {
                url: '/peliculas',
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
                url: '/peliculas/:peliculaNombre',
                param: {
                    peliculaNombre: null
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

