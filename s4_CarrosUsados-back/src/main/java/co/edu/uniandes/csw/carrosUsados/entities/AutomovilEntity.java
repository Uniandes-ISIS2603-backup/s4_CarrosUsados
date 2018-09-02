/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class AutomovilEntity extends BaseEntity implements Serializable {
    
    private long id;
    private int modelo;
    private String marca;
    private int anio;
    private String color;
    private String num_chasis;
    private String placa;
    //Chequear si toca agregar lo de TemporalType
    private Date fecha_agregacion; //Chequear el tipo de dato
    private String precio_original;
    
    //Relacion a FichaTecnicaDTO dado que esta tiene cardinalidad 
    @PodamExclude
    @OneToOne(mappedBy = "automovil", orphanRemoval = true)
    private FichaTecnicaEntity ficha_tecnica;
    
    
    //Falta agregar PuntoVenta y Calificacion
    
}
