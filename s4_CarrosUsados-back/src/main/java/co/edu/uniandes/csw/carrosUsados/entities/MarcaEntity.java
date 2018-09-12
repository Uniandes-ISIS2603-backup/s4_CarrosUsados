/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import uk.co.jemos.podam.common.PodamExclude;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;


/**
 *
 * @author estudiante
 */
@Entity
public class MarcaEntity extends BaseEntity implements Serializable {
    
    /**
     * nombre de la marca del automovil.
    */
    private String nombre;
    
    /**
     * pais de origen de la marca del automovil.
    */
    private String pais;
    
    /**
     * descripcion de la marca del automovil.
    */
    private String descripcion;
    
    /**
     * Mapea relacion con un objeto Modelo. Un modelo tiene una unica marca, pero una Marca puede tener muchos modelos.
    */
    @PodamExclude
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ModeloEntity> modelos;
    
    //Constructor Vacio Modelo
    public MarcaEntity (){
        
    }
      
    /**
     * Retorna el nombre de la marca del automovil.
     * @return el nombre de la marca
    */ 
    public String getNombre() {
        return nombre;
    }

    /**
     * Actualiza el nombre de las marca del automovil.
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Retorna el pais de origen de la marca del automovil.
     * @return el pais de la marca
     */ 
    public String getPais() {
        return pais;
    }

    /**
     * Actualiza el pais de origen de la marca del automovil.
     * @param pais de la marca
     */
    public void setPais(String pais) {
        this.pais = pais;
    }

    /**
     * Retorna la decripcion de la marca del automovil.
     * @return la descripcion de la marca
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Actualiza la dexcrocion de la marca del automovil.
     * @param descripcion de la marca
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    /**
     * Retorna los modelos que tiene la marca.
     * @return los modelos de las marcas
     */
    public List<ModeloEntity> getModelos(){
        return modelos;
    }
    
    /**
     * Actualiza la lista de modelos que tiene la marca.
     * @param modelos
     */
    public void setModelos(List<ModeloEntity> modelos){
        this.modelos = modelos;
    }
    
}
