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
    private final static Logger logger = Logger.getLogger(ClienteMock.class.getName());
    
    private static ArrayList<FuncionDTO> funciones;
    
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
