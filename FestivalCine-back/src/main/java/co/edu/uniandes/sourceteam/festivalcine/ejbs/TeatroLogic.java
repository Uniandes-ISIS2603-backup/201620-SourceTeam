/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.ejbs;

import co.edu.uniandes.sourceteam.festivalcine.api.ISalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.api.ITeatroLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.TeatroPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ba.bohorquez10
 */
@Stateless
public class TeatroLogic implements ITeatroLogic
{
    @Inject
    private TeatroPersistence persistence;
    
    @Inject 
    private ISalaLogic salasLogic;
    
    @Override
    public List<TeatroEntity> getTeatros() 
    {
        return persistence.findAll();
    }

    @Override
    public TeatroEntity getTeatro(Long id) 
    {
        return persistence.find(id);
    }

    @Override
    public TeatroEntity getTeatroByName(String name) 
    {
        return persistence.findByName(name);
    }

    @Override
    public TeatroEntity createTeatro(TeatroEntity entity) throws Exception 
    {
        TeatroEntity alreadyExist = getTeatroByName( entity.getName() );
        
        if (alreadyExist != null) 
        {
            throw new Exception("Ya existe un teatro con ese nombre.");
        } 
        else
        {
            persistence.create(entity);
        }
        
        return entity;
    }

    @Override
    public TeatroEntity updateTeatro(TeatroEntity entity) 
    {
        return persistence.update(entity);
    }

    @Override
    public void deleteTeatro(Long id)
    {
        persistence.delete(id);
    }
    
    @Override
    public SalaEntity getSala(Long teatroId, Long salaId)
    {
        List<SalaEntity> list = persistence.find(teatroId).getSalas();
        for(int i = 0; i < list.size(); i++)
        {
            SalaEntity actual = list.get(i);
            
            if(actual.getId() == salaId)
            {
                return actual;
            }
        }
        
        return null;
    }
    
    @Override
    public List<SalaEntity> listSalas(Long teatroId)
    {
        return persistence.find(teatroId).getSalas();
    }
    
    @Override
    public SalaEntity addSala(Long teatroId, Long salaId)
    {
        TeatroEntity teatroEntity = persistence.find(teatroId);
        SalaEntity salaEntity = salasLogic.getSala(salaId);
        salaEntity.setTeatro(teatroEntity);
        
        return salaEntity;
    }
    
    @Override
    public void removeSala(Long teatroId, Long salaId)
    {
        TeatroEntity teatro = persistence.find(teatroId);
        List<SalaEntity> list = teatro.getSalas();
        for(int i = 0; i < list.size(); i++)
        {
            SalaEntity actual = list.get(i);
            
            if(actual.getId() == salaId)
            {
                actual.setTeatro(null);
                break;
            }
        }
    }
}
