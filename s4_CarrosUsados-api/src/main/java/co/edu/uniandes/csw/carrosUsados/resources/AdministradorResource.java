/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

/**
 *
 * @author estudiante
 */
import java.util.logging.Logger;

import co.edu.uniandes.csw.carrosUsados.dtos.AdministradorDTO;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

@Path("administradores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AdministradorResource {

    private final static Logger LOGGER = Logger.getLogger(AdministradorResource.class.getName());

    @GET
    public AdministradorDTO getAdministrador() throws BusinessLogicException {
        return new AdministradorDTO();
    }

    @POST
    public AdministradorDTO createAdministrador(AdministradorDTO administrador) throws BusinessLogicException {
        return new AdministradorDTO();
    }
     /**
     * Borra el administrador con el id asociado recibido en la URL.
     *
     * @param administradoresId Identificador del administrador que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{administradoresId: \\d+}")
    public void deleteAdministrador(@PathParam("administradoresId") Long administradoresId)
    {
    }
}
