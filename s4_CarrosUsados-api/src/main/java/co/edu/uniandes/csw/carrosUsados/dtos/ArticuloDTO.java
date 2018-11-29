/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;
import co.edu.uniandes.csw.carrosUsados.entities.ArticuloEntity;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class ArticuloDTO implements Serializable {

    /**
     * el id del articulo
     */
    private long id;
    /**
     * el id del autovil que llega del servidor una vez para poder buscarlo
     */
    private long idAuto;
    /**
     * el id del modelo del automovil que llega del servidor una vez para poder buscar el automovil
     */
    private long idMod;
    /**
     * la url de la imagen del articulo
     */
    private String imagen;
    /**
     * el precio del articulo
     */
    private String precio;
    /**
     * la descripcion del articulo
     */
    private String descripcion;
    /**
     * la disponibilidad del articulo
     */
    private boolean disponibilidad;
    /**
     * el automovil del articulo
     */
    private AutomovilDTO automovil;
    /**
     * la factura del articulo
     */
    private FacturaDTO factura;

    /**
     * Crea un articuloDTO
     * @param articulo entidad con la cual se va a crear el articulo
     */
    public ArticuloDTO(ArticuloEntity articulo) {
        this.id = articulo.getId();
        this.precio = articulo.getPrecio();
        this.descripcion = articulo.getDescripcion();
        this.disponibilidad = articulo.isDisponibilidad();
        if(articulo.getAutomovil() != null)
            this.automovil = new AutomovilDTO(articulo.getAutomovil());
        if(articulo.getFactura() != null)
            this.factura = new FacturaDTO(articulo.getFactura());
        this.imagen = articulo.getImagen();
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
     * @return retorna la id del auto del articulo 
     */
    public long getIdAuto() {
        return idAuto;
    }
    
    /**
     * @param idAuto nueva id del automovil del articulo 
     */
    public void setIdAuto(long idAuto) {
        this.idAuto = idAuto;
    }
    
    /**
     * @return retorna la id del modelo del automovil del articulo 
     */
    public long getIdModelo() {
        return idMod;
    }
    
    /**
     * @param idMod nueva id del modelo del automovil del articulo 
     */
    public void setIdMod(long idMod) {
        this.idMod = idMod;
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
     * @return retorna la imagen del articulo 
     */
    public String getImagen() {
        return imagen;
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
     * @param imagen cambia la imagen del articulo
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
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
        entity.setImagen(imagen);
        return entity;
    }
}
