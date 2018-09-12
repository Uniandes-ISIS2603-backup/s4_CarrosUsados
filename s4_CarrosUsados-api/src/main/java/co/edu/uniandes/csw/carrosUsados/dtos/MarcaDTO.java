/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.MarcaEntity;

/**
 *
 * @author estudiante
 */
public class MarcaDTO {

    private Long id;
    private String nombre;
    private String pais;
    private String descripcion;
    
    //Relación con MARCA
    private MarcaDTO marca;

    public MarcaDTO() {
        
    }
    
    public MarcaDTO(MarcaEntity marcaEntity){
        
        this.id = marcaEntity.getId();
        this.nombre = marcaEntity.getNombre();
        this.pais = marcaEntity.getPais();
        this.descripcion = marcaEntity.getDescripcion();
        
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
}
