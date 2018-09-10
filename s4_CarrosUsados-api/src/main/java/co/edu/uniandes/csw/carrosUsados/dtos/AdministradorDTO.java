
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import java.io.Serializable;

/**
 *
 * @author js.bravo
 */
/**
 * Clase Data Transfer Object que representa un administrador con privilegios en
 * el sistema.
 */
public class AdministradorDTO extends UsuarioDTO implements Serializable {

    /**
     * Cadena de caracteres que representa la fecha de inicio del cargo del
     * Administrador.
     */
    private String fecha_inicio;
    /**
     * Cadena de caracteres que representa el cargo que ejerce el Administrador.
     */
    private String cargo;

    public AdministradorDTO() {
    }

    /**
     * Devuelve la fecha de inicio del cargo del administrador.
     *
     * @return fecha de inicio del administrador.
     */
    public String getFecha_Inicio() {
        return fecha_inicio;
    }

    /**
     * Modifica la fecha de incio del cargo del administrador.
     *
     * @param fecha_inicio fecha de inicio del cargo del administrador.
     */
    public void setFecha_Inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    /**
     * Devuelve el cargo del administrador.
     *
     * @return fecha de inicio del administrador.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Modifica el cargo del administrador.
     *
     * @param cargo cargo del administrador.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

}
