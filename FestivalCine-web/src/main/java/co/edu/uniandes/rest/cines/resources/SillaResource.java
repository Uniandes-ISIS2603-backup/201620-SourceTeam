/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.SillaDTO;
import co.edu.uniandes.rest.cines.exceptions.SillaException;
import co.edu.uniandes.rest.cines.mocks.SillaMock;
import co.edu.uniandes.sourceteam.festivalcine.api.ISillaLogic;
import java.util.List;
import javax.inject.Inject;
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
@Path("teatros/{idTeatro: \\d+}/salas/{idSala: \\d+}/sillas")
@Produces("application/json")
public class SillaResource {
    
    
    @Inject
    private ISillaLogic companyLogic;
    SillaMock sillas = new SillaMock();
    
    /**
     * Obtiene el listado de sillas.
     *
     * @return lista de Sillas
     * @throws SillaException excepción retornada por la lógica
     */
    @GET
    public List<SillaDTO> getSillas() throws SillaException {
        return sillas.getSillas();
    }

   
    /**
     * Agrega una silla
     *
     * @param silla silla a agregar
     * @return datos de la silla a agregar
     * @throws SillaException cuando ya existe una silla con el id
     * suministrado
     */
    @POST
    public SillaDTO createSilla(SillaDTO silla) throws SillaException {
        return sillas.createSilla(silla);
    }


    /**
     * Retorna una silla dado su id
     * 
     * @param id id de la silla a retornar
     * @return una silla
     * @throws SillaException excepción retornada por la lógica
     */
    @GET
    @Path("{id: \\d+}")
    public SillaDTO getSilla(@PathParam("id") int id) throws SillaException{
       return sillas.getSilla(id);
    }    
    
    /**
     * Actualiza la información de la silla identificada con id
     * 
     * @param id de la silla
     * @param silla con la que actualizar la información
     * @return la silla actualizada
     * @throws SillaException excepción retornada por la lógica
     */
    @PUT
    @Path("{id: \\d+}")
    public SillaDTO updateSilla(@PathParam("id") int id, SillaDTO silla) throws SillaException{
        return sillas.updateSilla(id, silla);
    }
    
    /**
     * Elimina una silla dado su id
     * 
     * @param id de la silla eliminada
     * @throws SillaException excepción retornada por la lógica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSilla(@PathParam("id")int id) throws SillaException{
        sillas.deleteSilla(id);
    }
}
