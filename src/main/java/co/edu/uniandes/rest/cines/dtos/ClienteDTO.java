/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

/**
 * Objeto de transferencia de datos de Clientes
 * @author ca.nieto11
 */
public class ClienteDTO {
 
    private String nombre;
    private boolean afiliado;
    private int[] calificacionCritico;
    private int[] calificacionPelicula;
    private int[] reservas;             //TODO Cambiar por Silla[]

    /**
     *
     */
    public ClienteDTO() {
    }

    /**
     *
     * @param nombre
     * @param afiliado
     */
    public ClienteDTO(String nombre, boolean afiliado) {
        super();
        this.nombre = nombre;
        this.afiliado = afiliado;
    }

    /**
     *
     * @param nombre
     * @param afiliado
     * @param calificacionCritico
     * @param calificacionPelicula
     * @param reservas
     */
    public ClienteDTO(String nombre, boolean afiliado, int[] calificacionCritico, int[] calificacionPelicula, int[] reservas) {
        super();
        this.nombre = nombre;
        this.afiliado = afiliado;
        this.calificacionCritico = calificacionCritico;
        this.calificacionPelicula = calificacionPelicula;
        this.reservas = reservas;
    }

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public boolean isAfiliado() {
        return afiliado;
    }

    /**
     *
     * @param afiliado
     */
    public void setAfiliado(boolean afiliado) {
        this.afiliado = afiliado;
    }

    /**
     *
     * @return
     */
    public int[] getCalificacionCritico() {
        return calificacionCritico;
    }

    /**
     *
     * @param calificacionCritico
     */
    public void setCalificacionCritico(int[] calificacionCritico) {
        this.calificacionCritico = calificacionCritico;
    }

    /**
     *
     * @return
     */
    public int[] getCalificacionPelicula() {
        return calificacionPelicula;
    }

    /**
     *
     * @param calificacionPelicula
     */
    public void setCalificacionPelicula(int[] calificacionPelicula) {
        this.calificacionPelicula = calificacionPelicula;
    }

    /**
     *
     * @return
     */
    public int[] getReservas() {
        return reservas;
    }

    /**
     *
     * @param reservas
     */
    public void setReservas(int[] reservas) {
        this.reservas = reservas;
    }

    @Override
    public String toString() {
        return "{nombre:\"" + nombre + "\", afiliado:\"" + afiliado + "\"}'";
    }
    
}
