  /*
   * To change this license header, choose License Headers in Project Properties.
   * To change this template file, choose Tools | Templates
   * and open the template in the editor.
   */
  package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.AutomovilPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.ModeloPersistence;
import co.edu.uniandes.csw.carrosUsados.entities.ModeloEntity;
import co.edu.uniandes.csw.carrosUsados.persistence.PuntoVentaPersistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.inject.Inject;

  /**
   *
   * @author estudiante
   */
   @Stateless
  public class AutomovilLogic {

      private static final Logger LOGGER = Logger.getLogger(AutomovilLogic.class.getName());

      @Inject
      private AutomovilPersistence persistence;

      @Inject
      private ModeloPersistence modeloPersistence;

      @Inject
      private PuntoVentaPersistence puntoVentaPersistence;

      /**
     * Guardar un nuevo automovil
     *
     * @param automovilEntity La entidad de tipo automovil del nuevo automovil a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si la placa es inválida, si el numero de chasis es invalido o si ya existe un automovil con la misma palca o el mismo numero de chasis en la
     * persistencia.
     */
      public AutomovilEntity createAutomovil(Long modeloId, AutomovilEntity automovilEntity) throws BusinessLogicException{
          LOGGER.log(Level.INFO, "Inicio proceso de creacion de un automovil");
          ModeloEntity modelo = modeloPersistence.find(modeloId);
          automovilEntity.setModeloAsociado(modelo);
          //Se chequea que el modelo, el cual contiene los automoviles, no sea nulo y que ya exista en la base de datos
          if((automovilEntity.getModeloAsociado() == null) || (modeloPersistence.find(automovilEntity.getModeloAsociado().getId()) == null )){
              throw new BusinessLogicException("El modelo es invalido");
          }
          if(!validatePlaca(automovilEntity.getPlaca())){
            throw new BusinessLogicException("La placa es invalida");
          }
          if(!validateNumChasis(automovilEntity.getNumChasis())){
            throw new BusinessLogicException("El numero de chasis es invalido");
          }
          if(persistence.findByPlaca(automovilEntity.getPlaca()) != null){
            throw new BusinessLogicException("Ya existe un automovil con la misma placa");
          }
          if(persistence.findByNumChasis(automovilEntity.getNumChasis()) != null){
            throw new BusinessLogicException("Ya existe un automovil con el mismo numero de chasis");
          }

          //Añadi desde aca
          if(automovilEntity.getPuntoVenta() != null){

              PuntoVentaEntity puntoVentaEntity = puntoVentaPersistence.find(automovilEntity.getPuntoVenta().getId());
              if(puntoVentaEntity != null){
                  automovilEntity.setPuntoVenta(puntoVentaEntity);
              }
          }

          persistence.create(automovilEntity);
          LOGGER.log(Level.INFO, "Termina proceso de creación del automovil");

          return automovilEntity;
      }

    /**
     * Devuelve todos los automoviles que hay en la base de datos.
     *
     * @return Lista de entidades de tipo automovil.
     */
    public List<AutomovilEntity> getAutomoviles(Long modeloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los automoviles");
        ModeloEntity modeloEntity = modeloPersistence.find(modeloId);
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los automoviles");
        return modeloEntity.getAutomoviles();
    }

    /**
     * Busca un automovil por ID
     *
     * @param automovilId El id del automovil a buscar
     * @return El automovil encontrado, null si no lo encuentra.
     */
    public AutomovilEntity getAutomovil(Long modeloId, Long automovilId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un automovil");
        if(automovilId == null){
          throw new BusinessLogicException("ID de automovil invalido");
        }
        AutomovilEntity automovilEntity = persistence.find(modeloId, automovilId);
        if(automovilEntity == null){
          LOGGER.log(Level.INFO, "El automovil con el id = {0} no existe", automovilId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar un automovil");
        return automovilEntity;
    }
    
    public AutomovilEntity getAutomovil(Long automovilId) throws BusinessLogicException{
      LOGGER.log(Level.INFO, "Inicia proceso de consultar un  Automovil");
      AutomovilEntity automovilEntity = persistence.find(automovilId);
      if(automovilEntity == null){
        LOGGER.log(Level.INFO, "El  Automovil con el id = {0} no existe", automovilId);
      }
      LOGGER.log(Level.INFO, "Termina proceso de consultar un Automovil según su Id");
      return automovilEntity;
    }
    
    /**
     * Actualizar un automovil por ID
     *
     * @param idAutomovil El ID del automovil a actualizar
     * @param automovilEntity La entidad del automovil con los cambios deseados
     * @return La entidad del automovil luego de actualizarla
     * @throws BusinessLogicException Si la placa es inválida, si el numero de chasis es invalido o si ya existe un automovil con la misma palca o el mismo numero de chasis en la
     * persistencia.
     */
    public AutomovilEntity updateAutomovil(Long idModelo, AutomovilEntity automovilEntity) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un automovil");
        ModeloEntity modeloEntity = modeloPersistence.find(idModelo);
        automovilEntity.setModeloAsociado(modeloEntity);
        
        if((automovilEntity.getModeloAsociado()== null) || (modeloPersistence.find(automovilEntity.getModeloAsociado().getId()) == null )){
            throw new BusinessLogicException("La marca no existe");
        }
        //Se chequea que el modelo, el cual contiene los automoviles, no sea nulo y que ya exista en la base de datos
        if(!validatePlaca(automovilEntity.getPlaca())){
          throw new BusinessLogicException("La placa del automovil no es valida");
        }
        if(!validateNumChasis(automovilEntity.getNumChasis())){
          throw new BusinessLogicException("El numero de chasis del automovil no es valido");
        }/*
        if(persistence.findByPlaca(automovilEntity.getPlaca()) != null){
          throw new BusinessLogicException("Ya existe un automovil con la misma placa");
        }
        if(persistence.findByNumChasis(automovilEntity.getNumChasis()) != null){
          throw new BusinessLogicException("Ya existe un automovil con el mismo numero de chasis");
        }*/
        
        persistence.update(automovilEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizacion de un automovil");
        return automovilEntity;
        
    }

    /**
     * Eliminar un automovil por ID
     *
     * @param automovilId El ID del automovil a eliminar
     * @throws BusinessLogicException si el automovil tiene ficha tecnica asociada
     */
    public void deleteAutomovil(Long modeloId, Long automovilId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el automovil con id = {0}", automovilId);
        AutomovilEntity old = getAutomovil(modeloId, automovilId);
        if (old == null)
        {throw new BusinessLogicException("El automovil con id = " + automovilId + " no esta asociado con la modelo con id = " + modeloId);}
        persistence.delete(old.getId());
        LOGGER.log(Level.INFO, "Termina proceso de borrar el automovil con id = {0}", automovilId);
    }

    /**
     * Metodo que chequea la validez de una placa.
     * @param placa La placa a validar
     * @return verdadero si es valida, falso de lo contrario
     */
      public boolean validatePlaca(String placa){
        if(placa == null || placa.isEmpty()){
          return false;
        }else{
            Matcher m = Pattern.compile("[A-Z][A-Z][A-Z]\\d\\d\\d").matcher(placa); //Chequea que la placa sea valida
            if(m.matches()){
                return true;
            }else{
                return false;
            }

        }
      }

      public boolean validateNumChasis(String numChasis){
        if(numChasis == null || numChasis.isEmpty()){
          return false;
        }else{
            Pattern pattern = Pattern.compile("[A-Z|\\d][A-Z|\\d][A-Z|\\d][A-Z|\\d][A-Z|\\d][A-Z|\\d][A-Z|\\d][A-Z|\\d][A-Z|\\d][A-Z|\\d][A-Z|\\d]\\d\\d\\d\\d\\d\\d");
            //numChasis = "AAAAAAAAAAA111111";
            Matcher matcher = pattern.matcher(numChasis);
            if(matcher.matches()){
                return true;
            }else{
                return false;
            }
        } 
      }

}
