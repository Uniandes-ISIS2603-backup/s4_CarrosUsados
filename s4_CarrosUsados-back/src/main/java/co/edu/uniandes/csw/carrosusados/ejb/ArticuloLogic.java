  /*
   * To change this license header, choose License Headers in Project Properties.
   * To change this template file, choose Tools | Templates
   * and open the template in the editor.
   */
  package co.edu.uniandes.csw.carrosusados.ejb;

  import co.edu.uniandes.csw.carrosusados.entities.ArticuloEntity;
  import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
  import co.edu.uniandes.csw.carrosusados.persistence.ArticuloPersistence;
  import co.edu.uniandes.csw.carrosusados.persistence.AutomovilPersistence;

  import javax.ejb.Stateless;
  import javax.inject.Inject;
  import java.util.List;
  import java.util.logging.Level;
  import java.util.logging.Logger;

  /**
   *
   * @author jd.tamara
   */
   @Stateless
  public class ArticuloLogic {

      private static final Logger LOGGER = Logger.getLogger(ArticuloLogic.class.getName());

      @Inject
      private ArticuloPersistence persistence;

      @Inject
      private AutomovilPersistence automovilPersistence;

      /**
     * Guardar un nuevo articulo
     *
     * @param articuloEntity La entidad de tipo articulo del nuevo articulo a persistir.
     * @return La entidad luego de persistirla
     * @throws BusinessLogicException Si el nombre es inválida, si el tipo es invalido o si ya existe un articulo con el mismo nombre en la
     * persistencia.
     */
      public ArticuloEntity createArticulo(ArticuloEntity articuloEntity) throws BusinessLogicException{
          LOGGER.log(Level.INFO, "Inicio proceso de creacion de una forma de pago");
                    
          if((articuloEntity.getAutomovil() == null) || (automovilPersistence.find(articuloEntity.getAutomovil().getId()) == null )){
              throw new BusinessLogicException("El automovil es invalido o no fue asociado correctamente");
          }
          if(!validateDescripcion(articuloEntity.getDescripcion())){
            throw new BusinessLogicException("La descripcion no es invalida");
          }
          if(!validateUbicacion(articuloEntity.getUbicacion())){
            throw new BusinessLogicException("La ubicacion no es invalida");
          }
          if(!validatePrecio(articuloEntity.getPrecio())){
            throw new BusinessLogicException("El precio no es invalido");
          }
          if(!articuloEntity.isDisponibilidad()){
            throw new BusinessLogicException("El articulo deberia estar disponible");
          }
          persistence.create(articuloEntity);
          LOGGER.log(Level.INFO, "Termina proceso de creación de la articulo");

          return articuloEntity;
      }

    /**
     * Devuelve todos los articulos que hay en la base de datos.
     *
     * @return Lista de entidades de tipo articulo.
     */
    public List<ArticuloEntity> getArticulos(){
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los articuloes");
        List<ArticuloEntity> articuloes = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los articuloes");
        return articuloes;
    }

    /**
     * Busca un articulo por ID
     *
     * @param articuloId El id del articulo a buscar
     * @return La articulo encontrado, null si no lo encuentra.
     */
    public ArticuloEntity getArticulo(Long articuloId) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un articulo");
        
        if(articuloId == null){
          throw new BusinessLogicException("ID de articulo invalido");
        }
        ArticuloEntity articuloEntity = persistence.find(articuloId);
        if(articuloEntity == null){
          LOGGER.log(Level.INFO, "La articulo con el id = {0} no existe", articuloId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar un articulo");
        return articuloEntity;
    }
    /**
     * Actualizar una articulo por ID
     *
     * @param idArticulo El ID del articulo a actualizar
     * @param articuloEntity La entidad del articulo con los cambios deseados
     * @return La entidad del articulo luego de actualizarla
     * @throws BusinessLogicException Si la nombre es inválida, si el numero de chasis es invalido o si ya existe un articulo con la misma palca o el mismo numero de chasis en la
     * persistencia.
     */
    public ArticuloEntity updateArticulo(Long idArticulo, ArticuloEntity articuloEntity) throws BusinessLogicException{
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un articulo");
        if(!validateDescripcion(articuloEntity.getDescripcion())){
            throw new BusinessLogicException("La descripcion no es invalida");
          }
          if(!validateUbicacion(articuloEntity.getUbicacion())){
            throw new BusinessLogicException("La ubicacion no es invalida");
          }
          if(!validatePrecio(articuloEntity.getPrecio())){
            throw new BusinessLogicException("El precio no es invalido");
          }
          if(articuloEntity.isDisponibilidad() && articuloEntity.getFactura() != null){
              throw new BusinessLogicException("Si un pago es realizado disponibilidad deberia estar en falso");
          }
        ArticuloEntity newEntity = persistence.update(articuloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizacion de un articulo");
        return newEntity;
    }

    /**
     * Eliminar un articulo por ID
     *
     * @param articuloId El ID del articulo a eliminar
     * @throws BusinessLogicException si el articulo tiene ficha tecnica asociada
     */
    public void deleteArticulo(Long articuloId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el articulo con id = {0}", articuloId);
        persistence.delete(articuloId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el articulo con id = {0}", articuloId);
    }

    /**
     * Valida el nombre de un articulo
     * @param nombre el nombre a validar.
     * @return retorna true si es valido, false si no es valido.
     */
      public boolean validateDescripcion(String nombre){
        if(nombre == null || nombre.isEmpty()){
          return false;
        }
        return true;
      }
      /**
       * Valida la ubicacion de un articulo
       * @param ubi ubicacion a validar.
       * @return retorna true si es valido, false si no es valido.
       */
      public boolean validateUbicacion(String ubi){
        if(ubi == null || ubi.isEmpty()){
          return false;
        }
        return true;
      }
      
      /**
       * Valida el precio de un articulo
       * @param precio precio a validad.
       * @return retorna true si es valido, false si no es valido.
       */
      public boolean validatePrecio(String precio){
        if(precio == null || precio.isEmpty()){
          return false;
        }
        try{
            int i = Integer.parseInt(precio);
            if(i > 0)
            return true;
        }
        catch(Exception e){
            return false;
        }        
        return false;
      }

}
