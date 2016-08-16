/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.ClienteDTO;
import co.edu.uniandes.rest.cines.exceptions.ClienteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ca.nieto11
 */
public class ClienteMock {
    
    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(ClienteMock.class.getName());
    
    // listado de clientes
    private static ArrayList<ClienteDTO> clientes;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ClienteMock() {

    	if (clientes == null) {
            clientes = new ArrayList<>();
            clientes.add(new ClienteDTO("Camilo", true));
            clientes.add(new ClienteDTO("Sebastian", false));
            clientes.add(new ClienteDTO("Laura", true));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de clientes");
    	logger.info("clientes" + clientes );
    }    
    
	/**
	 * Obtiene el listado de personas. 
	 * @return lista de clientes
	 * @throws ClienteException cuando no existe la lista en memoria  
	 */    
    public List<ClienteDTO> getClientes() throws ClienteException {
    	if (clientes == null) {
    		logger.severe("Error interno: lista de clientes no existe.");
    		throw new ClienteException("Error interno: lista de clientes no existe.");    		
    	}
    	
    	logger.info("retornando todas los clientes");
    	return clientes;
    }

 

    /**
     * Agrega un cliente a la lista.
     * @param newClient cliente a adicionar
     * @throws ClienteException cuando ya existe un cliente con el id suministrado
     * @return cliente agregado
     */
    public ClienteDTO createCliente(ClienteDTO newClient) throws ClienteException {
        logger.info("recibiendo solicitud de agregar cliente " + newClient);
        
        // busca la ciudad con el nombre suministrado
        for (ClienteDTO cliente : clientes) {
            // si existe un cliente con ese nombre
            if (Objects.equals(cliente.getNombre(), newClient.getNombre())){
                logger.severe("Ya existe un cliente con ese nombre");
                throw new ClienteException("Ya existe un cliente con ese nombre");
            }
        }
        // agrega al cliente
        logger.info("agregando cliente " + newClient);
        clientes.add(newClient);
        return newClient;
    }
   
    
    /**
     * Retorna un cliente dado su nombre
     * 
     * @param name nombre del cliente a buscar
     * @return cliente buscado
     * @throws ClienteException cuando no existe el nombre buscado
     */
    public ClienteDTO getClientbyName(String name) throws ClienteException{
        if (clientes == null) {
    		logger.severe("Error interno: lista de clientes no existe.");
    		throw new ClienteException("Error interno: lista de clientes no existe.");    		
    	}
        for (int i = 0; i < clientes.size(); i++) {
            if(clientes.get(i).getNombre().equalsIgnoreCase(name)){
                return clientes.get(i);
            }
        }
        throw new ClienteException("Error interno: no existe un cliente con ese nombre.");
    }

    /**
     * Actualiza un cliente dado su nombre
     * 
     * @param nombre del cliente a modificar
     * @param newClient información para actualizar
     * @return el cliente actualizado
     * @throws ClienteException si no existe un cliente con ese nombre
     */
    public ClienteDTO updateCliente(String nombre, ClienteDTO newClient) throws ClienteException {
        for (int i = 0; i < clientes.size(); i++) {
            if(clientes.get(i).getNombre().equalsIgnoreCase(nombre)){
                clientes.set(i, newClient);
                return clientes.get(i);
            }
        }
        logger.severe("No existe un cliente con ese nombre");
        throw new ClienteException("No existe un cliente con ese nombre");
    }
    
    
    /**
     * Elimina un cliente del listado
     * 
     * @param nombre del cliente a eliminar
     * @throws ClienteException si no existe un cliente con ese nombre
     */
    public void deleteCliente(String nombre) throws ClienteException{
//        logger.info("Antes del ciclo");
        for (int i = 0; i < clientes.size(); i++) {
//            logger.info("antes del if");
            if(clientes.get(i).getNombre().equals(nombre)){
//                logger.info("dentro del if");
                clientes.remove(i);
//                logger.info("despues de remover");
                return;
            }
        }
        logger.severe("No existe un cliente con ese nombre");
        throw new ClienteException("No existe un cliente con ese nombre");
    }
}
