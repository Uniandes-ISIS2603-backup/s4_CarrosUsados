package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.CalificacionDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.carrosUsados.ejb.AutomovilCalificacionLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.AutomovilLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.CalificacionLogic;
import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa el recurso "puntos/id/calificaciones"
 * @author Juan Esteban Mendez
 */


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AutomovilCalificacionResource {

    private static final Logger LOGGER = Logger.getLogger(AutomovilCalificacionResource.class.getName());

    @Inject
    private AutomovilCalificacionLogic automovilCalificacionLogic; //variable de logica de la relación.

    @Inject
    private CalificacionLogic calificacionLogic; //variable de logica de calificación

    @Inject
    private AutomovilLogic automovilLogic;


    /**
     * Asocia una calificación existente con un automovil existente
     *
     * @param automovilId El ID del automovil al cual se le va a asociar la calificación.
     * @param calificacionDTO El ID de calificación que se asocia
     * @return JSON {@link CalificacionDTO} - calificación asociado.
     * @throws WebApplicationException {@link co.edu.uniandes.csw.carrosUsados.mappers.WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificación.
     */
    @POST
    public CalificacionDTO addCalificacion(@PathParam("automovilId") Long automovilId, CalificacionDTO calificacionDTO) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AutomovilCalificacionResource addCalificacion: input: automovilId {0} , calificacionId {1}", new Object[]{automovilId});
        AutomovilEntity automovilEntity= automovilLogic.getAutomovil(automovilId);
        if(automovilEntity == null){
            throw new WebApplicationException("El recurso automovil/"+ automovilId+ " no existe.", 404);
        }
        calificacionDTO = new CalificacionDTO(automovilCalificacionLogic.addCalificacion(automovilId, calificacionDTO.toEntity()));
        LOGGER.log(Level.INFO, "AutomovilCalificacionResource addCalificacion: output: {0}", calificacionDTO);
        return calificacionDTO;
    }

    /**
     * Busca y devuelve todos las calificaciones que existen en un automovil.
     *
     * @param automovilId El ID del automovil del cual se buscan las calificaciones
     * @return JSONArray {@link CalificacionDetailDTO} - Las calificaciones encontradas en el punto
     * Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CalificacionDetailDTO> getCalificaciones(@PathParam("automovilId") Long automovilId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AutomovilCalificacionResource getCalificaciones: input: {0}", automovilId);
        List<CalificacionDetailDTO> lista = calificacionesListEntity2DTO(automovilCalificacionLogic.getCalificaciones(automovilId));
        LOGGER.log(Level.INFO, "AutomovilCalificacionResource getCalificaciones: output: {0}", lista.toString());
        return lista;
    }

    /**
     * Busca y devuelve la calificación con el ID recibido en la URL, relativo a un
     * punto.
     *
     * @param automovilId El ID del automovil del cual se busca la calificación
     * @param calificacionId El ID de la calificación que se busca
     * @return {@link CalificacionDetailDTO} - La calificación encontrado en el punto.
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     * si la calificación no está asociado al automovil.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @GET
    @Path("{calificacionId: \\d+}")
    public CalificacionDetailDTO getCalificacion(@PathParam("automovilId") Long automovilId, @PathParam("calificacionId") Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AutomovilCalificacionResource getCalificacion: input: automovilId {0} , calificacionId {1}", new Object[]{automovilId, calificacionId});


        if (calificacionLogic.getCalificacion(calificacionId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionId + " no existe.", 404);
        }
        CalificacionDetailDTO detailDTO = new CalificacionDetailDTO(automovilCalificacionLogic.getCalificacion(automovilId, calificacionId));
        LOGGER.log(Level.INFO, "AutomovilCalificacionResource getCalificacion: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de calificaciones de un automovil con la lista que se recibe en el
     * cuerpo
     *
     * @param automovilId El ID del automovil al cual se le va a asociar las calificaciones
     * @param calificaciones JSONArray {@link CalificacionDetailDTO} - La lista de calificaciones que se
     * desea guardar.
     * @return JSONArray {@link CalificacionDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificación.
     */
    @PUT
    public List<CalificacionDetailDTO> replaceCalificaciones(@PathParam("automovilId") Long automovilId, List<CalificacionDetailDTO> calificaciones) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AutomovilCalificacionResource replaceCalificaciones: input: automovilId {0} , calificaciones {1}", new Object[]{automovilId, calificaciones.toString()});
        for (CalificacionDetailDTO calificacion : calificaciones) {
            if (calificacionLogic.getCalificacion(calificacion.getId()) == null) {
                throw new WebApplicationException("El recurso /calificaciones/" + calificacion.getId() + " no existe.", 404);
            }
        }
        List<CalificacionDetailDTO> lista = calificacionesListEntity2DTO(automovilCalificacionLogic.replaceCalificaciones(automovilId, calificacionesListDTO2Entity(calificaciones)));
        LOGGER.log(Level.INFO, "AutomovilCalificacionResource replaceCalificaciones: output: {0}", lista.toString());
        return lista;
    }

    /**
     * Elimina la conexión entre la calificación y el automovil recibidos en la URL.
     *
     * @param automovilId El ID del automovil al cual se le va a desasociar la calificación
     * @param calificacionId el ID calificacion que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificación.
     */
    @DELETE
    @Path("{calificacionId: \\d+}")
    public void removeCalificacion(@PathParam("automovilId") Long automovilId, @PathParam("calificacionId") Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "AutomovilCalificacionResource deleteCalificacion: input: automovilId {0} , calificacionId {1}", new Object[]{automovilId, calificacionId});
        if (calificacionLogic.getCalificacion(calificacionId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionId + " no existe.", 404);
        }
        automovilCalificacionLogic.deleteCalificacion(automovilId, calificacionId);
        LOGGER.info("AutomovilCalificacionResource deleteCalificacion: output: void");
    }

    /**
     * Convierte una lista de CalificacionEntity a una lista de CalificacionDetailDTO.
     *
     * @param entityList Lista de CalificacionEntity a convertir.
     * @return Lista de CalificacionDetailDTO convertida.
     */
    private List<CalificacionDetailDTO> calificacionesListEntity2DTO(List<CalificacionEntity> entityList) {
        List<CalificacionDetailDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDetailDTO(entity));
        }
        return list;
    }

    /**
     * Convierte una lista de CalificacionDetailDTO a una lista de CalificacionEntity.
     *
     * @param dtos Lista de CalificacionDetailDTO a convertir.
     * @return Lista de CalificacionEntity convertida.
     */
    private List<CalificacionEntity> calificacionesListDTO2Entity(List<CalificacionDetailDTO> dtos) {
        List<CalificacionEntity> list = new ArrayList<>();
        for (CalificacionDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }

}
