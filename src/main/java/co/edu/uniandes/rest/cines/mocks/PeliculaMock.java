/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.PeliculaDTO;
import co.edu.uniandes.rest.cines.dtos.SalaDTO;
import co.edu.uniandes.rest.cines.exceptions.PeliculaException;
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
    
    /**
     * Retorna una pelicula dado su nombre
     * 
     * @param nombre nombre de la pelicula a buscar
     * @return pelicula buscada
     * @throws PeliculaException cuando no existe el nombre buscado
     */
    public PeliculaDTO getPeliculaPorNombre(String nombre) throws  PeliculaException{
        if (peliculas == null) {
    		logger.severe("Error interno: lista de peliculas no existe.");
    		throw new PeliculaException("Error interno: lista de peliculas no existe.");    		
    	}
        for (int i = 0; i < peliculas.size(); i++) {
            if(peliculas.get(i).getNombre().equals(nombre)){
                return peliculas.get(i);
            }
        }
        throw new PeliculaException("Error interno: no existe una pelicula con ese nombre.");
    }
    
    /**
     * Actualiza una pelicula dado su nombre
     * 
     * @param nombre de la pelicula a modificar
     * @param newPelicula información para actualizar
     * @return la pelicula actualizada
     * @throws PeliculaException si no existe una pelicula con ese nombre
     */
    public PeliculaDTO updatePelicula(String nombre, PeliculaDTO newPelicula) throws PeliculaException {
        for (int i = 0; i < peliculas.size(); i++) {
            if(peliculas.get(i).getNombre().equals(nombre)){
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
     * @param nombre de la sala a eliminar
     * @throws PeliculaException si no existe una sala con ese numero
     */
    public void deletePelicula(String nombre) throws PeliculaException{

        for (int i = 0; i < peliculas.size(); i++) {

            if(peliculas.get(i).getNombre().equals(nombre)){

                peliculas.remove(i);

                return;
            }
        }
        logger.severe("No existe una pelicula con ese nombre");
        throw new PeliculaException("No existe una pelicula con ese nombre");
    }

}
