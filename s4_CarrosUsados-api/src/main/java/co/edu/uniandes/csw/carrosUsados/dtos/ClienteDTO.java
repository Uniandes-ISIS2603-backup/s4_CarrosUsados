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
     * Clase Data Transfer Object que representa un Cliente o prospecto de compra en el sistema.
     */
public class ClienteDTO extends UsuarioDTO implements Serializable {

    /**
     * Cadena de caracteres que representa la direcciòn de residencia del cliente.
     */
    private String direccion;
    
    /**
     * Constructor por defecto
     */
    public ClienteDTO() {
    }
   /**
     * Devuelve la  direccion del cliente.
     *
     * @return direccion del usuario
     */
    public String getDireccion() {
        return direccion;
    } 
      /**
     * Modifica la dirección del cliente.
     *
     * @param direccion  direccion del cliente.
     */
    public void setDireccion(String direccion)
    {
        this.direccion = direccion;
    }

}
