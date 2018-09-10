/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

/**
 *
 * @author estudiante
 */
import co.edu.uniandes.csw.carrosUsados.dtos.FormaDePagoDetailDTO;
import java.util.logging.Logger;

import co.edu.uniandes.csw.carrosUsados.dtos.FormaDePagoDTO;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

@Path("formasDePago")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FormaDePagoResource {

    private final static Logger LOGGER = Logger.getLogger(FormaDePagoResource.class.getName());

    @GET
    @Path("{formasDePagoId: \\d+}")
    public FormaDePagoDTO getFormaDePago(@PathParam("formasDePagoId") Long formasDePagoId) throws BusinessLogicException {
        return new FormaDePagoDTO();
    }

    @GET
    public List<FormaDePagoDetailDTO> getFormasDePago() {
        return new ArrayList<FormaDePagoDetailDTO>();
    }

    @PUT
    public FormaDePagoDTO updateFormaDePago() throws BusinessLogicException {
        return new FormaDePagoDTO();
    }

    @POST
    public FormaDePagoDTO createFormaDePago(FormaDePagoDTO formaDePago) throws BusinessLogicException {
        return new FormaDePagoDTO();
    }

    /**
     * Borra la forma de pago con el id asociado recibido en la URL.
     *
     * @param formasDePagoId Identificador del cliente que se desea borrar. Este
     * debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{formasDePagoId: \\d+}")
    public void deleteFormaDePago(@PathParam("formasDePagoId") Long formasDePagoId) {
    }
}
