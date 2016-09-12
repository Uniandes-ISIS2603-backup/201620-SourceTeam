/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
(function (ng) {
    var mod = ng.module("calificacionesModule");

    mod.controller("calificacionesCtrl", ['$scope', '$state', '$stateParams', '$http', 'calificacionesContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de clientes está vacio
            $scope.records = {};
            // carga los clientes
            $http.get(context).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            // el controlador recibió un clienteNombre ??
            // revisa los parámetros (ver el :clienteNombre en la definición de la ruta)
            if ($stateParams.calificacionFuncion !== null && $stateParams.calificacionId !== undefined) {
                
                // toma el id del parámetro
                funcion = $stateParams.calificacionFuncion;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + funcion)
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
                    funcion: undefined /*Tipo double. El valor se asigna en el backend*/,
                    critico: '' /*Tipo String*/
                    
                };
              
                $scope.alerts = [];
            }


            this.saveRecord = function (funcion) {
                currentRecord = $scope.currentRecord;
                
                // si el id es null, es un registro nuevo, entonces lo crea
                if (funcion == null) {

                    // ejecuta POST en el recurso REST
                    return $http.post(context, currentRecord)
                        .then(function () {
                            // $http.post es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('calificacionesList');
                        }, responseError);
                        
                // si el id no es null, es un registro existente entonces lo actualiza
                } else {
                    
                    // ejecuta PUT en el recurso REST
                    return $http.put(context + "/" + currentRecord.funcion, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('calificacionesList');
                        }, responseError);
                };
            };
            
                 this.deleteRecord = function (funcion) {
                currentRecord = $scope.currentRecord;
                if(funcion!=null)
                {            
                    // ejecuta delete en el recurso REST
                    return $http.delete(context + "/" + funcion,currentRecord)
                        .then(function () {
                            $scope.records = {};
                            $http.get(context).then(function(response){
                                $scope.records = response.data;    
                            }, responseError);
                            $state.go('calificacionesList');
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



