(function (ng) {
    var mod = ng.module("peliculasModule");

    mod.controller("peliculasCtrl", ['$scope', '$state', '$stateParams', '$http', 'peliculasContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de clientes está vacio
            $scope.records = {};
            // carga los clientes
            $http.get(context).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            // el controlador recibió un clienteNombre ??
            // revisa los parámetros (ver el :clienteNombre en la definición de la ruta)
            if ($stateParams.peliculaNombre !== null && $stateParams.peliculaNombre !== undefined) {
                
                // toma el id del parámetro
                nombre = $stateParams.peliculaNombre;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + nombre)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un clienteNombre
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    id: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/
                };
              
                $scope.alerts = [];
            }


            this.saveRecord = function (nombre) {
                currentRecord = $scope.currentRecord;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (nombre == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('peliculasList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.nombre, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('peliculasList');
                        }, responseError);
                };
            };
            
            this.deleteRecord = function (nombre) {
                currentRecord = $scope.currentRecord;
                if(nombre!=null)
                {            
                    // ejecuta delete en el recurso REST
                    return $http.delete(context + "/" + nombre,currentRecord)
                        .then(function () {
                            $scope.records = {};
                            $http.get(context).then(function(response){
                                $scope.records = response.data;    
                            }, responseError);
                            $state.go('peliculasList');
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