/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.persistence;

import co.edu.uniandes.sourceteam.festivalcine.entities.FestivalEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.PeliculaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ya.bejarano10
 */

@Stateless
public class PeliculaPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(PeliculaPersistence.class.getName());

    @PersistenceContext(unitName = "SourceteamPU")
    protected EntityManager em;    
 
    
    public List<PeliculaEntity> findAll() {
        LOGGER.info("Consultando todos las Películas");
        Query q = em.createQuery("select u from PeliculaEntity u");
        return q.getResultList();
    }
        
    public PeliculaEntity create(PeliculaEntity entity) {
        LOGGER.info("Creando una Pelicula nuevo");
        em.persist(entity);
        LOGGER.info("Pelicula creado");
        return entity;
    }

    public PeliculaEntity update(PeliculaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Pelicula con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe el festival
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Pelicula con id={0}", id);
        PeliculaEntity entity = em.find(PeliculaEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
    
}
