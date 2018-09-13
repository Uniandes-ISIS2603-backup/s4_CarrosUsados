/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class PagoEntity extends BaseEntity implements Serializable{
    private String idPago;
    private String num_tarjeta;
    private String codigoTarjeta;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaTarjeta;
    
    private boolean comprobante_pago;

    
    @PodamExclude
    @OneToOne
    private FacturaEntity factura;
    
    @PodamExclude
    @OneToOne
    private ArticuloEntity articulo;

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }
  
  /*
    @PodamExclude
    @OneToOne
    private PagoEntity articulo;
    */

    public FacturaEntity getFactura() {
        return factura;
    }

    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    public String getIdPago()
    {
        return idPago;
    }
    public void setIdPago(String pIdPago)
    {
        idPago=pIdPago;
    }
    public String getNum_targeta()
    {
        return num_tarjeta;
    }
    public void setNum_tergeta(String pNumero)
    {
        num_tarjeta=pNumero;
    }
    public String getCodigoTargeta()
    {
        return codigoTarjeta;
    }
    public void setCodigoTargeta(String pCodigo)
    {
        codigoTarjeta=pCodigo;
    }
    public Date getFechaTarjeta()
    {
        return fechaTarjeta;
    }
    public void setFechaTarjeta(Date pFecha)
    {
        fechaTarjeta= pFecha;
    }
    public boolean getComprobanteDePago()
    {
        return comprobante_pago;
    }
    public void setComprobanteDePago(boolean comprobante)
    {
        comprobante_pago=comprobante;
    }
/*
    public PagoEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(PagoEntity articulo) {
        this.articulo = articulo;
    }
    */
    
}
