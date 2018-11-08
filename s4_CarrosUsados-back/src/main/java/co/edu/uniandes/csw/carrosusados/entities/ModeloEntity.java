/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.entities;

import uk.co.jemos.podam.common.PodamExclude;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

/**
 * @author estudiante
 */
@Entity
public class ModeloEntity extends BaseEntity implements Serializable {

    /**
     * numero de puertas del automovil.
     */
    private int num_puertas;

    /**
     * tipo de transmision del automovil.
     */
    private String transmision;

    /**
     * centrimetro cubicos del motor del automovil.
     */
    private int centrimetros_cubicos;

    /**
     * Mapea relacion con un objeto Marca. Un modelo tiene una unica marca, pero una Marca puede tener muchos modelos.
     */
    @PodamExclude
    @ManyToOne
    private MarcaEntity marca;

    /**
     * Mapea relacion con un objeto Automovil. Un automovil tiene un unico modelo, pero un Modelo puede tener muchos automoviles.
     */
    @PodamExclude
    @OneToMany(mappedBy = "modeloAsociado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AutomovilEntity> automoviles;

    //Constructor Vacio Modelo
    public ModeloEntity() {

    }

    /**
     * Retorna el numero de puertas del automovil.
     *
     * @return el numero de puertas
     */
    public int getNum_puertas() {
        return num_puertas;
    }

    /**
     * Actualiza el numero de puertas del automovil.
     *
     * @param numero de puertas
     */
    public void setNum_puertas(int num_puertas) {
        this.num_puertas = num_puertas;
    }

    /**
     * Retorna el tipo de transmisión del automovil.
     *
     * @return la transmision
     */
    public String getTransmision() {
        return transmision;
    }

    /**
     * Actualiza el tipo de transmision del automovil.
     *
     * @param tipo de transmision
     */
    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    /**
     * Retorna los centrimetros cubicos del motor del automovil.
     *
     * @return los centimetros cubicos
     */
    public int getCentrimetros_cubicos() {
        return centrimetros_cubicos;
    }

    /**
     * Actualiza el centimetros cubicos del automovil.
     *
     * @param centimetros cubicos
     */
    public void setCentrimetros_cubicos(int centrimetros_cubicos) {
        this.centrimetros_cubicos = centrimetros_cubicos;
    }

    /**
     * Retorna la marca del automovil.
     *
     * @return la marca
     */
    public MarcaEntity getMarca() {
        return marca;
    }

    /**
     * Actualiza la Marca del automovil.
     *
     * @param marca
     */
    public void setMarca(MarcaEntity marca) {
        this.marca = marca;
    }

    /**
     * Retorna los automoviles relacionados con el modelo.
     *
     * @return el modelo
     */
    public List<AutomovilEntity> getAutomoviles() {
        return automoviles;
    }

    /**
     * Actualiza la lista de automoviles del modelo.
     *
     * @param lista de automoviles
     */
    public void setAutomoviles(List<AutomovilEntity> automoviles) {
        this.automoviles = automoviles;
    }
}
