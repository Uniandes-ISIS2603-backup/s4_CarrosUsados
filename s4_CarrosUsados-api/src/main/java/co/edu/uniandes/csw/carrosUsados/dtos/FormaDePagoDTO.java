/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;
import co.edu.uniandes.csw.carrosUsados.entities.FormaDePagoEntity; 

/**
 *
 * @author estudiante
 */
public class FormaDePagoDTO {

    private long id;
    private String nombre;
    private String tipo;

    /**
     * Crea una formaDePagoDTO
     * @param formaDePago entidad con la cual se va a crear el formaDePago
     */
    public FormaDePagoDTO(FormaDePagoEntity formaDePago) {
        this.id = formaDePago.getId();
        this.nombre = formaDePago.getNombre();
        this.tipo = formaDePago.getTipo();
    }

    /**
     * Crea una forma de pago vacio
     */
    public FormaDePagoDTO() {

    }

    /**
     * @return retorna el id de la forma de pago
     */
    public long getId() {
        return id;
    }

    /**
     * @param id cambia el id de la forma de pago
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return retorna el nombre de la forma de pago
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return  retorna el tipo de forma de pago
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param nombreP cambia el nombre de la forma de pago
     */
    public void setNombre(String nombreP) {
        this.nombre = nombreP;
    }

    /**
     * @param tipoP cambia por el tipo de la forma de pago
     */
    public void setTipo(String tipoP) {
        this.tipo = tipoP;
    }
    
    /**
     * Convierte la actual forma de pago DTO en una entidad
     * @return retorna forma de pago entidad.
     */
    public FormaDePagoEntity toEntity(){
        FormaDePagoEntity entity = new FormaDePagoEntity();
        entity.setId(id);
        entity.setNombre(nombre);
        entity.setTipo(tipo);
        return entity;
    }
}
