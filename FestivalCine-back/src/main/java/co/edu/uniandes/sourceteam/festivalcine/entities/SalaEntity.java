/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author ya.bejarano10
 */
public class SalaEntity extends BaseEntity implements Serializable
{
        @ManyToOne
        private TeatroEntity teatro = new TeatroEntity();
        
        @OneToMany(mappedBy = "sala")
        private List<SillaEntity> sillas = new ArrayList();
        
        private int numSala;
        private int numSillas;
        private int numSillasGenerales;
        private int numSillasPreferenciales;
        private boolean esFestival;
        
        public int getNumSala()
        {
            return numSala;
        }
        public void setNumSala(int n )
        {
            numSala = n;
        }
        
        public int getNumSillas()
        {
            return numSillas;
        }
        public void setNumSillas(int n)
        {
            numSillas = n;
        }
        
        public int getNumSillasGenerales()
        {
            return numSillasGenerales;
        }
       public void setNumSillasGenerales(int n)
       {
           numSillasGenerales =n;
       }
       
       public int getNumSillasPreferenciales()
        {
            return numSillasPreferenciales;
        }
       public void setNumSillasPreferenciales(int n)
       {
           numSillasPreferenciales =n;
       }
       
       public boolean getEsFestival()
        {
            return esFestival;
        }
       public void setEsFestval(boolean e)
       {
           esFestival = e;
       }

        
        

    
}
