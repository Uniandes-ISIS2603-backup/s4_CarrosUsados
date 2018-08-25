/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.ejb.ModeloLogic;
import java.util.logging.Logger;

/**
 *
 * @author na.morenoe
 */

@Path("modelos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ModeloResource {
    
    @Inject
    ModeloLogic modeloLogic;
    
    private final static Logger LOGGER = Logger.getLogger(ModeloResource.class.getName());
    
    @GET
    public ModeloDTO getModelo () throws BusinessLogicException {
        return new ModeloDTO modelo;
    }
    
    @POST
    public ModeloDTO createModelo(ModeloDTO modelo) throws BusinessLogicException {
        return new ModeloDTO modelo;
    }
    
    
    
}
