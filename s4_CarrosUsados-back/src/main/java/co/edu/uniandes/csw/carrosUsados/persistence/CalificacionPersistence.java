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
 *
 * @author Daniella Arteaga
 */
@Stateless
public class CalificacionPersistence {
    
    
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());
     
      @PersistenceContext(unitName = "CarTeamPU")
     protected EntityManager em;
      
      
       public CalificacionEntity create(CalificacionEntity reviewEntity) {
        LOGGER.log(Level.INFO, "Creando una calificación nueva");
        em.persist(reviewEntity);
        LOGGER.log(Level.INFO, "Calificación creada");
        return reviewEntity;
    }

  
    public CalificacionEntity update(CalificacionEntity reviewEntity) {
        LOGGER.log(Level.INFO, "Actualizando review con id = {0}", reviewEntity.getId());
        return em.merge(reviewEntity);
    }

  
    public void delete(Long calificacionId) {
        LOGGER.log(Level.INFO, "Borrando review con id = {0}", calificacionId);
        CalificacionEntity reviewEntity = em.find(CalificacionEntity.class, calificacionId);
        em.remove(reviewEntity);
        LOGGER.log(Level.INFO, "Saliendo de borrar El review con id = {0}", calificacionId);
    }

     public List<CalificacionEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las calificaciones");
        TypedQuery q = em.createQuery("select u from CalificacionEntity u", CalificacionEntity.class);
        return q.getResultList();
    }
    
    public CalificacionEntity find(Long calificacionId) {
        LOGGER.log(Level.INFO, "Consultando la calificacion con id = {0}", calificacionId);
        
        return em.find(CalificacionEntity.class, calificacionId);
    }
}
