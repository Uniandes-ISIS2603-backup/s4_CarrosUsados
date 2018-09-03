/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.persistence;

import co.edu.uniandes.csw.carrosUsados.entities.AdministradorEntity;
import co.edu.uniandes.csw.carrosUsados.persistence.AdministradorPersistence;
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
 * @author js.bravo
 */
@RunWith(Arquillian.class)
public class AdministradorPersistenceTest {

    @Inject
    private AdministradorPersistence administradorPersistence;

    @PersistenceContext
    private EntityManager em;

    @Inject
    UserTransaction utx;

    private List<AdministradorEntity> data = new ArrayList<AdministradorEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AdministradorEntity.class.getPackage())
                .addPackage(AdministradorPersistence.class.getPackage())
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
        em.createQuery("delete from AdministradorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {

            AdministradorEntity entity = factory.manufacturePojo(AdministradorEntity.class);

            em.persist(entity);

            data.add(entity);
        }
    }

    /**
     * Prueba para crear un Administrador en la DB.
     */
    @Test
    public void createAdministradorTest() {
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);
        AdministradorEntity result = administradorPersistence.create(newEntity);

        Assert.assertNotNull(result);

        AdministradorEntity entity = em.find(AdministradorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());
        Assert.assertEquals(newEntity.getCargo(), entity.getCargo());
        Assert.assertEquals(newEntity.getNombre_usuario(), entity.getNombre_usuario());
        Assert.assertEquals(newEntity.getFecha_inicio(), entity.getFecha_inicio());
        Assert.assertEquals(newEntity.getContrasena(), entity.getContrasena());
        Assert.assertEquals(newEntity.getTelefono(), entity.getTelefono());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getFecha_nacimiento(), entity.getFecha_nacimiento());
    }

    /**
     * Prueba para consultar la lista de Administradores.
     */
    @Test
    public void getAdministradoresTest() {
        List<AdministradorEntity> list = administradorPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (AdministradorEntity ent : list) {
            boolean found = false;
            for (AdministradorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Administrador.
     */
    @Test
    public void getAdministradorTest() {
        AdministradorEntity entity = data.get(0);
        AdministradorEntity newEntity = administradorPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
               

        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getApellido(), entity.getApellido());
        Assert.assertEquals(newEntity.getCargo(), entity.getCargo());
        Assert.assertEquals(newEntity.getNombre_usuario(), entity.getNombre_usuario());
        Assert.assertEquals(newEntity.getFecha_inicio(), entity.getFecha_inicio());
        Assert.assertEquals(newEntity.getContrasena(), entity.getContrasena());
        Assert.assertEquals(newEntity.getTelefono(), entity.getTelefono());
        Assert.assertEquals(newEntity.getCorreo(), entity.getCorreo());
        Assert.assertEquals(newEntity.getFecha_nacimiento() , entity.getFecha_nacimiento());
    }

    /**
     * Prueba para eliminar un Administrador.
     */
    @Test
    public void deleteAdministradorTest() {
        AdministradorEntity entity = data.get(0);
        administradorPersistence.delete(entity.getId());
        AdministradorEntity deleted = em.find(AdministradorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Administrador.
     */
    @Test
    public void updateAdministradorTest() {
        AdministradorEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        AdministradorEntity newEntity = factory.manufacturePojo(AdministradorEntity.class);

        newEntity.setId(entity.getId());

        administradorPersistence.update(newEntity);

        AdministradorEntity updated = em.find(AdministradorEntity.class, entity.getId());

  Assert.assertEquals(newEntity.getNombre(), updated.getNombre());
        Assert.assertEquals(newEntity.getApellido(), updated.getApellido());
        Assert.assertEquals(newEntity.getCargo(), updated.getCargo());
        Assert.assertEquals(newEntity.getNombre_usuario(), updated.getNombre_usuario());
        Assert.assertEquals(newEntity.getFecha_inicio(), updated.getFecha_inicio());
        Assert.assertEquals(newEntity.getContrasena(), updated.getContrasena());
        Assert.assertEquals(newEntity.getTelefono(), updated.getTelefono());
        Assert.assertEquals(newEntity.getCorreo(), updated.getCorreo());
        Assert.assertEquals(newEntity.getFecha_nacimiento() , updated.getFecha_nacimiento());
    }
}
