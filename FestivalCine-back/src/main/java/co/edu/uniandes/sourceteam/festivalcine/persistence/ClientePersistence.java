/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.persistence;

import co.edu.uniandes.sourceteam.festivalcine.entities.ClienteEntity;
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
public class ClientePersistence {
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());

    @PersistenceContext(unitName = "SourceteamPU")
    protected EntityManager em;

    public ClienteEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando Cliente con id={0}", id);
        return em.find(ClienteEntity.class, id);
    }

    public ClienteEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando Cliente con name= ", name);
        TypedQuery<ClienteEntity> q
                = em.createQuery("select u from ClienteEntity u where u.name = :name", ClienteEntity.class);
        q = q.setParameter("name", name);
        return q.getSingleResult();
    }

    public List<ClienteEntity> findAll() {
        LOGGER.info("Consultando todos los Clientes");
        Query q = em.createQuery("select u from ClienteEntity u");
        return q.getResultList();
    }

    public ClienteEntity create(ClienteEntity entity) {
        LOGGER.info("Creando un Cliente nuevo");
        em.persist(entity);
        LOGGER.info("Cliente creado");
        return entity;
    }

    public ClienteEntity update(ClienteEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Cliente con id={0}", entity.getId());
        return em.merge(entity);
    }

    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Cliente con id={0}", id);
        ClienteEntity entity = em.find(ClienteEntity.class, id);
        em.remove(entity);
    }
}
