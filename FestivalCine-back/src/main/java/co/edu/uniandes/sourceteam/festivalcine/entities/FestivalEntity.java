/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.ardila13
 */
@Entity
public class FestivalEntity extends BaseEntity implements Serializable {
    
    @OneToMany(mappedBy = "festival")
    private List<CriticoEntity> criticos = new ArrayList<>();

    public List<CriticoEntity> getCriticos() {
        return criticos;
    }

    public void setCriticos(List<CriticoEntity> criticos) {
        this.criticos = criticos;
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

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }
    
    private int duracion;
    
    private String nombre;
    
    private String patrocinador;
    
}
