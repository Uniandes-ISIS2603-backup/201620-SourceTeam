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
