/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.ejb;


import co.edu.uniandes.csw.carrosusados.entities.PuntoVentaEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.persistence.PuntoVentaPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la conexion con la persistencia para la entidad de PuntoVenta.
 *
 * @author estudiante
 */
@Stateless
public class PuntoVentaLogic {

    @Inject
    private PuntoVentaPersistence persistencia;

    private static final Logger LOGGER = Logger.getLogger(PuntoVentaLogic.class.getName());


    /**
     * Crea un punto de venta en la persistencia.
     *
     * @param entityNew La entidad que representa el punto de venta a
     *                  persistir.
     * @return La entiddad de punto de venta luego de persistirla.
     * @throws BusinessLogicException Si puntos de venta a persistir ya existe.
     */
    public PuntoVentaEntity createPuntoVenta(PuntoVentaEntity entityNew) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia creación del punto de venta");

        if (persistencia.find(entityNew.getId()) != null) {
            throw new BusinessLogicException("Ya existe este punto de venta");
        }
        PuntoVentaEntity entity = persistencia.create(entityNew);
        LOGGER.log(Level.INFO, "Finaliza la creación de el punto de venta");
        return entity;
    }


    /**
     * Obtener todas los puntos de venta existentes en la base de datos.
     *
     * @return una lista de puntos de venta
     */
    public List<PuntoVentaEntity> getPuntosDeVenta() {
        LOGGER.log(Level.INFO, "Inicia consulta de puntos de venta");
        List<PuntoVentaEntity> lista = persistencia.findAll();
        LOGGER.log(Level.INFO, "Termina consulta");
        return lista;

    }


    /**
     * Obtener un punto por medio de su id.
     *
     * @param puntoVentaId Id: id de punto para ser buscado.
     * @return punto solicitado por medio de su id.
     * @throws BusinessLogicException si el ID es inválido  o si este no existe.
     */

    public PuntoVentaEntity getPuntoVenta(Long puntoVentaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Consulta de punto de venta con Id={0}", puntoVentaId);

        if (puntoVentaId == null) {
            throw new BusinessLogicException("ID inválido");
        }
        PuntoVentaEntity puntoVentaEntity = persistencia.find(puntoVentaId);

        if (puntoVentaEntity == null) {

            throw new BusinessLogicException("El punto de venta con el id = {0} no existe");
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el punto de venta con id = {0}", puntoVentaId);
        return puntoVentaEntity;
    }


    /**
     * Actualizar un punto.
     *
     * @param puntoVentaId     Id: id de punto para buscarlo en la base de
     *                         datos.
     * @param puntoVentaEntity Entity:punto con los cambios para ser actualizado,
     *                         por ejemplo el nombre.
     * @return punto con los cambios actualizados en la base de datos.
     */
    public PuntoVentaEntity updatePuntoVenta(Long puntoVentaId, PuntoVentaEntity puntoVentaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el punto de venta con id = {0}", puntoVentaId);
        PuntoVentaEntity newPuntoEntity = persistencia.update(puntoVentaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el punto de venta con id = {0}", puntoVentaId);
        return newPuntoEntity;
    }


    /**
     * Borrar un punto
     *
     * @param puntoVentaId Id: id del punto a borrar
     * @throws BusinessLogicException Si el punto de venta no existe.
     */
    public void deletePuntoVenta(Long puntoVentaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el punto de venta con id = {0}", puntoVentaId);

        if (puntoVentaId == null) {
            throw new BusinessLogicException("Id inválido.");
        }
        PuntoVentaEntity punto_venta = persistencia.find(puntoVentaId);
        if (punto_venta == null) {
            throw new BusinessLogicException("No se puede borrar el punto de venta con id = " + puntoVentaId + " porque no existe");
        }

        persistencia.delete(puntoVentaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el punto de venta con id = {0}", puntoVentaId);
    }
}
