/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("abonosModule", ["ngMessages"]);
    mod.constant("abonosContext", "api/abonos");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/abonos/';
            $urlRouterProvider.otherwise("/abonosList");
     
            $stateProvider.state('abonosList', {
                url: '/abonos',
                views: {
                    'mainView': {
                        controller: 'abonosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'abonos.list.html'
                    }
                }
            }).state('abonosCreate', {
                url: '/abonos/create',
                views: {
                    'mainView': {
                        controller: 'abonosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'abonos.create.html'
                    }
                }

            }).state('abonosEdit', {
                url: '/abonos/:abonosId',
                param: {
                    abonoId: null
                },
                views: {
                    'mainView': {
                        controller: 'abonosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'abonos.create.html'
                    }
                }
            });
        }]);
})(window.angular);
