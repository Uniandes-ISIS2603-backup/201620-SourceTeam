/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;

import co.edu.uniandes.rest.cines.dtos.BoletaDTO;
import co.edu.uniandes.rest.cines.exceptions.BoletaException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ca.nieto11
 */
public class BoletaMock {
    // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(BoletaMock.class.getName());
	
    // listado de boletas
    private static ArrayList<BoletaDTO> boletas;
    
    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public BoletaMock() {

    	if (boletas == null) {
            boletas = new ArrayList<>();
            boletas.add(new BoletaDTO(1, 2000));
            boletas.add(new BoletaDTO(2, 3000));
            boletas.add(new BoletaDTO(3, 5000));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de boletas");
    	logger.info("Boletas:" + boletas );
    }
    
    /**
     * Obtiene el listado de boletas.
     * @return lista de boletas
     * @throws BoletaException cuando no existe la lista en memoria
     */
    public List<BoletaDTO> getBoletas() throws BoletaException {
        if (boletas == null) {
            logger.severe("Error interno: lista de boletas no existe.");
            throw new BoletaException("Error interno: lista de boletas no existe.");
        }
        
        logger.info("retornando todas las boletas");
        return boletas;
    }
    
    /**
     * Agrega una boleta a la lista.
     * @param newBoleta ciudad a adicionar
     * @throws BoletaException cuando ya existe una boleta con el id suministrado
     * @return boleta agregada
     */
    public BoletaDTO createBoleta(BoletaDTO newBoleta) throws BoletaException {
        logger.info("recibiendo solicitud de agregar boleta " + newBoleta);
        
        // la nueva boleta tiene id ?
        if ((Integer) newBoleta.getId() != null ) {
            // busca la boleta con el id suministrado
            for (BoletaDTO boleta : boletas) {
                // si existe una ciudad con ese id
                if (Objects.equals(boleta.getId(), newBoleta.getId())){
                    logger.severe("Ya existe una boleta con ese id");
                    throw new BoletaException("Ya existe una boleta con ese id");
                }
            }
            
            // la nueva ciudad no tiene id ?
        } else {
            
            // genera un id para la ciudad
            logger.info("Generando id para la nueva boleta");
            int newId = 1;
            for (BoletaDTO boleta : boletas) {
                if (newId <= boleta.getId()){
                    newId =  boleta.getId() + 1;
                }
            }
            newBoleta.setId(newId);
        }
        
        // agrega la ciudad
        logger.info("agregando bolera " + newBoleta);
        boletas.add(newBoleta);
        return newBoleta;
    }

   
    /**
     * Retorna una boleta dado su id
     * 
     * @param id de la boleta a buscar
     * @return boleta buscada
     * @throws BoletaException cuando no existe el id buscado
     */
    public BoletaDTO getBoleta(int id) throws BoletaException{
        if (boletas == null) {
    		logger.severe("Error interno: lista de ciudades no existe.");
    		throw new BoletaException("Error interno: lista de ciudades no existe.");    		
    	}
        for (int i = 0; i < boletas.size(); i++) {
            if(boletas.get(i).getId()==id){
                return boletas.get(i);
            }
        }
        throw new BoletaException("Error interno: no existe una ciudad con ese id.");
    }
    
    
    /**
     * Retorna una boleta dado su precio
     * 
     * @param precio nombre de la ciudad a buscar
     * @return boleta buscada
     * @throws BoletaException cuando no existe el precio buscado
     */
    public BoletaDTO getBoletaPorPrecio(Double precio) throws BoletaException{
        if (boletas == null) {
    		logger.severe("Error interno: lista de boletas no existe.");
    		throw new BoletaException("Error interno: lista de boletas no existe.");    		
    	}
        for (int i = 0; i < boletas.size(); i++) {
            if(boletas.get(i).getPrecio()==precio){
                return boletas.get(i);
            }
        }
        throw new BoletaException("Error interno: no existe una ciudad con ese nombre.");
    }

    /**
     * Actualiza una boleta dado su id
     * 
     * @param id de la boleta a modificar
     * @param newBoleta información para actualizar
     * @return la boleta actualizada
     * @throws BoletaException si no existe una ciudad con ese id
     */
    public BoletaDTO updateBoleta(int id, BoletaDTO newBoleta) throws BoletaException {
        for (int i = 0; i < boletas.size(); i++) {
            if(id == boletas.get(i).getId()){
                boletas.set(i, newBoleta);
                return boletas.get(i);
            }
        }
        logger.severe("No existe una boleta con ese id");
        throw new BoletaException("No existe una boleta con ese id");
    }
    
    
    /**
     * Elimina una boleta del listado
     * 
     * @param id de la boleta a eliminar
     * @throws BoletaException si no existe una boleta con ese id
     */
    public void deleteBoleta(int id) throws BoletaException{
//        logger.info("Antes del ciclo");
        for (int i = 0; i < boletas.size(); i++) {
//            logger.info("antes del if");
            if(boletas.get(i).getId()==id){
//                logger.info("dentro del if");
                boletas.remove(i);
//                logger.info("despues de remover");
                return;
            }
        }
        logger.severe("No existe una boleta con ese id");
        throw new BoletaException("No existe una boleta con ese id");
    }
}
