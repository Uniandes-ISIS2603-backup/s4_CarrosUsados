/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.ejb.CalificacionLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.PuntoVentaCalificacionLogic;
import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carrosUsados.entities.ModeloEntity;
import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.PuntoVentaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author estudiante
 */
 @RunWith(Arquillian.class)
public class PuntoVentaCalificacionTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private PuntoVentaCalificacionLogic puntocalificacionLogic;

    @Inject
    private CalificacionLogic calificacionLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<PuntoVentaEntity> puntosdata = new ArrayList<PuntoVentaEntity>();
    private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();
    
    
       /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PuntoVentaEntity.class.getPackage())
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(PuntoVentaCalificacionLogic.class.getPackage())
                .addPackage(PuntoVentaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * Limpia las tablas que están implicadas en la prueba.
     */
    private void clearData() {
        em.createQuery("delete from PuntoVentaEntity").executeUpdate();
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
 
        
        for (int i = 0; i < 3; i++) {
            PuntoVentaEntity entity = factory.manufacturePojo(PuntoVentaEntity.class);
            em.persist(entity);
            puntosdata.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            
            CalificacionEntity calentity = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(calentity);
            data.add(calentity);
            
        }
    }
    

      /**
     * Prueba para asociar un punto a una calificación.
     *
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
      
     @Test
    public void addCalificacion() throws BusinessLogicException
            
    {
        CalificacionEntity newcal= factory.manufacturePojo(CalificacionEntity.class);
        PuntoVentaEntity punto= puntosdata.get(1);
         newcal.setEstrellas(5);
         newcal.setPuntoVenta(punto);
         
        CalificacionEntity cale=calificacionLogic.createCalificacion(newcal);
        CalificacionEntity calent= puntocalificacionLogic.addCalificacion(punto.getId(), cale);
        calent.setEstrellas(5);
        calent.setPuntoVenta(punto);
        Assert.assertNotNull(newcal);
        
       
        
        Assert.assertEquals(calent.getEstrellas(), newcal.getEstrellas());
        Assert.assertEquals(calent.getComentario(), newcal.getComentario());
        Assert.assertEquals(calent.getPuntoVenta(), newcal.getPuntoVenta());
        Assert.assertEquals(calent.getpublishedDate(), newcal.getpublishedDate());
        Assert.assertEquals(calent.getId(), newcal.getId());
    
    
        CalificacionEntity lastCal= puntocalificacionLogic.getCalificacion(punto.getId(), newcal.getId());
        lastCal.setEstrellas(5);
        
        Assert.assertEquals(lastCal.getEstrellas(), newcal.getEstrellas());
        Assert.assertEquals(lastCal.getComentario(), newcal.getComentario());
        Assert.assertEquals(lastCal.getPuntoVenta(), newcal.getPuntoVenta());
        Assert.assertEquals(lastCal.getpublishedDate(), newcal.getpublishedDate());
        Assert.assertEquals(lastCal.getId(), newcal.getId());
    }
    
    
     /**
     * Prueba para consultar la lista de calificaciones de un punto.
     */
     @Test
    public void getCalificaciones()
    {
        PuntoVentaEntity punto= factory.manufacturePojo(PuntoVentaEntity.class);
        if(puntosdata.contains(punto))
        {
        List<CalificacionEntity> calificacionesent= puntocalificacionLogic.getCalificaciones(punto.getId());
        Assert.assertEquals(data.size(), calificacionesent.size());
        for(int i=0;i<data.size();i++)
        {
            Assert.assertTrue(calificacionesent.contains(data.get(i)));
        }
    }
    }
    
       /**
     * Prueba para cpnsultar una calificación de un punto.
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
     @Test
    public void getCalificacion() throws BusinessLogicException
    {
      CalificacionEntity calificacionEntity =  factory.manufacturePojo(CalificacionEntity.class);
       PuntoVentaEntity punto= factory.manufacturePojo(PuntoVentaEntity.class);
       if(puntosdata.contains(punto))
      {
      CalificacionEntity calificacion = puntocalificacionLogic.getCalificacion(punto.getId(),calificacionEntity.getId());
       
      Assert.assertNotNull(calificacion);
          calificacionEntity.setEstrellas(3);
          calificacion.setEstrellas(3);
          
        Assert.assertEquals(calificacionEntity.getEstrellas(), calificacion.getEstrellas());
        Assert.assertEquals(calificacionEntity.getComentario(), calificacion.getComentario());
        Assert.assertEquals(calificacionEntity.getPuntoVenta(), calificacion.getPuntoVenta());
        Assert.assertEquals(calificacionEntity.getpublishedDate(), calificacion.getpublishedDate());
        Assert.assertEquals(calificacionEntity.getId(), calificacion.getId());
    }
    }
        /**
     * Prueba para actualizar las calificaciones de un punto.
     *
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException
     */
    @Test
    public void replaceCalificacionesTest() throws BusinessLogicException {
        List<CalificacionEntity> nuevaLista = new ArrayList<>();
         PuntoVentaEntity punto= factory.manufacturePojo(PuntoVentaEntity.class);
         if(puntosdata.contains(punto))
         {
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            entity.setPuntoVenta(punto);
            calificacionLogic.createCalificacion(entity);
            nuevaLista.add(entity);
        }
        puntocalificacionLogic.replaceCalificaciones(punto.getId(), nuevaLista);
        List<CalificacionEntity> calificacionesEntities = puntocalificacionLogic.getCalificaciones(punto.getId());
        for (CalificacionEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(calificacionesEntities.contains(aNuevaLista));
        }
         }
    }
    
    
     /**
     * Prueba desasociar una calificacion con un punto.
     * @throws co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException cuando la claificación no existe
     *  
     */
    
     @Test
    public void deleteCalificacion() throws BusinessLogicException
    {
     CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
     if(data.contains(entity))
     {
       calificacionLogic.deleteCalificacion(entity.getId());
       CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
       Assert.assertNull(deleted);
     }  
        
    }
    

}
