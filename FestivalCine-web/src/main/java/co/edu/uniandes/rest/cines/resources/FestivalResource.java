/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.FestivalDTO;
import co.edu.uniandes.rest.cines.dtos.FestivalDetailsDTO;
import co.edu.uniandes.rest.cines.exceptions.FestivalException;
import co.edu.uniandes.rest.cines.mocks.FestivalMock;
import co.edu.uniandes.sourceteam.festivalcine.api.IFestivalLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.FestivalEntity;
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
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author s.ardila13
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("festivales")
public class FestivalResource {
    FestivalMock festivales = new FestivalMock();
    
    @Inject
    private IFestivalLogic festivalLogic;
        
    /**
     * Obtiene el listado de festivales.
     *
     * @return lista de festivales
     * @throws FestivalException excepción retornada por la lógica
     */
    
    private List<FestivalDetailsDTO> listEntity2DTO(List<FestivalEntity> entityList) {
        List<FestivalDetailsDTO> list = new ArrayList<FestivalDetailsDTO>();
        for (FestivalEntity entity : entityList) {
            list.add(new FestivalDetailsDTO(entity));
        }
        return list;
    }
    
    
    
    
    
    @GET
    public List<FestivalDetailsDTO> getFestivales() throws FestivalException {
        List<FestivalEntity> festivales = festivalLogic.getFestivales();
        return listEntity2DTO(festivales);
    }
    
    @GET
    @Path("{festivalId: \\d+}")
    public FestivalDetailsDTO getFestival(@PathParam("festivalId") Long festivalId){
        FestivalEntity entity = festivalLogic.getFestival(festivalId);
        if(entity == null)
            throw new WebApplicationException(404);
        return new FestivalDetailsDTO(entity);
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
    public FestivalDTO createFestival(FestivalDetailsDTO festival) throws Exception {
        return new FestivalDetailsDTO(festivalLogic.createFestival(festival.toEntity()));
    }

  
    
    /**
     * Retorna un festival dado su duracion
     * 
     * @param nombre del festival a retornar
     * @return un festival
     * @throws FestivalException excepción retornada por la lógica
     */
    @GET
    @Path("/name")
    public FestivalDTO getFestivalPorNombre(@QueryParam("name") String nombre) throws FestivalException {
        FestivalEntity entity = festivalLogic.getFestivalByName(nombre);
        if(entity == null)
            throw new WebApplicationException(404);
        return new FestivalDetailsDTO(entity);
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
    @Path("{id: \\d+}")
    public FestivalDTO updateFestival(@PathParam("id") Long id, FestivalDetailsDTO festival) throws Exception{
        FestivalEntity entity = festival.toEntity();
        entity.setId(id);
        FestivalEntity entidadAnterior = festivalLogic.getFestival(id);
        entity.setCriticos(entidadAnterior.getCriticos());
        return new FestivalDetailsDTO(festivalLogic.updateFestival(entity));
    }    
    /**
     * Elimina un festival dado su nombre
     * 
     * @param nombre del festival eliminado
     * @return festival eliminado
     * @throws FestivalException excepción retornada por la lógica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFestival(@PathParam("id")Long id) throws FestivalException{
        festivalLogic.deleteFestival(id);
    }
}
