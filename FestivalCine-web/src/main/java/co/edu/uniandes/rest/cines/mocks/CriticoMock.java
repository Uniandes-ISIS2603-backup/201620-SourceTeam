/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.CriticoDTO;
import co.edu.uniandes.rest.cines.dtos.FestivalDTO;
import co.edu.uniandes.rest.cines.exceptions.CriticoException;
import co.edu.uniandes.rest.cines.exceptions.FestivalException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

        
        if(criticos == null)
        {
            criticos = new ArrayList<>();
            /*
            criticos.add( new CriticoDTO(1L,1, "Juan", 10208, new FestivalDTO(1L, 1, "Cannes Lion Festival", "Pat1" )));
            criticos.add( new CriticoDTO(2L,2, "Pablo", 25445,new FestivalDTO(2L, 1, "TIFF", "Pat2")));
            criticos.add( new CriticoDTO(3L,3, "Pedro", 36546, new FestivalDTO(3L, 1, "Festival de cine de Bogotá", "Pat3"))) ;
            */
        }

        
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
        logger.info("recibiendo solicitud de agregar critico " + newCritico);

        // la nueva ciudad tiene id ?
        if (newCritico.getId() != null) {
            // busca la ciudad con el id suministrado
            for (CriticoDTO critico : criticos) {
                // si existe una ciudad con ese id
                if (Objects.equals(critico.getId(), newCritico.getId())) {
                    logger.severe("Ya existe una ciudad con ese id");
                    throw new CriticoException("Ya existe un critico con ese id");
                };
                if (Objects.equals(critico.getNombre(), newCritico.getNombre())) {
                    logger.severe("Ya existe un critico con ese nombre");
                    throw new CriticoException("Ya existe un critico con ese nombre");
                }

            }

            // la nueva ciudad no tiene id ? 
        } else {
            for (CriticoDTO critico : criticos) {
                // si existe una ciudad con ese id
                
                if (Objects.equals(critico.getNombre(), newCritico.getNombre())) {
                    logger.severe("Ya existe un critico con ese nombre");
                    throw new CriticoException("Ya existe un critico con ese nombre");
                }

            }
            // genera un id para la ciudad
            logger.info("Generando id para el nuevo critico");
            long newId = 1;
            for (CriticoDTO critico : criticos) {
                if (newId <= critico.getId()) {
                    newId = critico.getId() + 1;
                }
            }
            newCritico.setId(newId);
        }

        // agrega la ciudad
        logger.info("agregando ciudad " + newCritico);
        criticos.add(newCritico);
        return newCritico;
    }

   
    /**
     * Retorna un critico dado su nombre
     * 
     * @param credencial del critico a buscar
     * @return critico buscada
     * @throws CriticoException cuando no existe el id buscado
     */
    public CriticoDTO getCritico(long id) throws CriticoException{
        if (criticos == null) {
    		logger.severe("Error interno: lista de criticos no existe.");
    		throw new CriticoException("Error interno: criticos de festivales no existe.");    		
    	}
        for (int i = 0; i < criticos.size(); i++) {
            if(criticos.get(i).getId()== (int)id){
                return criticos.get(i);
            }
        }
        throw new CriticoException("Error interno: no existe un festival con ese nombre.");
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
            if(credencial == criticos.get(i).getId()){
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
     * @param credencial del critico a eliminar
     * @throws CriticoException si no existe un critico con ese nombre
     */
    public CriticoDTO deleteCritico(int id) throws CriticoException{
//        logger.info("Antes del ciclo " + credencial);
        boolean found = false;
        for (int i = 0; i < criticos.size() && !found; i++) {
//            logger.info("antes del if");
            if(criticos.get(i).getId() == id){
//                logger.info("dentro del if");
                CriticoDTO encontrado = criticos.get(i);
                criticos.remove(i);
//                logger.info("despues de remover");
                found = true;
                return encontrado;
            }
        }
        logger.severe("No existe un critico con ese id");
        throw new CriticoException("No existe un critico con ese id" + id);
    }
    
}
