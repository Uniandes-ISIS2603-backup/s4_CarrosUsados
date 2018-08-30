/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.PuntoVentaDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.PuntoVentaDetailDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javafx.scene.media.Media;

import javax.faces.bean.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;


import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author estudiante
 */

@Path("puntos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PuntoVentaResource {
    
    private static final Logger LOGGER=Logger.getLogger(PuntoVentaResource.class.getName());
    
     @GET
     
    public List<PuntoVentaDetailDTO> getCalificaciones()
    {
        return new ArrayList<PuntoVentaDetailDTO>();
        
    }
    
    @GET
     @Path("{puntoId: \\d+}")
    public PuntoVentaDTO getPuntoVenta(@PathParam("puntoId") long id)
    {
        return new PuntoVentaDTO();
        
    }
    
    
    @POST
    public PuntoVentaDTO createPuntoVenta(PuntoVentaDTO punto)
    {
         return new PuntoVentaDTO();
    }
    
    @PUT
    @Path("{puntoId: \\d+}")
    public PuntoVentaDTO updatePunto(@PathParam("puntoId") Long puntoId, PuntoVentaDTO punto)
    {
       return new PuntoVentaDTO();
        
    }
    
     @DELETE
    @Path("{puntoId: \\d+}")
    public void deletePunto(@PathParam("puntoId") Long puntoId)
    {
        
    }
    
}
