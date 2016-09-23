/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mappers;

import co.edu.uniandes.rest.cines.exceptions.ClienteException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

/**
 *
 * @author s.rodriguez20
 */
public class ClienteExceptionMapper implements ExceptionMapper<ClienteException>{
    /**
     * Generador de una respuesta a partir de una excepción
     * @param ex excecpión a convertir a una respuesta REST
     * @return 
     */

    @Override
    public Response toResponse(ClienteException ex) {
        // retorna una respuesta
        return Response
                .status(Response.Status.NOT_FOUND)	// estado HTTP 404
                .entity(ex.getMessage())		// mensaje adicional
                .type("text/plain")
                .build();
    }
}
