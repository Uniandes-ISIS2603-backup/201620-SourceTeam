/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

/**
 *
 * @author ya.bejarano10
 */
public class SalaDTO 
{
    private Long numSala;
    private Long numSillas;
    private Long numSillasGenerales;
    private Long numSillasPreferenciales;
    private Boolean esFestival;

    /**
     * Constructor por defecto
     */
    public SalaDTO() 
    {
        
    }

    /**
     * Constructor con parámetros.
     * @param numSal numero de la sala
     * @param numS numero de sillas
     * @param numSillasG numero de sillas generales
     * @param numSillasP numero de sillas preferenciales
     * @param esF la sala es festival o no
     */
    public SalaDTO(long numSal, long numS, long numSillasG, long numSillasP, Boolean esF)
    {
		super();
                this.numSala = numSal;
		this.numSillas = numS;
		this.numSillasGenerales = numSillasG;
                this.numSillasPreferenciales = numSillasP;
                this.esFestival = esF;
    }

    /**
     * @return el numero de la sala
     */
    
    public Long getNumSala()
    {
        return numSala;
    }
    
    /**
     * @param numSS numero de sala a establecer
     */
    
    public void setNumSala(Long numSS)
    {
        this.numSala = numSS;
    }
    
    /**
     * @return el numero de sillas en la sala
     */
    public Long getNumSillas() {
        return numSillas;
    }

    /**
     * @param numS el numero de sillas a establecer
     */
    public void setNumSillas(Long numS) {
        this.numSillas = numS;
    }

    /**
     * @return el numero de sillas generales
     */
    public Long getNumSillasGenerales() {
        return numSillasGenerales;
    }
    
    /**
     * @param sillasG el numero de sillas generales a establecer
     */
    
    public void setNumSillasG(Long sillasG) {
        this.numSillasGenerales = sillasG;
    }
    
    /**
     * @return el numero de sillas preferenciales
     */
    public Long getNumSillasPreferenciales() {
        return numSillasPreferenciales;
    }
    
    /**
     * @param sillasP el numero de sillas preferenciales a establecer
     */
    public void setNumSillasP(Long sillasP) {
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
