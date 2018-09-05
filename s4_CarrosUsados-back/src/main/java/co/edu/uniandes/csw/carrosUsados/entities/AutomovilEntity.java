/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class AutomovilEntity extends BaseEntity implements Serializable {

    private int modelo;
    private String marca;
    private int anio;
    private String color;
    private String num_chasis;
    private String placa;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_agregacion; //Chequear el tipo de dato
    private String precio_original;

    //Relacion a FichaTecnicaDTO dado que esta tiene cardinalidad 
    @PodamExclude
    @OneToOne(mappedBy = "automovil")
    private FichaTecnicaEntity ficha_tecnica;

    //Falta agregar PuntoVenta y Calificacion
    @PodamExclude
    @OneToOne
    private PuntoVentaEntity punto_venta;

    @PodamExclude
    @OneToMany(mappedBy = "automovil", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalificacionEntity> calificaciones;
    
    @PodamExclude
    @OneToOne(mappedBy = "automovil")
    private CalificacionEntity articulo;

    @PodamExclude
    @ManyToOne
    private VendedorEntity vendedor;

    public int getModelo() {
        return modelo;
    }

    public void setModelo(int modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNum_chasis() {
        return num_chasis;
    }

    public void setNum_chasis(String num_chasis) {
        this.num_chasis = num_chasis;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getFecha_agregacion() {
        return fecha_agregacion;
    }

    public void setFecha_agregacion(Date fecha_agregacion) {
        this.fecha_agregacion = fecha_agregacion;
    }

    public String getPrecio_original() {
        return precio_original;
    }

    public void setPrecio_original(String precio_original) {
        this.precio_original = precio_original;
    }

    public FichaTecnicaEntity getFicha_tecnica() {
        return ficha_tecnica;
    }

    public void setFicha_tecnica(FichaTecnicaEntity ficha_tecnica) {
        this.ficha_tecnica = ficha_tecnica;
    }

    public List<CalificacionEntity> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(List<CalificacionEntity> calificaciones) {
        this.calificaciones = calificaciones;
    }

    public PuntoVentaEntity getPunto_venta() {
        return punto_venta;
    }

    public void setPunto_venta(PuntoVentaEntity punto_venta) {
        this.punto_venta = punto_venta;
    }

    public VendedorEntity getVendedor() {
        return vendedor;
    }

    public void setVendedor(VendedorEntity vendedor) {
        this.vendedor = vendedor;
    }

}
