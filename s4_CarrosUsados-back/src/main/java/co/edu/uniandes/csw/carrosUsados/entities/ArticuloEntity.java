/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class ArticuloEntity extends BaseEntity implements Serializable {
    
    // El precio del articulo
    private String precio;
    
    // Una breve descripcion del articulo para los vendedores
    private String descripcion;
    
    // El link hacia la imagen del automovil
    private String imagen;
    
    // Si el articulo esta disponible para comprar
    private boolean disponibilidad;
    
    // Relacion con el factura hecho
    @PodamExclude
    @OneToOne
    private FacturaEntity factura;
    
    // Relacion con el automovil que vende el articulo
    @PodamExclude
    @OneToOne
    private AutomovilEntity automovil;
    
    // Relacion con los clientes que desean comprar el articulo
    @PodamExclude
    @ManyToMany
    private List<ClienteEntity> clientes;

    /**
    @Return retorna el precio del carro en el articulo
    */
    public String getPrecio() {
        return precio;
    }

    /**
    @param precio Nuevo precio del carro en el articulo
    */
    public void setPrecio(String precio) {
        this.precio = precio;
    }

    /**
    @Return retorna la descripcion del articulo
    */
    public String getDescripcion() {
        return descripcion;
    }

    /**
    @param descripcion Cambia la descripcion por una nueva
    */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
    @Return retorna la imagen del articulo
    */
    public String getImagen() {
        return imagen;
    }

    /**
    @param imagen Cambia la anterior imagen por una nueva
    */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
    @Return retorna true si esta disponible, false si no lo esta y existe un factura
    */
    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    /**
    @param disponibilidad Cambia la disponibilidad del articulo
    */
    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    /**
    @Return retorna el factura un factura si disponibilidad es false, retorna null de lo contrario
    */
    public FacturaEntity getFactura() {
        return factura;
    }

    /**
    @param factura El nuevo factura que se realizo en el articulo
    */
    public void setFactura(FacturaEntity factura) {
        this.factura = factura;
    }

    /**
    @Return retorna el auto asociado al articulo
    */
    public AutomovilEntity getAutomovil() {
        return automovil;
    }

    /**
    @param automovil Cambia el automovil al entregado por parametro
    */
    public void setAutomovil(AutomovilEntity automovil) {
        this.automovil = automovil;
    }

    /**
    @Return retorna todos los clientes que estan asociados al articulo
    */
    public List<ClienteEntity> getClientes() {
        return clientes;
    }

    /**
    @param clientes Actualiza a los clientes asociados al articulo
    */
    public void setClientes(List<ClienteEntity> clientes) {
        this.clientes = clientes;
    }
    
    
}
