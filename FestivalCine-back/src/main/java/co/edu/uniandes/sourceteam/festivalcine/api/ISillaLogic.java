/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.api;

import co.edu.uniandes.sourceteam.festivalcine.entities.SillaEntity;
import co.edu.uniandes.sourceteam.festivalcine.exceptions.BusinessLogicException;
import java.util.List;

/**
 *
 * @author s.rodriguez20
 */
public interface ISillaLogic {
    
   public List<SillaEntity> getSillas(Long salaId);
    public SillaEntity getSilla(Long salaid);
    public SillaEntity getSillaByPos(Long salaId,int fila, int numero);
    public SillaEntity createSilla(Long salaid, SillaEntity entity) throws BusinessLogicException ;
    public SillaEntity updateSilla(Long salaid, SillaEntity entity);
    public void deleteSilla(Long id);
   
    
}
