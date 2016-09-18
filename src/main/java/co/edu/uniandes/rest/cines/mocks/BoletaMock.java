/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.BoletaDTO;
import co.edu.uniandes.rest.cines.exceptions.BoletaException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s.rodriguez20
 */
public class BoletaMock {
	
	// objeto para presentar logs de las operaciones
	private final static Logger logger = Logger.getLogger(BoletaMock.class.getName());
	
	// listado de Boletas
    private static ArrayList<BoletaDTO> boletas;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public BoletaMock() {

    	if (boletas == null) {
                try {
                    boletas = new ArrayList<>();
                    createBoleta(new BoletaDTO(8000));
                    createBoleta(new BoletaDTO(5000));
                    createBoleta(new BoletaDTO(6000));
                    createBoleta(new BoletaDTO(7000));
                    createBoleta(new BoletaDTO(8000));
                } catch (BoletaException ex) {
                   logger.severe("Error interno: No se pueden crear las boletas de prueba.");
                }
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de Boletas");
    	logger.info("Boletas" + boletas );
    }    
    
	/**
	 * Obtiene el listado de personas. 
	 * @return lista de Boletas
	 * @throws BoletaException cuando no existe la lista en memoria  
	 */    
    public List<BoletaDTO> getBoletas() throws BoletaException {
    	if (boletas == null) {
    		logger.severe("Error interno: lista de boletas no existe.");
    		throw new BoletaException("Error interno: lista boletas no existe.");    		
    	}
    	
    	logger.info("retornando todas las boletas");
    	return boletas;
    }

 

    /**
     * Agrega una Boletas a la lista.
     * @param newBoleta Boletas a adicionar
     * @throws BoletaException cuando ya existe una Boletas con el id suministrado
     * @return Boletas agregada
     */
    public BoletaDTO createBoleta(BoletaDTO newBoleta) throws BoletaException {
    	logger.info("recibiendo solicitud de agregar boleta " + newBoleta);
    	
    	// la nueva Boletas tiene id ?
    	if ( newBoleta.getId()!= null && newBoleta.getId() != 0  ) {
	    	// busca la Boletas con el id suministrado
	        for (BoletaDTO boleta : boletas) {
	        	// si existe una Boletas con ese id
	            if (Objects.equals(boleta.getId(), newBoleta.getId())){
	            	logger.severe("Ya existe una Boletas con ese id");
	                throw new BoletaException("Ya existe una boleta con ese id");
	            }
	        }
	    // la nueva Boletas no tiene id ? 
    	} else {

    		// genera un id para la Boletas
    		logger.info("Generando id paa la nueva Boletas");
    		long newId = 1;
	        for (BoletaDTO boleta : boletas) {
	            if (newId <= boleta.getId()){
	                newId =  boleta.getId() + 1;
	            }
	        }
	        newBoleta.setId(newId);
    	}
    	
        // agrega la Boletas
    	logger.info("agregando boleta " + newBoleta);
        boletas.add(newBoleta);
        return newBoleta;
    }
    public BoletaDTO getBoleta(int id) throws BoletaException{
        if (boletas == null) {
    		logger.severe("Error interno: lista de Boletas no existe.");
    		throw new BoletaException("Error interno: lista de boletas no existe.");    		
    	}
        for (int i = 0; i < boletas.size(); i++) {
            if(boletas.get(i).getId()==id){
                return boletas.get(i);
            }
        }
        throw new BoletaException("Error interno: no existe una boleta con ese id.");
    }
    

    /**
     * Actualiza una Boletas dado su id
     * 
     * @param id de la Boletas a modificar
     * @param newBoletas información para actualizar
     * @return la Boletas actualizada
     * @throws BoletasException si no existe una Boletas con ese id
     */
    public BoletaDTO updateBoleta(int id, BoletaDTO newBoleta) throws BoletaException {
        for (int i = 0; i < boletas.size(); i++) {
            if(id == boletas.get(i).getId()){
                boletas.set(i, newBoleta);
                return boletas.get(i);
            }
        }
        logger.severe("No existe una Boletas con ese id");
        throw new BoletaException("No existe una Boletas con ese id");
    }
    
    
    /**
     * Elimina una Boletas del listado
     * 
     * @param id de la Boletas a eliminar
     * @throws BoletasException si no existe una Boletas con ese id
     */
    public void deleteBoleta(int id) throws BoletaException{
//        logger.info("Antes del ciclo");
        for (int i = 0; i < boletas.size(); i++) {
//            logger.info("antes del if");
            if(boletas.get(i).getId()==id){
//                logger.info("dentro del if");
                boletas.remove(i);
//                logger.info("despues de remover");
                return;
            }
        }
        logger.severe("No existe una Boletas con ese id");
        throw new BoletaException("No existe una Boletas con ese id");
    }

   
}
