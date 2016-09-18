(function (ng){
    var mod = ng.module("funcionesModule");
    
    mod.controller("funcionesCtrl", ['$scope', '$state', '$stateParams', '$http', 'funcionesContext','peliculasContext', function ($scope, $state, $stateParams, $http, context, peliculasContext) {
            
            // El listado de funciones esta vacio en un principio.
            $scope.records = {};
            
            // Se cargan las funciones.
            $http.get(context).then(function(response){
                $scope.records = response.data;    
            }, responseError);
            
            // El controlador recibió un clienteNombre ??  
            // Revisa los parámetros (ver el :clienteNombre en la definición de la ruta) 
            if ($stateParams.funcionId !== null && $stateParams.funcionId !== undefined) {
                        
                // Se toma el id del parámetro. 
                id = $stateParams.funcionId;
                
                // Se obtiene el dato del recurso REST.
                $http.get(context + "/" + id)  
                    .then(function (response) {
                        // $http.get es una promesa.  
                        // Cuando llegue el dato, actualice currentRecord.        
                        
                        $scope.currentRecord = response.data; 
                    }, responseError); 
                    
                    

            // El controlador no recibió un clienteNombre. 
                    
            } else
            {
                // El registro actual tiene que estar vacio.
                $scope.currentRecord = {
                    dia: undefined,
                    precio: undefined,
                    pelicula : {},
                };
              
                $scope.alerts = [];
             }
             
             $http.get(peliculasContext).then(function (response) {
                $scope.peliculas = response.data;
            });
             
            this.deleteRecord = function (record) 
            {
                currentRecord = $scope.currentRecord;
                if(record != null)
                {   
                    return $http.delete(context + "/" + record.id)
                        .then(function () { 
                            $scope.records = {};
                            $http.get(context).then(function(response){
                                $scope.records = response.data;    
                            }, responseError);
                            $state.go('funcionesList');
                        }, responseError); 
                }
            };
             
            this.saveRecord = function (id) {
                 currentRecord = $scope.currentRecord;
                 // En caso de que el id sea nulo significa que el registro no existe entonces se crea.        
                 if (id == null) {
 
                     // Se ejecuta POST en el recurso REST. 
                     return $http.post(context, currentRecord)
                         .then(function () {
                             // $http.post es una promesa.
                             // Cuando termine bien, cambie de estado.
                             $state.go('funcionesList');   
                         }, responseError);
                             
                 // En caso de que el id no sea null significa que el registro existe entonces se actualiza.
                 } else {
                     
                     // Se ejecuta PUT en el recurso REST.  
                     return $http.put(context + "/" + currentRecord.id, currentRecord)
                         .then(function () {
                             // $http.put es una promesa.
                             // Cuando termine bien, cambie de estado.   
                             $state.go('funcionesList');
                         }, responseError);
                 };
                
            };  
            
            // -----------------------------------------------------------------
            // Funciones para manejar los mensajes en la aplicación.
            
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
       
}) (window.angular);

        

