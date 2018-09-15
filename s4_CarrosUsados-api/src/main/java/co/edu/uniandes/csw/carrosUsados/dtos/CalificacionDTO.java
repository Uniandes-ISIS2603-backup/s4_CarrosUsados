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
 *  Objeto de transferencia de calificaciones, Los DTO
 * contienen las representaciones de los JSON que se transfieren entre el
 * cliente y el servidor.
 * @author Daniella Arteaga
 */
public class CalificacionDTO implements Serializable {

    private int num_estrellas;
    private String comentario;
    private PuntoVentaEntity puntoVenta;
    private Date publishedDate;
    private Long id;

    /**
     * Constructor
     */
    public CalificacionDTO() {
    }

     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param entityCalificacion Entity: Es la entidad que se va a convertir a DTO
     */
    public CalificacionDTO(CalificacionEntity entityCalificacion) {
        this.num_estrellas = entityCalificacion.getNum_estrellas();
        this.comentario = entityCalificacion.getComentario();
        this.id= entityCalificacion.getId();
        this.publishedDate= entityCalificacion.getpublishedDate();
        this.puntoVenta= entityCalificacion.getPuntoVenta();
    }


    /**
     * Convertir DTO a Entity
     *
     * @return Un Entity con los valores del DTO
     */
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

    /**
     * Devuelve número de estrellas de la calificación.
     * @return num_estrellas número de estrellas de calificación.
     */
    public int getNum_estrellas() {
        return num_estrellas;
    }

 /**
     * Modifica el número de estrellas de la calificación.
     *
     * @param num_estrellas nuevo número de estrellas de la calificación.
     */
    public void setNum_estrellas(int num_estrellas) {
        this.num_estrellas = num_estrellas;
    }

  /**
     * Modifica el comentario de la calificacion.
     *
     * @param com el nuevo comentario de la calificacion.
     */
    public void setComentario(String com) {
        this.comentario = com;
    }

   /**
     * Devuelve el comentario de la calificacion.
     *
     * @return el comentario de la calificacion.
     */
    public String getComentario() {
        return comentario;
    }

       /**
     * Devuelve el nombre de la calificación
     *
     * @return puntoVenta el punto de venta de la calificación.
     */
    public PuntoVentaEntity getPuntoVenta() {
        return puntoVenta;
    }

/**
     * Modifica el nombre de la calificación
     *
     * @param puntoVenta punto nuevo a asignar
     */
 
    public void setPuntoVenta(PuntoVentaEntity puntoVenta) {
        this.puntoVenta = puntoVenta;
    }
    /**
     * Devuelve el ID de la calificación
     *
     * @return publishedDate fecha de publicación.
     */
    public Date getPublishedDate() {
        return publishedDate;
    }

     /**
     * Modifica el nombre de la calificación
     *
     * @param publishedDate nuevo fecha de publicación.
     */
  
    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }

    /**
     * Devuelve el ID de la calificación
     *
     * @return id el id de la calificación
     */
    public Long getId() {
        return id;
    }
 
    
    /**
     * Modifica el ID de la calificación
     *
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    
}
