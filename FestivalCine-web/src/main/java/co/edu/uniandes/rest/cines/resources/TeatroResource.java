/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.TeatroDetailDTO;
import co.edu.uniandes.sourceteam.festivalcine.api.ITeatroLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
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
 * @author ba.bohorquez10
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("teatros")
public class TeatroResource 
{
    
    @Inject
    private ITeatroLogic teatroLogic;
    
    /**
     * Convierte una lista de TeatroEntity a una lista de TeatroDetailDTO.
     * 
     * 
     * @param entityList Lista de TeatroEntity a convertir
     * @return Lista de TeatroDetailDTO convertida.
     */
    private List<TeatroDetailDTO> listEntity2DTO(List<TeatroEntity> entityList) 
    {
        List<TeatroDetailDTO> list = new ArrayList<>();
        
        for (TeatroEntity entity : entityList)
        {
            list.add( new TeatroDetailDTO(entity) );
        }
        
        return list;
    }
    
    
    /**
     * Obtiene la lista de registros de Teatro.
     * 
     * @return Colección de objetos de TeatroDetailDTO.
     */
    @GET
    public List<TeatroDetailDTO> getTeatros()
    {
        return listEntity2DTO( teatroLogic.getTeatros() );
    }
    
    /**
     * Obtiene los datos de una instancia Teatro a partir de su ID.
     * @param id Identificador de la instancia a consultar.
     * @return Intancia de TeatroDetailDTO con los datos del Teatro consultado.
     */
    @GET
    @Path("{idTeatro: \\d+}")
    public TeatroDetailDTO getTeatro(@PathParam("idTeatro") Long id) 
    {
        return new TeatroDetailDTO( teatroLogic.getTeatro(id) );
    }

    
    
    
    
    
    
    
    
    /**
     * Obtiene los datos de una instancia de Teatro a partir de su nombre.
     * 
     * @param name Nombre del Teatro que se quiere buscar.
     * 
     * @return instancia TeatroDetailDTO con el nombre dado.
     * 
     */
    @GET
    @Path("/name")
    public TeatroDetailDTO getTeatroByName(@QueryParam("name") String name)
    {
        return new TeatroDetailDTO( teatroLogic.getTeatroByName(name) );
    }
    
    /**
     * Crea un Teatro en la base de datos.
     * 
     * @param dto Objeto de TeatroDetailDTO con los nuevos datos.
     * @return Objeto de TeatroDetailDTO con los nuevos datos y su respectivo id.
     * 
     * @throws Exception Si el teatro que se quiere crear ya existe o hay 
     * un teatro con el mismo nombre.
     */
    @POST
    public TeatroDetailDTO createTeatro(TeatroDetailDTO dto) throws Exception
    {
        return new TeatroDetailDTO( teatroLogic.createTeatro( dto.toEntity() ) );
    }
    
    /**
     * Actualiza la información de una instancia Teatro.
     * 
     * @param id Identificador de la instancia Teatro a modificar.
     * @param dto Intancia TeatroDetailDTO con los nuevos datos.
     * @return Intancia de TeatroDetailDTO con los datos actualizados.
     */
    @PUT
    @Path("{id: \\d+}")
    public TeatroDetailDTO updateTeatro(@PathParam("id") Long id, TeatroDetailDTO dto) 
    {
        TeatroEntity entity = dto.toEntity();
        entity.setId(id);
        return new TeatroDetailDTO( teatroLogic.updateTeatro(entity) );
    }
    
    /**
     * Elimina una instancia de Teatro de la base de datos.
     * 
     * @param id Identificador de la instancia a eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteTeatro(@PathParam("id") Long id) 
    {
        teatroLogic.deleteTeatro(id);
    }
}
