/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.ClienteEntity;
import co.edu.uniandes.csw.carrosUsados.entities.FormaDePagoEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author js.bravo
 */
/**
 * Clase Data Transfer Object que representa un Cliente o prospecto de compra en
 * el sistema.
 */
public class ClienteDTO extends UsuarioDTO implements Serializable {

    /**
     * Cadena de caracteres que representa la direcciòn de residencia del
     * cliente.
     */
    private String direccion;
    /**
     * Lista que representa la relación OneToMany entre Cliente y Forma De Pago.
     */
    private List<FormaDePagoDTO> formasDePago = new ArrayList<>();

    /**
     * Constructor por defecto
     */
    public ClienteDTO() {
        //Constructor por defecto.
    }

    /** Transforma un Data Transfer Object a una Entidad de tipo Cliente.
     *
     * @return entidad de Cliente.
     */
    public ClienteEntity toEntity()
    {
        ClienteEntity entity = new ClienteEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setApellido(this.apellido);
        entity.setTelefono(this.telefono);
        entity.setNombreUsuario(this.nombreUsuario);
        entity.setContrasena(this.contrasena);
        entity.setFechaNacimiento(this.fechaNacimiento);
        entity.setCorreo(this.correo);
entity.setDireccion(this.direccion);
        return entity;
    }

    /**
     * Constructor que genera un Data Transfer Object ClienteDTO a partir de un ClienteEntity.
     * @param clienteEntity Entidad que representa al Cliente.
     */
    public ClienteDTO (ClienteEntity clienteEntity)
    {
        super(clienteEntity);
        this.direccion = clienteEntity.getDireccion();
    }

    /**
     * Devuelve la direccion del cliente.
     *
     * @return direccion del usuario
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Modifica la dirección del cliente.
     *
     * @param direccion direccion del cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    /**
     * Devuelve las formas de pago del cliente.
     *
     * @return las formas de pago del cliente.
     */
    public List<FormaDePagoDTO> getFormasDePago() {
        return formasDePago;
    }
    /**
     * Modifica las formas de pago del cliente.
     *
     * @param formasDePago las formas de pago del cliente.
     */
    public void setFormasDePago(List<FormaDePagoDTO> formasDePago) {
        this.formasDePago = formasDePago;
    }
}
