/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.persistence;

import co.edu.uniandes.csw.carrosusados.entities.FormaDePagoEntity;

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
    
    /**
     * Busca si hay alguna forma de pago con el nombre que se envía de argumento
     *
     * @param nombre: nombre de la forma de pago que se está buscando
     * @return null si no existe ninguna forma de pago con el nombre del argumento. Si
     * existe alguno devuelve el primero.
     */
    public FormaDePagoEntity findByNombre(String nombre) {
        LOGGER.log(Level.INFO, "Consultando formas de pago por nombre ", nombre);
        // Se crea un query para buscar forma de pagoes con la nombre que recibe el método como argumento. ":nombre" es un placeholder que debe ser remplazado
        TypedQuery query = em.createQuery("Select e From FormaDePagoEntity e where e.nombre = :nombre ", FormaDePagoEntity.class);
        // Se remplaza el placeholder ":nombre" con el valor del argumento 
        query = query.setParameter("nombre", nombre);
        // Se invoca el query se obtiene la lista resultado
        List<FormaDePagoEntity> sameTipo = query.getResultList();
        FormaDePagoEntity result;
        if (sameTipo.isEmpty()) {
            result = null;
        } else {
            result = sameTipo.get(0);
        }
        LOGGER.log(Level.INFO, "Saliendo de consultar formas de pago por nombre ", nombre);
        return result;
    }
}
