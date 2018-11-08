/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.ejb;

import co.edu.uniandes.csw.carrosusados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosusados.entities.PuntoVentaEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.persistence.AutomovilPersistence;
import co.edu.uniandes.csw.carrosusados.persistence.PuntoVentaPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author estudiante
 */

@Stateless
public class AutomovilPuntoVentaLogic {

    private static final Logger LOGGER = Logger.getLogger(AutomovilPuntoVentaLogic.class.getName());
    @Inject
    private AutomovilPersistence automovilPersistence;

    @Inject
    private PuntoVentaPersistence puntoVentaPersistence;


    /**
     * Reemplazar la ficha tecnica de un automovil.
     *
     * @param idAutomovil    id del automovil que se quiere actualizar.
     * @param idFichaTecnica El id de la ficha tecnica que será del automovil
     * @return el nuevo automovil.
     */
    public AutomovilEntity replacePuntoVenta(Long idAutomovil, Long idPuntoVenta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar automovil con id = {0}", idAutomovil);

        if (automovilPersistence.find(idAutomovil) == null) {
            throw new BusinessLogicException("El automovil no existe");
        }
        if (puntoVentaPersistence.find(idPuntoVenta) == null) {
            throw new BusinessLogicException("El punto de venta no existe");
        }
        AutomovilEntity automovilEntity = automovilPersistence.find(idAutomovil);
        PuntoVentaEntity puntoVenta = puntoVentaPersistence.find(idPuntoVenta);
        automovilEntity.setPuntoVenta(puntoVenta);
        automovilPersistence.update(automovilEntity); //Chequear si esto es correcto (Sobra ya que las em actualzia automaticamente entidades)
        LOGGER.log(Level.INFO, "Termina proceso de actualizar automovil con id = {0}", automovilEntity.getId());
        return automovilEntity;
    }

}
