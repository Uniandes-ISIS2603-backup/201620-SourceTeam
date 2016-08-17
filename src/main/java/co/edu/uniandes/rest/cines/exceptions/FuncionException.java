/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.exceptions;

/**
 *
 * @author ba.bohorquez10 
 */
public class FuncionException extends Exception
{
    public FuncionException(String mensaje)
    {
        super(mensaje);
    }
    
    public FuncionException(String mensaje, Throwable cause)
    {
        super(mensaje, cause);
    }
    
    public FuncionException(Throwable cause)
    {
        super(cause);
    }
    
    public FuncionException()
    {
        
    }
}
