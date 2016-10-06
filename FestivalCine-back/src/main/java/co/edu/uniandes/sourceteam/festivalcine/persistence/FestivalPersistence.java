
package co.edu.uniandes.sourceteam.festivalcine.persistence;

import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class FestivalPersistence {

    private static final Logger LOGGER = Logger.getLogger(FestivalPersistence.class.getName());
    
    @PersistenceContext(unitName = "SourceteamPU")
    protected EntityManager em;

}
