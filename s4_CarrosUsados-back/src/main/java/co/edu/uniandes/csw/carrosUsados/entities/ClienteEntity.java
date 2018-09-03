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
public class ClienteEntity extends BaseEntity implements Serializable{

    /**
     * Nombre del cliente.
     */
    private String nombre;
    /**
     * Apellido del cliente.
     */
    private String apellido;
    /**
     * Correo electrónico del cliente.
     */
    private String correo;
    /**
     * Nombre de usuario (login) del cliente.
     */
    private String nombre_usuario;
    /**
     * Cadena de caracteres que representa la fecha de nacimiento del cliente.
     */
    private String fecha_nacimiento;
    /**
     * Cadena de caracteres que representa el telefono del cliente.
     */
    private String telefono;
    /**
     * Dirección de entrega/residencia del cliente.
     */
    private String direccion;
    /**
     * Contraseña asociada al login del cliente.
     */
    private String contrasena;

    /**
     * Retorna el nombre del cliente.
     *
     * @return nombre del cliente.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el nombre del cliente.
     *
     * @param nombre - nombre del cliente.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el apellido del cliente.
     *
     * @return apellido del cliente.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Asigna el apellido del cliente.
     *
     * @param apellido - apellido del cliente.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * Retorna el correo electrónico del cliente.
     *
     * @return correo electrónico del cliente.
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Asigna el correo electrónico del cliente.
     *
     * @param correo - correo electrónico del cliente.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Retorna el nombre de usuario del cliente.
     *
     * @return el nombre de usuario del cliente.
     */
    public String getNombre_Usuario() {
        return nombre_usuario;
    }

    /**
     * Asigna el nombre de usuario del cliente.
     *
     * @param nombre_usuario - el nombre de usuario del cliente.
     */
    public void setNombre_Usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * Retorna la fecha de nacimiento del cliente.
     *
     * @return la fecha de nacimiento del cliente.
     */
    public String getFecha_Nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * Asigna la fecha de nacimiento del cliente.
     *
     * @param fecha_nacimiento - la fecha de nacimiento del cliente.
     */
    public void setFecha_Nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * Retorna el teléfono del cliente.
     *
     * @return el teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Asigna el teléfono del cliente.
     *
     * @param telefono - el teléfono del cliente.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Retorna la dirección de residencia/entrega del cliente.
     *
     * @return la dirección de residencia/entrega del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Asigna la dirección de residencia/entrega del cliente.
     *
     * @param direccion - la dirección de residencia/entrega del cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Retorna la contraseña del cliente.
     *
     * @return la contrasena asociada al login del cliente.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Asigna la contraseña del cliente.
     *
     * @param contrasena - la contrasena asociada al login del cliente.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

}
