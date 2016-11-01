/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.api;

import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import java.util.List;

/**
 *
 * @author ya.bejarano10
 */
public interface ISalaLogic 
{
    public List<SalaEntity> getSalas();
    public SalaEntity getSala(Long id);
    public SalaEntity createSala(SalaEntity entity);
    public SalaEntity updateSala(SalaEntity entity);
    public void deleteSala(Long id);
    
}
