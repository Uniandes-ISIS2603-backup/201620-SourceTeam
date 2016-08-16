/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.BoletaDTO;
import co.edu.uniandes.rest.cines.exceptions.BoletaException;
import co.edu.uniandes.rest.cines.mocks.BoletaMock;
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
 * @author ca.nieto11
 */
@Path("boletas")
@Produces("application/json")
public class BoletaResource {
    BoletaMock boletas = new BoletaMock();
    
    /**
     * Obtiene el listado de boletas.
     *
     * @return lista de boletas
     * @throws BoletaException excepción retornada por la lógica
     */
    @GET
    public List<BoletaDTO> getBoletas() throws BoletaException {
        return boletas.getBoletas();
    }

   
    /**
     * Agrega una boleta
     *
     * @param boleta boleta a agregar
     * @return datos de la boleta a agregar
     * @throws BoletaException cuando ya existe una boleta con el id
     * suministrado
     */
    @POST
    public BoletaDTO createBoleta(BoletaDTO boleta) throws BoletaException {
        return boletas.createBoleta(boleta);
    }


    /**
     * Retorna una boleta dado su id
     * 
     * @param id id de la boleta a retornar
     * @return una boleta
     * @throws BoletaException excepción retornada por la lógica
     */
    @GET
    @Path("{id: \\d+}")
    public BoletaDTO getBoleta(@PathParam("id") int id) throws BoletaException{
       return boletas.getBoleta(id);
    }
    
    
    /**
     * Retorna una boleta dado su precio
     * 
     * @param precio de la boleta a retornar
     * @return una boleta
     * @throws BoletaException excepción retornada por la lógica
     */
    @GET
    @Path("{precio: [0-9][0-9]*}")
    public BoletaDTO getBoletaPorPrecio(@PathParam("precio") double precio) throws BoletaException {
        return boletas.getBoletaPorPrecio(precio);
    }
    
    
    /**
     * Actualiza la información de la boleta identificada con id
     * 
     * @param id de la boleta
     * @param boleta con la que actualizar la información
     * @return la boleta actualizada
     * @throws BoletaException excepción retornada por la lógica
     */
    @PUT
    @Path("{id: \\d+}")
    public BoletaDTO updateCity(@PathParam("id") int id, BoletaDTO boleta) throws BoletaException{
        return boletas.updateBoleta(id, boleta);
    }
    
    /**
     * Elimina una boleta dado su id
     * 
     * @param id de la boleta eliminada
     * @throws BoletaException excepción retornada por la lógica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCity(@PathParam("id")int id) throws BoletaException{
        boletas.deleteBoleta(id);
    }
}
