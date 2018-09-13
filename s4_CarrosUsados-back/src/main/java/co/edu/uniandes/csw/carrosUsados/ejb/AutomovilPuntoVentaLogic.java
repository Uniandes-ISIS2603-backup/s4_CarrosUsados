/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.persistence.AutomovilPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.ModeloPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.PuntoVentaPersistence;
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
    private AutomovilPersistence persistence;

    @Inject
    private PuntoVentaPersistence modeloPersistence;
    
    
    
}
