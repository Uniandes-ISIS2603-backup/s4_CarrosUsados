/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class FormaDePagoEntity extends BaseEntity implements Serializable {
    
    // El nombre de la forma de pago
    private String nombre;
    
    // El tipo de pago que se va a realizar
    private String tipo;

    /**     
     * @return retorna el nombre de la forma de pago
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre Cambie el nombre de la forma de pago
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return retorna el tipo de forma de pago
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo cambia el tipo de forma de pago 
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    
}
