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
 * @author s.rodriguez20
 */
@Path("clientes")
@Produces("application/json")
public class ClienteResource {
    ClienteMock clientes = new ClienteMock();
    
    /**
     * Obtiene el listado de clientes.
     *
     * @return lista de Clientes
     * @throws ClienteException excepción retornada por la lógica
     */
    @GET
    public List<ClienteDTO> getClientes() throws ClienteException {
        return clientes.getClientes();
    }

   
    /**
     * Agrega una cliente
     *
     * @param cliente cliente a agregar
     * @return datos de la cliente a agregar
     * @throws ClienteException cuando ya existe una cliente con el id
     * suministrado
     */
    @POST
    public ClienteDTO createCliente(ClienteDTO cliente) throws ClienteException {
        return clientes.createCliente(cliente);
    }


    /**
     * Retorna una cliente dado su id
     * 
     * @param id id de la cliente a retornar
     * @return una cliente
     * @throws ClienteException excepción retornada por la lógica
     */
    @GET
    @Path("{id: \\d+}")
    public ClienteDTO getCliente(@PathParam("id") int id) throws ClienteException{
       return clientes.getCliente(id);
    }    
    
    /**
     * Actualiza la información de la cliente identificada con id
     * 
     * @param id de la cliente
     * @param cliente con la que actualizar la información
     * @return la cliente actualizada
     * @throws ClienteException excepción retornada por la lógica
     */
    @PUT
    @Path("{id: \\d+}")
    public ClienteDTO updateCliente(@PathParam("id") int id, ClienteDTO cliente) throws ClienteException{
        return clientes.updateCliente(id, cliente);
    }
    
    /**
     * Elimina una cliente dado su id
     * 
     * @param id de la cliente eliminada
     * @throws ClienteException excepción retornada por la lógica
     */
    @DELETE
    @Path("{id: \\d+}")
    public void deleteCliente(@PathParam("id")int id) throws ClienteException{
        clientes.deleteCliente(id);
    }
}
