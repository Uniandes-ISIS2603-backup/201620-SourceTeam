/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.ardila13
 */
public class FestivalEntity extends BaseEntity implements Serializable {
    
    @PodamExclude
    @OneToMany(mappedBy = "festival")
    private List<TeatroEntity> teatros = new ArrayList<>();
    
    
    @PodamExclude
    @OneToMany(mappedBy = "festival")
    private List<PeliculaEntity> peliculas = new ArrayList<>();
    
    private Long id;

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

    public String getPatrocinador() {
        return patrocinador;
    }

    public void setPatrocinador(String patrocinador) {
        this.patrocinador = patrocinador;
    }
    
    private int duracion;
    
    private String nombre;
    
    private String patrocinador;
    
    public List<TeatroEntity> getTeatros(){
        return teatros;
    }
    
    public void setTeatros(List<TeatroEntity> teatros){
        this.teatros = teatros;
    }
    
    public List<PeliculaEntity> getPelicula(){
        return peliculas;
    }
    
    public void setPeliculas(List<PeliculaEntity> peliculas){
        this.peliculas = peliculas;
    }
}
