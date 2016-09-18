(function (ng) {
    var mod = ng.module("clientesModule", ["ngMessages"]);
    mod.constant("clientesContext", "api/clientes");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/clientes/';
            $urlRouterProvider.otherwise("/clientes");
     
            $stateProvider.state('clientes', {
                url: '/clientes',
                abstract: true,
                views: {
                    'mainView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.html'
                    }
                }
            }).state('clientesList', {
                url: '/clientes',
                parent: 'clientes',
                views: {
                    'mainView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.list.html'
                    }
                }
            }).state('clienteCreate', {
                url: '/clientes/create',
                parent: 'clientes',
                views: {
                    'mainView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.create.html'
                    }
                }

            }).state('clienteEdit', {
                url: '/clientes/:clienteId',
                param: {
                    clienteId: null
                },
                parent: 'clientes',
                views: {
                    'mainView': {
                        controller: 'clientesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'clientes.create.html'
                    },'childView': {
                        templateUrl: basePath + 'clientes.instance.html'
                    }
                }
            });
        }]);
})(window.angular);
