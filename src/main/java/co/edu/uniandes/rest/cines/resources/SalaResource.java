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
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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

    
}


