/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

/**
 *
 * @author js.bravo
 */
/**
 * Clase Data Transfer Object Usuario que contiene atributos que utilizan tanto
 * ClienteDTO como AdministradorDTO.
 */
public class UsuarioDTO {

    /**
     * Cadena de caracteres que representa el id del usuario en el sistema.
     */
    private Long id;
    /**
     * Cadena de caracteres que representa el nombre del usuario en el sistema.
     */
    private String nombre;
    /**
     * Cadena de caracteres que representa el apellido del usuario en el
     * sistema.
     */
    private String apellido;
    /**
     * Cadena de caracteres que representa el correo del usuario en el sistema.
     */
    private String correo;
    /**
     * Cadena de caracteres que representa la contraseña del usuario en el
     * sistema.
     */
    private String contrasena;
    /**
     * Cadena de caracteres que representa el login del usuario en el sistema.
     */
    private String nombre_usuario;
    /**
     * Cadena de caracteres que representa la fecha de nacimiento del usuario en
     * el sistema.
     */
    private String fecha_nacimiento;
    /**
     * Cadena de caracteres que representa el telefono de contacto del usuario
     * en el sistema.
     */
    private String telefono;

    /**
     * Devuelve el ID del usuario.
     *
     * @return id del Usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID del usuario.
     *
     * @param id id del Usuario.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del Usuario.
     *
     * @return nombre del Usuario
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del usuario.
     *
     * @param nombre nuevo nombre del usuario..
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve el apellido del Usuario.
     *
     * @return nombre del usuario.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Modifica el apellido del usuario..
     *
     * @param apellido apellido del Usuario.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
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
     * @return contraasena del usuario
     */
    public String getContrasena() {
        return contrasena;
    }

    /**
     * Modifica la contrasena del usuario..
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
    public String getNombre_Usuario() {
        return nombre_usuario;
    }

    /**
     * Modifica el login del usuario..
     *
     * @param nombre_usuario El login del usuario.
     */
    public void setNombre_Usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    /**
     * Devuelve la fecha de nacimiento del usuario.
     *
     * @return fecha de nacimiento del usuario
     */
    public String getFecha_Nacimiento() {
        return fecha_nacimiento;
    }

    /**
     * Modifica la fecha de nacimiento del usuario..
     *
     * @param fecha_nacimiento fecha de nacimiento del usuario.
     */
    public void setFecha_Nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /**
     * Devuelve el telefono del Usuario.
     *
     * @return telefono del usuario
     */
    public String getTelefono() {
        return contrasena;
    }

    /**
     * Modifica el telofono del usuario..
     *
     * @param telefono telefono del usuario.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
