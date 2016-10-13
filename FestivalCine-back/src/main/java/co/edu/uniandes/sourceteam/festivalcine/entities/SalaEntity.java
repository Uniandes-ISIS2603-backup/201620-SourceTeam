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
import javax.persistence.OneToMany;

/**
 *
 * @author ya.bejarano10
 */
public class SalaEntity extends BaseEntity implements Serializable
{
        @OneToMany(mappedBy = "sala")
        private TeatroEntity teatro = new TeatroEntity();
        
        @OneToMany(mappedBy = "sala")
        private List<SillaEntity> sillas = new ArrayList();
        
        private int numSala;
        private int numSillas;

        
        

    
}
