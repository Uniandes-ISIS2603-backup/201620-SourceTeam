/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cities.dtos;

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
     * @param id identificador de la ciudad
     * @param name nombre de la ciudad
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
     * @return the id
     */
    
    public Long getNumSala()
    {
        return numSala;
    }
    
    public void setNumSala(Long numSS)
    {
        this.numSala = numSS;
    }
    public Long getNumSillas() {
        return numSillas;
    }

    /**
     * @param id the id to set
     */
    public void setNumSillas(Long numS) {
        this.numSillas = numS;
    }

    /**
     * @return the name
     */
    public Long getNumSillasGenerales() {
        return numSillasGenerales;
    }
    
    public void setNumSillasG(Long sillasG) {
        this.numSillasGenerales = sillasG;
    }
    
    public Long getNumSillasPreferenciales() {
        return numSillasPreferenciales;
    }
    
    public void setNumSillasP(Long sillasP) {
        this.numSillasPreferenciales = sillasP;
    }
    
    public Boolean esFestival() {
        return esFestival;
    }
    
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
