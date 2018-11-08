/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.persistence;

import co.edu.uniandes.csw.carrosusados.entities.AdministradorEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que maneja la persistencia para Administrador. Se conecta a través
 * Entity Manager de javax.persistance con la base de datos SQL.
 *
 * @author js.bravo
 */
@Stateless
public class AdministradorPersistence {

    private static final Logger LOGGER = Logger.getLogger(AdministradorPersistence.class.getName());

    @PersistenceContext(unitName = "CarTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param administradorEntity objeto administrador que se creará en la base
     *                            de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public AdministradorEntity create(AdministradorEntity administradorEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo administrador");
        em.persist(administradorEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo administrador");
        return administradorEntity;
    }

    /**
     * Consulta todos los administradores de la base de datos.
     *
     * @return Liat con todas las entidades de administradores de la base de
     * datos.
     */
    public List<AdministradorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los administradores");
        TypedQuery query = em.createQuery("select u from AdministradorEntity u", AdministradorEntity.class);
        return query.getResultList();
    }

    /**
     * Busca el administrador con el id enviado por argumento.
     *
     * @param administradorId: Id del administrador a buscar.
     * @return null si no existe ningún administrador con ese id. Si existe
     * retorna la entidad.
     */
    public AdministradorEntity find(Long administradorId) {
        LOGGER.log(Level.INFO, "Consultando administrador con id={0}", administradorId);
        return em.find(AdministradorEntity.class, administradorId);
    }

    /**
     * Busca el administrador con el nombre de usuario enviado por argumento.
     *
     * @param administradorNombreUsuario: Nombre del administrador a buscar.
     * @return null si no existe ningún administrador con ese nombre de usuario. Si existe
     * retorna la entidad.
     */
    public AdministradorEntity findByNombreUsuario(String administradorNombreUsuario) {
        LOGGER.log(Level.INFO, "Consultando administrador con nombreUsuario={0} ", administradorNombreUsuario);
        TypedQuery query = em.createQuery("Select e From AdministradorEntity e where e.nombreUsuario = :nombreUsuario", AdministradorEntity.class);
        query = query.setParameter("nombreUsuario", administradorNombreUsuario);
        List<AdministradorEntity> list = query.getResultList();
        return list != null && !list.isEmpty() ? list.get(0) : null;

    }


    /**
     * Actualiza el administrador cuya entidad es recibida por parámetro.
     *
     * @param administradorEntity: La entidad del administrador que se desea
     *                             actualizar.
     * @return la entidad del administrador actualizada.
     */
    public AdministradorEntity update(AdministradorEntity administradorEntity) {
        LOGGER.log(Level.INFO, "Actualizando administrador con id = {0}", administradorEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el administrador con id = {0}", administradorEntity.getId());
        return em.merge(administradorEntity);
    }

    /**
     * Borra un administrador de la base de datos recibiendo como argumento el
     * id del administrador
     *
     * @param administradorId: id correspondiente del administrador a borrar.
     */
    public void delete(Long administradorId) {
        LOGGER.log(Level.INFO, "Borrando administrador con id = {0}", administradorId);
        AdministradorEntity entity = em.find(AdministradorEntity.class, administradorId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el administrador con id = {0}", administradorId);
    }

}
