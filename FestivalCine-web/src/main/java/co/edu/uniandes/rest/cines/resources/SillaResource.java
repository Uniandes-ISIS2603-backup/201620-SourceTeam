

package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.sourceteam.festivalcine.api.ISalaLogic;
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
import co.edu.uniandes.sourceteam.festivalcine.api.ISillaLogic;
import co.edu.uniandes.rest.cines.dtos.SalaDetailDTO;
import co.edu.uniandes.rest.cines.dtos.SillaDetailDTO;
import co.edu.uniandes.sourceteam.festivalcine.entities.SillaEntity;
import co.edu.uniandes.sourceteam.festivalcine.exceptions.BusinessLogicException;
import co.edu.uniandes.sourceteam.festivalcine.persistence.SalaPersistence;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.WebApplicationException;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("teatros/{idTeatro: \\d+}/salas/{idSala: \\d+}/sillas")

public class SillaResource {

    private static final Logger LOGGER = Logger.getLogger(SillaResource.class.getName());

    @Inject
    private ISillaLogic sillaLogic;

    @Inject
    private ISalaLogic salaLogic;

    @PathParam("salaId")
    private Long salaId;

    /**
     * Convierte una lista de SillaEntity a una lista de
     * SillaDetailDTO
     *
     * @param entityList Lista de SillaEntity a convertir
     * @return Lista de SillaDetailDTO convertida
     *
     */
    private List<SillaDetailDTO> listEntity2DTO(List<SillaEntity> entityList) {
        List<SillaDetailDTO> list = new ArrayList<>();
        for (SillaEntity entity : entityList) {
            list.add(new SillaDetailDTO(entity));
        }
        return list;
    }

    public void existsSala(Long salaId) {
        SalaDetailDTO sala = new SalaDetailDTO(salaLogic.getSala(salaId));
        if (sala == null) {
            throw new WebApplicationException("La compañía no existe", 404);
        }
    }

    public void existsSilla(Long sillaId) {
        SillaDetailDTO silla = new SillaDetailDTO(sillaLogic.getSilla(sillaId));
        if (silla == null) {
            throw new WebApplicationException("El Departamento no existe", 404);
        }
    }

    /**
     * Obtiene los datos de los Sillas de una compañía a partir del ID de
     * la Sala
     *
     *
     * @return Lista de SillaDetailDTO con los datos del Silla
     * consultado
     *
     */
    @GET
    public List<SillaDetailDTO> getSillas() {
        existsSala(salaId);

        List<SillaEntity> sillas = sillaLogic.getSillas(salaId);

        return listEntity2DTO(sillas);
    }

    /**
     * Obtiene los datos de una instancia de Silla a partir de su ID
     * asociado a un Sala
     *
     * @param sillaId Identificador de la instancia a consultar
     * @return Instancia de SillaDetailDTO con los datos del Silla
     * consultado
     *
     */
    @GET
    @Path("{id: \\d+}")
    public SillaDetailDTO getSilla(@PathParam("id") Long sillaId) {
        existsSala(salaId);
        LOGGER.log(Level.INFO, "Consultando sala con id = {0}", salaId);
        SillaEntity entity = sillaLogic.getSilla(sillaId);
        LOGGER.log(Level.INFO, "Consultando sala con id = {0}", entity.getSala().getId());
        if (entity.getSala() != null && !salaId.equals(entity.getSala().getId())) {
            throw new WebApplicationException(404);
        }

        return new SillaDetailDTO(entity);
    }

    /**
     * Asocia un Silla existente a un Sala
     *
     * @param dto Objeto de SillaDetailDTO con los datos nuevos
     * @return Objeto de SillaDetailDTOcon los datos nuevos y su ID.
     *
     */
    @POST
    public SillaDetailDTO createSilla(SillaDetailDTO dto) throws BusinessLogicException {
        existsSala(salaId);
        return new SillaDetailDTO(sillaLogic.createSilla(salaId, dto.toEntity()));
    }

    /**
     * Actualiza la información de una instancia de Silla.
     *
     * @param sillaId Identificador de la instancia de Silla a
     * modificar
     * @param dto Instancia de SillaDetailDTO con los nuevos datos.
     * @return Instancia de SillaDetailDTO con los datos actualizados.
     *
     */
    @PUT
    @Path("{id: \\d+}")
    public SillaDetailDTO updateSilla(@PathParam("id") Long sillaId, SillaDetailDTO dto) {
        existsSala(salaId);
        existsSilla(sillaId);
        SillaEntity entity = dto.toEntity();
        entity.setId(sillaId);
        return new SillaDetailDTO(sillaLogic.updateSilla(salaId, entity));
    }

    /**
     * Elimina una instancia de Silla de la base de datos.
     *
     * @param sillaId Identificador de la instancia a eliminar.
     *
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSilla(@PathParam("id") Long sillaId) {

        existsSala(salaId);
        existsSilla(sillaId);
        sillaLogic.deleteSilla(sillaId);
    }

}