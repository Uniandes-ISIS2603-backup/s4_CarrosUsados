/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.FacturaEntity;

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
public class FacturaPersistence {
    private static final Logger LOGGER = Logger.getLogger(FacturaPersistence.class.getName());
    
    @PersistenceContext(unitName = "CarTeamPU")
    protected EntityManager em;
    
     public FacturaEntity create(FacturaEntity facturaEntity) {
        LOGGER.log(Level.INFO, "Creando una factura nuevo");
        em.persist(facturaEntity);
        LOGGER.log(Level.INFO, "Terminando de crear la factura nuevo");
        return facturaEntity;
    }
     public List<FacturaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando facturas existentes");
        TypedQuery query = em.createQuery("select u from FacturaEntity u", FacturaEntity.class);
        return query.getResultList();
    }
     public FacturaEntity find(Long facturaId) {
        LOGGER.log(Level.INFO, "Consultando factura con la id={0}", facturaId);
        return em.find(FacturaEntity.class, facturaId);
    }
     public FacturaEntity update(FacturaEntity facturaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la factura con id = {0}", facturaEntity.getIdFactura());
        LOGGER.log(Level.INFO, "Terminando de actualizar la factura con id = {0}", facturaEntity.getIdFactura());
        return em.merge(facturaEntity);
    }
      public void delete(Long facturaId) {
        LOGGER.log(Level.INFO, "Borrando la factura con la id = {0}", facturaId);
        FacturaEntity entity = em.find(FacturaEntity.class, facturaId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Terminando de borrar la factura con id = {0}", facturaId);
    }
}
