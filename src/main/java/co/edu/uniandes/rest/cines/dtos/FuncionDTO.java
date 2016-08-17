/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import java.util.Date;

/**
 * Objeto de transferencia de datos Funcion.
 * @author ba.bohorquez10
 */
public class FuncionDTO 
{
    private int id;
    private int hora;
    private double precio;
    private Date dia;
    private boolean esFestival;
    
    public FuncionDTO()
    {
        
    }
    
    public FuncionDTO(int pId, int pHora, double pPrecio, Date pDia, boolean pFestival)
    {
        id = pId;
        hora = pHora;
        precio = pPrecio;
        dia = pDia;
        esFestival= pFestival;
    }
    
    public int getId()
    {
        return id;
    }
    
    public int getHora()
    {
        return hora;
    }
    
    public double getPrecio()
    {
        return precio;
    }
    
    public Date getDia()
    {
        return dia;
    }
    
    public boolean esFestival()
    {
        return esFestival;
    }
    
    public void setId(int pId)
    {
        id = pId;
    }
    
    public void setHora(int pHora)
    {
        hora = pHora;
    }
    
    public void setPrecio(double pPrecio)
    {
        precio = pPrecio;
    }
    
    public void setDia(Date pDia)
    {
        dia = pDia;
    }
    
    public void setEsFestival(boolean pFestival)
    {
        esFestival = pFestival;
    }
    
    public String toString()
    {
        return "{id:" + id + ", hora:" + hora + ", precio:" + precio + ", dia:" + dia + ", esFestival:" + esFestival + " }";
    }
}
