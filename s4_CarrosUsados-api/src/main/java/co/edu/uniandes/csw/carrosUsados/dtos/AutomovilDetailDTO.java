/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class AutomovilDetailDTO extends AutomovilDTO {

    //Aca declaro la lista de objetos CalificacionDTO
    private List<CalificacionDTO> calificaciones;

    public AutomovilDetailDTO() {
        super();
    }

    public AutomovilDetailDTO(AutomovilEntity entity) {
        super(entity);
        if (entity.getCalificaciones() != null) {
            calificaciones = new ArrayList<>();
            for (CalificacionEntity entityCalificacion : entity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(entityCalificacion));
            }
        }

    }

}
