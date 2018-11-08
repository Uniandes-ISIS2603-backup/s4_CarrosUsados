/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carros.usados.dtos;

/**
 *
 * @author js.bravo
 */

import co.edu.uniandes.csw.carros.usados.entities.UsuarioEntity;

import java.util.Date;

/**
 * Clase Data Transfer Object Usuario que contiene atributos que utilizan tanto
 * ClienteDTO como AdministradorDTO.
 */
public class UsuarioDTO extends PersonaDTO {

    protected String correo;
    /**
     * Cadena de caracteres que representa la contraseña del usuario en el
     * sistema.
     */
    protected String contrasena;
    /**
     * Cadena de caracteres que representa el login del usuario en el sistema.
     */
    protected String nombreUsuario;
    /**
     * Cadena de caracteres que representa la fecha de nacimiento del usuario en
     * el sistema.
     */
    protected Date fechaNacimiento;
    /**
     * Cadena de caracteres que representa el telefono de contacto del usuario
     * en el sistema.
     */
    protected String telefono;

    /**
     * Constructor vacío generado por defecto.
     */
    public UsuarioDTO(){
        // Constructor por defecto
    }

    /**
     * Constructor que genera un Data Transfer Object UsuarioDTO a partir de un usuarioEntity.
     * @param usuarioEntity Entidad que representa al usuario.
     */
    public UsuarioDTO(UsuarioEntity usuarioEntity)
    {
        super(usuarioEntity);
        this.correo = usuarioEntity.getCorreo();
        this.contrasena = usuarioEntity.getContrasena();
        this.nombreUsuario = usuarioEntity.getNombreUsuario();
        this.fechaNacimiento = usuarioEntity.getFechaNacimiento();
        this.telefono = usuarioEntity.getTelefono();
    }


    /**
     * Devuelve el nombre del Usuario.
     *
     * @return nombre del Usuario
     */
    public String getCorreo() {
        return correo;
    }

    /**
     * Modifica el correo del usuario.
     *
     * @param correo correo del usuario.
     */
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    /**
     * Devuelve la contrasena del Usuario.
     *
     * @return contrasena del usuario.
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Modifica la contrasena del usuario.
     *
     * @param contrasena contrasena del usuario.
     */
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    /**
     * Devuelve el login del usuario.
     *
     * @return login del usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Modifica el login del usuario.
     *
     * @param nombreUsuario El login del usuario.
     */
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    /**
     * Devuelve la fecha de nacimiento del usuario.
     *
     * @return fecha de nacimiento del usuario
     */
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    /**
     * Modifica la fecha de nacimiento del usuario.
     *
     * @param fechaNacimiento fecha de nacimiento del usuario.
     */
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * Devuelve el telefono del Usuario.
     *
     * @return telefono del usuario
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Modifica el telefono del usuario.
     *
     * @param telefono telefono del usuario.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
