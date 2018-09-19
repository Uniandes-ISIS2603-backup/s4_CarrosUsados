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
     // relación  cero o muchos reviews 
    private List<FacturaDTO> reviews;
    
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