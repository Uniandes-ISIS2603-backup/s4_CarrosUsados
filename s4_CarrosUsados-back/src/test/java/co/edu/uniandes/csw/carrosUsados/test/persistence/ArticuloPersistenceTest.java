/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.ArticuloEntity;
import co.edu.uniandes.csw.carrosUsados.persistence.ArticuloPersistence;
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
public class ArticuloPersistenceTest {
    
    @Inject
    private ArticuloPersistence articuloPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<ArticuloEntity> data = new ArrayList<ArticuloEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ArticuloEntity.class.getPackage())
                .addPackage(ArticuloPersistence.class.getPackage())
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
        em.createQuery("delete from ArticuloEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ArticuloEntity entity = factory.manufacturePojo(ArticuloEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Articulo.
     */
    @Test
    public void createArticuloTest() {
        PodamFactory factory = new PodamFactoryImpl();
        ArticuloEntity newEntity = factory.manufacturePojo(ArticuloEntity.class);
        ArticuloEntity result = articuloPersistence.create(newEntity);

        Assert.assertNotNull(result);

        ArticuloEntity entity = em.find(ArticuloEntity.class, result.getId());

        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.isDisponibilidad(),newEntity.isDisponibilidad());
        Assert.assertEquals(newEntity.getAutomovil(), entity.getAutomovil());
        Assert.assertEquals(newEntity.getClientes(), entity.getClientes());
    }

    /**
     * Prueba para consultar la lista de Articuloes.
     */
    @Test
    public void getArticuloesTest() {
        List<ArticuloEntity> list = articuloPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ArticuloEntity ent : list) {
            boolean found = false;
            for (ArticuloEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Articulo.
     */
    @Test
    public void getArticuloTest() {
        ArticuloEntity entity = data.get(0);
        ArticuloEntity newEntity = articuloPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.isDisponibilidad(),newEntity.isDisponibilidad());
        Assert.assertEquals(newEntity.getAutomovil(), entity.getAutomovil());
        Assert.assertEquals(newEntity.getClientes(), entity.getClientes());
    }

    /**
     * Prueba para eliminar un Articulo.
     */
    @Test
    public void deleteArticuloTest() {
        ArticuloEntity entity = data.get(0);
        articuloPersistence.delete(entity.getId());
        ArticuloEntity deleted = em.find(ArticuloEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Articulo.
     */
    @Test
    public void updateArticuloTest() {
        ArticuloEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ArticuloEntity newEntity = factory.manufacturePojo(ArticuloEntity.class);

        newEntity.setId(entity.getId());

        entity = articuloPersistence.update(newEntity);

        ArticuloEntity resp = em.find(ArticuloEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.isDisponibilidad(),newEntity.isDisponibilidad());
        Assert.assertEquals(newEntity.getAutomovil(), entity.getAutomovil());
        Assert.assertEquals(newEntity.getClientes(), entity.getClientes());
    }    
}
