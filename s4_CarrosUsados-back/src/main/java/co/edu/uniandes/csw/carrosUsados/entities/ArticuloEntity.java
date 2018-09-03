/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
public class ArticuloEntity extends BaseEntity implements Serializable {
    
    // La id del articulo
    private long id;
    
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
    @OneToOne(mappedBy = "articulo", orphanRemoval = true)
    private PagoEntity pago;
    
    // Relacion con el automovil que vende el articulo
    @PodamExclude
    @OneToOne(mappedBy = "articulo")
    private AutomovilEntity automovil;
    
    // Relacion con los clientes que desean comprar el articulo
    @PodamExclude
    @ManyToMany(mappedBy = "automovil")
    private ClienteEntity cliente;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public PagoEntity getPago() {
        return pago;
    }

    public void setPago(PagoEntity pago) {
        this.pago = pago;
    }

    public AutomovilEntity getAutomovil() {
        return automovil;
    }

    public void setAutomovil(AutomovilEntity automovil) {
        this.automovil = automovil;
    }

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }
    
    
}
