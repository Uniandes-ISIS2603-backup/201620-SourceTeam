/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.CriticoEntity;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author s.ardila13
 */
@XmlRootElement
public class CriticoDTO {
    
    private Long id;
    
    private int duracion;
    
    private String nombre;
    
    private int credencial;
    
    
    public CriticoDTO(){
        
    }
    
    public CriticoDTO(CriticoEntity entity){
        if(entity != null)
        {
            this.id = entity.getId();
        
            this.duracion = entity.getDuracion();
        
            this.nombre = entity.getName();
        
            this.credencial = entity.getCredencial();
        }
                
    }
    
    public CriticoEntity toEntity(){
        CriticoEntity entity = new CriticoEntity();
        entity.setId(this.id);
        entity.setName(this.nombre);
        entity.setDuracion(this.duracion);
        entity.setCredencial(this.credencial);
        return entity;
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
    
    @Override
    public String toString(){
        return "Id : " + id + ", nombre: " + nombre + ", duracion: " + duracion + " credencial: " + credencial ;
    }
    
}
