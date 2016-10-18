/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author ba.bohorquez10
 */
@Entity
public class FuncionEntity extends BaseEntity implements Serializable
{
    private Date dia;
    
    private double precio;
    
    @OneToOne
    private PeliculaEntity pelicula;
    
    @OneToOne
    private SalaEntity sala;
    
    @OneToMany(mappedBy = "funcion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BoletaEntity> boletas = new ArrayList<>();
    
    public Date getDia()
    {
        return dia;
    }
    
    public double getPrecio()
    {
        return precio;
    }
    
    public PeliculaEntity getPelicula()
    {
        return pelicula;
    }
    
    public SalaEntity getSala()
    {
        return sala;
    }
    
    public void setDia(Date nDia)
    {
        dia = nDia;
    }
    
    public void setPrecio(double nPrecio)
    {
        precio = nPrecio;
    }
    
    public void setPelicula(PeliculaEntity nPelicula)
    {
        pelicula = nPelicula;
    }
    
    public void setSala(SalaEntity nSala)
    {
        sala = nSala;
    }
    
}
