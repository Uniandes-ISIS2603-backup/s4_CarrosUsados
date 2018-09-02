/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.FichaTecnicaEntity;
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
public class FichaTecnicaPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(FichaTecnicaPersistence.class.getName());
    
    @PersistenceContext("CarTeamPU")
    protected EntityManager em;
    
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param fichaTecnicaEntity objeto fichaTecnica que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    
    public FichaTecnicaEntity create(FichaTecnicaEntity fichaTecnicaEntity){
        LOGGER.log(Level.INFO, "Creando una fichaTecnica nueva");
        em.persist(fichaTecnicaEntity);
        LOGGER.log(Level.INFO, "FichaTecnica creada");
        return fichaTecnicaEntity;
    }
    
    /**
     * Devuelve todos las fichas tecnicas de la base de datos.
     *
     * @return una lista con todos las fichas tecnicas que encuentre en la base de datos,
     * "select u from FichaTecnicaEntity u" es como un "select * from FichaTecnicaEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    
    public List<FichaTecnicaEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos las fichas tecnicas");
        Query q = em.createQuery("select u from FichaTecnicaEntity u");
        return q.getResultList();
    }
    
    /**
     * Busca si hay alguna ficha tecnica con el id que se envía de argumento
     *
     * @param fichaTecnicaId: id correspondiente a la ficha tecnica buscada.
     * @return una ficha tecnica.
     */
    public FichaTecnicaEntity find(Long fichaTecnicaId) {
        LOGGER.log(Level.INFO, "Consultando la ficha tecnica con id={0}", fichaTecnicaId);
        return em.find(FichaTecnicaEntity.class, fichaTecnicaId);       
    }
    
    /**
     * Actualiza una ficha tecnica.
     *
     * @param fichaTecnicaEntity: la ficha tecnica que viene con los nuevos cambios. Por ejemplo
     * el num_airbags pudo cambiar. En ese caso, se haria uso del método update.
     * @return una ficha tecnica con los cambios aplicados.
     */
    public FichaTecnicaEntity update(FichaTecnicaEntity fichaTecnicaEntity) {
        LOGGER.log(Level.INFO, "Actualizando la ficha tecnica con id={0}", fichaTecnicaEntity.getId());
        return em.merge(fichaTecnicaEntity);
    }
    
    /**
     *
     * Borra una ficha tecnica de la base de datos recibiendo como argumento el id de la
     * ficha tecnica
     *
     * @param fichaTecnicaId: id correspondiente a la ficha tecnica a borrar.
     */
    public void delete(Long fichaTecnicaId) {
        LOGGER.log(Level.INFO, "Borrando la ficha tecnica con id={0}", fichaTecnicaId);
        FichaTecnicaEntity fichaTecnicaEntity = em.find(FichaTecnicaEntity.class, fichaTecnicaId);
        em.remove(fichaTecnicaEntity);
    }
   
    
    
}
