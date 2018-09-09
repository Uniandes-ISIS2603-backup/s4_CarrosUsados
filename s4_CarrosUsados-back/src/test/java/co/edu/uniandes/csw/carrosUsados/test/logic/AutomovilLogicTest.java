/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.ejb.AutomovilLogic;
import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.entities.FichaTecnicaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.AutomovilPersistence;
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
 * @author juanestebanmendez
 */
@RunWith(Arquillian.class)
public class AutomovilLogicTest {
    
   private PodamFactory factory = new PodamFactoryImpl();

   @Inject
   AutomovilLogic automovilLogic;
    
   @PersistenceContext
   private EntityManager em;

   @Inject
   private UserTransaction utx;
   
    
   private List<AutomovilEntity> data = new ArrayList<AutomovilEntity>();

   /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AutomovilEntity.class.getPackage())
                .addPackage(AutomovilLogic.class.getPackage())
                .addPackage(AutomovilPersistence.class.getPackage())
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
        em.createQuery("delete from FichaTecnicaEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            AutomovilEntity entity = factory.manufacturePojo(AutomovilEntity.class);
            em.persist(entity);
            //Esto lo añadi yo
            FichaTecnicaEntity fichaTecnicaEntity = factory.manufacturePojo(FichaTecnicaEntity.class);
            entity.setFichaTecnica(fichaTecnicaEntity);
            fichaTecnicaEntity.setAutomovil(entity);
            data.add(entity);
        }
    }
    
    
    /**
     * Prueba para crear un Automovil.
     */
    @Test
    public void createAutomovilTest() throws BusinessLogicException {
        AutomovilEntity newEntity = factory.manufacturePojo(AutomovilEntity.class);
        AutomovilEntity result = automovilLogic.createAutomovil(newEntity);
        Assert.assertNotNull(result);
        AutomovilEntity entity = em.find(AutomovilEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getAnio(), entity.getAnio());
        Assert.assertEquals(newEntity.getCalificaciones(), entity.getCalificaciones());
        Assert.assertEquals(newEntity.getColor(), newEntity.getColor());
        Assert.assertEquals(newEntity.getFechaAgregacion(), newEntity.getFechaAgregacion());
        Assert.assertEquals(newEntity.getFichaTecnica(), newEntity.getFichaTecnica());
        Assert.assertEquals(newEntity.getMarca(), newEntity.getMarca());
        Assert.assertEquals(newEntity.getModelo(), newEntity.getModelo());
        Assert.assertEquals(newEntity.getNumChasis(), newEntity.getNumChasis());
        Assert.assertEquals(newEntity.getPlaca(), newEntity.getPlaca());
        Assert.assertEquals(newEntity.getPrecioOriginal(), newEntity.getPrecioOriginal());
    }
    
    /**
     * Prueba para consultar la lista de Automoviles
     */
    @Test
    public void getAutomovilesTest() throws BusinessLogicException {
        List<AutomovilEntity> list = automovilLogic.getAutomoviles();
        Assert.assertEquals(data.size(), list.size());
        for (AutomovilEntity entity : list) {
            boolean found = false;
            for (AutomovilEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Automovil.
     */
    @Test
    public void getAutomovilTest() throws BusinessLogicException {
        AutomovilEntity entity = data.get(0);
        AutomovilEntity resultEntity = automovilLogic.getAutomovil(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getAnio(), entity.getAnio());
        Assert.assertEquals(resultEntity.getCalificaciones(), entity.getCalificaciones());
        Assert.assertEquals(resultEntity.getColor(), entity.getColor());
        Assert.assertEquals(resultEntity.getFechaAgregacion(), entity.getFechaAgregacion());
        Assert.assertEquals(resultEntity.getFichaTecnica(), entity.getFichaTecnica());
        Assert.assertEquals(resultEntity.getMarca(), entity.getMarca());
        Assert.assertEquals(resultEntity.getModelo(), entity.getModelo());
        Assert.assertEquals(resultEntity.getNumChasis(), entity.getNumChasis());
        Assert.assertEquals(resultEntity.getPlaca(), entity.getPlaca());
        Assert.assertEquals(resultEntity.getPrecioOriginal(), entity.getPrecioOriginal());
    }
    
    /**
     * Prueba para actualizar un Automovil.
     */
    @Test
    public void updateAutomovilTest() throws BusinessLogicException {
        AutomovilEntity entity = data.get(0);
        AutomovilEntity pojoEntity = factory.manufacturePojo(AutomovilEntity.class);

        pojoEntity.setId(entity.getId());

        automovilLogic.updateAutomovil(pojoEntity.getId(), pojoEntity);

        AutomovilEntity resp = em.find(AutomovilEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getAnio(), resp.getAnio());
        Assert.assertEquals(pojoEntity.getCalificaciones(), resp.getCalificaciones());
        Assert.assertEquals(pojoEntity.getColor(), resp.getColor());
        Assert.assertEquals(pojoEntity.getFechaAgregacion(), resp.getFechaAgregacion());
        Assert.assertEquals(pojoEntity.getFichaTecnica(), resp.getFichaTecnica());
        Assert.assertEquals(pojoEntity.getMarca(), resp.getMarca());
        Assert.assertEquals(pojoEntity.getModelo(), resp.getModelo());
        Assert.assertEquals(pojoEntity.getNumChasis(), resp.getNumChasis());
        Assert.assertEquals(pojoEntity.getPlaca(), resp.getPlaca());
        Assert.assertEquals(pojoEntity.getPrecioOriginal(), resp.getPrecioOriginal());   
    }
    
    /**
     * Prueba para eliminar un Automovil
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteAutomovilTest() throws BusinessLogicException {
        AutomovilEntity entity = data.get(0);
        automovilLogic.deleteAutomovil(entity.getId());
        AutomovilEntity deleted = em.find(AutomovilEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    
}
