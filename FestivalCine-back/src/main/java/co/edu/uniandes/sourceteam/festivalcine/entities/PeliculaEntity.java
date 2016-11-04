/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ya.bejarano10
 */
@Entity
public class PeliculaEntity extends BaseEntity implements Serializable
{   
    @PodamExclude
    @OneToMany(mappedBy = "pelicula", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FuncionEntity> funciones = new ArrayList<>();
    
    private String nombre;
    private String anuncio;
    private String creditos;
    private int duracion;
    private String pais;
    
    public List<FuncionEntity> getFunciones()
    {
        return funciones;
    }
    public void setFunciones(List<FuncionEntity> fun)
    {
        funciones = fun;
    }
    public String getNombre()
    {
        return  nombre;
    }
    public void setNombre(String n)
    {
        nombre = n;
    }
    
    public String getAnuncio()
    {
        return anuncio;
    }
    public void setAnuncio(String a)
    {
        anuncio = a;
    }
    
    public String getCreditos()
    {
        return creditos;
    }
    public void setCreditos(String c)
    {
        creditos = c;
    }
    
    public int getDuracion()
    {
        return duracion;
    }
    public void setDuracion(int d)
    {
        duracion = d;
    }
    
    public String getPais()
    {
        return pais;
    }
    public void setPais(String p)
    {
        pais = p;
    }
    
}
