/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.ejbs;

import co.edu.uniandes.sourceteam.festivalcine.api.IPeliculaLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.PeliculaEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.PeliculaPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author ya.bejarano10
 */
@Stateless
public class PeliculaLogic implements IPeliculaLogic
{
    @Inject
    private PeliculaPersistence persistence;

    /**
     * Obtiene la lista de los registros de Peliculas.
     *
     * @return Colección de objetos de CompanyEntity.
     *
     */
    @Override
    public List<PeliculaEntity> getPeliculas() {
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Company a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CompanyEntity con los datos del Company consultado.
     *
     */
    @Override
    public PeliculaEntity getPelicula(Long id) {
        return persistence.find(id);
    }
    
    
    @Override
    public PeliculaEntity getPeliculaByName(String name) {
        return persistence.findByName(name);
    }

    /**
     * Se encarga de crear un Company en la base de datos.
     *
     * @param entity Objeto de CompanyEntity con los datos nuevos
     * @return Objeto de CompanyEntity con los datos nuevos y su ID.
     *
     */
    @Override
    public PeliculaEntity createPelicula(PeliculaEntity entity) {
            persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Company.
     *
     * @param entity Instancia de CompanyEntity con los nuevos datos.
     * @return Instancia de CompanyEntity con los datos actualizados.
     *
     */
    @Override
    public  PeliculaEntity updatePelicula(PeliculaEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Company de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
    @Override
    public void deletePelicula(Long id) {
        persistence.delete(id);
    }
    

    
}
