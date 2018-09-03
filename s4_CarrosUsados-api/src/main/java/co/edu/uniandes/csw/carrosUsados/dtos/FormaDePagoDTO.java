/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

/**
 *
 * @author estudiante
 */
public class FormaDePagoDTO {
    
    private long id;
    private String nombre;
    private String tipo;
    
    public FormaDePagoDTO(){
    
    }
    
    public FormaDePagoDTO(String nombreP, String tipoP){
        this.nombre = nombreP;
        this.tipo = tipoP;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setNombre(String nombreP){
        this.nombre = nombreP;
    }
    
    public void setTipo(String tipoP){
        this.tipo = tipoP;
    }
}