/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import co.edu.uniandes.csw.carrosUsados.dtos.PagoDTO;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import java.util.logging.Logger;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
/**
 *
 * @author estudiante
 */
@Path("pagos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class PagoResource {
    private final static Logger LOGGER = Logger.getLogger(PagoResource.class.getName());
    public PagoResource()
    {
        
    }
    @GET
    public List<PagoDTO> getPagos()  throws BusinessLogicException
    {
        
       return new ArrayList<>();
    }
   
    @POST
    public PagoDTO createPago(PagoDTO pago) throws BusinessLogicException
    {
        return pago;
    }
    @PUT
    public PagoDTO putPago(PagoDTO pagoPut)throws BusinessLogicException
    {
        return pagoPut;
    }
    @DELETE
    public void deletePago(PagoDTO paguoD)throws BusinessLogicException
    {
        
    }
    
    
    
    
   
    
}
