(function (ng) {
    var mod = ng.module("sillasModule", ["ngMessages"]);
    mod.constant("sillasContext", "api/sillas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
            var basePath = 'src/modules/sillas/';
            $urlRouterProvider.otherwise("/sillas");
     
            $stateProvider.state('sillasList', {
                url: '/sillas',
                views: {
                    'mainView': {
                        controller: 'sillasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sillas.list.html'
                    }
                }
            }).state('sillaCreate', {
                url: '/sillas/create',
                views: {
                    'mainView': {
                        controller: 'sillasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sillas.create.html'
                    }
                }

            }).state('sillaEdit', {
                url: '/sillas/:sillaId',
                param: {
                    sillaId: null
                },
                views: {
                    'mainView': {
                        controller: 'sillasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'sillas.create.html'
                    }
                }
            });
        }]);
})(window.angular);
