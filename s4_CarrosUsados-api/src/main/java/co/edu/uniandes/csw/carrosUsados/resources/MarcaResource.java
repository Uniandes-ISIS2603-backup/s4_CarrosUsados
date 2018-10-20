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
import co.edu.uniandes.csw.carrosUsados.ejb.MarcaLogic;
import co.edu.uniandes.csw.carrosUsados.entities.MarcaEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;

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

    @Inject
    MarcaLogic marcaLogic;
    
    /**
     * Busca la marca con el id asociado recibido en la URL y lo devuelve.
     *
     * @param marcaId Identificador del marca que se esta buscando.
     * Este debe ser una cadena de dígitos.
     * @return JSON {@link MarcaDTO} - El marca buscado
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la marca.
     */
    @GET
    @Path("{marcaId: \\d+}")
    public MarcaDTO getMarca(@PathParam("marcaId") long marcaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MarcaResource getMarca: input: {0}", marcaId);
        MarcaEntity marcaEntity = marcaLogic.getMarca(marcaId);
        if (marcaEntity == null) {
            throw new WebApplicationException("El recurso /modelos/" + marcaId + " no existe.", 404);
        }
        MarcaDTO marcaDTO = new MarcaDTO(marcaEntity);
        LOGGER.log(Level.INFO, "MarcaResource getMarca: output: {0}", marcaDTO.toString());
        return marcaDTO;
    }

     /**
     * Busca y devuelve todas las marcas que existen en la aplicacion.
     *
     * @return JSONArray {@link ModeloDetailDTO} - Las Marcas
     * encontrados en la aplicación. Si no hay ninguno retorna una lista vacía.
     */
    @GET
    public List<MarcaDTO> getAllMarca() throws BusinessLogicException {
        LOGGER.info("MarcaResource getMarca: input: void");
        List<MarcaDTO> listaDTOs = listEntity2DTO(marcaLogic.getMarcas());
        LOGGER.log(Level.INFO, "MarcaResource getMarcas: output: {0}", listaDTOs.toString());
        return listaDTOs;
    }

     /**
     * Crea una nueva marca con la informacion que se recibe en el cuerpo de
     * la petición y se regresa un objeto identico con un id auto-generado por
     * la base de datos.
     *
     * @param marca {@link MarcaDTO} - El marca que se desea
     * guardar.
     * @return JSON {@link MarcaDTO} - El marca guardado con el atributo id
     * autogenerado.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando, la marca no existe, el numero de puertas esta dentro del rango (0,6) y los centrimetros cubicos sean > 100
     * invalido.
     */
    @POST
    public MarcaDTO createMarca(MarcaDTO marca) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MarcaResource createMarca: input: {0}", marca.toString());
        MarcaDTO nuevaMarcaDTO = new MarcaDTO(marcaLogic.createMarca(marca.toEntity()));
        LOGGER.log(Level.INFO, "MarcaResource createModelo: output: {0}", nuevaMarcaDTO.toString());
        return nuevaMarcaDTO;
    }

        /**
     * Actualiza el marca con el id recibido en la URL con la información
     * que se recibe en el cuerpo de la petición.
     *
     * @param marcaId Identificador de la marca que se desea actualizar.
     * Este debe ser una cadena de dígitos.
     * @param marca {@link MarcaDTO} La marca que se desea guardar.
     * @return JSON {@link MarcaDTO} - El marca guardado.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la marca a
     * actualizar.
     * @throws BusinessLogicException {@link BusinessLogicExceptionMapper} -
     * Error de lógica que se genera cuando no se puede actualizar la marca.
     */
    @PUT
    @Path("{marcaId: \\d+}")
    public MarcaDTO updateMarca(@PathParam("marcaId") Long marcaId, MarcaDTO marca) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MarcaResource updateMarca: input: id: {0} , marca: {1}", new Object[]{marcaId, marca.toString()});
        marca.setId(marcaId);
        if (marcaLogic.getMarca(marcaId) == null) {
            throw new WebApplicationException("El recurso /marcas/" + marcaId + " no existe.", 404);
        }
        MarcaDTO marcaDTO = new MarcaDTO(marcaLogic.updateMarca(marcaId, marca.toEntity()));
        LOGGER.log(Level.INFO, "MarcaResource updateMarca: output: {0}", marcaDTO.toString());
        return marcaDTO;
    }

     /**
     * Borra el marca con el id asociado recibido en la URL.
     *
     * @param marcaId Identificador del marca que se desea borrar.
     * Este debe ser una cadena de dígitos.
     * @throws WebApplicationException {@link WebApplicationExceptionMapper} -
     * Error de lógica que se genera cuando no se encuentra la marca.
     */
    @DELETE
    @Path("{marcaId: \\d+}")
    public void deleteMarca(@PathParam("marcaId") Long marcaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "MarcaResource deleteMarca: input: {0}", marcaId);
        MarcaEntity entity = marcaLogic.getMarca(marcaId);
        if (entity == null) {
            throw new WebApplicationException("El recurso /marca/" + marcaId + " no existe.", 404);
        }
        //La siguiente linea puede causar un error:
        //automovilFichaTecnicaLogic.removeFichaTecnica(automovilesId);
        //automovilLogic.deleteAutomovil(automovilesId);
        //LOGGER.info("AutomovilResource deleteAutomovil: output: void");
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
    @Path("{marcaId: \\d+}/modelos")
    public Class<ModeloResource> getModelResource(@PathParam("marcaId") Long marcaId) throws BusinessLogicException{
        if (marcaLogic.getMarca(marcaId) == null) {
            throw new WebApplicationException("El recurso /marcas/" + marcaId + "/modelos no existe.", 404);
        }
        return ModeloResource.class;
    }
    
    private List<MarcaDTO> listEntity2DTO(List<MarcaEntity> marcas) {
        List<MarcaDTO> list = new ArrayList<>();
        for (MarcaEntity entity : marcas) {
            list.add(new MarcaDTO(entity));
        }
        return list;
    }

}
