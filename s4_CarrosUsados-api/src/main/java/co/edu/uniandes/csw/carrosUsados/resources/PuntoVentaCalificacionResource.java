/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.CalificacionDetailDTO;
import co.edu.uniandes.csw.carrosUsados.ejb.CalificacionLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.PuntoVentaCalificacionLogic;
import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javax.ws.rs.core.MediaType;

/**
 * Clase que implementa el recurso "puntos/id/calificaciones"
 * @author Daniella Arteaga
 */

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class PuntoVentaCalificacionResource {
    
    private static final Logger LOGGER = Logger.getLogger(PuntoVentaResource.class.getName());
    
    @Inject
    private PuntoVentaCalificacionLogic puntocallogic; //variable de logica de la relación.
    
    @Inject
    private CalificacionLogic calificacionlogic; //variable de logica de calificación
    
    
    
    
     /**
     * Asocia una calificación existente con un punto de venta existente
     *
     * @param puntoId Id El ID del punto al cual se le va a asociar la calificación.
     * @param calificacionId El ID de calificación que se asocia
     * @return JSON {@link CalificacionDetailDTO} - calificación asociado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificación.
     */
    @POST
    @Path("{calificacionId: \\d+}")
    public CalificacionDetailDTO addCalificacion(@PathParam("puntoId") Long puntoId, @PathParam("calificacionId") Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PuntoVentaCalificacionResource addCalificacion: input: puntoId {0} , calificacionId {1}", new Object[]{puntoId, calificacionId});
        if (calificacionlogic.getCalificacion(calificacionId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionId + " no existe.", 404);
        }
        CalificacionDetailDTO detailDTO = new CalificacionDetailDTO(puntocallogic.addCalificacion(calificacionId, puntoId));
        LOGGER.log(Level.INFO, "PuntoVentaCalificacionResource addCalificacion: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Busca y devuelve todos las calificaciones que existen en un punto.
     *
     * @param puntoId El ID del punto del cual se buscan las calificaciones
     * @return JSONArray {@link CalificacionDetailDTO} - Las calificaciones encontradas en el punto
     * Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CalificacionDetailDTO> getCalificaciones(@PathParam("puntoId") Long puntoId) {
        LOGGER.log(Level.INFO, "PuntoCalificacionResource getCalificaciones: input: {0}", puntoId);
        List<CalificacionDetailDTO> lista = calificacionesListEntity2DTO(puntocallogic.getCalificaciones(puntoId));
        LOGGER.log(Level.INFO, "PuntoCalificacionResource getCalificaciones: output: {0}", lista.toString());
        return lista;
    }

    /**
     * Busca y devuelve la calificación con el ID recibido en la URL, relativo a un
     * punto.
     *
     * @param puntoId El ID del punto del cual se busca la calificación
     * @param calificacionId El ID de la calificación que se busca
     * @return {@link CalificacionDetailDTO} - La calificación encontrado en el punto.
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     * si la calificación no está asociado al punto de venta.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificacion.
     */
    @GET
    @Path("{booksId: \\d+}")
    public CalificacionDetailDTO getCalificacion(@PathParam("puntoId") Long puntoId, @PathParam("calificacionId") Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PuntoVentaCalificacionResource getCalificacion: input: puntoId {0} , calificacionId {1}", new Object[]{puntoId, calificacionId});
        if (calificacionlogic.getCalificacion(calificacionId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionId + " no existe.", 404);
        }
        CalificacionDetailDTO detailDTO = new CalificacionDetailDTO(puntocallogic.getCalificacion(puntoId, calificacionId));
        LOGGER.log(Level.INFO, "PuntoVentaCalificacionResource getCalificacion: output: {0}", detailDTO);
        return detailDTO;
    }

    /**
     * Actualiza la lista de calificaciones de un punto de venta con la lista que se recibe en el
     * cuerpo
     *
     * @param puntoId El ID del punto al cual se le va a asociar las calificaciones
     * @param calificaciones JSONArray {@link CalificacionDetailDTO} - La lista de calificaciones que se
     * desea guardar.
     * @return JSONArray {@link CalificacionDetailDTO} - La lista actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificación.
     */
    @PUT
    public List<CalificacionDetailDTO> replaceCalificaciones(@PathParam("puntoId") Long puntoId, List<CalificacionDetailDTO> calificaciones) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PuntoVentaCalificacionResource replaceCalificaciones: input: puntoId {0} , calificaciones {1}", new Object[]{puntoId, calificaciones.toString()});
        for (CalificacionDetailDTO calificacion : calificaciones) {
            if (calificacionlogic.getCalificacion(calificacion.getId()) == null) {
                throw new WebApplicationException("El recurso /calificaciones/" + calificacion.getId() + " no existe.", 404);
            }
        }
        List<CalificacionDetailDTO> lista = calificacionesListEntity2DTO(puntocallogic.replaceCalificaciones(puntoId, calificacionesListDTO2Entity(calificaciones)));
        LOGGER.log(Level.INFO, "PuntoVentaCalificacionResource replaceCalificaciones: output: {0}", lista.toString());
        return lista;
    }

    /**
     * Elimina la conexión entre la calificación y el punto recibidos en la URL.
     *
     * @param puntoId  El ID del punto al cual se le va a desasociar la calificación
     * @param calificacionI el ID calificacion que se desasocia
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificación.
     */
    @DELETE
    @Path("{calificacionId: \\d+}")
    public void removeCalificacion(@PathParam("puntoId") Long puntoId, @PathParam("calificacionId") Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PuntoVentaCalificacionResource deleteCalificacion: input: puntoId {0} , calificacionId {1}", new Object[]{puntoId, calificacionId});
        if (calificacionlogic.getCalificacion(calificacionId) == null) {
            throw new WebApplicationException("El recurso /calificaciones/" + calificacionId + " no existe.", 404);
        }
        puntocallogic.deleteCalificacion(puntoId, calificacionId);
        LOGGER.info("PuntoVentaCalificacionResource deleteCalificacion: output: void");
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
     * @return Lista de BookEntity convertida.
     */
    private List<CalificacionEntity> calificacionesListDTO2Entity(List<CalificacionDetailDTO> dtos) {
        List<CalificacionEntity> list = new ArrayList<>();
        for (CalificacionDetailDTO dto : dtos) {
            list.add(dto.toEntity());
        }
        return list;
    }
    
}
