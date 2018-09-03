/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class CalificacionEntity extends BaseEntity implements Serializable{
    
    
    private int num_estrellas;
    private String comentario;
    
    @Temporal(TemporalType.DATE)
    private Date publishDate;
    
    @PodamExclude
    @ManyToOne
    private PuntoVentaEntity puntoVenta;
    
    public CalificacionEntity()
    {
        
    }
    
    public PuntoVentaEntity getPuntoVenta()
    {
        return puntoVenta;
    }
    
    public void setPuntoVenta(PuntoVentaEntity puntoEntity)
    {
        this.puntoVenta=puntoEntity;
    }
    
    public int getEstrellas()
    {
         return num_estrellas;   
    }
    
    public void setEstrellas(int num)
            
    {
        this.num_estrellas=num;
    }
    
    
    
    public String getComentario()
    {
        return comentario;
    }
    
    public void setComentario(String com)
    {  
        this.comentario=com;
                   
    }
    
    public Date getpublishedDate()
    {
        return publishDate;
    }
    
    public void setPublishDate(Date newDate)
    {
        this.publishDate=newDate;
    }
}
