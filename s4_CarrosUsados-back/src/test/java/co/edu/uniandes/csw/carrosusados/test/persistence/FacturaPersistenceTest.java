/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.test.persistence;

import co.edu.uniandes.csw.carrosusados.entities.FacturaEntity;
import co.edu.uniandes.csw.carrosusados.persistence.FacturaPersistence;
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
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class FacturaPersistenceTest {
    @Inject
    private FacturaPersistence facturaPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<FacturaEntity> data = new ArrayList<FacturaEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FacturaEntity.class.getPackage())
                .addPackage(FacturaPersistence.class.getPackage())
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
        em.createQuery("delete from FacturaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            FacturaEntity entity = factory.manufacturePojo(FacturaEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear una Factura.
     */
    @Test
    public void createFacturaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);
        FacturaEntity result = facturaPersistence.create(newEntity);

        Assert.assertNotNull(result);

        FacturaEntity entity = em.find(FacturaEntity.class, result.getId());

        Assert.assertEquals(newEntity.getIdFactura(), entity.getIdFactura());
        Assert.assertEquals(newEntity.getProducto(), entity.getProducto());
        Assert.assertEquals(newEntity.getSubtotal(), entity.getSubtotal());
        Assert.assertEquals(newEntity.getTotal(), entity.getTotal());
        Assert.assertEquals(newEntity.getFormaDePago(), entity.getFormaDePago());

    }

    /**
     * Prueba para consultar la lista de Facturas.
     */
    @Test
    public void getFacturasTest() {
        List<FacturaEntity> list = facturaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (FacturaEntity ent : list) {
            boolean found = false;
            for (FacturaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Factira.
     */
    @Test
    public void getFacturaTest() {
        FacturaEntity entity = data.get(0);
        FacturaEntity newEntity = facturaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getIdFactura(), entity.getIdFactura());
        Assert.assertEquals(newEntity.getProducto(), entity.getProducto());
        Assert.assertEquals(newEntity.getSubtotal(), entity.getSubtotal());
        Assert.assertEquals(newEntity.getTotal(), entity.getTotal());
        Assert.assertEquals(newEntity.getFormaDePago(), entity.getFormaDePago());
    }

    /**
     * Prueba para eliminar una Factura.
     */
    @Test
    public void deleteFacturaTest() {
        FacturaEntity entity = data.get(0);
        facturaPersistence.delete(entity.getId());
        FacturaEntity deleted = em.find(FacturaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Marca.
     */
    @Test
    public void updatePagoTest() {
        FacturaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FacturaEntity newEntity = factory.manufacturePojo(FacturaEntity.class);

        newEntity.setId(entity.getId());
        newEntity.setTotal(123);

        facturaPersistence.update(newEntity);

        FacturaEntity resp = em.find(FacturaEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getIdFactura(), resp.getIdFactura());
        Assert.assertEquals(newEntity.getProducto(), resp.getProducto());
        Assert.assertEquals(newEntity.getSubtotal(), resp.getSubtotal());
        Assert.assertEquals(newEntity.getTotal(), resp.getTotal());
        Assert.assertEquals(newEntity.getFormaDePago(), resp.getFormaDePago());
    }
}
