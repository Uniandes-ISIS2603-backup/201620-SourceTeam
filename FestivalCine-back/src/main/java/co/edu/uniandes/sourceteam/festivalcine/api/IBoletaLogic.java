/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.api;

import co.edu.uniandes.sourceteam.festivalcine.entities.BoletaEntity;
import java.util.List;

/**
 *
 * @author s.rodriguez20
 */
public interface IBoletaLogic {
    
    public List<BoletaEntity> getBoleta(Long boletaId);
    
}
