
package co.edu.uniandes.sourceteam.festivalcine.persistence;

import co.edu.uniandes.sourceteam.festivalcine.entities.FestivalEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;


@Stateless
public class FestivalPersistence {

    private static final Logger LOGGER = Logger.getLogger(FestivalPersistence.class.getName());

    @PersistenceContext(unitName = "SourceteamPU")
    protected EntityManager em;    
 
    
    public List<FestivalEntity> findAll() {
        LOGGER.info("Consultando todos los Festivales");
        Query q = em.createQuery("select u from FestivalEntity u");
        return q.getResultList();
    }
    
    public FestivalEntity find(Long id) {
        LOGGER.log(Level.INFO, "Consultando teatro con id={0}", id);
        return em.find(FestivalEntity.class, id);
    }
            
    public FestivalEntity create(FestivalEntity entity) throws Exception{
        LOGGER.info("Creando un Festival nuevo");
        em.persist(entity);
        LOGGER.info("Festival creado");
        return entity;
    }

    public FestivalEntity update(FestivalEntity entity) {
        LOGGER.log(Level.INFO, "Actualizando Festival con id={0}", entity.getId());
        return em.merge(entity);
    }

    /**
     *
     * @param id: corresponde a un id válido que existe el festival
     * correspondiente en la base de datos.
     */
    public void delete(Long id) {
        LOGGER.log(Level.INFO, "Borrando Festival con id={0}", id);
        FestivalEntity entity = em.find(FestivalEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }

    public FestivalEntity findByName(String name) {
        LOGGER.log(Level.INFO, "Consultando festival con name = {0}", name);
        TypedQuery<FestivalEntity> q
                = em.createQuery("select u from FestivalEntity u where u.name = :name", FestivalEntity.class);
        q = q.setParameter("name", name);
        
       List<FestivalEntity> festivalSimilarName = q.getResultList();
        if (festivalSimilarName.isEmpty() ) {
            return null; 
        } else {
            return festivalSimilarName.get(0);
        }
    }
}
