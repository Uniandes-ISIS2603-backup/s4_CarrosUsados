/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.entities;

import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */
@Entity
public class FormaDePagoEntity extends BaseEntity implements Serializable {
    
    // El nombre de la forma de pago
    private String nombre;
    
    // El tipo de tarjeta que se usa en el pago que se va a realizar
    private String tipoTar;
    
    // El numero de la tarjeta 
    private String numeroTar;
    
    // El codigo de la tarjeta
    private String codigoTar;
    
    // La fecha de la tarjeta
    private Date fechaTar;
    
    @PodamExclude
    @OneToOne
    private FacturaEntity factura;

    /**     
     * @return retorna la factura de la forma de pago
     */
    public FacturaEntity getFactura() {
        return factura;
    }

    /**
     * @param factura Cambie la factura de la forma de pago
     */
    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }
    
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
        return tipoTar;
    }

    /**
     * @param tipo cambia el tipo de la tarjeta de forma de pago 
     */
    public void setTipo(String tipo) {
        this.tipoTar = tipo;
    }
    
    /**     
     * @return retorna el numero de la tarjeta de la forma de pago
     */
    public String getNumero() {
        return numeroTar;
    }

    /**
     * @param numero Cambia el numero de la tarjeta de la forma de pago
     */
    public void setNumero(String numero) {
        this.numeroTar = numero;
    }

    /**
     * @return retorna el codigo de la tarjeta de la forma de pago
     */
    public String getCodigo() {
        return codigoTar;
    }

    /**
     * @param codigo cambia el codigo de la tarjeta de la forma de pago 
     */
    public void setCodigo(String codigo) {
        this.codigoTar = codigo;
    }
    
    /**     
     * @return retorna la fecha de la tarjeta de la forma de pago
     */
    public Date getFecha() {
        return fechaTar;
    }

    /**
     * @param fecha cambia la fecha de la tarjeta de la forma de pago
     */
    public void setFecha(Date fecha) {
        this.fechaTar = fecha;
    }
    
}
