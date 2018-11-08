/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.ejb;

import co.edu.uniandes.csw.carrosusados.entities.ClienteEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.persistence.ClientePersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author js.bravo
 */
@Stateless
public class ClienteLogic extends UsuarioLogic {
    private static final Logger LOGGER = Logger.getLogger(ClienteLogic.class.getName());

    @Inject
    private ClientePersistence persistence;

    /**
     * Persiste un cliente en el sistema.
     *
     * @param clienteEntity - La entidad del cliente a persistir.
     * @return La entidad si esta pudo persistirse correctamente.
     * @throws BusinessLogicException Si la información del cliente es incorrecta.
     */
    public ClienteEntity createCliente(ClienteEntity clienteEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicio proceso de creación de un cliente");
        validateUsuarioWrapper(clienteEntity);
        persistence.create(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del cliente");
        return clienteEntity;
    }

    /**
     * Devuelve todos los clientes registrados en el sistema.
     *
     * @return Lista de entidades de tipo cliente.
     */
    public List<ClienteEntity> getClientes() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los clientes");
        List<ClienteEntity> clientes = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los clientes");
        return clientes;
    }

    /**
     * Consulta un cliente según su id.
     *
     * @param clienteId - El id del cliente a consultar.
     * @return El cliente si fue encontrado, null en caso contrario.
     * @throws BusinessLogicException Si el id enviado por parámetro es inválido.
     */
    public ClienteEntity getCliente(Long clienteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un cliente");
        ClienteEntity clienteEntity = persistence.find(clienteId);
        if (clienteEntity == null) {
            LOGGER.log(Level.INFO, "El cliente con el id = {0} no existe", clienteId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar un cliente según su Id");
        return clienteEntity;
    }

    /**
     * Actualiza un cliente según su id.
     *
     * @param clienteId     - El id del cliente a actualizar.
     * @param clienteEntity La entidad del cliente actualizada.
     * @return Si fue exitosa la actualización, retorna La entidad del cliente actualizada.
     * @throws BusinessLogicException Si la información del cliente es incorrecta.
     */
    public ClienteEntity updateCliente(Long clienteId, ClienteEntity clienteEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un cliente");
        validateUsuarioWrapper(clienteEntity);
        ClienteEntity newEntity = persistence.update(clienteEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualización de un cliente");
        return newEntity;
    }

    /**
     * Eliminar un cliente según su id.
     *
     * @param clienteId - El id del cliente a eliminar.
     * @throws BusinessLogicException Si no existe un cliente asociado al id enviado por parámetro.
     */
    public void deleteCliente(Long clienteId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el cliente con id = {0}", clienteId);
        ClienteEntity clienteEntity = persistence.find(clienteId);
        if (clienteEntity == null) {
            LOGGER.log(Level.INFO, "El cliente con el id = {0} no existe", clienteId);
        }
        persistence.delete(clienteId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el cliente con id = {0}", clienteId);
    }

}
