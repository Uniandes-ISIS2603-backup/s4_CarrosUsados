/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.test.logic;

import co.edu.uniandes.csw.carrosusados.ejb.VendedorLogic;
import co.edu.uniandes.csw.carrosusados.entities.VendedorEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.persistence.VendedorPersistence;
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
 * @author js.bravo
 */
@RunWith(Arquillian.class)
public class VendedorLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private VendedorLogic vendedorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<VendedorEntity> data = new ArrayList<VendedorEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VendedorEntity.class.getPackage())
                .addPackage(VendedorLogic.class.getPackage())
                .addPackage(VendedorPersistence.class.getPackage())
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
        em.createQuery("delete from VendedorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            VendedorEntity entity = factory.manufacturePojo(VendedorEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Vendedor en la DB.
     */
    public void createVendedorTest() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        VendedorEntity newEntity = factory.manufacturePojo(VendedorEntity.class);
        newEntity.setNombre("Thomas");
        newEntity.setApellido("Müller");
        VendedorEntity result = vendedorLogic.createVendedor(newEntity);

        Assert.assertNotNull(result);

        VendedorEntity entity = em.find(VendedorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());
    }

    /**
     * Prueba para crear un Vendedor en la DB con datos incorrectos.
     */
    @Test(expected = BusinessLogicException.class)
    public void createVendedorTestNombreInvalido() throws BusinessLogicException {
        PodamFactory factory = new PodamFactoryImpl();
        VendedorEntity newEntity = factory.manufacturePojo(VendedorEntity.class);
        newEntity.setNombre("6ix9ine");
        newEntity.setApellido("2Pac");
        VendedorEntity result = vendedorLogic.createVendedor(newEntity);

        Assert.assertNotNull(result);

        VendedorEntity entity = em.find(VendedorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());
    }

    /**
     * Prueba para consultar la lista de Vendedores.
     */
    @Test
    public void getVendedoresTest() {
        List<VendedorEntity> list = vendedorLogic.getVendedores();
        Assert.assertEquals(data.size(), list.size());
        for (VendedorEntity ent : list) {
            boolean found = false;
            for (VendedorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Vendedor.
     */
    @Test
    public void getVendedorTest() throws BusinessLogicException {
        VendedorEntity entity = data.get(0);
        VendedorEntity newEntity = vendedorLogic.getVendedor(entity.getId());
        Assert.assertNotNull(newEntity);

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());

    }

    /**
     * Prueba para eliminar un Vendedor.
     */
    @Test
    public void deleteVendedorTest() throws BusinessLogicException {
        VendedorEntity entity = data.get(0);
        vendedorLogic.deleteVendedor(entity.getId());
        VendedorEntity deleted = em.find(VendedorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Vendedor.
     */
    @Test
    public void updateVendedorTest() throws BusinessLogicException {
        VendedorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        VendedorEntity newEntity = factory.manufacturePojo(VendedorEntity.class);
        newEntity.setNombre("Juan");
        newEntity.setApellido("Bravo");
        newEntity.setId(entity.getId());

        vendedorLogic.updateVendedor(entity.getId(), newEntity);

        VendedorEntity updated = em.find(VendedorEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombre(), updated.getNombre());
        Assert.assertEquals(newEntity.getApellido(), updated.getApellido());

    }
}
