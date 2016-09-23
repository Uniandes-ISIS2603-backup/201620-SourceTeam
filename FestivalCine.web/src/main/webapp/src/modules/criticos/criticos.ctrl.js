(function (ng) {
    var mod = ng.module("criticosModule");

    mod.controller("criticosCtrl", ['$scope', '$state', '$stateParams', '$http', 'criticosContext', 'festivalesContext', function ($scope, $state, $stateParams, $http, context, festivalesContext) {

            // inicialmente el listado de criticos está vacio
            $scope.records = {};
            // carga los criticos
            $http.get(context).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            // el controlador recibió un CriticoCredencial ??
            // revisa los parámetros (ver el criticoCredencial en la definición de la ruta)
            if ($stateParams.criticoId !== null && $stateParams.criticoId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.criticoId;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + id)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un criticoCredencial
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    credencial: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    nombre: '' /*Tipo String*/,
                    duracion: undefined /*Tipo Long*/,
                    festival: {} /*Objeto que representa instancia de Festival*/
                };
                $scope.alerts = [];
            }
            
            $http.get(festivalesContext).then(function (response) {
                $scope.festivales = response.data;
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
                            $state.go('criticosList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.id, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('criticosList');
                        }, responseError);
                };
            };
            
            
            //this.deleteRecord() = function(credencial){
            //    currentRecord = $scope.currentRecord;
            //}
            
            this.deleteRecord = function (record) {
                currentRecord = $scope.currentRecord;
                if(record !=null)
                {            
                    // ejecuta delete en el recurso REST
                    return $http.delete(context + "/" + record.id)
                        .then(function () {
                            $scope.records = {};
                            $http.get(context).then(function(response){
                                $scope.records = response.data;    
                            }, responseError);
                            $state.go('criticosList');
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

