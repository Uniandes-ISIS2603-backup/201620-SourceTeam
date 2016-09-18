/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author s.ardila13
 */
public class FestivalDetailsDTO extends FestivalDTO {
    
    //Lista de criticos del festival
    private List<CriticoDTO> criticos = new ArrayList<>();
    
    //Lista de teatros del festival
    private List<TeatroDTO> teatros = new ArrayList<>();
    
    public FestivalDetailsDTO(){
        
    }
    
    public FestivalDetailsDTO(Long id, int duracion, String nombre, String patrocinador){
        super(id, duracion, nombre, patrocinador);
    }
    
    public FestivalDetailsDTO(FestivalDTO festival){
        super(festival.getId(), festival.getDuracion(), festival.getNombre(), festival.getPatrocinador());
    }
    
    public List<CriticoDTO> getListaCriticos(){
        return criticos;
    }
    
    public List<TeatroDTO> getListaTeatro(){
        return teatros;
    }
    
    public void setListaCriticos(List<CriticoDTO> criticos){
        this.criticos = criticos;
    }
    
    public void setListaTeatros(List<TeatroDTO> teatros){
        this.teatros = teatros;
    }
}
