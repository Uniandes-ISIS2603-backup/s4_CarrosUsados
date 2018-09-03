/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import java.util.List;
/**
 *
 * @author estudiante
 */
public class AutomovilDetailDTO extends AutomovilDTO {
    
    //Aca declaro la lista de objetos CalificacionDTO
    private List<CalificacionDTO> calificaciones;
    private List<PuntoVentaDTO> puntosDeVenta;
    
    public AutomovilDetailDTO() {
        super();
    }
    
    
    
}
