/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.persistence;


import co.edu.uniandes.csw.carrosUsados.entities.PuntoVentaEntity;

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
public class PuntoVentaPersistenceTest {
     @Inject
    private PuntoVentaPersistence puntoPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;
    
    private List<PuntoVentaEntity> data = new ArrayList<PuntoVentaEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PuntoVentaEntity.class.getPackage())
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
    
    
    /**
     * Prueba para crear un punto.
     */
    @Test
    public void createPuntoVentaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        PuntoVentaEntity newEntity = factory.manufacturePojo(PuntoVentaEntity.class);
        PuntoVentaEntity result = puntoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        PuntoVentaEntity entity = em.find(PuntoVentaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
        Assert.assertEquals(newEntity.getEmpleados(), entity.getEmpleados());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de puntos.
     */
    @Test
    public void getPuntosTest() {
        List<PuntoVentaEntity> list = puntoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PuntoVentaEntity ent : list) {
            boolean found = false;
            for (PuntoVentaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Punto.
     */
    @Test
    public void getPuntoTest() {
        PuntoVentaEntity entity = data.get(0);
        PuntoVentaEntity newEntity = puntoPersistence.find(entity.getId());
       
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
        Assert.assertEquals(newEntity.getEmpleados(), entity.getEmpleados());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

  
    @Test
    public void deletepuntoTest() {
        
        PuntoVentaEntity entity = data.get(0);
        puntoPersistence.delete(entity.getId());
        PuntoVentaEntity deleted = em.find(PuntoVentaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

   
    @Test
    public void updatePuntoTest() {
        PuntoVentaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PuntoVentaEntity newEntity = factory.manufacturePojo(PuntoVentaEntity.class);

        newEntity.setId(entity.getId());

        puntoPersistence.update(newEntity);

        PuntoVentaEntity resp = em.find(PuntoVentaEntity.class, entity.getId());

       Assert.assertEquals(newEntity.getCiudad(), entity.getCiudad());
        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
        Assert.assertEquals(newEntity.getEmpleados(), entity.getEmpleados());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }
    
}
