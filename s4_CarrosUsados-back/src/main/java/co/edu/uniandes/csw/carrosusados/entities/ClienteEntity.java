/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carros.usados.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author js.bravo
 */
@Entity
public class ClienteEntity extends UsuarioEntity implements Serializable {

    /**
     * Dirección de entrega/residencia del cliente.
     */
    private String direccion;

    @PodamExclude
    @ManyToMany(mappedBy = "clientes")
    private List<ArticuloEntity> articulos;

    public ClienteEntity(){
        //Constructor vacío por defecto
    }
 
    /**
     * Retorna la dirección de residencia/entrega del cliente.
     *
     * @return la dirección de residencia/entrega del cliente.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Asigna la dirección de residencia/entrega del cliente.
     *
     * @param direccion - la dirección de residencia/entrega del cliente.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}
