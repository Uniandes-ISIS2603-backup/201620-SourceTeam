/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

/**
 * Objeto de transferencia de datos de Silla
 * @author s.rodriguez20
 */
public class BoletaDTO {
    private Long id;
    private int precio;
    

    /**
     *
     */
    public BoletaDTO() {
    }

    /**
     *
     * @param id
     * @param precio
     */
    public BoletaDTO(int pPrecio) {
        this.id=0L;
        this.precio = pPrecio;
        
    }
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     *
     * @return
     */
    public int getPrecio() {
        return precio;
    }

    /**
     *
     * @param id
     */
    public void setPrecio(int pPrecio) {
        this.precio = pPrecio;
    }
    @Override
    public String toString() {
        return "{ id : " + id + ", precio:" + precio+"}";
    }
    
}
