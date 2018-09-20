/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;
import co.edu.uniandes.csw.carrosUsados.entities.ArticuloEntity;

/**
 *
 * @author estudiante
 */
public class ArticuloDetailDTO extends ArticuloDTO {

    private final AutomovilDTO automovil;

    /**
     * Crea un articulo vacio
     */
    public ArticuloDetailDTO() {
        super();
        automovil = null;
    }

    /**
     * Crea un articuloDetailDTO
     * @param articulo entity con la cual se va a generar el DTO
     */
    public ArticuloDetailDTO(ArticuloEntity articulo) {
        super( articulo );
        automovil = new AutomovilDTO(articulo.getAutomovil());
    }

    /**
     * @return retorna el automovil asociado al articulo 
     */
    public AutomovilDTO getAutomovil() {
        return automovil;
    }
}
