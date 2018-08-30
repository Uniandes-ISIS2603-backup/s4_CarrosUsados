/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.AutomovilDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.AutomovilDetailDTO;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
//Acordarse si es el otro import de POST
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author estudiante
 */

@Path("automoviles")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class AutomovilResource {
    
   private static final Logger LOGGER = Logger.getLogger(AutomovilResource.class.getName());
  //@Inject
  //AutomovilLogic automovilLogic;//Variable para acceder a la logica de la aplicacion. Es una inyeccion de dependencia
   @POST
  public AutomovilDTO createAutomovil(AutomovilDTO automovil) throws BusinessLogicException{ //Revisar si toca agregar la throws declaration
   // LOGGER.log(Level.INFO, "AutomovilResource createAutomovil: input: {0}", automovil.toString());
   // LOGGER.log(Level.INFO,"AutomovilResource createAutomovil: output: {0}", nuevoAutomovilDTO.toString())
    return new AutomovilDTO();
  }
  
  @GET
  public List<AutomovilDetailDTO> getAutomoviles(){
      
      return new ArrayList<AutomovilDetailDTO>();
  }
  
  @GET
  @Path("{automovilesId: \\d+}")
  public AutomovilDTO getAutomovil(@PathParam("automovilesId") long automovilesId) throws BusinessLogicException{
      
      return new AutomovilDTO();
  }
  
  @PUT
  @Path("{automovilesId: \\d+}")
  public AutomovilDTO updateAutomovil(@PathParam("automovilesId") long automovilesId, AutomovilDTO automovil){
      
      return automovil;
  }
  
  @DELETE
  @Path("{automovilesId: \\d+}")
  public void deleteAutomovil(@PathParam("automovilesId") long automovilesId) throws BusinessLogicException{ //Chequear si es Long con mayuscula o con l minuscula
    LOGGER.log(Level.INFO, "AutomovilResource deleteAutomovil: input: {0}", automovilesId);
    //Invoca la logica para eliminar el automovil
    //automovilLogic.deleteAutomovil(automovilesId);
    LOGGER.info("AutomovilResource deleteAutomovil: output: void");
    
    //Ver que queda pendientea gregar
  }
  
  
}
