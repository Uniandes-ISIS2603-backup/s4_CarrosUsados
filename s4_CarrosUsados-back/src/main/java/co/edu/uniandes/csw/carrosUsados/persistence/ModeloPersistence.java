/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.persistence;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import co.edu.uniandes.csw.carrosUsados.entities.ModeloEntity;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author estudiante
 */
@Stateless
public class ModeloPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ModeloPersistence.class.getName());
    
    @PersistenceContext(unitName = "CarTeamPU")
    protected EntityManager em;
    
    public ModeloEntity create(ModeloEntity modeloEntity) {
        LOGGER.log(Level.INFO, "Creando una modelo nueva");
        em.persist(modeloEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un modelo nueva");
        return modeloEntity;
    }
    
    public List<ModeloEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los Modelos");
        TypedQuery query = em.createQuery("select u from ModeloEntity u", ModeloEntity.class);
        return query.getResultList();
    }
    
    public ModeloEntity find(Long modeloId) {
        LOGGER.log(Level.INFO, "Consultando modelo con id={0}", modeloId);
        return em.find(ModeloEntity.class, modeloId);
    }
    
    public ModeloEntity update(ModeloEntity modeloEntity) {
        LOGGER.log(Level.INFO, "Actualizando modelo con id = {0}", modeloEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el modelo con id = {0}", modeloEntity.getId());
        return em.merge(modeloEntity);
    }
    
    public void delete(Long modeloId) {
        LOGGER.log(Level.INFO, "Borrando modelo con id = {0}", modeloId);
        ModeloEntity entity = em.find(ModeloEntity.class, modeloId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el modelo con id = {0}", modeloId);
    }
}
