/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.resources;

import co.edu.uniandes.rest.cities.dtos.CityDTO;
import co.edu.uniandes.rest.cities.dtos.SalaDTO;
import co.edu.uniandes.rest.cities.exceptions.CityLogicException;
import co.edu.uniandes.rest.cities.exceptions.SalaException;
import co.edu.uniandes.rest.cities.mocks.CityLogicMock;
import co.edu.uniandes.rest.cities.mocks.SalaMock;
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
     * Obtiene el listado de ciudades.
     *
     * @return lista de ciudades
     * @throws CityLogicException excepción retornada por la lógica
     */
    @GET
    public List<SalaDTO> getCities() throws SalaException {
        return sala.getSalas();
    }

   
    /**
     * Agrega una ciudad
     *
     * @param city ciudad a agregar
     * @return datos de la ciudad a agregar
     * @throws CityLogicException cuando ya existe una ciudad con el id
     * suministrado
     */
    @POST
    public SalaDTO createSala(SalaDTO salaN) throws SalaException {
        return sala.createSala(salaN);
    }

    
}


