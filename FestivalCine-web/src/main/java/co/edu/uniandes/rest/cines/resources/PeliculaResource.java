/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.PeliculaDetailDTO;
import co.edu.uniandes.rest.cines.exceptions.PeliculaException;
import co.edu.uniandes.sourceteam.festivalcine.api.IPeliculaLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.PeliculaEntity;
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
 * @author ya.bejarano10
 */

@Path("peliculas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PeliculaResource
{
    @Inject
    private IPeliculaLogic peliculaLogic;
    
    /**
     * Convierte una lista de PeliculaEntity a una lista de PeliculaDetailDTO.
     * 
     * 
     * @param entityList Lista de FuncionEntity a convertir
     * @return Lista de PeliculaDetailDTO convertida.
     */
    private List<PeliculaDetailDTO> listEntity2DTO(List<PeliculaEntity> entityList) 
    {
        List<PeliculaDetailDTO> list = new ArrayList<>();
        
        for (PeliculaEntity entity : entityList)
        {
            list.add( new PeliculaDetailDTO(entity) );
        }
        
        return list;
    }
    
    @GET
    public List<PeliculaDetailDTO> getPeliculas() throws PeliculaException
    {
        return listEntity2DTO( peliculaLogic.getPeliculas() );
    }
    
    /**
     * Obtiene los datos de una instancia Pelicula a partir de su ID.
     * @param id Identificador de la instancia a consultar.
     * @return Intancia de PeliculaDetailDTO con los datos de pelicula consultado.
     */
    @GET
    @Path("{id: \\d+}")
    public PeliculaDetailDTO getPelicula(@PathParam("id") Long id) 
    {
        return new PeliculaDetailDTO( peliculaLogic.getPelicula(id) );
    }

    /**
     * Obtiene los datos de una instancia de Pelicula a partir de su nombre.
     * 
     * @param name Nombre de la Pelicula que se quiere buscar.
     * 
     * @return Instancia PeliculaDetailDTO con el nombre dado.
     * 
     */
    @GET
    @Path("/name")
    public PeliculaDetailDTO getPeliculaByName(@QueryParam("name") String name)
    {
        return new PeliculaDetailDTO( peliculaLogic.getPeliculaByName(name) );
    }
    
    /**
     * Crea una Pelicula en la base de datos.
     * 
     * @param dto Objeto de PeliculaDetailDTO con los nuevos datos.
     * @return Objeto de PeliculaDetailDTO con los nuevos datos y su respectivo id.
     * 
     * @throws Exception Si el funcion que se quiere crear ya existe o hay 
     * un funcion con el mismo nombre.
     */
    @POST
    public PeliculaDetailDTO createPelicula(PeliculaDetailDTO dto) throws Exception
    {
        return new PeliculaDetailDTO( peliculaLogic.createPelicula(dto.toEntity()) );
    }
    
    /**
     * Actualiza la información de una instancia Pelicula.
     * 
     * @param id Identificador de la instancia Pelicula a modificar.
     * @param dto Intancia PeliculaDetailDTO con los nuevos datos.
     * @return Intancia de PeliculaDetailDTO con los datos actualizados.
     */
    @PUT
    @Path("{id: \\d+}")
    public PeliculaDetailDTO updatePelicula(@PathParam("id") Long id, PeliculaDetailDTO dto) 
    {
        PeliculaEntity entity = dto.toEntity();
        entity.setId(id);
        return new PeliculaDetailDTO( peliculaLogic.updatePelicula(entity) );
    }
    
    /**
     * Elimina una instancia de Pelicula de la base de datos.
     * 
     * @param id Identificador de la instancia a eliminar.
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deletePelicula(@PathParam("id") Long id) 
    {
        peliculaLogic.deletePelicula(id);
    }
}
