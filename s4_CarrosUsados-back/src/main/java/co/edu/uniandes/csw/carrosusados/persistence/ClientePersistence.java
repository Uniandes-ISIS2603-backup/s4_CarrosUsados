/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carros.usados.persistence;

import co.edu.uniandes.csw.carros.usados.entities.ClienteEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author js.bravo
 */
@Stateless
public class ClientePersistence {

    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());

    @PersistenceContext(unitName = "CarTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param clienteEntity objeto cliente que se creará en la base de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ClienteEntity create(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo cliente");
        em.persist(clienteEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo cliente");
        return clienteEntity;
    }

    /**
     * Consulta todos los clientes de la base de datos.
     *
     * @return Liat con todas las entidades de clientes de la base de datos.
     */
    public List<ClienteEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los clientes");
        TypedQuery query = em.createQuery("select u from ClienteEntity u", ClienteEntity.class);
        return query.getResultList();
    }

    /**
     * Busca el cliente con el id enviado por argumento.
     *
     * @param clienteId: Id del cliente a buscar.
     * @return null si no existe ningún cliente con ese id. Si existe retorna la
     * entidad.
     */
    public ClienteEntity find(Long clienteId) {
        LOGGER.log(Level.INFO, "Consultando cliente con id={0}", clienteId);
        return em.find(ClienteEntity.class, clienteId);
    }

    /**
     * Actualiza el cliente cuya entidad es recibida por parámetro.
     *
     * @param clienteEntity: La entidad del cliente que se desea actualizar.
     * @return la entidad del cliente actualizada.
     */
    public ClienteEntity update(ClienteEntity clienteEntity) {
        LOGGER.log(Level.INFO, "Actualizando cliente con id = {0}", clienteEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el cliente con id = {0}", clienteEntity.getId());
        return em.merge(clienteEntity);
    }

    /**
     * Borra un cliente de la base de datos recibiendo como argumento su id.
     *
     * @param clienteId: id correspondiente del cliente a borrar.
     */
    public void delete(Long clienteId) {
        LOGGER.log(Level.INFO, "Borrando cliente con id = {0}", clienteId);
        ClienteEntity entity = em.find(ClienteEntity.class, clienteId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el cliente con id = {0}", clienteId);
    }
}
