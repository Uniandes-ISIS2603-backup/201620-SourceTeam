
package co.edu.uniandes.sourceteam.festivalcine.persistence;

import co.edu.uniandes.sourceteam.festivalcine.entities.CriticoEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class CriticoPersistence {

    private static final Logger LOGGER = Logger.getLogger(CriticoPersistence.class.getName());
    
    @PersistenceContext(unitName = "SourceteamPU")
    protected EntityManager em;    
 
    
    public List<CriticoEntity> findAll() {
        LOGGER.info("Consultando todos los criticos");
        Query q = em.createQuery("select u from CriticoEntity u");
        return q.getResultList();
    }
        
    public CriticoEntity create(CriticoEntity entity) {
        LOGGER.info("Creando un critico nuevo");
        em.persist(entity);
        LOGGER.info("Critico creado");
        return entity;
    }

    public CriticoEntity update(CriticoEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando critico con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe el critico
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando critico con id={0}", id);
        CriticoEntity entity = em.find(CriticoEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }

    public CriticoEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando critico con id={0}", id);
        return em.find(CriticoEntity.class, id);
    }
}
