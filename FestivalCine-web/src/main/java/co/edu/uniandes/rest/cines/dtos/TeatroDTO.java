/*
 * 
 */
package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.TeatroEntity;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Objeto de transferencia de datos del teatro.
 * @author ba.bohorquez10
 */
@XmlRootElement
public class TeatroDTO 
{
    /**
     * Id teatro.
     */
    private Long id;
    /**
     * Ciudad donde se encuentra el teatro.
     */
    private String ciudad;
    
    /**
     * Nombre del teatro.
     */
    private String name;
        
    /**
     * 
     */
    public TeatroDTO()
    {
        
    }
    
    
    public TeatroDTO(TeatroEntity entity)
    {
        if(entity != null)
        {
            this.name = entity.getName();
            this.id = entity.getId();
            this.ciudad = entity.getCiudad();
        }
    }
    
    public TeatroEntity toEntity() 
    {
        TeatroEntity entity = new TeatroEntity();
        
        entity.setName(this.getNombre());
        entity.setId(this.getId());
        entity.setCiudad(this.getCiudad() );
        
        return entity;
    }
    
    /**
     * Retorna la ciudad donde se encuentra el teatro.
     * @return Ciudad donde se encuentra el teatro.
     */
    public String getCiudad()
    {
        return ciudad;
    }
    
    /**
     * Retorna el nombre del teatro.
     * @return Nombre del teatro.
     */
    public String getNombre()
    {
        return name;
    }
    
    public Long getId()
    {
        return id;
    }
    
    /**
     * Cambia el nombre del teatro.
     * @param pNombre Nuevo nomrbe.
     */
    public void setNombre(String pNombre)
    {
        name = pNombre;
    }
    
    /**
     * Cambia la ciudad del teatro.
     * @param pCiudad Nueva ciudad.
     */
    public void setCiudad(String pCiudad)
    {
        ciudad = pCiudad;
    }
    
    public void setId(Long pId)
    {
        id = pId;
    }
    
    /**
     * ToString del teatro.
     * @return Representacion en String del teatro.
     */
    @Override
    public String toString()
    {
        return "{ nombre:" + name + ", ciudad:" + ciudad + "}";
    }
}