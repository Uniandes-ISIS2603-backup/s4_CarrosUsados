/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import co.edu.uniandes.csw.carrosUsados.dtos.MarcaDTO;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */
@Path("marcas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class MarcaResource {
    
    private final static Logger LOGGER = Logger.getLogger(MarcaResource.class.getName());
    
    @GET
    @Path("{editorialsId: \\d+}")
    public MarcaDTO getMarca () throws BusinessLogicException {
        return new MarcaDTO();
    }
    
    @GET
    public MarcaDTO getAllMarca() throws BusinessLogicException {
        return new MarcaDTO();
    }
    
    @POST
    public MarcaDTO createMarca(MarcaDTO modelo) throws BusinessLogicException {
        return new MarcaDTO();
    }
    
    @PUT
    public MarcaDTO updateMarca() throws BusinessLogicException{
        return new MarcaDTO();
    }
            
    @DELETE
    public void deleteMarca() throws BusinessLogicException{
        
    }
            
    
}
