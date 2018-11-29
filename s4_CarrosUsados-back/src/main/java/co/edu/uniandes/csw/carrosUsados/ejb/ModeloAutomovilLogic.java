/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.ModeloEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.ModeloPersistence;
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
  private ModeloPersistence persistence;

  /**
 * Persiste un modelo en el sistema.
 *
 * @param modeloEntity - La entidad del modelo a persistir.
  * @throws BusinessLogicException Si la información del cliente es incorrecta.
 * @return La entidad si esta pudo persistirse correctamente.

 */
  public ModeloEntity createModelo(ModeloEntity modeloEntity) throws BusinessLogicException{
      LOGGER.log(Level.INFO, "Inicio proceso de creación de un modelo");
      persistence.create(modeloEntity);
      LOGGER.log(Level.INFO, "Termina proceso de creación del cliente");
      return modeloEntity;
  }
  /**
   * Devuelve todos los modelos registrados en el sistema.
   *
   * @return Lista de entidades de tipo modelo.
   */
  public List<ModeloEntity> getModelos() {
      LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los modelos");
      List<ModeloEntity> modelos = persistence.findAll();
      LOGGER.log(Level.INFO, "Termina proceso de consultar todos los modelos");
      return modelos;
  }
  /**
   * Consulta un modelo según su id.
   *
   * @param modeloId - El id del modelo a consultar.
   * @throws BusinessLogicException Si el id enviado por parámetro es inválido.
   * @return El modelo si fue encontrado, null en caso contrario.
   */
  public ModeloEntity getModelo(Long modeloId) throws BusinessLogicException{
      LOGGER.log(Level.INFO, "Inicia proceso de consultar un modelo");
      ModeloEntity modeloEntity = persistence.find(modeloId);
      if(modeloEntity == null){
        LOGGER.log(Level.INFO, "El modelo con el id = {0} no existe", modeloId);
      }
      LOGGER.log(Level.INFO, "Termina proceso de consultar un modelo según su Id");
      return modeloEntity;
  }
  /**
   * Actualiza un modelo según su id.
   *
   * @param modeloId - El id del modelo a actualizar.
   * @param modeloEntity La entidad del modelo actualizada.
      * @throws BusinessLogicException Si la información del modelo es incorrecta.
   * @return Si fue exitosa la actualización, retorna La entidad del modelo actualizada.
   */
  public ModeloEntity updateModelo(Long modeloId, ModeloEntity modeloEntity) throws BusinessLogicException{
      LOGGER.log(Level.INFO, "Inicia proceso de actualizar un modelo");
      ModeloEntity newEntity = persistence.update(modeloEntity);
      LOGGER.log(Level.INFO, "Termina proceso de actualización de un modelo");
      return newEntity;
  }

  /**
   * Eliminar un modelo según su id.
   *
   * @param modeloId - El id del modelo a eliminar.
   * @throws BusinessLogicException Si no existe un modelo asociado al id enviado por parámetro.
   */
  public void deleteModelo(Long modeloId) throws BusinessLogicException {
      LOGGER.log(Level.INFO, "Inicia proceso de borrar el modelo con id = {0}", modeloId);
      ModeloEntity modeloEntity = persistence.find(modeloId);
      if(modeloEntity == null){
        LOGGER.log(Level.INFO, "El modelo con el id = {0} no existe", modeloId);
      }
      persistence.delete(modeloId);
      LOGGER.log(Level.INFO, "Termina proceso de borrar el modelo con id = {0}", modeloId);
  }

    
}
