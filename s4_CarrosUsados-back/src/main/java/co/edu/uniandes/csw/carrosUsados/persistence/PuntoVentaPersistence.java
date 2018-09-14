/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class PuntoVentaPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(PuntoVentaPersistence.class.getName());
     
      @PersistenceContext(unitName = "CarTeamPU")
     protected EntityManager em;
      
       public PuntoVentaEntity create(PuntoVentaEntity puntoEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo punto de venta.");
        em.persist(puntoEntity);
        LOGGER.log(Level.INFO, "Punto de venta creado");
        return puntoEntity;
    }
      
       
        public List<PuntoVentaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los puntos de venta");
        TypedQuery q = em.createQuery("select u from PuntoVentaEntity u", PuntoVentaEntity.class);
        return q.getResultList();
    }

  
    public PuntoVentaEntity find(Long puntoId) {
        LOGGER.log(Level.INFO, "Consultando el punto de venta con id={0}", puntoId);
        return em.find(PuntoVentaEntity.class, puntoId);
    }

 
    public PuntoVentaEntity update(PuntoVentaEntity puntoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el punto de venta con id={0}", puntoEntity.getId());
        return em.merge(puntoEntity);
    }

   
    public void delete(Long puntoId) {
        LOGGER.log(Level.INFO, "Borrando el punto de venta con id={0}", puntoId);
        PuntoVentaEntity puntoEntity = em.find(PuntoVentaEntity.class, puntoId);
        em.remove(puntoEntity);
    }

 
    public PuntoVentaEntity findName(String name) {
        LOGGER.log(Level.INFO, "Consultando puntos de venta por nombre ", name);
        // Se crea un query para buscar 
        TypedQuery query = em.createQuery("Select e From PuntoVentaEntity e where e.nombre = :nombre", PuntoVentaEntity.class);
        // Se remplaza el placeholder ":isbn" con el valor del argumento 
        query = query.setParameter("nombre", name);
        // Se invoca el query se obtiene la lista resultado
        List<PuntoVentaEntity> sameName = query.getResultList();
        PuntoVentaEntity result;
        if (sameName == null) {
            result = null;
        } else if (sameName.isEmpty()) {
            result = null;
        } else {
            result = sameName.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultarpuntos de venta por nombre ", name);
        return result;
    }

}
