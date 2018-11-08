/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.ejb;

import co.edu.uniandes.csw.carrosusados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosusados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.persistence.AutomovilPersistence;
import co.edu.uniandes.csw.carrosusados.persistence.CalificacionPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author estudiante
 */
@Stateless
public class AutomovilCalificacionLogic {

    private static final Logger LOGGER = Logger.getLogger(AutomovilCalificacionLogic.class.getName());

    @Inject
    private CalificacionPersistence calificacionPersistence;

    @Inject
    private AutomovilPersistence automovilPersistence;


    /**
     * Agregar una calificación al automovil.
     *
     * @param calificacionId El id de la calificación a guardar.
     * @param automovilId    El id del automovil en el cual se va a guardar
     *                       la calificación.
     * @return El libro creado.
     */
    public CalificacionEntity addCalificacion(Long automovilId, Long calificacionId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una calificación al automovil con id{0}", automovilId);
        CalificacionEntity calificacionent = calificacionPersistence.find(calificacionId);
        AutomovilEntity automovilEntity = automovilPersistence.find(automovilId);
        automovilEntity.getCalificaciones().add(calificacionent);
        calificacionent.setAutomovil(automovilEntity);

        return calificacionent;
    }

    /**
     * Remplaza las instancias de Calificacion asociadas a una instancia de Automovil
     *
     * @param automovilId    Id Identificador de la instancia de automovil
     * @param calificaciones Colección de instancias de CalificacionEntity a asociar a instancia
     *                       de punto
     * @return Nueva colección de CalificacionEntity asociada a la instancia de Automovil
     */
    public List<CalificacionEntity> replaceCalificaciones(Long automovilId, List<CalificacionEntity> calificaciones) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los calificaciones asocidas al punto con id = {0}", automovilId);
        AutomovilEntity automovilEntity = automovilPersistence.find(automovilId);
        List<CalificacionEntity> calList = calificacionPersistence.findAll();
        for (CalificacionEntity calificacion : calList) {
            if (calificaciones.contains(calificacion)) {

                calificacion.setAutomovil(automovilEntity);

            }
        }
        automovilEntity.setCalificaciones(calList);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar las calificaciones asocidos al punto con id = {0}", automovilId);
        return automovilEntity.getCalificaciones();
    }


    /**
     * Retorna todas las calificaciones asociadas a un automovil.
     *
     * @param automovilId El ID del punto de venta buscado
     * @return La lista de calificaciones del automovil.
     */
    public List<CalificacionEntity> getCalificaciones(Long automovilId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consulta de calificaciones del automovil con id:{0}", automovilId);
        if (automovilPersistence.find(automovilId) == null) {
            throw new BusinessLogicException("El automovil no existe");
        }
        List<CalificacionEntity> listaCalificaciones = automovilPersistence.find(automovilId).getCalificaciones();
        LOGGER.log(Level.INFO, "Inicia proceso de consulta de calificaciones del automovil con id:{0}", automovilId);
        return listaCalificaciones;

    }


    /**
     * Retorna una calificación asociada a un automovil.
     *
     * @param automovilId    El id del punto del automovil a buscar
     * @param calificacionId El id de la calificación a buscar
     * @return La calificación encontrada dentro del automovil.
     * @throws BusinessLogicException Si la calificación no se encuentra en el automovil.
     */
    public CalificacionEntity getCalificacion(Long automovilId, Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consulta de calificación con id={0} del automovil con id=" + automovilId, calificacionId);
        List<CalificacionEntity> listaCalificaciones = automovilPersistence.find(automovilId).getCalificaciones();
        CalificacionEntity calificacionent = calificacionPersistence.find(calificacionId);
        int index = listaCalificaciones.indexOf(calificacionent);
        CalificacionEntity calreturn = null;
        if (index < 0) {
            throw new BusinessLogicException("La calificación no está asociada al automovil.");
        }
        calreturn = listaCalificaciones.get(index);

        LOGGER.log(Level.INFO, "Termina proceso de consulta de calificación con id={0} del automovil con id=" + automovilId, calificacionId);

        return calreturn;
    }


    /**
     * Borrar una calificación de un automovil. Este metodo se utiliza para borrar la
     * relacion de una calificación.
     *
     * @param automovilId    el automovil del que se desea borrar la calificación.
     * @param calificacionId La calificacion que se desea borrar del automovil.
     */
    public void deleteCalificacion(Long automovilId, Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificación con id = {0} del automovil con id = " + automovilId, calificacionId);

        CalificacionEntity old = getCalificacion(automovilId, calificacionId);
        if (old == null) {
            throw new BusinessLogicException("La calificación con id = " + calificacionId + " no está asociado al automovil con id = " + automovilId);
        }
        CalificacionEntity calent = calificacionPersistence.find(calificacionId);
        AutomovilEntity automovilEntity = automovilPersistence.find(automovilId);
        automovilEntity.getCalificaciones().remove(calent);

        LOGGER.log(Level.INFO, "Termina proceso de borrar la calificación con id = {0} del automovil con id = " + automovilId, calificacionId);
    }

}
