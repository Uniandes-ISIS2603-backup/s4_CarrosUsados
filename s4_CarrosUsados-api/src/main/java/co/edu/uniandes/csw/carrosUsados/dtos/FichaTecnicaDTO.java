/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.FichaTecnicaEntity;
import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class FichaTecnicaDTO implements Serializable {
    
    private Long id;
    private String vidrios;
    private boolean camaraReversa;
    private boolean sensores;
    private String rines;
    private boolean aireAcondicionado;
    private int numAirbags;

    public FichaTecnicaDTO() {

    }

    public FichaTecnicaDTO(FichaTecnicaEntity fichaTecnicaEntity) {
        if(fichaTecnicaEntity != null){
            this.id = fichaTecnicaEntity.getId();
            this.vidrios = fichaTecnicaEntity.getVidrios();
            this.camaraReversa = fichaTecnicaEntity.isCamaraReversa();
            this.sensores = fichaTecnicaEntity.isSensores();
            this.rines = fichaTecnicaEntity.getRines();
            this.aireAcondicionado = fichaTecnicaEntity.isAireAcondicionado();
            this.numAirbags = fichaTecnicaEntity.getNumAirbags();
        }
    }

    public FichaTecnicaEntity toEntity() {
        FichaTecnicaEntity entity = new FichaTecnicaEntity();
        
        entity.setId(id);
        entity.setVidrios(vidrios);
        entity.setCamaraReversa(camaraReversa);
        entity.setSensores(sensores);
        entity.setRines(rines);
        entity.setAireAcondicionado(aireAcondicionado);
        entity.setNumAirbags(numAirbags);
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public boolean isCamaraReversa() {
        return camaraReversa;
    }

    public void setCamaraReversa(boolean camaraReversa) {
        this.camaraReversa = camaraReversa;
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

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    public int getNumAirbags() {
        return numAirbags;
    }

    public void setNumAirbags(int numAirbags) {
        this.numAirbags = numAirbags;
    }
    
    
    
     @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
    
}
