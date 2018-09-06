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
import java.util.logging.Logger;

import co.edu.uniandes.csw.carrosUsados.dtos.ArticuloDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.ArticuloDetailDTO;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

@Path("articulos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ArticuloResource {

    private final static Logger LOGGER = Logger.getLogger(ArticuloResource.class.getName());
    /*
    @GET
    public ArticuloDTO getArticulo() throws BusinessLogicException {
        return new ArticuloDTO();
    }*/
    
    @GET     
    public List<ArticuloDetailDTO> getArticulos()
    {
        return new ArrayList<ArticuloDetailDTO>();        
    }
    
    @PUT
    public ArticuloDTO updateArticulo() throws BusinessLogicException{
        return new ArticuloDTO();
    }

    @POST
    public ArticuloDTO createArticulo(ArticuloDTO articulo) throws BusinessLogicException {
        return new ArticuloDTO();
    }
      /**
     * Borra un articulo con el id asociado recibido en la URL.
     *
     * @param articulosId Identificador del cliente que se desea borrar.
     * Este debe ser una cadena de dígitos.
     */
    @DELETE
    @Path("{articulosId: \\d+}")
    public void deleteArticulo(@PathParam("articulosId") Long articulosId)
    {
    }
}