/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.VendedorEntity;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author js.bravo
 */
public class VendedorPersistence{
    private static final Logger LOGGER = Logger.getLogger(ClientePersistence.class.getName());

    @PersistenceContext(unitName = "CarTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param vendedorEntity objeto vendedor que se creará en la base
     * de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public VendedorEntity create(VendedorEntity vendedorEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo vendedor");
        em.persist(vendedorEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo vendedor");
        return vendedorEntity;
    }

    /**
     * Consulta todos los vendedores de la base de datos.
     *
     * @return Liat con todas las entidades de vendedores de la base de
     * datos.
     */
    public List<VendedorEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los vendedores");
        TypedQuery query = em.createQuery("select u from VendedorEntity u", VendedorEntity.class);
        return query.getResultList();
    }

    /**
     * Busca el vendedor con el id enviado por argumento.
     *
     * @param vendedorId: Id del vendedor a buscar.
     * @return null si no existe ningún vendedor con ese id. Si existe
     * retorna la entidad.
     */
    public VendedorEntity find(Long vendedorId) {
        LOGGER.log(Level.INFO, "Consultando vendedor con id={0}", vendedorId);
        return em.find(VendedorEntity.class, vendedorId);
    }

    /**
     * Actualiza el vendedor cuya entidad es recibida por parámetro.
     *
     * @param vendedorEntity: La entidad del vendedor que se desea
     * actualizar.
     * @return la entidad del vendedor actualizada.
     */
    public VendedorEntity update(VendedorEntity vendedorEntity) {
        LOGGER.log(Level.INFO, "Actualizando vendedor con id = {0}", vendedorEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el vendedor con id = {0}", vendedorEntity.getId());
        return em.merge(vendedorEntity);
    }

    /**
     * Borra un vendedor de la base de datos recibiendo como argumento su id.
     *
     * @param vendedorId: id correspondiente del vendedor a borrar.
     */
    public void delete(Long vendedorId) {
        LOGGER.log(Level.INFO, "Borrando vendedor con id = {0}", vendedorId);
        VendedorEntity entity = em.find(VendedorEntity.class, vendedorId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el vendedor con id = {0}", vendedorId);
    }
    
}
