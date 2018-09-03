/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.persistence;


import co.edu.uniandes.csw.carrosUsados.entities.PagoEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
public class PagoPersistence {
    private static final Logger LOGGER = Logger.getLogger(PagoPersistence.class.getName());
    
    @PersistenceContext(unitName = "CarTeamPU")
    protected EntityManager em;
    
     public PagoEntity create(PagoEntity pagoEntity) {
        LOGGER.log(Level.INFO, "Creando un pago nuevo");
        em.persist(pagoEntity);
        LOGGER.log(Level.INFO, "Terminando de crear pago nuevo");
        return pagoEntity;
    }
     public List<PagoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando pagos existentes");
        TypedQuery query = em.createQuery("select u from PagoEntity u", PagoEntity.class);
        return query.getResultList();
    }
     public PagoEntity find(Long pagoId) {
        LOGGER.log(Level.INFO, "Consultando pago con la id={0}", pagoId);
        return em.find(PagoEntity.class, pagoId);
    }
     public PagoEntity update(PagoEntity pagoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el pago con id = {0}", pagoEntity.getIdPago());
        LOGGER.log(Level.INFO, "Terminando de actualizar el pago con id = {0}", pagoEntity.getIdPago());
        return em.merge(pagoEntity);
    }
      public void delete(Long pagoId) {
        LOGGER.log(Level.INFO, "Borrando el pago con la id = {0}", pagoId);
        PagoEntity entity = em.find(PagoEntity.class, pagoId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Terminando de borrar el pago con id = {0}", pagoId);
    }
     
}
