/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;
import co.edu.uniandes.rest.cines.dtos.AbonoDTO;
import co.edu.uniandes.rest.cines.dtos.CalificacionDTO;
import co.edu.uniandes.rest.cines.dtos.CriticoDTO;
import co.edu.uniandes.rest.cines.dtos.FuncionDTO;
//import co.edu.uniandes.rest.cines.exceptions.AbonoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author pa.alvarado10
 */
public class CalificacionMock {
    
 // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(CalificacionMock.class.getName());
	
    // listado de boletas
    private static ArrayList<CalificacionDTO> calificaciones;
    
    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public CalificacionMock() {

    	if (calificaciones == null) {
            FuncionDTO fa = new FuncionDTO();
            fa.setId(1);
             FuncionDTO fb = new FuncionDTO();
            fa.setId(2);
             FuncionDTO fc = new FuncionDTO();
            fa.setId(3);
            CriticoDTO a = new CriticoDTO(1, "a", 1);
            CriticoDTO b = new CriticoDTO(2,"b",2);
           CriticoDTO c = new CriticoDTO(3,"c",3);
            calificaciones = new ArrayList<CalificacionDTO>();
            calificaciones.add(new CalificacionDTO(a, fa));
            calificaciones.add(new CalificacionDTO(b, fb));
            calificaciones.add(new CalificacionDTO(c, fc));
        }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de calificaciones");
    	logger.info("Calificaciones:" + calificaciones);
    }
    
    /**
     * Obtiene el listado de ab.onos
     * @return lista de boletas
     * @throws Exception cuando no existe la lista en memoria
     */
    public List<CalificacionDTO> getCalificaciones() throws Exception {
        if (calificaciones == null) {
            logger.severe("Error interno: lista de calificaciones no existe.");
            throw new Exception("Error interno: lista de calificaciones no existe.");
        }
        
        logger.info("retornando todos los abonos");
        return calificaciones;
    }
    
    /**
     * Agrega un calificacion a la lista.
     * @param newCalificacion calificacion a adicionar
     * @throws Exception cuando ya existe una calificacion con el id suministrado
     * @return calificacion agregada
     */
    public CalificacionDTO createCalificacion(CalificacionDTO newCalificacion) throws Exception {
        logger.info("recibiendo solicitud de agregar calificacion " + newCalificacion);
        
        // la nueva boleta tiene id ?
        if ((Integer) newCalificacion.getIdAbono()!= null ) {
            // busca el abono con el id suministrado
            for (CalificacionDTO abono : abonos) {
                // si existe una ciudad con ese id
                if (Objects.equals(abono.getIdAbono(), newAbono.getIdAbono())){
                    logger.severe("Ya existe abono con ese id");
                    throw new Exception("Ya existe una boleta con ese id");
                }
            }
            
            // la nueva ciudad no tiene id ?
        } else {
            
            // genera un id para la ciudad
            logger.info("Generando id para el nuevo abono");
            int newId = 1;
            for (CalificacionDTO abono : abonos) {
                if (newId <= abono.getIdAbono()){
                    newId =  abono.getIdAbono() + 1;
                }
            }
            newAbono.setId(newId);
        }
        
        // agrega la ciudad
        logger.info("agregando abono " + newAbono);
        abonos.add(newAbono);
        return newAbono;
    }

   
    /**
     * Retorna un abono dado su id
     * 
     * @param id de la abono a buscar
     * @return abono buscado
     * @throws Exception cuando no existe el id buscado
     */
    public CalificacionDTO getAbono(int id) throws Exception{
        if (abonos == null) {
    		logger.severe("Error interno: lista de abonos no existe.");
    		throw new Exception("Error interno: lista de abonos no existe.");    		
    	}
        for (int i = 0; i < abonos.size(); i++) {
            if(abonos.get(i).getIdAbono()==id){
                return abonos.get(i);
            }
        }
        throw new Exception("Error interno: no existe un abono con ese id.");
    }
    
    
    /**
     * Retorna una abono dado su precio
     * 
     * @param precio precio del abono a buscar
     * @return abono buscado
     * @throws Exception cuando no existe el precio buscado
     */
    public AbonoDTO getAbonoPorPrecio(Double precio) throws Exception{
        if (abonos == null) {
    		logger.severe("Error interno: lista de abonos no existe.");
    		throw new Exception("Error interno: lista de abonos no existe.");    		
    	}
        for (int i = 0; i < abonos.size(); i++) {
            if(abonos.get(i).getPrecioAbono()==precio){
                return abonos.get(i);
            }
        }
        throw new Exception("Error interno: no existe abono con ese precio.");
    }
/**
     * Retorna una abono dado su cliente
     * 
     * @param cliente, cliente del abono a buscar
     * @return abono buscado
     * @throws Exception cuando no existe el precio buscado
     */
    public AbonoDTO getAbonoPorCliente(String cliente) throws Exception{
        if (abonos == null) {
    		logger.severe("Error interno: lista de abonos no existe.");
    		throw new Exception("Error interno: lista de abonos no existe.");    		
    	}
        for (int i = 0; i < abonos.size(); i++) {
            if(abonos.get(i).getCliente()==cliente){
                return abonos.get(i);
            }
        }
        throw new Exception("Error interno: no existe abono con ese cliente.");
    }

    /**
     * Actualiza un abono dado su id
     * 
     * @param id del abono a modificar
     * @param newAbono información para actualizar
     * @return la abono actualizada
     * @throws Exception si no existe un abono con ese id
     */
    public AbonoDTO updateAbono(int id, AbonoDTO newAbono) throws Exception {
        for (int i = 0; i < abonos.size(); i++) {
            if(id == abonos.get(i).getIdAbono()){
                abonos.set(i, newAbono);
                return abonos.get(i);
            }
        }
        logger.severe("No existe un abono con ese id");
        throw new Exception("No existe un abono con ese id");
    }
    
    
    /**
     * Elimina un abono del listado
     * 
     * @param id del abono a eliminar
     * @throws Exception si no existe un abono con ese id
     */
    public void deleteAbono(int id) throws Exception{
//        logger.info("Antes del ciclo");
        for (int i = 0; i < abonos.size(); i++) {
//            logger.info("antes del if");
            if(abonos.get(i).getIdAbono()==id){
//                logger.info("dentro del if");
               abonos.remove(i);
//                logger.info("despues de remover");
                return;
            }
        }
        logger.severe("No existe un abono con ese id");
        throw new Exception("No existe un abono con ese id");
    }
  
}

