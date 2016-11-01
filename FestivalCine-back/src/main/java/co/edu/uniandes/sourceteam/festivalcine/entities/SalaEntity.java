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
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ya.bejarano10
 */
@Entity
public class SalaEntity extends BaseEntity implements Serializable
{
        @PodamExclude
        @ManyToOne
        private TeatroEntity teatro;
        
        @OneToMany(mappedBy = "sala")
        private List<SillaEntity> sillas = new ArrayList();
        
        @PodamExclude
        @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<FuncionEntity> funciones = new ArrayList<>();
        
        private int numSala;
        private int numSillas;

    public TeatroEntity getTeatro() {
        return teatro;
    }

    public void setTeatro(TeatroEntity teatro) {
        this.teatro = teatro;
    }

    public List<SillaEntity> getSillas() {
        return sillas;
    }

    public void setSillas(List<SillaEntity> sillas) {
        this.sillas = sillas;
    }

    public List<FuncionEntity> getFunciones() {
        return funciones;
    }

    public void setFunciones(List<FuncionEntity> funciones) {
        this.funciones = funciones;
    }

    public boolean isEsFestival() {
        return esFestival;
    }

    public void setEsFestival(boolean esFestival) {
        this.esFestival = esFestival;
    }
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
