/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.SillaEntity;

/**
 * Objeto de transferencia de datos de Silla
 * @author s.rodriguez20
 */
public class SillaDTO {
    private Long id;
    private int fila;
    private int numero;
    private boolean preferencial;
    private boolean reservada;

    /**
     *
     */
    public SillaDTO() {
    }
    
    public SillaDTO(SillaEntity entity) {
        if (entity != null) {
            this.id = entity.getId();
            this.fila=entity.getFila();
            this.numero=entity.getNumero();
            this.preferencial=entity.isPreferencial();
            this.reservada=entity.isReservada();
        }
    }
    /**
     * Convierte un objeto SillaDTO a SillaEntity.
     *
     * @return Nueva objeto SillaEntity.
     * 
     */
    public SillaEntity toEntity() {
        SillaEntity entity = new SillaEntity();
        entity.setId(this.getId());
        entity.setFila(this.getFila());
        entity.setNumero(this.getNumero());
        entity.setPreferencial(this.getPreferencial());
        entity.setReservada(this.getReservada());
        return entity;
    }

    /**
     *
     * @param id
     * @param precio
     */
    public SillaDTO(int pFila, int pNumero, boolean pPreferencial, boolean pReservada) {
        this.id=0L;
        this.fila = pFila;
        this.numero = pNumero;
        this.preferencial= pPreferencial;
        this.reservada= pReservada;
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
    public int getFila() {
        return fila;
    }

    /**
     *
     * @param id
     */
    public void setFila(int pfila) {
        this.fila = pfila;
    }

    public int getNumero() {
        return numero;
    }
    /**
     *
     * @param id
     */
    public void setNumero(int pnumero) {
        this.numero = pnumero;
    }

    public boolean getPreferencial() {
        return preferencial;
    }
     public void setPreferencial(boolean pPreferencial) {
        this.preferencial = pPreferencial;
    }
     
    public boolean getReservada() {
        return reservada;
    }
     public void setReservada(boolean pReservada) {
        this.reservada = pReservada;
    }
    @Override
    public String toString() {
        return "{ id : " + getId() + ", fila:" + fila + ", numero:" + numero + ", Preferencial:" + preferencial + ", Reservada:"+ reservada + "}";
    }
    
}
