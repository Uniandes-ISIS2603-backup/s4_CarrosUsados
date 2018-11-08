/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.dtos;

import co.edu.uniandes.csw.carrosusados.entities.ArticuloEntity;
import co.edu.uniandes.csw.carrosusados.entities.ClienteEntity;

import java.util.LinkedList;
import java.util.List;

/**
 * @author estudiante
 */
public class ArticuloDetailDTO extends ArticuloDTO {

    private final List<ClienteDTO> clientes;

    /**
     * Crea un articulo vacio
     */
    public ArticuloDetailDTO() {
        super();
        clientes = null;
    }

    /**
     * Crea un articuloDetailDTO
     *
     * @param articulo entity con la cual se va a generar el DTO
     */
    public ArticuloDetailDTO(ArticuloEntity articulo) {
        super(articulo);
        clientes = new LinkedList<>();
        for (int i = 0; i < articulo.getClientes().size(); i++) {
            clientes.add(new ClienteDTO(articulo.getClientes().get(i)));
        }
    }

    /**
     * @return retorna el cliente asociado al articulo
     */
    public List<ClienteDTO> getCliente() {
        return clientes;
    }

    /**
     * Crea un articulo entity con los datos de este articulo.
     *
     * @return articulo entity con los datos de este articulo.
     */
    @Override
    public ArticuloEntity toEntity() {
        ArticuloEntity entity = super.toEntity();
        List<ClienteEntity> list = new LinkedList<>();
        for (int i = 0; i < clientes.size(); i++) {
            list.add(clientes.get(i).toEntity());
        }
        entity.setClientes(list);
        return entity;
    }
}
