/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class FacturaEntity extends BaseEntity implements Serializable{
    private String idFactura;
    private String producto;
    private Integer subtotal;
    private Integer total;
    private String formaDePago;
    private String descripcion;
    
    @PodamExclude
    @ManyToOne
    private ClienteEntity cliente;

    
    
    /**
     * Retorna la descripción del producto
     * @return String descirpcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(String formaDePago) {
        this.formaDePago = formaDePago;
    }
    public String getIdFactura() {
        return idFactura;
    }
    public void setIdFactura(String id) {
        this.idFactura = id;
    }
    public String getProducto() {
        return producto;
    }
    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Integer getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Integer subtotal) {
        this.subtotal = subtotal;
    }

    /**
     * Retorna el precio total de los productos en facturados
     * @return Precio total de los productos
     */
    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    
    
    
    
}
