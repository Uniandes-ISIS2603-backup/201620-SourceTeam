/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author ba.bohorquez10
 */
@Entity
public class TeatroEntity extends BaseEntity implements Serializable
{
   private String ciudad;
   
   @OneToMany(mappedBy = "teatro", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<SalaEntity> salas = new ArrayList<>();
   
   public String getCiudad()
   {
       return ciudad;
   }
   
   public void setCiudad(String nCiudad)
   {
       ciudad = nCiudad;
   }
   
   public List<SalaEntity> getSalas()
   {
       return salas;
   }
   
   public void setSalas(List<SalaEntity> nSalas)
   {
       salas = nSalas;
   }
}
