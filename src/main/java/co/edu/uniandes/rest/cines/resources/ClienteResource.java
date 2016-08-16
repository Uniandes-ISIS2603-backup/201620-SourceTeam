/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.resources;

import co.edu.uniandes.rest.cines.dtos.ClienteDTO;
import co.edu.uniandes.rest.cines.exceptions.ClienteException;
import co.edu.uniandes.rest.cines.mocks.ClienteMock;
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
 * @author ca.nieto11
 */
@Path("clientes")
@Produces("application/json")
public class ClienteResource {
    ClienteMock clientes = new ClienteMock();
    
    /**
     * Obtiene el listado de clientes.
     *
     * @return lista de clientes
     * @throws ClienteException excepción retornada por la lógica
     */
    @GET
    public List<ClienteDTO> getClientes() throws ClienteException {
        return clientes.getClientes();
    }

   
    /**
     * Agrega un cliente
     *
     * @param cliente  a agregar
     * @return datos del cliente a agregar
     * @throws ClienteException cuando ya existe una boleta con el id
     * suministrado
     */
    @POST
    public ClienteDTO createCliente(ClienteDTO cliente) throws ClienteException {
        return clientes.createCliente(cliente);
    }
    
    
    /**
     * Retorna un cliente dado su nombre
     * 
     * @param nombre del cliente a retornar
     * @return un cliente
     * @throws ClienteException excepción retornada por la lógica
     */
    @GET
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public ClienteDTO getClientbyName(@PathParam("name") String nombre) throws ClienteException {
        return clientes.getClientbyName(nombre);
    }
    
    
    /**
     * Actualiza la información de un cliente identificada con su nombre
     * 
     * @param nombre del cliente
     * @param cliente con el que actualizar la información
     * @return el cliente actualizado
     * @throws ClienteException excepción retornada por la lógica
     */
    @PUT
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public ClienteDTO updateCliente(@PathParam("name") String nombre, ClienteDTO cliente) throws ClienteException{
        return clientes.updateCliente(nombre, cliente);
    }
    
    /**
     * Elimina un cliente dado su nombre
     * 
     * @param nombre del cliente eliminado
     * @throws ClienteException excepción retornada por la lógica
     */
    @DELETE
    @Path("{name: [a-zA-Z][a-zA-Z]*}")
    public void deleteCity(@PathParam("name")String nombre) throws ClienteException{
        clientes.deleteCliente(nombre);
    }
}
