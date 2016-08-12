/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.resources;

import co.edu.uniandes.rest.cities.dtos.CityDTO;
import co.edu.uniandes.rest.cities.exceptions.CityLogicException;
import co.edu.uniandes.rest.cities.mocks.CityLogicMock;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author ya.bejarano10
 */
public class SalaResource 
{
@Path("salas")
@Produces("application/json")
public class CityResource {

    CityLogicMock cityLogic = new CityLogicMock();

    /**
     * Obtiene el listado de ciudades.
     *
     * @return lista de ciudades
     * @throws CityLogicException excepción retornada por la lógica
     */
    @GET
    public List<CityDTO> getCities() throws CityLogicException {
        return cityLogic.getCities();
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
    public CityDTO createCity(CityDTO city) throws CityLogicException {
        return cityLogic.createCity(city);
    }

    
}
}

