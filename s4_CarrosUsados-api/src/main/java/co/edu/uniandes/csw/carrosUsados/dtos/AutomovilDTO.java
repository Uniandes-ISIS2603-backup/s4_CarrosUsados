/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class AutomovilDTO implements Serializable {

    /**
     * El id del automovil
     */
    private Long id;

    //private String modelo;
    /**
     * La marca del automovil
     */
    private String marca;
    /**
     * El año del automovil
     */
    private int anio;
    /**
     * El color del automovil
     */
    private String color;
    /**
     * El numero de chasis del automovil
     */
    private String numChasis;
    /**
     * La placa del automovil
     */
    private String placa;
    /**
     * La fecha de agregacion del automovil
     */
    private Date fechaAgregacion; //Chequear el tipo de dato
    /**
     * El precio original del automovil
     */
    private String precioOriginal;

    //Relacion a FichaTecnicaDTO dado que esta tiene cardinalidad 1
    private FichaTecnicaDTO fichaTecnica;

    //Relacion a PuntoVentaDTO dado que esta tiene cardinalidad 1
    private PuntoVentaDTO puntoVenta;
    
    //Relacion a ModeloDTO que tiene cardinalidad 1
    private ModeloDTO modeloAsociado;
    
    public AutomovilDTO() {

    }

    /**
     * Constructor del dto que recibe los datos en una entidad
     * @param automovilEntity La entidad que contiene los datos
     */
    public AutomovilDTO(AutomovilEntity automovilEntity) {
        this.id = automovilEntity.getId();
        //this.modelo = automovilEntity.getModelo();
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

    /**
     * Metodo que convierte el DTO en una entidad
     * @return La entidad automovil
     */
    public AutomovilEntity toEntity() {
        AutomovilEntity entity = new AutomovilEntity();

        entity.setId(this.id);
        //entity.setModelo(this.modelo);
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

    /**
     * Metodo que retorna el id del automovil
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Metodo que cambia el id del automovil
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }
    /*
    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }*/

    /**
     * Metodo que retorna la marca del automovil
     * @return La marca del automovil
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Metodo que cambia la marca del automovil
     * @param marca
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * Metodo que retorn el año del automovil
     * @return El año del automovil
     */
    public int getAnio() {
        return anio;
    }

    /**
     * Metodo que cambia el año del automovil
     * @param anio El año del automovil
     */
    public void setAnio(int anio) {
        this.anio = anio;
    }

    /**
     * Metodo que retorn el color del automovil
     * @return El color del autmovil
     */
    public String getColor() {
        return color;
    }

    /**
     * Metodo que cambia el color del automocil
     * @param color El color del automovil
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Metodo que retorna el numero de chasis del carro
     * @return El numero de chasis del carro
     */
    public String getNumChasis() {
        return numChasis;
    }

    /**
     * Metodo que cambia el numero de chasis del carro
     * @param numChasis El numero de chasis del carro
     */
    public void setNumChasis(String numChasis) {
        this.numChasis = numChasis;
    }

    /**
     * Metodo que retorna la placa del automovil
     * @return La placa del automovil
     */
    public String getPlaca() {
        return placa;
    }

    /**
     * Metodo que cambia la placa del automovil
     * @param placa La placa del automovil
     */
    public void setPlaca(String placa) {
        this.placa = placa;
    }

    /**
     * Metodo que retorna la fecha de agregacion del automovil
     * @return La fecha de agregacion
     */
    public Date getFechaAgregacion() {
        return fechaAgregacion;
    }

    /**
     * Metodo que cambia la fecha de agregacion dela automovil
     * @param fechaAgregacion La fecha de agregacion
     */
    public void setFechaAgregacion(Date fechaAgregacion) {
        this.fechaAgregacion = fechaAgregacion;
    }

    /**
     * Metodo que retorna el precio original del automovil
     * @return El precio original del automovil
     */
    public String getPrecioOriginal() {
        return precioOriginal;
    }

    /**
     * Metodo que cambia el precio original del automovil
     * @param precioOriginal El precio del automovil
     */
    public void setPrecioOriginal(String precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    /**
     * Metodoq que retorna la ficha tecnica asociada al automovil
     * @return La ficha tecnica
     */
    public FichaTecnicaDTO getFichaTecnica() {
        return fichaTecnica;
    }

    /**
     * Metodo que cambia la ficha tecnica asociada al automovil
     * @param fichaTecnica La ficha tecnica del automovil
     */
    public void setFichaTecnica(FichaTecnicaDTO fichaTecnica) {
        this.fichaTecnica = fichaTecnica;
    }

    /**
     * Metodo que retorna el punto de venta asociado al automovil
     * @return El punto de venta
     */
    public PuntoVentaDTO getPuntoVenta() {
        return puntoVenta;
    }

    /**
     * Metodo que cambia el punto de venta asociado al automovil
     * @param puntoVenta El punto de venta del automovil
     */
    public void setPuntoVenta(PuntoVentaDTO puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    /**
     * Metodo que retorna el modelo asociado al automovil
     * @return El modelo del automovil
     */
    public ModeloDTO getModeloAsociado() {
        return modeloAsociado;
    }

    /**
     * Metodo que cambia el modelo asociado al automovil
     * @param modeloAsociado El modelo asociado al automovil
     */
    public void setModeloAsociado(ModeloDTO modeloAsociado) {
        this.modeloAsociado = modeloAsociado;
    }
    
    
    
}
