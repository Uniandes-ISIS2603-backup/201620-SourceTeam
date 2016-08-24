/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.FestivalDTO;
import co.edu.uniandes.rest.cines.exceptions.FestivalException;
import co.edu.uniandes.rest.cines.mocks.FestivalMock;
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
 * @author s.ardila13
 */
@Path("festivales")
@Produces("application/json")
public class FestivalResource {
    FestivalMock festivales = new FestivalMock();
    
    /**
     * Obtiene el listado de festivales.
     *
     * @return lista de festivales
     * @throws FestivalException excepción retornada por la lógica
     */
    @GET
    public List<FestivalDTO> getFestivales() throws FestivalException {
        return festivales.getFestivales();
    }

   
    /**
     * Agrega una festival
     *
     * @param festival festival a agregar
     * @return datos del festival a agregar
     * @throws FestivalException cuando ya existe un festival con el nombre
     * suministrado
     */
    @POST
    public FestivalDTO createFestival(FestivalDTO festival) throws FestivalException {
        return festivales.createFestival(festival);
    }

  
    
    /**
     * Retorna un festival dado su duracion
     * 
     * @param nombre del festival a retornar
     * @return un festival
     * @throws FestivalException excepción retornada por la lógica
     */
    @GET
    @Path("{nombre: [a-zA-Z][a-zA-Z]*}")
    public FestivalDTO getFestivalPorNombre(@PathParam("nombre") String nombre) throws FestivalException {
        return festivales.getFestivalPorNombre(nombre);
    }
    
    
    /**
     * Actualiza la información del festival identificada con nombre
     * 
     * @param nombre del festival
     * @param festival festival con el que actualizar la información
     * @return el festival actualizado
     * @throws FestivalException excepción retornada por la lógica
     */
    @PUT
    @Path("{nombre: [a-zA-Z][a-zA-Z]*}")
    public FestivalDTO updateFestival(@PathParam("nombre") String nombre, FestivalDTO festival) throws FestivalException{
        return festivales.updateFestival(nombre, festival);
    }
    
    /**
     * Elimina un festival dado su nombre
     * 
     * @param nombre del festival eliminado
     * @return festival eliminado
     * @throws FestivalException excepción retornada por la lógica
     */
    @DELETE
    @Path("{nombre: [a-zA-Z][a-zA-Z]*}")
    public FestivalDTO deleteFestival(@PathParam("nombre")String nombre) throws FestivalException{
        return festivales.deleteFestival(nombre);
    }
}
