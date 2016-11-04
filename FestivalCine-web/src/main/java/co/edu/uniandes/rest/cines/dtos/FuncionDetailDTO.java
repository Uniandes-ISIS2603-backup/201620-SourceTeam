/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.BoletaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.FuncionEntity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ba.bohorquez10
 */
@XmlRootElement
public class FuncionDetailDTO extends FuncionDTO
{
    @PodamExclude
    private PeliculaDTO pelicula;
    
    @PodamExclude
    private SalaDTO sala;
    
    private List<BoletaDTO> boletas = new ArrayList<>();
    
    @PodamExclude
    private FestivalDTO festival;
    
    public FuncionDetailDTO()
    {
        super();
    }
    
    public FuncionDetailDTO (FuncionEntity entity)
    {
       super(entity);
       
       if(entity.getPelicula() != null)
       {
           this.pelicula = new PeliculaDTO( entity.getPelicula() );
       }
       
       if(entity.getSala() != null)
       {
           this.sala = new SalaDTO( entity.getSala() );
       }
       
       List<BoletaEntity> list = entity.getBoletas();
       
       for(BoletaEntity boleta: list)
       {
           this.boletas.add( new BoletaDTO(boleta) );
       }
       
       if(entity.)
    }
    
    @Override
    public FuncionEntity toEntity()
    {
        FuncionEntity entity = super.toEntity();
        
        if(this.getPelicula() != null)
        {
            entity.setPelicula(this.getPelicula().toEntity() );
        }
        
        if(this.getSala() != null)
        {
            entity.setSala(this.getSala().toEntity() );
        }
        
        for (BoletaDTO boleta : this.boletas) 
        {         
            entity.getBoletas().add( boleta.toEntity() );
        }
        
        return entity;
    }

    public PeliculaDTO getPelicula() {
        return pelicula;
    }

    public void setPelicula(PeliculaDTO pelicula) {
        this.pelicula = pelicula;
    }

    public SalaDTO getSala() {
        return sala;
    }

    public void setSala(SalaDTO sala) {
        this.sala = sala;
    }

    public List<BoletaDTO> getBoletas() {
        return boletas;
    }

    public void setBoletas(List<BoletaDTO> boletas) {
        this.boletas = boletas;
    }
    
    
}
