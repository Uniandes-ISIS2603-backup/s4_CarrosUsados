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
public class VendedorEntity extends BaseEntity implements Serializable  {
     /**
     * Nombre del vendedor.
     */
    private String nombre;
    /**
     * Apellido del vendedor.
     */
    private String apellido;
    
     /**
     * Retorna el nombre del vendedor.
     *
     * @return nombre del vendedor.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Retorna el nombre del vendedor.
     *
     * @param nombre - nombre del vendedor.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el apellido del vendedor.
     *
     * @return apellido del vendedor.
     */
    public String getApellido() {
        return apellido;
    }

    /**
     * Asigna el apellido del vendedor.
     *
     * @param apellido - apellido del vendedor.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
