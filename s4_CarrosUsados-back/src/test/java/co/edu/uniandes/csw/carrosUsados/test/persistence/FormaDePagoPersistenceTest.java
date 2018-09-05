/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.FormaDePagoEntity;
import co.edu.uniandes.csw.carrosUsados.persistence.FormaDePagoPersistence;
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
public class FormaDePagoPersistenceTest {
    
    @Inject
    private FormaDePagoPersistence formaDePagoPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<FormaDePagoEntity> data = new ArrayList<FormaDePagoEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FormaDePagoEntity.class.getPackage())
                .addPackage(FormaDePagoPersistence.class.getPackage())
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
        em.createQuery("delete from FormaDePagoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            FormaDePagoEntity entity = factory.manufacturePojo(FormaDePagoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un FormaDePago.
     */
    @Test
    public void createFormaDePagoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);
        FormaDePagoEntity result = formaDePagoPersistence.create(newEntity);

        Assert.assertNotNull(result);

        FormaDePagoEntity entity = em.find(FormaDePagoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        Assert.assertEquals(newEntity.getCliente(), entity.getCliente());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultar la lista de FormaDePagoes.
     */
    @Test
    public void getFormaDePagoesTest() {
        List<FormaDePagoEntity> list = formaDePagoPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FormaDePagoEntity ent : list) {
            boolean found = false;
            for (FormaDePagoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un FormaDePago.
     */
    @Test
    public void getFormaDePagoTest() {
        FormaDePagoEntity entity = data.get(0);
        FormaDePagoEntity newEntity = formaDePagoPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        Assert.assertEquals(newEntity.getCliente(), entity.getCliente());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para eliminar un FormaDePago.
     */
    @Test
    public void deleteFormaDePagoTest() {
        FormaDePagoEntity entity = data.get(0);
        formaDePagoPersistence.delete(entity.getId());
        FormaDePagoEntity deleted = em.find(FormaDePagoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un FormaDePago.
     */
    @Test
    public void updateFormaDePagoTest() {
        FormaDePagoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FormaDePagoEntity newEntity = factory.manufacturePojo(FormaDePagoEntity.class);

        newEntity.setId(entity.getId());

        entity = formaDePagoPersistence.update(newEntity);

        FormaDePagoEntity resp = em.find(FormaDePagoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
        Assert.assertEquals(newEntity.getCliente(), entity.getCliente());
        Assert.assertEquals(newEntity.getId(), entity.getId());
    }

    /**
     * Prueba para consultasr un FormaDePago por tipo.
     */
    @Test
    public void findFormaDePagoByISBNTest() {
        FormaDePagoEntity entity = data.get(0);
        FormaDePagoEntity newEntity = formaDePagoPersistence.findByTipo(entity.getTipo());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getTipo(), newEntity.getTipo());

        newEntity = formaDePagoPersistence.findByTipo(null);
        Assert.assertNull(newEntity);
    }
}
