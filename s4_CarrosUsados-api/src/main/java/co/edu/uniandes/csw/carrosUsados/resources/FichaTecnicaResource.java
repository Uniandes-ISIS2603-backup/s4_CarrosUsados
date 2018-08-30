/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.AutomovilDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.FichaTecnicaDTO;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("fichastecnicas")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FichaTecnicaResource {
    
    private static final Logger LOGGER = Logger.getLogger(FichaTecnicaResource.class.getName());
    
     @POST
  public FichaTecnicaDTO createFichaTecnica(FichaTecnicaDTO ficha_tecnica) throws BusinessLogicException{ //Revisar si toca agregar la throws declaration
   // LOGGER.log(Level.INFO, "AutomovilResource createAutomovil: input: {0}", automovil.toString());
   // LOGGER.log(Level.INFO,"AutomovilResource createAutomovil: output: {0}", nuevoAutomovilDTO.toString())
    return new FichaTecnicaDTO();
  }
    
  @GET
  @Path("{fichastecnicasId: \\d+}")
  public FichaTecnicaDTO getFichaTecnica(@PathParam("fichastecnicasId") long fichastecnicasId) throws BusinessLogicException{
      
      return new FichaTecnicaDTO();
  }
  
  @PUT
  @Path("{fichastecnicasId: \\d+}")
  public FichaTecnicaDTO updateFichaTecnica(@PathParam("fichastecnicasId") long fichastecnicasId, FichaTecnicaDTO ficha_tecnica){
      
      return ficha_tecnica;
  }
  
  @DELETE
  @Path("{fichastecnicasId: \\d+}")
  public void deleteFichaTecnica(@PathParam("fichastecnicasId") long fichasTecnicasId){
      
  }
}
