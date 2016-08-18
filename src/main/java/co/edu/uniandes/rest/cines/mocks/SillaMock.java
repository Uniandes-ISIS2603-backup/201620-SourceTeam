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
	
	// listado de ciudades
    private static ArrayList<SillaDTO> sillas;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public SillaMock() {

    	if (sillas == null) {
            sillas = new ArrayList<>();
            sillas.add(new SillaDTO(1L,1,3,false, false));
            sillas.add(new SillaDTO(2L,1,5,false, false));
            sillas.add(new SillaDTO(3L,1,6,true,false));
            sillas.add(new SillaDTO(4L,2,2,false, false));
            sillas.add(new SillaDTO(5L,3,4,false, false));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de ciudades");
    	logger.info("Sillas" + sillas );
    }    
    
	/**
	 * Obtiene el listado de personas. 
	 * @return lista de ciudades
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
     * Agrega una ciudad a la lista.
     * @param newSilla ciudad a adicionar
     * @throws SillaException cuando ya existe una ciudad con el id suministrado
     * @return ciudad agregada
     */
    public SillaDTO createSilla(SillaDTO newSilla) throws SillaException {
    	logger.info("recibiendo solicitud de agregar silla " + newSilla);
    	
    	// la nueva ciudad tiene id ?
    	if ( newSilla.getFila() != 0  ) {
	    	// busca la ciudad con el id suministrado
	        for (SillaDTO city : sillas) {
	        	// si existe una ciudad con ese id
	            if (Objects.equals(city.getId(), newSilla.getId())){
	            	logger.severe("Ya existe una ciudad con ese id");
	                throw new SillaException("Ya existe una silla con ese id");
	            }
	        }
	        
	    // la nueva ciudad no tiene id ? 
    	} else {

    		// genera un id para la ciudad
    		logger.info("Generando id paa la nueva ciudad");
    		long newId = 1;
	        for (SillaDTO city : sillas) {
	            if (newId <= city.getId()){
	                newId =  city.getId() + 1;
	            }
	        }
	        newSilla.setId(newId);
    	}
    	
        // agrega la ciudad
    	logger.info("agregando silla " + newSilla);
        sillas.add(newSilla);
        return newSilla;
    }
    public SillaDTO getSilla(int id) throws SillaException{
        if (sillas == null) {
    		logger.severe("Error interno: lista de ciudades no existe.");
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
     * Actualiza una boleta dado su id
     * 
     * @param id de la boleta a modificar
     * @param newBoleta información para actualizar
     * @return la boleta actualizada
     * @throws BoletaException si no existe una ciudad con ese id
     */
    public SillaDTO updateBoleta(int id, SillaDTO newBoleta) throws SillaException {
        for (int i = 0; i < sillas.size(); i++) {
            if(id == sillas.get(i).getId()){
                sillas.set(i, newBoleta);
                return sillas.get(i);
            }
        }
        logger.severe("No existe una boleta con ese id");
        throw new SillaException("No existe una boleta con ese id");
    }
    
    
    /**
     * Elimina una boleta del listado
     * 
     * @param id de la boleta a eliminar
     * @throws BoletaException si no existe una boleta con ese id
     */
    public void deleteBoleta(int id) throws SillaException{
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
        logger.severe("No existe una boleta con ese id");
        throw new SillaException("No existe una boleta con ese id");
    }

   
}
