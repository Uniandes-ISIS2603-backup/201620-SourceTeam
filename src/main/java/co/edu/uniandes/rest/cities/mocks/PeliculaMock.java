/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.mocks;

import co.edu.uniandes.rest.cities.dtos.PeliculaDTO;
import co.edu.uniandes.rest.cities.exceptions.PeliculaException;
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
            peliculas.add(new PeliculaDTO("superman", "", "","" , 180, ""));
            peliculas.add(new PeliculaDTO("batman", "", "", "", 180, ""));
            peliculas.add(new PeliculaDTO("tarzan", "", "", "", 180, ""));
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
    	
    	logger.info("retornando todas las salas");
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
    	if ( newPelicula.getNombre() != null ) {
	    	// busca la pelicula con el nombre suministrado
	        for (PeliculaDTO pelicula : peliculas) {
	        	// si existe una pelicula con ese nombre
	            if (Objects.equals(pelicula.getNombre(), newPelicula.getNombre())){
	            	logger.severe("Ya existe una pelicula con ese nombre");
	                throw new PeliculaException("Ya existe una pelicula con ese nombre");
	            }
	        }
	        
	    // la nueva pelicula no tiene nombre ? 
    	} else {

    		throw new PeliculaException("la nueva pelicula necesita un nombre");
    	}
    	
        // agrega la sala
    	logger.info("agregando pelicula " + newPelicula);
        peliculas.add(newPelicula);
        return newPelicula;
    }

}
