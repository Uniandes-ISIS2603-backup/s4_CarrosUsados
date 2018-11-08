/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carros.usados.entities;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author estudiante
 */
@Entity
public class FichaTecnicaEntity extends BaseEntity implements Serializable {
    
    private String vidrios;
    private boolean camaraReversa;
    private boolean sensores;
    private String rines;
    private boolean aireAcondicionado;
    private int numAirbags;
    
    /**
     * Retorna los vidrios de la ficha tecnica.
     * @return los vidrios
     */
    public String getVidrios() {
        return vidrios;
    }
    /**
     * Actualiza los vidrios de la ficha tecnica.
     * @param vidrios 
     */
    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }
    /**
     * Retorna la camara de reversa de la ficha tecnica
     * @return la camara de reversa
     */
    public boolean isCamaraReversa() {
        return camaraReversa;
    }
    /**
     * Actualiza la camara de reversa de la ficha tecnica
     * @param camaraReversa 
     */
    public void setCamaraReversa(boolean camaraReversa) {
        this.camaraReversa = camaraReversa;
    }
    /**
     * Retorna los sensores de la ficha tecnica
     * @return los sensores
     */
    public boolean isSensores() {
        return sensores;
    }
    /**
     * Actualiza los sensores de una ficha tecnica.
     * @param sensores 
     */
    public void setSensores(boolean sensores) {
        this.sensores = sensores;
    }
    /**
     * Retorna los rines de la ficha tecnica
     * @return los rines
     */
    public String getRines() {
        return rines;
    }
    /**
     * Actualiza los rines de la ficha tecnica.
     * @param rines 
     */
    public void setRines(String rines) {
        this.rines = rines;
    }
    /**
     * Retorna el aire acondicionado de la ficha tecnica.
     * @return el aire acondicionado
     */
    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }
    /**
     * Actualiza el aire acondicioando de la ficha tecnica
     * @param aireAcondicionado 
     */
    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }
    /**
     * Retorna le numero de airbags de la ficha tecnica
     * @return el numero de airbags
     */
    public int getNumAirbags() {
        return numAirbags;
    }
    /**
     * Actualiza el numero de airbags de la ficha tecnica.
     * @param numAirbags 
     */
    public void setNumAirbags(int numAirbags) {
        this.numAirbags = numAirbags;
    }
    
    
}
