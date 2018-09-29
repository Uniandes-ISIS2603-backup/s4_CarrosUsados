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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import co.edu.uniandes.csw.carrosUsados.dtos.AdministradorDTO;
import co.edu.uniandes.csw.carrosUsados.ejb.AdministradorLogic;
import co.edu.uniandes.csw.carrosUsados.entities.AdministradorEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.carrosUsados.mappers.WebApplicationExceptionMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;

@Path("administradores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AdministradorResource {

    private final static Logger LOGGER = Logger.getLogger(AdministradorResource.class.getName());
    @Inject
    AdministradorLogic administradorLogic;
    /**
     * Busca y devuelve todos los administradores que existen en la aplicacion.
     *
     * @return JSONArray {@link AdministradorDTO} - Los administradores
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<AdministradorDTO> getAdministradores() throws BusinessLogicException {
        LOGGER.info("AdministradorResource getAdministradores: input: void");
        List<AdministradorDTO> lista = new ArrayList<>();
        for (AdministradorEntity entity : administradorLogic.getAdministradores())
        {
            lista.add(new AdministradorDTO(entity));
        }
        LOGGER.log(Level.INFO, "AdministradorResource getAdministradores: output: {0}", lista.toString());
        return lista;
    }
    /**
     * Busca el administrador con el id asociado recibido como parámetro URL y lo devuelve.
     *
     * @param administradoresId Identificador del administrador que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link AdministradorDTO} - El administrador buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el administrador.
     */
    @GET
    @Path("{administradoresId: \\d+}")
    public AdministradorDTO getAdministrador(@PathParam("administradoresId") long administradoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorResource getAdministrador: input: {0}", administradoresId);
        AdministradorEntity entity = administradorLogic.getAdministrador(administradoresId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /administradores/" + administradoresId + " no existe.", 404);
        }
        AdministradorDTO dto= new AdministradorDTO(entity);
        LOGGER.log(Level.INFO, "AdministradorResource getAdministrador: output: {0}", dto.toString());
        return dto;
    }
/**
        * Crea un nuevo administrador con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
            *
            * @param administrador {@link AdministradorDTO} - El administrador que se desea
     * guardar.
     * @return JSON {@link AdministradorDTO} - El administrador guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
            * Error de lógica que se genera cuando el cargo es inválido, la fecha de inicio del cargo y/o de nacimiento es inválida y/o los nombres, apellidos, correo, contraseña, telefono no tienen el formato esperado.
     */
    @POST
    public AdministradorDTO createAdministrador(AdministradorDTO administrador) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: input: {0}", administrador.toString());

        AdministradorDTO dto = new AdministradorDTO(administradorLogic.createAdministrador(administrador.toEntity()));

        LOGGER.log(Level.INFO, "AdministradorResource createAdministrador: output: {0}", dto.toString());
        return dto;
    }
    /**
     * Actualiza el administrador con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param administradoresId Identificador del administrador que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @param administrador {@link AdministradorDTO} El administrador con el que se desea actualizar.
     * @return JSON {@link AdministradorDTO} - El administrador actualizado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el administrador a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el administrador.
     */
    @PUT
    @Path("{administradoresId: \\d+}")
    public AdministradorDTO updateAdministrador(@PathParam("administradoresId") Long administradoresId, AdministradorDTO administrador) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AdministradorResource updateAdministrador: input: id: {0} , administrador: {1}", new Object[]{administradoresId, administrador.toString()});
        administrador.setId(administradoresId);
        if (administradorLogic.getAdministrador(administradoresId) == null) {
            throw new WebApplicationException("El recurso /administradores/" + administradoresId + " no existe.", 404);
        }
        AdministradorDTO dto = new AdministradorDTO(administradorLogic.updateAdministrador(administradoresId, administrador.toEntity()));
        LOGGER.log(Level.INFO, "AdministradorResource updateAdministrador: output: {0}", dto.toString());
        return dto;
    }

    /**
     * Borra el administrador con el id asociado recibido en la URL.
     *
     * @param administradoresId Identificador del administrador que se desea
     * borrar. Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} Error de lógica que se genera cuando no se encuentra el administrador.
     */
    @DELETE
    @Path("{administradoresId: \\d+}")
    public void deleteAdministrador(@PathParam("administradoresId") Long administradoresId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "AdministradorResource deleteAdministrador: input: {0}", administradoresId);
        AdministradorEntity entity = administradorLogic.getAdministrador(administradoresId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /administradores/" + administradoresId + " no existe.", 404);
        }
        administradorLogic.deleteAdministrador(administradoresId);
        LOGGER.info("AdministradorResource deleteAdministrador: output: void");
    }
}
