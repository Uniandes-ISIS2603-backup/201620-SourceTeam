(function (ng) {
    var mod = angular.module("salasModule", ["ngMessages"]);
    mod.constant("salasContext", "api/salas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/salas/';
            $urlRouterProvider.otherwise("/salas");
            
            $stateProvider.state('salasList', {
            url:"/salas",
            
            views: {
                    'salaView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.list.html'
                    }
                }
            })
                    
            .state('salasListNoEdit', {
                url: '/salas',
                parent: 'teatroEdit',
                views: {
                    'teatroInstanceView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.listnoedit.html'
                    }
                }
            }).state('salasCreate', {
                url: '/salas/create',
                parent: 'teatroEdit',
                views: {
                    'teatroInstanceView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.create.html'
                    }
                }

            }).state('salasEdit', {
                url: '/salas/:salaId',
                parent: 'teatroEdit',
                param: {
                    salaId: null
                },
                views: {
                    'teatroInstanceView': {
                        controller: 'salasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'salas.create.html'
                    },
                    'childView': {
                        templateUrl: basePath + 'salas.instance.html'
                    }
                }
            });
        }]);
})(window.angular);


