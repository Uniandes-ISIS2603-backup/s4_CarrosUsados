/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.entities;

import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author js.bravo
 */
@Entity
public class VendedorEntity extends PersonaEntity implements Serializable {


    @PodamExclude
    @OneToMany(mappedBy = "vendedor")
    private List<AutomovilEntity> automoviles;

    public List<AutomovilEntity> getAutomoviles() {
        return automoviles;
    }
    /**
     * Asigna los automoviles del vendedor.
     *
     * @param automoviles automoviles del vendedor.
     */
    public void setAutomoviles(List<AutomovilEntity> automoviles) {
        this.automoviles = automoviles;
    }

    public VendedorEntity(){
        //Constructor vacío por defecto
    }
}
