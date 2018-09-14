/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.CalificacionDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.PuntoVentaDetailDTO;
import co.edu.uniandes.csw.carrosUsados.ejb.CalificacionLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.PuntoVentaLogic;
import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
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
@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {

    private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());
    
    @Inject
    private CalificacionLogic calificacionLogic;

    @GET
    @Path("{calificacionId: \\d+}")
    public CalificacionDTO getCalificacion(@PathParam("calificacionId") long calificacionId) throws BusinessLogicException {
       
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: input:",calificacionId);
        CalificacionEntity calificacionEntity= calificacionLogic.getCalificacion(calificacionId);
        if(calificacionEntity==null)
        {
            throw new WebApplicationException("El recurso /calificaciones/"+calificacionId+"no existe.",404);
        }
        CalificacionDTO calificacion= new CalificacionDTO(calificacionEntity);
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output:",calificacion.toString());
        
        return calificacion;
    }

    
    @GET
    public List<CalificacionDTO> getCalificaciones()
    {
       LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: input:void");
       List<CalificacionDTO> calificaciones=listEntity2DTO(calificacionLogic.getCalificaciones());
       LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: output:",calificaciones.toString()); 
       return calificaciones;
    }
    
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO califica) throws BusinessLogicException {
       
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: input:",califica);    
        CalificacionDTO calificacion= new CalificacionDTO(calificacionLogic.createCalificacion(califica.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: output:",calificacion.toString());
        return calificacion;
    }

    @PUT
    @Path("{calificacionId: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("calificacionId") Long calificacionId, CalificacionDTO calificacion) throws BusinessLogicException {
       LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: input:",new Object[]{calificacionId, calificacion.toString()});
       calificacion.setId(calificacionId);
       
       if(calificacionLogic.getCalificacion(calificacionId)==null)
       {
           throw new WebApplicationException("El recurso /calificaciones/"+calificacionId +"no existe.",404);
       }
      
       CalificacionDTO calificacionDTO= new CalificacionDTO( calificacionLogic.updateCalificacion(calificacionId,calificacion.toEntity()));
       LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: output:",calificacionDTO.toString());
       return  calificacionDTO;
    }

    @DELETE
    @Path("{calificacionId: \\d+}")
    public void deleteCalificacion(@PathParam("calificacionId") Long calificacionId) throws BusinessLogicException {

         LOGGER.log(Level.INFO, "CalificacionResource deleteCalificacion: input:",calificacionId);
         
         if(calificacionLogic.getCalificacion(calificacionId)==null)
         {
             throw new WebApplicationException("El recurso /calificaciones/"+ calificacionId+"no existe.",404);
         }
         
         calificacionLogic.deleteCalificacion(calificacionId);
         LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output: void");
    
    }

       
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos PuntoVetaEntity a una lista de
     * objetos PuntoVentaDetailDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<CalificacionDTO> listEntity2DTO(List<CalificacionEntity> entityList) {
        List<CalificacionDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDTO(entity));
        }
        return list;
    }
    
}
