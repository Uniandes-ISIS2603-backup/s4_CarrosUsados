/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.ArticuloEntity;
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
public class ArticuloPersistence {
    
    private static final Logger LOGGER = Logger.getLogger(ArticuloPersistence.class.getName());

    @PersistenceContext(unitName = "CarTeamPU")
    protected EntityManager em;

    /**
     * Método para persisitir la entidad en la base de datos.
     *
     * @param articuloEntity objeto articulo que se creará en la base
     * de datos
     * @return devuelve la entidad creada con un id dado por la base de datos.
     */
    public ArticuloEntity create(ArticuloEntity articuloEntity) {
        LOGGER.log(Level.INFO, "Creando un nuevo articulo");
        em.persist(articuloEntity);
        LOGGER.log(Level.INFO, "Saliendo de crear un nuevo articulo");
        return articuloEntity;
    }

    /**
     * Consulta todos los articulos de la base de datos.
     *
     * @return Liat con todas las entidades de articulos de la base de
     * datos.
     */
    public List<ArticuloEntity> findAll() {
        LOGGER.log(Level.INFO, "Consultando todos los articulos");
        TypedQuery query = em.createQuery("select u from ArticuloEntity u", ArticuloEntity.class);
        return query.getResultList();
    }

    /**
     * Busca el articulo con el id enviado por argumento.
     *
     * @param articuloId: Id del articulo a buscar.
     * @return null si no existe ningún articulo con ese id. Si existe
     * retorna la entidad.
     */
    public ArticuloEntity find(Long articuloId) {
        LOGGER.log(Level.INFO, "Consultando articulo con id={0}", articuloId);
        return em.find(ArticuloEntity.class, articuloId);
    }

    /**
     * Actualiza el articulo cuya entidad es recibida por parámetro.
     *
     * @param articuloEntity: La entidad del articulo que se desea
     * actualizar.
     * @return la entidad del articulo actualizada.
     */
    public ArticuloEntity update(ArticuloEntity articuloEntity) {
        LOGGER.log(Level.INFO, "Actualizando articulo con id = {0}", articuloEntity.getId());
        LOGGER.log(Level.INFO, "Saliendo de actualizar el articulo con id = {0}", articuloEntity.getId());
        return em.merge(articuloEntity);
    }

    /**
     * Borra un articulo de la base de datos recibiendo como argumento el
     * id del articulo
     *
     * @param articuloId: id correspondiente del articulo a borrar.
     */
    public void delete(Long articuloId) {
        LOGGER.log(Level.INFO, "Borrando articulo con id = {0}", articuloId);
        ArticuloEntity entity = em.find(ArticuloEntity.class, articuloId);
        em.remove(entity);
        LOGGER.log(Level.INFO, "Saliendo de borrar el articulo con id = {0}", articuloId);
    }
}