/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.entities;

import java.io.Serializable;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.ardila13
 */
public class CriticoEntity extends BaseEntity implements Serializable{
    @PodamExclude
    @ManyToOne
    private FestivalEntity fetival;
    
    private Long id;
    
    private int duracion;
    
    private String nombre;

    public FestivalEntity getFetival() {
        return fetival;
    }

    public void setFetival(FestivalEntity fetival) {
        this.fetival = fetival;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCredencial() {
        return credencial;
    }

    public void setCredencial(int credencial) {
        this.credencial = credencial;
    }
    
    private int credencial;

    
    public FestivalEntity getFestival(){
        return fetival;
    }
    
    public void setFestival(FestivalEntity festival){
        this.fetival = festival;
    }
}
