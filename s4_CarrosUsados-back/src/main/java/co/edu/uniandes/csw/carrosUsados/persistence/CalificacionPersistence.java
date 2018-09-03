/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class CalificacionPersistence {
    
    
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionPersistence.class.getName());
     
      @PersistenceContext(unitName = "CarTeamPU")
     protected EntityManager em;
      
      
       public CalificacionEntity create(CalificacionEntity reviewEntity) {
        LOGGER.log(Level.INFO, "Creando un review nuevo");
        em.persist(reviewEntity);
        LOGGER.log(Level.INFO, "Review creado");
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
        Query q = em.createQuery("select u from CalificacionEntity u");
        return q.getResultList();
    }
    
    public CalificacionEntity find(Long puntoId, Long calificacionId) {
        LOGGER.log(Level.INFO, "Consultando el review con id = {0} del punto con id = " + puntoId, calificacionId);
        TypedQuery<CalificacionEntity> q = em.createQuery("select p from Entity p where (p.punto.id = :puntoid) and (p.id = :calificacionId)", CalificacionEntity.class);
        q.setParameter("puntoid", puntoId);
        q.setParameter("reviewsId", calificacionId);
        List<CalificacionEntity> results = q.getResultList();
        CalificacionEntity review = null;
        if (results == null) {
            review = null;
        } else if (results.isEmpty()) {
            review = null;
        } else if (results.size() >= 1) {
            review = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el comentario con id = {0} del punto con id =" + puntoId, calificacionId);
        return review;
    }
}
