/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

/**
 *
 * @author s.ardila13
 */
public class CriticoDTO {
    
    private Long id;
    
    private int duracion;
    
    private String nombre;
    
    private int credencial;
    
    //Relación con el festival
    private FestivalDTO festival;
    
    public CriticoDTO(){
        
    }
    
    public CriticoDTO(Long id, int duracion, String nombre, int credencial, FestivalDTO festival){
        
        this.id = id;
        
        this.duracion = duracion;
        
        this.nombre = nombre;
        
        this.credencial = credencial;
        
        this.festival = festival;
        
    }
    
    public int getDuracion(){
        return duracion;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public Long getId(){
        return this.id;
    }
    
    public int getCredencial(){
        return credencial;
    }
    
    public FestivalDTO getFestival(){
        return this.festival;
    }
    
    public void setDuracion(int duracion){
        this.duracion = duracion;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setCredencial(int credencial){
        this.credencial = credencial;
    }
    
    public void setId(Long id){
        this.id =id;
    }
    
    public void setFestival(FestivalDTO festival){
        this.festival = festival;
    }
    
    @Override
    public String toString(){
        return "Id : " + id + ", nombre: " + nombre + ", duracion: " + duracion + " credencial: " + credencial + " festival: " + festival;
    }
    
}
