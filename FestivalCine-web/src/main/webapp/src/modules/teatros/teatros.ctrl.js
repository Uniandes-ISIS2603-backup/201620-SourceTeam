(function (ng) {
     
     var mod = ng.module("teatrosModule");
     
     mod.controller("teatrosCtrl", ['$scope', '$state', '$stateParams', '$http', 'teatrosContext', '$anchorScroll' , function ($scope, $state, $stateParams, $http, context,$anchorScroll) {
             
             // El listado de teatros esta vacio en un principio.
             $scope.records = {};
             
             // Se cargan los teatros.
             $http.get(context).then(function(response){
                 $scope.records = response.data;
                 $anchorScroll('info');
             }, responseError);
             
             // el controlador recibió un clienteNombre ??  
             // revisa los parámetros (ver el :clienteNombre en la definición de la ruta) 
             if ($stateParams.teatroId !== null && $stateParams.teatroId !== undefined) {
                         
                 // Se toma el id de lparámetro. 
                 id = $stateParams.teatroId;
                 
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
                     nombre: undefined  ,
                    ciudad: undefined
                  };
                
                  $scope.alerts = [];
               }
                
             
             this.deleteRecord = function (record) 
             {
                 currentRecord = $scope.currentRecord;
                 if(record!=null)
                 {   
                     return $http.delete(context + "/" + record.id)
                         .then(function () { 
                             $scope.records = {};
                             $http.get(context).then(function(response){
                                 $scope.records = response.data;    
                             }, responseError);
                             $state.go('teatrosList');
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
                             $state.go('teatrosList');   
                         }, responseError);
                             
                 // En caso de que el id no sea null significa que el registro existe entonces se actualiza.
                 } else {
                     
                     // Se ejecuta PUT en el recurso REST.  
                     return $http.put(context + "/" + currentRecord.id, currentRecord)
                         .then(function () {
                             // $http.put es una promesa.
                             // Cuando termine bien, cambie de estado.   
                             $state.go('teatrosList');
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
 