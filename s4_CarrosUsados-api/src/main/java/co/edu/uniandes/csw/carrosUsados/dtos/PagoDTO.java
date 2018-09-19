/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.PagoEntity;
import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class PagoDTO implements Serializable {

    public String getIdPago() {
        return idPago;
    }

    public void setIdPago(String idPago) {
        this.idPago = idPago;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public String getCodigoTarjeta() {
        return codigoTarjeta;
    }

    public void setCodigoTarjeta(String codigoTarjeta) {
        this.codigoTarjeta = codigoTarjeta;
    }

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    private String idPago;
    private String num_tarjeta;
    private String codigoTarjeta;
    public PagoDTO() {

    }
     public PagoDTO(PagoEntity pagoEntity) {
         this.codigoTarjeta = pagoEntity.getCodigoTargeta();
         this.idPago = pagoEntity.getIdPago();
         this.num_tarjeta = pagoEntity.getNum_targeta();

    }

}
