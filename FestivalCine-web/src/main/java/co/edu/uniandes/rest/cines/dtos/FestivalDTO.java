/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.FestivalEntity;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author s.ardila13
 */
@XmlRootElement
public class FestivalDTO {
    
    private Long id;
    
    private int duracion;
    
    private String nombre;
    
    private String patrocinador;
    
    
    public FestivalDTO(){
        
    }
    
    public FestivalDTO(FestivalEntity entity){
        
        if(entity != null){
            this.id = entity.getId();
                
            this.duracion = entity.getDuracion();
        
            this.nombre = entity.getName();
        
            this.patrocinador = entity.getPatrocinador();
        }
        
    }
    
    public FestivalEntity toEntity(){
        FestivalEntity entity = new FestivalEntity();
        entity.setId(this.getId());
        entity.setDuracion(this.getDuracion());
        entity.setName(this.getNombre());
        entity.setPatrocinador(this.getPatrocinador());
        return entity;
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
