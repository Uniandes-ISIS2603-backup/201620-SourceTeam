/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.persistence;

import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author ya.bejarano10
 */
@Stateless
public class SalaPersistence 
{
    private static final Logger LOGGER = Logger.getLogger(SalaPersistence.class.getName());

    @PersistenceContext(unitName = "SourceteamPU")
    protected EntityManager em;    
 
    public  SalaEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Sala con id={0}", id);
        return em.find(SalaEntity.class, id);
    }
    public List<SalaEntity> findAll() {
        LOGGER.info("Consultando todos las Salas");
        Query q = em.createQuery("select u from SalaEntity u");
        return q.getResultList();
    }
       
    public SalaEntity findByNumero(long num) {
        LOGGER.log(Level.INFO, "Consultando sala con numero= ", num);
        TypedQuery<SalaEntity> q
                = em.createQuery("select u from SalaEntity u where u.numSala = :numSala", SalaEntity.class);
        q = q.setParameter("numeSala", num);
        return q.getSingleResult();
    }
    
    public SalaEntity create(SalaEntity entity) {
        LOGGER.info("Creando una sala nueva");
        em.persist(entity);
        LOGGER.info("Sala creada");
        return entity;
    }

    public SalaEntity update(SalaEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Sala con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe el festival
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Sala con id={0}", id);
        SalaEntity entity = em.find(SalaEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
    
    
    
}
