/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

/**
 * Objeto de transferencia de datos de Cliente
 * @author s.rodriguez20
 */
public class ClienteDTO {
    private Long id;
    private String nombres;
    private String apellidos;
    private Long documento;
    private boolean afiliado;

    /**
     *
     */
    public ClienteDTO() {
    }

    /**
     *
     * @param pNombre
     * @param pApellido
     * @param pDocumento
     * @param pAfiliado
     */
    public ClienteDTO(String pNombre, String pApellido, Long pDocumento, boolean pAfiliado) {
        this.id=0L;
        this.nombres = pNombre;
        this.apellidos = pApellido;
        this.documento= pDocumento;
        this.afiliado= pAfiliado;
    }
    public ClienteDTO(Long pId,String pNombre, String pApellido, Long pDocumento, boolean pAfiliado) {
        this.id=pId;
        this.nombres = pNombre;
        this.apellidos = pApellido;
        this.documento= pDocumento;
        this.afiliado= pAfiliado;
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

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Long getDocumento() {
        return documento;
    }

    public void setDocumento(Long documento) {
        this.documento = documento;
    }

    public boolean isAfiliado() {
        return afiliado;
    }

    public void setAfiliado(boolean afiliado) {
        this.afiliado = afiliado;
    }
    
    
    
    @Override
    public String toString() {
        return "{ id : " + getId() + ", nombre: \"" + nombres + "\", apellidos:\"" + apellidos + "\", documento:" + documento + ", afiliados:"+ afiliado + "}";
    }
    
}
