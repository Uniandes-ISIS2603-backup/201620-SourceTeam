/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.CriticoDTO;
import co.edu.uniandes.rest.cines.dtos.CriticoDetailDTO;
import co.edu.uniandes.rest.cines.exceptions.CriticoException;
import co.edu.uniandes.rest.cines.mocks.CriticoMock;
import co.edu.uniandes.sourceteam.festivalcine.api.ICriticoLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.CriticoEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author s.ardila13
 */
@Path("criticos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CriticoResource {
    
    
    @Inject
    private ICriticoLogic criticoLogic;
    
    /**
     * Obtiene el listado de criticos.
     *
     * @return lista de criticos
     * @throws CriticoException excepción retornada por la lógica
     */
    
    private List<CriticoDetailDTO> listEntity2DTO(List<CriticoEntity> entityList){
        List<CriticoDetailDTO> list = new ArrayList<>();
        for(CriticoEntity entidad : entityList){
            list.add(new CriticoDetailDTO(entidad));
        }
        return list;
    } 

    @GET
    public List<CriticoDetailDTO> getCriticos() throws CriticoException {
        return listEntity2DTO(criticoLogic.getCriticos());
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
    public CriticoDetailDTO createCritico(CriticoDetailDTO critico) throws CriticoException, Exception {
        return new CriticoDetailDTO(criticoLogic.createCritico(critico.toEntity()));
    }


    /**
     * Retorna un critico dado su credencial
     * 
     * @param credencial credencial del critico a retornar
     * @return un critico
     * @throws CriticoException excepción retornada por la lógica
     */
    @GET
    @Path("{id: \\d+}")
    public CriticoDetailDTO getCritico(@PathParam("id") long id) throws CriticoException{
       return new CriticoDetailDTO(criticoLogic.getCritico(id));
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
    @Path("{id: \\d+}")
    public CriticoDetailDTO updateCritico(@PathParam("id") long id, CriticoDetailDTO critico) throws Exception{
        CriticoEntity entity = critico.toEntity();
        entity.setId(id);
        
        return new CriticoDetailDTO(criticoLogic.updateCritico(entity));
    }
    
    /**
     * Elimina un festival dado su id
     * 
     * @param id del festival eliminado
     * @throws CriticoException excepción retornada por la lógica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCritico(@PathParam("id")long id) throws CriticoException{
         criticoLogic.deleteCritico(id);
    }
    
    @GET
    @Path("/name")
    public CriticoDetailDTO getCriticoByName(@QueryParam("name") String name) {
        return new CriticoDetailDTO(criticoLogic.findByName(name));
    }
}
