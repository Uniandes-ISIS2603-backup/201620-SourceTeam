/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("abonosModule");

    mod.controller("abonosCtrl", ['$scope', '$state', '$stateParams', '$http', 'abonosContext','festivalesContext',
        function ($scope, $state, $stateParams, $http, abonosContext, festivalesContext) {

            // inicialmente el listado de clientes está vacio
            $scope.records = {};
            // carga los clientes
            $http.get(abonosContext).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            // el controlador recibió un clienteNombre ??
            // revisa los parámetros (ver el :clienteNombre en la definición de la ruta)
            if ($stateParams.abonoId !== null && $stateParams.abonoId !== undefined) {
                
                // toma el id del parámetro
                id = $stateParams.abonoId;
                // obtiene el dato del recurso REST
                $http.get(abonosContext + "/" + id)
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
                    id: undefined /*Tipo int. El valor se asigna en el backend*/, 
                    critico: '' /*Tipo String*/
                    festivales();
                };
              
                $scope.alerts = [];
            }
            $http.get(festivalesContext).then(function (response) {
                $scope.festivales = response.data;
            }};



            this.saveRecord = function (id) {
                currentRecord = $scope.currentRecord;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (id == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(abonosContext, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('abonosList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(abonosContext+ "/" + currentRecord.id, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('abonosList');
                        }, responseError);
                };
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



