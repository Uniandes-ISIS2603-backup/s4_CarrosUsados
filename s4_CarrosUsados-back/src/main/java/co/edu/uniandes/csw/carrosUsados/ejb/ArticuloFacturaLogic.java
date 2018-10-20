/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.FacturaEntity;
import co.edu.uniandes.csw.carrosUsados.entities.ArticuloEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.FacturaPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.ArticuloPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class ArticuloFacturaLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ArticuloFacturaLogic.class.getName());

    @Inject
    ArticuloPersistence articuloPersistence;
    
    @Inject
    FacturaPersistence facturaPersistence;
    
    /**
     * Agregar un factura a la articulo
     *
     * @param facturaId El id factura a guardar
     * @param articuloId El id de la articulo en la cual se va a guardar el
     * factura.
     * @return El factura creado.
     */
    public FacturaEntity addFactura(Long facturaId, Long articuloId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un factura a la articulo con id = {0}", articuloId);
        ArticuloEntity articuloEntity = articuloPersistence.find(articuloId);
        FacturaEntity facturaEntity = facturaPersistence.find(facturaId);
        articuloEntity.setFactura(facturaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un factura a la articulo con id = {0}", articuloId);
        return facturaEntity;
    }
    
     /**
     * Reemplazar la factura de un articulo.
     *
     * @param idArticulo id del articulo que se quiere actualizar.
     * @param idFactura El id del factura que será del articulo
     * @return el nuevo articulo.
     */
    public ArticuloEntity replaceFactura(Long idArticulo, Long idFactura) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar articulo con id = {0}", idArticulo);
        ArticuloEntity articuloEntity = articuloPersistence.find(idArticulo);
        FacturaEntity facturaEntity = facturaPersistence.find(idFactura);
        articuloEntity.setFactura(facturaEntity);
        articuloPersistence.update(articuloEntity); //Chequear si esto es correcto
        facturaPersistence.update(facturaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar articulo con id = {0}", articuloEntity.getId());
        return articuloEntity;
    }   
    
}
