/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.CriticoDTO;
import co.edu.uniandes.rest.cines.exceptions.CriticoException;
import co.edu.uniandes.rest.cines.exceptions.FestivalException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author s.ardila13
 */
public class CriticoMock {
        // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(CriticoMock.class.getName());
	
    // listado de los festivales
    private static ArrayList<CriticoDTO> criticos;
    
    /**
     * Constructor.
     */
    public CriticoMock() {


        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de criticos");
    }
    
    /**
     * Obtiene el listado de los festivales.
     * @return lista de festivales
     * @throws FestivalException cuando no existe la lista en memoria
     */
    public List<CriticoDTO> getCriticos() throws CriticoException {
        if (criticos == null) {
            logger.severe("Error interno: la lista de criticos no existe.");
            throw new CriticoException("Error interno: lista de criticos no existe.");
        }
        
        logger.info("retornando todas los criticos");
        return criticos;
    }
    
    /**
     * Agrega un critico a la lista.
     * @param newCritico ciudad a adicionar
     * @throws CriticoException cuando ya existe un critico con la credencial suministrada
     * @return critico agregado
     */
    public CriticoDTO createCritico(CriticoDTO newCritico) throws CriticoException {
        logger.info("recibiendo solicitud de agregar un critico " + newCritico);
       boolean found = false; 
       for(int i = 0; i < criticos.size()&& !found;i++){
           CriticoDTO actualCritico = criticos.get(i);
           if(actualCritico.getCredencial() == newCritico.getCredencial())
               found = true;
       }
       if(found){
           throw new CriticoException("El critico ya existe");
       }
       else{
           criticos.add(newCritico);
           return newCritico;
       }
    }

   
    /**
     * Retorna un festival dado su nombre
     * 
     * @param nombre del critico a buscar
     * @return critico buscada
     * @throws CriticoException cuando no existe el id buscado
     */
    public CriticoDTO getCritico(int credencial) throws CriticoException{
        if (criticos == null) {
    		logger.severe("Error interno: lista de criticos no existe.");
    		throw new CriticoException("Error interno: criticos de festivales no existe.");    		
    	}
        for (int i = 0; i < criticos.size(); i++) {
            if(criticos.get(i).getCredencial() == credencial){
                return criticos.get(i);
            }
        }
        throw new CriticoException("Error interno: no existe un festival con ese nombre.");
    }
    
    
    /**
     * Retorna un festival dado su duracion
     * 
     * @param duracion del critico a buscar
     * @return critico buscado
     * @throws CriticoException cuando no existe la duracion buscada
     */
    public CriticoDTO getCriticoPorDuracion(int duracion) throws CriticoException{
        if (criticos == null) {
    		logger.severe("Error interno: lista de criticos no existe.");
    		throw new CriticoException("Error interno: lista de criticos no existe.");    		
    	}
        for (int i = 0; i < criticos.size(); i++) {
            if(criticos.get(i).getDuracion()==duracion){
                return criticos.get(i);
            }
        }
        throw new CriticoException("Error interno: no existe un critico con esa duracion.");
    }

    /**
     * Actualiza un critico dada su credencial
     * 
     * @param credencial del critico a modificar
     * @param newCritico información para actualizar
     * @return el critico actualizado
     * @throws CriticoException si no existe un critico con esa credencial
     */
    public CriticoDTO updateCritico(int credencial, CriticoDTO newCritico) throws CriticoException {
        for (int i = 0; i < criticos.size(); i++) {
            if(credencial == criticos.get(i).getCredencial()){
                criticos.set(i, newCritico);
                return criticos.get(i);
            }
        }
        logger.severe("No existe un festival con ese nombre");
        throw new CriticoException("No existe un festival con ese nombre");
    }
    
    
    /**
     * Elimina un critico del listado
     * 
     * @param crendencial del critico a eliminar
     * @throws CriticoException si no existe un critico con ese nombre
     */
    public void deleteCritico(int credencial) throws CriticoException{
//        logger.info("Antes del ciclo");
        boolean found = false;
        for (int i = 0; i < criticos.size() && !found; i++) {
//            logger.info("antes del if");
            if(criticos.get(i).getCredencial() == credencial){
//                logger.info("dentro del if");
                criticos.remove(i);
//                logger.info("despues de remover");
                found = true;
            }
        }
        logger.severe("No existe un critico con ese id");
        throw new CriticoException("No existe un critico con ese id");
    }
    
}
