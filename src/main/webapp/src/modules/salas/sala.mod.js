/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var mod = angular.module('salaModule', ['ui.router']);
mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/sala/';
         $urlRouterProvider.otherwise("/sala");
         $stateProvider.state('salaList', {
                url: '/sala',
                views: {
                    'mainView': {
                        controller: 'salaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sala.list.html'
                    }
                }
            }).state('salaCreate', {
                url: '/sala/create',
                views: {
                    'mainView': {
                        controller: 'salaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sala.create.html'
                    }
                }

            }).state('salaEdit', {
                url: '/sala/:numSala',
                param: {
                    numSala: null
                },
                views: {
                    'mainView': {
                        controller: 'salaCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sala.create.html'
                    }
                }
            });
}]);


