/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package co.edu.uniandes.rest.cines.dtos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Rubby
 */
public class ClienteDetailDTO extends ClienteDTO{
   
    // relación  cero o muchos boletas 
    private List<BoletaDTO> boletas = new ArrayList<>();
    
        
    
    public ClienteDetailDTO() {
        
    }
   
    public ClienteDetailDTO(String pNombre, String pApellido, Long pDocumento, boolean pAfiliado) {
        super(pNombre, pApellido, pDocumento, pAfiliado);
       
     }
    
    public ClienteDetailDTO(ClienteDTO cliente) {
       super(cliente.getId(), cliente.getNombres(), cliente.getApellidos(), cliente.getDocumento(), cliente.isAfiliado());
        
       
     }
    /**
     * @return the boleta
     */
    public List<BoletaDTO> getReviews() {
        return boletas;
    }

    /**
     * @param reviews the boleta to set
     */
    public void setReviews(List<BoletaDTO> reviews) {
        this.boletas = reviews;
    }

    
    
    @Override
    public String toString() {
        return super.toString() + ", Reviews \"" + getReviews().toString();
    }
}
