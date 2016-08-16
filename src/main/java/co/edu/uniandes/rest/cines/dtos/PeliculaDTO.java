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
public class PeliculaDTO
{
    private String nombre;
    private String anuncio;
    private String creditos;
    private String critico;
    private int duracion ;
    private String pais;

    /**
     * Constructor por defecto
     */
    public PeliculaDTO() 
    {
        
    }

    /**
     * Constructor con parámetros.
     * @param nomb nombre de la pelicula
     * @param anun anuncio de la pelicula
     * @param credi creditos de la pelicula
     * @param criti critico de la pelicula
     * @param dura duracion de la pelicula
     * @param paisP pais de la pelicula
     */
    public PeliculaDTO(String nomb, String anun, String credi, String criti, int dura, String paisP)
    {
		super();
                this.nombre = nomb;
                this.anuncio = anun;
		this.creditos = credi;
		this.critico = criti;
                this.duracion = dura;
                this.pais = paisP;
    }
    
    /**
     * @return el nombre de la pelicula
     */
    public String getNombre()
    {
        return nombre;
    }
    
    /**
     * @param nomb nombre a establecer para la pelicula
     */
    public void setNombre(String nomb)
    {
        this.nombre = nomb;
    }

    /**
     * @return el enlace del anuncio
     */
    
    public String getAnuncio()
    {
        return anuncio;
    }
    
    /**
     * @param anun enlace nuevo del anuncio
     */
    
    public void setAnuncio(String anun)
    {
        this.anuncio = anun;
    }
    
    /**
     * @return los creditos de la pelicula
     */
    public String getCreditos() {
        return creditos;
    }

    /**
     * @param credi los nuevos creditos de la pelicula
     */
    public void setCreditos(String credi) {
        this.creditos = credi;
    }

    /**
     * @return el critico de la pelicula
     */
    public String getCritico() {
        return critico;
    }
    
    /**
     * @param criti el critico de la pelicula
     */
    
    public void setCritico(String criti) {
        this.critico = criti;
    }
    
    /**
     * @return la duracion de la pelicula
     */
    public int getDuracion() {
        return duracion;
    }
    
    /**
     * @param dura la duracion de la pelicula a establecer
     */
    public void setDuracion(int dura) {
        this.duracion = dura;
    }
    
    /**
     * @return el pais de la pelicula
     */
    public String getPais() {
        return pais;
    }
    
    /**
     * @param paisP el pais de la pelicula a establecer
     */
    public void setEsFestival(String paisP) {
        this.pais = paisP;
    }
    
    /**
     * Convierte el objeto a una cadena
     */
    @Override
    public String toString() {
    	return "{nombre : " + getNombre()+ " duracion : " + getDuracion() + ", critico : \"" + getCritico() + "\" }" ;  
    }
}