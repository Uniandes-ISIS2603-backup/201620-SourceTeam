(function (ng) {
    var mod = ng.module("peliculaModule");

    mod.controller("peliculaCtrl", ['$scope', '$state', '$stateParams', '$http', 'peliculaContext', function ($scope, $state, $stateParams, $http, context) {

            // inicialmente el listado de peliculas está vacio
            $scope.records = {};
            // carga las peliculas
            $http.get(context).then(function(response){
                $scope.records = response.data;    
            }, responseError);

            // el controlador recibió un peliculaNombre ??
            // revisa los parámetros (ver el :peliculaNombre en la definición de la ruta)
            if ($stateParams.peliculaNombre !== null && $stateParams.peliculaNombre !== undefined) {
                
                // toma el id del parámetro
                name = $stateParams.peliculaNombre;
                // obtiene el dato del recurso REST
                $http.get(context + "/" + name)
                    .then(function (response) {
                        // $http.get es una promesa
                        // cuando llegue el dato, actualice currentRecord
                        $scope.currentRecord = response.data;
                    }, responseError);

            // el controlador no recibió un peliculaNombre
            } else
            {
                // el registro actual debe estar vacio
                $scope.currentRecord = {
                    nombre: undefined /*Tipo Long. El valor se asigna en el backend*/,
                    name: '' /*Tipo String*/
                };
              
                $scope.alerts = [];
            }


            this.saveRecord = function (name) {
                currentRecord = $scope.currentRecord;
                
                // si el nombre es null, es un registro nuevo, entonces lo crea
                if (name === null) {

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
                    return $http.put(context + "/" + currentRecord.name, currentRecord)
                        .then(function () {
                            // $http.put es una promesa
                            // cuando termine bien, cambie de estado
                            $state.go('peliculasList');
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



