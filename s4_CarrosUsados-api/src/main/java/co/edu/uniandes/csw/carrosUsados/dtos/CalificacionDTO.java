/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */
public class CalificacionDTO implements Serializable {

    private int num_estrellas;
    private String comentario;
    private PuntoVentaEntity puntoVenta;
    private Date publishedDate;
    private Long id;

    public CalificacionDTO() {
    }

    public CalificacionDTO(CalificacionEntity entityCalificacion) {
        this.num_estrellas = entityCalificacion.getNum_estrellas();
        this.comentario = entityCalificacion.getComentario();
        this.id= entityCalificacion.getId();
        this.publishedDate= entityCalificacion.getpublishedDate();
        this.puntoVenta= entityCalificacion.getPuntoVenta();
    }

    public CalificacionEntity toEntity()
    {
        CalificacionEntity calEntity= new CalificacionEntity();
        calEntity.setComentario(this.comentario);
        calEntity.setEstrellas(this.num_estrellas);
        calEntity.setId(this.id);
        calEntity.setPuntoVenta(this.puntoVenta);
        calEntity.setPublishDate(this.publishedDate);
        
        return calEntity;
    }

    public int getNum_estrellas() {
        return num_estrellas;
    }

    public void setNum_estrellas(int num_estrellas) {
        this.num_estrellas = num_estrellas;
    }
    
    public void setNumestrellas(int num) {
        this.num_estrellas = num;
    }

    public int getNumestrellas() {
        return num_estrellas;
    }

    public void setComentario(String com) {
        this.comentario = com;
    }

    public String getComentario() {
        return comentario;
    }

    public PuntoVentaEntity getPuntoVenta() {
        return puntoVenta;
    }

    public void setPuntoVenta(PuntoVentaEntity puntoVenta) {
        this.puntoVenta = puntoVenta;
    }

    public Date getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
}
