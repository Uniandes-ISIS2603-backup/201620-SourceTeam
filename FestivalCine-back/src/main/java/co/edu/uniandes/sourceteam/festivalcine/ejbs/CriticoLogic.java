/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.sourceteam.festivalcine.ejbs;

import co.edu.uniandes.sourceteam.festivalcine.api.ICriticoLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.CriticoEntity;
import co.edu.uniandes.sourceteam.festivalcine.persistence.CriticoPersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;


@Stateless
public class CriticoLogic implements ICriticoLogic {

    @Inject private CriticoPersistence persistence;


    /**
     * Obtiene la lista de los registros de Critico.
     *
     * @return Colección de objetos de CriticoEntity.
     * 
     */
    @Override
    public List<CriticoEntity> getCriticos() {
        return persistence.findAll();
    }


    /**
     * Obtiene los datos de una instancia de Critico a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de CriticoEntity con los datos del Critico consultado.
     * 
     */
    @Override
    public CriticoEntity getCritico(Long id) {
        return persistence.find(id);
    }

    /**
     * Obtiene los datos de una instancia de Critico a partir de su name.
     *
     * @param name nombre del empleado de la instancia a consultar
     * @return el primer empleado con ese nombre .
     * 
     */
    @Override
    public CriticoEntity findByName(String name) {
        return persistence.findByName(name);
    }
    /**
     * Se encarga de crear un Critico en la base de datos.
     *
     * @param entity Objeto de CriticoEntity con los datos nuevos
     * @return Objeto de CriticoEntity con los datos nuevos y su ID.
     * 
     */
    @Override
    public CriticoEntity createCritico(CriticoEntity entity) throws Exception {
        persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Critico.
     *
     * @param entity Instancia de CriticoEntity con los nuevos datos.
     * @return Instancia de CriticoEntity con los datos actualizados.
     * 
     */
    @Override
    public CriticoEntity updateCritico(CriticoEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Critico de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     * 
     */
    @Override
    public void deleteCritico(Long id) {
        persistence.delete(id);
    }

  
}
