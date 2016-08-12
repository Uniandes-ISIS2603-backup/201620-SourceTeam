/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.mocks;

import co.edu.uniandes.rest.cities.dtos.CityDTO;
import co.edu.uniandes.rest.cities.dtos.SalaDTO;
import co.edu.uniandes.rest.cities.exceptions.CityLogicException;
import co.edu.uniandes.rest.cities.exceptions.SalaException;
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
	
	// listado de ciudades
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
	 * Obtiene el listado de personas. 
	 * @return lista de ciudades
	 * @throws CityLogicException cuando no existe la lista en memoria  
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
     * Agrega una ciudad a la lista.
     * @param newCity ciudad a adicionar
     * @throws CityLogicException cuando ya existe una ciudad con el id suministrado
     * @return ciudad agregada
     */
    public SalaDTO createSala(SalaDTO newCity) throws SalaException {
    	logger.info("recibiendo solicitud de agregar ciudad " + newCity);
    	
    	// la nueva ciudad tiene id ?
    	if ( newCity.getNumSala() != null ) {
	    	// busca la ciudad con el id suministrado
	        for (SalaDTO city : salas) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(city.getNumSala(), newCity.getNumSala())){
	            	logger.severe("Ya existe una sala con ese numero");
	                throw new SalaException("Ya existe una sala con ese numero");
	            }
	        }
	        
	    // la nueva ciudad no tiene id ? 
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando numero de sala para la nueva sala");
    		long newSala = 1;
	        for (SalaDTO city : salas) {
	            if (newSala <= city.getNumSala()){
	                newSala =  city.getNumSala() + 1;
	            }
	        }
	        newCity.setNumSala(newSala);
    	}
    	
        // agrega la ciudad
    	logger.info("agregando sala " + newCity);
        salas.add(newCity);
        return newCity;
    }

}
