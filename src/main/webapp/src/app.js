(function (ng) {

    var mainApp = ng.module("mainApp",["ui.router","sillasModule","abonoModule","boletaModule","calificacionModule","clienteModule","criticoModule","festivalModule","funcionModule","peliculasModule","salasModule","teatroModule"]);

    mainApp.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mainApp.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/citiesList');
        }]);

  
})(window.angular);