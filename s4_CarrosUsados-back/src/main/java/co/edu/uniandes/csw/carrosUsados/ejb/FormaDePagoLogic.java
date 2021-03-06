  /*
   * To change this license header, choose License Headers in Project Properties.
   * To change this template file, choose Tools | Templates
   * and open the template in the editor.
   */
  package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.FormaDePagoPersistence;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.regex.Pattern;

  /**
   *
   * @author jd.tamara
   */
   @Stateless
  public class FormaDePagoLogic {

      private static final Logger LOGGER = Logger.getLogger(FormaDePagoLogic.class.getName());
      
      private static final Pattern tarjeta = Pattern.compile("^(?:4[0-9]{12}(?:[0-9]{3})?|[25][1-7][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\\d{3})\\d{11})$");

      @Inject
      private FormaDePagoPersistence persistence;

      /**
     * Guardar un nuevo formaDePago
     *
     * @param formaDePagoEntity La entidad de tipo formaDePago del nuevo formaDePago a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el nombre es inválida, si el tipo es invalido o si ya existe un formaDePago con el mismo nombre en la
     * persistencia.
     */
      public FormaDePagoEntity createFormaDePago(FormaDePagoEntity formaDePagoEntity) throws BusinessLogicException{
          LOGGER.log(Level.INFO, "Inicio proceso de creacion de una forma de pago");
                    
          if(!validateNombre(formaDePagoEntity.getNombre())){
            throw new BusinessLogicException("El nombre es invalido");
          }
          if(!validateTipo(formaDePagoEntity.getTipo())){
            throw new BusinessLogicException("El tipo de pago es invalido");
          }
          if(!validateNumero(formaDePagoEntity.getNumero())){
            throw new BusinessLogicException("El numero es invalido");
          }
          if(!validateCodigo(formaDePagoEntity.getCodigo())){
            throw new BusinessLogicException("El codigo es invalido");
          }
          if(!validateFecha(formaDePagoEntity.getFecha())){
            throw new BusinessLogicException("La fecha es invalida");
          }
          if(persistence.findByNombre(formaDePagoEntity.getNombre()) != null){
            throw new BusinessLogicException("Ya existe una formaDePago con el mismo nombre");
          }
          persistence.create(formaDePagoEntity);
          LOGGER.log(Level.INFO, "Termina proceso de creación de la formaDePago");

          return formaDePagoEntity;
      }

    /**
     * Devuelve todas las formas de pago que hay en la base de datos.
     *
     * @return Lista de entidades de tipo formaDePago.
     */
    public List<FormaDePagoEntity> getFormasDePago() throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los formaDePagoes");
        List<FormaDePagoEntity> formaDePagoes = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los formaDePagoes");
        return formaDePagoes;
    }

    /**
     * Busca un formaDePago por ID
     *
     * @param formaDePagoId El id del formaDePago a buscar
     * @return La formaDePago encontrado, null si no lo encuentra.
     */
    public FormaDePagoEntity getFormaDePago(Long formaDePagoId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un formaDePago");
        
        if(formaDePagoId == null){
          throw new BusinessLogicException("ID de formaDePago invalido");
        }
        FormaDePagoEntity formaDePagoEntity = persistence.find(formaDePagoId);
        if(formaDePagoEntity == null){
          LOGGER.log(Level.INFO, "La formaDePago con el id = {0} no existe", formaDePagoId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar un formaDePago");
        return formaDePagoEntity;
    }
    /**
     * Actualizar una formaDePago por ID
     *
     * @param idFormaDePago El ID del formaDePago a actualizar
     * @param formaDePagoEntity La entidad del formaDePago con los cambios deseados
     * @return La entidad del formaDePago luego de actualizarla
     * @throws BusinessLogicException Si la nombre es inválida, si el numero de chasis es invalido o si ya existe un formaDePago con la misma palca o el mismo numero de chasis en la
     * persistencia.
     */
    public FormaDePagoEntity updateFormaDePago(Long idFormaDePago, FormaDePagoEntity formaDePagoEntity) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un formaDePago");
        if(!validateNombre(formaDePagoEntity.getNombre())){
            throw new BusinessLogicException("El nombre es invalido");
          }
          if(!validateTipo(formaDePagoEntity.getTipo())){
            throw new BusinessLogicException("El tipo de pago es invalido");
          }
          if(!validateNumero(formaDePagoEntity.getNumero())){
            throw new BusinessLogicException("El numero es invalido");
          }
          if(!validateCodigo(formaDePagoEntity.getCodigo())){
            throw new BusinessLogicException("El codigo es invalido");
          }
          if(!validateFecha(formaDePagoEntity.getFecha())){
            throw new BusinessLogicException("La fecha es invalida");
          }
          if(persistence.findByNombre(formaDePagoEntity.getNombre()) != null){
            throw new BusinessLogicException("Ya existe una formaDePago con el mismo nombre");
          }
        FormaDePagoEntity newEntity = persistence.update(formaDePagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizacion de un formaDePago");
        return newEntity;
    }

    /**
     * Eliminar un formaDePago por ID
     *
     * @param formaDePagoId El ID del formaDePago a eliminar
     * @throws BusinessLogicException si el formaDePago tiene ficha tecnica asociada
     */
    public void deleteFormaDePago(Long formaDePagoId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el formaDePago con id = {0}", formaDePagoId);
        persistence.delete(formaDePagoId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el formaDePago con id = {0}", formaDePagoId);
    }

    /**
     * Valida el nombre de una forma de pago
     * @param nombre nombre a validar.
     * @return retorna true si es valido, false si no es valido
     */
      public boolean validateNombre(String nombre){
        if(nombre == null || nombre.isEmpty()){
          return false;
        }
        return true;
      }

      /**
       * Valida el tipo de una forma de pago
       * @param tipo tipo a ser validado
       * @return retorna true si es valido, false si no es valido
       */
      public boolean validateTipo(String tipo){
        if(tipo == null || tipo.isEmpty()){
          return true;
        }
        return true;
      }
      
      /**
     * Valida el numero de la tarjeta
     * @param numero numero a validar.
     * @return retorna true si es valido, false si no es valido
     */
      public boolean validateNumero(String numero){
        if(numero == null || numero.isEmpty()){
          return true;
        }        
        return true;
      }

      /**
       * Valida el codigo de la tarjeta de una forma de pago
       * @param codigo codigo a ser validado
       * @return retorna true si es valido, false si no es valido
       */
      public boolean validateCodigo(String codigo){
        if(codigo == null || codigo.isEmpty() || codigo.length() == 3){
          return true;
        }
        return true;
      }
      
      /**
     * Valida la fecha de la tarjeta de una forma de pago
     * @param fecha fecha a validar.
     * @return retorna true si es valido, false si no es valido
     */
      public boolean validateFecha(Date fecha){
        if(fecha != null){
          return false;
        }
        return true;
      }

}
