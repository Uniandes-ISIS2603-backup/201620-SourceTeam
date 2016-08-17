/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.FuncionDTO;
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
            funciones.add( new FuncionDTO(1, 23, 1500, new Date(), true) );
            funciones.add( new FuncionDTO(2, 10, 3400, new Date(), true) );
            funciones.add( new FuncionDTO(3, 14, 8100, new Date(), false) );
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
            logger.severe("Error, la lista de funciones no existe.");
            throw new FuncionException("La lista de funciones no existe.");
        }
        
        logger.info("Retornando la lista de funciones.");
        return funciones;
    }
    
    /**
     * Busca una funcion en especifico con el id dado.
     * @param pId Id de la funcion buscada.
     * @return Funcion con el id dado.
     * @throws FuncionException Si no existe la funcion.
     */
    public FuncionDTO getFuncionById(int pId) throws FuncionException
    {
        if(funciones == null)
        {
            logger.severe("Error, la lista de funciones no existe.");
            throw new FuncionException("La lista de funciones no existe.");
        }
        
        logger.info("Buscando funcion por id. ");
        
        for(FuncionDTO funcion: funciones)
        {
            if(funcion.getId() == pId)
            {
                return funcion;
            }
        }
        
        logger.severe("Error, no existe una funcion con el id dado.");
        throw new FuncionException("No existe una funcion con el id dado.");
    }
    
    public FuncionDTO getFuncionByHour(int pHora) throws FuncionException
    {
        if(funciones == null)
        {
            logger.severe("Error, la lista de funciones no existe.");
            throw new FuncionException("La lista de funciones no existe.");
        }
        
        logger.info("Buscando funcion por hora. ");
        
        for(FuncionDTO funcion: funciones)
        {
            if(funcion.getHora() == pHora)
            {
                return funcion;
            }
        }
        
        logger.severe("Error, no existe una funcion con la hora dada.");
        throw new FuncionException("No existe una funcion con la hora dada.");
    }
    
    /**
     * Crea una nueva funcion.
     * @param nueva la funcion nueva a agregar a la lista de funciones.
     * @return Funcion creada.
     * @throws FuncionException  Si la funcion ya existe o no pudo ser creada.
     */
    public FuncionDTO createFuncion(FuncionDTO nueva) throws FuncionException
    {
        logger.info("Recibiendo solicitud de agregar funcion " + nueva);
        
        for(FuncionDTO funcion: funciones)
        {
            if ( Objects.equals(funcion.getId(), nueva.getId() ) )
            {
                logger.severe("Ya existe una funcion con el id dado.");
                throw new FuncionException("Ya existe una funcion con ese nombre.");
            }
        }
        
        logger.info("Funcion agregada " + nueva);
        funciones.add(nueva);
        return nueva;
    }
    
    /**
     * Actualiza la informacion de una funcion a partir de la informacion dada.
     * @param pId Id de la funcion que se quiere actualizar.
     * @param nueva Funcion con la informacion nueva.
     * @return La funcion actualizada.
     * @throws FuncionException Si la funcion no se encontro.
     */
    public FuncionDTO updateFuncion(int pId, FuncionDTO nueva) throws FuncionException
    {
        logger.info("Buscando la funcion.");
        
        for (int i = 0; i < funciones.size(); i++) 
        {
            if(funciones.get(i).getId() == pId)
            {
                logger.info("Actualizando informacion.");
                funciones.set(i, nueva);
                return nueva;
            }
        }
        
        logger.severe("No existe la funcion con el id dado.");
        throw new FuncionException("No existe la funcion con el id dado.");
    }
    
    /**
     * Borra la funcion con el id dado.
     * @param pId Id de la funcion a borrar.
     * @throws FuncionException Si la funcion con el id dado no se encuentra.
     */
    public void deleteFuncion(int pId) throws FuncionException
    {
        for (int i = 0; i < funciones.size(); i++) 
        {
            if(funciones.get(i).getId() == pId)
            {
                logger.info("Eliminando funcion.");
                funciones.remove(i);
                return;
            }
        }
        
        logger.severe("No existe la funcion con el id dado.");
        throw new FuncionException("No existe la funcion con el id dado.");
    }
}
