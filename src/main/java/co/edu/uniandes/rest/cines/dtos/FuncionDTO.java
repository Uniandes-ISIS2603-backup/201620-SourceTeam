/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

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
     * Precio de la funcion.
     */
    private double precio;
    
    /**
     * Fecha de la funcion.
     */
    private Date dia;
    
    /**
     * Crítico de la funcion.
     */
    private CriticoDTO critico;
        
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

     */
    public FuncionDTO(Long pId, double pPrecio, Date pDia, CriticoDTO critico)
    {
        id = pId;
        precio = pPrecio;
        dia = pDia;
        this.critico = critico;
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
     * Retorna el precio de la funcion.
     * @return Precio de la funcion.
     */
    public double getPrecio()
    {
        return precio;
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
     * Cambia el id de la funcion.
     * @param pId Nuevo id.
     */
    public void setId(Long pId)
    {
        id = pId;
    }
        
    /**
     * Cambia el precio de la funcion.
     * @param pPrecio Nuevo precio.
     */
    public void setPrecio(double pPrecio)
    {
        precio = pPrecio;
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
        return "{id:" + id + ", precio:" + precio + ", dia:" + dia + " }";
    }
}
