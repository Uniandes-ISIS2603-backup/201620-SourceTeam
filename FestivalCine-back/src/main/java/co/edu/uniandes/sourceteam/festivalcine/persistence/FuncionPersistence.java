package co.edu.uniandes.sourceteam.festivalcine.persistence;

import co.edu.uniandes.sourceteam.festivalcine.entities.FuncionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * 
 * @author ba.bohorquez10
 */
@Stateless
public class FuncionPersistence 
{
    private static final Logger LOGGER = Logger.getLogger( FuncionPersistence.class.getName() );

    @PersistenceContext(unitName = "SourceteamPU")
    protected EntityManager em;

    public FuncionEntity find(Long id)
    {
        LOGGER.log(Level.INFO, "Consultando funcion con id={0}", id);
        return em.find(FuncionEntity.class, id);
    }
    
    public List<FuncionEntity> findAll() 
    {
        LOGGER.info("Consultando todas las funciones");
        Query q = em.createQuery("select u from FuncionEntity u");
        return q.getResultList();
    }
        
    public FuncionEntity create(FuncionEntity entity) 
    {
        LOGGER.info("Creando una funcion nueva");
        em.persist(entity);
        LOGGER.info("Funcion creada");
        return entity;
    }

    public FuncionEntity update(FuncionEntity entity) 
    {
        LOGGER.log(Level.INFO, "Actualizando funcion con id={0}", entity.getId() );
        return em.merge(entity);
    }
    
    public void delete(Long id) 
    {
        LOGGER.log(Level.INFO, "Borrando funcion con id={0}", id);
        FuncionEntity entity = em.find(FuncionEntity.class, id);
        assert entity != null;
        em.remove(entity);
    }
}
