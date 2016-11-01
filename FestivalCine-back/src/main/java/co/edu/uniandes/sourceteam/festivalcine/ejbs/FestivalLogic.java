package co.edu.uniandes.sourceteam.festivalcine.ejbs;

import co.edu.uniandes.sourceteam.festivalcine.entities.FestivalEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.sourceteam.festivalcine.api.IFestivalLogic;
import co.edu.uniandes.sourceteam.festivalcine.persistence.FestivalPersistence;

@Stateless
public class FestivalLogic implements IFestivalLogic {

    
    @Inject private FestivalPersistence persistence;

    
    @Override
    public List<FestivalEntity> getFestivales(Long festivalId) {
        return persistence.findAll();
    }
    
    @Override
    public FestivalEntity getFestival(Long festivalid) {
        return persistence.find(festivalid);
    }

    @Override
    public FestivalEntity createFestival(FestivalEntity entity) {
        return persistence.create(entity);
    }

    @Override
    public FestivalEntity updateFestival(FestivalEntity entity) {
       return persistence.update(entity);
    }

    @Override
    public void deleteFestival(Long id) {
        persistence.delete(id);
    }


}
