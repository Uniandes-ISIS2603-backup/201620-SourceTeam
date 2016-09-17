/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.exceptions;

/**
 * Representa las excepciones de la clase Boleta
 * 
 * @author s.rodriguez20
 */
public class BoletaException extends Exception{

    /**
     * Constructor con un mensaje
     * @param message mensaje de la excepción
     */
    public BoletaException(String message) {
        super(message);
    }
    
    /**
     * Constructor con mensaje y causa.
     * @param message mensaje de la excepción
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public BoletaException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor con una causa
     * @param cause causa de la excepción. Usada para generar la traza.
     */
    public BoletaException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor por defecto
     */
    public BoletaException() {
    }
    
}