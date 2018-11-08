/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.resources;

import co.edu.uniandes.csw.carrosusados.dtos.AutomovilDTO;
import co.edu.uniandes.csw.carrosusados.dtos.AutomovilDetailDTO;
import co.edu.uniandes.csw.carrosusados.ejb.AutomovilFichaTecnicaLogic;
import co.edu.uniandes.csw.carrosusados.ejb.AutomovilLogic;
import co.edu.uniandes.csw.carrosusados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.carrosusados.mappers.WebApplicationExceptionMapper;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//Acordarse si es el otro import de POST

/**
 * @author estudiante
 */
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AutomovilResource {

    private static final Logger LOGGER = Logger.getLogger(AutomovilResource.class.getName());
    @Inject
    AutomovilLogic automovilLogic;//Variable para acceder a la logica de la aplicacion. Es una inyeccion de dependencia

    @Inject
    private AutomovilFichaTecnicaLogic automovilFichaTecnicaLogic;

    /**
     * Crea un nuevo automovil con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param automovil {@link AutomovilDTO} - EL automovil que se desea
     *                  guardar.
     * @return JSON {@link AutomovilDTO} - El libro guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     *                                Error de lógica que se genera cuando ya existe el automovil o la placa o
     *                                el numero de chasis es inválido o si el modelo asociado ingresado es
     *                                invalido.
     */
    @POST
    public AutomovilDTO createAutomovil(@PathParam("modeloId") Long modeloId, AutomovilDTO automovil) throws BusinessLogicException { //Revisar si toca agregar la throws declaration
        LOGGER.log(Level.INFO, "AutomovilResource createAutomovil: input: {0}", automovil.toString());

        AutomovilDTO nuevoAutomovilDTO = new AutomovilDTO(automovilLogic.createAutomovil(modeloId, automovil.toEntity()));
        //chequear que hacer ocn los AUTOMOVILDETAIL

        LOGGER.log(Level.INFO, "AutomovilResource createAutomovil: output: {0}", nuevoAutomovilDTO.toString());
        return nuevoAutomovilDTO;
    }

    /**
     * Busca y devuelve todos los automoviles que existen en la aplicacion.
     *
     * @return JSONArray {@link AutomovilDetailDTO} - Los automoviles
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<AutomovilDetailDTO> getAutomoviles(@PathParam("modeloId") Long modeloId) throws BusinessLogicException {
        LOGGER.info("AutomovilResource getAutomoviles: input: void");
        List<AutomovilDetailDTO> listaAutomoviles = listEntity2DetailDTO(automovilLogic.getAutomoviles(modeloId));
        LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", listaAutomoviles.toString());
        return listaAutomoviles;
    }

    /**
     * Busca el automovil con el id asociado recibido en la URL y lo devuelve.
     *
     * @param automovilesId Identificador del automovil que se esta buscando.
     *                      Este debe ser una cadena de dígitos.
     * @return JSON {@link AutomovilDetailDTO} - El automovil buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el automovil.
     */
    @GET
    @Path("{automovilesId: \\d+}")
    public AutomovilDTO getAutomovil(@PathParam("modeloId") Long modeloId, @PathParam("automovilesId") long automovilesId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AutomovilResource getAutomovil: input: {0}", automovilesId);
        AutomovilEntity automovilEntity = automovilLogic.getAutomovil(modeloId, automovilesId);
        if (automovilEntity == null) {
            throw new WebApplicationException("El recurso /automoviles/" + automovilesId + " no existe.", 404);
        }
        AutomovilDetailDTO automovilDetailDTO = new AutomovilDetailDTO(automovilEntity);
        LOGGER.log(Level.INFO, "AutomovilResource getBook: output: {0}", automovilDetailDTO.toString());
        return automovilDetailDTO;
    }

    /**
     * Actualiza el automovil con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param automovilesId Identificador del automovil que se desea actualizar.
     *                      Este debe ser una cadena de dígitos.
     * @param automovil     {@link AutomovilDTO} El automovil que se desea guardar.
     * @return JSON {@link AutomovilDetailDTO} - El automovil guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el automovil a
     *                                 actualizar.
     * @throws BusinessLogicException  {@link BusinessLogicExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se puede actualizar el automovil.
     */
    @PUT
    @Path("{automovilesId: \\d+}")
    public AutomovilDetailDTO updateAutomovil(@PathParam("modeloId") Long modeloId, @PathParam("automovilesId") Long automovilesId, AutomovilDTO automovil) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AutomovilResource updateAutomovil: input: id: {0} , automovil: {1}", new Object[]{modeloId, automovilesId, automovil.toString()});
        automovil.setId(automovilesId);
        if (automovilesId.equals(automovil.getId())) {
            throw new BusinessLogicException("Los IDs no coinciden");
        }
        AutomovilEntity automovilEntity = automovilLogic.getAutomovil(modeloId, automovilesId);
        if (automovilEntity == null) {
            throw new WebApplicationException("El recurso /automoviles/" + automovilesId + " no existe.", 404);
        }
        AutomovilDetailDTO detailDTO = new AutomovilDetailDTO(automovilLogic.updateAutomovil(modeloId, automovil.toEntity()));
        LOGGER.log(Level.INFO, "AutomovilResource updateAutomovil: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Borra el automovi con el id asociado recibido en la URL.
     *
     * @param automovilesId Identificador del automovil que se desea borrar.
     *                      Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el automovil.
     */
    @DELETE
    @Path("{automovilesId: \\d+}")
    public void deleteAutomovil(@PathParam("modeloId") Long modeloId, @PathParam("automovilesId") Long automovilesId) throws BusinessLogicException { //Chequear si es Long con mayuscula o con l minuscula
        LOGGER.log(Level.INFO, "AutomovilResource deleteAutomovil: input: {0}", automovilesId);
        AutomovilEntity entity = automovilLogic.getAutomovil(modeloId, automovilesId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /automoviles/" + automovilesId + " no existe.", 404);
        }
        //La siguiente linea puede causar un error:
        automovilLogic.deleteAutomovil(modeloId, automovilesId);
        LOGGER.info("AutomovilResource deleteAutomovil: output: void");

        //Ver que queda pendientea gregar
    }

    private List<AutomovilDetailDTO> listEntity2DetailDTO(List<AutomovilEntity> automoviles) {
        List<AutomovilDetailDTO> list = new ArrayList<>();
        for (AutomovilEntity entity : automoviles) {
            list.add(new AutomovilDetailDTO(entity));
        }
        return list;
    }

}
