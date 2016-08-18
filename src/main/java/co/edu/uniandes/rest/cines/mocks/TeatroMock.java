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
            teatros.add( new TeatroDTO("Bogota", "Embajador") );
            teatros.add( new TeatroDTO("Bogota", "Greco") );
            teatros.add( new TeatroDTO("Springfield", "Azteca") );
            teatros.add( new TeatroDTO("Bogota", "Cinemateca") );
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
     * Busca un teatro por nombre.
     * @param nombre Nombre del teatro a buscar.
     * @return Teatro con el nombre dado por parametro.
     * @throws TeatroException Si no se encuantra el teatro con el nombre dado por parametro.
     */
    public TeatroDTO getTeatroByName(String nombre) throws TeatroException
    {
        logger.info("Solicitud de buscar teatro.");
        for(TeatroDTO teatro: teatros)
        {
            if(teatro.getNombre().equals(nombre) )
            {
                return teatro;
            }
        }
        
        logger.severe("No se encontro el teatro con el nombre dado.");
        throw new TeatroException("No se encontro el teatro con el nombre dado.");
        
    }
    
    /**
     * Crea un nuevo teatro.
     * @param nuevo Teatro a agregar.
     * @return Teatro creado.
     * @throws TeatroException Si el teatro que se quiere agregar ya existe.
     */
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
    
    /**
     * Modifica un teatro existente.
     * @param name Nombre del teatro a modificar.
     * @param nuevo Teatro con la informacion modificada.
     * @return El teatro modificado.
     * @throws TeatroException Si no se encuentra el teatro con el nombre dado por parametro.
     */
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
    
    /**
     * Elimina el teatro con el nombre dado por parametro.
     * @param nombre Nombre del teatro que se quiere eliminar.
     * @throws TeatroException Si no se encuentra el teatro con el nombre dado por parametro.
     */
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
