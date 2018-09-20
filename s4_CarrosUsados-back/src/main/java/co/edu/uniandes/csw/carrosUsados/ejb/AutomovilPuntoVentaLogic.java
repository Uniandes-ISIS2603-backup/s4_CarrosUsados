/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.entities.FichaTecnicaEntity;
import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.AutomovilPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.ModeloPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.PuntoVentaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
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
     * @param idAutomovil id del automovil que se quiere actualizar.
     * @param idFichaTecnica El id de la ficha tecnica que será del automovil
     * @return el nuevo automovil.
     */
    public AutomovilEntity replacePuntoVenta(Long idAutomovil, Long idPuntoVenta) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar automovil con id = {0}", idAutomovil);
        
        if(automovilPersistence.find(idAutomovil) == null){
            throw new BusinessLogicException("El automovil no existe");
        }
        if(puntoVentaPersistence.find(idPuntoVenta) == null){
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
