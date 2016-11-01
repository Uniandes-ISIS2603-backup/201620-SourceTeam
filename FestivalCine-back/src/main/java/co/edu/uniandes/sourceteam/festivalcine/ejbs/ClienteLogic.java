/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.ejbs;


import co.edu.uniandes.sourceteam.festivalcine.api.IClienteLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.ClienteEntity;
import co.edu.uniandes.sourceteam.festivalcine.exceptions.BusinessLogicException;
import co.edu.uniandes.sourceteam.festivalcine.persistence.ClientePersistence;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class ClienteLogic implements IClienteLogic {

    @Inject
    private ClientePersistence persistence;

    /**
     * Obtiene la lista de los registros de Cliente.
     *
     * @return Colección de objetos de ClienteEntity.
     *
     */
    @Override
    public List<ClienteEntity> getClientes() {
        return persistence.findAll();
    }

    /**
     * Obtiene los datos de una instancia de Cliente a partir de su ID.
     *
     * @param id Identificador de la instancia a consultar
     * @return Instancia de ClienteEntity con los datos del Cliente consultado.
     *
     */
    public ClienteEntity getCliente(Long id) {
        return persistence.find(id);
    }

    /**
     * Se encarga de crear un Cliente en la base de datos.
     *
     * @param entity Objeto de ClienteEntity con los datos nuevos
     * @return Objeto de ClienteEntity con los datos nuevos y su ID.
     *
     */
    @Override
    public ClienteEntity createCliente(ClienteEntity entity)  {
        
            persistence.create(entity);
        return entity;
    }

    /**
     * Actualiza la información de una instancia de Cliente.
     *
     * @param entity Instancia de ClienteEntity con los nuevos datos.
     * @return Instancia de ClienteEntity con los datos actualizados.
     *
     */
    @Override
    public ClienteEntity updateCliente(ClienteEntity entity) {
        return persistence.update(entity);
    }

    /**
     * Elimina una instancia de Cliente de la base de datos.
     *
     * @param id Identificador de la instancia a eliminar.
     *
     */
    @Override
    public void deleteCliente(Long id) {
        persistence.delete(id);
    }

   

    @Override
    public Integer getNumberOfBolestasCliente(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
