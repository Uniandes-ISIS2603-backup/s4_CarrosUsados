/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.FacturaDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.FichaTecnicaDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.PagoDTO;
import co.edu.uniandes.csw.carrosUsados.ejb.FacturaLogic;
import co.edu.uniandes.csw.carrosUsados.entities.FacturaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

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

    @GET
    public List<PagoDTO> getFacturas() throws BusinessLogicException {

        return new ArrayList<>();
    }
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

    @POST
    public FacturaDTO createFactura(FacturaDTO fact) throws BusinessLogicException {
       //LOGGER.log(Level.INFO, "FichaTecnicaResource createFichaTecnica: input: {0}", fact.toString());
        FacturaDTO nuevaFacturaDTO = new FacturaDTO(facturaLogic.createFactura(fact.toEntity()));
        //LOGGER.log(Level.INFO, "FichaTecnicaResource createFichaTecnica: output: {0}", fact.toString());
        return nuevaFacturaDTO;
    }

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
}
