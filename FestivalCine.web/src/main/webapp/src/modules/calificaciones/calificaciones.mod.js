/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("calificacionesModule", ["ngMessages"]);
    mod.constant("calificacionesContext", "api/calificaciones");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/calificaciones/';
            $urlRouterProvider.otherwise("/calificacionesList");
     
            $stateProvider.state('calificacionesList', {
                url: '/calificaciones',
                views: {
                    'mainView': {
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'calificaciones.list.html'
                    }
                }
            }).state('calificacionesCreate', {
                url: '/calificaciones/create',
                views: {
                    'mainView': {
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'calificaciones.create.html'
                    }
                }

            }).state('calificacionesEdit', {
                url: '/calificaciones/:calificacionId',
                param: {
                    calificacionId: null
                },
                views: {
                    'mainView': {
                        controller: 'calificacionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'calificaciones.create.html'
                    }
                }
            });
        }]);
})(window.angular);


