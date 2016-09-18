(function (ng) {
    var mod = ng.module("sillasModule", ["ngMessages"]);
    mod.constant("sillasContext", "api/sillas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/sillas/';
            $urlRouterProvider.otherwise("/sillas");
     
            $stateProvider.state('sillasList', {
                url: '/sillas',
                parent: 'salasEdit', 
                views: {
                    'salaInstanceView': {
                        controller: 'sillasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sillas.list.html'
                    }
                }
            }).state('sillasCreate', {
                url: '/sillas/create',
                parent: 'salasEdit',
                views: {
                    'salaInstanceView': {
                        controller: 'sillasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sillas.create.html'
                    }
                }

            }).state('sillaEdit', {
                url: '/sillas/:sillaId',
                parent: 'salasEdit',
                param: {
                    sillaId: null
                },
                views: {
                    'salaInstanceView': {
                        controller: 'sillasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sillas.create.html'
                    }
                }
            });
        }]);
})(window.angular);
