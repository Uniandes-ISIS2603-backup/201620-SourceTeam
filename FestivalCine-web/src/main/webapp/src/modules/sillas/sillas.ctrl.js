(function (ng) {
    var mod = ng.module("sillasModule");

    mod.controller("sillasCtrl", ['$scope', '$state', '$stateParams', '$http', 'teatrosContext','salasContext', function ($scope, $state, $stateParams, $http,teatrosContext,salasContext) {
            $scope.salasContext = "salas"
            $scope.sillasContext = 'sillas';
            // inicialmente el listado de sillas está vacio
            $scope.records = {};
            // carga las ciudades
            $http.get(teatrosContext + "/" + $stateParams.teatroId +"/" + $scope.salasContext + "/" + $stateParams.salaId + "/" + $scope.sillasContext).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            // el controlador recibió un sillaId ??
            // revisa los parámetros (ver el :sillaId en la definición de la ruta)
            if ($stateParams.sillaId !== null && $stateParams.sillaId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.sillaId;
                // obtiene el dato del recurso REST
                    $http.get(teatrosContext + "/" + $stateParams.teatroId + "/" + $scope.salasContext + "/" + $stateParams.salaId + "/" + $scope.sillasContext +"/" + id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un sillaId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/
                };
              
                $scope.alerts = [];
            }


            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(teatrosContext + "/" + $stateParams.teatroId+"/" + $scope.salasContext + "/" + $stateParams.salaId + "/" + $scope.sillasContext +"/" + id, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('sillasList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(teatrosContext + "/" + $stateParams.teatroId+"/" +$scope.salasContext + "/" + $stateParams.salaId + "/" + $scope.sillasContext + "/" + currentRecord.id, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('sillasList');
                        }, responseError);
                };
            };

            this.deleteRecord = function (id) {
                currentRecord = $scope.currentRecord;
                if(id!=null)
                {            
                    // ejecuta delete en el recurso REST
                    return $http.delete(teatrosContext + "/" + $stateParams.teatroId+"/" +$scope.salasContext + "/" + $stateParams.salaId + "/" + $scope.sillasContext + "/" + id)
                        .then(function () {
                            $scope.records = {};
                            $http.get(teatrosContext + "/" + $stateParams.teatroId+"/" +$scope.salasContext + "/" + $stateParams.salaId + "/" + $scope.sillasContext).then(function(response){
                                $scope.records = response.data;    
                            }, responseError);
                            $state.go('sillasList');
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

