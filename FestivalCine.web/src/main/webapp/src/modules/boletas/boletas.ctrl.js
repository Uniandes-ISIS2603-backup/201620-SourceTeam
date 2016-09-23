(function (ng) {
    var mod = ng.module("boletasModule");

    mod.controller("boletasCtrl", ['$scope', '$state', '$stateParams', '$http', 'boletasContext','funcionesContext','teatrosContext', function ($scope, $state, $stateParams, $http, context,funcionesContext,teatrosContext) {

            // inicialmente el listado de boletas está vacio
            $scope.records = {};
            // carga las ciudades
            $http.get(context).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            // el controlador recibió un boletaId ??
            // revisa los parámetros (ver el :boletaId en la definición de la ruta)
            if ($stateParams.boletaId !== null && $stateParams.boletaId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.boletaId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un boletaId
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    precio: 2000 /*Tipo String*/,
                    funcion:{}
                };
              
                $scope.alerts = [];
            }
            $http.get(teatrosContext).then(function (response) {
                $scope.teatros = response.data;
            });
            
            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('boletasListNoEdit');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('boletasListNoEdit');
                        }, responseError);
                };
            };
            

            this.deleteRecord = function (id) {
                currentRecord = $scope.currentRecord;
                if(id!=null)
                {            
                    // ejecuta delete en el recurso REST
                    return $http.delete(context + "/" + id,currentRecord)
                        .then(function () {
                            $scope.records = {};
                            $http.get(context).then(function(response){
                                $scope.records = response.data;    
                            }, responseError);
                            $state.go('boletasList');
                        }, responseError); 
                }
                }

            this.actualizarFunciones = function () {
                var idTeatro = $scope.currentRecord.teatro.id;
                $http.get(teatrosContext + "/" + idTeatro + "/funciones").then(function (response) {
                    $scope.funciones = response.data;
                });
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

