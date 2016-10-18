/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.ardila13
 */
@Entity
public class CriticoEntity extends BaseEntity implements Serializable{
    @PodamExclude
    @ManyToOne
    private FestivalEntity festival;
    
    
    private int duracion;
    
    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getCredencial() {
        return credencial;
    }

    public void setCredencial(int credencial) {
        this.credencial = credencial;
    }
    
    private int credencial;

    
    public FestivalEntity getFestival(){
        return festival;
    }
    
    public void setFestival(FestivalEntity festival){
        this.festival = festival;
    }
}
