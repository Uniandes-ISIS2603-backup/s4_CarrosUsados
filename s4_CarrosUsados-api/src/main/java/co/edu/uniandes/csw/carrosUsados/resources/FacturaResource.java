/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.FacturaDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.FacturaDetailDTO;
import co.edu.uniandes.csw.carrosUsados.ejb.FacturaLogic;
import co.edu.uniandes.csw.carrosUsados.entities.FacturaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.ArrayList;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;
import java.util.List;

/**
 *
 * @author estudiante
 */
@Path("factura")
public class FacturaResource {

    @Inject
    private FacturaLogic facturaLogic;
    
    
    public FacturaResource() {

    }
 
    /**
     * Busca y devuelve todas las facturas que existen en la aplicacion.
     *
     * @return JSONArray {@link FacturaEntity} - Los libros encontrados en la
     * aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<FacturaEntity> getFacturas() throws BusinessLogicException {

        //LOGGER.info("BookResource getBooks: input: void");
        List<FacturaEntity> listaBooks = facturaLogic.getFacturas();
        //LOGGER.log(Level.INFO, "BookResource getBooks: output: {0}", listaBooks.toString());
        return listaBooks;
    }
    /**
     * Busca la factura con el id asociado recibido en la URL y lo devuelve.
     *
     * @param facturaId
     * @return JSON {@link FacturaDTO} - El libro buscado
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la factura.
     */
     @GET
    @Path("{facturaId: \\d+}")
    public FacturaDTO getBook(@PathParam("facturaId") Long facturaId) throws BusinessLogicException {
        //LOGGER.log(Level.INFO, "BookResource getBook: input: {0}", booksId);
        FacturaEntity facturaEntity = facturaLogic.getFactura(facturaId);
        if (facturaEntity == null) {
            throw new WebApplicationException("El recurso /books/" + facturaId + " no existe.", 404);
        }
        FacturaDTO facturaDTO = new FacturaDTO(facturaEntity);
       // LOGGER.log(Level.INFO, "BookResource getBook: output: {0}", bookDetailDTO.toString());
        return facturaDTO;
    }

       /**
     * Crea una nueva Factura con la informacion que se recibe en el cuerpo de la
     * petición y se regresa un objeto identico con un id auto-generado por la
     * base de datos.
     *
     * @param fact
     * @return JSON {@link FacturaDTO} - La factura guardada con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} 
     */
    @POST
    public FacturaDTO createFactura(FacturaDTO fact) throws BusinessLogicException {
       //LOGGER.log(Level.INFO, "FichaTecnicaResource createFichaTecnica: input: {0}", fact.toString());
        FacturaDTO nuevaFacturaDTO = new FacturaDTO(facturaLogic.createFactura(fact.toEntity()));
        //LOGGER.log(Level.INFO, "FichaTecnicaResource createFichaTecnica: output: {0}", fact.toString());
        return nuevaFacturaDTO;
    }
     /**
     * Actualiza la factura con el id recibido en la URL con la información que se
     * recibe en el cuerpo de la petición.
     *
     * @param facturaId
     * @param fact
     * @return JSON {@link FacturaDTO} - El libro guardada.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la factura 
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el libro.
     */
    @PUT
    @Path("{facturaId: \\d+}")
    public FacturaDTO putFactura(@PathParam("facturaId") Long facturaId, FacturaDTO fact) throws BusinessLogicException {
         //LOGGER.log(Level.INFO, "BookResource updateBook: input: id: {0} , book: {1}", new Object[]{booksId, book.toString()});
        fact.setId(facturaId);
        if (facturaLogic.getFactura(facturaId) == null) {
            throw new WebApplicationException("El recurso /factura/" + facturaId + " no existe.", 404);
        }
        FacturaDTO detailDTO = new FacturaDTO(facturaLogic.updateFactura(facturaId, fact.toEntity()));
       // LOGGER.log(Level.INFO, "BookResource updateBook: output: {0}", detailDTO.toString());
        return detailDTO;
    }
/**
     * Borra la factura con el id asociado recibido en la URL.
     *
     * @param facturaId
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la factura.
     */
    @DELETE
    @Path("{facturaId: \\d+}")
    public void deleteFactura(@PathParam("facturaId") Long facturaId) throws BusinessLogicException {
        //LOGGER.log(Level.INFO, "BookResource deleteBook: input: {0}", booksId);
        FacturaEntity entity = facturaLogic.getFactura(facturaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /factura/" + facturaId + " no existe.", 404);
        }
       
        facturaLogic.deleteFactura(facturaId);
        //LOGGER.info("BookResource deleteBook: output: void");
    }
    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BookEntity a una lista de
     * objetos BookDetailDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<FacturaDetailDTO> listEntity2DetailDTO(List<FacturaEntity> entityList) {
        List<FacturaDetailDTO> list = new ArrayList<>();
        for (FacturaEntity entity : entityList) {
            list.add(new FacturaDetailDTO(entity));
        }
        return list;
    }
}
