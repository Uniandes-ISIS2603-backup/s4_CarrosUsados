/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.PuntoVentaDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.PuntoVentaDetailDTO;
import co.edu.uniandes.csw.carrosUsados.ejb.PuntoVentaLogic;
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
@Path("puntos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PuntoVentaResource {

    private static final Logger LOGGER = Logger.getLogger(PuntoVentaResource.class.getName());

    @Inject
    private PuntoVentaLogic puntologic;
    
    @GET
    public List<PuntoVentaDetailDTO> getPuntos()
    {
        LOGGER.log(Level.INFO, "PuntoVentaResource getPuntos: input: void");
        List<PuntoVentaDetailDTO> lista= listEntity2DetailDTO(puntologic.getPuntosDeVenta());
        LOGGER.log(Level.INFO, "PuntoVentaResource getPuntos: output: {0}", lista.toString());
      
        return lista;
    }
    
    @GET
    @Path("{puntoId: \\d+}")
    public PuntoVentaDTO getPuntoVenta(@PathParam("puntoId") long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PuntoVentaResource getPunto: input: {0}",id);
        PuntoVentaEntity entity= puntologic.getPuntoVenta(id);
         if (entity == null) {
            throw new WebApplicationException("El recurso /puntos/" + id + " no existe.", 404);
        }
         PuntoVentaDTO punto= new PuntoVentaDTO(entity);
        LOGGER.log(Level.SEVERE, "PuntoVentaResource getPuntos: OUTput:", punto.toString());
        return  punto;

    }

    @POST
    public PuntoVentaDTO createPuntoVenta(PuntoVentaDTO punto)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PuntoVentaResource createPunto: input:", punto.toString());
        PuntoVentaDTO nuevo= new PuntoVentaDTO(puntologic.createPuntoVenta(punto.toEntity()));
        LOGGER.log(Level.INFO, "PuntoVentaDTO createPuntoVenta: output:", nuevo.toString());
        return nuevo;
    }

    @PUT
    @Path("{puntoId: \\d+}")
    public PuntoVentaDTO updatePunto(@PathParam("puntoId") Long puntoId, PuntoVentaDTO punto) throws BusinessLogicException {
      
       LOGGER.log(Level.INFO, "PuntoVentaResource updatepunto:input:{0}",new Object[]{puntoId, punto.toString()});
       punto.setId(puntoId);
       
       if(puntologic.getPuntoVenta(puntoId)==null)
       {
           throw new WebApplicationException("El recurso /puntos/"+puntoId+"no existe.",404);
       }
       
       PuntoVentaDTO puntoDTO= new PuntoVentaDTO(puntologic.updatePuntoVenta(puntoId, punto.toEntity()));
      
       LOGGER.log(Level.INFO, "PuntoVentaResource updatePunto: output:", puntoDTO.toString());
        
       return new PuntoVentaDTO();

    }

    @DELETE
    @Path("{puntoId: \\d+}")
    public void deletePunto(@PathParam("puntoId") Long puntoId) throws BusinessLogicException {

        LOGGER.log(Level.INFO, "PuntoVentaResource deletePuntoVenta: input:{0}",puntoId);
        if(puntologic.getPuntoVenta(puntoId)==null)
            
        {
            throw new WebApplicationException("El recurso /puntos/"+ puntoId + "no existe.", 404);
        }
        
        puntologic.deletePuntoVenta(puntoId);
        LOGGER.log(Level.INFO, "PuntoVentaResource deletePunto: output:void");
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
    private List<PuntoVentaDetailDTO> listEntity2DetailDTO(List<PuntoVentaEntity> entityList) {
        List<PuntoVentaDetailDTO> list = new ArrayList<>();
        for (PuntoVentaEntity entity : entityList) {
            list.add(new PuntoVentaDetailDTO(entity));
        }
        return list;
    }
    
    /**
     * Conexión con el servicio de reseñas para un libro. {@link ReviewResource}
     *
     * Este método conecta la ruta de /puntos con las rutas de /calificaciones que
     * dependen del punto, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de las reseñas.
     *
     * @param puntoId El ID del punto de venta con respecto al cual se accede al
     * servicio.
     * @return El servicio de Reseñas para ese libro en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el punto de venta.
     */
    @Path("{puntoId: \\d+}/calificaciones")
    public Class<CalificacionResource> getCalificacionResource(@PathParam("puntoId") Long puntoId) throws BusinessLogicException {
        
        if (puntologic.getPuntoVenta(puntoId)== null) {
            throw new WebApplicationException("El recurso /puntos/" +puntoId + "/calificaciones no existe.", 404);
        }
        return CalificacionResource.class;
    }

}
