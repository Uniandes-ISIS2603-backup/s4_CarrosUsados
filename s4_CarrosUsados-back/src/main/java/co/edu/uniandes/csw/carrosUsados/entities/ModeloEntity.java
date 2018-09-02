/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.entities;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class ModeloEntity extends BaseEntity implements Serializable{
    
    private int num_puertas;
    private String transmision;
    private int centrimetros_cubicos;

    public int getNum_puertas() {
        return num_puertas;
    }

    public void setNum_puertas(int num_puertas) {
        this.num_puertas = num_puertas;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public int getCentrimetros_cubicos() {
        return centrimetros_cubicos;
    }

    public void setCentrimetros_cubicos(int centrimetros_cubicos) {
        this.centrimetros_cubicos = centrimetros_cubicos;
    }
    
    
    
    
}
