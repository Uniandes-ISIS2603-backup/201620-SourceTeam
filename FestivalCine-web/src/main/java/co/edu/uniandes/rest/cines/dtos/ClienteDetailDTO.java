package co.edu.uniandes.rest.cines.dtos;

import co.edu.uniandes.sourceteam.festivalcine.entities.ClienteEntity;
import co.edu.uniandes.sourceteam.festivalcine.entities.BoletaEntity;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClienteDetailDTO extends ClienteDTO {

    // relación  cero o muchos con departments 
    private List<BoletaDTO> departments = new ArrayList<>();

    public ClienteDetailDTO() {
        super();
    }
    

    /**
     * Crea un objeto ClienteDetailDTO a partir de un objeto ClienteEntity
     * incluyendo los atributos de ClienteDTO.
     *
     * @param entity Entidad ClienteEntity desde la cual se va a crear el nuevo
     * objeto.
     *
     */
    public ClienteDetailDTO(ClienteEntity entity) {
        super(entity);
        List<BoletaEntity> departmentsList = entity.getBoletas();
        for (BoletaEntity bol : departmentsList) {
            this.departments.add(new BoletaDTO(bol));
        }
    }

    /**
     * Convierte un objeto ClienteDetailDTO a ClienteEntity incluyendo los
     * atributos de ClienteDTO.
     *
     * @return objeto ClienteEntity.
     *
     */
    @Override
    public ClienteEntity toEntity() {
        ClienteEntity entity = super.toEntity();
         List<BoletaDTO> departments = this.getBoletas();
        for (BoletaDTO bol : this.departments) {         
            entity.getBoletas().add(bol.toEntity());
        }
        return entity;
    }

    /**
     * @return the departments
     */
    public List<BoletaDTO> getBoletas() {
        return departments;
    }

    /**
     * @param departments the departments to set
     */
    public void setBoletas(List<BoletaDTO> departments) {
        this.departments = departments;
    }

}