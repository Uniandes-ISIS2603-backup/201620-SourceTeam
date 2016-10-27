/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.api;

import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
import java.util.List;

/**
 *
 * @author ba.bohorquez10
 */
public interface ITeatroLogic 
{
    public List<TeatroEntity> getTeatros();

    public TeatroEntity getTeatro(Long id);

    public TeatroEntity getTeatroByName(String name);

    public TeatroEntity createTeatro(TeatroEntity entity) throws Exception;

    public TeatroEntity updateTeatro(TeatroEntity entity);

    public void deleteTeatro(Long id);
}
