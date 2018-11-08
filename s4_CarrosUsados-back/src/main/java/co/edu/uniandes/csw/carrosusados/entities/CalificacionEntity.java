/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.entities;

import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa una calificación en la persistencia y permite su
 * serialización.
 * @author Daniella Arteaga
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable{
    
    
    private int numEstrellas;
    private String comentario;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date publishDate;
    
    @PodamExclude
    @ManyToOne
    private PuntoVentaEntity puntoVenta;
    
    @PodamExclude
    @ManyToOne
    private AutomovilEntity automovil;
    
    
    /**
     * Constructor de entidad.
     */
    public CalificacionEntity()
    {
        
    }
    
    /**
     *  Obtiene punto de venta de la calificación
     * @return puntoVenta de la calificación
     */
    public PuntoVentaEntity getPuntoVenta()
    {
        return puntoVenta;
    }
    
    /**
     * Modifica el punto de venta de la calificación
     * @param puntoEntity punto que se califica.
     */
    public void setPuntoVenta(PuntoVentaEntity puntoEntity)
    {
        this.puntoVenta=puntoEntity;
    }
    
    /**
     *  Retorna número de estrellas asignado a la calificación.
     * @return número de estrellas de calificación.
     */
    public int getEstrellas()
    {
         return numEstrellas;   
    }
    
    /**
     * Modifica número de estrellas de la calificación.
     * @param num número de estrellas a reasignar de la calificación.
     */
    public void setEstrellas(int num)
            
    {
        this.numEstrellas=num;
    }
    
    /**
     * Devuelve comentario de la calificación. 
     * @return comentario asignado a la calificación.
     */
    public String getComentario()
    {
        return comentario;
    }
    
    /**
     * Modifica comentario de la calificación.
     * @param com comentario que se va a asignar a la calificación.
     */
    public void setComentario(String com)
    {  
        this.comentario=com;
                   
    }
    
    /**
     * Devuelve fecha de publicación de la calificación.
     * @return publishDate fecha de la publicación.
     */
    public Date getpublishedDate()
    {
        return publishDate;
    }
    
    /**
     * Modifica la fecha de publicación.
     * @param newDate nueva fecha a asignar.
     */
    public void setPublishDate(Date newDate)
    {
        this.publishDate=newDate;
    }


    /**
     *  Devuelve auto de la calificación.
     * @return automovil de la calificación a devolver.
     */
    public AutomovilEntity getAutomovil() {
        return automovil;
    }

    /**
     *  Modifica el carro asignado a la calificación.
     * @param automovil entidad del auto con el que se va a modificar.
     */
    public void setAutomovil(AutomovilEntity automovil) {
        this.automovil = automovil;
    }

    
    
}
