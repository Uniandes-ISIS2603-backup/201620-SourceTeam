/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.ejbs;

import co.edu.uniandes.sourceteam.festivalcine.api.IFuncionLogic;
import co.edu.uniandes.sourceteam.festivalcine.api.IPeliculaLogic;
import co.edu.uniandes.sourceteam.festivalcine.api.ISalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.FuncionEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.PeliculaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.FuncionPersistence;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ba.bohorquez10
 */
@Stateless
public class FuncionLogic implements IFuncionLogic
{
    @Inject
    private FuncionPersistence persistence;
    
    @Inject
    private IPeliculaLogic peliculasLogic;
    
    @Inject
    private ISalaLogic salasLogic;
    
    @Override
    public List<FuncionEntity> getFunciones() 
    {
        return persistence.findAll();
    }
    
    @Override
    public FuncionEntity getFuncion(Long id) 
    {
        return persistence.find(id);
    }

    @Override
    public FuncionEntity getFuncionByName(String name) 
    {
        return persistence.findByName(name);
    }

    @Override
    public FuncionEntity createFuncion(FuncionEntity entity) throws Exception 
    {
        FuncionEntity alreadyExist = getFuncionByName( entity.getName() );
        
        if (alreadyExist != null) 
        {
            throw new Exception("Ya existe una funcion con ese nombre.");
        }
        
        if (entity.getPrecio() <= 0)
        {
            throw new Exception("El precio debe ser mayor a 0.");
        }
       
        persistence.create(entity);
        
        return entity;
    }

    @Override
    public FuncionEntity updateFuncion(FuncionEntity entity) 
    {
        return persistence.update(entity);
    }
    
    @Override
    public void deleteFuncion(Long id)
    {
        persistence.delete(id);
    }
    
    @Override
    public PeliculaEntity getPelicula(Long funcionId)
    {
        return persistence.find(funcionId).getPelicula();
    }
    
    @Override
    public PeliculaEntity setPelicula(Long funcionId, Long peliculaId)
    {
        FuncionEntity funcionEntity = persistence.find(funcionId);
        PeliculaEntity peliculaEntity = peliculasLogic.getPelicula(peliculaId);
        funcionEntity.setPelicula(peliculaEntity);
        
        return peliculaEntity;
    }
    
    @Override
    public SalaEntity getSala(Long funcionId)
    {
        return persistence.find(funcionId).getSala();
    }
    
    @Override
    public SalaEntity setSala(Long funcionId, Long salaId)
    {
        FuncionEntity funcionEntity = persistence.find(funcionId);
        SalaEntity salaEntity = salasLogic.getSala(salaId);
        funcionEntity.setSala(salaEntity);
        
        return salaEntity; 
    }
}
