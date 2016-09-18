/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function(ng){
var moduloFestival = ng.module("festivalesModule",['ui.router']);
    moduloFestival.constant("festivalesContext", "api/festivales");
    moduloFestival.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider){
        var basePath = 'src/modules/festivales/';
        $urlRouterProvider.otherwise("/festivalesList");
        $stateProvider
                .state('festivales',{
                    url: "/festivales",
                    abstract : true,
                     views: {
                    'mainView': {
                        controller: 'festivalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'festivales.html'
                    }
                }
                }).state('festivalesList',{
                    url: "/list",
                    parent : 'festivales',
                     views: {
                    'festivalesView': {
                        controller: 'festivalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'festivales.list.html'
                    }
                }
                }).state('festivalesCreate', {
                url: '/festivales/create',
                parent : 'festivales',
                views: {
                    'festivalesView': {
                        controller: 'festivalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'festivales.create.html'
                    }
                }

            }).state('festivalesEdit', {
                url: '/festivales/:festivalId',
                param: { festivalId : null},
                parent: 'festivales',
                views: {
                    'festivalesView': {
                        controller: 'festivalesCtrl',
                        controllerAs: 'ctrl',
                        templateUrl: basePath + 'festivales.create.html'
                    },
                    'childView':{
                        templateUrl: basePath + 'festivales.instance.html'
                    }
                }
            });
}]);
})(window.angular);


