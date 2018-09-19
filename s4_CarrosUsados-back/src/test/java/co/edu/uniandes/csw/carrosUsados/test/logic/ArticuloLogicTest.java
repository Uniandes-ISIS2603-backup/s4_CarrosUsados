/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.carrosUsados.test.logic;

import co.edu.uniandes.csw.carrosUsados.ejb.ArticuloLogic;
import co.edu.uniandes.csw.carrosUsados.entities.ArticuloEntity;
import co.edu.uniandes.csw.carrosUsados.entities.AutomovilEntity;
import co.edu.uniandes.csw.carrosUsados.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.carrosUsados.persistence.ArticuloPersistence;
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
public class ArticuloLogicTest {
    
   private PodamFactory factory = new PodamFactoryImpl();

   @Inject
   ArticuloLogic articuloLogic;
    
   @PersistenceContext
   private EntityManager em;

   @Inject
   private UserTransaction utx;
   
    
   private List<ArticuloEntity> data = new ArrayList<ArticuloEntity>();
   
   private List<AutomovilEntity> automovilData = new ArrayList<AutomovilEntity>();
   /**
     * @return Devuelve el jar que Arquillian va a desplegar en Payara embebido.
     * El jar contiene las clases, el descriptor de la base de datos y el
     * archivo beans.xml para resolver la inyección de dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ArticuloEntity.class.getPackage())
                .addPackage(ArticuloLogic.class.getPackage())
                .addPackage(ArticuloPersistence.class.getPackage())
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
        em.createQuery("delete from ArticuloEntity").executeUpdate();
        em.createQuery("delete from AutomovilEntity").executeUpdate();
    }
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ArticuloEntity entity = factory.manufacturePojo(ArticuloEntity.class);
            em.persist(entity);
            data.add(entity);
        }
        for (int i = 0; i < 3; i++) {
            AutomovilEntity automovilEntity = factory.manufacturePojo(AutomovilEntity.class);
            em.persist(automovilEntity);
            automovilData.add(automovilEntity);
        }
    }
    
    
    /**
     * Prueba para crear un Articulo.
     */
    @Test
    public void createArticuloTest() throws BusinessLogicException {
        
        ArticuloEntity newEntity = factory.manufacturePojo(ArticuloEntity.class);
        newEntity.setAutomovil(automovilData.get(0));
            
        ArticuloEntity result = articuloLogic.createArticulo(newEntity);
        Assert.assertNotNull(result);
        ArticuloEntity entity = em.find(ArticuloEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(newEntity.getUbicacion(), entity.getUbicacion());
        Assert.assertEquals(newEntity.getPrecio(), entity.getPrecio());
        Assert.assertEquals(newEntity.getAutomovil(), newEntity.getAutomovil());
    }
    
    /**
     * Prueba para consultar la lista de Articuloes
     */
    @Test
    public void getArticuloesTest() throws BusinessLogicException {
        List<ArticuloEntity> list = articuloLogic.getArticuloes();
        Assert.assertEquals(data.size(), list.size());
        for (ArticuloEntity entity : list) {
            boolean found = false;
            for (ArticuloEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un Articulo.
     */
    @Test
    public void getArticuloTest() throws BusinessLogicException {
        ArticuloEntity entity = data.get(0);
        ArticuloEntity resultEntity = articuloLogic.getArticulo(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getDescripcion(), entity.getDescripcion());
        Assert.assertEquals(resultEntity.getUbicacion(), entity.getUbicacion());
        Assert.assertEquals(resultEntity.getPrecio(), entity.getPrecio());
        Assert.assertEquals(resultEntity.getAutomovil(), resultEntity.getAutomovil());
    }
    
    /**
     * Prueba para actualizar un Articulo.
     */
    @Test
    public void updateArticuloTest() throws BusinessLogicException {
        ArticuloEntity entity = data.get(0);
        ArticuloEntity pojoEntity = factory.manufacturePojo(ArticuloEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setAutomovil(entity.getAutomovil());

        articuloLogic.updateArticulo(pojoEntity.getId(), pojoEntity);
        

        ArticuloEntity resp = em.find(ArticuloEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getDescripcion(), resp.getDescripcion());
        Assert.assertEquals(pojoEntity.getUbicacion(), resp.getUbicacion());
        Assert.assertEquals(pojoEntity.getPrecio(), resp.getPrecio());
        Assert.assertEquals(pojoEntity.getAutomovil(), resp.getAutomovil()); 
    }
    
    /**
     * Prueba para eliminar un Articulo
     *
     * @throws co.edu.uniandes.csw.bookstore.exceptions.BusinessLogicException
     */
    @Test
    public void deleteArticuloTest() throws BusinessLogicException {
        ArticuloEntity entity = data.get(0);
        articuloLogic.deleteArticulo(entity.getId());
        ArticuloEntity deleted = em.find(ArticuloEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
}
