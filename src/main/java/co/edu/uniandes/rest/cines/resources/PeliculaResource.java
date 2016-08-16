/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.ClienteDTO;
import co.edu.uniandes.rest.cines.dtos.PeliculaDTO;
import co.edu.uniandes.rest.cines.dtos.SalaDTO;
import co.edu.uniandes.rest.cines.exceptions.ClienteException;
import co.edu.uniandes.rest.cines.exceptions.PeliculaException;
import co.edu.uniandes.rest.cines.exceptions.SalaException;
import co.edu.uniandes.rest.cines.mocks.PeliculaMock;
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
    
    /**
     * Retorna una pelicula dado su nombre
     * 
     * @param nombre de la pelicula a retornar
     * @return una pelicula
     * @throws PeliculaException excepción retornada por la lógica
     */
    @GET
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public PeliculaDTO getPeliculaPorNombre(@PathParam("name") String nombre) throws PeliculaException {
        return pelicula.getPeliculaPorNombre(nombre);
    }
    
    
    /**
     * Actualiza la información de un cliente identificada con su nombre
     * 
     * @param nombre de la pelicula
     * @param peliculaP con el que actualizar la información
     * @return la pelicula actualizada
     * @throws PeliculaException excepción retornada por la lógica
     */
    @PUT
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public PeliculaDTO updatePelicula(@PathParam("name") String nombre, PeliculaDTO peliculaP) throws PeliculaException{
        return pelicula.updatePelicula(nombre, peliculaP);
    }
    
    /**
     * Elimina una pelicula dado su nombre
     * 
     * @param nombre de la pelicula
     * @throws PeliculaException excepción retornada por la lógica
     */
    @DELETE
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public void deleteCity(@PathParam("name")String nombre) throws PeliculaException{
        pelicula.deletePelicula(nombre);
    }
}
