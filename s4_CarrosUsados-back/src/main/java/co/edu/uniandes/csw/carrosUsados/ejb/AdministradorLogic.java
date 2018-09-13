/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import javax.ejb.Stateless;
import co.edu.uniandes.csw.carrosUsados.entities.AdministradorEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.AdministradorPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author js.bravo
 */
@Stateless
public class AdministradorLogic extends UsuarioLogic {

    private static final Logger LOGGER = Logger.getLogger(AdministradorLogic.class.getName());
    private static final int MINIMA_EXPERIENCIA_EN_ANIOS = 0;

    @Inject
    private AdministradorPersistence persistence;

    /**
     * Persiste un administrador en el sistema.
     *
     * @param administradorEntity - La entidad del administrador a persistir.
     * @throws BusinessLogicException Si la información del administrador es
     * incorrecta.
     * @return La entidad si esta pudo persistirse correctamente.
     *
     */
    public AdministradorEntity createAdministrador(AdministradorEntity administradorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicio proceso de creación de un administrador");
        validateUsuarioWrapper(administradorEntity);
        if (!(validateFecha(administradorEntity.getFechaInicio(), MINIMA_EXPERIENCIA_EN_ANIOS))) {
            throw new BusinessLogicException("La fecha de inicio del cargo no supera el mínimo de años del cargo (" + MINIMA_EXPERIENCIA_EN_ANIOS + ").");
        }
        if (!(validateNombre(administradorEntity.getCargo()))) {
            throw new BusinessLogicException("El cargo es inválido.");
        }
        persistence.create(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de creación del administrador");

        return administradorEntity;
    }

    /**
     * Devuelve todos los administradores registrados en el sistema.
     *
     * @return Lista de entidades de tipo administrador.
     */
    public List<AdministradorEntity> getAdministradores() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todos los administradores");
        List<AdministradorEntity> administradores = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todos los administradores");
        return administradores;
    }

    /**
     * Consulta un administrador según su id.
     *
     * @param administradorId - El id del administrador a consultar.
     * @throws BusinessLogicException Si el id enviado por parámetro es
     * inválido.
     * @return El administrador si fue encontrado, null en caso contrario.
     */
    public AdministradorEntity getAdministrador(Long administradorId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un administrador");
        AdministradorEntity administradorEntity = persistence.find(administradorId);
        if (administradorEntity == null) {
            LOGGER.log(Level.INFO, "El administrador con el id = {0} no existe", administradorId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar un administrador según su Id");
        return administradorEntity;
    }

    /**
     * Actualiza administrador según su id.
     *
     * @param administradorId - El id del administrador a actualizar.
     * @param administradorEntity La entidad del administrador actualizada.
     * @throws BusinessLogicException Si la información del administrador es
     * incorrecta.
     * @return Si fue exitosa la actualización, retorna La entidad del
     * administrador actualizada.
     */
    public AdministradorEntity updateAdministrador(Long administradorId, AdministradorEntity administradorEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar un administrador");
        validateUsuarioWrapper(administradorEntity);
        if (!(validateFecha(administradorEntity.getFechaInicio(), MINIMA_EXPERIENCIA_EN_ANIOS))) {
            throw new BusinessLogicException("La fecha de inicio del cargo no supera el mínimo de años del cargo (" + MINIMA_EXPERIENCIA_EN_ANIOS + ").");
        }
        if (!(validateNombre(administradorEntity.getCargo()))) {
            throw new BusinessLogicException("El cargo es inválido.");
        }
        AdministradorEntity newEntity = persistence.update(administradorEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualización de un administrador");
        return newEntity;
    }

    /**
     * Eliminar un administrador según su id.
     *
     * @param administradorId - El id del administrador a eliminar.
     */
    public void deleteAdministrador(Long administradorId) {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el administrador con id = {0}", administradorId);
        AdministradorEntity administradorEntity = persistence.find(administradorId);
        if (administradorEntity == null) {
            LOGGER.log(Level.INFO, "El administrador con el id = {0} no existe", administradorId);
        }
        persistence.delete(administradorId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el administrador con id = {0}", administradorId);
    }

}
