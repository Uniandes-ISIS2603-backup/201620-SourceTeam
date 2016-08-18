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
    private int id;
    
    /**
     * Hora de la funcion.
     */
    private int hora;
    
    /**
     * Precio de la funcion.
     */
    private double precio;
    
    /**
     * Fecha de la funcion.
     */
    private Date dia;
    
    /**
     * Boolean que indica si la funcion pertenece o no a un festival.
     */
    private boolean esFestival;
    
    /**
     * 
     */
    public FuncionDTO()
    {
        
    }
    
    /**
     * Constructor de la funcion.
     * @param pId Id de la funcion.
     * @param pHora Hora de la funcion.
     * @param pPrecio Precio de la funcion.
     * @param pDia Fecha de la funcion.
     * @param pFestival Si la funcion pertenece o no a un festival.
     */
    public FuncionDTO(int pId, int pHora, double pPrecio, Date pDia, boolean pFestival)
    {
        id = pId;
        hora = pHora;
        precio = pPrecio;
        dia = pDia;
        esFestival= pFestival;
    }
    
    /**
     * Retorna el id de la funcion.
     * @return Id de la funcion.
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * Retorna la hora de la funcion.
     * @return Hora de la funcion.
     */
    public int getHora()
    {
        return hora;
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
     * Indica si la funcion pertenece a un festival.
     * @return 
     */
    public boolean esFestival()
    {
        return esFestival;
    }
    
    /**
     * Cambia el id de la funcion.
     * @param pId Nuevo id.
     */
    public void setId(int pId)
    {
        id = pId;
    }
    
    /**
     * Cambia la hora de la funcion.
     * @param pHora Nueva hora.
     */
    public void setHora(int pHora)
    {
        hora = pHora;
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
     * Cambia la fecha de la funcion.
     * @param pDia Nueva fecha.
     */
    public void setDia(Date pDia)
    {
        dia = pDia;
    }
    
    /**
     * Modifica la funcion para indicar si esta pertenece a un festival o no.
     * @param pFestival 
     */
    public void setEsFestival(boolean pFestival)
    {
        esFestival = pFestival;
    }
    
    @Override
    public String toString()
    {
        return "{id:" + id + ", hora:" + hora + ", precio:" + precio + ", dia:" + dia + ", esFestival:" + esFestival + " }";
    }
}
