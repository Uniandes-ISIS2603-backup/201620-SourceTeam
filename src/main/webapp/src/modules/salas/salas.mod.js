/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    var mod = angular.module("salasModule", ["ngMessages"]);
    mod.constant("salasContext", "api/salas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/salas/';
            $urlRouterProvider.otherwise("/salas");
     
            $stateProvider.state('salasList', {
                url: '/salas',
                parent: 'teatroEdit',   
                views: {
                    'teatroInstanceView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.list.html'
                    }
                }
            }).state('salasCreate', {
                url: '/salas/create',
                parent: 'teatroEdit',
                views: {
                    'teatroInstanceView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.create.html'
                    }
                }

            }).state('salasEdit', {
                url: '/salas/:salaId',
                parent: 'teatroEdit',
                param: {
                    salaId: null
                },
                views: {
                    'teatroInstanceView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.create.html'
                    }
                }
            });
        }]);
(window.angular);


