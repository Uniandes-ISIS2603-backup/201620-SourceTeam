/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.SalaDTO;
import co.edu.uniandes.rest.cines.exceptions.SalaException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ya.bejarano10
 */
public class SalaMock {
    // objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(SalaMock.class.getName());
	
	// listado de salas
    private static ArrayList<SalaDTO> salas;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public SalaMock() {

    	if (salas == null) {
            salas = new ArrayList<>();
            salas.add(new SalaDTO(1, 30 ,15, 15, Boolean.TRUE));
            salas.add(new SalaDTO(2, 30, 15, 15, Boolean.TRUE));
            salas.add(new SalaDTO(3, 30, 15, 15, Boolean.TRUE));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de salas");
    	logger.info("salas" + salas );
    }    
    
	/**
	 * Obtiene el listado de salas. 
	 * @return lista de salas
	 * @throws SalaException cuando no existe la lista en memoria  
	 */    
    public List<SalaDTO> getSalas() throws SalaException {
    	if (salas == null) {
    		logger.severe("Error interno: lista de salas no existe.");
    		throw new SalaException("Error interno: lista de salas no existe.");    		
    	}
    	
    	logger.info("retornando todas las salas");
    	return salas;
    }

 

    /**
     * Agrega una sala a la lista.
     * @param newSala sala a adicionar
     * @throws SalaException cuando ya existe una sala con el id suministrado
     * @return sala agregada
     */
    public SalaDTO createSala(SalaDTO newSala) throws SalaException {
    	logger.info("recibiendo solicitud de agregar sala " + newSala);
    	
    	// la nueva sala tiene numero ?
    	if ( newSala.getNumSala() != null ) {
	    	// busca la sala con el numero suministrado
	        for (SalaDTO sala : salas) {
	        	// si existe una sala con ese id
	            if (Objects.equals(sala.getNumSala(), newSala.getNumSala())){
	            	logger.severe("Ya existe una sala con ese numero");
	                throw new SalaException("Ya existe una sala con ese numero");
	            }
	        }
	        
	    // la nueva sala no tiene numero ? 
    	} else {

    		// genera un numero para la ciudad
    		logger.info("Generando numero de sala para la nueva sala");
    		long newSal = 1;
	        for (SalaDTO sala : salas) {
	            if (newSal <= sala.getNumSala()){
	                newSal =  sala.getNumSala() + 1;
	            }
	        }
	        newSala.setNumSala(newSal);
    	}
    	
        // agrega la sala
    	logger.info("agregando sala " + newSala);
        salas.add(newSala);
        return newSala;
    }

}
