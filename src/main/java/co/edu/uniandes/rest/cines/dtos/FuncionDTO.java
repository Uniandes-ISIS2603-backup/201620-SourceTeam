/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import java.io.Console;
import java.util.Date;

/**
 * Objeto de transferencia de datos de Funcion.
 * @author ba.bohorquez10
 */
public class FuncionDTO 
{
    /**
     * Identificador unico de la funcion.
     */
    private Long id;
        
    /**
     * Fecha de la funcion.
     */
    private Date dia;
    
    /**
     * Crítico de la funcion.
     */
    private CriticoDTO critico;
        
    private int precio;
    
    /**
     * Crítico de la funcion.
     */
    private PeliculaDTO pelicula;
        
    /**
     * Sala de la funcion.
     */
    private SalaDTO sala;
        
    
    /**
     * 
     */
    public FuncionDTO()
    {
        
    }
    
    /**
     * Constructor de la funcion.
     * @param pId Id de la funcion.
     * @param pPrecio Precio de la funcion.
     * @param pDia Fecha de la funcion.
     * @param pelicula

     */
    public FuncionDTO(Long pId, Date pDia, PeliculaDTO pelicula, SalaDTO sala, int precio)
    {
        id = pId;
        dia = pDia;
        this.pelicula = pelicula;
        this.sala = sala;
        this.precio = precio;
    }
    
    public void setPrecio(int pPrecio)
    {
        this.precio = pPrecio;
    }
    
    public int getPrecio()
    {
        return precio;
    }
    
    
    /**
     * Retorna el id de la funcion.
     * @return Id de la funcion.
     */
    public Long getId()
    {
        return id;
    }
    
    
    /**
     * Retorna la fecha de la funcion.
     * @return Fecha de la funcion.
     */
    public Date getDia()
    {
        return dia;
    }
    
    /**
     * Retorna el critico de la funcion.
     * @return Critico de la funcion.
     */
    public CriticoDTO getCritico()
    {
        return critico;
    }
    
    /**
     * Retorna la sala de la funcion.
     * @return Sala de la funcion.
     */
    public SalaDTO getSala()
    {
        return sala;
    }
    
    
    /**
     * Cambia el id de la funcion.
     * @param pId Nuevo id.
     */
    public void setId(Long pId)
    {
        id = pId;
    }
    
    /**
     * Cambia el critico de la funcion.
     * @param critico Nuevo precio.
     */
    public void setPrecio(CriticoDTO critico)
    {
        this.critico = critico;
    }
    
        /**
     * Cambia la pelicula de la funcion.
     * @param pelicula Nuevo precio.
     */
    public void setPelicula(PeliculaDTO pelicula)
    {
        this.pelicula = pelicula;
    }
    
     /**
     * Retorna la pelicula de la funcion.
     */
    public PeliculaDTO getPelicula()
    {
        return this.pelicula;
    }
    
    
     /**
     * Retorna la sala de la funcion.
     */
    public void setSala(SalaDTO sala)
    {
        this.sala = sala;
    }
    
    /**
     * Cambia la fecha de la funcion.
     * @param pDia Nueva fecha.
     */
    public void setDia(Date pDia)
    {
        dia = pDia;
    }
    
    @Override
    public String toString()
    {
        return "{id:" + id + ", precio:" + precio + ", dia:" + dia +" }";
    }
}
