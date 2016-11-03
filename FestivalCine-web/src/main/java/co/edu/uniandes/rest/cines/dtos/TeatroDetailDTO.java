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
 * @author ba.bohorquez10
 */
public class TeatroDetailDTO extends TeatroDTO
{
    
    private List<SalaDTO> salas = new ArrayList<>();
    
    public TeatroDetailDTO()
    {
         
    }
    public TeatroDetailDTO(Long id, String pCiudad, String pNombre)
    {
        super(id, pCiudad, pNombre);
    }
    
    public TeatroDetailDTO(TeatroDTO nuevo)
    {
        super(nuevo.getId(), nuevo.getCiudad(), nuevo.getNombre());
    }
    
    public List<SalaDTO> getSalas()
    {
        return salas;
    }
    
    public void setSalas(List<SalaDTO> nuevas)
    {
        salas = nuevas;
    }
    
}