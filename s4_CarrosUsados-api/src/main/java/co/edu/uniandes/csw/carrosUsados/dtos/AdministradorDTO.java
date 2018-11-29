
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.AdministradorEntity;
import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author js.bravo
 */
/**
 * Clase Data Transfer Object que representa un administrador con privilegios en
 * el sistema.
 */
public class AdministradorDTO extends UsuarioDTO implements Serializable {

    /**
     * Cadena de caracteres que representa la fecha de inicio del cargo del
     * Administrador.
     */
    private Date fechaInicio;
    /**
     * Cadena de caracteres que representa el cargo que ejerce el Administrador.
     */
    private String cargo;
    /**
     * Lista que representa la relación ManyToMany entre Administrador y Punto de Venta.
     */
    private List<PuntoVentaDTO> puntosVenta = new ArrayList<>();
    /**
     * Constructor vacío generado por defecto.
     */
    public AdministradorDTO() {
        // Constructor por defecto
    }
    /**
     * Constructor que genera un Data Transfer Object AdministradorDTO a partir de un AdministradorEntity.
     * @param administradorEntity Entidad que representa al administrador.
     */
    public AdministradorDTO(AdministradorEntity administradorEntity) {
        super(administradorEntity);
        if (administradorEntity != null) {
            this.fechaInicio = administradorEntity.getFechaInicio();
            this.cargo = administradorEntity.getCargo();

            if (administradorEntity.getPuntosDeVenta() != null) {
                for (PuntoVentaEntity puntoVenta : administradorEntity.getPuntosDeVenta()) {
                    puntosVenta.add(new PuntoVentaDTO(puntoVenta));
                }
            }
        }
    }

    /** Transforma un Data Transfer Object a una Entidad de tipo Administrador.
     *
     * @return entidad de Administrador.
     */
    public AdministradorEntity toEntity()
    {
        AdministradorEntity entity = new AdministradorEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setTelefono(this.telefono);
        entity.setApellido(this.apellido);
        entity.setNombreUsuario(this.nombreUsuario);
        entity.setContrasena(this.contrasena);
        entity.setFechaNacimiento(this.fechaNacimiento);
        entity.setCorreo(this.correo);
        entity.setFechaInicio(this.fechaInicio);
        entity.setCargo(this.cargo);
        if (puntosVenta!= null)
        {
            List<PuntoVentaEntity> puntosVentaEntity = new ArrayList<>();
            for (PuntoVentaDTO puntoVenta : puntosVenta)
            {
                puntosVentaEntity.add(puntoVenta.toEntity());
            }
        }
        return entity;
    }

    /**
     * Devuelve la fecha de inicio del cargo del administrador.
     *
     * @return fecha de inicio del administrador.
     */
    public Date getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Modifica la fecha de incio del cargo del administrador.
     *
     * @param fechaInicio fecha de inicio del cargo del administrador.
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Devuelve el cargo del administrador.
     *
     * @return fecha de inicio del administrador.
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * Modifica el cargo del administrador.
     *
     * @param cargo cargo del administrador.
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    /**
     * Devuelve los puntos de venta del administrador.
     *
     * @return los puntos de venta del administrador.
     */
    public List<PuntoVentaDTO> getPuntosVenta() {
        return puntosVenta;
    }
    /**
     * Modifica los puntos de venta del administrador.
     *
     * @param puntosVenta  puntos de venta que tiene asociados el administrador.
     */
    public void setPuntosVenta(List<PuntoVentaDTO> puntosVenta) {
        this.puntosVenta = puntosVenta;
    }
}
