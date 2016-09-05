/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

(function (ng){
    
    var mod = ng.module("funcionesModule", ["ngMessages"]);
    mod.constant("funcionesContext", "api/funciones");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
    {
        var basePath = 'src/modules/funciones/';
        $urlRouterProvider.otherwise("/funcionesList");
        
        $stateProvider
        .state('funcionesList', {
            url:"/funciones",
            views: {
                    'mainView': {
                        controller: 'funcionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'funciones.list.html'
                    }
                }
        })
        .state('funcionesCreate', {
            url: '/funciones/create',
            views: {
                'mainView': {
                    controller: 'funcionesCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'funciones.create.html'
                }
            }
        })
        .state('funcionEdit', {
            url: '/funciones/:funcionId',
            param: {
                    funcionId: null
            },
            views: {
                'mainView': {
                    controller: 'funcionesCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'funciones.create.html'
                }
            }
        });
    }]);

})(window.angular);
