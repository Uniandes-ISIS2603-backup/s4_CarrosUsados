/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.test.logic;

import co.edu.uniandes.csw.carrosusados.ejb.MarcaLogic;
import co.edu.uniandes.csw.carrosusados.entities.MarcaEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.persistence.MarcaPersistence;
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
public class MarcaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    MarcaLogic marcaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;


    private List<MarcaEntity> data = new ArrayList<MarcaEntity>();

    //private List<MarcaEntity> marcaData = new ArrayList<MarcaEntity>();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MarcaEntity.class.getPackage())
                .addPackage(MarcaLogic.class.getPackage())
                .addPackage(MarcaPersistence.class.getPackage())
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
        em.createQuery("delete from MarcaEntity").executeUpdate();
        em.createQuery("delete from ModeloEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            MarcaEntity entity = factory.manufacturePojo(MarcaEntity.class);
            em.persist(entity);
            data.add(entity);
        }
    }


    /**
     * Prueba para crear una Marca.
     */
    @Test
    public void createMarcaTest() throws BusinessLogicException {

        MarcaEntity newEntity = factory.manufacturePojo(MarcaEntity.class);

        MarcaEntity result = marcaLogic.createMarca(newEntity);
        Assert.assertNotNull(result);
        MarcaEntity entity = em.find(MarcaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getPais(), entity.getPais());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
    }

    /**
     * Prueba para consultar la lista de Marcas
     */
    @Test
    public void getMarcasTest() throws BusinessLogicException {
        List<MarcaEntity> list = marcaLogic.getMarcas();
        Assert.assertEquals(data.size(), list.size());
        for (MarcaEntity entity : list) {
            boolean found = false;
            for (MarcaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Marca.
     */
    @Test
    public void getMarcaTest() throws BusinessLogicException {
        MarcaEntity entity = data.get(0);
        MarcaEntity resultEntity = marcaLogic.getMarca(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(resultEntity.getPais(), entity.getPais());
        Assert.assertEquals(resultEntity.getDescripcion(), entity.getDescripcion());
    }

    /**
     * Prueba para actualizar una Marca.
     */
    @Test
    public void updateMarcaTest() throws BusinessLogicException {
        MarcaEntity entity = data.get(0);
        MarcaEntity pojoEntity = factory.manufacturePojo(MarcaEntity.class);

        pojoEntity.setId(entity.getId());

        marcaLogic.updateMarca(pojoEntity.getId(), pojoEntity);

        MarcaEntity resp = em.find(MarcaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getPais(), resp.getPais());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
    }

    /**
     * Prueba para eliminar una Marca
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteMarcaTest() throws BusinessLogicException {
        MarcaEntity entity = data.get(0);
        marcaLogic.deleteMarca(entity.getId());
        MarcaEntity deleted = em.find(MarcaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

}
