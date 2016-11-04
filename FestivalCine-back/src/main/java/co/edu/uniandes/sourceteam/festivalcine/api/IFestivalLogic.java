/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package co.edu.uniandes.sourceteam.festivalcine.api;

import co.edu.uniandes.sourceteam.festivalcine.entities.CriticoEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.FestivalEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.FuncionEntity;
import java.util.List;

public interface IFestivalLogic {
  
    public List<FestivalEntity> getFestivales();
    public FestivalEntity getFestival(Long festivalid);
    public FestivalEntity createFestival(FestivalEntity entity) throws Exception;
    public FestivalEntity updateFestival(FestivalEntity entity);
    public void deleteFestival(Long id);
    public List<CriticoEntity> listCriticos(Long festivalId);
    public CriticoEntity addCritico(Long festivalId, Long criticoId);
    public void removeCritico(Long festivalId, Long criticoId);
    public CriticoEntity getCritico(Long festivalId, Long criticoId);
    public FestivalEntity getFestivalByName(String name);
    public List<FuncionEntity> listFunciones(Long festivalId);
    public FuncionEntity addFuncion(Long festivalId, Long funcionId);
    public void removeFuncion(Long festivalId, Long funcionId);
    public FuncionEntity getFuncion(Long festivalId, Long funcionId);
}
