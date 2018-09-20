/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;
import co.edu.uniandes.csw.carrosUsados.entities.ArticuloEntity; 

/**
 *
 * @author estudiante
 */
public class ArticuloDTO {

    private long id;
    private String ubicacion;
    private String precio;
    private String descripcion;
    private boolean disponibilidad;

    /**
     * Crea un articuloDTO
     * @param articulo entidad con la cual se va a crear el articulo
     */
    public ArticuloDTO(ArticuloEntity articulo) {
        this.id = articulo.getId();
        this.ubicacion = articulo.getUbicacion();
        this.precio = articulo.getPrecio();
        this.descripcion = articulo.getDescripcion();
        this.disponibilidad = articulo.isDisponibilidad();
    }

    /**
     * Crea un articulo vacio
     */
    public ArticuloDTO() {

    }

    /**
     * @return retorna la id del articulo 
     */
    public long getId() {
        return id;
    }

    /**
     * @return retorna la ubicacion del articulo 
     */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
     * @return retorna el precio del articulo 
     */
    public String getPrecio() {
        return precio;
    }
    
    /**
     * @return retorna la descripcion del articulo 
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @return retorna la disponibilidad del articulo 
     */
    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    /**
     * @param id nueva id del articulo 
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @param ubicacion nueva ubicacion del articulo 
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    /**
     * @param precio nuevo precio del articulo 
     */
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /**
     * @param descripcion nueva descripcion del articulo 
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @param disponibilidad cambia la disponibilidad del articulo
     */
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
     * Crea un articulo entity con los datos de este articulo.
     * @return articulo entity con los datos de este articulo.
     */
    public ArticuloEntity toEntity(){
        ArticuloEntity entity = new ArticuloEntity();
        entity.setId(id);
        entity.setAutomovil(null);
        entity.setClientes(null);
        entity.setDescripcion(descripcion);
        entity.setDisponibilidad(true);
        entity.setPago(null);
        entity.setPrecio(precio);
        entity.setUbicacion(ubicacion);
        return entity;
    }
}
