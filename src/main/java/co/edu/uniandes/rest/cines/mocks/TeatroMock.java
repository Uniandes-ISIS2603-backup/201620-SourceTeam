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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ba.bohorquez10
 */
public class TeatroMock 
{
    private static ArrayList<TeatroDTO> teatros;
    
    private final static Logger logger = Logger.getLogger(TeatroMock.class.getName());
    
    public TeatroMock()
    {
        if(teatros == null)
        {
            teatros = new ArrayList<>();
            teatros.add( new TeatroDTO("Bogota", "El Embajador") );
            teatros.add( new TeatroDTO("Bogota", "El Greco") );
            teatros.add( new TeatroDTO("Cartagena", "India Catalina") );
            teatros.add( new TeatroDTO("Bogota", "Cinemateca Distrital") );
        }
        
         
        // indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de teatros.");
    	logger.info("Funciones" + teatros );
    }
    
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
    
    public TeatroDTO createTeatro(TeatroDTO nuevo) throws TeatroException
    {
        for(TeatroDTO teatro: teatros)
        {
            if(teatro.getNombre().equals(nuevo.getNombre() ) )
            {
                logger.severe("Ya existe un teatro con ese nombre.");
                throw new TeatroException("Ya existe un teatro con el nombre dado");
            }
        }
        
        logger.info("Se agrego " + nuevo);
        teatros.add(nuevo);
        return nuevo;
    }
    
    public TeatroDTO updateTeatro(String name, TeatroDTO nuevo) throws TeatroException
    {
        for(int i = 0; i < teatros.size(); i++)
        {
            if(teatros.get(i).getNombre().equals(name) )
            {
                logger.info("Actualizando informacion.");
                teatros.set(i, nuevo);
                return nuevo;
            }
        }
        
        logger.severe("No se encontro el teatro con el nombre dado.");
        throw new TeatroException("No se encontro el teatro con el nombre dado.");
    }
    
    public void deleteTeatro(String nombre) throws TeatroException
    {
        for(int i = 0; i < teatros.size(); i++)
        {
            if(teatros.get(i).getNombre().equals(nombre) )
            {
                logger.info("Eliminando teatro.");
                teatros.remove(i);
                return;
            }
        }
        
        logger.severe("No se encontro el teatro con el nombre dado.");
        throw new TeatroException("No se encontro el teatro con el nombre dado.");
    }
    
}
