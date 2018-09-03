/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

/**
 *
 * @author estudiante
 */
public class ArticuloDTO {
    
    private long id;
    private String ubicacion;
    private String precio;
    private String descripcion;
    private String disponibilidad;

    public ArticuloDTO(long id, String ubicacion, String precio, String descripcion, String disponibilidad) {
        this.id = id;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.descripcion = descripcion;
        this.disponibilidad = disponibilidad;
    }
    
    public ArticuloDTO(){
        
    }

    public long getId() {
        return id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public String getPrecio() {
        return precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setDisponibilidad(String disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
}