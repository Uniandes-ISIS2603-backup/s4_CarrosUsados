/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.ArticuloEntity;
import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.persistence.ArticuloPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.AutomovilPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author jd.tamara
 */

@Stateless
public class ArticuloAutomovilLogic {
   
    private static final Logger LOGGER = Logger.getLogger(ArticuloAutomovilLogic.class.getName());

    @Inject
    ArticuloPersistence articuloPersistence;
    
    @Inject
    AutomovilPersistence automovilPersistence;
    
    /**
     * Agregar un automovil a la articulo
     *
     * @param automovilId El id automovil a guardar
     * @param articuloId El id de la articulo en la cual se va a guardar el
     * automovil.
     * @return El automovil creado.
     */
    public AutomovilEntity addAutomovil(Long automovilId, Long articuloId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un automovil a la articulo con id = {0}", articuloId);
        ArticuloEntity articuloEntity = articuloPersistence.find(articuloId);
        AutomovilEntity automovilEntity = automovilPersistence.find(automovilId);
        articuloEntity.setAutomovil(automovilEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un automovil a la articulo con id = {0}", articuloId);
        return automovilEntity;
    }
    
    /**
     * Reemplazar la automovil de un articulo.
     *
     * @param idArticulo id del articulo que se quiere actualizar.
     * @param idAutomovil El id del automovil que será del articulo
     * @return el nuevo articulo.
     */
    public ArticuloEntity replaceAutomovil(Long idArticulo, Long idAutomovil) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar articulo con id = {0}", idArticulo);
        ArticuloEntity articuloEntity = articuloPersistence.find(idArticulo);
        AutomovilEntity automovilEntity = automovilPersistence.find(idAutomovil);
        articuloEntity.setAutomovil(automovilEntity);
        articuloPersistence.update(articuloEntity); //Chequear si esto es correcto
        articuloEntity = articuloPersistence.find(idArticulo);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar articulo con id = {0}", articuloEntity.getId());
        return articuloEntity;
    }
    
}
