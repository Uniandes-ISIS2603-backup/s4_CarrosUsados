/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;
import co.edu.uniandes.csw.carrosUsados.entities.ModeloEntity;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class ModeloDTO implements Serializable{
    
    private Long id;
    private int num_puertas;
    private String transmision;
    private int centimetros_cubicos;
    
    //Relación con MARCA
    private MarcaDTO marca;

    public ModeloDTO() {
        
    }
    
    public ModeloDTO(ModeloEntity modeloEntity){
        
        this.id = modeloEntity.getId();
        this.num_puertas = modeloEntity.getNum_puertas();
        this.transmision = modeloEntity.getTransmision();
        this.centimetros_cubicos = modeloEntity.getCentrimetros_cubicos();
        
        if (modeloEntity.getMarca() != null) {
            this.marca = new MarcaDTO(modeloEntity.getMarca());
        }
        
    } 
    
    public ModeloEntity toEntity(){
        ModeloEntity entity = new ModeloEntity();
        
        entity.setId(this.id);
        entity.setNum_puertas(this.num_puertas);
        entity.setTransmision(this.transmision);
        entity.setCentrimetros_cubicos(this.centimetros_cubicos);
        
        if (this.marca != null) {
            entity.setMarca(this.marca.toEntity());
        }
        
        return entity;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
