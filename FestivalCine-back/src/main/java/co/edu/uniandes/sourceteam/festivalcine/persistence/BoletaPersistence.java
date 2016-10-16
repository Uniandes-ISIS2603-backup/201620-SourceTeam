/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.persistence;

import co.edu.uniandes.sourceteam.festivalcine.entities.BoletaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author s.rodriguez20
 */
public class BoletaPersistence {
    private static final Logger LOGGER = Logger.getLogger(BoletaPersistence.class.getName());

    @PersistenceContext(unitName = "SourceteamPU")
    protected EntityManager em;

    public BoletaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Boleta con id={0}", id);
        return em.find(BoletaEntity.class, id);
    }

    public List<BoletaEntity> findAll() {
        LOGGER.info("Consultando todos los Boletas");
        Query q = em.createQuery("select u from BoletaEntity u");
        return q.getResultList();
    }

    public BoletaEntity create(BoletaEntity entity) {
        LOGGER.info("Creando un Boleta nuevo");
        em.persist(entity);
        LOGGER.info("Boleta creado");
        return entity;
    }

    public BoletaEntity update(BoletaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Boleta con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Boleta con id={0}", id);
        BoletaEntity entity = em.find(BoletaEntity.class, id);
        em.remove(entity);
    }
}
