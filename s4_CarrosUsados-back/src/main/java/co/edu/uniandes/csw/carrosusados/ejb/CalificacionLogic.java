 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carros.usados.ejb;

import co.edu.uniandes.csw.carros.usados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carros.usados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carros.usados.persistence.CalificacionPersistence;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de
 * Calificación.
 * @author Daniella Arteaga
 */

@Stateless
public class CalificacionLogic  {
    
    @Inject private CalificacionPersistence persistencia;
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    
    
    
    
    
    /**
     * Se encarga de crear una calificación en la base de datos.
     *
     * @param entityNew Objeto de CalificacionEntity con los datos nuevos
     * @return Objeto de CalificacionEntity con los datos nuevos y su ID.
     * @throws BusinessLogicException si calificacionId no es el mismo que tiene el
     * entity.
     *si calificacionId es inválido.
     *si ya existe una calificación con calificacionId.
     *Si el comentario ingresado es igual a null.
     *Si la calificación recibida es menor a 0 o mayor a 5.
     */
    public CalificacionEntity createCalificacion(CalificacionEntity entityNew) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia creación de calificación");

        if(entityNew.getComentario()==null)
        {
            throw new BusinessLogicException("Comentario inválido");
        }
        
        if(entityNew.getEstrellas()>5 || (entityNew.getEstrellas()<0))
        {
            throw new BusinessLogicException("Calificación inválida: debe tener un número entre 0 a 5");
        }
        
        CalificacionEntity entity= persistencia.create(entityNew);
        LOGGER.log(Level.INFO, "Finaliza la creación de la calificación");
        return entity;
    }
    
    
     /**
     * Obtiene la lista de los registros de Calificación.
     *
     * @return Colección de objetos de CalificacionEntity.
     */
    public List<CalificacionEntity> getCalificaciones()
    {
        LOGGER.log(Level.INFO, "Inicia consulta de calificaciones");
        List<CalificacionEntity> lista= persistencia.findAll();
        LOGGER.log(Level.INFO, "Termina consulta");
        return lista;
        
    }
    
    
    
      /**
     * Obtiene los datos de una instancia de Calificacion a partir de su ID. 
     * @param calificacionId Identificador de la Reseña a consultar
     * @return Instancia de CalificacionEntity con los datos de la Calificacion consultada.
     * @throws BusinessLogicException si la entity de la calificación no existe o si el ID recibido es igual a null.
     */
    public CalificacionEntity getCalificacion(Long calificacionId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Consulta de calificacion con Id={0}",calificacionId);
        
        if(calificacionId==null)
        {
            throw new BusinessLogicException("Id inválido");
        }
        
        CalificacionEntity calificacionEntity = persistencia.find(calificacionId);
        if (calificacionEntity == null) {
            throw new BusinessLogicException("La calificación no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la calificacion con id = {0}", calificacionId);
        return calificacionEntity;
    }
    
    
    
    /**
     * Actualiza la información de una instancia de Calificacion.
     *
     * @param calificacionEntity Instancia de CalificaciónEntity con los nuevos datos.
     * @param calificacionId id de calificación que se actualizará.
     * @return Instancia de CalificacionEntity con los datos actualizados.
     *
     */
     public CalificacionEntity updateCalificacion(Long calificacionId, CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar  la calificación con id = {0}", calificacionId);
        CalificacionEntity newCalificacionEntity = persistencia.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la calificación con id = {0}", calificacionId);
        return newCalificacionEntity;
    }
     
     
        /**
     * Elimina una instancia de Calificacion de la base de datos.
     *
     * @param calificacionId Identificador de la instancia a eliminar.
     * @throws BusinessLogicException Si el id es inválido (si es igual a null)
     *
     */
        public void deleteCalificacion(Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificacion con id = {0}", calificacionId);
        if(calificacionId==null)
        {
            throw new BusinessLogicException("Id inválido");
        }
    
        persistencia.delete(calificacionId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la calificacion con id = {0}", calificacionId);
    }
}