/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.ejb;

import co.edu.uniandes.csw.carrosusados.entities.MarcaEntity;
import co.edu.uniandes.csw.carrosusados.entities.ModeloEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.persistence.MarcaPersistence;
import co.edu.uniandes.csw.carrosusados.persistence.ModeloPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author na.morenoe
 */
@Stateless
public class ModeloLogic {

    private static final Logger LOGGER = Logger.getLogger(ModeloLogic.class.getName());

    @Inject
    private ModeloPersistence persistence;

    @Inject
    private MarcaPersistence marcaPersistence;

    /**
     * Guardar un nuevo Modelo
     *
     * @param modeloEntity La entidad de tipo modelo del nuevo modelo a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException si la marca no existe, si el numero de puertas esta dentro del rango (0,6) y el numero de centrimetros cubicos es invalido.
     */
    public ModeloEntity createModelo(Long marcaId, ModeloEntity modeloEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicio proceso de creacion de un modelo");
        MarcaEntity marca = marcaPersistence.find(marcaId);
        modeloEntity.setMarca(marca);

        if ((modeloEntity.getMarca() == null) || (marcaPersistence.find(modeloEntity.getMarca().getId()) == null)) {
            throw new BusinessLogicException("El marca no existe");
        }

        persistence.create(modeloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del modelo");

        return modeloEntity;
    }

    /**
     * Devuelve todos los modelos que hay en la base de datos.
     *
     * @return Lista de entidades de tipo modelo.
     */
    public List<ModeloEntity> getModelos(Long marcaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los modelos");
        MarcaEntity marcaEntity = marcaPersistence.find(marcaId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los modelos");
        return marcaEntity.getModelos();
    }

    /**
     * Busca un modelo por ID
     *
     * @param modeloId El id del modelo a buscar
     * @return El modelo encontrado, null si no lo encuentra.
     */
    public ModeloEntity getModelo(Long marcaId, Long modeloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un modelo");
        if (modeloId == null) {
            throw new BusinessLogicException("El ID del modelo es invalido");
        }
        ModeloEntity modeloEntity = persistence.find(marcaId, modeloId);
        if (modeloEntity == null) {
            LOGGER.log(Level.INFO, "El modelo con el id = {0} no existe", modeloId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar un modelo");
        return modeloEntity;
    }

    public ModeloEntity getModelo(Long modeloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un modelo");
        ModeloEntity modeloEntity = persistence.find(modeloId);
        if (modeloEntity == null) {
            LOGGER.log(Level.INFO, "El modelo con el id = {0} no existe", modeloId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar un modelo según su Id");
        return modeloEntity;
    }

    /**
     * Actualizar un modelo por ID
     *
     * @param idMarca      El ID del modelo a actualizar
     * @param modeloEntity La entidad del modelo con los cambios deseados
     * @return La entidad del modelo luego de actualizarla
     * @throws BusinessLogicException si la marca no existe, si el numero de puertas esta dentro del rango (0,6) y el numero de centrimetros cubicos es invalido.
     */
    public ModeloEntity updateModelo(Long idMarca, ModeloEntity modeloEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un modelo");
        MarcaEntity marcaEntity = marcaPersistence.find(idMarca);
        modeloEntity.setMarca(marcaEntity);
        if ((modeloEntity.getMarca() == null) || (marcaPersistence.find(modeloEntity.getMarca().getId()) == null)) {
            throw new BusinessLogicException("La marca no existe");
        }
        persistence.update(modeloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizacion de un modelo");
        return modeloEntity;

    }

    /**
     * Eliminar un modelo por ID
     *
     * @param modeloId El ID del modelo a eliminar.
     */
    public void deleteModelo(Long marcaId, Long modeloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el modelo con id = {0}", modeloId);
        ModeloEntity old = getModelo(marcaId, modeloId);
        if (old == null) {
            throw new BusinessLogicException("El modelo con id = " + modeloId + " no esta asociado con la marca con id = " + marcaId);
        }
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el modelo con id = {0}", modeloId);
    }


}
