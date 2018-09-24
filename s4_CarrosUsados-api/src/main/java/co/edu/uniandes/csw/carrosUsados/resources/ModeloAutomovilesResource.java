/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.AutomovilDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.AutomovilDetailDTO;
import co.edu.uniandes.csw.carrosUsados.ejb.AutomovilLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.ModeloAutomovilLogic;
import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ModeloAutomovilesResource {
    private static final Logger LOGGER = Logger.getLogger(ModeloAutomovilesResource.class.getName());

    @Inject
    private ModeloAutomovilLogic modeloAutomovilLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private AutomovilLogic automovilLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    /**
     * Guarda un automovil dentro de un modelo con la informacion que recibe en
     * la URL. Se devuelve el automovil que se guarda en el modelo.
     *
     * @param modeloId Identificador del modelol que se esta
     * actualizando. Este debe ser una cadena de dígitos.
     * @param automovilId Identificador del automovil que se desea guardar. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AutomovilDTO} - El automovil guardado en el modelo.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el automovil.
     */
    @POST
    @Path("{automovilId: \\d+}")
    public AutomovilDTO addAutomovil(@PathParam("modeloId") Long modeloId, @PathParam("automovilId") Long automovilId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModeloAutomovilesResource addAutomovil: input: modeloID: {0} , automovilId: {1}", new Object[]{modeloId, automovilId});
        if (automovilLogic.getAutomovil(automovilId) == null) {
            throw new WebApplicationException("El recurso /automoviles/" + automovilId + " no existe.", 404);
        }
        AutomovilDTO automovilDTO = new AutomovilDTO(modeloAutomovilLogic.addAutomovil(automovilId, modeloId));
        LOGGER.log(Level.INFO, "ModeloAutomovilesResource addAutomovil: output: {0}", automovilDTO.toString());
        return automovilDTO;
    }

    /**
     * Busca y devuelve todos los automoviles que existen en el modelo.
     *
     * @param modeloId Identificador del modelo que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSONArray {@link AutomovilDetailDTO} - Los automoviles encontrados en el
     * modelo. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<AutomovilDetailDTO> getAutomoviles(@PathParam("modeloId") Long modeloId) {
        LOGGER.log(Level.INFO, "ModeloAutomovilesResource getAutomoviles: input: {0}", modeloId);
        List<AutomovilDetailDTO> listaDetailDTOs = automovilesListEntity2DTO(modeloAutomovilLogic.getAutomoviles(modeloId));
        LOGGER.log(Level.INFO, "ModeloAutomovilesResource getAutomoviles: output: {0}", listaDetailDTOs.toString());
        return listaDetailDTOs;
    }

    /**
     * Busca el automovil con el id asociado dentro del modelo con id asociado.
     *
     * @param modeloId Identificador de la editorial que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @param automovilId Identificador del automovil que se esta buscando. Este debe
     * ser una cadena de dígitos.
     * @return JSON {@link AutomovilDetailDTO} - El libro buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el automovil.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el automovil en el
     * modelo.
     */
    @GET
    @Path("{automovilId: \\d+}")
    public AutomovilDetailDTO getAutomovil(@PathParam("modeloId") Long modeloId, @PathParam("automovilId") Long automovilId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModeloAutomovilesResource getAutomovil: input: modeloID: {0} , automovilId: {1}", new Object[]{modeloId, automovilId});
        if (automovilLogic.getAutomovil(automovilId) == null) {
            throw new WebApplicationException("El recurso /modelos/" + modeloId + "/automoviles/" + automovilId + " no existe.", 404);
        }
        AutomovilDetailDTO automovilDetailDTO = new AutomovilDetailDTO(modeloAutomovilLogic.getAutomovil(modeloId, automovilId));
        LOGGER.log(Level.INFO, "ModeloAutomovilesResource getAutomovil: output: {0}", automovilDetailDTO.toString());
        return automovilDetailDTO;
    }

    /**
     * Remplaza las instancias de Automovil asociadas a una instancia de Modelo
     *
     * @param modeloId Identificador del modelo que se esta
     * remplazando. Este debe ser una cadena de dígitos.
     * @param automoviles JSONArray {@link AutomovilDTO} El arreglo de automoviles nuevo para el
     * modelo.
     * @return JSON {@link AutomovilDTO} - El arreglo de automoviles guardado en el
     * modelo.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el Automovil.
     */
    @PUT
    public List<AutomovilDetailDTO> replaceAutomoviles(@PathParam("modeloId") Long modeloId, List<AutomovilDetailDTO> automoviles) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModeloAutomovilesResource replaceAutomoviles input: modeloId: {0} , automoviles: {1}", new Object[]{modeloId, automoviles.toString()});
        for (AutomovilDetailDTO automovil :automoviles) {
            if (automovilLogic.getAutomovil(automovil.getId()) == null) {
                throw new WebApplicationException("El recurso /automoviles/" + automovil.getId() + " no existe.", 404);
            }
        }
        List<AutomovilDetailDTO> listaDetailDTOs = automovilesListEntity2DTO(modeloAutomovilLogic.replaceAutomoviles(modeloId, automovilesListDTO2Entity(automoviles)));
        LOGGER.log(Level.INFO, "ModeloAutomovilesResource replaceAutomoviles: output: {0}", listaDetailDTOs.toString());
        return listaDetailDTOs;
    }

    /**
     * Convierte una lista de AutomovilEntity a una lista de AutomovilDetailDTO.
     *
     * @param entityList Lista de AutomovilEntity a convertir.
     * @return Lista de AutomovilDTO convertida.
     */
    private List<AutomovilDetailDTO> automovilesListEntity2DTO(List<AutomovilEntity> entityList) {
        List<AutomovilDetailDTO> list = new ArrayList();
        for (AutomovilEntity entity : entityList) {
            list.add(new AutomovilDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de AutomovilDetailDTO a una lista de AutomoEntity.
     *
     * @param dtos Lista de AutomovilDetailDTO a convertir.
     * @return Lista de AutomovilEntity convertida.
     */
    private List<AutomovilEntity> automovilesListDTO2Entity(List<AutomovilDetailDTO> dtos) {
        List<AutomovilEntity> list = new ArrayList<>();
        for (AutomovilDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
}
