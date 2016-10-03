(function (ng) {

    var mainApp = ng.module("mainApp",["ui.router","sillasModule","boletasModule","clientesModule","criticosModule","festivalesModule","funcionesModule","peliculasModule","salasModule","teatrosModule"]);

    mainApp.config(['$logProvider', function ($logProvider) {
            $logProvider.debugEnabled(true);
        }]);

    mainApp.config(['$urlRouterProvider', function ($urlRouterProvider) {
            $urlRouterProvider.otherwise('/cines');
        }]);

  
})(window.angular);