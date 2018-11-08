/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carros.usados.persistence;

import co.edu.uniandes.csw.carros.usados.entities.PuntoVentaEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Clase que maneja la persistencia para PuntoVenta Se conecta a través Entity.
 * Manager de javax.persistance con la base de datos SQL.
 * @author Daniella Arteaga
 */
@Stateless
public class PuntoVentaPersistence {
    
     private static final Logger LOGGER = Logger.getLogger(PuntoVentaPersistence.class.getName());
     
    /**
     *
     */
    @PersistenceContext(unitName = "CarTeamPU")
     protected EntityManager em;
      
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param puntoEntity  objeto punto que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public PuntoVentaEntity create(PuntoVentaEntity puntoEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo punto de venta.");
        em.persist(puntoEntity);
        LOGGER.log(Level.INFO, "Punto de venta creado");
        return puntoEntity;
    }
      
 /**
     * Devuelve todas los puntos de venta de la base de datos.
     *
     * @return una lista con todas los puntos que encuentre en la base de
     * datos, 
     */
    public List<PuntoVentaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los puntos de venta");
        TypedQuery q = em.createQuery("select u from PuntoVentaEntity u", PuntoVentaEntity.class);
        return q.getResultList();
    }

    /**
     * Busca si hay algun punto con el id que se envía de argumento
     *
     * @param puntoId Id: id correspondiente al punto buscada.
     * @return un punto de venta.
     */
    public PuntoVentaEntity find(Long puntoId) {
        LOGGER.log(Level.INFO, "Consultando el punto de venta con id={0}", puntoId);
        return em.find(PuntoVentaEntity.class, puntoId);
    }

    
    
    /**
     * Actualiza un punto de venta.
     *
     * @param puntoEntity Entity: el punto que viene con los nuevos cambios.
     * Por ejemplo el nombre pudo cambiar. En ese caso, se haria uso del método
     * update.
     * @return un punto con los cambios aplicados.
     */
    public PuntoVentaEntity update(PuntoVentaEntity puntoEntity) {
        LOGGER.log(Level.INFO, "Actualizando el punto de venta con id={0}", puntoEntity.getId());
        return em.merge(puntoEntity);
    }

	
    /**
     *
     * Borra un punto de la base de datos recibiendo como argumento el id
     * del punto.
     *
     * @param puntoId: id correspondiente al punto a borrar.
     */
    public void delete(Long puntoId) {
        LOGGER.log(Level.INFO, "Borrando el punto de venta con id={0}", puntoId);
        PuntoVentaEntity puntoEntity = em.find(PuntoVentaEntity.class, puntoId);
        em.remove(puntoEntity);
    }


}
