/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.FacturaEntity;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class FacturaDetailDTO extends FacturaDTO implements Serializable{

    public List<FacturaDTO> getReviews() {
        return reviews;
    }

    // relación  cero o muchos reviews
    public void setReviews(List<FacturaDTO> reviews) {
        this.reviews = reviews;
    }
    private List<FacturaDTO> reviews;
    
    public FacturaDetailDTO(){
        
    }
    
    public FacturaDetailDTO(FacturaEntity facturaEntity) {
        super(facturaEntity);
    }
    /**
     * Transformar el DTO a una entidad
     *
     * @return La entidad que representa el libro.
     */
    @Override
    public FacturaEntity toEntity() {
        FacturaEntity facturaEntity = super.toEntity();
        return facturaEntity;
    }
}
