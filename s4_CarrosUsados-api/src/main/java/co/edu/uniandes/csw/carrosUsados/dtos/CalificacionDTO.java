/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;

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

    private int numEstrellas;
    private String comentario;
    private PuntoVentaDTO puntoVenta;
    private Date publishedDate;
    private Long id;

    /**
     * Constructor
     */
    public CalificacionDTO() {
        //este constructor es dejado vacìo intencionalmente
    }

     /**
     * Conviertir Entity a DTO (Crea un nuevo DTO con los valores que recibe en
     * la entidad que viene de argumento.
     *
     * @param entityCalificacion Entity: Es la entidad que se va a convertir a DTO
     */
    public CalificacionDTO(CalificacionEntity entityCalificacion) {
       if(entityCalificacion!=null)
       {
        this.numEstrellas = entityCalificacion.getEstrellas();
        this.comentario = entityCalificacion.getComentario();
        this.id= entityCalificacion.getId();
        this.publishedDate= entityCalificacion.getpublishedDate();
        
        if(entityCalificacion.getPuntoVenta()!=null)
        {
        this.puntoVenta=  new PuntoVentaDTO(entityCalificacion.getPuntoVenta());
    }}
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
        calEntity.setEstrellas(this.numEstrellas);
        calEntity.setId(this.id);
        
        calEntity.setPublishDate(this.publishedDate);
        
        if(this.puntoVenta!=null)
        {
            calEntity.setPuntoVenta(this.puntoVenta.toEntity());
        }
        
        return calEntity;
    }

    /**
     * Devuelve número de estrellas de la calificación.
     * @return numEstrellas número de estrellas de calificación.
     */
    public int getNum_estrellas() {
        return numEstrellas;
    }

 /**
     * Modifica el número de estrellas de la calificación.
     *
     * @param num nuevo número de estrellas de la calificación.
     */
    public void setNum_estrellas(int num) {
        this.numEstrellas = num;
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
    public PuntoVentaDTO getPuntoVenta() {
        return puntoVenta;
    }

/**
     * Modifica el nombre de la calificación
     *
     * @param puntoVenta punto nuevo a asignar
     */
 
    public void setPuntoVenta(PuntoVentaDTO puntoVenta) {
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
     * Modifica fecha de la calificación
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
