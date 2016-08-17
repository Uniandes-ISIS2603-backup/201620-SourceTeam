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
     * Obtiene el listado de calificaciones
     * @return lista de calificaciones
     * @throws Exception cuando no existe la lista en memoria
     */
    public List<CalificacionDTO> getCalificaciones() throws Exception {
        if (calificaciones == null) {
            logger.severe("Error interno: lista de calificaciones no existe.");
            throw new Exception("Error interno: lista de calificaciones no existe.");
        }
        
        logger.info("retornando todos las calificaciones");
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
        if ((Double) newCalificacion.getId()!=null ) {
            // busca el abono con el id suministrado
            for (CalificacionDTO calificacion : calificaciones) {
                // si existe una ciudad con ese id
                if ((Objects.equals(calificacion.getId(), newCalificacion.getId()))){
                    logger.severe("Ya existe calificacion con el mismo id dado");
                    throw new Exception("Ya existe calificacion con los parametros dados");
                }
            }
            
            // la nueva ciudad no tiene id ?
        } else {
            
            // genera un id para la ciudad
            logger.info("Generando id para el nuevo abono");
            double newId = 1;
            for (CalificacionDTO calificacion : calificaciones) {
                if (newId <= calificacion.getId()){
                    newId =  calificacion.getId() + 1;
                }
            }
            newCalificacion.setId(newId);
        }
        
        // agrega la ciudad
        logger.info("agregando calificacion " + newCalificacion);
        calificaciones.add(newCalificacion);
        return newCalificacion;
    }

   
    /**
     * Retorna una calificacion dado su id
     * 
     * @param id de la calificacion a buscar
     * @return calificacion buscado
     * @throws Exception cuando no existe el id buscado
     */
    public CalificacionDTO getCalificacion(double id) throws Exception{
        if (calificaciones == null) {
    		logger.severe("Error interno: lista de calificaciones no existe.");
    		throw new Exception("Error interno: lista de calificaciones no existe.");    		
    	}
        for (int i = 0; i < calificaciones.size(); i++) {
            if(calificaciones.get(i).getId()==id){
                return calificaciones.get(i);
            }
        }
        throw new Exception("Error interno: no existe una calificacion con ese id.");
    }
    
    
    /**
     * Retorna una lista de calificaciones dado su critico
     * 
     * @param critico, critico a buscar
     * @return criticos , lista calificaciones del critico
     * @throws Exception cuando no existe el critico buscado
     */
    public ArrayList<CalificacionDTO> getCalificacionPorCritico(CriticoDTO critico) throws Exception{
        
        if (calificaciones == null) {
    		logger.severe("Error interno: lista de calificaciones no existe.");
    		throw new Exception("Error interno: lista de calificaciones no existe.");    		
    	}
        ArrayList<CalificacionDTO> criticos = new ArrayList<CalificacionDTO>();
        for (int i = 0; i < calificaciones.size(); i++) {
            if(Objects.equals(calificaciones.get(i).getCritico(), critico)){
                criticos.add(calificaciones.get(i));
            }
        }
        if(criticos.size()==0){
        throw new Exception("Error interno: no existe abono con ese precio.");
        }
        else {
            return criticos;
        }
    }
/**
     * Retorna una abono dado su funcion
     * 
     * @param funcion, funcion a buscar
     * @return lista calificaciones de la funcion buscada
     * @throws Exception cuando no existe la funcion buscada
     */
    public ArrayList<CalificacionDTO>  getCalificacionPorFuncion(FuncionDTO funcion) throws Exception{
        if (calificaciones == null) {
    		logger.severe("Error interno: lista de calificaciones no existe.");
    		throw new Exception("Error interno: lista de calificaciones no existe.");    		
    	}
        ArrayList<CalificacionDTO> funciones = new ArrayList<>();
        for (int i = 0; i < calificaciones.size(); i++) {
            if(calificaciones.get(i).getFuncion()==funcion){
                funciones.add(calificaciones.get(i));
            }
        }
        if(funciones.size()==0){
        throw new Exception("Error interno: no existe calificacion con esa funcion.");
    }
        return funciones;
    }
    
        /**
     * Actualiza un abono dado su id
     * 
     * @param id del abono a modificar
     * @param newAbono información para actualizar
     * @return la abono actualizada
     * @throws Exception si no existe un abono con ese id
     */
    public CalificacionDTO updateCalificacion(double id, CalificacionDTO newcali) throws Exception {
        for (int i = 0; i < calificaciones.size(); i++) {
            if(id == calificaciones.get(i).getId()){
                calificaciones.set(i, newcali);
                return calificaciones.get(i);
            }
        }
        logger.severe("No existe un abono con ese id");
        throw new Exception("No existe un abono con ese id");
    }
    
    /**
     * Elimina una calificacion del listado
     * 
     * @param id de la calificacion a eliminar
     * @throws Exception si no existe una calificacion con ese id
     */
    public void deleteCalificacion(double id) throws Exception{
//        logger.info("Antes del ciclo");
        for (int i = 0; i < calificaciones.size(); i++) {
//            logger.info("antes del if");
            if(calificaciones.get(i).getId()==id){
//                logger.info("dentro del if");
               calificaciones.remove(i);
//                logger.info("despues de remover");
                return;
            }
        }
        logger.severe("No existe una calificacion con ese id");
        throw new Exception("No existe un calificacion con ese id");
    }
  
}

