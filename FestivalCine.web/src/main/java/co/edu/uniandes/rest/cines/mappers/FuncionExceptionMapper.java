/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.mappers;

import co.edu.uniandes.rest.cines.exceptions.FuncionException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Convertidor de excepciones FuncionException a mensajes REST.
 * 
 * @author ba.bohorquez10
 */
@Provider
public class FuncionExceptionMapper implements ExceptionMapper<FuncionException>
{
    @Override
    public Response toResponse(FuncionException ex) {
        // retorna una respuesta
        return Response
                .status(Response.Status.NOT_FOUND)	// estado HTTP 404
                .entity(ex.getMessage())		// mensaje adicional
                .type("text/plain")
                .build();
    }
}
