/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author estudiante
 */

public class AutomovilDTO implements Serializable{
    
    private long id;
    private int modelo;
    private String marca;
    private int anio;
    private String color;
    private String numChasis;
    private String placa;
    private Date fecha_agregacion; //Chequear el tipo de dato
    private String precioOriginal;
    
    //Relacion a FichaTecnicaDTO dado que esta tiene cardinalidad 1
    private FichaTecnicaDTO ficha_tecnica;
    
    public AutomovilDTO(){
        
    }
    
    
}
