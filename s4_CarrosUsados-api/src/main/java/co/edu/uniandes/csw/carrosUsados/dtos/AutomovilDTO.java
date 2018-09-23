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

}
