/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.SillaDTO;
import co.edu.uniandes.rest.cines.exceptions.SillaException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s.rodriguez20
 */
public class SillaMock {
	
	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(SillaMock.class.getName());
	
	// listado de Sillas
    private static ArrayList<SillaDTO> sillas;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public SillaMock() {

    	if (sillas == null) {
                try {
                    sillas = new ArrayList<>();
                    createSilla(new SillaDTO(1,3,false, false));
                    createSilla(new SillaDTO(7,5,false, true));
                    createSilla(new SillaDTO(1,6,true,false));
                    createSilla(new SillaDTO(2,2,false, false));
                    createSilla(new SillaDTO(3,4,true, true));
                } catch (SillaException ex) {
                   logger.severe("Error interno: No se pueden crear las sillas de prueba.");
                }
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de Sillas");
    	logger.info("Sillas" + sillas );
    }    
    
	/**
	 * Obtiene el listado de personas. 
	 * @return lista de Sillas
	 * @throws SillaException cuando no existe la lista en memoria  
	 */    
    public List<SillaDTO> getSillas() throws SillaException {
    	if (sillas == null) {
    		logger.severe("Error interno: lista de sillas no existe.");
    		throw new SillaException("Error interno: lista sillas no existe.");    		
    	}
    	
    	logger.info("retornando todas las sillas");
    	return sillas;
    }

 

    /**
     * Agrega una Sillas a la lista.
     * @param newSilla Sillas a adicionar
     * @throws SillaException cuando ya existe una Sillas con el id suministrado
     * @return Sillas agregada
     */
    public SillaDTO createSilla(SillaDTO newSilla) throws SillaException {
    	logger.info("recibiendo solicitud de agregar silla " + newSilla);
    	
    	// la nueva Sillas tiene id ?
    	if ( newSilla.getId()!= null && newSilla.getId() != 0  ) {
	    	// busca la Sillas con el id suministrado
	        for (SillaDTO city : sillas) {
	        	// si existe una Sillas con ese id
	            if (Objects.equals(city.getId(), newSilla.getId())){
	            	logger.severe("Ya existe una Sillas con ese id");
	                throw new SillaException("Ya existe una silla con ese id");
	            }
	        }
	    // la nueva Sillas no tiene id ? 
    	} else {

    		// genera un id para la Sillas
    		logger.info("Generando id paa la nueva Sillas");
    		long newId = 1;
	        for (SillaDTO city : sillas) {
	            if (newId <= city.getId()){
	                newId =  city.getId() + 1;
	            }
	        }
	        newSilla.setId(newId);
    	}
    	
        // agrega la Sillas
    	logger.info("agregando silla " + newSilla);
        sillas.add(newSilla);
        return newSilla;
    }
    public SillaDTO getSilla(int id) throws SillaException{
        if (sillas == null) {
    		logger.severe("Error interno: lista de Sillas no existe.");
    		throw new SillaException("Error interno: lista de sillas no existe.");    		
    	}
        for (int i = 0; i < sillas.size(); i++) {
            if(sillas.get(i).getId()==id){
                return sillas.get(i);
            }
        }
        throw new SillaException("Error interno: no existe una silla con ese id.");
    }
    

    /**
     * Actualiza una Sillas dado su id
     * 
     * @param id de la Sillas a modificar
     * @param newSillas información para actualizar
     * @return la Sillas actualizada
     * @throws SillasException si no existe una Sillas con ese id
     */
    public SillaDTO updateSilla(int id, SillaDTO newSilla) throws SillaException {
        for (int i = 0; i < sillas.size(); i++) {
            if(id == sillas.get(i).getId()){
                sillas.set(i, newSilla);
                return sillas.get(i);
            }
        }
        logger.severe("No existe una Sillas con ese id");
        throw new SillaException("No existe una Sillas con ese id");
    }
    
    
    /**
     * Elimina una Sillas del listado
     * 
     * @param id de la Sillas a eliminar
     * @throws SillasException si no existe una Sillas con ese id
     */
    public void deleteSilla(int id) throws SillaException{
//        logger.info("Antes del ciclo");
        for (int i = 0; i < sillas.size(); i++) {
//            logger.info("antes del if");
            if(sillas.get(i).getId()==id){
//                logger.info("dentro del if");
                sillas.remove(i);
//                logger.info("despues de remover");
                return;
            }
        }
        logger.severe("No existe una Sillas con ese id");
        throw new SillaException("No existe una Sillas con ese id");
    }

   
}
