/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng){
    
    var mod = ng.module("teatrosModule", ["ngMessages"]);
    mod.constant("teatrosContext", "api/teatros");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
    {
        var basePath = 'src/modules/teatros/';
        $urlRouterProvider.otherwise("/teatrosList");
        
        $stateProvider
        .state('teatrosList', {
            url:"/teatros",
            views: {
                    'mainView': {
                        controller: 'teatrosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'teatros.list.html'
                    }
                }
        })
        .state('teatrosCreate', {
            url: '/teatros/create',
            views: {
                'mainView': {
                    controller: 'teatrosCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'teatros.create.html'
                }
            }
        })
        .state('teatroEdit', {
            url: '/teatros/:teatroId',
            param: {
                    teatroId: null
            },
            views: {
                'mainView': {
                    controller: 'teatrosCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'teatros.create.html'
                }
            }
        });
    }]);

})(window.angular);





