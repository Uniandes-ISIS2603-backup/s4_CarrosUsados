/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.AutomovilDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.FichaTecnicaDTO;
import co.edu.uniandes.csw.carrosUsados.ejb.FichaTecnicaLogic;
import co.edu.uniandes.csw.carrosUsados.entities.FichaTecnicaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */
@Path("fichastecnicas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FichaTecnicaResource {

    private static final Logger LOGGER = Logger.getLogger(FichaTecnicaResource.class.getName());
    
    @Inject
    FichaTecnicaLogic fichaTecnicaLogic;
    
    /**
     * Crea una nueva ficha tecnica con la informacion que se recibe en el cuerpo
     * de la petición y se regresa un objeto identico con un id auto-generado
     * por la base de datos.
     *
     * @param fichaTecnica {@link FichaTecnicaDTO} - La ficha tecnica que se
     * desea guardar.
     * @return JSON {@link FichaTEcnicaDTO} - La ficha tecnica guardada con el
     * atributo id autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe la ficha tecnica o el automovil asocaido es invalido.
     */
    @POST
    public FichaTecnicaDTO createFichaTecnica(FichaTecnicaDTO fichaTecnica) throws BusinessLogicException { //Revisar si toca agregar la throws declaration
        LOGGER.log(Level.INFO, "FichaTecnicaResource createFichaTecnica: input: {0}", fichaTecnica.toString());
        FichaTecnicaDTO nuevaFichaTecnicaDTO = new FichaTecnicaDTO(fichaTecnicaLogic.createFichaTecnica(fichaTecnica.toEntity()));
        LOGGER.log(Level.INFO, "FichaTecnicaResource createFichaTecnica: output: {0}", nuevaFichaTecnicaDTO.toString());
        return nuevaFichaTecnicaDTO;
    }
    
    /**
     * Busca la ficha tecnica con el id asociado recibido en la URL y lo
     * devuelve.
     *
     * @param fichastecnicasId Identificador de la ficha tecnica que se esta
     * buscando. Este debe ser una cadena de dígitos.
     * @return JSON {@link FichaTecnicaDTO} - La ficha tecnica buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la ficha tecnica
     */
    @GET
    @Path("{fichastecnicasId: \\d+}")
    public FichaTecnicaDTO getFichaTecnica(@PathParam("fichastecnicasId") long fichastecnicasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FichaTecnicaResource getFichaTecnica: input: {0}", fichastecnicasId);
        FichaTecnicaEntity fichaTecnicaEntity = fichaTecnicaLogic.getFichaTecnica(fichastecnicasId);
        if (fichaTecnicaEntity == null) {
            throw new WebApplicationException("El recurso /fichastecnicas/" + fichastecnicasId + " no existe.", 404);
        }
        FichaTecnicaDTO fichaTecnicaDTO = new FichaTecnicaDTO(fichaTecnicaEntity);
        LOGGER.log(Level.INFO, "FichaTecnicaResource getFichaTecnica: output: {0}", fichaTecnicaDTO.toString());
        return fichaTecnicaDTO;
    }
    
    /**
     * Actualiza la ficha tecnica con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param fichastecnicasId Identificador de la ficha tecnica que se desea
     * actualizar. Este debe ser una cadena de dígitos.
     * @param fichaTecnica {@link FichaTecnicaDTO} La ficha tecnica que se desea
     * guardar.
     * @return JSON {@link FichaTecnicaDTO} - La ficha tecnica guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la ficha tecnica a
     * actualizar.
     */
    @PUT
    @Path("{fichastecnicasId: \\d+}")
    public FichaTecnicaDTO updateFichaTecnica(@PathParam("fichastecnicasId") Long fichastecnicasId, FichaTecnicaDTO fichaTecnica) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FichaTecnicaResource updateFichaTecnica: input: id: {0} , fichaTecnica: {1}", new Object[]{fichastecnicasId, fichaTecnica.toString()});
        fichaTecnica.setId(fichastecnicasId);
        FichaTecnicaEntity entityFichaTecnica = fichaTecnicaLogic.getFichaTecnica(fichastecnicasId);
        if (entityFichaTecnica == null) {
            throw new WebApplicationException("El recurso /fichastecnicas/" + fichastecnicasId + " no existe.", 404);
        }
        FichaTecnicaDTO fichaTecnicaDTO = new FichaTecnicaDTO(entityFichaTecnica);
        LOGGER.log(Level.INFO, "FichaTecnicaResource updateFichaTecnica: output: {0}", fichaTecnicaDTO.toString());
        return fichaTecnicaDTO;
    }
    
    /**
     * Borra la ficha tecnica con el id asociado recibido en la URL.
     *
     * @param fichastecnicasId Identificador de la ficha tecnica que se desea
     * borrar. Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede eliminar la
     * ficha tecnica.
     */
    @DELETE
    @Path("{fichastecnicasId: \\d+}")
    public void deleteFichaTecnica(@PathParam("fichastecnicasId") long fichastecnicasId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FichaTecnicaResource deleteFichaTecnica: input: {0}", fichastecnicasId);
        if (fichaTecnicaLogic.getFichaTecnica(fichastecnicasId) == null) {
            throw new WebApplicationException("El recurso /fichastecnicas/" + fichastecnicasId + " no existe.", 404);
        }
        fichaTecnicaLogic.deleteFichaTecnica(fichastecnicasId);
        LOGGER.info("FichaTecnicaResource deleteFichaTecnica: output: void");
    }
}
