/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carros.usados.dtos;
import co.edu.uniandes.csw.carros.usados.entities.ArticuloEntity;

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
    private AutomovilDTO automovil;
    private FacturaDTO factura;

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
        this.automovil = new AutomovilDTO(articulo.getAutomovil());
        this.factura = new FacturaDTO(articulo.getFactura());
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
     * @return retorna el automovil del articulo 
     */
    public AutomovilDTO getAutomovil() {
        return automovil;
    }

    /**
     * @param automovil el nuevo automovil del articulo 
     */
    public void setAutomovil(AutomovilDTO automovil) {
        this.automovil = automovil;
    }

    /**
     * @return retorna la factura del articulo 
     */
    public FacturaDTO getFactura() {
        return factura;
    }

    /**
     * @param factura la nueva factura del articulo
     */
    public void setFactura(FacturaDTO factura) {
        this.factura = factura;
    }

    /**
     * Crea un articulo entity con los datos de este articulo.
     * @return articulo entity con los datos de este articulo.
     */
    public ArticuloEntity toEntity(){
        ArticuloEntity entity = new ArticuloEntity();
        entity.setId(id);
        if(automovil != null)
            entity.setAutomovil(automovil.toEntity());
        entity.setDescripcion(descripcion);
        entity.setDisponibilidad(true);
        if(factura != null)
            entity.setFactura(factura.toEntity());
        entity.setPrecio(precio);
        entity.setUbicacion(ubicacion);
        return entity;
    }
}
