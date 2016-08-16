/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

/**
 * Objeto de transferencia de datos de Boletas
 * @author ca.nieto11
 */
public class BoletaDTO {
    private int id;
    private double precio;

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
    public BoletaDTO(int id, double precio) {
        this.id = id;
        this.precio = precio;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public double getPrecio() {
        return precio;
    }

    /**
     *
     * @param precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "{" + "id:\"" + id + "\", precio:\"" + precio + "\"}";
    }
    
}
