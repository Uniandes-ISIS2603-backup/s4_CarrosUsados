/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.PagoEntity;
import co.edu.uniandes.csw.carrosUsados.entities.ArticuloEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.PagoPersistence;
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
public class ArticuloPagoLogic {
    
    private static final Logger LOGGER = Logger.getLogger(ArticuloPagoLogic.class.getName());

    @Inject
    ArticuloPersistence articuloPersistence;
    
    @Inject
    PagoPersistence pagoPersistence;
    
    /**
     * Agregar un pago a la articulo
     *
     * @param pagoId El id pago a guardar
     * @param articuloId El id de la articulo en la cual se va a guardar el
     * pago.
     * @return El pago creado.
     */
    public PagoEntity addPago(Long pagoId, Long articuloId) {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle un pago a la articulo con id = {0}", articuloId);
        ArticuloEntity articuloEntity = articuloPersistence.find(articuloId);
        PagoEntity pagoEntity = pagoPersistence.find(pagoId);
        articuloEntity.setPago(pagoEntity);
        pagoEntity.setArticulo(articuloEntity);
        LOGGER.log(Level.INFO, "Termina proceso de agregarle un pago a la articulo con id = {0}", articuloId);
        return pagoEntity;
    }
    
     /**
     * Reemplazar la pago de un articulo.
     *
     * @param idArticulo id del articulo que se quiere actualizar.
     * @param idPago El id del pago que será del articulo
     * @return el nuevo articulo.
     */
    public ArticuloEntity replacePago(Long idArticulo, Long idPago) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar articulo con id = {0}", idArticulo);
        ArticuloEntity articuloEntity = articuloPersistence.find(idArticulo);
        PagoEntity pagoEntity = pagoPersistence.find(idPago);
        articuloEntity.setPago(pagoEntity);
        pagoEntity.setArticulo(articuloEntity);
        articuloPersistence.update(articuloEntity); //Chequear si esto es correcto
        pagoPersistence.update(pagoEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar articulo con id = {0}", articuloEntity.getId());
        return articuloEntity;
    }
    
}
