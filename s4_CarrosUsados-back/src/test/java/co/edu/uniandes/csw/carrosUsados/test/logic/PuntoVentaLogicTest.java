/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.ejb.CalificacionLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.PuntoVentaLogic;
import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.AutomovilPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.CalificacionPersistence;
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
public class PuntoVentaLogicTest {
    

     private PodamFactory factory = new PodamFactoryImpl();

   @Inject
   PuntoVentaLogic puntoLogic;
    
   @PersistenceContext
   private EntityManager em;

   @Inject
   private UserTransaction utx;
   
   private List<PuntoVentaEntity> data= new ArrayList<PuntoVentaEntity>();
   
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PuntoVentaEntity.class.getPackage())
                .addPackage(PuntoVentaPersistence.class.getPackage())
                .addPackage(PuntoVentaLogic.class.getPackage())
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
            em.joinTransaction();
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
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PuntoVentaEntity entity = factory.manufacturePojo(PuntoVentaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
       @Test
    public void createPuntoTest() throws BusinessLogicException {
        
        PuntoVentaEntity newEntity = factory.manufacturePojo(PuntoVentaEntity.class);
       
            
        PuntoVentaEntity result = puntoLogic.createPuntoVenta(newEntity);
        Assert.assertNotNull(result);
        PuntoVentaEntity entity = em.find(PuntoVentaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
        Assert.assertEquals(newEntity.getEmpleados(), entity.getEmpleados());
           Assert.assertEquals(newEntity.getCalificaciones(), entity.getCalificaciones());
              Assert.assertEquals(newEntity.getAutomovil(), entity.getAutomovil());
           
    }
    
  
    
    @Test
    public void getPuntosTest() throws BusinessLogicException {
        List<PuntoVentaEntity> list = puntoLogic.getPuntosDeVenta();
        Assert.assertEquals(data.size(), list.size());
        for (PuntoVentaEntity entity : list) {
            boolean found = false;
            for (PuntoVentaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
   
    @Test
    public void getCalificacionTest() throws BusinessLogicException {
        PuntoVentaEntity entity = data.get(0);
        PuntoVentaEntity newEntity = puntoLogic.getPuntoVenta(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
        Assert.assertEquals(newEntity.getCalificaciones(), entity.getCalificaciones());
        Assert.assertEquals(newEntity.getAutomovil(), entity.getAutomovil());
        Assert.assertEquals(newEntity.getEmpleados(), entity.getEmpleados());
    }
    
   
    @Test
    public void updateCalificacionTest() throws BusinessLogicException {
        PuntoVentaEntity entity = data.get(0);
        PuntoVentaEntity pojoEntity = factory.manufacturePojo(PuntoVentaEntity.class);

        pojoEntity.setId(entity.getId());

        puntoLogic.updatePuntoVenta(pojoEntity.getId(), pojoEntity);

        PuntoVentaEntity resp = em.find(PuntoVentaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getEmpleados(), resp.getEmpleados());
        Assert.assertEquals(pojoEntity.getCiudad(), resp.getCiudad());
        Assert.assertEquals(pojoEntity.getUbicacion(), resp.getUbicacion());
        Assert.assertEquals(pojoEntity.getAutomovil(), resp.getAutomovil());
        Assert.assertEquals(pojoEntity.getCalificaciones(), resp.getCalificaciones());
        
    }
    
  
    @Test
    public void deletePuntoVentaTest() throws BusinessLogicException {
        PuntoVentaEntity entity = data.get(0);
       puntoLogic.deletePuntoVenta(entity.getId());
       PuntoVentaEntity deleted = em.find(PuntoVentaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
