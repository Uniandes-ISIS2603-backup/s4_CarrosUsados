/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carros.usados.test.logic;


import co.edu.uniandes.csw.carros.usados.ejb.CalificacionLogic;
import co.edu.uniandes.csw.carros.usados.entities.CalificacionEntity;
import co.edu.uniandes.csw.carros.usados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carros.usados.persistence.CalificacionPersistence;
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
public class CalificacionLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();

   @Inject
   CalificacionLogic calificacionLogic;
    
   @PersistenceContext
   private EntityManager em;

   @Inject
   private UserTransaction utx;
   
   private List<CalificacionEntity> data= new ArrayList<CalificacionEntity>();
   
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CalificacionEntity.class.getPackage())
                .addPackage(CalificacionPersistence.class.getPackage())
                .addPackage(CalificacionLogic.class.getPackage())
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
        em.createQuery("delete from CalificacionEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
      
        for (int i = 0; i < 3; i++) {
            CalificacionEntity entity = factory.manufacturePojo(CalificacionEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
       @Test
    public void createCalificacionTest() throws BusinessLogicException {
        
        CalificacionEntity newEntity = factory.manufacturePojo(CalificacionEntity.class);
         newEntity.setEstrellas(3);
            
        CalificacionEntity result = calificacionLogic.createCalificacion(newEntity);
        Assert.assertNotNull(result);
        CalificacionEntity entity = em.find(CalificacionEntity.class, result.getId());
        entity.setEstrellas(3);
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getAutomovil(), entity.getAutomovil());
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(newEntity.getEstrellas(), entity.getEstrellas());
        Assert.assertEquals(newEntity.getPuntoVenta(), entity.getPuntoVenta());
        Assert.assertEquals(newEntity.getpublishedDate(), entity.getpublishedDate());
       
    }
    
  
    
    @Test
    public void getCalificacionesTest() throws BusinessLogicException {
        List<CalificacionEntity> list = calificacionLogic.getCalificaciones();
        Assert.assertEquals(data.size(), list.size());
        for (CalificacionEntity entity : list) {
            boolean found = false;
            for (CalificacionEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
   
    @Test
    public void getCalificacionTest() throws BusinessLogicException {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity newEntity = calificacionLogic.getCalificacion(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getAutomovil(), entity.getAutomovil());
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(newEntity.getEstrellas(), entity.getEstrellas());
        Assert.assertEquals(newEntity.getPuntoVenta(), entity.getPuntoVenta());
        Assert.assertEquals(newEntity.getpublishedDate(), entity.getpublishedDate());
    }
    
   
    @Test
    public void updateCalificacionTest() throws BusinessLogicException {
        CalificacionEntity entity = data.get(0);
        CalificacionEntity pojoEntity = factory.manufacturePojo(CalificacionEntity.class);

        pojoEntity.setId(entity.getId());

        calificacionLogic.updateCalificacion(pojoEntity.getId(), pojoEntity);

        CalificacionEntity resp = em.find(CalificacionEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getAutomovil(), resp.getAutomovil());
        Assert.assertEquals(pojoEntity.getComentario(), resp.getComentario());
        Assert.assertEquals(pojoEntity.getEstrellas(), resp.getEstrellas());
        Assert.assertEquals(pojoEntity.getPuntoVenta(), resp.getPuntoVenta());
        Assert.assertEquals(pojoEntity.getpublishedDate(), resp.getpublishedDate());
        
    }
    
  
    @Test
    public void deleteCalificacionTest() throws BusinessLogicException {
        CalificacionEntity entity = data.get(0);
        calificacionLogic.deleteCalificacion(entity.getId());
        CalificacionEntity deleted = em.find(CalificacionEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
