/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;




import co.edu.uniandes.csw.carrosUsados.entities.PagoEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.PagoPersistence;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author estudiante
 */
@Stateless
public class PagoLogic {
     
    private static final Logger LOGGER = Logger.getLogger(PagoLogic.class.getName());
    
    @Inject
    private PagoPersistence persistence;
    /**
     * Guardar un Pago nuevo
     *
     * @param pagoEntity La entidad de tipo PagoEntity del pago que se percistira.
     * @return La entidad luego de persistirla
     */
    public PagoEntity createPago(PagoEntity pagoEntity) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de creación de la ficha tecnica");
        if(pagoEntity.getIdPago() == null ){
          throw new BusinessLogicException("El pago debe tener una id");
        }
        Date fechaValida = new Date(2018,12, 31);
        if (pagoEntity.getFechaTarjeta()!=null && pagoEntity.getFechaTarjeta().after(fechaValida))
        {
            throw new BusinessLogicException("La fecha de la tarjeta de credito esta en el futuro");
            
        }
        if(pagoEntity.getNum_targeta().length() <16 || pagoEntity.getNum_targeta().length()>16)
        {
            throw new BusinessLogicException("El numero de la tarjeta no tiene 16 digitos");
        }
        
        persistence.create(pagoEntity);
        LOGGER.log(Level.INFO, "Se creo el Pago");
        return pagoEntity;
    }
    /**
     * Busca un Pago por la ID
     *
     * @param idPago La id del pago que se quiere encontrar
     * @return El pago que se encontro, null si no la encuentra.
     */
    public PagoEntity getPago(Long idPago) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de consultar un Pago");
        if(idPago == null){
          throw new BusinessLogicException("La ID del pago no puede ser null");
        }
        PagoEntity pagoEntity = persistence.find(idPago);
        if(pagoEntity == null){
          LOGGER.log(Level.INFO, "El pago buscado con la id no existe",idPago);
        }
        LOGGER.log(Level.INFO, "Terminando de buscar el Pago");
        return pagoEntity;
    }
   
        /**
     * Elimina un Pago con la ID dada
     *
     * @param idPago La Id del pago que se quiere eliminar.
     */
     public void deletePago(Long idPago) {
        LOGGER.log(Level.INFO, "Borrando el pago con id = {0}", idPago);
        persistence.delete(idPago);
        LOGGER.log(Level.INFO, "Termino de borrar el pago con id = {0}", idPago);
    }
    
    
}
