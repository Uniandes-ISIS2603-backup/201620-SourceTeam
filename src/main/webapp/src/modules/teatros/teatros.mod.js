(function (ng){
    
    var mod = ng.module("teatrosModule", ["ngMessages"]);
    mod.constant("teatrosContext", "api/teatros");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
    {
        var basePath = 'src/modules/teatros/';
        $urlRouterProvider.otherwise("/teatrosList");
        
        $stateProvider
        .state('teatros', {
            url:"/teatros",
            abstract: true,
            views: {
                    'mainView': {
                        controller: 'teatrosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'teatros.html'
                    }
                }
        })
        .state('teatrosList', {
            url:"/list",
            parent: 'teatros',
            views: {
                    'teatroView': {
                        controller: 'teatrosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'teatros.list.html'
                    }
                }
        })
        .state('teatrosCreate', {
            url: '/create',
            parent: 'teatros',
            views: {
                'teatroView': {
                    controller: 'teatrosCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'teatros.create.html'
                }
            }
        })
        .state('teatroEdit', {
            url: '/{teatroId:int}/edit',
                param: {'teatroId': null},
                parent: 'teatros',
                views: {
                    'teatroView': {
                        controller: 'teatrosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'teatros.create.html'
                    },
                    'childView': {
                        templateUrl: basePath + 'teatros.instance.html'
                    }
                }
        }).state('festivalTeatrosList', {
            url:"/list",
            parent: 'festivalesEdit',
            views: {
                    'festivalesInstanceView': {
                        controller: 'teatrosCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'teatros.listNoEdit.html'
                    }
                }
        });
    }]);

})(window.angular);
