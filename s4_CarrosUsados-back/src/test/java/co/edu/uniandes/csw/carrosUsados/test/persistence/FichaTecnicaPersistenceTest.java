/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.entities.FichaTecnicaEntity;
import co.edu.uniandes.csw.carrosUsados.persistence.AutomovilPersistence;
import co.edu.uniandes.csw.carrosUsados.persistence.FichaTecnicaPersistence;
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
public class FichaTecnicaPersistenceTest {
     
    @Inject
    private FichaTecnicaPersistence fichaTecnicaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<FichaTecnicaEntity> data = new ArrayList<FichaTecnicaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FichaTecnicaEntity.class.getPackage())
                .addPackage(FichaTecnicaPersistence.class.getPackage())
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
        em.createQuery("delete from FichaTecnicaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FichaTecnicaEntity entity = factory.manufacturePojo(FichaTecnicaEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un FichaTecnica.
     */
    @Test
    public void createFichaTecnicaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FichaTecnicaEntity newEntity = factory.manufacturePojo(FichaTecnicaEntity.class);
        FichaTecnicaEntity result = fichaTecnicaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        FichaTecnicaEntity entity = em.find(FichaTecnicaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNumAirbags(), entity.getNumAirbags());
        Assert.assertEquals(newEntity.getRines(), entity.getRines());
        Assert.assertEquals(newEntity.getVidrios(), entity.getVidrios());
    }

    /**
     * Prueba para consultar la lista de FichaTecnicas.
     */
    @Test
    public void getFichaTecnicasTest() {
        List<FichaTecnicaEntity> list = fichaTecnicaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FichaTecnicaEntity ent : list) {
            boolean found = false;
            for (FichaTecnicaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un FichaTecnica.
     */
    @Test
    public void getFichaTecnicaTest() {
        FichaTecnicaEntity entity = data.get(0);
        FichaTecnicaEntity newEntity = fichaTecnicaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNumAirbags(), entity.getNumAirbags());
        Assert.assertEquals(newEntity.getRines(), entity.getRines());
        Assert.assertEquals(newEntity.getVidrios(), entity.getVidrios());
    }

    /**
     * Prueba para eliminar un FichaTecnica.
     */
    @Test
    public void deleteFichaTecnicaTest() {
        FichaTecnicaEntity entity = data.get(0);
        fichaTecnicaPersistence.delete(entity.getId());
        FichaTecnicaEntity deleted = em.find(FichaTecnicaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un FichaTecnica.
     */
    @Test
    public void updateFichaTecnicaTest() {
        FichaTecnicaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FichaTecnicaEntity newEntity = factory.manufacturePojo(FichaTecnicaEntity.class);

        newEntity.setId(entity.getId());

        entity = fichaTecnicaPersistence.update(newEntity);

        FichaTecnicaEntity resp = em.find(FichaTecnicaEntity.class, entity.getId());
        Assert.assertEquals(newEntity.getNumAirbags(), entity.getNumAirbags());
        Assert.assertEquals(newEntity.getRines(), entity.getRines());
        Assert.assertEquals(newEntity.getVidrios(), entity.getVidrios());   
    }

    
}

