/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.exceptions;

/**
 * Representa la excepcion de la clase teatro.
 * @author ba.bohorquez10
 */
public class TeatroException extends Exception
{
    public TeatroException(String mensaje)
    {
        super(mensaje);
    }
    
    public TeatroException(String mensaje, Throwable cause)
    {
        super(mensaje, cause);
    }
    
    public TeatroException(Throwable cause)
    {
        super(cause);
    }
    
    public TeatroException()
    {
        
    }
}
