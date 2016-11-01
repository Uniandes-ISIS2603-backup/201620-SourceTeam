/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.api;

import co.edu.uniandes.sourceteam.festivalcine.entities.ClienteEntity;
import co.edu.uniandes.sourceteam.festivalcine.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author s.rodriguez20
 */
public interface IClienteLogic {

    public List<ClienteEntity> getClientes();

    public ClienteEntity getCliente(Long id);

    public ClienteEntity createCliente(ClienteEntity entity);

    public ClienteEntity updateCliente(ClienteEntity entity);

    public void deleteCliente(Long id);

    public Integer getNumberOfBolestasCliente(Long id);

    
}