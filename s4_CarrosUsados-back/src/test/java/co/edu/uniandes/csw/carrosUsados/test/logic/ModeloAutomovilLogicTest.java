/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.ejb.ModeloLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.ModeloAutomovilLogic;
import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.entities.ModeloEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.ModeloPersistence;
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
public class ModeloAutomovilLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ModeloAutomovilLogic modeloAutomovilLogic;

    @Inject
    private ModeloLogic modeloLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ModeloEntity> data = new ArrayList<ModeloEntity>();

    private List<AutomovilEntity> automovilesData = new ArrayList();

    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ModeloEntity.class.getPackage())
                .addPackage(ModeloLogic.class.getPackage())
                .addPackage(ModeloPersistence.class.getPackage())
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
        em.createQuery("delete from ModeloEntity").executeUpdate();
        em.createQuery("delete from MarcaEntity").executeUpdate();
        em.createQuery("delete from AutomovilEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            AutomovilEntity autos = factory.manufacturePojo(AutomovilEntity.class);
            em.persist(autos);
            automovilesData.add(autos);
        }
        for (int i = 0; i < 3; i++) {
            ModeloEntity entity = factory.manufacturePojo(ModeloEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                automovilesData.get(i).setModeloAsociado(entity);
            }
        }
    }

    /**
     * Prueba para asociar un Automovil existente a un Modelo.
     */
    @Test
    public void addAutomovilTest() {
        ModeloEntity entity = data.get(0);
        AutomovilEntity automovilEntity = automovilesData.get(1);
        AutomovilEntity response = modeloAutomovilLogic.addAutomovil(automovilEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(automovilEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de automoviles asociadas a una
     * instancia Modelo.
     */
    @Test
    public void getAutomovilesTest() {
        List<AutomovilEntity> list = modeloAutomovilLogic.getAutomoviles(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }  
    
    /**
     * Prueba para obtener una instancia de automovil asociada a una instancia
     * Modelo.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getAutomovilTest() throws BusinessLogicException {
        ModeloEntity entity = data.get(0);
        AutomovilEntity automovilEntity = automovilesData.get(0);
        AutomovilEntity response = modeloAutomovilLogic.getAutomovil(entity.getId(), automovilEntity.getId());

        Assert.assertEquals(automovilEntity.getId(), response.getId());
        Assert.assertEquals(automovilEntity.getPlaca(), response.getPlaca());
        Assert.assertEquals(automovilEntity.getNumChasis(), response.getNumChasis());
    }

    /**
     * Prueba para remplazar las instancias de automovil asociadas a una instancia
     * de Modelo.
     */
    @Test
    public void replaceAutomovilesTest() throws BusinessLogicException{
        ModeloEntity entity = data.get(0);
        List<AutomovilEntity> list = automovilesData.subList(1, 3);
        modeloAutomovilLogic.replaceAutomoviles(entity.getId(), list);

        entity = modeloLogic.getModelo(entity.getId());
        Assert.assertFalse(entity.getAutomoviles().contains(automovilesData.get(0)));
        Assert.assertTrue(entity.getAutomoviles().contains(automovilesData.get(1)));
        Assert.assertTrue(entity.getAutomoviles().contains(automovilesData.get(2)));
    } 
    
}
