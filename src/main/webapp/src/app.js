(function (ng) {

    var mainApp = ng.module("mainApp",["ui.router","sillasModule","abonoModule","boletaModule","calificacionModule","clienteModule","criticoModule","festivalModule","funcionModule","peliculaModule","salaModule","teatroModule"]);

    mod.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mod.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/citiesList');
        }]);

  
})(window.angular);