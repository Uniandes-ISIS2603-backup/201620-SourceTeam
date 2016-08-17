/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.FestivalDTO;
import co.edu.uniandes.rest.cines.exceptions.FestivalException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s.ardila13
 */
public class FestivalMock {
        // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(FestivalMock.class.getName());
	
    // listado de los festivales
    private static ArrayList<FestivalDTO> festivales;
    
    /**
     * Constructor.
     */
    public FestivalMock() {


        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de festivales");
    }
    
    /**
     * Obtiene el listado de los festivales.
     * @return lista de festivales
     * @throws FestivalException cuando no existe la lista en memoria
     */
    public List<FestivalDTO> getFestivales() throws FestivalException {
        if (festivales == null) {
            logger.severe("Error interno: lista de festivales no existe.");
            throw new FestivalException("Error interno: lista de festivales no existe.");
        }
        
        logger.info("retornando todas los festivales");
        return festivales;
    }
    
    /**
     * Agrega un festival a la lista.
     * @param newFestival ciudad a adicionar
     * @throws FestivalException cuando ya existe un festival con el id suministrado
     * @return festival agregado
     */
    public FestivalDTO createFestival(FestivalDTO newFestival) throws FestivalException {
        logger.info("recibiendo solicitud de agregar un festival " + newFestival);
       boolean found = false; 
       for(int i = 0; i < festivales.size()&& !found;i++){
           FestivalDTO actualFestival = festivales.get(i);
           if(actualFestival.getNombre().equalsIgnoreCase(newFestival.getNombre()))
               found = true;
       }
       if(found){
           throw new FestivalException("El festival ya existe");
       }
       else{
           festivales.add(newFestival);
           return newFestival;
       }
    }

   
    /**
     * Retorna un festival dado su nombre
     * 
     * @param nombre del festival a buscar
     * @return festival buscado
     * @throws FestivalException cuando no existe el id buscado
     */
    public FestivalDTO getFestival(String nombre) throws FestivalException{
        if (festivales == null) {
    		logger.severe("Error interno: lista de festivales no existe.");
    		throw new FestivalException("Error interno: lista de festivales no existe.");    		
    	}
        for (int i = 0; i < festivales.size(); i++) {
            if(festivales.get(i).getNombre().equalsIgnoreCase(nombre)){
                return festivales.get(i);
            }
        }
        throw new FestivalException("Error interno: no existe un festival con ese nombre.");
    }
    
    
    /**
     * Retorna un festival dado su duracion
     * 
     * @param duracion del festival a buscar
     * @return festival buscado
     * @throws FestivalException cuando no existe la duracion buscada
     */
    public FestivalDTO getFestivalPorDuracion(int duracion) throws FestivalException{
        if (festivales == null) {
    		logger.severe("Error interno: lista de festivales no existe.");
    		throw new FestivalException("Error interno: lista de festivales no existe.");    		
    	}
        for (int i = 0; i < festivales.size(); i++) {
            if(festivales.get(i).getDuracion()==duracion){
                return festivales.get(i);
            }
        }
        throw new FestivalException("Error interno: no existe un festival con esa duracion.");
    }

    /**
     * Actualiza un festival dado su nombre
     * 
     * @param nombre de la boleta a modificar
     * @param newFestival información para actualizar
     * @return el festival actualizado
     * @throws FestivalException si no existe un festival con ese nombre
     */
    public FestivalDTO updateFestival(String nombre, FestivalDTO newFestival) throws FestivalException {
        for (int i = 0; i < festivales.size(); i++) {
            if(nombre.equalsIgnoreCase(festivales.get(i).getNombre())){
                festivales.set(i, newFestival);
                return festivales.get(i);
            }
        }
        logger.severe("No existe un festival con ese nombre");
        throw new FestivalException("No existe un festival con ese nombre");
    }
    
    
    /**
     * Elimina un festival del listado
     * 
     * @param nombre del festival a eliminar
     * @throws FestivalException si no existe un festival con ese nombre
     */
    public void deleteFestival(String nombre) throws FestivalException{
//        logger.info("Antes del ciclo");
        for (int i = 0; i < festivales.size(); i++) {
//            logger.info("antes del if");
            if(festivales.get(i).getNombre().equalsIgnoreCase(nombre)){
//                logger.info("dentro del if");
                festivales.remove(i);
//                logger.info("despues de remover");
                return;
            }
        }
        logger.severe("No existe una boleta con ese id");
        throw new FestivalException("No existe una boleta con ese id");
    }
    
}
