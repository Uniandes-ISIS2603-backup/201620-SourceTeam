/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;


import co.edu.uniandes.rest.cines.dtos.SalaDTO;
import co.edu.uniandes.rest.cines.dtos.SalaDetailDTO;
import co.edu.uniandes.rest.cines.exceptions.SalaException;
import co.edu.uniandes.rest.cines.mocks.SalaMock;
import co.edu.uniandes.sourceteam.festivalcine.api.ISalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
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
import javax.ws.rs.core.MediaType;

/**
 *
 * @author ya.bejarano10
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("salas/{idSala: \\d+}/salas")
public class SalaResource 
{

    @Inject
    private ISalaLogic salaLogic;
    
    /**
     * Convierte una lista de SalaEntity a una lista de SalaDetailDTO.
     * 
     * 
     * @param entityList Lista de SalaEntity a convertir
     * @return Lista de SalaDetailDTO convertida.
     */
    private List<SalaDetailDTO> listEntity2DTO(List<SalaEntity> entityList) 
    {
        List<SalaDetailDTO> list = new ArrayList<>();
        
        for (SalaEntity entity : entityList)
        {
            list.add( new SalaDetailDTO(entity) );
        }
        
        return list;
    }
    
    
    /**
     * Obtiene la lista de registros de Sala.
     * 
     * @return Colección de objetos de SalaDetailDTO.
     */
    @GET
    public List<SalaDetailDTO> getSalas()
    {
        return listEntity2DTO( salaLogic.getSalas() );
    }
    
    /**
     * Obtiene los datos de una instancia Sala a partir de su ID.
     * @param id Identificador de la instancia a consultar.
     * @return Intancia de SalaDetailDTO con los datos de Sala consultado.
     */
    @GET
    @Path("{idSala: \\d+}")
    public SalaDetailDTO getSala(@PathParam("idSala") Long id) 
    {
        return new SalaDetailDTO( salaLogic.getSala(id) );
    }
    
    /**
     * Crea una Sala en la base de datos.
     * 
     * @param dto Objeto de SalaDetailDTO con los nuevos datos.
     * @return Objeto de SalaDetailDTO con los nuevos datos y su respectivo id.
     * 
     * @throws Exception Si el sala que se quiere crear ya existe o hay 
     * un funcion con el mismo nombre.
     */
    @POST
    public SalaDetailDTO createSala(SalaDetailDTO dto) throws Exception
    {
        return new SalaDetailDTO( salaLogic.createSala(dto.toEntity()) );
    }
    
    /**
     * Actualiza la información de una instancia Sala.
     * 
     * @param id Identificador de la instancia sala a modificar.
     * @param dto Intancia SalaDetailDTO con los nuevos datos.
     * @return Intancia de SalaDetailDTO con los datos actualizados.
     */
    @PUT
    @Path("{id: \\d+}")
    public SalaDetailDTO updateSala(@PathParam("id") Long id, SalaDetailDTO dto) 
    {
        SalaEntity entity = dto.toEntity();
        entity.setId(id);
        return new SalaDetailDTO( salaLogic.updateSala(entity) );
    }
    
    /**
     * Elimina una instancia de Sala de la base de datos.
     * 
     * @param id Identificador de la instancia a eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSala(@PathParam("id") Long id) 
    {
        salaLogic.deleteSala(id);
    }

    
}


