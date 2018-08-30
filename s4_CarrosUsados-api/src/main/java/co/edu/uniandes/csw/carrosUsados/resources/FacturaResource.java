/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.FacturaDTO;
import co.edu.uniandes.csw.carrosUsados.dtos.PagoDTO;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;

/**
 *
 * @author estudiante
 */
public class FacturaResource {
   
    public FacturaResource()
    {
        
    }
    @GET
    public List<PagoDTO> getFacturas()  throws BusinessLogicException
    {
        
       return new ArrayList<>();
    }
   
    @POST
    public FacturaDTO createFactura(FacturaDTO fact) throws BusinessLogicException
    {
        return fact;
    } 
    @PUT
    public FacturaDTO putFactura(FacturaDTO facturaPut)throws BusinessLogicException
    {
        return facturaPut;
    }
    @DELETE
    public void deleteFactura(FacturaDTO facturaD)throws BusinessLogicException
    {
        
    }
}
