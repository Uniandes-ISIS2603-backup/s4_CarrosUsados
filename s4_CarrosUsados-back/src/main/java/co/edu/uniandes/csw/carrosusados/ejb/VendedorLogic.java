/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.ejb;

import co.edu.uniandes.csw.carrosusados.entities.VendedorEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.persistence.VendedorPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author js.bravo
 */
@Stateless
public class VendedorLogic extends BaseLogic {
      private static final Logger LOGGER = Logger.getLogger(VendedorLogic.class.getName());

  @Inject
  private VendedorPersistence persistence;

  /**
 * Persiste un vendedor en el sistema.
 *
 * @param vendedorEntity - La entidad del vendedor a persistir.
  * @throws BusinessLogicException Si la información del vendedor es incorrecta.
 * @return La entidad si esta pudo persistirse correctamente.

 */
  public VendedorEntity createVendedor(VendedorEntity vendedorEntity) throws BusinessLogicException{
      LOGGER.log(Level.INFO, "Inicio proceso de creación de un vendedor");
 if(!(validateNombre(vendedorEntity.getNombre())&&validateNombre(vendedorEntity.getApellido())))
 throw new BusinessLogicException ("La información del vendedor es inválida.");
      persistence.create(vendedorEntity);
      LOGGER.log(Level.INFO, "Termina proceso de creación del vendedor");
      return vendedorEntity;
  }
  /**
   * Devuelve todos los vendedors registrados en el sistema.
   *
   * @return Lista de entidades de tipo vendedor.
   */
  public List<VendedorEntity> getVendedores() {
      LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los vendedores");
      List<VendedorEntity> vendedores = persistence.findAll();
      LOGGER.log(Level.INFO, "Termina proceso de consultar todos los vendedores");
      return vendedores;
  }
  /**
   * Consulta un vendedor según su id.
   *
   * @param vendedorId - El id del vendedor a consultar.
   * @throws BusinessLogicException Si el id enviado por parámetro es inválido.
   * @return El vendedor si fue encontrado, null en caso contrario.
   */
  public VendedorEntity getVendedor(Long vendedorId) throws BusinessLogicException{
      LOGGER.log(Level.INFO, "Inicia proceso de consultar un vendedor");
      VendedorEntity vendedorEntity = persistence.find(vendedorId);
      if(vendedorEntity == null){
        LOGGER.log(Level.INFO, "El vendedor con el id = {0} no existe", vendedorId);
      }
      LOGGER.log(Level.INFO, "Termina proceso de consultar un vendedor según su Id");
      return vendedorEntity;
  }
  /**
   * Actualiza un vendedor según su id.
   *
   * @param vendedorId - El id del vendedor a actualizar.
   * @param vendedorEntity La entidad del vendedor actualizada.
      * @throws BusinessLogicException Si la información del vendedor es incorrecta.
   * @return Si fue exitosa la actualización, retorna La entidad del vendedor actualizada.
   */
  public VendedorEntity updateVendedor(Long vendedorId, VendedorEntity vendedorEntity) throws BusinessLogicException{
      LOGGER.log(Level.INFO, "Inicia proceso de actualizar un vendedor");
      if(!(validateNombre(vendedorEntity.getNombre())&&validateNombre(vendedorEntity.getApellido())))
      throw new BusinessLogicException ("La información del vendedor es inválida.");
      VendedorEntity newEntity = persistence.update(vendedorEntity);
      LOGGER.log(Level.INFO, "Termina proceso de actualización de un vendedor");
      return newEntity;
  }

  /**
   * Eliminar un vendedor según su id.
   *
   * @param vendedorId - El id del vendedor a eliminar.
   * @throws BusinessLogicException Si no existe un vendedor asociado al id enviado por parámetro.
   */
  public void deleteVendedor(Long vendedorId) throws BusinessLogicException {
      LOGGER.log(Level.INFO, "Inicia proceso de borrar el vendedor con id = {0}", vendedorId);
      VendedorEntity vendedorEntity = persistence.find(vendedorId);
      if(vendedorEntity == null){
        LOGGER.log(Level.INFO, "El vendedor con el id = {0} no existe", vendedorId);
      }
      persistence.delete(vendedorId);
      LOGGER.log(Level.INFO, "Termina proceso de borrar el vendedor con id = {0}", vendedorId);
  }

}
