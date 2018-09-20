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
    
    // La ubicacion del articulo
    private String ubicacion;
    
    // El precio del articulo
    private String precio;
    
    // Una breve descripcion del articulo para los vendedores
    private String descripcion;
    
    // Si el articulo esta disponible para comprar
    private boolean disponibilidad;
    
    // Relacion con el pago hecho
    @PodamExclude
    @OneToOne(mappedBy = "articulo")
    private PagoEntity pago;
    
    // Relacion con el automovil que vende el articulo
    @PodamExclude
    @OneToOne(mappedBy = "articulo")
    private AutomovilEntity automovil;
    
    // Relacion con los clientes que desean comprar el articulo
    @PodamExclude
    @ManyToMany
    private List<ClienteEntity> clientes;

    /**
    @Return retorna la ubicacion del carro
    */
    public String getUbicacion() {
        return ubicacion;
    }

    /**
    @param ubicacion Nueva ubicacion a ser reemplazada
    */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

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
    @Return retorna true si esta disponible, false si no lo esta y existe un pago
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
    @Return retorna el pago un pago si disponibilidad es false, retorna null de lo contrario
    */
    public PagoEntity getPago() {
        return pago;
    }

    /**
    @param pago El nuevo pago que se realizo en el articulo
    */
    public void setPago(PagoEntity pago) {
        this.pago = pago;
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
    @param cliente Actualiza a los clientes asociados al articulo
    */
    public void setClientes(List<ClienteEntity> cliente) {
        this.clientes = cliente;
    }
    
    
}
