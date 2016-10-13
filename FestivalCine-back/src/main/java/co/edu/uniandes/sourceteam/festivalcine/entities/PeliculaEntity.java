/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

/**
 *
 * @author ya.bejarano10
 */
@Entity
public class PeliculaEntity extends BaseEntity implements Serializable
{
    @OneToOne(mappedBy = "pelicula")
    private CriticoEntity critico = new CriticoEntity();
    private String nombre;
    private String anuncio;
    private String creditos;
    private int duracion;
    private String pais;
    
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
