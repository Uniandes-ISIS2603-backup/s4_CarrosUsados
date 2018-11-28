/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
 

/**
  * Clase que maneja la persistencia para Calificacion Se conecta a través Entity
 * Manager de javax.persistance con la base de datos SQL.
 * @author Daniella Arteaga
 */
@Stateless
public class CalificacionPersistence {
    
    
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());
     
    /**
     *
     */
    @PersistenceContext(unitName = "CarTeamPU")
     protected EntityManager em;
      
     /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param reviewEntity objeto calificación que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public CalificacionEntity create(CalificacionEntity reviewEntity) {
        LOGGER.log(Level.INFO, "Creando una calificación nueva");
        em.persist(reviewEntity);
        LOGGER.log(Level.INFO, "Calificación creada");
        return reviewEntity;
    }


    /**
     * Actualiza una calificación.
     *
     * @param reviewEntity Entity: calificación que viene con los nuevos cambios. Por ejemplo
     * el nombre pudo cambiar. En ese caso, se haria uso del método update.
     * @return una calificación con los cambios aplicados.
     */
    public CalificacionEntity update(CalificacionEntity reviewEntity) {
        LOGGER.log(Level.INFO, "Actualizando review con id = {0}", reviewEntity.getId());
        return em.merge(reviewEntity);
    }

    /**
     *
     * Borra una calificación de la base de datos recibiendo como argumento el id de la
     * calificación
     *
     * @param calificacionId Id: id correspondiente al libro a borrar.
     */
    public void delete(Long calificacionId) {
        LOGGER.log(Level.INFO, "Borrando review con id = {0}", calificacionId);
        CalificacionEntity reviewEntity = em.find(CalificacionEntity.class, calificacionId);
        em.remove(reviewEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El review con id = {0}", calificacionId);
    }

    
    /**
     * Devuelve todos los calificaciones de la base de datos.
     *
     * @return una lista con todos las calificaciones que encuentre en la base de datos,
     */
    public List<CalificacionEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las calificaciones");
        TypedQuery q = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        return q.getResultList();
    }
    
    public List<CalificacionEntity> findCalPunto(Long puntoId)
    {
        LOGGER.log(Level.INFO, "Consultando todos las calificaciones con punto de venta: {0}"+puntoId);
        TypedQuery q= em.createNamedQuery("select u from CalificacionEntity WHERE u.puntoventa_id = :id", CalificacionEntity.class);
        q.setParameter("id",puntoId);
        return q.getResultList();
       
    }
    
    /**
     * Busca si hay alguna calificación con el id que se envía de argumento
     *
     * @param calificacionId Id: id correspondiente a calificación buscada.
     * @return una calificación.
     */
    public CalificacionEntity find(Long calificacionId) {
        LOGGER.log(Level.INFO, "Consultando la calificacion con id = {0}", calificacionId);
        
        return em.find(CalificacionEntity.class, calificacionId);
    }
}
