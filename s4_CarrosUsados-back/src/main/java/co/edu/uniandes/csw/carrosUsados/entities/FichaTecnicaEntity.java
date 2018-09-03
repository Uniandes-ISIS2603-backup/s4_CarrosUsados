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
    private AutomovilEntity automovil;

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public boolean isCamara_reversa() {
        return camara_reversa;
    }

    public void setCamara_reversa(boolean camara_reversa) {
        this.camara_reversa = camara_reversa;
    }

    public boolean isSensores() {
        return sensores;
    }

    public void setSensores(boolean sensores) {
        this.sensores = sensores;
    }

    public String getRines() {
        return rines;
    }

    public void setRines(String rines) {
        this.rines = rines;
    }

    public boolean isAire_acondicionado() {
        return aire_acondicionado;
    }

    public void setAire_acondicionado(boolean aire_acondicionado) {
        this.aire_acondicionado = aire_acondicionado;
    }

    public int getNum_airbags() {
        return num_airbags;
    }

    public void setNum_airbags(int num_airbags) {
        this.num_airbags = num_airbags;
    }

    public AutomovilEntity getAutomovil() {
        return automovil;
    }

    public void setAutomovil(AutomovilEntity automovil) {
        this.automovil = automovil;
    }
    
    
    
    
    
}
