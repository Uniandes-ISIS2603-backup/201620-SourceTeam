/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("criticosModule", ["ngMessages"]);
    mod.constant("criticosContext", "api/criticos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/criticos/';
            $urlRouterProvider.otherwise("/criticosList");
     
            $stateProvider.state('criticos', {
                url: '/criticos',
                abstract: true,
                parent : 'festivalesEdit',
                views: {
                    'festivalesInstanceView': {
                        controller: 'criticosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'criticos.html'
                    }
                }
            }).state('criticosList', {
                url: '/criticos',
                parent : 'festivalesEdit',
                views: {
                    'festivalesInstanceView': {
                        controller: 'criticosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'criticos.list.html'
                    }
                }
            }).state('criticosCreate', {
                url: '/criticos/create',
                parent : 'festivalesEdit',
                views: {
                    'festivalesInstanceView': {
                        controller: 'criticosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'criticos.create.html'
                    }
                }

            }).state('criticosEdit', {
                url: '/criticos/:criticoId',
                parent : 'festivalesEdit',
                param: {
                    criticoId: null
                },
                views: {
                    'festivalesInstanceView': {
                        controller: 'criticosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'criticos.create.html'
                    }
                }
            });
        }]);
})(window.angular);