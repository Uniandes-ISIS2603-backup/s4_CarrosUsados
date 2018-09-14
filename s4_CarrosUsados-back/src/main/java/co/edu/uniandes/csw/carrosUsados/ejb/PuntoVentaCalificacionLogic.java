/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.ejb;

import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.CalificacionPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.PuntoVentaPersistence;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Daniella Arteaga
 */
@Stateless
public class PuntoVentaCalificacionLogic {
  
    private static final Logger LOGGER = Logger.getLogger(PuntoVentaCalificacionLogic.class.getName());
     
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    @Inject
    private PuntoVentaPersistence puntoPersistence;
    
    public CalificacionEntity addCalificacion(Long calificacioinId,Long puntoId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una calificación al punto de venta con id{0}",puntoId);
        CalificacionEntity calificacionent= calificacionPersistence.find(calificacioinId);
        PuntoVentaEntity puntoent= puntoPersistence.find(puntoId);
        puntoent.getCalificaciones().add(calificacionent);
        calificacionent.setPuntoVenta(puntoent);
      
        return calificacionent;
    }
    
    public List<CalificacionEntity> getCalificaciones(Long puntoId)
    {
        LOGGER.log(Level.INFO,"Inicia proceso de consulta de calificaciones del punto de venta con id:{0}",puntoId);
        List<CalificacionEntity> listaCalificaciones= puntoPersistence.find(puntoId).getCalificaciones();
        LOGGER.log(Level.INFO,"Inicia proceso de consulta de calificaciones del punto de venta con id:{0}",puntoId);
        return listaCalificaciones;
 
    }
    
    
      public CalificacionEntity getCalificacion(Long puntoId, Long calificacionId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO,"Inicia proceso de consulta de calificación con id={0} del punto de venta con id="+puntoId,calificacionId);
        List<CalificacionEntity> listaCalificaciones= puntoPersistence.find(puntoId).getCalificaciones();
        CalificacionEntity calificacionent= calificacionPersistence.find(calificacionId);
        int index= listaCalificaciones.indexOf(calificacionent);
        CalificacionEntity calreturn = null;
        if(index<0)
        {
             throw new BusinessLogicException("La calificación no está asociada al punto de venta."); 
        }    
          calreturn=listaCalificaciones.get(index);
       
         LOGGER.log(Level.INFO,"Termina proceso de consulta de calificación con id={0} del punto de venta con id="+puntoId,calificacionId);
         
         return calreturn;
    }
      
      
        public List<CalificacionEntity> replaceCalificaciones(Long puntoId, List<CalificacionEntity> calificaciones) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar el punto con id = {0}",puntoId);
        PuntoVentaEntity puntoEntity = puntoPersistence.find(puntoId);
        List<CalificacionEntity> calificacionList = calificacionPersistence.findAll();
        for (CalificacionEntity cal : calificacionList) {
            if (calificaciones.contains(cal)) {
                cal.setPuntoVenta(puntoEntity);
                
            } else if (cal.getPuntoVenta() != null && cal.getPuntoVenta().equals(puntoEntity)) {
                cal.setPuntoVenta(null);
            }
        }
        LOGGER.log(Level.INFO, "Termina proceso de actualizar el punto con id = {0}", puntoId);
        return calificacionList;
    }
        
        
        public void deleteCalificacion(Long puntoId, Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificación con id = {0} del punto con id = " + puntoId, calificacionId);
        
        CalificacionEntity old = getCalificacion(puntoId, calificacionId);
        if (old == null) {
            throw new BusinessLogicException("La calificación con id = " + calificacionId + " no está asociado al punto con id = " + puntoId);
        }
        CalificacionEntity calent=calificacionPersistence.find(calificacionId);
        PuntoVentaEntity puntoent= puntoPersistence.find(puntoId);
        puntoent.getCalificaciones().remove(calent);
        
        LOGGER.log(Level.INFO, "Termina proceso de borrar la calificación con id = {0} del punto con id = " + puntoId, calificacionId);
    }
}
