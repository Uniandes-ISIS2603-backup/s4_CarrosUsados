/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;


import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.PuntoVentaPersistence;
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
public class PuntoVentaLogic {
    
    @Inject private PuntoVentaPersistence persistencia;
    
    private static final Logger LOGGER = Logger.getLogger(PuntoVentaLogic.class.getName());
    
     
    public PuntoVentaEntity createPuntoVenta(PuntoVentaEntity entityNew) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia creación del punto de venta");

        if(persistencia.find(entityNew.getId())!=null)
        {
            throw new BusinessLogicException("Ya existe este punto de venta");
        }
        PuntoVentaEntity entity= persistencia.create(entityNew);
        LOGGER.log(Level.INFO, "Finaliza la creación de el punto de venta");
        return entity;
    }
    
    public List<PuntoVentaEntity> getPuntosDeVenta()
    {
        LOGGER.log(Level.INFO, "Inicia consulta de puntos de venta");
        List<PuntoVentaEntity> lista= persistencia.findAll();
        LOGGER.log(Level.INFO, "Termina consulta");
        return lista;
        
    }
    
    
    
    
    public PuntoVentaEntity getPuntoVenta(Long puntoVentaId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Consulta de punto de venta con Id={0}",puntoVentaId);
        
        if(puntoVentaId==null)
        {
            throw new BusinessLogicException("ID inválido");
        }
         PuntoVentaEntity puntoVentaEntity = persistencia.find(puntoVentaId);
        if (puntoVentaEntity == null) {
            LOGGER.log(Level.SEVERE, "El punto de venta con el id = {0} no existe", puntoVentaId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar el punto de venta con id = {0}", puntoVentaId);
        return puntoVentaEntity;
    }
    
    
    
    
     public PuntoVentaEntity updatePuntoVenta(Long puntoVentaId, PuntoVentaEntity puntoVentaEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el punto de venta con id = {0}", puntoVentaId);
        PuntoVentaEntity newPuntoEntity = persistencia.update(puntoVentaEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el punto de venta con id = {0}", puntoVentaId);
        return newPuntoEntity;
    }
     
     
     
        public void deletePuntoVenta(Long puntoVentaId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar el punto de venta con id = {0}", puntoVentaId);
        
        if(puntoVentaId==null)
        {
            throw new BusinessLogicException("Id inválido.");
        }
        PuntoVentaEntity punto_venta = persistencia.find(puntoVentaId);
        if (punto_venta == null) {
            throw new BusinessLogicException("No se puede borrar el punto de venta con id = " + puntoVentaId + " porque no existe");
        }
       
        persistencia.delete(puntoVentaId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar el punto de venta con id = {0}", puntoVentaId);
    }
}
