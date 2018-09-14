/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class PuntoVentaDetailDTO extends PuntoVentaDTO {
 
    //lista de calificaciones del punto de venta. relación de cero a muchas calificaciones.
    private List<CalificacionDTO> calificaciones;
    
    
    public PuntoVentaDetailDTO() {
        super();
    }
    
    
    public PuntoVentaDetailDTO(PuntoVentaEntity entity)
    {
        super(entity);
        if(entity.getCalificaciones()!=null)
        {
            calificaciones= new ArrayList<>();
            
            for(CalificacionEntity calificacionEntity: entity.getCalificaciones())
            {
                calificaciones.add(new CalificacionDTO(calificacionEntity));
            }
        }
    }
    
    
    public PuntoVentaEntity toEntity()
    {
       PuntoVentaEntity entity= super.toEntity();
       if(calificaciones!=null)
       {
           List<CalificacionEntity> calificacionesEntity=     new ArrayList<>();
           for(CalificacionDTO calificacionDTO:getCalificaciones())
           {
               calificacionesEntity.add(calificacionDTO.toEntity());
           }
           
           entity.setCalificaciones(calificacionesEntity);
       }
       
        return entity;
    }

    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }
    
    
    
}
