/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.ejb.ModeloLogic;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.logging.Logger;

import co.edu.uniandes.csw.carrosUsados.dtos.ModeloDTO;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;

/**
 *
 * @author na.morenoe
 */

@Path("modelos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ModeloResource {

    private final static Logger LOGGER = Logger.getLogger(ModeloResource.class.getName());

    @GET
    @Path("{editorialsId: \\d+}")
    public ModeloDTO getModelo() throws BusinessLogicException {
        return new ModeloDTO();
    }

    @GET
    public ModeloDTO getAllModelo() throws BusinessLogicException {
        return new ModeloDTO();
    }

    @POST
    public ModeloDTO createModelo(ModeloDTO modelo) throws BusinessLogicException {
        return new ModeloDTO();
    }

    @PUT
    public ModeloDTO updateModelo() throws BusinessLogicException {
        return new ModeloDTO();
    }

    @DELETE
    public void deleteModelo() throws BusinessLogicException {

    }

}
