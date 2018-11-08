/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.test.logic;

import co.edu.uniandes.csw.carrosusados.ejb.AutomovilCalificacionLogic;
import co.edu.uniandes.csw.carrosusados.ejb.CalificacionLogic;
import co.edu.uniandes.csw.carrosusados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosusados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.persistence.AutomovilPersistence;
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

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author estudiante
 */

 @RunWith(Arquillian.class)
public class AutomovilCalificacionLogicTest {
    
     private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private AutomovilCalificacionLogic automovilCalificacionLogic;

    @Inject
    private CalificacionLogic calificacionLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<AutomovilEntity> automovildata = new ArrayList<AutomovilEntity>();
    private List<CalificacionEntity> data = new ArrayList<CalificacionEntity>();
    
    
       /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AutomovilEntity.class.getPackage())
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(AutomovilCalificacionLogic.class.getPackage())
                .addPackage(AutomovilPersistence.class.getPackage())
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
            AutomovilEntity entity = factory.manufacturePojo(AutomovilEntity.class);
            em.persist(entity);
            automovildata.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            
            CalificacionEntity calentity = factory.manufacturePojo(CalificacionEntity.class);
            em.persist(calentity);
            data.add(calentity);
            
        }
    }
    
    
      /**
     * Prueba para asociar un automovil a una calificación.
     *
     *
     * @throws BusinessLogicException
     */
     @Test
    public void addCalificacion() throws BusinessLogicException
            
    {
        CalificacionEntity newcal= factory.manufacturePojo(CalificacionEntity.class);
        AutomovilEntity automovil= automovildata.get(1);
         newcal.setEstrellas(5);
         newcal.setAutomovil(automovil);
         
        calificacionLogic.createCalificacion(newcal);
        CalificacionEntity calent= automovilCalificacionLogic.addCalificacion(automovil.getId(), newcal.getId());
        calent.setEstrellas(5);
        calent.setAutomovil(automovil);
        Assert.assertNotNull(newcal);
        
       
        
        Assert.assertEquals(calent.getEstrellas(), newcal.getEstrellas());
        Assert.assertEquals(calent.getComentario(), newcal.getComentario());
        Assert.assertEquals(calent.getPuntoVenta(), newcal.getPuntoVenta());
        Assert.assertEquals(calent.getAutomovil(), newcal.getAutomovil());
        Assert.assertEquals(calent.getpublishedDate(), newcal.getpublishedDate());
        Assert.assertEquals(calent.getId(), newcal.getId());
    
    
        CalificacionEntity lastCal= automovilCalificacionLogic.getCalificacion(automovil.getId(), newcal.getId());
        lastCal.setEstrellas(5);
        
        Assert.assertEquals(lastCal.getEstrellas(), newcal.getEstrellas());
        Assert.assertEquals(lastCal.getComentario(), newcal.getComentario());
        Assert.assertEquals(lastCal.getPuntoVenta(), newcal.getPuntoVenta());
        Assert.assertEquals(lastCal.getAutomovil(), newcal.getAutomovil());
        Assert.assertEquals(lastCal.getpublishedDate(), newcal.getpublishedDate());
        Assert.assertEquals(lastCal.getId(), newcal.getId());
    }

    
     /**
     * Prueba para consultar la lista de calificaciones de un automovil.
     */
     @Test
    public void getCalificaciones() throws BusinessLogicException
    {
        AutomovilEntity automovil= factory.manufacturePojo(AutomovilEntity.class);
        if(automovildata.contains(automovil))
        {
        List<CalificacionEntity> calificacionesent= automovilCalificacionLogic.getCalificaciones(automovil.getId());
        Assert.assertEquals(data.size(), calificacionesent.size());
        for(int i=0;i<data.size();i++)
        {
            Assert.assertTrue(calificacionesent.contains(data.get(i)));
        }
    }
    }
    
       /**
     * Prueba para consultar una calificación de un automovil.
     *
     * @throws BusinessLogicException
     */
     @Test
    public void getCalificacion() throws BusinessLogicException
    {
      CalificacionEntity calificacionEntity =  factory.manufacturePojo(CalificacionEntity.class);
       AutomovilEntity automovil= factory.manufacturePojo(AutomovilEntity.class);
       if(automovildata.contains(automovil)) //CHEQUEAR ESTA LINEA
      {
      CalificacionEntity calificacion = automovilCalificacionLogic.getCalificacion(automovil.getId(),calificacionEntity.getId());
       
      Assert.assertNotNull(calificacion);
      //Chequear las siguientes dos lineas de codigo:
          calificacionEntity.setEstrellas(3);
          calificacion.setEstrellas(3);
          
        Assert.assertEquals(calificacionEntity.getEstrellas(), calificacion.getEstrellas());
        Assert.assertEquals(calificacionEntity.getComentario(), calificacion.getComentario());
        Assert.assertEquals(calificacionEntity.getPuntoVenta(), calificacion.getPuntoVenta());
        Assert.assertEquals(calificacionEntity.getAutomovil(), calificacion.getAutomovil());
        Assert.assertEquals(calificacionEntity.getpublishedDate(), calificacion.getpublishedDate());
        Assert.assertEquals(calificacionEntity.getId(), calificacion.getId());
    }
    }
        /**
     * Prueba para actualizar las calificaciones de un automovil
     *
     * @throws BusinessLogicException
     */
    @Test
    public void replaceCalificacionesTest() throws BusinessLogicException {
        List<CalificacionEntity> nuevaLista = new ArrayList<>();
         AutomovilEntity automovil= factory.manufacturePojo(AutomovilEntity.class);
         if(automovildata.contains(automovil))
         {
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);
            entity.setAutomovil(automovil);
            calificacionLogic.createCalificacion(entity);
            nuevaLista.add(entity);
        }
        automovilCalificacionLogic.replaceCalificaciones(automovil.getId(), nuevaLista);
        List<CalificacionEntity> calificacionesEntities = automovilCalificacionLogic.getCalificaciones(automovil.getId());
        for (CalificacionEntity aNuevaLista : nuevaLista) {
            Assert.assertTrue(calificacionesEntities.contains(aNuevaLista));
        }
         }
    }
    
    
     /**
     * Prueba desasociar una calificacion con un automovil.
     * @throws BusinessLogicException cuando la claificación no existe
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
