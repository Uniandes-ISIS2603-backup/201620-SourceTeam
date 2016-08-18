/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

/**
 * Objeto de transferencia de datos del teatro.
 * @author ba.bohorquez10
 */
public class TeatroDTO 
{
    /**
     * Ciudad donde se encuentra el teatro.
     */
    private String ciudad;
    
    /**
     * Nombre del teatro.
     */
    private String nombre;
    
    /**
     * 
     */
    public TeatroDTO()
    {
        
    }
    
    /**
     * Crea un nuevo teatro.
     * @param pCiudad Ciudad donde se encuantra el teatro.
     * @param pNombre Nombre del teatro.
     */
    public TeatroDTO(String pCiudad, String pNombre)
    {
        ciudad = pCiudad;
        nombre = pNombre;
    }
    
    /**
     * Retorna la ciudad donde se encuentra el teatro.
     * @return Ciudad donde se encuentra el teatro.
     */
    public String getCiudad()
    {
        return ciudad;
    }
    
    /**
     * Retorna el nombre del teatro.
     * @return Nombre del teatro.
     */
    public String getNombre()
    {
        return nombre;
    }
    
    /**
     * Cambia el nombre del teatro.
     * @param pNombre Nuevo nomrbe.
     */
    public void setNombre(String pNombre)
    {
        nombre = pNombre;
    }
    
    /**
     * Cambia la ciudad del teatro.
     * @param pCiudad Nueva ciudad.
     */
    public void setCiudad(String pCiudad)
    {
        ciudad = pCiudad;
    }
    
    /**
     * ToString del teatro.
     * @return Representacion en String del teatro.
     */
    @Override
    public String toString()
    {
        return "{ nombre:" + nombre + ", ciudad:" + ciudad + "}";
    }
}
