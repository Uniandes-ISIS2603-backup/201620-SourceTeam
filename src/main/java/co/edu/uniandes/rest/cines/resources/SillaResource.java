/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.SillaDTO;
import co.edu.uniandes.rest.cines.exceptions.SillaException;
import co.edu.uniandes.rest.cines.mocks.SillaMock;
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
 * @author s.rodriguez20
 */
@Path("sillas")
@Produces("application/json")
public class SillaResource {
    SillaMock sillas = new SillaMock();
    
    /**
     * Obtiene el listado de boletas.
     *
     * @return lista de boletas
     * @throws SillaException excepción retornada por la lógica
     */
    @GET
    public List<SillaDTO> getBoletas() throws SillaException {
        return sillas.getSillas();
    }

   
    /**
     * Agrega una boleta
     *
     * @param silla silla a agregar
     * @return datos de la boleta a agregar
     * @throws SillaException cuando ya existe una boleta con el id
     * suministrado
     */
    @POST
    public SillaDTO createBoleta(SillaDTO silla) throws SillaException {
        return sillas.createSilla(silla);
    }


    /**
     * Retorna una boleta dado su id
     * 
     * @param id id de la boleta a retornar
     * @return una boleta
     * @throws SillaException excepción retornada por la lógica
     */
    @GET
    @Path("{id: \\d+}")
    public SillaDTO getSilla(@PathParam("id") int id) throws SillaException{
       return sillas.getSilla(id);
    }    
    
    /**
     * Actualiza la información de la boleta identificada con id
     * 
     * @param id de la boleta
     * @param boleta con la que actualizar la información
     * @return la boleta actualizada
     * @throws SillaException excepción retornada por la lógica
     */
    @PUT
    @Path("{id: \\d+}")
    public SillaDTO updateSilla(@PathParam("id") int id, SillaDTO boleta) throws SillaException{
        return sillas.updateBoleta(id, boleta);
    }
    
    /**
     * Elimina una boleta dado su id
     * 
     * @param id de la boleta eliminada
     * @throws SillaException excepción retornada por la lógica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSilla(@PathParam("id")int id) throws SillaException{
        sillas.deleteBoleta(id);
    }
}
