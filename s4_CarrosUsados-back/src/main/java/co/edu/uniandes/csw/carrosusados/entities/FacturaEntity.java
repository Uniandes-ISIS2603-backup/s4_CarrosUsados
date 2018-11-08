/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carros.usados.entities;

import java.io.Serializable;
import javax.persistence.Entity;
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
    
    @PodamExclude
    @OneToOne
    private ArticuloEntity articulo;
    
    @PodamExclude
    @OneToOne(mappedBy = "factura")
    private FormaDePagoEntity formaDePago;

    public FormaDePagoEntity getFormaDePago() {
        return formaDePago;
    }

    public void setFormaDePago(FormaDePagoEntity formaDePago) {
        this.formaDePago = formaDePago;
    }
    
    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    
    
    
    
}
