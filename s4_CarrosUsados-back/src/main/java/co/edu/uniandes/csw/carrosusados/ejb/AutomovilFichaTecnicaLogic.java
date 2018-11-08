/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.ejb;

import co.edu.uniandes.csw.carrosusados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosusados.entities.FichaTecnicaEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.persistence.AutomovilPersistence;
import co.edu.uniandes.csw.carrosusados.persistence.FichaTecnicaPersistence;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author juanestebanmendez
 */

@Stateless
public class AutomovilFichaTecnicaLogic {

    private static final Logger LOGGER = Logger.getLogger(AutomovilFichaTecnicaLogic.class.getName());

    @Inject
    AutomovilPersistence automovilPersistence;

    @Inject
    FichaTecnicaPersistence fichaTecnicaPersistence;


    /**
     * Reemplazar la ficha tecnica de un automovil.
     *
     * @param idAutomovil    id del automovil que se quiere actualizar.
     * @param idFichaTecnica El id de la ficha tecnica que será del automovil
     * @return el nuevo automovil.
     */
    public AutomovilEntity replaceFichaTecnica(Long idAutomovil, Long idFichaTecnica) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar automovil con id = {0}", idAutomovil);
        if (automovilPersistence.find(idAutomovil) == null) {
            throw new BusinessLogicException("El automovil no existe");
        }
        if (fichaTecnicaPersistence.find(idFichaTecnica) == null) {
            throw new BusinessLogicException("El ficha tecnica no existe");
        }
        AutomovilEntity automovilEntity = automovilPersistence.find(idAutomovil);
        FichaTecnicaEntity fichaTecnicaEntity = fichaTecnicaPersistence.find(idFichaTecnica);
        automovilEntity.setFichaTecnica(fichaTecnicaEntity);
        automovilPersistence.update(automovilEntity); //Chequear si esto es correcto (Sobra ya que las em actualzia automaticamente entidades)
        LOGGER.log(Level.INFO, "Termina proceso de actualizar automovil con id = {0}", automovilEntity.getId());
        return automovilEntity;
    }
}
