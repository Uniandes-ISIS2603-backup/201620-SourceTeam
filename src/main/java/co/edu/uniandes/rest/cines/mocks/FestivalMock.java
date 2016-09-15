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
import java.util.Objects;
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

        if(festivales == null)
        {
            festivales = new ArrayList<>();
            festivales.add( new FestivalDTO(1L, 1, "Aventura", "Pat1") );
            festivales.add( new FestivalDTO(2L, 1, "Accion", "Pat2"));
            festivales.add( new FestivalDTO(3L, 1, "Thriller", "Pat3") );
        }
        
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
       logger.info("recibiendo solicitud de agregar ciudad " + newFestival);

        // la nueva ciudad tiene id ?
        if (newFestival.getId() != null) {
            // busca la ciudad con el id suministrado
            for (FestivalDTO city : festivales) {
                // si existe una ciudad con ese id
                if (Objects.equals(city.getId(), newFestival.getId())) {
                    logger.severe("Ya existe una ciudad con ese id");
                    throw new FestivalException("Ya existe una ciudad con ese id");
                };
                if (Objects.equals(city.getNombre(), newFestival.getNombre())) {
                    logger.severe("Ya existe un festival con ese nombre");
                    throw new FestivalException("Ya existe un festival con ese nombre");
                }

            }

            // la nueva ciudad no tiene id ? 
        } else {
            for (FestivalDTO city : festivales) {
                // si existe una ciudad con ese id
                
                if (Objects.equals(city.getNombre(), newFestival.getNombre())) {
                    logger.severe("Ya existe un festival con ese nombre");
                    throw new FestivalException("Ya existe un festival con ese nombre");
                }

            }
            // genera un id para la ciudad
            logger.info("Generando id para la nueva ciudad");
            long newId = 1;
            for (FestivalDTO city : festivales) {
                if (newId <= city.getId()) {
                    newId = city.getId() + 1;
                }
            }
            newFestival.setId(newId);
        }

        // agrega la ciudad
        logger.info("agregando ciudad " + newFestival);
        festivales.add(newFestival);
        return newFestival;
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
     * @param nombre del festival a buscar
     * @return festival buscado
     * @throws FestivalException cuando no existe la duracion buscada
     */
    public FestivalDTO getFestivalPorNombre(Long id) throws FestivalException{
        if (festivales == null) {
    		logger.severe("Error interno: lista de festivales no existe.");
    		throw new FestivalException("Error interno: lista de festivales no existe.");    		
    	}
        for (int i = 0; i < festivales.size(); i++) {
            if(festivales.get(i).getId() == id){
                return festivales.get(i);
            }
        }
        throw new FestivalException("Error interno: no existe un festival con ese nombre.");
    }

    /**
     * Actualiza un festival dado su nombre
     * 
     * @param nombre de la boleta a modificar
     * @param newFestival información para actualizar
     * @return el festival actualizado
     * @throws FestivalException si no existe un festival con ese nombre
     */
    public FestivalDTO updateFestival(Long id, FestivalDTO newFestival) throws FestivalException {
        for (int i = 0; i < festivales.size(); i++) {
            if(id == festivales.get(i).getId()){
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
     * @return Festival eliminado
     * @throws FestivalException si no existe un festival con ese nombre
     */
    public FestivalDTO deleteFestival(Long id) throws FestivalException{
//        logger.info("Antes del ciclo");
        for (int i = 0; i < festivales.size(); i++) {
//            logger.info("antes del if");
            if(festivales.get(i).getId() == id){
//                logger.info("dentro del if");
                FestivalDTO eliminado = festivales.get(i);
                festivales.remove(i);
//                logger.info("despues de remover");
                return eliminado;
            }
        }
        logger.severe("No existe una boleta con ese id");
        throw new FestivalException("No existe una boleta con ese id");
    }
    
}
