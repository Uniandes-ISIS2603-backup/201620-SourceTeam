/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.TeatroDTO;
import co.edu.uniandes.rest.cines.exceptions.TeatroException;
import co.edu.uniandes.rest.cines.mocks.TeatroMock;
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
 * @author ba.bohorquez10
 */
@Path("teatros")
@Produces("application/json")
public class TeatroResource 
{
    TeatroMock teatros = new TeatroMock();
    
    /**
     * Obtiene el listado de teatros.
     * @return Listado de teatros.
     * @throws TeatroException Si ocurre algun problema en el metodo.
     */
    @GET
    public List<TeatroDTO>getTeatros() throws TeatroException
    {
        return teatros.getTeatros();
    }
    
    /**
     * Obtiene el teatro con el nombre que ingresa por parametro en el path.
     * @param id Nombre del teatro que se quiere buscar.
     * @return Teatro con el nombre dado.
     * @throws TeatroException Si hay algun problema en el metodo.
     */
    @GET
    @Path("{id: \\d+}")
    public TeatroDTO getTeatro(@PathParam("id") Long id) throws TeatroException {
        return teatros.getTeatro(id);
    }

    /**
     * Crea un nuevo teatro a partir del DTO que ingresa por parametro.
     * @param nuevo Nuevo teatro.
     * @return Teatro creado.
     * @throws TeatroException si hay algun problema con el metodo. 
     */
    @POST
    public TeatroDTO createTeatro(TeatroDTO nuevo) throws TeatroException
    {
        return teatros.createTeatro(nuevo);
    }
    
    /**
     * Modifica el teatro con el nombre que ingresa por parametro en el path.
     * @param id Nombre del teatro a modificar.
     * @param nuevo DTO con la informacion de las modificaciones que se quieren realizar.
     * @return Teatro modificado.
     * @throws TeatroException Si hay algun problema en el metodo.
     */
    @PUT
    @Path("{id: \\d+}")
    public TeatroDTO updateTeatro(@PathParam("id") Long id, TeatroDTO nuevo) throws TeatroException {
        return teatros.updateTeatro(id, nuevo);
    }
    
    /**
     * Elimina el teatro con el nombre que ingresa por parametro en el path.
     * @param id Nombre del teatro que se quiere eliminar.
     * @throws TeatroException Si hay algun problema en el metodo.
     */
    @DELETE
    @Path("{id: \\d+}")
    public TeatroDTO deleteTeatro(@PathParam("id") Long id) throws TeatroException {
    	return teatros.deleteTeatro(id);
    }
}
