/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.ejbs;

import co.edu.uniandes.sourceteam.festivalcine.api.ISalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.api.ISillaLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.SillaEntity;
import co.edu.uniandes.sourceteam.festivalcine.exceptions.BusinessLogicException;
import co.edu.uniandes.sourceteam.festivalcine.persistence.SillaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

/**
 *
 * @author s.rodriguez20
 */
@Stateless
public class SillaLogic implements ISillaLogic{
    
   private static final Logger LOGGER = Logger.getLogger(SillaLogic.class.getName());

    @Inject
    private SillaPersistence persistence;

    @Inject
    private ISalaLogic salaLogic;

    /**
     * Obtiene la lista de los registros de Silla que pertenecen a un
     * Sala.
     *
     * @param salaid id del Sala el cual es padre de los Sillas.
     * @return Colección de objetos de SillaEntity.
     *
     */
    @Override
    public List<SillaEntity> getSillas(Long salaid) {
        SalaEntity sala = salaLogic.getSala(salaid);
        return sala.getSillas();
    }

    /**
     * Obtiene los datos de una instancia de Silla a partir de su ID.
     *
     * @param sillaid Identificador del Silla a consultar
     * @return Instancia de SillaEntity con los datos del Silla
     * consultado.
     *
     */
    @Override
    public SillaEntity getSilla(Long sillaId) {
        LOGGER.log(Level.INFO, "Consultando silla con sillaId={0}", sillaId);
        try {
            return persistence.find(sillaId);
        } catch (NoResultException e) {
            throw new IllegalArgumentException("El Silla no existe");
        }
    }

    
    
    
    @Override
    public SillaEntity getSillaByPos(Long salaId, int fila, int numero) {
        return persistence.findByPos(salaId, fila, numero);
    }

    /**
     * Se encarga de crear un Silla en la base de datos.
     *
     * @param entity Objeto de SillaEntity con los datos nuevos
     * @param salaid id del Sala el cual sera padre del nuevo Silla.
     * @return Objeto de SillaEntity con los datos nuevos y su ID.
     *
     */
    @Override
    public SillaEntity createSilla(Long salaid, SillaEntity entity) throws BusinessLogicException {
        SillaEntity alreadyExist = getSillaByPos(salaid, entity.getFila(), entity.getNumero());
        if (alreadyExist != null) {
            throw new BusinessLogicException("Ya existe un departamento con esa pocision en la misma compañía ");
        } else {
            SalaEntity  sala = salaLogic.getSala(salaid);
            entity.setSala(sala);

            entity = persistence.create(entity);
        }
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Silla.
     *
     * @param entity Instancia de SillaEntity con los nuevos datos.
     * @param salaid id del Sala el cual sera padre del Silla
     * actualizado.
     * @return Instancia de SillaEntity con los datos actualizados.
     *
     */
    @Override
    public SillaEntity updateSilla(Long salaid, SillaEntity entity) {
        SalaEntity sala = salaLogic.getSala(salaid);
        entity.setSala(sala);
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Silla de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * @param salaid id del Sala el cual es padre del Silla.
     *
     */
    @Override
    public void deleteSilla(Long id) {
        SillaEntity old = getSilla(id);
        persistence.delete(old.getId());
    }

    

    
    
    
    
    
}
