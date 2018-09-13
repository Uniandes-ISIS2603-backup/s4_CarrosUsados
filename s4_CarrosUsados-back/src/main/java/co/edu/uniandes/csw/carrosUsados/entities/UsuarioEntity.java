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
public class UsuarioEntity extends BaseEntity implements Serializable {

    /**
     * Nombre del usuario.
     */
    private String nombre;
    /**
     * Apellido del usuario.
     */
    private String apellido;
    /**
     * Correo electrónico del usuario.
     */
    private String correo;
    /**
     * Nombre de usuario (login) del usuario.
     */
    private String nombreUsuario;
    /**
     * Cadena de caracteres que representa la fecha de nacimiento del
     * usuario.
     */
    
    private Date fechaNacimiento;
    /**
     * Cadena de caracteres que representa el telefono del usuario.
     */
    private String telefono;
    /**
     * Contraseña asociada al login del usuario.
     */
    private String contrasena;

    /**
     * Retorna el nombre del usuario.
     *
     * @return nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el nombre del usuario.
     *
     * @param nombre - nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el apellido del usuario.
     *
     * @return apellido del usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Asigna el apellido del usuario.
     *
     * @param apellido - apellido del usuario.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Retorna el correo electrónico del usuario.
     *
     * @return correo electrónico del usuario.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Asigna el correo electrónico del usuario.
     *
     * @param correo - correo electrónico del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Retorna el nombre de usuario del usuario.
     *
     * @return el nombre de usuario del usuario.
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Asigna el nombre de usuario del usuario.
     *
     * @param nombreUsuario - el nombre de usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Retorna la fecha de nacimiento del usuario.
     *
     * @return la fecha de nacimiento del usuario.
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Asigna la fecha de nacimiento del usuario.
     *
     * @param fechaNacimiento - la fecha de nacimiento del usuario.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Retorna el teléfono del usuario.
     *
     * @return el teléfono del usuario.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna el teléfono del usuario.
     *
     * @param telefono - el teléfono del usuario.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Retorna la contraseña del usuario.
     *
     * @return la contrasena asociada al login del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Asigna la contraseña del usuario.
     *
     * @param contrasena - la contrasena asociada al login del usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
