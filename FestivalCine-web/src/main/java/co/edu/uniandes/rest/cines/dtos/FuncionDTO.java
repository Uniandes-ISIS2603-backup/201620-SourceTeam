/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.FuncionEntity;
import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Objeto de transferencia de datos de Funcion.
 * @author ba.bohorquez10
 */
@XmlRootElement
public class FuncionDTO
{
    private Long id;
    private String name;
    private Date dia;
    private double precio;
    
    /**
     * 
     */
    public FuncionDTO()
    {
        
    }
    
    public FuncionDTO(FuncionEntity entity)
    {
        if (entity != null) 
        {
            this.name = entity.getName();
            this.id = entity.getId();
            this.dia = entity.getDia();
            this.precio = entity.getPrecio();
        }
    }
    
    public FuncionEntity toEntity() 
    {
        FuncionEntity entity = new FuncionEntity();
        
        entity.setName( this.getName() );
        entity.setId( this.getId() );
        entity.setDia( this.getDia() );
        entity.setPrecio( this.getPrecio() );
        
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString()
    {
        return "{id:" + id + ", precio:" + precio + ", dia:" + dia +" }";
    }
}
