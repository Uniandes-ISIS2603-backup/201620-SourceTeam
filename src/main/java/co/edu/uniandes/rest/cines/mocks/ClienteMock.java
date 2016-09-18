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
 * @author s.rodriguez20
 */
public class ClienteMock {
	
	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(ClienteMock.class.getName());
	
	// listado de Clientes
    private static ArrayList<ClienteDTO> clientes;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public ClienteMock() {

    	if (clientes == null) {
                try {
                    clientes = new ArrayList<>();
                    createCliente(new ClienteDTO("Santipan","Rodriguez ",12341L, true));
                    createCliente(new ClienteDTO("Batman","Rodriguez ",12213L, false));
                    createCliente(new ClienteDTO("Maxitos","Rodriguez ",3214L, true));
                } catch (ClienteException ex) {
                   logger.severe("Error interno: No se pueden crear las clientes de prueba.");
                }
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de Clientes");
    	logger.info("Clientes" + clientes );
    }    
    
	/**
	 * Obtiene el listado de personas. 
	 * @return lista de Clientes
	 * @throws ClienteException cuando no existe la lista en memoria  
	 */    
    public List<ClienteDTO> getClientes() throws ClienteException {
    	if (clientes == null) {
    		logger.severe("Error interno: lista de Clientes no existe.");
    		throw new ClienteException("Error interno: lista clientes no existe.");    		
    	}
    	
    	logger.info("retornando todas las clientes");
    	return clientes;
    }

 

    /**
     * Agrega una Clientes a la lista.
     * @param newCliente Clientes a adicionar
     * @throws ClienteException cuando ya existe una Clientes con el id suministrado
     * @return Clientes agregada
     */
    public ClienteDTO createCliente(ClienteDTO newCliente) throws ClienteException {
    	logger.info("recibiendo solicitud de agregar cliente " + newCliente);
    	
    	// la nueva Clientes tiene id ?
    	if ( newCliente.getId()!= null && newCliente.getId() != 0  ) {
	    	// busca la Clientes con el id suministrado
	        for (ClienteDTO cliente : clientes) {
	        	// si existe una Clientes con ese id
	            if (Objects.equals(cliente.getId(), newCliente.getId())){
	            	logger.severe("Ya existe una Clientes con ese id");
	                throw new ClienteException("Ya existe una cliente con ese id");
	            }
	        }
	    // la nueva Clientes no tiene id ? 
    	} else {

    		// genera un id para la Clientes
    		logger.info("Generando id paa la nueva Clientes");
    		long newId = 1;
	        for (ClienteDTO cliente : clientes) {
	            if (newId <= cliente.getId()){
	                newId =  cliente.getId() + 1;
	            }
	        }
	        newCliente.setId(newId);
    	}
    	
        // agrega la Clientes
    	logger.info("agregando cliente " + newCliente);
        clientes.add(newCliente);
        return newCliente;
    }
    public ClienteDTO getCliente(int id) throws ClienteException{
        if (clientes == null) {
    		logger.severe("Error interno: lista de Clientes no existe.");
    		throw new ClienteException("Error interno: lista de clientes no existe.");    		
    	}
        for (int i = 0; i < clientes.size(); i++) {
            if(clientes.get(i).getId()==id){
                return clientes.get(i);
            }
        }
        throw new ClienteException("Error interno: no existe una cliente con ese id.");
    }
    

    /**
     * Actualiza una Clientes dado su id
     * 
     * @param id de la Clientes a modificar
     * @param newClientes información para actualizar
     * @return la Clientes actualizada
     * @throws ClientesException si no existe una Clientes con ese id
     */
    public ClienteDTO updateCliente(int id, ClienteDTO newCliente) throws ClienteException {
        for (int i = 0; i < clientes.size(); i++) {
            if(id == clientes.get(i).getId()){
                clientes.set(i, newCliente);
                return clientes.get(i);
            }
        }
        logger.severe("No existe una Clientes con ese id");
        throw new ClienteException("No existe una Clientes con ese id");
    }
    
    
    /**
     * Elimina una Clientes del listado
     * 
     * @param id de la Clientes a eliminar
     * @throws ClientesException si no existe una Clientes con ese id
     */
    public void deleteCliente(int id) throws ClienteException{
//        logger.info("Antes del ciclo");
        for (int i = 0; i < clientes.size(); i++) {
//            logger.info("antes del if");
            if(clientes.get(i).getId()==id){
//                logger.info("dentro del if");
                clientes.remove(i);
//                logger.info("despues de remover");
                return;
            }
        }
        logger.severe("No existe una Clientes con ese id");
        throw new ClienteException("No existe una Clientes con ese id");
    }

   
}
