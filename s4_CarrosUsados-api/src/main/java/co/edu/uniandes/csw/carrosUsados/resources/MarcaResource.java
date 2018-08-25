/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import java.util.logging.Logger;

/**
 *
 * @author na.morenoe
 */
public class MarcaResource {
    
    @Inject
    MarcaLogic marcaLogic;
    
    private final static Logger LOGGER = Logger.getLogger(MarcaResource.class.getName());
    
    @GET
    public MarcaDTO getMarca () throws BusinessLogicException {
        return new MarcaDTO marca;
    }
    
    @POST
    public MarcaDTO createMarca(MarcaDTO modelo) throws BusinessLogicException {
        return new MarcaDTO marca;
    }
    
}
