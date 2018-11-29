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
 * Clase que implementa el recurso "puntos".
 * @author Daniella Arteaga
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
    
    
    
        /**
     * Busca y devuelve el punto con el ID recibido en la URL
     * 
     *
     * @param id  El ID del punto del cual se busca
     * @return {@link PuntoVentaDTO} - el punto buscado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el punto.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el punto.
     */
    @GET
    @Path("{puntoId: \\d+}")
    public PuntoVentaDetailDTO getPuntoVenta(@PathParam("puntoId") long id) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PuntoVentaResource getPunto: input: {0}",id);
        PuntoVentaEntity entity= puntologic.getPuntoVenta(id);
         if (entity == null) {
            throw new WebApplicationException("El recurso /puntos/" + id + " no existe.", 404);
        }
         PuntoVentaDetailDTO punto= new PuntoVentaDetailDTO(entity);
        LOGGER.log(Level.SEVERE, "PuntoVentaResource getPuntos: ouput:", punto.toString());
        return  punto;

    }

    
    /**
     * Crea un nuevo punto con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param punto {@link PuntoVentaDTO} - EL punto que se desea guardar.
     * @return JSON {@link PuntoVentaDTO} - El punto guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el punto o el id es
     * inválido o si el punto ingresada es invalido.
     */
    @POST
    public PuntoVentaDTO createPuntoVenta(PuntoVentaDTO punto)throws BusinessLogicException {
        LOGGER.log(Level.INFO, "PuntoVentaResource createPunto: input:{0}", punto.toString());
        PuntoVentaDTO nuevo= new PuntoVentaDTO(puntologic.createPuntoVenta(punto.toEntity()));
        LOGGER.log(Level.INFO, "PuntoVentaDTO createPuntoVenta: output:{0}", nuevo.toString());
        return nuevo;
    }

    
        /**
     * Actualiza un punto de venta con la informacion que se recibe en el cuerpo de la
     * petición y se regresa el objeto actualizado.
     *
     * @param puntoId Id El ID del libro del cual se guarda la reseña
     * @param punto {@link PuntoVentaDTO} - el punto de venta que se desea guardar.
     * @return JSON {@link PuntoVentaDTO} - wl punto actualizada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el punto a actualizar.
     */
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
      
       LOGGER.log(Level.INFO, "PuntoVentaResource updatePunto: output:{0}", puntoDTO.toString());
        
       return puntoDTO;

    }

    
       /**
     * Borra el punto con el id asociado recibido en la URL.
     *
     * @param puntoId Identificador del punto que se desea borrar. Este debe ser
     * una cadena de dígitos.
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     * cuando el punto de venta no existe.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el punto.
     */
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
     * @param entityList corresponde a la lista de puntos de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de puntos en forma DTO (json)
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
     * de la URL que se encarga de las calificaciones.
     *
     * @param puntoId El ID del punto de venta con respecto al cual se accede al
     * servicio.
     * @return El servicio de Calificaciones para ese punto de venta en paricular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el punto de venta.
     */
    @Path("{puntoId: \\d+}/calificaciones")
    public Class<PuntoVentaCalificacionResource> getCalificacionPuntoVentaResource(@PathParam("puntoId") Long puntoId) throws BusinessLogicException {
        
        if (puntologic.getPuntoVenta(puntoId)== null) {
            throw new WebApplicationException("El recurso /puntos/" +puntoId + "/calificaciones no existe.", 404);
        }
        return PuntoVentaCalificacionResource.class;
    }

}
