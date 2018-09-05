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
public class FormaDePagoDetailDTO extends FormaDePagoDTO {
    
    //Relacion de carnidalidad 1 con cliente
    private final ClienteDTO cliente;
    
    public FormaDePagoDetailDTO(String nombreP, String tipoP, ClienteDTO clienteP){
        super(nombreP,tipoP);
        this.cliente = clienteP;        
    }
    
    public FormaDePagoDetailDTO(){
        super();
        cliente = null;
    }
    
    public ClienteDTO getCliente( ){
        return cliente;
    }
}