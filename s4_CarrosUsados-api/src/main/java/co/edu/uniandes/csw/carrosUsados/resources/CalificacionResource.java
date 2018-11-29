/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.CalificacionDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.CalificacionDetailDTO;

import co.edu.uniandes.csw.carrosUsados.ejb.CalificacionLogic;

import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;

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
 * Clase que implementa el recurso "calificaciones".
 * @author Daniella Arteaga
 */
@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {

    private static final Logger LOGGER = Logger.getLogger(CalificacionResource.class.getName());
    
    @Inject
    private CalificacionLogic calificacionLogic;
    

   /**
     * Busca la calificación con el id asociado recibido en la URL y la devuelve.
     *
     * @param calificacionId Id Identificador de la calificación que se está buscando. Esta debe
     * ser una cadena de dígitos.
     * @return JSON {@link CalificacionDTO} - La calificación buscada
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el autor.
     */
    @GET
    @Path("{calificacionId: \\d+}")
    public CalificacionDetailDTO getCalificacion(@PathParam("calificacionId") long calificacionId) throws BusinessLogicException {
       
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: input:{0}",calificacionId);
        CalificacionEntity calificacionEntity= calificacionLogic.getCalificacion(calificacionId);
        if(calificacionEntity==null)
        {
            throw new WebApplicationException("El recurso /calificaciones/"+calificacionId+"no existe.",404);
        }
        CalificacionDetailDTO calificacion= new CalificacionDetailDTO(calificacionEntity);
        LOGGER.log(Level.INFO, "CalificacionResource getCalificacion: output:{0}",calificacion.toString());
        
        return calificacion;
    }

    
    
     /**
     * Busca y devuelve todas las calificaciones que existen en la aplicacion.
     *
     * @return JSONArray {@link CalificacionDTO} - Los libros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<CalificacionDetailDTO> getCalificaciones()
    {
       LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: input:void");
       List<CalificacionDetailDTO> calificaciones=listEntity2DTO(calificacionLogic.getCalificaciones());
       LOGGER.log(Level.INFO, "CalificacionResource getCalificaciones: output:{0}",calificaciones.toString()); 
       return calificaciones;
    }
    
    /**
     * Crea una nueva calificación con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param califica  {@link CaliificacionDTO} - la calificación que se desea guardar.
     * @return JSON {@link CalificacionDTO} - la calificación guardado con el atributo id
     * autogenerado.
     */
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO califica) throws BusinessLogicException {
       
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: input:{0}",califica);    
        CalificacionDTO calificacion= new CalificacionDTO(calificacionLogic.createCalificacion(califica.toEntity()));
        LOGGER.log(Level.INFO, "CalificacionResource createCalificacion: output:{0}",calificacion.toString());
        return calificacion;
    }

    /**
     * Actualiza la calificación con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param calificacionId Id Identificador  la calificación que se desea actualizar. Este debe
     * ser una cadena de dígitos.
     * @param calificacion  {@link CalificacionDTO} El libro que se desea guardar.
     * @return JSON {@link CalificacionDTO} - la calificación guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificación a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar la calificación.
     */
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
       LOGGER.log(Level.INFO, "CalificacionResource updateCalificacion: output:{0}",calificacionDTO.toString());
       return  calificacionDTO;
    }

    /**
     * Borra la calificación con el id asociado recibido en la URL.
     *
     * @param calificacionId Id Identificador de la calificación que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la calificación.
     */
    @DELETE
    @Path("{calificacionId: \\d+}")
    public void deleteCalificacion(@PathParam("calificacionId") Long calificacionId) throws BusinessLogicException {

         LOGGER.log(Level.INFO, "CalificacionResource deleteCalificacion: input:{0}",calificacionId);
         
         if(calificacionLogic.getCalificacion(calificacionId)==null)
         {
             throw new WebApplicationException("El recurso /calificaciones/"+ calificacionId+" no existe. ",404);
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
    private List<CalificacionDetailDTO> listEntity2DTO(List<CalificacionEntity> entityList) {
        List<CalificacionDetailDTO> list = new ArrayList<>();
        for (CalificacionEntity entity : entityList) {
            list.add(new CalificacionDetailDTO(entity));
        }
        return list;
    }
    
}
