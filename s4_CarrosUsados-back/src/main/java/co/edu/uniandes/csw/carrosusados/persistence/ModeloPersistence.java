/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.persistence;

import co.edu.uniandes.csw.carrosusados.entities.ModeloEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author estudiante
 */
@Stateless
public class ModeloPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ModeloPersistence.class.getName());
    
    @PersistenceContext(unitName = "CarTeamPU")
    protected EntityManager em;
      
    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param modeloEntity objeto libro que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ModeloEntity create(ModeloEntity modeloEntity) {
        LOGGER.log(Level.INFO, "Creando una modelo nueva");
        em.persist(modeloEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un modelo nueva");
        return modeloEntity;
    }
    
    /**
     * Devuelve todos los modelos de la base de datos.
     *
     * @return una lista con todos los modelos que encuentre en la base de datos,
     * "select u from ModeloEntity u" es como un "select * from AutomovilEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */
    public List<ModeloEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los Modelos");
        TypedQuery query = em.createQuery("select u from ModeloEntity u", ModeloEntity.class);
        return query.getResultList();
    }
    
    /**
     * Busca si hay algun modelo con el id que se envía de argumento
     *
     * @param modeloId: id correspondiente al modelo buscado.
     * @return un modelo.
     */
    public ModeloEntity find(Long modeloId) {
        LOGGER.log(Level.INFO, "Consultando modelo con id={0}", modeloId);
        return em.find(ModeloEntity.class, modeloId);
    }
    
    /**
     * Actualiza un modelo.
     *
     * @param modeloEntity: el modelo que viene con los nuevos cambios. 
     * @return un modelo con los cambios aplicados.
     */
    public ModeloEntity update(ModeloEntity modeloEntity) {
        LOGGER.log(Level.INFO, "Actualizando modelo con id = {0}", modeloEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el modelo con id = {0}", modeloEntity.getId());
        return em.merge(modeloEntity);
    }
    
     /**
     *
     * Borra un modelo de la base de datos recibiendo como argumento el id del
     * modelo
     *
     * @param modeloId: id correspondiente al modelo a borrar.
     */
    public void delete(Long modeloId) {
        LOGGER.log(Level.INFO, "Borrando modelo con id = {0}", modeloId);
        ModeloEntity entity = em.find(ModeloEntity.class, modeloId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el modelo con id = {0}", modeloId);
    }
    
    /**
     * Buscar un modelo
     *
     * Busca si hay algun modelo asociado a una marca y con un ID específico
     *
     * @param marcaId El ID de la marca con respecto al cual se busca
     * @param modeloId El ID del modelo buscada
     * @return El modelo encontrada o null. Nota: Si existe una o más modelos
     * devuelve siempre la primera que encuentra
     */
    public ModeloEntity find(Long marcaId, Long modeloId) {
        LOGGER.log(Level.INFO, "Consultando el modelo con id = {0} de la marca con id = " + marcaId, modeloId);
        TypedQuery<ModeloEntity> q = em.createQuery("select p from ModeloEntity p where (p.marca.id = :marcaid) and (p.id = :modeloId)", ModeloEntity.class);
        q.setParameter("marcaid", marcaId);
        q.setParameter("modeloId", modeloId);
        List<ModeloEntity> results = q.getResultList();
        ModeloEntity modelo = null;
        if (results == null) {
            modelo = null;
        } else if (results.isEmpty()) {
            modelo = null;
        } else if (results.size() >= 1) {
            modelo = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el modelo con id = {0} de la marca con id =" + marcaId, modeloId);
        return modelo;
    }
}
