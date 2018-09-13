/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Temporal;

/**
 *
 * @author js.bravo
 */
@Entity
public class AdministradorEntity extends UsuarioEntity implements Serializable {

    /**
     * Cadena de caracteres que representa la fecha de inicio del cargo del
     * administrador.
     */
   
    private Date fechaInicio;
    /**
     * Cargo del administrador.
     */
    private String cargo;

  
    /**
     * Retorna la fecha de inicio del cargo del administrador.
     *
     * @return la fecha de inicio del cargo del administrador.
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Asigna la fecha de inicio del cargo del administrador.
     *
     * @param fechaInicio - la fecha de inicio del cargo del administrador.
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Retorna el cargo del administrador.
     *
     * @return el cargo del administrador.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Asigna el cargo del administrador.
     *
     * @param cargo - el cargo del administrador.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }



}
