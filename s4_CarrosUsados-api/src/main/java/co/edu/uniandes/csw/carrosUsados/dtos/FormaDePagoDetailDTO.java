/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;
import co.edu.uniandes.csw.carrosUsados.entities.FormaDePagoEntity;

/**
 *
 * @author estudiante
 */
public class FormaDePagoDetailDTO extends FormaDePagoDTO {

    private final ClienteDTO cliente;

    /**
     * Crea un formaDePago vacio
     */
    public FormaDePagoDetailDTO() {
        super();
        cliente = null;
    }

    /**
     * Crea un formaDePagoDetailDTO
     * @param formaDePago entity con la cual se va a generar el DTO
     */
    public FormaDePagoDetailDTO(FormaDePagoEntity formaDePago) {
        super( formaDePago );
        cliente = new ClienteDTO(formaDePago.getCliente());        
    }

    /**
     * @return retorna el cliente asociado al formaDePago 
     */
    public ClienteDTO getCliente() {
        return cliente;
    }
}
