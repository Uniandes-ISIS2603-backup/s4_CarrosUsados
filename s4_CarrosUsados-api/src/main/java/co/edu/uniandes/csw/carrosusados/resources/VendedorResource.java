/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.resources;

/**
 * @author js.bravo
 */

import co.edu.uniandes.csw.carrosusados.dtos.VendedorDTO;
import co.edu.uniandes.csw.carrosusados.ejb.VendedorLogic;
import co.edu.uniandes.csw.carrosusados.entities.VendedorEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.mappers.BusinessLogicExceptionMapper;
import co.edu.uniandes.csw.carrosusados.mappers.WebApplicationExceptionMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("vendedores")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped

public class VendedorResource {

    private final static Logger LOGGER = Logger.getLogger(VendedorResource.class.getName());
    @Inject
    VendedorLogic vendedorLogic;

    /**
     * Busca y devuelve todos los vendedores que existen en la aplicacion.
     *
     * @return JSONArray {@link VendedorDTO} - Los vendedores
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<VendedorDTO> getVendedores() throws BusinessLogicException {
        LOGGER.info("VendedorResource getVendedores: input: void");
        List<VendedorDTO> lista = new ArrayList<>();
        for (VendedorEntity entity : vendedorLogic.getVendedores()) {
            lista.add(new VendedorDTO(entity));
        }
        LOGGER.log(Level.INFO, "VendedorResource getVendedores: output: {0}", lista.toString());
        return lista;
    }

    /**
     * Busca el vendedor con el id asociado recibido como parámetro URL y lo devuelve.
     *
     * @param vendedoresId Identificador del vendedor que se esta buscando.
     *                     Este debe ser una cadena de dígitos.
     * @return JSON {@link VendedorDTO} - El vendedor buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el vendedor.
     */
    @GET
    @Path("{vendedoresId: \\d+}")
    public VendedorDTO getVendedor(@PathParam("vendedoresId") long vendedoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VendedorResource getVendedor: input: {0}", vendedoresId);
        VendedorEntity entity = vendedorLogic.getVendedor(vendedoresId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /vendedores/" + vendedoresId + " no existe.", 404);
        }
        VendedorDTO dto = new VendedorDTO(entity);
        LOGGER.log(Level.INFO, "VendedorResource getVendedor: output: {0}", dto.toString());
        return dto;
    }

    /**
     * Crea un nuevo vendedor con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param vendedor {@link VendedorDTO} - El vendedor que se desea
     *                 guardar.
     * @return JSON {@link VendedorDTO} - El vendedor guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     *                                Error de lógica que se genera cuando los nombres y/o apellidos no tienen el formato esperado.
     */
    @POST
    public VendedorDTO createVendedor(VendedorDTO vendedor) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VendedorResource createVendedor: input: {0}", vendedor.toString());

        VendedorDTO dto = new VendedorDTO(vendedorLogic.createVendedor(vendedor.toEntity()));

        LOGGER.log(Level.INFO, "VendedorResource createVendedor: output: {0}", dto.toString());
        return dto;
    }

    /**
     * Actualiza el vendedor con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param vendedoresId Identificador del vendedor que se desea actualizar.
     *                     Este debe ser una cadena de dígitos.
     * @param vendedor     {@link VendedorDTO} El vendedor con el que se desea actualizar.
     * @return JSON {@link VendedorDTO} - El vendedor actualizado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el vendedor a
     *                                 actualizar.
     * @throws BusinessLogicException  {@link BusinessLogicExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se puede actualizar el vendedor.
     */
    @PUT
    @Path("{vendedoresId: \\d+}")
    public VendedorDTO updateVendedor(@PathParam("vendedoresId") Long vendedoresId, VendedorDTO vendedor) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VendedorResource updateVendedor: input: id: {0} , vendedor: {1}", new Object[]{vendedoresId, vendedor.toString()});
        vendedor.setId(vendedoresId);
        if (vendedorLogic.getVendedor(vendedoresId) == null) {
            throw new WebApplicationException("El recurso /vendedores/" + vendedoresId + " no existe.", 404);
        }
        VendedorDTO dto = new VendedorDTO(vendedorLogic.updateVendedor(vendedoresId, vendedor.toEntity()));
        LOGGER.log(Level.INFO, "VendedorResource updateVendedor: output: {0}", dto.toString());
        return dto;
    }

    /**
     * Borra el vendedor con el id asociado recibido en la URL.
     *
     * @param vendedoresId Identificador del vendedor que se desea
     *                     borrar. Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} Error de lógica que se genera cuando no se encuentra el vendedor.
     */
    @DELETE
    @Path("{vendedoresId: \\d+}")
    public void deleteVendedor(@PathParam("vendedoresId") Long vendedoresId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "VendedorResource deleteVendedor: input: {0}", vendedoresId);
        VendedorEntity entity = vendedorLogic.getVendedor(vendedoresId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /vendedores/" + vendedoresId + " no existe.", 404);
        }
        vendedorLogic.deleteVendedor(vendedoresId);
        LOGGER.info("VendedorResource deleteVendedor: output: void");
    }

}
