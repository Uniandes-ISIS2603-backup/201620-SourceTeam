/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.persistence;

import co.edu.uniandes.sourceteam.festivalcine.entities.SillaEntity;
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
public class SillaPersistence {
    private static final Logger LOGGER = Logger.getLogger(SillaPersistence.class.getName());

    @PersistenceContext(unitName = "SourceteamPU")
    protected EntityManager em;

    public SillaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Silla con id={0}", id);
        return em.find(SillaEntity.class, id);
    }
     public List<SillaEntity> findAllInSala(Long salaId) {
        LOGGER.log(Level.INFO, "Consultando todas las sillas de la sala id={0}", salaId);
        TypedQuery q = em.createQuery("select d from SillaEntity d  where d.sala.id = :salaId", SillaEntity.class);
        q = q.setParameter("salaId", salaId);
        return q.getResultList();
    }

    public List<SillaEntity> findAll() {
        LOGGER.info("Consultando todos los Sillas");
        Query q = em.createQuery("select u from SillaEntity u");
        return q.getResultList();
    }

    public SillaEntity create(SillaEntity entity) {
        LOGGER.info("Creando un Silla nuevo");
        em.persist(entity);
        LOGGER.info("Silla creado");
        return entity;
    }

    public SillaEntity update(SillaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Silla con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Silla con id={0}", id);
        SillaEntity entity = em.find(SillaEntity.class, id);
        em.remove(entity);
    }
}
