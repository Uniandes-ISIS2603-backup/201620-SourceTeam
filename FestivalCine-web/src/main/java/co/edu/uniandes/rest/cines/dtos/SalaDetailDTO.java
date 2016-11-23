/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.SillaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ya.bejarano10
 */
@XmlRootElement
public class SalaDetailDTO extends SalaDTO
{
     @PodamExclude
    private TeatroDTO teatro;
    private List<SillaEntity> sillas = new ArrayList();
     
     public SalaDetailDTO()
    {
        super();
    }
    
    public SalaDetailDTO(SalaEntity entity){
        super(entity);
        if(entity.getTeatro() != null)
            this.teatro = new TeatroDTO(entity.getTeatro());
        
        if(entity.getSillas() != null)
        {
            for (int i = 0; i < entity.getSillas().size(); i++)
            {
                SillaEntity actual = (SillaEntity)entity.getSillas().get(i);
                sillas.add(actual);
                
            }
        }
    }
    
    @Override
    public SalaEntity toEntity(){
        SalaEntity entity = super.toEntity();
        if(this.getTeatro() != null)
            entity.setTeatro(this.getTeatro().toEntity());
        
        if(this.getSillas() != null)
            entity.setSillas(this.getSillas());
        return entity;
    }
    
    public TeatroDTO getTeatro(){
        return this.teatro;
    }
    
    public void setTeatro(TeatroDTO teatro){
        this.teatro = teatro;
    }
    
    public List<SillaEntity> getSillas(){
        return this.sillas;
    }
    
    public void setSillas(List<SillaEntity> sillas){
        this.sillas = sillas;
    }
    
}
