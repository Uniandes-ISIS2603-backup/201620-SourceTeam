
package co.edu.uniandes.sourceteam.festivalcine.persistence;

import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
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
 * @author ba.bohorquez10
 */
@Stateless
public class TeatroPersistence
{
    private static final Logger LOGGER = Logger.getLogger(TeatroPersistence.class.getName());

    @PersistenceContext(unitName = "SourceteamPU")
    protected EntityManager em;

    public TeatroEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando teatro con id={0}", id);
        return em.find(TeatroEntity.class, id);
    }
    
    public TeatroEntity findByName(String name)
    {
        LOGGER.log(Level.INFO, "Consultando teatro con name = {0}", name);
        TypedQuery<TeatroEntity> q
                = em.createQuery("select u from TeatroEntity u where u.name = :name", TeatroEntity.class);
        q = q.setParameter("name", name);
        
       List<TeatroEntity> companiesSimilarName = q.getResultList();
        if (companiesSimilarName.isEmpty() ) {
            return null; 
        } else {
            return companiesSimilarName.get(0);
        }
    }
    
    public List<TeatroEntity> findAll() 
    {
        LOGGER.info("Consultando todos los teatros");
        Query q = em.createQuery("select u from TeatroEntity u");
        return q.getResultList();
    }
        
    public TeatroEntity create(TeatroEntity entity) 
    {
        LOGGER.info("Creando un teatro nuevo");
        em.persist(entity);
        LOGGER.info("Teatro creado");
        return entity;
    }

    public TeatroEntity update(TeatroEntity entity) 
    {
        LOGGER.log(Level.INFO, "Actualizando teatro con id={0}", entity.getId() );
        return em.merge(entity);
    }
    
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando teatro con id={0}", id);
        TeatroEntity entity = em.find(TeatroEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
}
