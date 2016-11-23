    (function (ng) {
    var mod = ng.module("salasModule");

    mod.controller("salasCtrl", ['$scope', '$state', '$stateParams', '$http', 'teatrosContext', function ($scope, $state, $stateParams, $http, teatroContext) {

            // inicialmente el listado de salas está vacio
            $scope.salasContext = '/salas';
            $scope.records = {};
            // carga las salas
            $http.get(teatroContext + "/" + $stateParams.teatroId + $scope.salasContext).then(function(response){
                $scope.records = response.data;  
            }, responseError);

            // el controlador recibió un numSala ??
            // revisa los parámetros (ver el :numSala en la definición de la ruta)
            if ($stateParams.salaId !== null && $stateParams.salaId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.salaId;
                // obtiene el dato del recurso REST
                
                $http.get(teatroContext + "/" + $stateParams.teatroId +$scope.salasContext + "/" + id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un numero de sala
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/,
                };
              
                $scope.alerts = [];
            }
               
             $http.get(teatrosContext).then(function (response) {
                $scope.teatros = response.data;
            });

            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                
                // si el nombre es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    
                    return $http.post(teatroContext + "/" + $stateParams.teatroId + $scope.salasContext, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('salasListNoEdit');
                        }, responseError);
                        
                // si el numero no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    
                    return $http.put(teatroContext + "/" + $stateParams.teatroId + $scope.salasContext + "/" + currentRecord.id, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('salasListNoEdit');
                        }, responseError);
                };
            };
            
            this.deleteRecord = function (id) {
                currentRecord = $scope.currentRecord;
                if(id != null)
                {            
                    // ejecuta delete en el recurso REST
                    
                    return $http.delete(teatroContext + "/" + $stateParams.teatroId + $scope.salasContext + "/" + id,currentRecord)
                        .then(function () {
                            $scope.records = {};
                            $http.get(teatroContext + "/" + $stateParams.teatroId + $scope.salasContext).then(function(response){
                                $scope.records = response.data;    
                            }, responseError);
                            $state.go('salasList');
                        }, responseError); 
                }
                };



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
