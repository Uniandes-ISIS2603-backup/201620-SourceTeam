/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.sourceteam.festivalcine.api;

import co.edu.uniandes.sourceteam.festivalcine.entities.FuncionEntity;
import java.util.List;

/**
 *
 * @author ba.bohorquez10
 */
public interface IFuncionLogic 
{
    public List<FuncionEntity> getFunciones();

    public FuncionEntity getFuncion(Long id);

    public FuncionEntity getFuncionByName(String name);

    public FuncionEntity createFuncion(FuncionEntity entity) throws Exception;

    public FuncionEntity updateFuncion(FuncionEntity entity);

    public void deleteFuncion(Long id);
}
