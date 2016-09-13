/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("salasModule", ["ngMessages"]);
    mod.constant("salasContext", "api/salas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/salas/';
            $urlRouterProvider.otherwise("/salasList");
     
            $stateProvider.state('salasList', {
                url: '/salas',
                views: {
                    'mainView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.list.html'
                    }
                }
            }).state('salasCreate', {
                url: '/salas/create',
                views: {
                    'mainView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.create.html'
                    }
                }

            }).state('salasEdit', {
                url: '/salas/:numeroSala',
                param: {
                    numeroSala: null
                },
                views: {
                    'mainView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.create.html'
                    }
                }
            });
        }]);
})(window.angular);


