/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.api;

import co.edu.uniandes.sourceteam.festivalcine.entities.CriticoEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.PeliculaEntity;
import java.util.List;

/**
 *
 * @author ya.bejarano10
 */
public interface IPeliculaLogic 
{
    public List<PeliculaEntity> getPeliculas();
    public PeliculaEntity getPelicula(Long id);
    public PeliculaEntity createPelicula(PeliculaEntity entity)throws Exception;
    public PeliculaEntity updatePelicula(PeliculaEntity entity);
    public void deletePelicula(Long id);
    
}
