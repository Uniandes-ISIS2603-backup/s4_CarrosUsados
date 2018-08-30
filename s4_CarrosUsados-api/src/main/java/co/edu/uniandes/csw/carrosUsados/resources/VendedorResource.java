/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

/**
 *
 * @author js.bravo
 */
import java.util.logging.Logger;
import co.edu.uniandes.csw.carrosUsados.dtos.VendedorDTO;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

@Path("vendedores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class VendedorResource {
    private final static Logger LOGGER = Logger.getLogger(VendedorResource.class.getName());

    @GET
    public VendedorDTO getVendedor() throws BusinessLogicException {
        return new VendedorDTO();
    }

    @POST
    public VendedorDTO createVendedor(VendedorDTO vendedor) throws BusinessLogicException {
        return new VendedorDTO();
    }
     /**
     * Borra el administrador con el id asociado recibido en la URL.
     *
     * @param vendedoresId Identificador del administrador que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{vendedoresId: \\d+}")
    public void deleteVendedor(@PathParam("vendedoresId") Long vendedoresId)
    {
    }
    
}
