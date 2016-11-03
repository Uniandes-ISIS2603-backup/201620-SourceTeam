/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.ejbs;

import co.edu.uniandes.sourceteam.festivalcine.api.ISalaLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.SalaPersistence;
import java.util.List;
import javax.inject.Inject;

/**
 *
 * @author ya.bejarano10
 */
public class SalaLogic implements ISalaLogic
{
    @Inject
    private SalaPersistence persistence;

    /**
     * Obtiene la lista de los registros de Company.
     *
     * @return Colección de objetos de CompanyEntity.
     *
     */
    @Override
    public List<SalaEntity> getSalas() {
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
    public SalaEntity getSala(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Company en la base de datos.
     *
     * @param entity Objeto de CompanyEntity con los datos nuevos
     * @return Objeto de CompanyEntity con los datos nuevos y su ID.
     * @throws java.lang.Exception
     *
     */
    @Override
    public SalaEntity createSala(SalaEntity entity) 
    {
     
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
    public  SalaEntity updateSala(SalaEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Company de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
    @Override
    public void deleteSala(Long id) {
        persistence.delete(id);
    }

    
}
