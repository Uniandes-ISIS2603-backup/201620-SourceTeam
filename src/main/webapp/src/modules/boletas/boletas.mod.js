(function (ng) {
    var mod = ng.module("boletasModule", ["ngMessages"]);
    mod.constant("boletasContext", "api/boletas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/boletas/';
            $urlRouterProvider.otherwise("/boletas");
     
            $stateProvider.state('boletasList', {
                url: '/boletas',
                views: {
                    'mainView': {
                        controller: 'boletasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'boletas.list.html'
                    }
                }
            }).state('boletaCreate', {
                url: '/boletas/create',
                views: {
                    'mainView': {
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
                views: {
                    'mainView': {
                        controller: 'boletasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'boletas.create.html'
                    }
                }
            });
        }]);
})(window.angular);
