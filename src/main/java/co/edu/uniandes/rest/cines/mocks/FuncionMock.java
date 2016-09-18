/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.CriticoDTO;
import co.edu.uniandes.rest.cines.dtos.FestivalDTO;
import co.edu.uniandes.rest.cines.dtos.FuncionDTO;
import co.edu.uniandes.rest.cines.dtos.PeliculaDTO;
import co.edu.uniandes.rest.cines.exceptions.FuncionException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ba.bohorquez10
 */
public class FuncionMock 
{
    /**
     * Objeto para presentar los logs de las operaciones.
     */
    private final static Logger logger = Logger.getLogger(FuncionMock.class.getName());
    
    /**
     * Lista de funciones.
     */
    private static ArrayList<FuncionDTO> funciones;
    
    /**
     * Constructor que crea los datos de ejemplo.
     */
    public FuncionMock()
    {
        if(funciones == null)
        {
            funciones = new ArrayList<>();
            funciones.add( new FuncionDTO(1L, 1500, new Date(), new PeliculaDTO("superman", "heroe", "nn" , 180, "estados unidos") ) );
            funciones.add( new FuncionDTO(2L, 3400, new Date(), new PeliculaDTO("batman", "heroe", "nn", 180, "estados unidos") ) );
            funciones.add( new FuncionDTO(3L, 8100, new Date(), new PeliculaDTO("tarzan", "heroe", "nn", 180, "estados unidos") ) );
        }
        
        // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de funciones.");
    	logger.info("Funciones" + funciones );
    }
    
    /**
     * Retorna la lista de funciones.
     * @return Lista de funciones.
     * @throws FuncionException Si no hay funciones.
     */
    public List<FuncionDTO> getFunciones() throws FuncionException
    {
        if(funciones == null)
        {
            logger.severe("No hay funciones.");
            throw new FuncionException("No hay funciones.");
        }
        
        logger.info("Retornando funciones.");
        return funciones;
    }
    
    /**
     * Busca una funcion en especifico con el id dado.
     * @param id Id de la funcion buscada.
     * @return Funcion con el id dado.
     * @throws FuncionException Si no existe la funcion.
     */
    public FuncionDTO getFuncion(Long id) throws FuncionException
    {
        logger.info("Recibiendo solicitud de teatro con id " + id);
        
        // busca la funcion con el id suministrado
        for (FuncionDTO funcion : funciones) {
            if (Objects.equals(funcion.getId(), id) ) {
                logger.info("retornando funcion" + funcion);
                return funcion;
            }
        }

        // si no encuentra la funcion
        logger.severe("No existe funcnion con ese id");
        throw new FuncionException("No existe funcion con ese id");
    }
    
    /**
     * Crea una nueva funcion.
     * @param newFuncion la funcion nueva a agregar a la lista de funciones.
     * @return Funcion creada.
     * @throws FuncionException  Si la funcion ya existe o no pudo ser creada.
     */
    public FuncionDTO createFuncion(FuncionDTO newFuncion) throws FuncionException
    {
        logger.info("Recibiendo solicitud de agregar funcion " + newFuncion);

        // La nueva funcion tiene id ?
        if (newFuncion.getId() != null) {
            // Busca la funcion con el id suministrado
            for (FuncionDTO funcion : funciones) {
                // si existe una funcion con ese id.
                if (Objects.equals(funcion.getId(), newFuncion.getId() ) ) {
                    logger.severe("Ya existe una funcion con ese id");
                    throw new FuncionException("Ya existe una funcion con ese id");
                }
            }

            // La nueva funcion no tiene id ? 
        } else {
            
            // Genera un id para la funcion.
            logger.info("Generando id para el nuevo teatro");
            long newId = 1;
            for (FuncionDTO funcion : funciones) {
                if (newId <= funcion.getId() ) {
                    newId = funcion.getId() + 1;
                }
            }
            
            newFuncion.setId(newId);
        }

        // Agrega la funcion
        logger.info("Agregando funcion " + newFuncion);
        funciones.add(newFuncion);
        return newFuncion;
    }
    
    /**
     * Actualiza la informacion de una funcion a partir de la informacion dada.
     * @param id Id de la funcion que se quiere actualizar.
     * @param updatedFuncion Funcion con la informacion nueva.
     * @return La funcion actualizada.
     * @throws FuncionException Si la funcion no se encontro.
     */
    public FuncionDTO updateFuncion(Long id, FuncionDTO updatedFuncion) throws FuncionException
    {
        logger.info("Recibiendo solictud de modificar funcion " + updatedFuncion);

        // Busca la funcion con el id suministrado.
        for (FuncionDTO funcion : funciones) {
            if (Objects.equals(funcion.getId(), id) ) {

                // Modifica la funcion.
                
                funcion.setDia(updatedFuncion.getDia() );
                funcion.setPrecio(updatedFuncion.getPrecio() );

                // Retorna la funcion modificada.
                logger.info("Modificando funcion " + funcion);
                return funcion;
            }
        }

        // No encontró la funcion con ese id ?
        logger.severe("No existe un teatro con ese id");
        throw new FuncionException("No existe un teatro con ese id");
    }
    
    /**
     * Borra la funcion con el id dado.
     * @param id Id de la funcion a borrar.
     * @return 
     * @throws FuncionException Si la funcion con el id dado no se encuentra.
     */
    public FuncionDTO deleteFuncion(Long id) throws FuncionException
    {
        logger.info("Recibiendo solictud de eliminar funcion con id " + id);

        // Busca la funcion con el id suministrado.
        for (FuncionDTO funcion : funciones) {
            if (Objects.equals(funcion.getId(), id) ) {

                // Eimina la funcion.
                logger.info("Eliminando funcion" + funcion);
                funciones.remove(funcion);
                return funcion;
            }
        }

        // No encontró el teatro con ese id ?
        logger.severe("No existe una funcion con ese id");
        throw new FuncionException("No existe una funcion con ese id");
    }
}
