/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.BoletaEntity;

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
    
    public BoletaDTO(BoletaEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.precio = entity.getPrecio();
        }
    }
    
    public BoletaEntity toEntity() {
        BoletaEntity entity = new BoletaEntity();
        entity.setId(this.getId());
        entity.setPrecio(precio);
        return entity;
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
        return "{ id : " + id + ", precio:" + precio+ "}";
    }
    
}
