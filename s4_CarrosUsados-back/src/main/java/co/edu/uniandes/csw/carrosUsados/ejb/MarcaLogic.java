/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.MarcaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.MarcaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author na.morenoe
 */
@Stateless
public class MarcaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(MarcaLogic.class.getName());

      @Inject
      private MarcaPersistence persistence;
      
    /**
     * Guardar una nueva Marca
     *
     * @param marcaEntity La entidad de tipo marca del nuevo marca a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException 
     */
      public MarcaEntity createMarca(MarcaEntity marcaEntity) throws BusinessLogicException{
          LOGGER.log(Level.INFO, "Inicio proceso de creacion de un marca");
           
          if(marcaEntity.getNombre() == null ){
              throw new BusinessLogicException("El nombre no es valido");
          }
          if(marcaEntity.getPais() == null){
              throw new BusinessLogicException("El País no es valido");
          }
          persistence.create(marcaEntity);
          LOGGER.log(Level.INFO, "Termina proceso de creación del marca");

          return marcaEntity;
      }

     /**     
     * Devuelve todas las marcas que hay en la base de datos.
     *
     * @return Lista de entidades de tipo marca.
     */
    public List<MarcaEntity> getMarcas() throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los marcas");
        List<MarcaEntity> marcas = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los marcas");
        return marcas;
    }
    
        /**
     * Busca una marca por ID
     *
     * @param marcaId El id de la marca a buscar
     * @return La marca encontrada, null si no lo encuentra.
     */
    public MarcaEntity getMarca(Long marcaId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una marca");
        if(marcaId == null){
          throw new BusinessLogicException("El ID de la marca es invalido");
        }
        MarcaEntity marcaEntity = persistence.find(marcaId);
        if(marcaEntity == null){
          LOGGER.log(Level.INFO, "La marca con el id = {0} no existe", marcaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar un modelo");
        return marcaEntity;
    }

     /**
     * Actualizar una marca por ID
     *
     * @param idMarca El ID de la marca a actualizar
     * @param marcaEntity La entidad de la marca con los cambios deseados
     * @return La entidad de la marca luego de actualizarla
     * @throws BusinessLogicException 
     */
    public MarcaEntity updateMarca(Long idMarca, MarcaEntity marcaEntity) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar una marca");
        
          if(marcaEntity.getNombre() == null ){
              throw new BusinessLogicException("El nombre no es valido");
          }
          if(marcaEntity.getPais() == null){
              throw new BusinessLogicException("El País no es valido");
          }
        MarcaEntity newEntity = persistence.update(marcaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizacion de una marca");
        return newEntity;
    }

    /**
     * Eliminar una marca por ID
     *
     * @param marcaId El ID de la marca a eliminar.
     */
    public void deleteMarca(Long marcaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la marca con id = {0}", marcaId);
        persistence.delete(marcaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la marca con id = {0}", marcaId);
    }

}
