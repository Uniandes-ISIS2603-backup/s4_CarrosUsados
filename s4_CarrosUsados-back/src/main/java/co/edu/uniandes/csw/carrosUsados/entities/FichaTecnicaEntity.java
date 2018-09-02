/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import uk.co.jemos.podam.common.PodamExclude;

/**
 *
 * @author estudiante
 */
@Entity
public class FichaTecnicaEntity extends BaseEntity implements Serializable {
    
    private String vidrios;
    private boolean camara_reversa;
    private boolean sensores;
    private String rines;
    private boolean aire_acondicionado;
    private int num_airbags;
    
    @PodamExclude
    @OneToOne
    AutomovilEntity automovil;
    
    
    
    
}
