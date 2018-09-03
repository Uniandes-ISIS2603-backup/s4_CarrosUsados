/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.FormaDePagoEntity;
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
public class FormaDePagoPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(FormaDePagoPersistence.class.getName());

    @PersistenceContext(unitName = "CarTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param formaDePagoEntity objeto formaDePago que se creará en la base
     * de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public FormaDePagoEntity create(FormaDePagoEntity formaDePagoEntity) {
        LOGGER.log(Level.INFO, "Creando una nueva forma de pago");
        em.persist(formaDePagoEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear una nueva forma de pago");
        return formaDePagoEntity;
    }

    /**
     * Consulta todas las formas de pago de la base de datos.
     *
     * @return Liat con todas las entidades de formas de pago de la base de
     * datos.
     */
    public List<FormaDePagoEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todas las formas de pago");
        TypedQuery query = em.createQuery("select u from FormaDePagoEntity u", FormaDePagoEntity.class);
        return query.getResultList();
    }

    /**
     * Busca la forma de pago con el id enviado por argumento.
     *
     * @param formaDePagoId: Id de la forma de pago a buscar.
     * @return null si no existe ningúna forma de pago con ese id. Si existe
     * retorna la entidad.
     */
    public FormaDePagoEntity find(Long formaDePagoId) {
        LOGGER.log(Level.INFO, "Consultando forma de pago con id={0}", formaDePagoId);
        return em.find(FormaDePagoEntity.class, formaDePagoId);
    }

    /**
     * Actualiza la forma de pago cuya entidad es recibida por parámetro.
     *
     * @param formaDePagoEntity: La entidad de la forma de pago que se desea
     * actualizar.
     * @return la entidad de la forma de pago actualizada.
     */
    public FormaDePagoEntity update(FormaDePagoEntity formaDePagoEntity) {
        LOGGER.log(Level.INFO, "Actualizando forma de pago con id = {0}", formaDePagoEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el forma de pago con id = {0}", formaDePagoEntity.getId());
        return em.merge(formaDePagoEntity);
    }

    /**
     * Borra una forma de pago de la base de datos recibiendo como argumento el
     * id de la forma de pago
     *
     * @param formaDePagoId: id correspondiente del formaDePago a borrar.
     */
    public void delete(Long formaDePagoId) {
        LOGGER.log(Level.INFO, "Borrando forma de pago con id = {0}", formaDePagoId);
        FormaDePagoEntity entity = em.find(FormaDePagoEntity.class, formaDePagoId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar la forma de pago con id = {0}", formaDePagoId);
    }
}
