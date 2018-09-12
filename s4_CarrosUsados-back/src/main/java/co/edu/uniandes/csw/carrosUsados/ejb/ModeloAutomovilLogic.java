/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.persistence.AutomovilPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.ModeloPersistence;
import co.edu.uniandes.csw.carrosUsados.entities.ModeloEntity;
import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
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
public class ModeloAutomovilLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ModeloAutomovilLogic.class.getName());

    @Inject
    ModeloPersistence modeloPersistence;
    
    @Inject
    AutomovilPersistence automovilPersistence;
    
    /**
     * Agregar un automovil al modelo
     *
     * @param automovilId El id automovil a guardar
     * @param modeloId El id del modelo en la cual se va a guardar el
     * automovil.
     * @return El automovil creado.
     */
    public AutomovilEntity addAutomovil(Long automovilId, Long modeloId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un automovil al modelo con id = {0}", modeloId);
        ModeloEntity modeloEntity = modeloPersistence.find(modeloId);
        AutomovilEntity automovilEntity = automovilPersistence.find(automovilId);
        automovilEntity.setModeloAsociado(modeloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle unautomovil al modelo con id = {0}", modeloId);
        return automovilEntity;
    }
    
     /**
     * Retorna todos los automoviles asociados a un modelo
     *
     * @param modeloId El ID de la editorial buscada
     * @return La lista de automoviles del modelo
     */
    public List<AutomovilEntity> getAutomoviles(Long modeloId) {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar los automoviles asociados al modelo con id = {0}", modeloId);
        return modeloPersistence.find(modeloId).getAutomoviles();
    }
    
    /**
     * Retorna un automovil asociado a un modelo
     *
     * @param modeloId El id del modelo a buscar.
     * @param automovilId El id del automovil a buscar
     * @return El automovil encontrado dentro del Modelo.
     * @throws BusinessLogicException Si el automovil no se encuentra en el modelo
     */
    public AutomovilEntity getAutomovil(Long modeloId, Long automovilId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar el automovil con id = {0} del modelo con id = " + modeloId, automovilId);
        List<AutomovilEntity> autos = modeloPersistence.find(modeloId).getAutomoviles();
        AutomovilEntity autoEntity = automovilPersistence.find(automovilId);
        int index = autos.indexOf(autoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de consultar el automovil con id = {0} del modelo con id = " + modeloId, automovilId);
        if (index >= 0) {
            return autos.get(index);
        }
        throw new BusinessLogicException("El automovil no está asociado al modelo");
    }
    
     /**
     * Remplazar automoviles de un modelo
     *
     * @param automoviles Lista de automoviles que serán los del modelo.
     * @param modeloId El id del modelo que se quiere actualizar.
     * @return La lista de automoviles actualizada.
     */
    public List<AutomovilEntity> replaceAutomoviles(Long modeloId, List<AutomovilEntity> autos) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el modelo con id = {0}", modeloId);
        ModeloEntity modeloEntity = modeloPersistence.find(modeloId);
        List<AutomovilEntity> autosList = automovilPersistence.findAll();
        for (AutomovilEntity auto : autosList) {
            if (autos.contains(auto)) {
                auto.setModeloAsociado(modeloEntity);
            } else if (auto.getModeloAsociado() != null && auto.getModeloAsociado().equals(modeloEntity)) {
                auto.setModeloAsociado(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el Modelo con id = {0}", modeloId);
        return autos;
    }

}
