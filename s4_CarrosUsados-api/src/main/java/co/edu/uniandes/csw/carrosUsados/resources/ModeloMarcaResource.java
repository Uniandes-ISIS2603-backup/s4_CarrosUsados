/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import co.edu.uniandes.csw.carrosUsados.ejb.MarcaModeloLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.ModeloLogic;
import co.edu.uniandes.csw.carrosUsados.dtos.ModeloDTO;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;

/**
 * Clase que implementa el recurso "marca/{id}/modelo".
 *
 * @author estudiante
 * @version 1.0
 */
@Path("modelos/{modeloId: \\d+}/marca")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ModeloMarcaResource {
    
    private static final Logger LOGGER = Logger.getLogger(ModeloMarcaResource.class.getName());

    @Inject
    private MarcaModeloLogic marcaModeloLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.

    @Inject
    private ModeloLogic modeloLogic; // Variable para acceder a la lógica de la aplicación. Es una inyección de dependencias.


    @POST
    @Path("{modeloId: \\d+}")
    public ModeloDTO addModelo(@PathParam("marcaId") Long marcaId, @PathParam("modeloId") Long modeloId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "ModeloMarcaResource addModelo: input: marcaID: {0} , modeloId: {1}", new Object[]{marcaId, modeloId});
        if (modeloLogic.getModelo(modeloId) == null) {
            throw new WebApplicationException("El recurso /modelo/" + modeloId + " no existe.", 404);
        }
        ModeloDTO modeloDTO = new ModeloDTO(marcaModeloLogic.addModelo(modeloId, marcaId));
        LOGGER.log(Level.INFO, "MarcaModeloResource addmodelo: output: {0}", modeloDTO.toString());
        return modeloDTO;
    }
    
}
