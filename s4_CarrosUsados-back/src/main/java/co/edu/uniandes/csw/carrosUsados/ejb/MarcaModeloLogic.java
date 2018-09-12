/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.ModeloEntity;
import co.edu.uniandes.csw.carrosUsados.entities.MarcaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.ModeloPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.MarcaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class MarcaModeloLogic {
    
    private static final Logger LOGGER = Logger.getLogger(MarcaModeloLogic.class.getName());

    @Inject
    MarcaPersistence marcaPersistence;
    
    @Inject
    ModeloPersistence modeloPersistence;
    
    /**
     * Agregar un modelo a la marca
     *
     * @param modeloId El id modelo a guardar
     * @param marcaId El id de la marca en la cual se va a guardar el
     * modelo.
     * @return El modelo creado.
     */
    public ModeloEntity addModelo(Long modeloId, Long marcaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un modelo a la marca con id = {0}", marcaId);
        MarcaEntity marcaEntity = marcaPersistence.find(marcaId);
        ModeloEntity modeloEntity = modeloPersistence.find(modeloId);
        modeloEntity.setMarca(marcaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un modelo a la marca con id = {0}", marcaId);
        return modeloEntity;
    }
    
     /**
     * Retorna todos los modelos asociados a una marca
     *
     * @param marcaId El ID de la marca buscada
     * @return La lista de modelos de la marca
     */
    public List<ModeloEntity> getModelos(Long marcaId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los modelos asociados a la marca con id = {0}", marcaId);
        return marcaPersistence.find(marcaId).getModelos();
    }
    
    /**
     * Retorna un modelo asociado a una marca
     *
     * @param marcaId El id de la marca a buscar.
     * @param modeloId El id de la marca a buscar
     * @return El modelo encontrado dentro de la Marca.
     * @throws BusinessLogicException Si el modelo no se encuentra e la marca
     */
    public ModeloEntity getModelo(Long marcaId, Long modeloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el modelo con id = {0} de la marca con id = " + marcaId, modeloId);
        List<ModeloEntity> modelos = marcaPersistence.find(marcaId).getModelos();
        ModeloEntity modeloEntity = modeloPersistence.find(modeloId);
        int index = modelos.indexOf(modeloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el modelo con id = {0} de la marca con id = " + marcaId, modeloId);
        if (index >= 0) {
            return modelos.get(index);
        }
        throw new BusinessLogicException("El modelo no está asociado a la marca");
    }
    
     /**
     * Remplazar modelos de la marca
     *
     * @param modelos Lista de modelos que serán los de la marca.
     * @param marcaId El id de la marca que se quiere actualizar.
     * @return La lista de modelos actualizada.
     */
    public List<ModeloEntity> replaceModelos(Long marcaId, List<ModeloEntity> modelos) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la marca con id = {0}", marcaId);
        MarcaEntity marcaEntity = marcaPersistence.find(marcaId);
        List<ModeloEntity> modelosList = modeloPersistence.findAll();
        for (ModeloEntity modelo : modelosList) {
            if (modelos.contains(modelo)) {
                modelo.setMarca(marcaEntity);
            } else if (modelo.getMarca()!= null && modelo.getMarca().equals(marcaEntity)) {
                modelo.setMarca(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la Marca con id = {0}", marcaId);
        return modelos;
    }
    
}
