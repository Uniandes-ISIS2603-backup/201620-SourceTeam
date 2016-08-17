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
    private int id;
    private int hora;
    private double precio;
    private Date dia;
    private boolean esFestival;
    
    /**
     * 
     */
    public FuncionDTO()
    {
        
    }
    
    /**
     * Constructor funcion.
     * @param pId Id funcion.
     * @param pHora Hora funcion.
     * @param pPrecio Precio funcion.
     * @param pDia Dia de la funcion.
     * @param pFestival Si la funcion pertenece o no al festival.
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
     * 
     * @return 
     */
    public int getId()
    {
        return id;
    }
    
    /**
     * 
     * @return 
     */
    public int getHora()
    {
        return hora;
    }
    
    /**
     * 
     * @return 
     */
    public double getPrecio()
    {
        return precio;
    }
    
    /**
     * 
     * @return 
     */
    public Date getDia()
    {
        return dia;
    }
    
    /**
     * 
     * @return 
     */
    public boolean esFestival()
    {
        return esFestival;
    }
    
    /**
     * 
     * @param pId 
     */
    public void setId(int pId)
    {
        id = pId;
    }
    
    /**
     * 
     * @param pHora 
     */
    public void setHora(int pHora)
    {
        hora = pHora;
    }
    
    /**
     * 
     * @param pPrecio 
     */
    public void setPrecio(double pPrecio)
    {
        precio = pPrecio;
    }
    
    /**
     * 
     * @param pDia 
     */
    public void setDia(Date pDia)
    {
        dia = pDia;
    }
    
    /**
     * 
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
