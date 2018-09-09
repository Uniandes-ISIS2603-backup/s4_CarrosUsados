/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import java.io.Serializable;

/**
 *
 * @author estudiante
 */
public class FichaTecnicaDTO implements Serializable {
    
    private String vidrios;
    private boolean camaraReversa;
    private boolean sensores;
    private String rines;
    private boolean aireAcondicionado;
    private int numAirbags;
    
    public FichaTecnicaDTO(){
        
    }
    
}
