/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.dtos;

import co.edu.uniandes.csw.carrosusados.entities.FormaDePagoEntity;

import java.util.Date;

/**
 * @author estudiante
 */
public class FormaDePagoDTO {

    private long id;
    private String nombreTar;
    private String tipoTar;
    private String numeroTar;
    private String codigoTar;
    private Date fechaTar;

    /**
     * Crea una formaDePagoDTO
     *
     * @param formaDePago entidad con la cual se va a crear el formaDePago
     */
    public FormaDePagoDTO(FormaDePagoEntity formaDePago) {
        this.id = formaDePago.getId();
        this.nombreTar = formaDePago.getNombre();
        this.tipoTar = formaDePago.getTipo();
        this.numeroTar = formaDePago.getNumero();
        this.codigoTar = formaDePago.getCodigo();
        this.fechaTar = formaDePago.getFecha();
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
        return nombreTar;
    }

    /**
     * @return retorna el tipo de forma de pago
     */
    public String getTipo() {
        return tipoTar;
    }

    /**
     * @return retorna el numero de la tarjeta de la forma de pago
     */
    public String getNumero() {
        return numeroTar;
    }

    /**
     * @return retorna el codigo de la tarjeta de forma de pago
     */
    public String getCodigo() {
        return codigoTar;
    }

    /**
     * @return retorna la fecha de la tarjeta de la forma de pago
     */
    public Date getFecha() {
        return fechaTar;
    }

    /**
     * @param nombreP cambia el nombre de la forma de pago
     */
    public void setNombre(String nombreP) {
        this.nombreTar = nombreP;
    }

    /**
     * @param tipoP cambia por el tipo de la tarjeta de la forma de pago
     */
    public void setTipo(String tipoP) {
        this.tipoTar = tipoP;
    }

    /**
     * @param numeroP cambie el numero de la tarjeta de la forma de pago
     */
    public void setNumero(String numeroP) {
        this.numeroTar = numeroP;
    }

    /**
     * @param codigoP cambia el codigo de la tarjeta de la forma de pago
     */
    public void setCodigo(String codigoP) {
        this.codigoTar = codigoP;
    }

    /**
     * @param fechaP cambia la fecha de la tarjeta de la forma de pago
     */
    public void setFecha(Date fechaP) {
        this.fechaTar = fechaP;
    }

    /**
     * Convierte la actual forma de pago DTO en una entidad
     *
     * @return retorna forma de pago entidad.
     */
    public FormaDePagoEntity toEntity() {
        FormaDePagoEntity entity = new FormaDePagoEntity();
        entity.setId(id);
        entity.setNombre(nombreTar);
        entity.setTipo(tipoTar);
        entity.setNumero(numeroTar);
        entity.setCodigo(codigoTar);
        entity.setFecha(fechaTar);
        return entity;
    }
}
