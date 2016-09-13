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
     
            $stateProvider.state('criticosList', {
                url: '/criticos',
                views: {
                    'mainView': {
                        controller: 'criticosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'criticos.list.html'
                    }
                }
            }).state('criticosCreate', {
                url: '/criticos/create',
                views: {
                    'mainView': {
                        controller: 'criticosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'criticos.create.html'
                    }
                }

            }).state('criticosEdit', {
                url: '/criticos/:criticoCredencial',
                param: {
                    'criticoCredencial': null
                },
                views: {
                    'mainView': {
                        controller: 'criticosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'criticos.create.html'
                    }
                }
            });
        }]);
})(window.angular);