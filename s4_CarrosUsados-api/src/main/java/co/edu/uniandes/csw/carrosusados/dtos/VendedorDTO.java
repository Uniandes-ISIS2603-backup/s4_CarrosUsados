/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.dtos;

import co.edu.uniandes.csw.carrosusados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosusados.entities.VendedorEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author js.bravo
 */

/**
 * Clase Data Transfer Object Vendedor que representa el vendedor de un
 * automovil en el sistema.
 */
public class VendedorDTO extends PersonaDTO implements Serializable {


    /**
     * Lista que representa la relación OneToMany entre Vendedor y Automovil.
     */
    private List<AutomovilDTO> automoviles;

    /**
     * Constructor vacío generado por defecto.
     */
    public VendedorDTO() {
        //Constructor por defecto
    }

    /**
     * Transforma un Data Transfer Object a una Entidad de tipo Vendedor.
     *
     * @return entidad de Vendedor.
     */
    public VendedorEntity toEntity() {
        VendedorEntity entity = new VendedorEntity();
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setApellido(this.apellido);
        if (automoviles != null) {
            List<AutomovilEntity> automovilesEntity = new ArrayList<>();
            for (AutomovilDTO automovil : automoviles) {
                automovilesEntity.add(automovil.toEntity());
            }
        }
        return entity;
    }

    /**
     * Constructor que genera un Data Transfer Object Vendedor a partir de un VendedorEntity.
     *
     * @param vendedorEntity Entidad que representa al vendedor.
     */
    public VendedorDTO(VendedorEntity vendedorEntity) {
        super(vendedorEntity);
        if (vendedorEntity.getAutomoviles() != null) {
            for (AutomovilEntity automovil : vendedorEntity.getAutomoviles()) {
                automoviles.add(new AutomovilDTO(automovil));
            }
        }
    }


}
