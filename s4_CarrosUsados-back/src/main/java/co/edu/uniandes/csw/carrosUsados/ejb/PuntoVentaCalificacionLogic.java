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
 * Clase que implementa la conexion con la persistencia para la relación entre Calificación y PuntoVenta.
 * @author Daniella Arteaga
 */
@Stateless
public class PuntoVentaCalificacionLogic {
  
    private static final Logger LOGGER = Logger.getLogger(PuntoVentaCalificacionLogic.class.getName());
     
    @Inject
    private CalificacionPersistence calificacionPersistence;
    
    @Inject
    private PuntoVentaPersistence puntoPersistence;
    
    
    
     /**
     * Agregar una calificación al punto de venta. 
     *
     * @param calificacionId El id de la calificación a guardar.
     * @param puntoId El id del punto de venta en el cual se va a guardar
     * la calificación.
     * @return El libro creado.
     */
    public CalificacionEntity addCalificacion(Long puntoId,Long calificacionId) 
    {
        LOGGER.log(Level.INFO, "Inicia proceso de agregarle una calificación al punto de venta con id{0}",puntoId);
        CalificacionEntity calificacionent= calificacionPersistence.find(calificacionId);
        PuntoVentaEntity puntoent= puntoPersistence.find(puntoId);
        puntoent.getCalificaciones().add(calificacionent);
        calificacionent.setPuntoVenta(puntoent);
      
        return calificacionent;
    }
    
        /**
     * Remplaza las instancias de Book asociadas a una instancia de Author
     *
     * @param puntoId Id Identificador de la instancia de puntoVenta
     * @param calificaciones  Colección de instancias de CalificacionEntity a asociar a instancia
     * de punto
     * @return Nueva colección de BookEntity asociada a la instancia de Author
     */
    public List<CalificacionEntity> replaceCalificaciones(Long puntoId, List<CalificacionEntity> calificaciones) {
        LOGGER.log(Level.INFO, "Inicia proceso de reemplazar los calificaciones asocidas al punto con id = {0}", puntoId);
        PuntoVentaEntity puntoEntity = puntoPersistence.find(puntoId);
        List<CalificacionEntity> calList = calificacionPersistence.findAll();
        for (CalificacionEntity calificacion : calList) {
            if (calificaciones.contains(calificacion)) {
                
                   calificacion.setPuntoVenta(puntoEntity);
 
            }
        }
        puntoEntity.setCalificaciones(calList);
        LOGGER.log(Level.INFO, "Termina proceso de reemplazar las calificaciones asocidos al punto con id = {0}", puntoId);
        return  puntoEntity.getCalificaciones();
    }
    
    
    
    /**
     * Retorna todas las calificaciones asociadas a un punto.
     *
     * @param puntoId El ID del punto de venta buscado
     * @return La lista de calificaciones del punto de venta.
     */
    public List<CalificacionEntity> getCalificaciones(Long puntoId)
    {
        LOGGER.log(Level.INFO,"Inicia proceso de consulta de calificaciones del punto de venta con id:{0}",puntoId);
        List<CalificacionEntity> listaCalificaciones= puntoPersistence.find(puntoId).getCalificaciones();
        LOGGER.log(Level.INFO,"Inicia proceso de consulta de calificaciones del punto de venta con id:{0}",puntoId);
        return listaCalificaciones;
 
    }
    
    
     /**
     * Retorna una calificación asociada a un punto de venta.
     *
     * @param puntoId El id del punto de venta a buscar.
     * @param calificacionId El id de la calificación a buscar
     * @return La calificación encontrada dentro del punto.
     * @throws BusinessLogicException Si la calificación no se encuentra en el punto.
     * 
     */
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
      
 
       
         /**
     * Borrar una calificación de un punto de venta. Este metodo se utiliza para borrar la
     * relacion de una calificación.
     *@param  puntoId el punto de venta del que se desea borrar la calificación.
     * @param calificacionId La calificacion que se desea borrar del punto de venta.
     */
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
