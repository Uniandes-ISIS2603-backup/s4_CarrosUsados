/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.resources;

import co.edu.uniandes.csw.carrosUsados.dtos.AutomovilDetailDTO;
import co.edu.uniandes.csw.carrosUsados.ejb.ModeloLogic;
import co.edu.uniandes.csw.carrosUsados.entities.ModeloEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import java.util.logging.Logger;

import co.edu.uniandes.csw.carrosUsados.dtos.ModeloDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;

/**
 *
 * @author na.morenoe
 */
@Path("modelos")
@Produces("application/json")
@Consumes("application/json")
@RequestScoped
public class ModeloResource {

    private final static Logger LOGGER = Logger.getLogger(ModeloResource.class.getName());
    
    @Inject
    ModeloLogic modeloLogic;
    
     /**
     * Busca el modelo con el id asociado recibido en la URL y lo devuelve.
     *
     * @param modeloId Identificador del modelo que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link ModeloDTO} - El modelo buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el modelo.
     */
    @GET
    @Path("{modeloId: \\d+}")
    public ModeloDTO getModelo(@PathParam("marcaId") Long marcaId, @PathParam("modeloId") long modeloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModeloResource getModelo: input: {0}", modeloId);
        ModeloEntity modeloEntity = modeloLogic.getModelo(marcaId, modeloId);
        if (modeloEntity == null) {
            throw new WebApplicationException("El recurso /modelos/" + modeloId + " no existe.", 404);
        }
        ModeloDTO modeloDTO = new ModeloDTO(modeloEntity);
        LOGGER.log(Level.INFO, "ModeloResource getBook: output: {0}", modeloDTO.toString());
        return modeloDTO;
    }

    /**
     * Busca y devuelve todos los modelos que existen en la aplicacion.
     *
     * @return JSONArray {@link ModeloDetailDTO} - Los modelos
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<ModeloDTO> getAllModelo(@PathParam("marcaId") Long marcaId){
        LOGGER.log(Level.INFO,"ModeloResource getModelos: input: {0}", marcaId);
        List<ModeloDTO> listaDTOs = listEntity2DTO(modeloLogic.getModelos(marcaId));
        LOGGER.log(Level.INFO, "ModeloResource getModelos: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }

     /**
     * Crea un nuevo modelo con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param modelo {@link ModeloDTO} - El modelo que se desea
     * guardar.
     * @return JSON {@link ModeloDTO} - El modelo guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando, la marca no existe, el numero de puertas esta dentro del rango (0,6) y los centrimetros cubicos sean > 100
     * invalido.
     */
    @POST
    public ModeloDTO createModelo(@PathParam("marcaId") Long marcaId, ModeloDTO modelo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModeloResource createModelo: input: {0}", modelo.toString());
        ModeloDTO nuevoModeloDTO = new ModeloDTO(modeloLogic.createModelo(marcaId, modelo.toEntity()));
        LOGGER.log(Level.INFO, "ModeloResource createModelo: output: {0}", nuevoModeloDTO.toString());
        return nuevoModeloDTO;
    }

     /**
     * Actualiza el modelo con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param modeloId Identificador del modelo que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @param modelo {@link ModeloDTO} El modelo que se desea guardar.
     * @return JSON {@link ModeloDTO} - El modelo guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el automovil a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar el modelo.
     */
    @PUT
    @Path("{modeloId: \\d+}")
    public ModeloDTO updateModelo(@PathParam("marcaId") Long marcaId, @PathParam("modeloId") Long modeloId, ModeloDTO modelo) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModeloResource updateModelo: input: id: {0} , modelo: {1}", new Object[]{marcaId, modeloId, modelo.toString()});
        modelo.setId(modeloId);
        if (modeloId.equals(modelo.getId())) {
            throw new BusinessLogicException("Los IDs no coinciden");
        }
        ModeloEntity modeloEntity = modeloLogic.getModelo(marcaId, modeloId);
        if (modeloEntity == null)
        {
            throw new WebApplicationException("El recurso /marca/" + marcaId + "/modelos/" + modeloId + " no existe.", 404);
        }
        ModeloDTO modeloDTO = new ModeloDTO(modeloLogic.updateModelo(marcaId, modelo.toEntity()));
        LOGGER.log(Level.INFO, "ModeloResource updateModelo: output: {0}", modeloDTO.toString());
        return modeloDTO;
    }

     /**
     * Borra el modelo con el id asociado recibido en la URL.
     *
     * @param modeloId Identificador del modelo que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra el modelo.
     */
    @DELETE
    @Path("{modeloId: \\d+}")
    public void deleteModelo(@PathParam("marcaId") Long marcaId, @PathParam("modeloId") Long modeloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "ModeloResource deleteModelo: input: {0}", modeloId);
        ModeloEntity entity = modeloLogic.getModelo(marcaId, modeloId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /modelos/" + modeloId + " no existe.", 404);
        }
        modeloLogic.deleteModelo(marcaId ,modeloId);
        
    }
    
    /**
     * Conexión con el servicio de modelos para una marca. {@link ModeloResource}
     *
     * Este método conecta la ruta de /marcas con las rutas de /modelos que
     * dependen del libro, es una redirección al servicio que maneja el segmento
     * de la URL que se encarga de los modelos.
     *
     * @param marcaId El ID de la marca con respecto al cual se accede al
     * servicio.
     * @return El servicio de Modelos para esa marca en particular.\
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la marca.
     */
    @Path("{modeloId: \\d+}/automoviles")
    public Class<AutomovilResource> getAutomovilResource(@PathParam("modeloId") Long modeloId) throws BusinessLogicException{
        if (modeloLogic.getModelo(modeloId) == null) {
            throw new WebApplicationException("El recurso /modelo/" + modeloId + "/automoviles no existe.", 404);
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
