/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.entities;

import java.io.Serializable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author s.ardila13
 */
public class CriticoEntity extends BaseEntity implements Serializable{
    @PodamExclude
    @OneToOne
    private FestivalEntity fetival;
    
    public FestivalEntity getFestival(){
        return fetival;
    }
    
    public void setFestival(FestivalEntity festival){
        this.fetival = festival;
    }
}
