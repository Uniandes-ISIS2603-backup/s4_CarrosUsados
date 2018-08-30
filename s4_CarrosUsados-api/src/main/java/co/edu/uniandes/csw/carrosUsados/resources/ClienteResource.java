/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

/**
 *
 * @author js.bravo
 */
import java.util.logging.Logger;

import co.edu.uniandes.csw.carrosUsados.dtos.ClienteDTO;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

@Path("clientes")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ClienteResource {

    private final static Logger LOGGER = Logger.getLogger(ClienteResource.class.getName());

    @GET
    public ClienteDTO getCliente() throws BusinessLogicException {
        return new ClienteDTO();
    }

    @POST
    public ClienteDTO createCliente(ClienteDTO cliente) throws BusinessLogicException {
        return new ClienteDTO();
    }
      /**
     * Borra el cliente con el id asociado recibido en la URL.
     *
     * @param clientesId Identificador del cliente que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{clientesId: \\d+}")
    public void deleteCliente (@PathParam("clientesId") Long clientesId)
    {
    }
    @PUT
    public ClienteDTO updateCliente(ClienteDTO vendedor) throws BusinessLogicException {
        return new ClienteDTO();
    }
}
