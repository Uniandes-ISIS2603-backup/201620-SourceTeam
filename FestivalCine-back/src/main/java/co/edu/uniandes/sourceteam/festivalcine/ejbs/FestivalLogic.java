package co.edu.uniandes.sourceteam.festivalcine.ejbs;

import co.edu.uniandes.sourceteam.festivalcine.api.ICriticoLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.FestivalEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import co.edu.uniandes.sourceteam.festivalcine.api.IFestivalLogic;
import co.edu.uniandes.sourceteam.festivalcine.api.IFuncionLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.CriticoEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.FuncionEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.FestivalPersistence;

@Stateless
public class FestivalLogic implements IFestivalLogic {

    
    @Inject private FestivalPersistence persistence;

    @Inject 
    private ICriticoLogic criticosLogic;
    
    @Inject 
    private IFuncionLogic funcionLogic;
    
    @Override
    public List<FestivalEntity> getFestivales() {
        return persistence.findAll();
    }
    
    @Override
    public FestivalEntity getFestival(Long festivalid) {
        return persistence.find(festivalid);
    }

    @Override
    public FestivalEntity createFestival(FestivalEntity entity) throws Exception {
       FestivalEntity alreadyExist = getFestivalByName(entity.getName());
        if (alreadyExist != null) {
            throw new Exception("Ya existe una festival con ese nombre");
        } else
        {
            persistence.create(entity);
        }
        return entity;
    }

    @Override
    public FestivalEntity updateFestival(FestivalEntity entity) {
       return persistence.update(entity);
    }

    @Override
    public void deleteFestival(Long id) {
        persistence.delete(id);
    }

    @Override
    public FestivalEntity getFestivalByName(String name) {
        return persistence.findByName(name);
    
    }
    
    @Override
    public List<CriticoEntity> listCriticos(Long festivalId) {
        return persistence.find(festivalId).getCriticos();
    }

    @Override
    public CriticoEntity getCritico(Long festivalId, Long criticoId) {
        List<CriticoEntity> list = persistence.find(festivalId).getCriticos();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId() == criticoId){
                return list.get(i);
            }
        }
        return null;
    }
    
    @Override
    public CriticoEntity addCritico(Long festivalId, Long criticoId) {
        FestivalEntity festivalEntity = persistence.find(festivalId);
        CriticoEntity criticoEntity = criticosLogic.getCritico(criticoId);
        criticoEntity.setFestival(festivalEntity);
        return criticoEntity;
    }
    
    @Override
    public void removeCritico(Long festivalId, Long criticoId) {
        FestivalEntity festival = persistence.find(festivalId);
        List<CriticoEntity> list = festival.getCriticos();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId() == criticoId)
            {
                list.get(i).setFestival(null);
                break;
            }
        }
    }
    
    
    
    
    
    @Override
    public List<FuncionEntity> listFunciones(Long festivalId) {
        return persistence.find(festivalId).getFunciones();
    }

    @Override
    public FuncionEntity getFuncion(Long festivalId, Long funcionId) {
        List<FuncionEntity> list = persistence.find(festivalId).getFunciones();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId() == funcionId){
                return list.get(i);
            }
        }
        return null;
    }
    
    @Override
    public FuncionEntity addFuncion(Long festivalId, Long funcionId) {
        FestivalEntity festivalEntity = persistence.find(festivalId);
        FuncionEntity funcionEntity = funcionLogic.getFuncion(funcionId);
 //       funcionEntity.set(festivalEntity);
        return funcionEntity;
    }
    
    @Override
    public void removeFuncion(Long festivalId, Long criticoId) {
        FestivalEntity festival = persistence.find(festivalId);
        List<CriticoEntity> list = festival.getCriticos();
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).getId() == criticoId)
            {
                list.get(i).setFestival(null);
                break;
            }
        }
    }

}
