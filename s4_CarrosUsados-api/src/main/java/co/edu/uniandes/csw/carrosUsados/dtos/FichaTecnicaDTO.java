/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.FichaTecnicaEntity;
import java.io.Serializable;

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
        this.id = fichaTecnicaEntity.getId();
        this.vidrios = fichaTecnicaEntity.getVidrios();
        this.camaraReversa = fichaTecnicaEntity.isCamaraReversa();
        this.sensores = fichaTecnicaEntity.isSensores();
        this.rines = fichaTecnicaEntity.getRines();
        this.aireAcondicionado = fichaTecnicaEntity.isAireAcondicionado();
        this.numAirbags = fichaTecnicaEntity.getNumAirbags();

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
    
    
}
