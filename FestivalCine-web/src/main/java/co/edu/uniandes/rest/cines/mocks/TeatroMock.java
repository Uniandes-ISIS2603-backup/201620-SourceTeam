/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.TeatroDTO;
import co.edu.uniandes.rest.cines.exceptions.TeatroException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ba.bohorquez10
 */
public class TeatroMock 
{
    /**
     * Lista de teatros.
     */
    private static ArrayList<TeatroDTO> teatros;
    
    /**
     * Objeto para representar los logs de las operaciones.
     */
    private final static Logger logger = Logger.getLogger(TeatroMock.class.getName());
    
    /**
     * Constructor de los datos de ejemplo.
     */
    public TeatroMock()
    {
        if(teatros == null)
        {
            teatros = new ArrayList<>();
            //teatros.add( new TeatroDTO(1L, "Bogota", "Embajador") );
            //teatros.add( new TeatroDTO(2L, "Bogota", "Greco") );
            //teatros.add( new TeatroDTO(3L, "Springfield", "Azteca") );
            //teatros.add( new TeatroDTO(4L, "Bogota", "Cinemateca") );
        }
        
        // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de teatros.");
    	logger.info("Funciones" + teatros );
    }
    
    /**
     * Retorna la lista de teatros.
     * @return Lista de teatros.
     * @throws TeatroException Si no hay ningun teatro.
     */
    public List<TeatroDTO> getTeatros() throws TeatroException
    {
        if(teatros == null)
        {
            logger.severe("No hay teatros.");
            throw new TeatroException("No hay teatros.");
        }
        
        logger.info("Retornando teatros.");
        return teatros;
    }
    
    /**
     * 
     * @param id
     * @return
     * @throws TeatroException 
     */
    public TeatroDTO getTeatro(Long id) throws TeatroException {
        logger.info("Recibiendo solicitud de teatro con id " + id);

        // busca la ciudad con el id suministrado
        for (TeatroDTO teatro : teatros) {
            if (Objects.equals(teatro.getId(), id)) {
                logger.info("retornando teatro " + teatro);
                return teatro;
            }
        }

        // si no encuentra la ciudad
        logger.severe("No existe teatro con ese id");
        throw new TeatroException("No existe teatro con ese id");
    }
    
    /**
     * Crea un nuevo teatro.
     * @param newTeatro Teatro a agregar.
     * @return Teatro creado.
     * @throws TeatroException Si el teatro que se quiere agregar ya existe.
     */
    public TeatroDTO createTeatro(TeatroDTO newTeatro) throws TeatroException
    {
        logger.info("Recibiendo solicitud de agregar teatro " + newTeatro);

        // El nuevo teatro tiene id ?
        if (newTeatro.getId() != null) {
            // Busca el teatro con el id suministrado
            for (TeatroDTO teatro : teatros) {
                // si existe un teatro con ese id.
                if (Objects.equals(teatro.getId(), newTeatro.getId() ) ) {
                    logger.severe("Ya existe un teatro con ese id");
                    throw new TeatroException("Ya existe un teatro con ese id");
                }
                if (Objects.equals(teatro.getNombre(), newTeatro.getNombre() ) ) {
                    logger.severe("Ya existe un teatro con ese nombre");
                    throw new TeatroException("Ya existe un teatro con ese nombre");
                }

            }

            // El nuevo teatro no tiene id ? 
        } else {
            for (TeatroDTO teatro : teatros) {
                // Si existe un teatro con ese id.
                
                if (Objects.equals(teatro.getNombre(), newTeatro.getNombre() ) ) {
                    logger.severe("Ya existe un teatro con ese nombre");
                    throw new TeatroException("Ya existe un teatro con ese nombre");
                }

            }
            // Genera un id para el teatro.
            logger.info("Generando id para el nuevo teatro");
            long newId = 1;
            for (TeatroDTO teatro : teatros) {
                if (newId <= teatro.getId()) {
                    newId = teatro.getId() + 1;
                }
            }
            newTeatro.setId(newId);
        }

        // agrega la ciudad
        logger.info("Agregando teatro " + newTeatro);
        teatros.add(newTeatro);
        return newTeatro;
    }
    
    /**
     * Modifica un teatro existente.
     * @param id Id del teatro a modificar.
     * @param updatedTeatro Teatro con la informacion modificada.
     * @return El teatro modificado.
     * @throws TeatroException Si no se encuentra el teatro con el nombre dado por parametro.
     */
    public TeatroDTO updateTeatro(Long id, TeatroDTO updatedTeatro) throws TeatroException
    {
        logger.info("Recibiendo solictud de modificar teatro " + updatedTeatro);

        // Busca el teatro con el id suministrado.
        for (TeatroDTO teatro : teatros) {
            if (Objects.equals(teatro.getId(), id) ) {

                // Modifica el teatro.
                //teatro.setId(updatedTeatro.getId() );
                teatro.setCiudad(updatedTeatro.getCiudad() );
                teatro.setNombre(updatedTeatro.getNombre() );

                // Retorna el teatro modificado.
                logger.info("Modificando teatro " + teatro);
                return teatro;
            }
        }

        // No encontró el teatro con ese id ?
        logger.severe("No existe un teatro con ese id");
        throw new TeatroException("No existe un teatro con ese id");
    }
    
    /**
     * Elimina el teatro con el nombre dado por parametro.
     * @param id Nombre del teatro que se quiere eliminar.
     * @throws TeatroException Si no se encuentra el teatro con el nombre dado por parametro.
     */
    public TeatroDTO deleteTeatro(Long id) throws TeatroException
    {
        logger.info("Recibiendo solictud de eliminar teatro con id " + id);

        // Busca el teatro con el id suministrado.
        for (TeatroDTO teatro : teatros) {
            if (Objects.equals(teatro.getId(), id) ) {

                // Eimina el teatro.
                logger.info("Eliminando teatro " + teatro);
                teatros.remove(teatro);
                return teatro;
            }
        }

        // No encontró el teatro con ese id ?
        logger.severe("No existe un teatro con ese id");
        throw new TeatroException("No existe un teatro con ese id");
    }
    
}
