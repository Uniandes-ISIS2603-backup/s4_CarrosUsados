package co.edu.uniandes.csw.carrosUsados.ejb;


import co.edu.uniandes.csw.carrosUsados.entities.FichaTecnicaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.AutomovilPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.FichaTecnicaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;



@Stateless
public class FichaTecnicaLogic{


  private static final Logger LOGGER = Logger.getLogger(FichaTecnicaLogic.class.getName());

  @Inject
  private FichaTecnicaPersistence persistence;

  @Inject
  private AutomovilPersistence automovilPersistence;

  /**
     * Guardar una nueva ficha tecnica
     *
     * @param fichaTecnicaEntity La entidad de tipo ficha tecnica de la nueva ficha tecnica a persistir.
     * @return La entidad luego de persistirla
     */
    public FichaTecnicaEntity createFichaTecnica(FichaTecnicaEntity fichaTecnicaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la ficha tecnica");
        if(persistence.find(fichaTecnicaEntity.getId()) != null){
            throw new BusinessLogicException("Ya existe una ficha tecnica con ese ID");
        }
        persistence.create(fichaTecnicaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación de la ficha tecnica");
        return fichaTecnicaEntity;
    }

    /**
     * Busca una ficha tecnica por ID
     *
     * @param idFichaTecnica El id de la ficha tecnica a buscar
     * @return La ficha tecnica encontrada, null si no la encuentra.
     */
    public FichaTecnicaEntity getFichaTecnica(Long idFichaTecnica) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar una ficha tecnica");

        if(idFichaTecnica == null){
          throw new BusinessLogicException("ID de ficha tecnica invalido");
        }
        FichaTecnicaEntity fichaTecnicaEntity = persistence.find(idFichaTecnica);
        if(fichaTecnicaEntity == null){
          LOGGER.log(Level.INFO, "La ficha tecnica con el id = {0} no existe",idFichaTecnica);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar una ficha tecnica");
        return fichaTecnicaEntity;
    }

    /**
     * Actualizar una ficha tecnica por ID
     *
     * @param idFichaTecnica El ID de la ficha tecnica a actualizar
     * @param fichaTecnicaEntity La entidad de la ficha tecnica con los cambios deseados
     * @return La entidad de la ficha tecnnica luego de actualizarla
     * @throws BusinessLogicException Si el automovil de la actualización es inválido
     */
    public FichaTecnicaEntity updateFichaTecnica(Long idFichaTecnica, FichaTecnicaEntity fichaTecnicaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar la ficha tecnica con id = {0}", idFichaTecnica);
        if(persistence.find(idFichaTecnica) == null){
            throw new BusinessLogicException("La ficha tecnica no existe");
        }
        FichaTecnicaEntity newEntity = persistence.update(fichaTecnicaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la ficha tecnica con id = {0}", fichaTecnicaEntity.getId());
        return newEntity;
    }



}
