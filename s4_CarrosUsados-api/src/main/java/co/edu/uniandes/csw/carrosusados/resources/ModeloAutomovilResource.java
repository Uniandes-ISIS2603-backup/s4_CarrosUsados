/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.resources;

import co.edu.uniandes.csw.carrosusados.dtos.ModeloDTO;
import co.edu.uniandes.csw.carrosusados.ejb.ModeloAutomovilLogic;
import co.edu.uniandes.csw.carrosusados.entities.ModeloEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author estudiante
 */
@Path("modelos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ModeloAutomovilResource {

    private final static Logger LOGGER = Logger.getLogger(ModeloAutomovilResource.class.getName());

    @Inject
    ModeloAutomovilLogic modeloAutomovilLogic;

    /**
     * Busca el modelo con el id asociado recibido en la URL y lo devuelve.
     *
     * @param modeloId Identificador del modelo que se esta buscando.
     *                 Este debe ser una cadena de dígitos.
     * @return JSON {@link ModeloDTO} - El modelo buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el modelo.
     */

    @GET
    @Path("{modeloId: \\d+}")
    public ModeloDTO getModelo(@PathParam("modeloId") long modeloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModeloAutomovilResource getModelo: input: {0}", modeloId);
        ModeloEntity entity = modeloAutomovilLogic.getModelo(modeloId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /modelos/" + modeloId + " no existe.", 404);
        }
        ModeloDTO dto = new ModeloDTO(entity);
        LOGGER.log(Level.INFO, "ModeloAutomovilResource getModelo: output: {0}", dto.toString());
        return dto;
    }

    /**
     * Busca y devuelve todos los modelos que existen en la aplicacion.
     *
     * @return JSONArray {@link ModeloDetailDTO} - Los modelos
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ModeloDTO> getModelos() throws BusinessLogicException {
        LOGGER.info("ModeloAutomovilResource getModelos: input: void");
        List<ModeloDTO> lista = new ArrayList<>();
        for (ModeloEntity entity : modeloAutomovilLogic.getModelos()) {
            lista.add(new ModeloDTO(entity));
        }
        LOGGER.log(Level.INFO, "ModeloAutomovilResource getModelos: output: {0}", lista.toString());
        return lista;
    }

    /**
     * Crea un nuevo modelo con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param modelo {@link ModeloDTO} - El modelo que se desea
     *               guardar.
     * @return JSON {@link ModeloDTO} - El modelo guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     *                                Error de lógica que se genera cuando, la marca no existe, el numero de puertas esta dentro del rango (0,6) y los centrimetros cubicos sean > 100
     *                                invalido.
     */
    @POST
    public ModeloDTO createModelo(ModeloDTO modelo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModeloAutomovilResource createModelo: input: {0}", modelo.toString());

        ModeloDTO dto = new ModeloDTO(modeloAutomovilLogic.createModelo(modelo.toEntity()));

        LOGGER.log(Level.INFO, "ModeloAutomovilResource createModelo: output: {0}", dto.toString());
        return dto;
    }

    /**
     * Actualiza el modelo con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param modeloId Identificador del modelo que se desea actualizar.
     *                 Este debe ser una cadena de dígitos.
     * @param modelo   {@link ModeloDTO} El modelo que se desea guardar.
     * @return JSON {@link ModeloDTO} - El modelo guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el automovil a
     *                                 actualizar.
     * @throws BusinessLogicException  {@link BusinessLogicExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se puede actualizar el modelo.
     */
    @PUT
    @Path("{modeloId: \\d+}")
    public ModeloDTO updateModelo(@PathParam("modeloId") Long modeloId, ModeloDTO modelo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModeloAutomovilResource updateModelo: input: id: {0} , modelo: {1}", new Object[]{modeloId, modelo.toString()});
        modelo.setId(modeloId);
        if (modeloAutomovilLogic.getModelo(modeloId) == null) {
            throw new WebApplicationException("El recurso /modelos/" + modeloId + " no existe.", 404);
        }
        ModeloDTO dto = new ModeloDTO(modeloAutomovilLogic.updateModelo(modeloId, modelo.toEntity()));
        LOGGER.log(Level.INFO, "ModeloAutomovilResource updatemodelo: output: {0}", dto.toString());
        return dto;
    }

    /**
     * Borra el modelo con el id asociado recibido en la URL.
     *
     * @param modeloId Identificador del modelo que se desea borrar.
     *                 Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra el modelo.
     */
    @DELETE
    @Path("{modeloId: \\d+}")
    public void deleteModelo(@PathParam("modeloId") Long modeloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModeloAutomovilResource deleteModelo: input: {0}", modeloId);
        ModeloEntity entity = modeloAutomovilLogic.getModelo(modeloId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /modelos/" + modeloId + " no existe.", 404);
        }
        modeloAutomovilLogic.deleteModelo(modeloId);
        LOGGER.info("ModeloResource deleteModelo: output: void");
    }

    /**
     * Conexión con el servicio de modelos para una modelo. {@link ModeloResource}
     * <p>
     * Este método conecta la ruta de /modelos con las rutas de /modelos que
     * dependen del libro, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de los modelos.
     *
     * @param modeloId El ID de la marca con respecto al cual se accede al
     *                 servicio.
     * @return El servicio de Modelos para esa marca en particular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     *                                 Error de lógica que se genera cuando no se encuentra la marca.
     */
    @Path("{modeloId: \\d+}/automoviles")
    public Class<AutomovilResource> getAutomovilResource(@PathParam("modeloId") Long modeloId) throws BusinessLogicException {
        if (modeloAutomovilLogic.getModelo(modeloId) == null) {
            throw new WebApplicationException("El recurso /modelos/" + modeloId + "/automoviles no existe.", 404);
        }
        return AutomovilResource.class;
    }

    private List<ModeloDTO> listEntity2DTO(List<ModeloEntity> modelos) {
        List<ModeloDTO> list = new ArrayList<>();
        for (ModeloEntity entity : modelos) {
            list.add(new ModeloDTO(entity));
        }
        return list;
    }

}
