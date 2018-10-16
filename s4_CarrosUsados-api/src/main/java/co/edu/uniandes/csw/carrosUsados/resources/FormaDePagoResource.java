/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.FormaDePagoDTO;
import co.edu.uniandes.csw.carrosUsados.ejb.FormaDePagoLogic;
import co.edu.uniandes.csw.carrosUsados.entities.FormaDePagoEntity;
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
import javax.ws.rs.WebApplicationException;

/**
 *
 * @author estudiante
 */
@Path("formasDePago")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class FormaDePagoResource {

    private static final Logger LOGGER = Logger.getLogger(FormaDePagoResource.class.getName());
    @Inject
    FormaDePagoLogic formaDePagoLogic;//Variable para acceder a la logica de la aplicacion. Es una inyeccion de dependencia


    /**
     * Crea un nuevo formaDePago con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param formaDePago {@link FormaDePagoDTO} - EL formaDePago que se desea
     * guardar.
     * @return JSON {@link FormaDePagoDTO} - El libro guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando ya existe el formaDePago o la placa o
     * el numero de chasis es inválido o si el modelo asociado ingresado es
     * invalido.
     */
    @POST
    public FormaDePagoDTO createFormaDePago(FormaDePagoDTO formaDePago) throws BusinessLogicException { //Revisar si toca agregar la throws declaration
        
        FormaDePagoDTO nuevoFormaDePagoDTO = new FormaDePagoDTO(formaDePagoLogic.createFormaDePago(formaDePago.toEntity()));
        
        return nuevoFormaDePagoDTO;
    }

    /**
     * Busca y devuelve todos los formasDePago que existen en la aplicacion.
     *
     * @return JSONArray {@link FormaDePagoDetailDTO} - Los formasDePagoe
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<FormaDePagoDTO> getFormaDePagos() throws BusinessLogicException {
        List<FormaDePagoDTO> listaBooks = listEntity2DetailDTO(formaDePagoLogic.getFormasDePago());
        return listaBooks;
    }

    /**
     * Busca el formaDePago con el id asociado recibido en la URL y lo devuelve.
     *
     * @param formasDePagoId Identificador del formaDePago que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link FormaDePagoDetailDTO} - El formaDePago buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el formaDePago.
     */
    @GET
    @Path("{formasDePagoId: \\d+}")
    public FormaDePagoDTO getFormaDePago(@PathParam("formasDePagoId") long formasDePagoId) throws BusinessLogicException {
        FormaDePagoEntity formaDePagoEntity = formaDePagoLogic.getFormaDePago(formasDePagoId);
        if (formaDePagoEntity == null) {
            throw new WebApplicationException("El recurso /formasDePago/" + formasDePagoId + " no existe.", 404);
        }
        FormaDePagoDTO formaDePagoDetailDTO = new FormaDePagoDTO(formaDePagoEntity);
        return formaDePagoDetailDTO;
    }

    /**
     * Actualiza el formaDePago con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param formasDePagoId Identificador del formaDePago que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @param formaDePago {@link FormaDePagoDTO} El formaDePago que se desea guardar.
     * @return JSON {@link FormaDePagoDetailDTO} - El formaDePago guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el formaDePago a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el formaDePago.
     */
    @PUT
    @Path("{formasDePagoId: \\d+}")
    public FormaDePagoDTO updateFormaDePago(@PathParam("formasDePagoId") Long formasDePagoId, FormaDePagoDTO formaDePago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "FormaDePagoResource updateFormaDePago: input: id: {0} , formaDePago: {1}", new Object[]{formasDePagoId, formaDePago.toString()});
        formaDePago.setId(formasDePagoId);
        if (formaDePagoLogic.getFormaDePago(formasDePagoId) == null) {
            throw new WebApplicationException("El recurso /formasDePago/" + formasDePagoId + " no existe.", 404);
        }
        FormaDePagoDTO detailDTO = new FormaDePagoDTO(formaDePagoLogic.updateFormaDePago(formasDePagoId, formaDePago.toEntity()));
        LOGGER.log(Level.INFO, "FormaDePagoResource updateFormaDePago: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Borra el automovi con el id asociado recibido en la URL.
     *
     * @param formasDePagoId Identificador del formaDePago que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el formaDePago.
     */
    @DELETE
    @Path("{formasDePagoId: \\d+}")
    public void deleteFormaDePago(@PathParam("formasDePagoId") Long formasDePagoId) throws BusinessLogicException { //Chequear si es Long con mayuscula o con l minuscula
        LOGGER.log(Level.INFO, "FormaDePagoResource deleteFormaDePago: input: {0}", formasDePagoId);
        FormaDePagoEntity entity = formaDePagoLogic.getFormaDePago(formasDePagoId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /formasDePago/" + formasDePagoId + " no existe.", 404);
        }
        
        formaDePagoLogic.deleteFormaDePago(formasDePagoId);
        LOGGER.info("FormaDePagoResource deleteFormaDePago: output: void");

        //Ver que queda pendientea gregar
    }

    /**
     * Convierte una lista de entidades a DTO.
     *
     * Este método convierte una lista de objetos BookEntity a una lista de
     * objetos BookDetailDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity que
     * vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<FormaDePagoDTO> listEntity2DetailDTO(List<FormaDePagoEntity> formasDePago) {
        List<FormaDePagoDTO> list = new ArrayList<>();
        for (FormaDePagoEntity entity : formasDePago) {
            list.add(new FormaDePagoDTO(entity));
        }
        return list;
    }

}
