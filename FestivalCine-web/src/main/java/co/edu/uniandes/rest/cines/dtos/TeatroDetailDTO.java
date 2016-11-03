/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
import java.util.ArrayList;
//import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ba.bohorquez10
 */
@XmlRootElement
public class TeatroDetailDTO extends TeatroDTO
{
    private List<SalaDTO> salas = new ArrayList<>();
    
    public TeatroDetailDTO()
    {
        super();
    }
    
    public TeatroDetailDTO(TeatroEntity entity)
    {
        super(entity);
        
        List<SalaEntity> list = entity.getSalas();
        for (SalaEntity sala : list) 
        {
            //this.salas.add( new SalaDTO(sala) );
        }
    }
    
    @Override
    public TeatroEntity toEntity() 
    {
        TeatroEntity entity = super.toEntity();
        List<SalaDTO> salas = this.getSalas();
        
        for (SalaDTO sala : this.salas) 
        {         
           // entity.getSalas().add( sala.toEntity() );
        }
        
        return entity;
    }
    
    public List<SalaDTO> getSalas()
    {
        return salas;
    }
    
    public void setSalas(List<SalaDTO> nuevas)
    {
        salas = nuevas;
    }
    
}
