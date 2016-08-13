/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.resources;

import co.edu.uniandes.rest.cities.dtos.PeliculaDTO;
import co.edu.uniandes.rest.cities.dtos.SalaDTO;
import co.edu.uniandes.rest.cities.exceptions.PeliculaException;
import co.edu.uniandes.rest.cities.exceptions.SalaException;
import co.edu.uniandes.rest.cities.mocks.PeliculaMock;
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
@Path("peliculas")
@Produces("application/json")
public class PeliculaResource
{
    PeliculaMock pelicula = new PeliculaMock();

    /**
     * Obtiene el listado de peliculas.
     *
     * @return lista de peliculas
     * @throws PeliculaException excepción retornada por la lógica
     */
    @GET
    public List<PeliculaDTO> getPeliculas() throws PeliculaException {
        return pelicula.getPeliculas();
    }

   
    /**
     * Agrega una pelicula
     *
     * @param peliculaN pelicula a agregar
     * @return datos de la pelicula a agregar
     * @throws PeliculaException cuando ya existe una pelicula con el nombre suministrado
     */
    @POST
    public PeliculaDTO createSala(PeliculaDTO peliculaN) throws PeliculaException {
        return pelicula.createPelicula(peliculaN);
    }
}
