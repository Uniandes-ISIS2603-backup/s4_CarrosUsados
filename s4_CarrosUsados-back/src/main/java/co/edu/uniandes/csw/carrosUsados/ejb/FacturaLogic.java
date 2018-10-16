/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.FacturaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.FacturaPersistence;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

/**
 *
 * @author estudiante
 */
@Stateless
public class FacturaLogic {
private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());
    
    @Inject
    private FacturaPersistence persistence;
    /**
     * Guardar un Pago nuevo
     *
     * @param facturaEntity La entidad de tipo PagoEntity del pago que se percistira.
     * @return La entidad luego de persistirla
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    public FacturaEntity createFactura(FacturaEntity facturaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la factura");
        if(facturaEntity.getId()== null ){
          throw new BusinessLogicException("El pago debe tener una id");
        }       
        if (facturaEntity.getTotal()<0)
        {
            throw new BusinessLogicException("El valor total debe ser positivo");
            
        }
        if(facturaEntity.getProducto()==null || "".equals(facturaEntity.getProducto()) )
        {
            throw new BusinessLogicException("NO hay productos");
        }
        if(facturaEntity.getSubtotal()<0)
        {
            throw new BusinessLogicException("El valor subtotal debe ser positivo");
        }
        if(facturaEntity.getFormaDePago()==null)
        {
            throw new BusinessLogicException("NO tiene una forma de pago");
        }
        
        persistence.create(facturaEntity);
        LOGGER.log(Level.INFO, "Se creo la Factura");
        return facturaEntity;
    }
    /**
     * Busca un Pago por la ID
     *
     * @param idFactura La id del pago que se quiere encontrar
     * @return El pago que se encontro, null si no la encuentra.
     */
    public FacturaEntity getFactura(Long idFactura) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un Pago");
        if(idFactura == null){
          throw new BusinessLogicException("La ID del pago no puede ser null");
        }
        FacturaEntity facturaEntity = persistence.find(idFactura);
        if(facturaEntity == null){
          LOGGER.log(Level.INFO, "El pago buscado con la id no existe",idFactura);
        }
        LOGGER.log(Level.INFO, "Terminando de buscar el Pago");
        return facturaEntity;
    }
    /**
     * Actualizar el pago mediate la ID 
     *
     * @param idFactura El ID de la factura que se quiere actualizar
     * @param facturaEntity La entity con las modificaciones que se haran
     * @return La entidad de la factura despues del update
     * @throws BusinessLogicException Si la factura no tiene un ID o la tarjeta no es valida
     */
     public FacturaEntity updateFactura(Long idFactura, FacturaEntity facturaEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el Pago con id = {0}", idFactura);
       
        
        if (facturaEntity.getTotal()<0 ||facturaEntity.getTotal()==null)
        {
            throw new BusinessLogicException("El valor no es valido");
            
        }
        if(facturaEntity.getProducto() == null)
        {
            throw new BusinessLogicException("La factura no tiene producto");
        }
        
        FacturaEntity newEntity = persistence.update(facturaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el pago con id = {0}", facturaEntity.getId());
        return newEntity;
    }
     
     
    /**
     * Devuelve todos las Facturas que hay en la base de datos.
     *
     * @return Lista de entidades de tipo libro.
     */
    public List<FacturaEntity> getFacturas() {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar todas las facturas");
        List<FacturaEntity> books = persistence.findAll();
        LOGGER.log(Level.INFO, "Termina proceso de consultar todas las factura");
        return books;
    }
        /**
     * Elimina un Pago con la ID dada
     *
     * @param idFactura La Id del pago que se quiere eliminar.
     */
     public void deleteFactura(Long idFactura) {
        LOGGER.log(Level.INFO, "Borrando el pago con id = {0}", idFactura);
        persistence.delete(idFactura);
        LOGGER.log(Level.INFO, "Termino de borrar el pago con id = {0}", idFactura);
    }
}
