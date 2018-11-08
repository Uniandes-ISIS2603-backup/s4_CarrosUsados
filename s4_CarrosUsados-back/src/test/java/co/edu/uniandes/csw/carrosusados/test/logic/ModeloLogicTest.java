/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosusados.test.logic;

import co.edu.uniandes.csw.carrosusados.ejb.ModeloLogic;
import co.edu.uniandes.csw.carrosusados.entities.MarcaEntity;
import co.edu.uniandes.csw.carrosusados.entities.ModeloEntity;
import co.edu.uniandes.csw.carrosusados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosusados.persistence.ModeloPersistence;
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
 *
 * @author estudiante
 */
@RunWith(Arquillian.class)
public class ModeloLogicTest {
    
   private PodamFactory factory = new PodamFactoryImpl();

   @Inject
   ModeloLogic modeloLogic;
    
   @PersistenceContext
   private EntityManager em;

   @Inject
   private UserTransaction utx;
   
    
   private List<ModeloEntity> data = new ArrayList<ModeloEntity>();
   
   private List<MarcaEntity> marcaData = new ArrayList<MarcaEntity>();
   
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
        em.createQuery("delete from AutomovilEntity").executeUpdate();
        //em.createQuery("delete from MarcaEntity").executeUpdate();
        em.createQuery("delete from ModeloEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            MarcaEntity marcaEntity = factory.manufacturePojo(MarcaEntity.class);
            em.persist(marcaEntity);
            marcaData.add(marcaEntity);
        }
        for (int i = 0; i < 3; i++) {
            ModeloEntity entity = factory.manufacturePojo(ModeloEntity.class);
            entity.setMarca(marcaData.get(1));
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    /**
     * Prueba para crear un Modelo.
     */
    @Test
    public void createModeloTest() throws BusinessLogicException {
        
        ModeloEntity newEntity = factory.manufacturePojo(ModeloEntity.class);
        newEntity.setMarca(marcaData.get(1));
        ModeloEntity result = modeloLogic.createModelo(marcaData.get(0).getId(),newEntity);
        Assert.assertNotNull(result);
        ModeloEntity entity = em.find(ModeloEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNum_puertas(), entity.getNum_puertas());
        Assert.assertEquals(newEntity.getTransmision(), entity.getTransmision());
        Assert.assertEquals(newEntity.getCentrimetros_cubicos(), entity.getCentrimetros_cubicos());
    }
    
    /**
     * Prueba para consultar la lista de Modelos
     */
    @Test
    public void getModelosTest() throws BusinessLogicException {
        List<ModeloEntity> list = modeloLogic.getModelos(marcaData.get(1).getId());
        Assert.assertEquals(data.size(), list.size());
        for (ModeloEntity entity : list) {
            boolean found = false;
            for (ModeloEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Modelo.
     */
    @Test
    public void getModeloTest() throws BusinessLogicException {
        ModeloEntity entity = data.get(0);
        ModeloEntity resultEntity = modeloLogic.getModelo(marcaData.get(1).getId(), entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getNum_puertas(), entity.getNum_puertas());
        Assert.assertEquals(resultEntity.getTransmision(), entity.getTransmision());
        Assert.assertEquals(resultEntity.getCentrimetros_cubicos(), entity.getCentrimetros_cubicos());
    }
    
    /**
     * Prueba para actualizar un Modelo.
     */
    @Test
    public void updateModeloTest() throws BusinessLogicException {
        ModeloEntity entity = data.get(0);
        ModeloEntity pojoEntity = factory.manufacturePojo(ModeloEntity.class);

        pojoEntity.setId(entity.getId());

        MarcaEntity marcaEntity = marcaData.get(0);
        pojoEntity.setMarca(marcaEntity);
        modeloLogic.updateModelo(marcaData.get(0).getId(), pojoEntity);

        ModeloEntity resp = em.find(ModeloEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNum_puertas(), resp.getNum_puertas());
        Assert.assertEquals(pojoEntity.getTransmision(), resp.getTransmision());
        Assert.assertEquals(pojoEntity.getCentrimetros_cubicos(), resp.getCentrimetros_cubicos()); 
    }
    
    /**
     * Prueba para eliminar un Modelo
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteModeloTest() throws BusinessLogicException {
        ModeloEntity entity = data.get(0);
        modeloLogic.deleteModelo(marcaData.get(1).getId(), entity.getId());
        ModeloEntity deleted = em.find(ModeloEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para eliminarle un review a un book del cual no pertenece.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void deleteModeloConMarcaNoAsociadaTest() throws BusinessLogicException {
        ModeloEntity entity = data.get(0);
        modeloLogic.deleteModelo(marcaData.get(0).getId(), entity.getId());
    }
}
