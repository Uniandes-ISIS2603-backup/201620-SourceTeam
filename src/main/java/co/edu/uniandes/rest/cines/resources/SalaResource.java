/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;


import co.edu.uniandes.rest.cines.dtos.SalaDTO;
import co.edu.uniandes.rest.cines.exceptions.SalaException;
import co.edu.uniandes.rest.cines.mocks.SalaMock;
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
 * @author ya.bejarano10
 */
@Path("salas")
@Produces("application/json")
public class SalaResource 
{


    SalaMock sala = new SalaMock();

    /**
     * Obtiene el listado de salas.
     *
     * @return lista de salas
     * @throws SalaException excepción retornada por la lógica
     */
    @GET
    public List<SalaDTO> getSalas() throws SalaException {
        return sala.getSalas();
    }

   
    /**
     * Agrega una sala
     *
     * @param salaN sala a agregar
     * @return datos de la sala a agregar
     * @throws SalaException cuando ya existe una sala con el numero suministrado
     */
    @POST
    public SalaDTO createSala(SalaDTO salaN) throws SalaException {
        return sala.createSala(salaN);
    }
    
    /**
     * Retorna una sala dado su numero
     * 
     * @param id de la sala a retornar
     * @return una sala
     * @throws SalaeException excepción retornada por la lógica
     */
    @GET
    @Path("{id: \\d+}")
    public SalaDTO getSalaPorNumero(@PathParam("id") Long id) throws SalaException {
        return sala.getSalabyId(id);
    }
    
    
    /**
     * Actualiza la información de una sala identificada con su numero
     * 
     * @param id de la sala
     * @param salaP con el que actualizar la información
     * @return la sala  actualizado
     * @throws SalaException excepción retornada por la lógica
     */
    @PUT
    @Path("{id: \\d+}")
    public SalaDTO updateSala(@PathParam("id") Long id, SalaDTO salaP) throws SalaException{
        return sala.updateSala(id, salaP);
    }
    
    /**
     * Elimina una sala dado su numero
     * 
     * @param id de la sala eliminada
     * @throws SalaException excepción retornada por la lógica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteSala(@PathParam("{id: \\d+}")Long  id) throws SalaException{
        sala.deleteSala(id);
    }

    
}


