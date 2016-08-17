/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;
import co.edu.uniandes.rest.cines.dtos.AbonoDTO;
import co.edu.uniandes.rest.cines.mocks.AbonoMock;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
/**
 *
 * @author pa.alvarado10
 */
@Path("abonos")
@Produces("application/json")
public class AbonoResource {
 
   AbonoMock abonos = new AbonoMock();
    
    /**
     * Obtiene el listado de abonos.
     *
     * @return lista de abonos
     * @throws Exception excepción retornada por la lógica
     */
    @GET
    public List<AbonoDTO> getAbonos() throws Exception {
        return abonos.getAbonos();
    }

   
    /**
     * Agrega un abono
     *
     * @param abono a agregar
     * @return datos del abono a agregar
     * @throws Exception cuando ya existe un abono con el id
     * suministrado
     */
    @POST
    public AbonoDTO createBoleta(AbonoDTO abono) throws Exception {
        return abonos.createAbono(abono);
    }


    /**
     * Retorna un abono dado su id
     * 
     * @param id id del abono a retornar
     * @return un abono
     * @throws Exception excepción retornada por la lógica
     */
    @GET
    @Path("{id: \\d+}")
    public AbonoDTO getAbono(@PathParam("id") int id) throws Exception{
       return abonos.getAbono(id);
    }
    
    
    /**
     * Retorna un abono dado su precio
     * 
     * @param precio del abono a retornar
     * @return un abono
     * @throws Exception excepción retornada por la lógica
     */
    @GET
    @Path("{precio: [0-9][0-9]*}")
    public AbonoDTO getAbonoPorPrecio(@PathParam("precio") double precio) throws Exception {
        return abonos.getAbonoPorPrecio(precio);
    }
    
    
    /**
     * Actualiza la información del abono identificado con id
     * 
     * @param id del abono
     * @param abono con la que actualizar la información
     * @return el abono actualizado
     * @throws Exception excepción retornada por la lógica
     */
    @PUT
    @Path("{id: \\d+}")
    public AbonoDTO updateAbono(@PathParam("id") int id, AbonoDTO abono) throws Exception{
        return abonos.updateAbono(id, abono);
    }
    
    /**
     * Elimina un abono dado su id
     * 
     * @param id del abono eliminado
     * @throws Exception excepción retornada por la lógica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteAbono(@PathParam("id")int id) throws Exception{
        abonos.deleteAbono(id);
    }
}
 

