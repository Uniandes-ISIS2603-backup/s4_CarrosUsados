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
     * Clase Data Transfer Object Vendedor que representa el vendedor de un automovil en el sistema.
     */
public class VendedorDTO implements Serializable{
 /**
     * Cadena de caracteres que representa el id del usuario en el sistema.
     */
    private Long id;
    /**
     * Cadena de caracteres que representa el nombre del usuario en el sistema.
     */
    private String nombre;
    /**
     * Cadena de caracteres que representa el apellido del usuario en el sistema.
     */
    private String apellido;
    /**
     * Cadena de caracteres que representa el correo del usuario en el sistema.
     */    
    /**
     * Constructor por defecto
     */
    public VendedorDTO() {
    }
     /**
     * Devuelve el ID del vendedor.
     *
     * @return  id del vendedor.
     */
    public Long getId() {
        return id;
    }

    /**
     * Modifica el ID del vendedor.
     *
     * @param id id del vendedor.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Devuelve el nombre del vendedor.
     *
     * @return nombre del vendedor
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Modifica el nombre del vendedor.
     *
     * @param nombre nuevo nombre del vendedor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Devuelve el apellido del vendedor.
     *
     * @return nombre del vendedor.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Modifica el apellido del vendedor.
     *
     * @param apellido apellido del vendedor.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    
}
