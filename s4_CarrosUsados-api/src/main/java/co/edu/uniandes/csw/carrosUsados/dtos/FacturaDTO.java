/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.dtos;

import co.edu.uniandes.csw.carrosUsados.entities.FacturaEntity;


/**
 *
 * @author estudiante
 */
public class FacturaDTO {

    public Long getId() {
        return id;
    }

    public void setId(Long idF) {
        this.id = idF;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getFormaDepago() {
        return formaDepago;
    }

    public void setFormaDepago(String formaDepago) {
        this.formaDepago = formaDepago;
    }

    
    private Long id;
    private String producto;
    private int subtotal;
    private int total;
    private String formaDepago;
    public FacturaDTO() {

    }
     /**
     * Constructor a partir de la entidad
     *
     * @param facturaEntity
     */
    public FacturaDTO(FacturaEntity facturaEntity) {
        this.id = facturaEntity.getId();
        this.producto = facturaEntity.getProducto();
        this.total = facturaEntity.getTotal();
        this.subtotal = facturaEntity.getSubtotal();
        this.formaDepago = facturaEntity.getFormaDePago();
        
    }
     /**
     * Método para transformar el DTO a una entidad.
     *
     * @return La entidad de la facrura asociado.
     */
     public FacturaEntity toEntity() {
        FacturaEntity entity = new FacturaEntity();
        
        entity.setId(id);
        entity.setProducto(producto);
        entity.setTotal(total);
        entity.setSubtotal(subtotal);
        entity.setFormaDePago(formaDepago);
        return entity;
    }
}
