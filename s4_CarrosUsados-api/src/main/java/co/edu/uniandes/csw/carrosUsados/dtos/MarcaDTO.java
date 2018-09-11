/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.MarcaEntity;

/**
 *
 * @author estudiante
 */
public class MarcaDTO {

    public MarcaDTO() {
    }

    MarcaDTO(MarcaEntity marca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public MarcaEntity toEntity(){
        
        return new MarcaEntity();
    }
}
