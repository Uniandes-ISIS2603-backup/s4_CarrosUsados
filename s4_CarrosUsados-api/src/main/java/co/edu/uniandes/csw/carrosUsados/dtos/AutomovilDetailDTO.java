/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */
public class AutomovilDetailDTO extends AutomovilDTO implements Serializable {

    //Aca declaro la lista de objetos CalificacionDTO
    /**
     * La lista de calificaciones del automovil
     */
    private List<CalificacionDTO> calificaciones;

    /**
     * El constructor vacio obligatorio para cualquier DTO
     */
    public AutomovilDetailDTO() {
        super();
    }

    /**
     * Constructor de la clase DTO que recibe una entidad Automovil e instancia los atributos con los que reciba en esa clase
     * @param entity La entidad automovil que contiene los datos a asignarle al DTO
     */
    public AutomovilDetailDTO(AutomovilEntity entity) {
        super(entity);
        if (entity.getCalificaciones() != null) {
            calificaciones = new ArrayList<>();
            for (CalificacionEntity entityCalificacion : entity.getCalificaciones()) {
                calificaciones.add(new CalificacionDTO(entityCalificacion));
            }
        }

    }

    /**
     * Convierte el DTO en una entidad y lo retorna
     * @return La entidad Automovil
     */
    public AutomovilEntity toEntity(){
        AutomovilEntity entity = new AutomovilEntity();

        entity.setId(super.getId());
        entity.setMarca(super.getMarca());
        entity.setAnio(super.getAnio());
        entity.setColor(super.getColor());
        entity.setNumChasis(super.getNumChasis());
        entity.setPlaca(super.getPlaca());
        entity.setFechaAgregacion(super.getFechaAgregacion());
        entity.setPrecioOriginal(super.getPrecioOriginal());

        if (super.getFichaTecnica() != null) {
            entity.setFichaTecnica(super.getFichaTecnica().toEntity());
        }
        if(super.getPuntoVenta() != null){
            entity.setPuntoVenta(super.getPuntoVenta().toEntity());
        }
        if(this.calificaciones != null){
            List<CalificacionEntity> lista = new ArrayList<CalificacionEntity>();
            for(CalificacionDTO c:this.calificaciones){
                lista.add(c.toEntity());
            }
            entity.setCalificaciones(lista);
        }

        return entity;
    }

    /**
     * Retorna las calificaciones del automovil
     * @return La lista de calificaciones
     */
    public List<CalificacionDTO> getCalificaciones() {
        return calificaciones;
    }

    /**
     * Cambia las calificaciones del automovil
     * @param calificaciones Las calificaciones
     */
    public void setCalificaciones(List<CalificacionDTO> calificaciones) {
        this.calificaciones = calificaciones;
    }
}
