/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.CalificacionDTO;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.Consumes;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;

import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("calificaciones")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class CalificacionResource {
    
     private static final Logger LOGGER=Logger.getLogger(CalificacionResource.class.getName());
    
    @GET
    public CalificacionDTO getCalificacion(CalificacionDTO calificacion)
    {
        return calificacion;
        
    }
    
    
    @POST
    public CalificacionDTO createCalificacion(CalificacionDTO califica)
    {
        return califica;
    }
    
    @PUT
    @Path("{calificacionId: \\d+}")
    public CalificacionDTO updateCalificacion(@PathParam("calificacionId") Long calificacionId, CalificacionDTO calificacion)
    {
        return calificacion;
        
    }
    
     @DELETE
    @Path("{calificacionId: \\d+}")
    public void deleteCalificacion(@PathParam("calificacionId") Long calificacionId)
    {
    
    }
        
    
    
    
    
    
    
}
