/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.ejbs;

import co.edu.uniandes.sourceteam.festivalcine.api.IBoletaLogic;
import co.edu.uniandes.sourceteam.festivalcine.entities.BoletaEntity;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author s.rodriguez20
 */
@Stateless
public class BoletaLogic implements IBoletaLogic {

    @Override
    public List<BoletaEntity> getBoleta(Long boletaId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
