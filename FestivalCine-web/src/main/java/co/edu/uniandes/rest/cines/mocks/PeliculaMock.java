/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.PeliculaDTO;
import co.edu.uniandes.rest.cines.dtos.SillaDTO;
import co.edu.uniandes.rest.cines.exceptions.PeliculaException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ya.bejarano10
 */
public class PeliculaMock 
{
    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(PeliculaMock.class.getName());
	
    // listado de peliculas
    private static ArrayList<PeliculaDTO> peliculas;

    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public PeliculaMock() {

    	if (peliculas == null) {
           
                    peliculas = new ArrayList<>();
                    //createPelicula(new PeliculaDTO(0L, "superman", "heroe", "nn" , 180, "estados unidos"));
                    //createPelicula(new PeliculaDTO(0L, "batman", "heroe", "nn", 180, "estados unidos"));
                    //createPelicula(new PeliculaDTO(0L, "tarzan", "heroe", "nn", 180, "estados unidos"));
                
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de peliculas");
    	logger.info("peliculas" + peliculas );
    }    
    
	/**
	 * Obtiene el listado de peliculas. 
	 * @return lista de peliculas
	 * @throws PeliculaException cuando no existe la lista en memoria  
	 */    
    public List<PeliculaDTO> getPeliculas() throws PeliculaException {
    	if (peliculas == null) {
    		logger.severe("Error interno: lista de peliculas no existe.");
    		throw new PeliculaException("Error interno: lista de peliculas no existe.");    		
    	}
    	
    	logger.info("retornando todas las peliculas");
    	return peliculas;
    }

 

    /**
     * Agrega una pelicula a la lista.
     * @param newPelicula sala a adicionar
     * @throws PeliculaException cuando ya existe una Pelicula con el nombre suministrado
     * @return sala agregada
     */
    public PeliculaDTO createPelicula(PeliculaDTO newPelicula) throws PeliculaException {
    	logger.info("recibiendo solicitud de agregar pelicula " + newPelicula);
    	
    	// la nueva pelicula tiene nombre ?
    	if ( newPelicula.getId() != null && newPelicula.getId() != 0) {
	    	// busca la pelicula con el nombre suministrado
	        for (PeliculaDTO pelicula : peliculas) {
	        	// si existe una pelicula con ese nombre
	            if (Objects.equals(pelicula.getNombre(), newPelicula.getNombre())){
	            	logger.severe("Ya existe una pelicula con ese nombre");
	                throw new PeliculaException("Ya existe una pelicula con ese nombre");
	            }
	        }
	        
	    // la nueva pelicula no tiene id ? 
    	} else {

    		logger.info("Generando id paa la nueva pelicula");
    		long newId = 1;
	        for (PeliculaDTO peli : peliculas) {
	            if (newId <= peli.getId()){
	                newId =  peli.getId() + 1;
	            }
	        }
	        newPelicula.setId(newId);
    	}
    	
        // agrega la sala
    	logger.info("agregando pelicula " + newPelicula);
        peliculas.add(newPelicula);
        return newPelicula;
    }
    
    /**
     * Retorna una pelicula dado su nombre
     * 
     * @param idd nombre de la pelicula a buscar
     * @return pelicula buscada
     * @throws PeliculaException cuando no existe el nombre buscado
     */
    public PeliculaDTO getPeliculaPorId(Long idd) throws  PeliculaException{
        if (peliculas == null) {
    		logger.severe("Error interno: lista de peliculas no existe.");
    		throw new PeliculaException("Error interno: lista de peliculas no existe.");    		
    	}
        for (int i = 0; i < peliculas.size(); i++) {
            if(peliculas.get(i).getId().equals(idd)){
                return peliculas.get(i);
            }
        }
        throw new PeliculaException("Error interno: no existe una pelicula con ese nombre.");
    }
    
    /**
     * Actualiza una pelicula dado su nombre
     * 
     * @param id de la pelicula a modificar
     * @param newPelicula información para actualizar
     * @return la pelicula actualizada
     * @throws PeliculaException si no existe una pelicula con ese nombre
     */
    public PeliculaDTO updatePelicula(Long id, PeliculaDTO newPelicula) throws PeliculaException {
        for (int i = 0; i < peliculas.size(); i++) {
            if(peliculas.get(i).getId().equals(id)){
                peliculas.set(i, newPelicula);
                return peliculas.get(i);
            }
        }
        logger.severe("No existe una pelicula con ese numero");
        throw new PeliculaException("No existe una pelicula con ese nombre");
    }
    
    /**
     * Elimina una pelicula del listado
     * 
     * @param id de la sala a eliminar
     * @throws PeliculaException si no existe una sala con ese numero
     */
    public void deletePelicula(Long id) throws PeliculaException{

        for (int i = 0; i < peliculas.size(); i++) {

            if(peliculas.get(i).getId().equals(id)){

                peliculas.remove(i);

                return;
            }
        }
        logger.severe("No existe una pelicula con ese ide");
        throw new PeliculaException("No existe una pelicula con ese id");
    }

}
