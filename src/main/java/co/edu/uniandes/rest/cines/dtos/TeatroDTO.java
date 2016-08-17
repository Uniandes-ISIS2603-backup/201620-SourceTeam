/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

/**
 *
 * @author ba.bohorquez10
 */
public class TeatroDTO 
{
    private String ciudad;
    private String nombre;
    
    public TeatroDTO()
    {
        
    }
    
    public TeatroDTO(String pCiudad, String pNombre)
    {
        ciudad = pCiudad;
        nombre = pNombre;
    }
    
    public String getCiudad()
    {
        return ciudad;
    }
    
    public String getNombre()
    {
        return nombre;
    }
    
    public void setNombre(String pNombre)
    {
        nombre = pNombre;
    }
    
    public void setCiudad(String pCiudad)
    {
        ciudad = pCiudad;
    }
    
    @Override
    public String toString()
    {
        return "{ nombre:" + nombre + ", ciudad:" + ciudad + "}";
    }
}
