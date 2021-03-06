(function (ng) {
    var mod = ng.module("festivalesModule");

    mod.controller("festivalesCtrl", ['$scope', '$state', '$stateParams', '$http', 'festivalesContext','$anchorScroll', function ($scope, $state, $stateParams, $http, context, $anchorScroll) {

            // inicialmente el listado de festivales está vacio
            $scope.records = {};
            // carga los festivales
            $http.get(context).then(function(response){
                $scope.records = response.data;    
                $anchorScroll('info');
            }, responseError);

            // el controlador recibió un FestivalNombre ??
            // revisa los parámetros (ver el :festivalNombre en la definición de la ruta)
            if ($stateParams.festivalId !== null && $stateParams.festivalId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.festivalId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un festivalNombre
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id : undefined,
                    nombre: '' /*Tipo String*/,
                    duracion: undefined /*Tipo String*/,
                    patrocinador: '' /*Tipo String*/    
                    
                };
              
                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                // si el nombre es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('festivalesList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('festivalesList');
                        }, responseError);
                };
            };

            this.deleteRecord = function (record) {
                
                currentRecord = $scope.currentRecord;
                if(record != null)
                {
                    // ejecuta delete en el recurso REST
                    return $http.delete(context + "/" + record)
                        .then(function () {
                            $scope.records = {};
                            $http.get(context).then(function(response){
                                $scope.records = response.data;    
                            }, responseError);
                            $state.go('festivalesList');
                        }, responseError); 
                }
                }


            // -----------------------------------------------------------------
            // Funciones para manejra los mensajes en la aplicación


            //Alertas
            this.closeAlert = function (index) {
                $scope.alerts.splice(index, 1);
            };

            // Función showMessage: Recibe el mensaje en String y su tipo con el fin de almacenarlo en el array $scope.alerts.
            function showMessage(msg, type) {
                var types = ["info", "danger", "warning", "success"];
                if (types.some(function (rc) {
                    return type === rc;
                })) {
                    $scope.alerts.push({type: type, msg: msg});
                }
            }

            this.showError = function (msg) {
                showMessage(msg, "danger");
            };

            this.showSuccess = function (msg) {
                showMessage(msg, "success");
            };

            var self = this;
            function responseError(response) {

                self.showError(response.data);
            }
        }]);

})(window.angular);

