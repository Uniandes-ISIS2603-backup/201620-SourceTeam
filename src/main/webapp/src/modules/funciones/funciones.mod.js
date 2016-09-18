(function (ng){
    
    var mod = ng.module("funcionesModule", ["ngMessages"]);
    mod.constant("funcionesContext", "api/funciones");
    
    mod.config(['$stateProvider', '$urlRouterProvider', function($stateProvider, $urlRouterProvider)
    {
        var basePath = 'src/modules/funciones/';
        $urlRouterProvider.otherwise("/funciones");
        
        $stateProvider.state('funciones', {
            url:"/funciones",
            abstact: true,
            views: {
                    'mainView': {
                        controller: 'funcionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'funciones.html'
                    }
                }
        })
        .state('funcionesList', {
            url: '/funciones',
            parent: 'funciones',
            views: {
                'mainView': {
                    controller: 'funcionesCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'funciones.list.html'
                }
            }
        }).state('funcionesCreate', {
                    url: '/funciones/create',
                parent: 'funciones',
                views: {
                    'mainView': {
                        controller: 'funcionesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'funciones.create.html'
                    }
        }})
        .state('funcionEdit', {
            url: '/funciones/:funcionId',
            param: {
                    funcionId: null
            },
            parent: 'funciones',
            views: {
                'mainView': {
                    controller: 'funcionesCtrl',
                    controllerAs: 'ctrl',
                    templateUrl: basePath + 'funciones.create.html'
                }, 'childView':{
                    templateUrl: basePath + 'funciones.instance.html'
                }
            }
        });
    }]);

})(window.angular);
