/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.FuncionDTO;
import co.edu.uniandes.rest.cines.dtos.FuncionDetailDTO;
import co.edu.uniandes.rest.cines.exceptions.FuncionException;
import co.edu.uniandes.rest.cines.mocks.FuncionMock;
import co.edu.uniandes.sourceteam.festivalcine.api.IFuncionLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.FuncionEntity;
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
@Path("teatros/{idTeatro: \\d+}/funciones")
public class FuncionResource 
{
    
    @Inject
    private IFuncionLogic funcionLogic;
    
    /**
     * Convierte una lista de FuncionEntity a una lista de FuncionDetailDTO.
     * 
     * 
     * @param entityList Lista de FuncionEntity a convertir
     * @return Lista de FuncionDetailDTO convertida.
     */
    private List<FuncionDetailDTO> listEntity2DTO(List<FuncionEntity> entityList) 
    {
        List<FuncionDetailDTO> list = new ArrayList<>();
        
        for (FuncionEntity entity : entityList)
        {
            list.add( new FuncionDetailDTO(entity) );
        }
        
        return list;
    }
    
    
    /**
     * Obtiene la lista de registros de Funcion.
     * 
     * @return Colección de objetos de FuncionDetailDTO.
     */
    @GET
    public List<FuncionDetailDTO> getFunciones()
    {
        return listEntity2DTO( funcionLogic.getFunciones() );
    }
    
    /**
     * Obtiene los datos de una instancia Funcion a partir de su ID.
     * @param id Identificador de la instancia a consultar.
     * @return Intancia de FuncionDetailDTO con los datos de Funcion consultado.
     */
    @GET
    @Path("{idFuncion: \\d+}")
    public FuncionDetailDTO getFuncion(@PathParam("idFuncion") Long id) 
    {
        return new FuncionDetailDTO( funcionLogic.getFuncion(id) );
    }

    /**
     * Obtiene los datos de una instancia de Funcion a partir de su nombre.
     * 
     * @param name Nombre de la Funcion que se quiere buscar.
     * 
     * @return Instancia FuncionDetailDTO con el nombre dado.
     * 
     */
    @GET
    @Path("/name")
    public FuncionDetailDTO getFuncionByName(@QueryParam("name") String name)
    {
        return new FuncionDetailDTO( funcionLogic.getFuncionByName(name) );
    }
    
    /**
     * Crea una Funcion en la base de datos.
     * 
     * @param dto Objeto de FuncionDetailDTO con los nuevos datos.
     * @return Objeto de FuncionDetailDTO con los nuevos datos y su respectivo id.
     * 
     * @throws Exception Si el funcion que se quiere crear ya existe o hay 
     * un funcion con el mismo nombre.
     */
    @POST
    public FuncionDetailDTO createFuncion(FuncionDetailDTO dto) throws Exception
    {
        return new FuncionDetailDTO( funcionLogic.createFuncion( dto.toEntity() ) );
    }
    
    /**
     * Actualiza la información de una instancia Funcion.
     * 
     * @param id Identificador de la instancia Funcion a modificar.
     * @param dto Intancia FuncionDetailDTO con los nuevos datos.
     * @return Intancia de FuncionDetailDTO con los datos actualizados.
     */
    @PUT
    @Path("{id: \\d+}")
    public FuncionDetailDTO updateFuncion(@PathParam("id") Long id, FuncionDetailDTO dto) 
    {
        FuncionEntity entity = dto.toEntity();
        entity.setId(id);
        return new FuncionDetailDTO( funcionLogic.updateFuncion(entity) );
    }
    
    /**
     * Elimina una instancia de Funcion de la base de datos.
     * 
     * @param id Identificador de la instancia a eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteFuncion(@PathParam("id") Long id) 
    {
        funcionLogic.deleteFuncion(id);
    }
    
}
