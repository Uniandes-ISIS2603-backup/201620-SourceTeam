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

    /**
     * Constructor con parámetros.
     * @param numSal numero de la sala
     * @param numS numero de sillas
     * @param numSillasG numero de sillas generales
     * @param numSillasP numero de sillas preferenciales
     * @param esF la sala es festival o no
     */
    public SalaDTO(int numSal, int numS, int numSillasG, int numSillasP, Boolean esF)
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
