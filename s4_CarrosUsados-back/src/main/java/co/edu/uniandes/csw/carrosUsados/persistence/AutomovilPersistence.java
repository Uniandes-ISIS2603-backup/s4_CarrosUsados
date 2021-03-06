/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
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
public class AutomovilPersistence {

    private static final Logger LOGGER = Logger.getLogger(AutomovilPersistence.class.getName());

    @PersistenceContext(unitName = "CarTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param automovilEntity objeto libro que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */

    public AutomovilEntity create(AutomovilEntity automovilEntity){
        LOGGER.log(Level.INFO, "Creando un automovil nuevo");
        em.persist(automovilEntity);
        LOGGER.log(Level.INFO, "Automovil creado");
        return automovilEntity;
    }

    /**
     * Devuelve todos los automoviles de la base de datos.
     *
     * @return una lista con todos los automoviles que encuentre en la base de datos,
     * "select u from AutomovilEntity u" es como un "select * from AutomovilEntity;" -
     * "SELECT * FROM table_name" en SQL.
     */

    public List<AutomovilEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los automoviles");
        Query q = em.createQuery("select u from AutomovilEntity u");
        return q.getResultList();
    }

    /**
     * Busca si hay algun automovil con el id que se envía de argumento
     *
     * @param automovilId: id correspondiente al libro buscado.
     * @return un automovil.
     */
    public AutomovilEntity find(Long automovilId) {
        LOGGER.log(Level.INFO, "Consultando el automovil con id={0}", automovilId);
        return em.find(AutomovilEntity.class, automovilId);
    }

    /**
     * Actualiza un automovil.
     *
     * @param automovilEntity: el automovil que viene con los nuevos cambios. Por ejemplo
     * el modelo pudo cambiar. En ese caso, se haria uso del método update.
     * @return un automovil con los cambios aplicados.
     */
    public AutomovilEntity update(AutomovilEntity automovilEntity) {
        LOGGER.log(Level.INFO, "Actualizando el automovil con id={0}", automovilEntity.getId());
        return em.merge(automovilEntity);
    }

    /**
     *
     * Borra un automovil de la base de datos recibiendo como argumento el id del
     * automovil
     *
     * @param automovilId: id correspondiente al automovil a borrar.
     */
    public void delete(Long automovilId) {
        LOGGER.log(Level.INFO, "Borrando el automovil con id={0}", automovilId);
        AutomovilEntity automovilEntity = em.find(AutomovilEntity.class, automovilId);
        em.remove(automovilEntity);
    }

    /**
     * Busca si hay algun automovil con la placa que se envía de argumento
     *
     * @param placa: placa del automovil que se está buscando
     * @return null si no existe ningun automovil con la placa del argumento. Si
     * existe alguno devuelve el primero.
     */
    public AutomovilEntity findByPlaca(String placa) {
        LOGGER.log(Level.INFO, "Consultando automoviles por placa ", placa);
        // Se crea un query para buscar automoviles con la placa que recibe el método como argumento. ":placa" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From AutomovilEntity e where e.placa = :placa", AutomovilEntity.class);
        // Se remplaza el placeholder ":placa" con el valor del argumento
        query = query.setParameter("placa", placa);
        // Se invoca el query se obtiene la lista resultado
        List<AutomovilEntity> samePlaca = query.getResultList();
        AutomovilEntity result;
        if (samePlaca == null) {
            result = null;
        } else if (samePlaca.isEmpty()) {
            result = null;
        } else {
            result = samePlaca.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar automoviles por placa ", placa);
        return result;
    }
    /**
     * Busca si hay algun automovil con el mismo numero de chasis que se envía de argumento
     *
     * @param numChasis: numero de chasis del automovil que se está buscando
     * @return null si no existe ningun automovil con el numero de chasis del argumento. Si
     * existe alguno devuelve el primero.
     */
    public AutomovilEntity findByNumChasis(String numChasis) {
        LOGGER.log(Level.INFO, "Consultando automoviles por numero de chasis ", numChasis);
        // Se crea un query para buscar automoviles con el numero de chasis que recibe el método como argumento. ":numChasis" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From AutomovilEntity e where e.numChasis = :numChasis", AutomovilEntity.class);
        // Se remplaza el placeholder ":numChasis" con el valor del argumento
        query = query.setParameter("numChasis", numChasis);
        // Se invoca el query se obtiene la lista resultado
        List<AutomovilEntity> sameNumChasis = query.getResultList();
        AutomovilEntity result;
        if (sameNumChasis == null) {
            result = null;
        } else if (sameNumChasis.isEmpty()) {
            result = null;
        } else {
            result = sameNumChasis.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar automoviles por numero de chasis ", numChasis);
        return result;
    }
    
     /**
     * Buscar un automovil
     *
     * Busca si hay algun automovil asociado a un modelo y con un ID específico
     *
     * @param modeloId El ID de la marca con respecto al cual se busca
     * @param automovilId El ID del modelo buscada
     * @return El modelo encontrada o null. Nota: Si existe una o más modelos
     * devuelve siempre la primera que encuentra
     */
    public AutomovilEntity find(Long modeloId, Long automovilId) {
        LOGGER.log(Level.INFO, "Consultando el automovil con id = {0} de la modelo con id = " + automovilId, modeloId);
       TypedQuery<AutomovilEntity> q = em.createQuery("select p from AutomovilEntity p where (p.modeloAsociado.id = :modeloid) and (p.id = :automovilId)", AutomovilEntity.class);
        q.setParameter("modeloid", modeloId);
       q.setParameter("automovilId", automovilId);
        List<AutomovilEntity> results = q.getResultList();
        AutomovilEntity automovil = null;
        if (results == null) {
            automovil = null;
        } else if (results.isEmpty()) {
            automovil = null;
        } else if (results.size() >= 1) {
            automovil = results.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar el automovil con id = {0} del modelo con id =" + automovilId, modeloId);
        return automovil;
    }
}
