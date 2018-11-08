/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.resources;

import co.edu.uniandes.csw.carrosusados.dtos.ArticuloDTO;
import co.edu.uniandes.csw.carrosusados.dtos.ArticuloDetailDTO;
import co.edu.uniandes.csw.carrosusados.ejb.ArticuloAutomovilLogic;
import co.edu.uniandes.csw.carrosusados.ejb.ArticuloLogic;
import co.edu.uniandes.csw.carrosusados.entities.ArticuloEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//Acordarse si es el otro import de POST

/**
 * @author estudiante
 */
@Path("articulos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ArticuloResource {

    private static final Logger LOGGER = Logger.getLogger(ArticuloResource.class.getName());
    @Inject
    ArticuloLogic articuloLogic;//Variable para acceder a la logica de la aplicacion. Es una inyeccion de dependencia

    @Inject
    private ArticuloAutomovilLogic articuloAutomovilLogic;

    /**
     * Crea un nuevo articulo con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param articulo {@link ArticuloDTO} - EL articulo que se desea
     *                 guardar.
     * @return JSON {@link ArticuloDTO} - El libro guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     *                                Error de lógica que se genera cuando ya existe el articulo o la placa o
     *                                el numero de chasis es inválido o si el modelo asociado ingresado es
     *                                invalido.
     */
    @POST
    public ArticuloDTO createArticulo(ArticuloDTO articulo) throws BusinessLogicException { //Revisar si toca agregar la throws declaration

        ArticuloDTO nuevoArticuloDTO = new ArticuloDTO(articuloLogic.createArticulo(articulo.toEntity()));

        return nuevoArticuloDTO;
    }

    /**
     * Busca y devuelve todos los articulos que existen en la aplicacion.
     *
     * @return JSONArray {@link ArticuloDetailDTO} - Los articulos
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ArticuloDetailDTO> getArticuloes() throws BusinessLogicException {
        List<ArticuloDetailDTO> listaBooks = listEntity2DetailDTO(articuloLogic.getArticulos());
        return listaBooks;
    }

    /**
     * Busca el articulo con el id asociado recibido en la URL y lo devuelve.
     *
     * @param articulosId Identificador del articulo que se esta buscando.
     *                    Este debe ser una cadena de dígitos.
     * @return JSON {@link ArticuloDetailDTO} - El articulo buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el articulo.
     */
    @GET
    @Path("{articulosId: \\d+}")
    public ArticuloDTO getArticulo(@PathParam("articulosId") long articulosId) throws BusinessLogicException {
        ArticuloEntity articuloEntity = articuloLogic.getArticulo(articulosId);
        if (articuloEntity == null) {
            throw new WebApplicationException("El recurso /articulos/" + articulosId + " no existe.", 404);
        }
        ArticuloDetailDTO articuloDetailDTO = new ArticuloDetailDTO(articuloEntity);
        return articuloDetailDTO;
    }

    /**
     * Actualiza el articulo con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param articulosId Identificador del articulo que se desea actualizar.
     *                    Este debe ser una cadena de dígitos.
     * @param articulo    {@link ArticuloDTO} El articulo que se desea guardar.
     * @return JSON {@link ArticuloDetailDTO} - El articulo guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el articulo a
     *                                 actualizar.
     * @throws BusinessLogicException  {@link BusinessLogicExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se puede actualizar el articulo.
     */
    @PUT
    @Path("{articulosId: \\d+}")
    public ArticuloDetailDTO updateArticulo(@PathParam("articulosId") Long articulosId, ArticuloDTO articulo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ArticuloResource updateArticulo: input: id: {0} , articulo: {1}", new Object[]{articulosId, articulo.toString()});
        articulo.setId(articulosId);
        if (articuloLogic.getArticulo(articulosId) == null) {
            throw new WebApplicationException("El recurso /articulos/" + articulosId + " no existe.", 404);
        }
        ArticuloDetailDTO detailDTO = new ArticuloDetailDTO(articuloLogic.updateArticulo(articulosId, articulo.toEntity()));
        LOGGER.log(Level.INFO, "ArticuloResource updateArticulo: output: {0}", detailDTO.toString());
        return detailDTO;
    }

    /**
     * Borra el automovi con el id asociado recibido en la URL.
     *
     * @param articulosId Identificador del articulo que se desea borrar.
     *                    Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el articulo.
     */
    @DELETE
    @Path("{articulosId: \\d+}")
    public void deleteArticulo(@PathParam("articulosId") Long articulosId) throws BusinessLogicException { //Chequear si es Long con mayuscula o con l minuscula
        LOGGER.log(Level.INFO, "ArticuloResource deleteArticulo: input: {0}", articulosId);
        ArticuloEntity entity = articuloLogic.getArticulo(articulosId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /articulos/" + articulosId + " no existe.", 404);
        }

        articuloLogic.deleteArticulo(articulosId);
        LOGGER.info("ArticuloResource deleteArticulo: output: void");

        //Ver que queda pendientea gregar
    }

    /**
     * Convierte una lista de entidades a DTO.
     * <p>
     * Este método convierte una lista de objetos BookEntity a una lista de
     * objetos BookDetailDTO (json)
     *
     * @param entityList corresponde a la lista de libros de tipo Entity que
     *                   vamos a convertir a DTO.
     * @return la lista de libros en forma DTO (json)
     */
    private List<ArticuloDetailDTO> listEntity2DetailDTO(List<ArticuloEntity> articulos) {
        List<ArticuloDetailDTO> list = new ArrayList<>();
        for (ArticuloEntity entity : articulos) {
            list.add(new ArticuloDetailDTO(entity));
        }
        return list;
    }

}
