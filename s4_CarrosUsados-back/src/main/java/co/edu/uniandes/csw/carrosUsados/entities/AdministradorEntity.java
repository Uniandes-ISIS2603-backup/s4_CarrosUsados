/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author js.bravo
 */
@Entity
public class AdministradorEntity extends BaseEntity implements Serializable {

    /**
     * Nombre del administrador.
     */
    private String nombre;
    /**
     * Apellido del administrador.
     */
    private String apellido;
    /**
     * Correo electrónico del administrador.
     */
    private String correo;
    /**
     * Nombre de usuario (login) del administrador.
     */
    private String nombre_usuario;
    /**
     * Cadena de caracteres que representa la fecha de nacimiento del
     * administrador.
     */
    private String fecha_nacimiento;
    /**
     * Cadena de caracteres que representa el telefono del administrador.
     */
    private String telefono;
    /**
     * Cadena de caracteres que representa la fecha de inicio del cargo del
     * administrador.
     */
    private String fecha_inicio;
    /**
     * Cargo del administrador.
     */
    private String cargo;
    /**
     * Contraseña asociada al login del administrador.
     */
    private String contrasena;

    /**
     * Retorna el nombre del administrador.
     *
     * @return nombre del administrador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el nombre del administrador.
     *
     * @param nombre - nombre del administrador.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el apellido del administrador.
     *
     * @return apellido del administrador.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Asigna el apellido del administrador.
     *
     * @param apellido - apellido del administrador.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Retorna el correo electrónico del administrador.
     *
     * @return correo electrónico del administrador.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Asigna el correo electrónico del administrador.
     *
     * @param correo - correo electrónico del administrador.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Retorna el nombre de usuario del administrador.
     *
     * @return el nombre de usuario del administrador.
     */
    public String getNombre_Usuario() {
        return nombre_usuario;
    }

    /**
     * Asigna el nombre de usuario del administrador.
     *
     * @param nombre_usuario - el nombre de usuario del administrador.
     */
    public void setNombre_Usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * Retorna la fecha de nacimiento del administrador.
     *
     * @return la fecha de nacimiento del administrador.
     */
    public String getFecha_Nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * Asigna la fecha de nacimiento del administrador.
     *
     * @param fecha_nacimiento - la fecha de nacimiento del administrador.
     */
    public void setFecha_Nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * Retorna el teléfono del administrador.
     *
     * @return el teléfono del administrador.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna el teléfono del administrador.
     *
     * @param telefono - el teléfono del administrador.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Retorna la fecha de inicio del cargo del administrador.
     *
     * @return la fecha de inicio del cargo del administrador.
     */
    public String getFecha_Inicio() {
        return fecha_inicio;
    }

    /**
     * Asigna la fecha de inicio del cargo del administrador.
     *
     * @param fecha_inicio - la fecha de inicio del cargo del administrador.
     */
    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
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

    /**
     * Retorna la contraseña del administrador.
     *
     * @return la contrasena asociada al login del administrador.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Asigna la contraseña del administrador.
     *
     * @param contrasena - la contrasena asociada al login del administrador.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
