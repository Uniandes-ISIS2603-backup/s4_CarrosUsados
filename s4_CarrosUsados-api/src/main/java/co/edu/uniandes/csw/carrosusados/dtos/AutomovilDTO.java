/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.dtos;

import co.edu.uniandes.csw.carrosusados.entities.AutomovilEntity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class AutomovilDTO implements Serializable {

    private Long id;
    private String modelo;
    private String marca;
    private int anio;
    private String color;
    private String numChasis;
    private String placa;
    private Date fechaAgregacion; //Chequear el tipo de dato
    private String precioOriginal;

    //Relacion a FichaTecnicaDTO dado que esta tiene cardinalidad 1
    private FichaTecnicaDTO fichaTecnica;

    //Relacion a PuntoVentaDTO dado que esta tiene cardinalidad 1
    private PuntoVentaDTO puntoVenta;
    
    //Relacion a ModeloDTO que tiene cardinalidad 1
    private ModeloDTO modeloAsociado;
    
    public AutomovilDTO() {

    }

    public AutomovilDTO(AutomovilEntity automovilEntity) {
        this.id = automovilEntity.getId();
        this.modelo = automovilEntity.getModelo();
        this.marca = automovilEntity.getMarca();
        this.anio = automovilEntity.getAnio();
        this.color = automovilEntity.getColor();
        this.numChasis = automovilEntity.getNumChasis();
        this.placa = automovilEntity.getPlaca();
        this.fechaAgregacion = automovilEntity.getFechaAgregacion();
        this.precioOriginal = automovilEntity.getPrecioOriginal();

        if (automovilEntity.getFichaTecnica() != null) {
            this.fichaTecnica = new FichaTecnicaDTO(automovilEntity.getFichaTecnica());
        }
        if (automovilEntity.getPuntoVenta() != null) {
            this.puntoVenta = new PuntoVentaDTO(automovilEntity.getPuntoVenta());
        }
    }

    public AutomovilEntity toEntity() {
        AutomovilEntity entity = new AutomovilEntity();

        entity.setId(this.id);
        entity.setModelo(this.modelo);
        entity.setMarca(this.marca);
        entity.setAnio(this.anio);
        entity.setColor(this.color);
        entity.setNumChasis(this.numChasis);
        entity.setPlaca(this.placa);
        entity.setFechaAgregacion(this.fechaAgregacion);
        entity.setPrecioOriginal(this.precioOriginal);

        if (this.fichaTecnica != null) {
            entity.setFichaTecnica(this.fichaTecnica.toEntity());
        }
        if(this.puntoVenta != null){
            entity.setPuntoVenta(this.puntoVenta.toEntity());
        }
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
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

    public String getNumChasis() {
        return numChasis;
    }

    public void setNumChasis(String numChasis) {
        this.numChasis = numChasis;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Date getFechaAgregacion() {
        return fechaAgregacion;
    }

    public void setFechaAgregacion(Date fechaAgregacion) {
        this.fechaAgregacion = fechaAgregacion;
    }

    public String getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(String precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public FichaTecnicaDTO getFichaTecnica() {
        return fichaTecnica;
    }

    public void setFichaTecnica(FichaTecnicaDTO fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    public PuntoVentaDTO getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(PuntoVentaDTO puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public ModeloDTO getModeloAsociado() {
        return modeloAsociado;
    }

    public void setModeloAsociado(ModeloDTO modeloAsociado) {
        this.modeloAsociado = modeloAsociado;
    }
    
    
    
}
