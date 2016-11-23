/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.FuncionEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.PeliculaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author ya.bejarano10
 */
@XmlRootElement
public class PeliculaDetailDTO extends PeliculaDTO
{
    @PodamExclude
    private List<FuncionEntity> funciones = new ArrayList();
    
    public PeliculaDetailDTO()
    {
        super();
    }
    
    public PeliculaDetailDTO(PeliculaEntity pelicula)
    {
        super(pelicula);
        for(int i = 0; i<pelicula.getFunciones().size(); i++)
        {
            FuncionEntity actual = (FuncionEntity)pelicula.getFunciones().get(i);
            this.funciones.add(actual);
        }
       
    }
    
    @Override
    public PeliculaEntity toEntity(){
        PeliculaEntity entity = super.toEntity();
        if(this.getFunciones() != null)
            entity.setFunciones(this.getFunciones());
        return entity;
    }
     public List<FuncionEntity> getFunciones(){
        return this.funciones;
    }
    
    public void setFunciones(List<FuncionEntity> funciones){
        this.funciones = funciones;
    }    
}
