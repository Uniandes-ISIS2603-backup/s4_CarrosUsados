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
public class ArticuloDetailDTO extends ArticuloDTO {

    private final AutomovilDTO automovil;

    public ArticuloDetailDTO() {
        super();
        automovil = null;
    }

    public ArticuloDetailDTO(AutomovilDTO automovilP, long id, String ubicacion, String precio, String descripcion, String disponibilidad) {
        super(id, ubicacion, precio, descripcion, disponibilidad);
        automovil = automovilP;
    }

    public AutomovilDTO getAutomovil() {
        return automovil;
    }
}
