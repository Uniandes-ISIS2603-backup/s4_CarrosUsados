/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.persistence;
import co.edu.uniandes.csw.carrosUsados.entities.VendedorEntity;
import co.edu.uniandes.csw.carrosUsados.persistence.VendedorPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author js.bravo
 */
public class VendedorPersistenceTest {
    @Inject
    private VendedorPersistence vendedorPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<VendedorEntity> data = new ArrayList<VendedorEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(VendedorEntity.class.getPackage())
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
    @Test
    public void createVendedorTest() {
        PodamFactory factory = new PodamFactoryImpl();
        VendedorEntity newEntity = factory.manufacturePojo(VendedorEntity.class);
        VendedorEntity result = vendedorPersistence.create(newEntity);

        Assert.assertNotNull(result);

        VendedorEntity entity = em.find(VendedorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());
    }

    /**
     * Prueba para consultar la lista de Vendedores.
     */
    @Test
    public void getAdministradoresTest() {
        List<VendedorEntity> list = vendedorPersistence.findAll();
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
    public void getVendedorTest() {
        VendedorEntity entity = data.get(0);
        VendedorEntity newEntity = vendedorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
               

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());
      
    }

    /**
     * Prueba para eliminar un Vendedor.
     */
    @Test
    public void deleteVendedorTest() {
        VendedorEntity entity = data.get(0);
        vendedorPersistence.delete(entity.getId());
        VendedorEntity deleted = em.find(VendedorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Vendedor.
     */
    @Test
    public void updateVendedorTest() {
        VendedorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        VendedorEntity newEntity = factory.manufacturePojo(VendedorEntity.class);

        newEntity.setId(entity.getId());

        vendedorPersistence.update(newEntity);

        VendedorEntity updated = em.find(VendedorEntity.class, entity.getId());

  Assert.assertEquals(newEntity.getNombre(), updated.getNombre());
        Assert.assertEquals(newEntity.getApellido(), updated.getApellido());
     
    }
}
