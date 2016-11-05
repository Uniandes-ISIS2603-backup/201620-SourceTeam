package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.SillaEntity;
import co.edu.uniandes.rest.cines.dtos.SalaDTO;
import co.edu.uniandes.rest.cines.dtos.SillaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;
import uk.co.jemos.podam.common.PodamExclude;


@XmlRootElement
public class SillaDetailDTO extends SillaDTO {

    @PodamExclude
    private SalaDTO company;

     
    /**
     *
     */
    public SillaDetailDTO() {
        super();
    }

    /**
     * Crea un objeto SillaDetailDTO a partir de un objeto SillaEntity
     * incluyendo los atributos de SillaDTO.
     *
     * @param entity Entidad SillaEntity desde la cual se va a crear el
     * nuevo objeto.
     *
     */
    public SillaDetailDTO(SillaEntity entity) {
        super(entity);
        if (entity.getSala() != null) {
            this.company = new SalaDTO(entity.getSala());
        }

    }

    /**
     * Convierte un objeto SillaDetailDTO a SillaEntity incluyendo los
     * atributos de SillaDTO.
     *
     * @return  objeto SillaEntity.
     *
     */
    @Override
    public SillaEntity toEntity() {
        SillaEntity entity = super.toEntity();
        if (this.getSala() != null) {
            entity.setSala(this.getSala().toEntity());
        }
        return entity;
    }

    /**
     * Obtiene el atributo company.
     *
     * @return atributo company.
     *
     */
    public SalaDTO getSala() {
        return company;
    }

    /**
     * Establece el valor del atributo company.
     *
     * @param company nuevo valor del atributo
     *
     */
    public void setSala(SalaDTO company) {
        this.company = company;
    }

}