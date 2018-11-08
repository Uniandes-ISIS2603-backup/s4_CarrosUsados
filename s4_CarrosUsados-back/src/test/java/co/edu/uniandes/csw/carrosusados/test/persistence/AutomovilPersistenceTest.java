/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.test.persistence;

import co.edu.uniandes.csw.carrosusados.entities.AutomovilEntity;
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
public class AutomovilPersistenceTest {
    
    @Inject
    private AutomovilPersistence automovilPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<AutomovilEntity> data = new ArrayList<AutomovilEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AutomovilEntity.class.getPackage())
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
        em.createQuery("delete from AutomovilEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AutomovilEntity entity = factory.manufacturePojo(AutomovilEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Automovil.
     */
    @Test
    public void createAutomovilTest() {
        PodamFactory factory = new PodamFactoryImpl();
        AutomovilEntity newEntity = factory.manufacturePojo(AutomovilEntity.class);
        AutomovilEntity result = automovilPersistence.create(newEntity);

        Assert.assertNotNull(result);

        AutomovilEntity entity = em.find(AutomovilEntity.class, result.getId());

        Assert.assertEquals(newEntity.getAnio(), entity.getAnio());
        Assert.assertEquals(newEntity.getColor(), entity.getColor());
        Assert.assertEquals(newEntity.getFechaAgregacion(), entity.getFechaAgregacion());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getMarca(),newEntity.getMarca());
        Assert.assertEquals(newEntity.getModelo(), entity.getModelo());
        Assert.assertEquals(newEntity.getNumChasis(), entity.getNumChasis());
        Assert.assertEquals(newEntity.getPlaca(), entity.getPlaca());
        Assert.assertEquals(newEntity.getPrecioOriginal(), entity.getPrecioOriginal());
    }

    /**
     * Prueba para consultar la lista de Automoviles.
     */
    @Test
    public void getAutomovilesTest() {
        List<AutomovilEntity> list = automovilPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (AutomovilEntity ent : list) {
            boolean found = false;
            for (AutomovilEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Automovil.
     */
    @Test
    public void getAutomovilTest() {
        AutomovilEntity entity = data.get(0);
        AutomovilEntity newEntity = automovilPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getAnio(), entity.getAnio());
        Assert.assertEquals(newEntity.getColor(), entity.getColor());
        Assert.assertEquals(newEntity.getFechaAgregacion(), entity.getFechaAgregacion());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getMarca(),newEntity.getMarca());
        Assert.assertEquals(newEntity.getModelo(), entity.getModelo());
        Assert.assertEquals(newEntity.getNumChasis(), entity.getNumChasis());
        Assert.assertEquals(newEntity.getPlaca(), entity.getPlaca());
        Assert.assertEquals(newEntity.getPrecioOriginal(), entity.getPrecioOriginal());
    }

    /**
     * Prueba para eliminar un Automovil.
     */
    @Test
    public void deleteAutomovilTest() {
        AutomovilEntity entity = data.get(0);
        automovilPersistence.delete(entity.getId());
        AutomovilEntity deleted = em.find(AutomovilEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Automovil.
     */
    @Test
    public void updateAutomovilTest() {
        AutomovilEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AutomovilEntity newEntity = factory.manufacturePojo(AutomovilEntity.class);

        newEntity.setId(entity.getId());

        entity = automovilPersistence.update(newEntity);

        AutomovilEntity resp = em.find(AutomovilEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getAnio(), entity.getAnio());
        Assert.assertEquals(newEntity.getColor(), entity.getColor());
        Assert.assertEquals(newEntity.getFechaAgregacion(), entity.getFechaAgregacion());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getMarca(),newEntity.getMarca());
        Assert.assertEquals(newEntity.getModelo(), entity.getModelo());
        Assert.assertEquals(newEntity.getNumChasis(), entity.getNumChasis());
        Assert.assertEquals(newEntity.getPlaca(), entity.getPlaca());
        Assert.assertEquals(newEntity.getPrecioOriginal(), entity.getPrecioOriginal());
    }

    /**
     * Prueba para consultasr un Automovil por placa.
     */
    @Test
    public void findAutomovilByPlacaTest() {
        AutomovilEntity entity = data.get(0);
        AutomovilEntity newEntity = automovilPersistence.findByPlaca(entity.getPlaca());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getPlaca(), newEntity.getPlaca());

        newEntity = automovilPersistence.findByPlaca(null);
        Assert.assertNull(newEntity);
    }
}
