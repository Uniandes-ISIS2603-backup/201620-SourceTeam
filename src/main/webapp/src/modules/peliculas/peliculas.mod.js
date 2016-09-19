(function (ng) {
    var mod = ng.module("peliculasModule",["ngMessages"]);
    mod.constant("peliculasContext", "api/peliculas");
    mod.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/peliculas/';
         $urlRouterProvider.otherwise("/peliculas");
        $stateProvider.state('peliculasList', {
                url: '/peliculas',
                views: {
                    'mainView': {
                        controller: 'peliculasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'peliculas.list.html'
                    }
                }
            }).state('peliculasCreate', {
                url: '/peliculas/create',
                views: {
                    'mainView': {
                        controller: 'peliculasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'peliculas.create.html'
                    }
                }

            }).state('festivalPeliculasList', {
            url:"/list",
            parent: 'festivalesEdit',
            views: {
                    'festivalesInstanceView': {
                        controller: 'peliculasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'peliculas.listNoEdit.html'
                    }
                }
        }).state('peliculasEdit', {
                url: '/peliculas/:peliculaId',
                param: {
                    peliculaId: null
                },
                views: {
                    'mainView': {
                        controller: 'peliculasCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'peliculas.create.html'
                    }
                }
            });
       
}]);
})(window.angular);
