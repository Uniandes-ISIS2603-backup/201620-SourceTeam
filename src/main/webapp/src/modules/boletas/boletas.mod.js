(function (ng) {
    var mod = ng.module("boletasModule", ["ngMessages"]);
    mod.constant("boletasContext", "api/boletas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/boletas/';
            $urlRouterProvider.otherwise("/boletas");
     
            $stateProvider.state('boletasList', {
                url: '/boletas',
                parent: 'clienteEdit',
                views: {
                    'clienteInstanceView': {
                        controller: 'boletasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'boletas.listnoedit.html'
                    }
                }
            }).state('boletasListNoEdit', {
                url: '/boletas',
                parent: 'clienteEdit',
                views: {
                    'clienteInstanceView': {
                        controller: 'boletasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'boletas.listnoedit.html'
                    }
                }
            }).state('boletaCreate', {
                url: '/boletas/create',
                parent: 'clienteEdit',
                views: {
                    'clienteInstanceView': {
                        controller: 'boletasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'boletas.create.html'
                    }
                }

            }).state('boletaEdit', {
                url: '/boletas/:boletaId',
                param: {
                    boletaId: null
                },
                parent: 'clienteEdit',
                views: {
                    'clienteInstanceView': {
                        controller: 'boletasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'boletas.create.html'
                    }
                }
            });
        }]);
})(window.angular);
