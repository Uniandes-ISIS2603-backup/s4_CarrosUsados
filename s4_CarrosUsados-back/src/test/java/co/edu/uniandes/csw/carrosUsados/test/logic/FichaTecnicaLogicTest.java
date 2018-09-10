/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.ejb.FichaTecnicaLogic;
import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.entities.FichaTecnicaEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.FichaTecnicaPersistence;
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
public class FichaTecnicaLogicTest {
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private FichaTecnicaLogic fichaTecnicaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;
    
    private List<FichaTecnicaEntity> data = new ArrayList<FichaTecnicaEntity>();
    
    private List<AutomovilEntity> automovilData = new ArrayList<AutomovilEntity>();
    
    /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FichaTecnicaEntity.class.getPackage())
                .addPackage(FichaTecnicaLogic.class.getPackage())
                .addPackage(FichaTecnicaPersistence.class.getPackage())
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
        em.createQuery("delete from FichaTecnicaEntity").executeUpdate();
        em.createQuery("delete from AutomovilEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
        AutomovilEntity automovil = factory.manufacturePojo(AutomovilEntity.class);
            em.persist(automovil);
            automovilData.add(automovil);
        }
        for (int i = 0; i < 3; i++) {
            FichaTecnicaEntity fichaTecnica = factory.manufacturePojo(FichaTecnicaEntity.class);
            em.persist(fichaTecnica);
            data.add(fichaTecnica);
        }
    }
    
     /**
     * Prueba para crear una Ficha Tecnica
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void createFichaTecnicaTest() throws BusinessLogicException {
        FichaTecnicaEntity newEntity = factory.manufacturePojo(FichaTecnicaEntity.class);
        newEntity.setAutomovil(automovilData.get(0));
        FichaTecnicaEntity result = fichaTecnicaLogic.createFichaTecnica(newEntity);
        Assert.assertNotNull(result);
        FichaTecnicaEntity entity = em.find(FichaTecnicaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getAutomovil(), entity.getAutomovil());
        Assert.assertEquals(newEntity.getNumAirbags(), entity.getNumAirbags());
        Assert.assertEquals(newEntity.getRines(), entity.getRines());
        Assert.assertEquals(newEntity.getVidrios(), entity.getVidrios());
    }
    
    /**
     * Prueba para crear una Ficha Tecnica con un automovil que no existe.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test(expected = BusinessLogicException.class)
    public void createFichaTecnicaTestConAutomovilInexistente() throws BusinessLogicException {
        FichaTecnicaEntity newEntity = factory.manufacturePojo(FichaTecnicaEntity.class);
        AutomovilEntity automovilEntity = new AutomovilEntity();
        automovilEntity.setId(Long.MIN_VALUE);
        newEntity.setAutomovil(automovilEntity);
        fichaTecnicaLogic.createFichaTecnica(newEntity);
    }
    
    /**
     * Prueba para consultar una FichaTecnica.
     */
    @Test
    public void getFichaTecnicaTest() throws BusinessLogicException {
        FichaTecnicaEntity entity = data.get(0);
        FichaTecnicaEntity resultEntity = fichaTecnicaLogic.getFichaTecnica(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getAutomovil(), resultEntity.getAutomovil());
        Assert.assertEquals(entity.getNumAirbags(), resultEntity.getNumAirbags());
        Assert.assertEquals(entity.getRines(), resultEntity.getRines());
        Assert.assertEquals(entity.getVidrios(), resultEntity.getVidrios());
    }
    
    /**
     * Prueba para actualizar una Ficha Tecnica.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void updateFichaTecnicaTest() throws BusinessLogicException {
        FichaTecnicaEntity entity = data.get(0);
        FichaTecnicaEntity pojoEntity = factory.manufacturePojo(FichaTecnicaEntity.class);
        pojoEntity.setAutomovil(automovilData.get(1));
        pojoEntity.setId(entity.getId());
        fichaTecnicaLogic.updateFichaTecnica(pojoEntity.getId(), pojoEntity);
        FichaTecnicaEntity resp = em.find(FichaTecnicaEntity.class, entity.getId());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getAutomovil(), resp.getAutomovil());
        Assert.assertEquals(pojoEntity.getNumAirbags(), resp.getNumAirbags());
        Assert.assertEquals(pojoEntity.getRines(), resp.getRines());
        Assert.assertEquals(pojoEntity.getVidrios(), resp.getVidrios());
    }
    
    
    
    /**
     * Prueba para eliminar una ficha tecnica.
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteFichaTecnicaTest() throws BusinessLogicException {
        FichaTecnicaEntity entity = data.get(0);
        fichaTecnicaLogic.deleteFichaTecnica(entity.getId());
        FichaTecnicaEntity deleted = em.find(FichaTecnicaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
