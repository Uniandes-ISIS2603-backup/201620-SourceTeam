
package co.edu.uniandes.sourceteam.festivalcine.persistence;

import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
    
}
