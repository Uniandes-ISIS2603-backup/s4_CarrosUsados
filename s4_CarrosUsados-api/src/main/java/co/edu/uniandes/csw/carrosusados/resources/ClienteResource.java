/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.resources;

/**
 * @author js.bravo
 */

import co.edu.uniandes.csw.carrosusados.dtos.ClienteDTO;
import co.edu.uniandes.csw.carrosusados.ejb.ClienteLogic;
import co.edu.uniandes.csw.carrosusados.entities.ClienteEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.carrosusados.mappers.WebApplicationExceptionMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("clientes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ClienteResource {

    private final static Logger LOGGER = Logger.getLogger(ClienteResource.class.getName());
    @Inject
    ClienteLogic clienteLogic;

    /**
     * Busca el cliente con el id asociado recibido como parámetro URL y lo devuelve.
     *
     * @param clientesId Identificador del cliente que se esta buscando.
     *                   Este debe ser una cadena de dígitos.
     * @return JSON {@link ClienteDTO} - El cliente buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @GET
    @Path("{clientesId: \\d+}")
    public ClienteDTO getCliente(@PathParam("clientesId") long clientesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteResource getCliente: input: {0}", clientesId);
        ClienteEntity entity = clienteLogic.getCliente(clientesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDTO dto = new ClienteDTO(entity);
        LOGGER.log(Level.INFO, "ClienteResource getCliente: output: {0}", dto.toString());
        return dto;
    }

    /**
     * Busca y devuelve todos los clientes que existen en la aplicacion.
     *
     * @return JSONArray {@link ClienteDTO} - Los clientes
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ClienteDTO> getClientes() throws BusinessLogicException {
        LOGGER.info("ClienteResource getClientes: input: void");
        List<ClienteDTO> lista = new ArrayList<>();
        for (ClienteEntity entity : clienteLogic.getClientes()) {
            lista.add(new ClienteDTO(entity));
        }
        LOGGER.log(Level.INFO, "ClienteResource getClientes: output: {0}", lista.toString());
        return lista;
    }

    /**
     * Crea un nuevo cliente con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param cliente {@link ClienteDTO} - El cliente que se desea
     *                guardar.
     * @return JSON {@link ClienteDTO} - El cliente guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     *                                Error de lógica que se genera cuando la fecha de nacimiento es inválida y/o los nombres, apellidos, correo, contraseña, telefono no tienen el formato esperado.
     */
    @POST
    public ClienteDTO createCliente(ClienteDTO cliente) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteResource createCliente: input: {0}", cliente.toString());

        ClienteDTO dto = new ClienteDTO(clienteLogic.createCliente(cliente.toEntity()));

        LOGGER.log(Level.INFO, "ClienteResource createCliente: output: {0}", dto.toString());
        return dto;
    }

    /**
     * Borra el cliente con el id asociado recibido en la URL.
     *
     * @param clientesId Identificador del cliente que se desea borrar. Este
     *                   debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} Error de lógica que se genera cuando no se encuentra el cliente.
     */
    @DELETE
    @Path("{clientesId: \\d+}")
    public void deleteCliente(@PathParam("clientesId") Long clientesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteResource deleteCliente: input: {0}", clientesId);
        ClienteEntity entity = clienteLogic.getCliente(clientesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        clienteLogic.deleteCliente(clientesId);
        LOGGER.info("ClienteResource deleteCliente: output: void");
    }

    /**
     * Actualiza el cliente con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param clientesId Identificador del cliente que se desea actualizar.
     *                   Este debe ser una cadena de dígitos.
     * @param cliente    {@link ClienteDTO} El cliente con el que se desea actualizar.
     * @return JSON {@link ClienteDTO} - El cliente actualizado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el cliente a
     *                                 actualizar.
     * @throws BusinessLogicException  {@link BusinessLogicExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se puede actualizar el cliente.
     */
    @PUT
    @Path("{clientesId: \\d+}")
    public ClienteDTO updateCliente(@PathParam("clientesId") Long clientesId, ClienteDTO cliente) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ClienteResource updatecliente: input: id: {0} , cliente: {1}", new Object[]{clientesId, cliente.toString()});
        cliente.setId(clientesId);
        if (clienteLogic.getCliente(clientesId) == null) {
            throw new WebApplicationException("El recurso /clientes/" + clientesId + " no existe.", 404);
        }
        ClienteDTO dto = new ClienteDTO(clienteLogic.updateCliente(clientesId, cliente.toEntity()));
        LOGGER.log(Level.INFO, "ClienteResource updatecliente: output: {0}", dto.toString());
        return dto;
    }
}
