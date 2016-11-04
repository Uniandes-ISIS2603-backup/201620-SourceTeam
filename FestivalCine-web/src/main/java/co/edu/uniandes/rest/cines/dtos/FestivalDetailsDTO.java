/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.FestivalEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author s.ardila13
 */
@XmlRootElement
public class FestivalDetailsDTO extends FestivalDTO {
    
    //Lista de criticos del festival
    private List<CriticoDTO> criticos = new ArrayList<>();
    
    public FestivalDetailsDTO() {
        super();
    }
    
    public FestivalDetailsDTO(FestivalEntity entity){
        super(entity);
    }
    

    public FestivalEntity toEntity(){
        FestivalEntity entity = super.toEntity();
        return entity;
    }
    
    public List<CriticoDTO> getListaCriticos(){
        return criticos;
    }
    
    public void setListaCriticos(List<CriticoDTO> criticos){
        this.criticos = criticos;
    }
    
}