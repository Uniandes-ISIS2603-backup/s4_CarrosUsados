/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carros.usados.persistence;

import co.edu.uniandes.csw.carros.usados.entities.MarcaEntity;
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
public class MarcaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(MarcaPersistence.class.getName());
    
    @PersistenceContext(unitName = "CarTeamPU")
    protected EntityManager em;
    
    public MarcaEntity create(MarcaEntity marcaEntity) {
        LOGGER.log(Level.INFO, "Creando una marca nueva");
        em.persist(marcaEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una marca nueva");
        return marcaEntity;
    }
    
    public List<MarcaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las Marcas");
        TypedQuery query = em.createQuery("select u from MarcaEntity u", MarcaEntity.class);
        return query.getResultList();
    }
    
    public MarcaEntity find(Long marcaId) {
        LOGGER.log(Level.INFO, "Consultando marca con id={0}", marcaId);
        return em.find(MarcaEntity.class, marcaId);
    }
    
    public MarcaEntity update(MarcaEntity marcaEntity) {
        LOGGER.log(Level.INFO, "Actualizando marca con id = {0}", marcaEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el marca con id = {0}", marcaEntity.getId());
        return em.merge(marcaEntity);
    }
    
    public void delete(Long marcaId) {
        LOGGER.log(Level.INFO, "Borrando marca con id = {0}", marcaId);
        MarcaEntity entity = em.find(MarcaEntity.class, marcaId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la marca con id = {0}", marcaId);
    }
    
}
