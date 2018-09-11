/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.ModeloEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.ModeloPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.MarcaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author na.morenoe
 */
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
      public ModeloEntity createModelo(ModeloEntity modeloEntity) throws BusinessLogicException{
          LOGGER.log(Level.INFO, "Inicio proceso de creacion de un modelo");
          //Se chequea que la marca, el cual contiene los modelos, no sea nulo y que ya exista en la base de datos
          
          
          if((modeloEntity.getMarca() == null) || (marcaPersistence.find(modeloEntity.getMarca().getId()) == null )){
              throw new BusinessLogicException("El marca no existe");
          }
         /* if(modeloEntity.getNum_puertas() < 1){
            throw new BusinessLogicException("el numero de puertas es invalido");
          }
          if(modeloEntity.getCentrimetros_cubicos() < 100){
            throw new BusinessLogicException("El numero de centrimetros cubicos es invalido");
          }*/
          persistence.create(modeloEntity);
          LOGGER.log(Level.INFO, "Termina proceso de creación del modelo");

          return modeloEntity;
      }

     /**     
     * Devuelve todos los modelos que hay en la base de datos.
     *
     * @return Lista de entidades de tipo modelo.
     */
    public List<ModeloEntity> getModelos() throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los modelos");
        List<ModeloEntity> modelos = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los modelos");
        return modelos;
    }
    
        /**
     * Busca un modelo por ID
     *
     * @param modeloId El id del modelo a buscar
     * @return El modelo encontrado, null si no lo encuentra.
     */
    public ModeloEntity getModelo(Long modeloId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un modelo");
        if(modeloId == null){
          throw new BusinessLogicException("El ID del modelo es invalido");
        }
        ModeloEntity modeloEntity = persistence.find(modeloId);
        if(modeloEntity == null){
          LOGGER.log(Level.INFO, "El modelo con el id = {0} no existe", modeloId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar un modelo");
        return modeloEntity;
    }

     /**
     * Actualizar un modelo por ID
     *
     * @param idModelo El ID del modelo a actualizar
     * @param modeloEntity La entidad del modelo con los cambios deseados
     * @return La entidad del modelo luego de actualizarla
     * @throws BusinessLogicException si la marca no existe, si el numero de puertas esta dentro del rango (0,6) y el numero de centrimetros cubicos es invalido.
     */
    public ModeloEntity updateModelo(Long idModelo, ModeloEntity modeloEntity) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un modelo");
        
          if((modeloEntity.getMarca() == null) || (marcaPersistence.find(modeloEntity.getMarca().getId()) == null )){
              throw new BusinessLogicException("El marca no existe");
          }
          /*if(modeloEntity.getNum_puertas() < 1){
            throw new BusinessLogicException("el numero de puertas es invalido");
          }
          if(modeloEntity.getCentrimetros_cubicos() < 100){
            throw new BusinessLogicException("El numero de centrimetros cubicos es invalido");
          }*/
        ModeloEntity newEntity = persistence.update(modeloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizacion de un modelo");
        return newEntity;
    }

    /**
     * Eliminar un modelo por ID
     *
     * @param modeloId El ID del modelo a eliminar.
     */
    public void deleteModelo(Long modeloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el modelo con id = {0}", modeloId);
        persistence.delete(modeloId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el modelo con id = {0}", modeloId);
    }

    
}
