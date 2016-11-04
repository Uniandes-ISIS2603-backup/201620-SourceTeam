/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.CriticoEntity;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.ardila13
 */
@XmlRootElement
public class CriticoDetailDTO extends CriticoDTO {
    @PodamExclude
    private FestivalDTO festival;
    
    public CriticoDetailDTO()
    {
        super();
    }
    
    public CriticoDetailDTO(CriticoEntity entity){
        super(entity);
        if(entity.getFestival() != null)
            this.festival = new FestivalDTO(entity.getFestival());
    }
    
    @Override
    public CriticoEntity toEntity(){
        CriticoEntity entity = super.toEntity();
        if(this.getFestival() != null)
            entity.setFestival(this.getFestival().toEntity());
        return entity;
    }
    
    public FestivalDTO getFestival(){
        return this.festival;
    }
    
    public void setFestival(FestivalDTO festival){
        this.festival = festival;
    }
    
}
