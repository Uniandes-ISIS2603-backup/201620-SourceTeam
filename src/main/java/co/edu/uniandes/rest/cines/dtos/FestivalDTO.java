/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

/**
 *
 * @author s.ardila13
 */public class FestivalDTO {
    
    private Long id;
    
    private int duracion;
    
    private String nombre;
    
    private String patrocinador;
    
    public FestivalDTO(){
        
    }
    
    public FestivalDTO(Long id, int duracion, String nombre, String patrocinador){
        
        this.id = id;
                
        this.duracion = duracion;
        
        this.nombre = nombre;
        
        this.patrocinador = patrocinador;
        
    }
    
    public int getDuracion(){
        return duracion;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getPatrocinador(){
        return patrocinador;
    }
    
    public Long getId(){
        return id;
    }
    
    public void setDuracion(int duracion){
        this.duracion = duracion;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public void setId(Long id){
        this.id = id;
    }
    
    public void setPatrocinador(String patrocinador){
        this.patrocinador = patrocinador;
    }
    
    @Override
    public String toString(){
        return "Id: " + id + "nombre: " + nombre + ", duracion: " + duracion + " patrocinar: " + patrocinador;
    }
    
}
