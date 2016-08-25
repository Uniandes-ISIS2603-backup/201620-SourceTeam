/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;
import co.edu.uniandes.rest.cines.dtos.CalificacionDTO;
import co.edu.uniandes.rest.cines.exceptions.CalificacionException;
import co.edu.uniandes.rest.cines.mocks.CalificacionMock;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
/**
 *
 * @author pa.alvarado10
 */
@Path("calificaciones")
@Produces("application/json")
public class CalificacionResource {
    
    
   CalificacionMock calificaciones = new CalificacionMock();
    
    /**
     * Obtiene el listado de calificaciones.
     *
     * @return lista de calificaciones
     * @throws Exception excepción retornada por la lógica
     */
    @GET
    public List<CalificacionDTO> getCalificaciones() throws CalificacionException {
        return calificaciones.getCalificaciones();
    }

   
    /**
     * Agrega una calificacion
     *
     * @param calificacion a agregar
     * @return datos de la calificacion a agregar
     * @throws Exception cuando ya existe una calificacion con el id
     * suministrado
     */
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO calificacion) throws CalificacionException {
        return calificaciones.createCalificacion(calificacion);
    }


    /**
     * Retorna una Calificacion dado su id
     * 
     * @param id id del Calificacion a retornar
     * @return una Calificacion
     * @throws Exception excepción retornada por la lógica
     */
    @GET
    @Path("{id: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("id") double id) throws CalificacionException{
       return calificaciones.getCalificacion(id);
    }
    
    
    /**
     * Retorna una Calificacion dado su precio
     * 
     * @param precio del abono a retornar
     * @return un abono
     * @throws Exception excepción retornada por la lógica
     */
  //  @GET
    //@Path("{precio: [0-9][0-9]*}")
    //public AbonoDTO getAbonoPorPrecio(@PathParam("precio") double precio) throws Exception {
     //   return abonos.getAbonoPorPrecio(precio);
   // }
    
    
    /**
     * Actualiza la información de la calificacion identificada con id
     * 
   
     * @param calificacion con la que actualizar la información
     * @return el calificacion actualizado
     * @throws Exception excepción retornada por la lógica
     */
    @PUT
    
    @Path("{id: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("id") double id, CalificacionDTO cali) throws CalificacionException{
        return calificaciones.updateCalificacion(id, cali);
    }
    
    /**
     * Elimina una calificacion dado su id
     * 
     * @param id de la calificacion eliminado
     * @throws Exception excepción retornada por la lógica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCalificacion(@PathParam("id")double id) throws CalificacionException{
        calificaciones.deleteCalificacion(id);
    }
    
    
}
