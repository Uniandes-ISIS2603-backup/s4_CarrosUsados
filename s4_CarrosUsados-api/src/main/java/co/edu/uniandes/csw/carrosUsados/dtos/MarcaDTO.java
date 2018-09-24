/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.MarcaEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author estudiante
 */
public class MarcaDTO {

    private Long id;
    private String nombre;
    private String pais;
    private String descripcion;
    

    public MarcaDTO() {
        
    }
    
    public MarcaDTO(MarcaEntity marcaEntity){
        
        if (marcaEntity != null) {
        this.id = marcaEntity.getId();
        this.nombre = marcaEntity.getNombre();
        this.pais = marcaEntity.getPais();
        this.descripcion = marcaEntity.getDescripcion();
        }
        
    } 
    
    public MarcaEntity toEntity(){
        MarcaEntity entity = new MarcaEntity();
        
        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setPais(this.pais);
        entity.setDescripcion(this.descripcion);
        
        return entity;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
        @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
