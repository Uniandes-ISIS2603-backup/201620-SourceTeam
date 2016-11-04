/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;
import co.edu.uniandes.sourceteam.festivalcine.entities.CriticoEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.SalaEntity;
/**
 *
 * @author ya.bejarano10
 */
public class SalaDTO 
{
    private Long id;
    private int numSala;
    private int numSillas;
    private int numSillasGenerales;
    private int numSillasPreferenciales;
    private Boolean esFestival;

    /**
     * Constructor por defecto
     */
    public SalaDTO() 
    {
        /* autoimplemented method
        */
    }

    public SalaDTO(SalaEntity entity){
        if(entity != null)
        {
            this.id = entity.getId();
        
            this.numSala = entity.getNumSala();
            this.numSillas = entity.getNumSillas();
            this.numSillasGenerales = entity.getNumSillasGenerales();
            this.numSillasPreferenciales = entity.getNumSillasPreferenciales();
        }
                
    }

    /**
     * @return el numero de la sala
     */
    
    public SalaEntity toEntity(){
        SalaEntity entity = new SalaEntity();
        entity.setId(this.getId());
        entity.setNumSala(this.getNumSala());
        entity.setNumSillas(this.getNumSillas());
        entity.setNumSillasGenerales(this.getNumSillasGenerales());
        entity.setNumSillasPreferenciales(this.getNumSillasPreferenciales());
        return entity;
    }
    public Long getId()
    {
        return id;
    }
    public void setId(Long pid)
    {
        id = pid;
    }
    
    public int getNumSala()
    {
        return numSala;
    }
    
    /**
     * @param numSS numero de sala a establecer
     */
    
    public void setNumSala(int numSS)
    {
        this.numSala = numSS;
    }
    
    /**
     * @return el numero de sillas en la sala
     */
    public int getNumSillas() {
        return numSillas;
    }

    /**
     * @param numS el numero de sillas a establecer
     */
    public void setNumSillas(int numS) {
        this.numSillas = numS;
    }

    /**
     * @return el numero de sillas generales
     */
    public int getNumSillasGenerales() {
        return numSillasGenerales;
    }
    
    /**
     * @param sillasG el numero de sillas generales a establecer
     */
    
    public void setNumSillasGenerales(int sillasG) {
        this.numSillasGenerales = sillasG;
    }
    
    /**
     * @return el numero de sillas preferenciales
     */
    public int getNumSillasPreferenciales() {
        return numSillasPreferenciales;
    }
    
    /**
     * @param sillasP el numero de sillas preferenciales a establecer
     */
    public void setNumSillasPreferenciales(int sillasP) {
        this.numSillasPreferenciales = sillasP;
    }
    
    /**
     * @return si es festival o no
     */
    public Boolean esFestival() {
        return esFestival;
    }
    
    /**
     * @param esF el estado actual de la sala
     */
    public void setEsFestival(Boolean esF) {
        this.esFestival = esF;
    }
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{numero de sala : " + getNumSala() + " numero de sillas : " + getNumSillas()+ ", es festival : \"" + esFestival() + "\" }" ;  
    }
}
