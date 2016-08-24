/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.CriticoDTO;
import co.edu.uniandes.rest.cines.exceptions.CriticoException;
import co.edu.uniandes.rest.cines.mocks.CriticoMock;
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
@Path("criticos")
@Produces("application/json")
public class CriticoResource {
    CriticoMock criticos = new CriticoMock();
    
    /**
     * Obtiene el listado de criticos.
     *
     * @return lista de criticos
     * @throws CriticoException excepción retornada por la lógica
     */
    @GET
    public List<CriticoDTO> getCriticos() throws CriticoException {
        return criticos.getCriticos();
    }

   
    /**
     * Agrega una critico
     *
     * @param critico critico a agregar
     * @return datos del critico a agregar
     * @throws CriticoException cuando ya existe un critico con la credencial
     * suministrada
     */
    @POST
    public CriticoDTO createCritico(CriticoDTO critico) throws CriticoException {
        return criticos.createCritico(critico);
    }


    /**
     * Retorna un critico dado su credencial
     * 
     * @param credencial credencial del critico a retornar
     * @return un critico
     * @throws CriticoException excepción retornada por la lógica
     */
    @GET
    @Path("{credencial: \\d+}")
    public CriticoDTO getCritico(@PathParam("credencial") long credencial) throws CriticoException{
       return criticos.getCritico(credencial);
    }
    
    
    /**
     * Retorna un critico dado su duracion
     * 
     * @param duracion del critico a retornar
     * @return un critico
     * @throws CriticoException excepción retornada por la lógica
     */
    @GET
    @Path("{duracion: \\id+}")
    public CriticoDTO getCriticoPorDuracion(@PathParam("duracion") long duracion) throws CriticoException {
        return criticos.getCriticoPorDuracion((int)duracion);
    }
    
    
    /**
     * Actualiza la información del festival identificado con credencial
     * 
     * @param credencial del critico
     * @param critico con el que actualizar la información
     * @return el critico actualizado
     * @throws CriticoException excepción retornada por la lógica
     */
    @PUT
    @Path("{credencial: \\d+}")
    public CriticoDTO updateCritico(@PathParam("credencial") long credencial, CriticoDTO critico) throws CriticoException{
        return criticos.updateCritico((int)credencial, critico);
    }
    
    /**
     * Elimina un festival dado su nombre
     * 
     * @param credencial del festival eliminado
     * @throws CriticoException excepción retornada por la lógica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCritico(@PathParam("credencial")long credencial) throws CriticoException{
        criticos.deleteCritico((int)credencial);
    }
}
