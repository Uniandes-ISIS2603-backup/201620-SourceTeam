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
     * 
     * @return
     * @throws TeatroException 
     */
    @GET
    public List<TeatroDTO> getTeatros() throws TeatroException
    {
        return teatros.getTeatros();
    }
    
    
    @GET
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public TeatroDTO getTeatroByName(@PathParam("name") String name) throws TeatroException
    {
        return teatros.getTeatroByName(name);
    }
    
    @POST
    public TeatroDTO createTeatro(TeatroDTO nuevo) throws TeatroException
    {
        return teatros.createTetro(nuevo);
    }
    
    @PUT
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public TeatroDTO updateTeatro(@PathParam("name") String name, TeatroDTO nuevo) throws TeatroException
    {
        return teatros.updateTeatro(name, nuevo);
    }
    
    @DELETE
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public void deleteTeatro(@PathParam("name") String name) throws TeatroException
    {
        teatros.deleteTeatro(name);
    }
    
}
