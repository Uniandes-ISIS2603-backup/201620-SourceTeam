/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mocks;
import co.edu.uniandes.rest.cines.dtos.AbonoDTO;
import co.edu.uniandes.rest.cines.dtos.ClienteDTO;
import co.edu.uniandes.rest.cines.exceptions.AbonoException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author pa.alvarado10
 */
public class AbonoMock {
   // objeto para presentar logs de las operaciones
    private final static Logger logger = Logger.getLogger(AbonoMock.class.getName());
	
    // listado de boletas
    private static ArrayList<AbonoDTO> abonos;
    
    /**
     * Constructor. Crea los datos de ejemplo.
     */
    public AbonoMock() {

 if (abonos == null){
        abonos= new ArrayList<AbonoDTO>();
        ClienteDTO clienteA = new ClienteDTO("juan", true);
        ClienteDTO clienteB = new ClienteDTO("Mariana", true);
        ClienteDTO clienteC = new ClienteDTO("Pacho", true);
            abonos = new ArrayList<>();
            abonos.add(new AbonoDTO(clienteA, 2000));
            abonos.add(new AbonoDTO(clienteB, 3000));
            abonos.add(new AbonoDTO(clienteC, 5000));
 }
        
    	// indica que se muestren todos los mensajes
    	logger.setLevel(Level.INFO);
    	
    	// muestra información 
    	logger.info("Inicializa la lista de abonos");
    	logger.info("abonos:" + abonos);
    }
    
    /**
     * Obtiene el listado de ab.onos
     * @return lista de boletas
     * @throws co.edu.uniandes.rest.cines.exceptions.AbonoException
     */
    public List<AbonoDTO> getAbonos() throws AbonoException {
        if (abonos == null) {
            logger.severe("Error interno: lista de abonos no existe.");
            throw new AbonoException("Error interno: lista de abonos no existe.");
        }
        
        logger.info("retornando todos los abonos");
        return abonos;
    }
    
        /**
     * Retorna un abono dado su id
     * 
     * @param id de la abono a buscar
     * @return abono buscado
     * @throws Exception cuando no existe el id buscado
     */
    public AbonoDTO getAbono(int id) throws AbonoException{
        if (abonos == null) {
    		logger.severe("Error interno: lista de abonos no existe.");
    		throw new AbonoException("Error interno: lista de abonos no existe.");    		
    	}
        for (int i = 0; i < abonos.size(); i++) {
            if(abonos.get(i).getId()==id){
                return abonos.get(i);
            }
        }
        throw new AbonoException("Error interno: no existe un abono con ese id.");
    }
    
    
    /**
     * Agrega un abono a la lista.
     * @param newAbono abono a adicionar
     * @throws Exception cuando ya existe una boleta con el id suministrado
     * @return abono agregada
     */
    public AbonoDTO createAbono(AbonoDTO newAbono) throws AbonoException {
        logger.info("recibiendo solicitud de agregar abono"+newAbono);
        
        // el nuevo abono tiene id?
        if ((Integer) newAbono.getId()!= null ) {
            // busca el abono con el id suministrado
            for (AbonoDTO abono : abonos) {
                // si existe una ciudad con ese id
                if (Objects.equals(abono.getId(), newAbono.getId())){
                    logger.severe("Ya existe abono con ese id");
                    throw new AbonoException("Ya existe un abono con ese id");
                }
            }
            
    
        } else {

            // genera un id para la ciudad
            logger.info("Generando id para el nuevo abono"+newAbono);
            int newId = 1;
            for (AbonoDTO abono : abonos) {
                if (newId <= abono.getId()){
                    newId =  abono.getId() + 1;
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
     * Retorna una abono dado su cliente
     * 
     * @param cliente, cliente del abono a buscar
     * @return abono buscado
     * @throws Exception cuando no existe el precio buscado
     */
    //public AbonoDTO getAbonoPorCliente(String nombreCliente) throws AbonoException{
      //  if (abonos == null) {
    //		logger.severe("Error interno: lista de abonos no existe.");
    //		throw new AbonoException("Error interno: lista de abonos no existe.");    		
    //	}
      //  for (int i = 0; i < abonos.size(); i++) {
      //      if(abonos.get(i).getNombreCliente()==nombreCliente){
      //          return abonos.get(i);
      ////      }
     //   }
       // throw new AbonoException("Error interno: no existe abono con ese cliente.");
//}

    /**
     * Actualiza un abono dado su id
     * 
     * @param id del abono a modificar
     * @param newAbono información para actualizar
     * @return la abono actualizada
     * @throws Exception si no existe un abono con ese id
     */
    public AbonoDTO updateAbono(int id, AbonoDTO newAbono) throws AbonoException {
        logger.info("recibiendo solictud de modificar author " + newAbono);
       for (AbonoDTO abono : abonos) {
            if (Objects.equals(abono.getId(), id)){
                abono.setId(newAbono.getId());
               abono.setCliente(newAbono.getCliente());
    
                logger.info("Modificando abono " + abono);
                return abono;
            }
        }
        logger.severe("No existe un abono con ese id");
        throw new AbonoException("No existe un abono con ese id");
    }
    
    
    /**
     * Elimina un abono del listado
     * 
     * @param id del abono a eliminar
     * @throws Exception si no existe un abono con ese id
     */
    public void deleteAbono(int id) throws AbonoException{
//        logger.info("Antes del ciclo");
        for (int i = 0; i < abonos.size(); i++) {
//            logger.info("antes del if");
            if(abonos.get(i).getId()==id){
//                logger.info("dentro del if");
               abonos.remove(i);
//                logger.info("despues de remover");
                return;
            }
        }
        logger.severe("No existe un abono con ese id");
        throw new AbonoException("No existe un abono con ese id");
    }
  
}

