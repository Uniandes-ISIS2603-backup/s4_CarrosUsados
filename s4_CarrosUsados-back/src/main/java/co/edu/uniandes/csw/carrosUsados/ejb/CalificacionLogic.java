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
public class CalificacionLogic {
    
    @Inject private CalificacionPersistence persistencia;
    
    private static final Logger LOGGER = Logger.getLogger(CalificacionLogic.class.getName());
    
    
    public CalificacionEntity createCalificacion(CalificacionEntity entityNew) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Inicia creación de calificación");

        if(entityNew.getId()==null)
        {
            throw new BusinessLogicException("Id inválido");
        }
        
        if(persistencia.find(entityNew.getId())!=null)
        {
            throw new BusinessLogicException("Ya existe esta calificación.");
        }
        
        if(entityNew.getComentario()==null || (entityNew.getComentario()==""))
        {
            throw new BusinessLogicException("Comentario inválido");
        }
        
        if(entityNew.getEstrellas()>5 || (entityNew.getEstrellas()<0))
        {
            throw new BusinessLogicException("Calificación inválida: debe tener un número entre 0 a 5");
        }
        
        CalificacionEntity entity= persistencia.create(entityNew);
        LOGGER.log(Level.INFO, "Finaliza la creción de la calificación");
        return entity;
    }
    
    public List<CalificacionEntity> getCalificaciones()
    {
        LOGGER.log(Level.INFO, "Iniciaconsulta de calificaciones");
        List<CalificacionEntity> lista= persistencia.findAll();
        LOGGER.log(Level.INFO, "Termina consulta");
        return lista;
        
    }
    
    
    
    
    public CalificacionEntity getCalificacion(Long calificacionId) throws BusinessLogicException
    {
        LOGGER.log(Level.INFO, "Consulta de calificacion con Id={0}",calificacionId);
        
        if(calificacionId==null)
        {
            throw new BusinessLogicException("Id inválido");
        }
        
        CalificacionEntity calificacionEntity = persistencia.find(calificacionId);
        if (calificacionEntity == null) {
            LOGGER.log(Level.SEVERE, "La calificacion con el id = {0} no existe", calificacionId);
        }
        LOGGER.log(Level.INFO, "Termina proceso de consultar la calificacion con id = {0}", calificacionId);
        return calificacionEntity;
    }
    
    
    
    
     public CalificacionEntity updateCalificacion(Long calificacionId, CalificacionEntity calificacionEntity) {
        LOGGER.log(Level.INFO, "Inicia proceso de actualizar  la calificación con id = {0}", calificacionId);
        CalificacionEntity newAuthorEntity = persistencia.update(calificacionEntity);
        LOGGER.log(Level.INFO, "Termina proceso de actualizar la calificación con id = {0}", calificacionId);
        return newAuthorEntity;
    }
     
     
     
        public void deleteCalificacion(Long calificacionId) throws BusinessLogicException {
        LOGGER.log(Level.INFO, "Inicia proceso de borrar la calificacion con id = {0}", calificacionId);
        if(calificacionId==null)
        {
            throw new BusinessLogicException("Id inválido");
        }
        PuntoVentaEntity punto_venta = getCalificacion(calificacionId).getPuntoVenta();
        if (punto_venta != null) {
            throw new BusinessLogicException("No se puede borrar la calificacion con id = " + calificacionId + " porque tiene un punto de venta asociado");
        }
       
        persistencia.delete(calificacionId);
        LOGGER.log(Level.INFO, "Termina proceso de borrar la calificacion con id = {0}", calificacionId);
    }
}
