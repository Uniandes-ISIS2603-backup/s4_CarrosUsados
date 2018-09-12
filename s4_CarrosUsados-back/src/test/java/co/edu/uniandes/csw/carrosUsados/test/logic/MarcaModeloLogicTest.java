/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import co.edu.uniandes.csw.carrosUsados.ejb.MarcaModeloLogic;
import co.edu.uniandes.csw.carrosUsados.ejb.MarcaLogic;
import co.edu.uniandes.csw.carrosUsados.entities.MarcaEntity;
import co.edu.uniandes.csw.carrosUsados.entities.ModeloEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.MarcaPersistence;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class MarcaModeloLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private MarcaModeloLogic marcaModeloLogic;

    @Inject
    private MarcaLogic marcaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<MarcaEntity> data = new ArrayList<MarcaEntity>();

    private List<ModeloEntity> modelosData = new ArrayList();

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
        em.createQuery("delete from ModeloEntity").executeUpdate();
        em.createQuery("delete from MarcaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ModeloEntity modelos = factory.manufacturePojo(ModeloEntity.class);
            em.persist(modelos);
            modelosData.add(modelos);
        }
        for (int i = 0; i < 3; i++) {
            MarcaEntity entity = factory.manufacturePojo(MarcaEntity.class);
            em.persist(entity);
            data.add(entity);
            if (i == 0) {
                modelosData.get(i).setMarca(entity);
            }
        }
    }

    /**
     * Prueba para asociar un Modelo existente a una Marca.
     */
    @Test
    public void addModelosTest() {
        MarcaEntity entity = data.get(0);
        ModeloEntity modeloEntity = modelosData.get(1);
        ModeloEntity response = marcaModeloLogic.addModelo(modeloEntity.getId(), entity.getId());

        Assert.assertNotNull(response);
        Assert.assertEquals(modeloEntity.getId(), response.getId());
    }

    /**
     * Prueba para obtener una colección de instancias de modelos asociadas a una
     * instancia Marca.
     */
    @Test
    public void getModelosTest() {
        List<ModeloEntity> list = marcaModeloLogic.getModelos(data.get(0).getId());

        Assert.assertEquals(1, list.size());
    }  
    
    /**
     * Prueba para obtener una instancia de Modelo asociada a una instancia
     * Marca.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void getModeloTest() throws BusinessLogicException {
        MarcaEntity entity = data.get(0);
        ModeloEntity modeloEntity = modelosData.get(0);
        ModeloEntity response = marcaModeloLogic.getModelo(entity.getId(), modeloEntity.getId());

        Assert.assertEquals(modeloEntity.getId(), response.getId());
        Assert.assertEquals(modeloEntity.getNum_puertas(), response.getNum_puertas());
        Assert.assertEquals(modeloEntity.getTransmision(), response.getTransmision());
        Assert.assertEquals(modeloEntity.getCentrimetros_cubicos(), response.getCentrimetros_cubicos());
    }

    /**
     * Prueba para remplazar las instancias de Modelo asociadas a una instancia
     * de Marca.
     */
    @Test
    public void replaceModelosTest() throws BusinessLogicException{
        MarcaEntity entity = data.get(0);
        List<ModeloEntity> list = modelosData.subList(1, 3);
        marcaModeloLogic.replaceModelos(entity.getId(), list);

        entity = marcaLogic.getMarca(entity.getId());
        Assert.assertFalse(entity.getModelos().contains(modelosData.get(0)));
        Assert.assertTrue(entity.getModelos().contains(modelosData.get(1)));
        Assert.assertTrue(entity.getModelos().contains(modelosData.get(2)));
    }    
    
}
